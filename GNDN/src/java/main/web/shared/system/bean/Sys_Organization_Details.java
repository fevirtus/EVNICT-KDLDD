package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.system.S_Organization;
import main.remote.shared.system.ISysRemote;
import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageBean;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import main.web.converters.DoubleConverter;
import main.web.shared.dialog.bean.OrganizationDialog;
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
public class Sys_Organization_Details extends BasePageBean implements Serializable {

    private ISysRemote iSys = null;
    private S_Organization m_S_Organization;
    private S_Organization m_Parent;
    private ResourcesFactory m_RfOrganizationProp;
    //Check nhập
    String m_Style_TxtCMID;
    String m_Style_TxtAssetID;
    String m_Style_TxtMeterID;
    String m_MsgCheck;

    //{{<editor-fold defaultstate="collapsed" desc="Contructor">
    public Sys_Organization_Details() {
        m_Parent = new S_Organization();
        if (m_RfOrganizationProp == null) {
            m_RfOrganizationProp = new ResourcesFactory("main/web/shared/system/prop/SystemProp");
        }
        if (!isPostback()) {
            String orgid = WorkingContext.getRequestQueryString("ORGID");
            if (orgid != null) {
                LoadOrganizationDetail(orgid);
                setPageDetailInfo(enumResultSetPosition.eInit);
            } 
            else {
                AddNewOrganizationDetail();
            }
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Functions">
    public void CleanScreen() {
        m_S_Organization = new S_Organization();
        m_S_Organization.setActive(true);
        m_Parent = new S_Organization();
        resetInputRequire();
    }

    /**
     * Load đơn vị
     * @param orgid
     */
    public void LoadOrganizationDetail(String orgid) {
        try {
            CleanScreen();
            setFormMode(enumFormMode.eView);
            //Caller
            m_S_Organization = (S_Organization) getISysRemote().findById(orgid, S_Organization.class);
            if (m_S_Organization != null) {
                m_Parent = m_S_Organization.getsOrgidParent();
                if (m_Parent==null)
                    m_Parent = new S_Organization();
            } else {
                m_bDelete = true;
                m_bSave = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            WorkingContext.showMessages(enumMessageMode.eError, null, ex.getMessage());
        }
    }

    public void AddNewOrganizationDetail() {
        CleanScreen();
        setFormMode(enumFormMode.eAddNew);
        m_bSave = false;
    }    

    private boolean CheckInput() {
        try {
            resetInputRequire();
            if (m_S_Organization == null) {
                return false;
            }
            boolean bCheck = true;
            S_Organization org = m_S_Organization;
            //Check nhap desc cho Don vi
            if (org.getOrgdesc() == null || org.getOrgdesc().trim().isEmpty()) {
                bCheck = false;
                m_Style_TxtCMID = "error";
            }

            //Check nhap ten viet tat cho Don vi
            if (org.getOrgcode() == null || org.getOrgcode().trim().isEmpty()) {
                bCheck = false;
                m_Style_TxtAssetID = "error";
            }
            
            if (!bCheck) {
                m_MsgCheck = m_rfCommon.getMessage("msgNotEnoughDataInput");
                return false;
            }

            //Mã đã tồn tại
            if (getFormMode().equals(enumFormMode.eAddNew)) {
                S_Organization Temp = (S_Organization) getISysRemote().findById(m_S_Organization.getOrgid(), S_Organization.class);
                if (Temp != null) {
                    m_Style_TxtCMID = "error";
                    WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgIDExist"));
                    return false;
                }
            }

            /*
             * Valid du lieu nhap
             */
            if (!checkParentOrg())
                return false;
            
            //Vùng thay đổi
            if (getFormMode() == enumFormMode.eAddNew) {                

                /*
                 * Sinh mã
                 */
                //Sinh key
                ISystemCommonRemote iscr = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
                S_Key_ControlInfo kc = new S_Key_ControlInfo(m_S_Organization.getOrgid(), S_Organization.class.getSimpleName());
                kc = iscr.getGenKeyID(kc);
                if (kc == null || kc.getGenStatus() != 0) //Không thành công
                {
                    showErrorFromGenKey(kc, Sys_Organization_Details.class.getName() + ".CheckInput()");
                    return false;
                }
                m_S_Organization.setOrgid(kc.getOutputValue());
            } else {
//                m_Op_Condition_Monitor.setUserMdfId(WorkingContext.getUserName());
//                m_Op_Condition_Monitor.setUserMdfDtime(new Date());
            }
            return bCheck;
        } catch (Exception e) {
            showErrorFromException(e, Sys_Organization_Details.class.getName() + ".CheckInput()");
            return false;
        }
    }

    /**
     * Cập nhật mảng id khi thêm mới, xóa
     * @param type 0: thêm mới; 1: xóa
     */
    private void UpdateListOrganization(int type,String cmID){
        List<String> lstCMID = (List<String>) WorkingContext.getSessionValue(WorkingContext.LIST_S_ORGANIZATION);
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
                        LoadOrganizationDetail(lstCMID.get(idxNew));
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
        WorkingContext.setSessionValue(WorkingContext.LIST_S_ORGANIZATION,lstCMID);
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Override">
    @Override
    protected void setPageDetailInfo(enumResultSetPosition resultSetPosition) {
        try {
            List<String> lstCMID = (List<String>) WorkingContext.getSessionValue(WorkingContext.LIST_S_ORGANIZATION);
            setDisableNavigator(true);
            if (lstCMID != null && m_S_Organization != null) {
                if (!lstCMID.isEmpty() && m_S_Organization.getOrgid() != null) {
                    //Tìm vị trí hiện tại
                    String cmID = m_S_Organization.getOrgid();
                    int idx = lstCMID.indexOf(cmID);
                    if (idx >= 0 && idx < lstCMID.size()) {
                        if (resultSetPosition.equals(enumResultSetPosition.eInit)) {
                            updateNavigator(idx, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eFirst)) {
                            LoadOrganizationDetail(lstCMID.get(0));
                            updateNavigator(0, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.ePrevious)) {
                            idx--;
                            if (idx >= 0) {
                                LoadOrganizationDetail(lstCMID.get(idx));
                            }
                            updateNavigator(idx, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eNext)) {
                            idx++;
                            if (idx < lstCMID.size()) {
                                LoadOrganizationDetail(lstCMID.get(idx));
                            }
                            updateNavigator(idx, lstCMID);
                        } else {//last
                            LoadOrganizationDetail(lstCMID.get(lstCMID.size() - 1));
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
    public void onAddNewOrganization(ActionEvent event) {
        try {
            AddNewOrganizationDetail();
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_Organization_Details.class.getName() + ".onAddNewOrganization()");
        }
    }

    public void onSaveOrganization(ActionEvent event) {
        try {
            if (!CheckInput()) {
                if(!m_MsgCheck.isEmpty())
                    WorkingContext.showMessages(enumMessageMode.eWarn, null, m_MsgCheck);
                return;
            }
            if (!checkID_Standard(m_S_Organization.getOrgid()))
                return;
            //Lưu
            if (getFormMode() == enumFormMode.eAddNew) {
                if (getISysRemote() != null) {
                    if (getISysRemote().insert(m_S_Organization)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                        //List
                        UpdateListOrganization(0,m_S_Organization.getOrgid());
                        //LoadMeterDataCurrent();
                    }else
                        WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgSaveError"));

                }
            }else{
                if (getISysRemote() != null) {
                    if (getISysRemote().update(m_S_Organization)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                        //LoadMeterDataCurrent();
                    }else
                        WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgSaveError"));
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_Organization_Details.class.getName() + ".onSaveOrganization()");
        }
    }

    public void onDeleteOrganization(ActionEvent event) {
        try {
            if (m_S_Organization.getOrgid()!=null && getISysRemote()!=null) {
                String cmid = m_S_Organization.getOrgid();
                //if (getISysRemote().delete(cmid, S_Organization.class)) {
                if (getISysRemote().deleteOrganization(cmid)) {
                    WorkingContext.showMessages(enumMessageMode.eInfo,null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    CleanScreen();
                    UpdateListOrganization(1,cmid);
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

    public void onAcceptOrganizationClick(ActionEvent event) {

        List<S_Organization> lst = OrganizationDialog.getSelected();
        if(lst!=null)
            if(!lst.isEmpty()){
                S_Organization org = lst.get(0);
                m_S_Organization.setOrgidParent(org.getOrgid());
                m_Parent=org;
            }
    }
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

    public S_Organization getParent() {
        return m_Parent;
    }

    public void setParent(S_Organization m_Parent) {
        this.m_Parent = m_Parent;
    }

    public S_Organization getOrganization() {
        return m_S_Organization;
    }

    public void setOrganization(S_Organization m_S_Organization) {
        this.m_S_Organization = m_S_Organization;
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

    protected boolean checkParentOrg()
    {
        if (m_Parent.getOrgid() != null && !m_Parent.getOrgid().isEmpty())
        {
            //Kiểm tra tồn tại mã nhập vào
            S_Organization org = (S_Organization) getISysRemote().findById(m_Parent.getOrgid(), S_Organization.class);
            if(org==null)
            {
                WorkingContext.showMessages(enumMessageMode.eInfo, null,m_RfOrganizationProp.getMessage("msgOrgParentFalse"));
                return false;
            }
            //Nếu nhập mã đơn vị cha trùng với mã đơn vị con
            if(m_S_Organization.getOrgid().equals(m_Parent.getOrgid())){
                m_Style_TxtMeterID = "error";
                m_MsgCheck = m_RfOrganizationProp.getMessage("detail_MsgOrgInvalid");
                return false;
            }
            // chọn cấp con bên dưới làm cấp cha cho node cha
            if (getISysRemote().doesOrgHasChild(m_S_Organization.getOrgid(),m_Parent.getOrgid())){
                m_Style_TxtMeterID = "error";
                WorkingContext.showMessages(enumMessageMode.eError, null, m_RfOrganizationProp.getMessage("msgOrgChildNotParent"));
                return false;
            }
            //Tất cả hợp lệ
            m_Parent=org;
            m_S_Organization.setOrgidParent(org.getOrgid());
        }
        else {
            m_Parent.setOrgdesc("");
            m_S_Organization.setOrgidParent(null);
        }

        return true;
    }

//    public String getOrgDesc() {
//        if (m_S_Organization.getOrgidParent()!=null){
//            if (!m_S_Organization.getOrgidParent().isEmpty()){
//                S_Organization org = (S_Organization) getISysRemote().findById(m_S_Organization.getOrgidParent(), S_Organization.class);
//                if(org!=null)
//                    return org.getOrgdesc();
//            }
//        }
//
//        return null;
//    }
    //}}</editor-fold>
}
