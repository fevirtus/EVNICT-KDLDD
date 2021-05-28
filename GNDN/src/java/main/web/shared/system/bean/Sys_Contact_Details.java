package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.system.S_Company;
import main.entity.shared.system.S_Company_Contact;
import main.remote.shared.system.ISysRemote;
import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageBean;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import main.web.converters.DoubleConverter;
import evnit.util.common.BaseConstant;
import evnit.util.common.BaseConstant.enumFormMode;
import evnit.util.common.BaseConstant.enumMessageMode;
import evnit.util.common.BaseConstant.enumResultSetPosition;
import evnit.util.common.S_Key_ControlInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author LanTH
 */
@ManagedBean
@ViewScoped
public class Sys_Contact_Details extends BasePageBean implements Serializable {

    private ISysRemote iSys = null;
    private S_Company_Contact m_S_Contact;
    //private S_Dept m_Parent;
    private ResourcesFactory m_RfDeptProp;
    String contactid;
    String compid;
    //Check nhập
    String m_Style_TxtCMID;
    String m_Style_TxtAssetID;
    String m_Style_TxtMeterID;
    String m_MsgCheck;
    List<String> mLstContactID; //ThảoDD sửa lại: thêm biến để sử dụng cơ chế truyền danh sách qua session và gỡ bỏ luôn

    private enum enumAction
    {
        eNone,
        eAdd,
        eEdit,
        eDelete,
        eChangeStatus,
        eDuplicate
    }
    private enumAction m_eActionCurrent=enumAction.eNone; //Biến lưu thao tác hiện tại

    //{{<editor-fold defaultstate="collapsed" desc="Contructor">
    public Sys_Contact_Details() {
        //m_Parent = new S_Dept();
        if (m_RfDeptProp == null) {
            m_RfDeptProp = new ResourcesFactory("main/web/shared/system/prop/SystemProp");
        }
        if (!isPostback()) {
            contactid = WorkingContext.getRequestQueryString("CONTACTID");
            compid = (String) WorkingContext.getRequestQueryString("COMPID");
            mLstContactID = (List<String>) WorkingContext.getSessionValueAndRemove(WorkingContext.LIST_S_COMPANY_CONTACT);
            if (contactid != null) {
                LoadContactDetail(contactid);
                fDtShowEditCONTACT(contactid);
                setPageDetailInfo(enumResultSetPosition.eInit);
            } 
            else {                
                //hien thi contact
                if (compid != null) {
                    AddNewContactDetail();
                }
            }
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Functions">
    public void CleanScreen() {
        m_S_Contact = new S_Company_Contact();        
        //m_Parent = new S_Dept();
        resetInputRequire();
    }

    /**
     * Load danh sách liên hệ
     * @param contactid
     */
    public void LoadContactDetail(String contactid) {
        try {
            CleanScreen();
            setFormMode(enumFormMode.eView);
            //Caller
            m_S_Contact = (S_Company_Contact) getISysRemote().findById(contactid, S_Company_Contact.class);
            if (m_S_Contact != null) {
                compid = m_S_Contact.getCompid();
            } else {
                m_bDelete = true;
                m_bSave = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            WorkingContext.showMessages(enumMessageMode.eError, null, ex.getMessage());
        }
    }

    public void AddNewContactDetail() {
        CleanScreen();
        setFormMode(enumFormMode.eAddNew);
        m_bSave = false;
        m_S_Contact.setCompid(compid);
    }    

    private boolean CheckInput() {
        try {
            resetInputRequire();
            if (m_S_Contact == null) {
                return false;
            }
            boolean bCheck = true;
            S_Company_Contact cont = m_S_Contact;
            //Check nhap desc cho Phong ban/Bo phan
            if (cont.getContactdesc() == null || cont.getContactdesc().trim().isEmpty()) {
                bCheck = false;
                m_Style_TxtCMID = "error";
            }            
            
            if (!bCheck) {
                m_MsgCheck = m_rfCommon.getMessage("msgNotEnoughDataInput");
                return false;
            }

            /*
             * Valid du lieu nhap
             */            
            
            
            //Vùng thay đổi
            if (getFormMode() == enumFormMode.eAddNew) {                

                /*
                 * Sinh mã
                 */
                //Sinh key
                ISystemCommonRemote iscr = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
                S_Key_ControlInfo kc = new S_Key_ControlInfo(m_S_Contact.getContactid(), S_Company_Contact.class.getSimpleName());
                kc = iscr.getGenKeyID(kc);
                if (kc == null || kc.getGenStatus() != 0) //Không thành công
                {
                    showErrorFromGenKey(kc, Sys_Contact_Details.class.getName() + ".CheckInput()");
                    return false;
                }
                m_S_Contact.setContactid(kc.getOutputValue());
            } 
            return bCheck;
        } catch (Exception e) {
            showErrorFromException(e, Sys_Contact_Details.class.getName() + ".CheckInput()");
            return false;
        }
    }

    /**
     * Cập nhật mảng id khi thêm mới, xóa
     * @param type 0: thêm mới; 1: xóa
     */
    private void UpdateListContact(int type,String cmID){
        List<String> lstCMID = mLstContactID;// (List<String>) WorkingContext.getSessionValue(WorkingContext.LIST_S_COMPANY_CONTACT);
        if (lstCMID == null)
            lstCMID = new ArrayList<String>();
        switch(type){
            case 0://Them moi
                lstCMID.add(cmID);
                updateNavigator(lstCMID.size() - 1, lstCMID);
                break;
            case 1://Delete
                if(lstCMID.contains(cmID)){
                    int idx = lstCMID.indexOf(cmID);
                    lstCMID.remove(cmID);
                    //Mảng còn
                    int idxNew = 0;
                    if (idx < lstCMID.size()-1)
                        idxNew = idx +1;
                    else if(idx >= lstCMID.size()-1)
                        idxNew = lstCMID.size()-1;
                    else if(idx>0)
                        idxNew = idx -1;
                    if (idxNew >= 0 && idxNew < lstCMID.size()){
                        LoadContactDetail(lstCMID.get(idxNew));
                        updateNavigator(idxNew, lstCMID);
                    }else{
                        setFormMode(enumFormMode.eInit);
                    }
                }else{
                    setPageDetailInfo(enumResultSetPosition.eFirst);
                }
                break;
            default:
                break;
        }
        //WorkingContext.setSessionValue(WorkingContext.LIST_S_COMPANY_CONTACT,lstCMID);
    }

    public boolean  getDtIsDisabledSaveMain()
    {
         if (m_eActionCurrent!=enumAction.eAdd)
            if  (getDtIsDisabledEditDataMain())
                return true;

        return false;
    }


    public boolean  getDtIsDisabledEditDataMain()
    {
        if (!getIsAddNew()) {
            return !checkRightEditOrg(m_S_Contact.getsCompany().getOrgid());
        }
        return true;
    }

    public boolean getIsAddNew() {
        if (getFormMode().equals(enumFormMode.eAddNew)) {
            return true;
        }
        return false;
    }

    protected final void fDtShowEditCONTACT(String sCONTACT) {
        try {
            //ISafetyRemote ipm = getICollectionRemote();
            S_Company_Contact m_dtCurrentNew = (S_Company_Contact) getISysRemote().findById(sCONTACT, S_Company_Contact.class);
            if (m_dtCurrentNew == null) {
                showError(getISysRemote().getLastActionInfo(), Sys_Contact_Details.class.getName() + ".fDtShowEditCONTACT()");
                setFormMode(enumFormMode.eView);
                return;
            }
            //Kiem tra quyen truy cap
            if (!checkRightAccessOrg(m_dtCurrentNew.getsCompany().getOrgid())) {
                showError(m_rfCommon.getMessage("msgNotAccessOnOrg") + " (ID=" + m_dtCurrentNew.getsCompany().getOrgid() + ")", Sys_Contact_Details.class.getName() + ".fDtShowEditCOMP()");
                m_dtCurrentNew = null;
                setFormMode(enumFormMode.eView);
                CleanScreen();
                return;
            }

            //m_dtCurrentNew.initAllObjForEdit();
            setFormMode(enumFormMode.eUpdate); //quan trọng: Cập nhật lại kiểu
            //refresh cac tab
            //fDtRefreshTab();
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_Contact_Details.class.getName() + ".fDtShowEditCONTACT()");
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Override">
    @Override
    protected void setPageDetailInfo(enumResultSetPosition resultSetPosition) {
        try {
            List<String> lstCMID = mLstContactID;//(List<String>) WorkingContext.getSessionValue(WorkingContext.LIST_S_COMPANY_CONTACT);
            setDisableNavigator(true);
            if (lstCMID != null && m_S_Contact != null) {
                if (!lstCMID.isEmpty() && m_S_Contact.getContactid() != null) {
                    //Tìm vị trí hiện tại
                    String cmID = m_S_Contact.getContactid();
                    int idx = lstCMID.indexOf(cmID);
                    if (idx >= 0 && idx < lstCMID.size()) {
                        if (resultSetPosition.equals(enumResultSetPosition.eInit)) {
                            fDtShowEditCONTACT(lstCMID.get(idx));
                            updateNavigator(idx, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eFirst)) {
                            LoadContactDetail(lstCMID.get(0));
                            fDtShowEditCONTACT(lstCMID.get(idx));
                            updateNavigator(0, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.ePrevious)) {
                            idx--;
                            if (idx >= 0) {
                                LoadContactDetail(lstCMID.get(idx));
                            }
                            fDtShowEditCONTACT(lstCMID.get(idx));
                            updateNavigator(idx, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eNext)) {
                            idx++;
                            if (idx < lstCMID.size()) {
                                LoadContactDetail(lstCMID.get(idx));
                            }
                            fDtShowEditCONTACT(lstCMID.get(idx));
                            updateNavigator(idx, lstCMID);
                        } else {//last
                            LoadContactDetail(lstCMID.get(lstCMID.size() - 1));
                            fDtShowEditCONTACT(lstCMID.get(idx));
                            updateNavigator(lstCMID.size() - 1, lstCMID);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void resetInputRequire() {
        m_Style_TxtCMID = "";
        m_Style_TxtAssetID = "";
        m_Style_TxtMeterID = "";
        m_MsgCheck = "";
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Events">
    public void onAddNewContact(ActionEvent event) {
        try {
            AddNewContactDetail();
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_Contact_Details.class.getName() + ".onAddNewContact()");
        }
    }

    public void onSaveContact(ActionEvent event) {
        try {
            if (!CheckInput()) {
                if(!m_MsgCheck.isEmpty())
                    WorkingContext.showMessages(enumMessageMode.eWarn, null, m_MsgCheck);
                return;
            }
            //Lưu
            if (getFormMode() == enumFormMode.eAddNew) {
                if (getISysRemote() != null) {
                    if (getISysRemote().insert(m_S_Contact)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                        //List
                        UpdateListContact(0,m_S_Contact.getContactid());
                        //LoadMeterDataCurrent();
                    }else
                        WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgSaveError"));

                }
            }else{
                if (getISysRemote() != null) {
                    if (getISysRemote().update(m_S_Contact)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                        //LoadMeterDataCurrent();
                    }else
                        WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgSaveError"));
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_Contact_Details.class.getName() + ".onSaveContact()");
        }
    }

    public void onDeleteContact(ActionEvent event) {
        try {
            if (m_S_Contact.getContactid()!=null && getISysRemote()!=null) {
                String cmid = m_S_Contact.getContactid();
                if (getISysRemote().delete(cmid, S_Company_Contact.class)) {
                    WorkingContext.showMessages(enumMessageMode.eInfo,null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    CleanScreen();
                    UpdateListContact(1,cmid);
                } else {
                    WorkingContext.showMessages(enumMessageMode.eError,null, m_rfCommon.getMessage("msgDeleteError")
                            + BaseConstant.CRLF()+ getISysRemote().getLastActionInfo());
                }
            }
        } catch (Exception ex) {
            WorkingContext.showMessages(enumMessageMode.eError,null, m_rfCommon.getMessage("msgDeleteError"));
            //showErrorFromException(ex, Sys_Organization_Details.class.getName() + ".onDeleteOrganization()");
        }
    }

//    public void onAcceptDeptClick(ActionEvent event) {
//
//        List<S_Dept> lst = DeptDialog.getSelected();
//        if(lst!=null)
//            if(!lst.isEmpty()){
//                S_Dept dept = lst.get(0);
//                m_Parent.setDeptid(dept.getDeptid());
//                m_Parent.setDeptdesc(dept.getDeptdesc());
//            }
//    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Properties">
    public ISysRemote getISysRemote(){
        try {
            if (iSys == null) {
                iSys = (ISysRemote) EjbContext.getLocalEJBRemote(ISysRemote.class.getSimpleName());
            }else{
            EjbContext.LoginEJB();//Login lại khi gọi ejb
            }
        } catch (Exception ex) {
            return null;
        }
        return iSys;
    }    

//    public S_Dept getParent() {
//        return m_Parent;
//    }
//
//    public void setParent(S_Dept m_Parent) {
//        this.m_Parent = m_Parent;
//    }

    public S_Company_Contact getContact() {
        return m_S_Contact;
    }

    public void setContact(S_Company_Contact m_S_Contact) {
        this.m_S_Contact = m_S_Contact;
    }

    public Boolean getTxtCMIDReadOnly() {
        if (getFormMode() == enumFormMode.eAddNew) {
            return false;
        }
        return true;
    }

    public String getStyle_TxtAssetID() {
        return m_Style_TxtAssetID;
    }

    public String getStyle_TxtCMID() {
        return m_Style_TxtCMID;
    }

    public String getStyle_TxtMeterID() {
        return m_Style_TxtMeterID;
    }    

    public DoubleConverter getDoubleConverter(){
        DoubleConverter converter = new DoubleConverter();
        converter.setPatern_EN("#,##0.0###");
        return converter;
    }

    public String getCompDesc() {
        if(m_S_Contact.getContactid()!=null){
            S_Company comp = (S_Company) getISysRemote().findById(m_S_Contact.getCompid(), S_Company.class);
            return comp.getCompdesc();
        }
        else{
            S_Company co = (S_Company) getISysRemote().findById(compid, S_Company.class);
            return co.getCompdesc();
        }
    }
    //}}</editor-fold>
}
