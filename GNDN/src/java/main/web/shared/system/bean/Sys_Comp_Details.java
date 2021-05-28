package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.system.S_Company;
import main.entity.shared.system.S_Company_Contact;
import main.entity.shared.system.S_Company_Type;
import main.entity.shared.system.S_Organization;
import main.remote.shared.system.ISysRemote;
import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageBean;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import main.web.converters.DoubleConverter;
import main.web.shared.dialog.bean.CompanyDialog;
import main.web.shared.dialog.bean.OrganizationDialog;
import evnit.util.common.BaseConstant;
import evnit.util.common.BaseConstant.enumFormMode;
import evnit.util.common.BaseConstant.enumMessageMode;
import evnit.util.common.BaseConstant.enumResultSetPosition;
import evnit.util.common.S_Key_ControlInfo;
import evnit.util.common.Tools;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.PrimeFaces;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author LanTH
 */
@ManagedBean
@ViewScoped
public class Sys_Comp_Details extends BasePageBean implements Serializable {

    private ISysRemote iSys = null;
    private S_Company m_S_Company;
    private S_Company m_Parent;
    private List<S_Company_Contact> m_LstContact;
    private S_Company_Contact[] arrSelectContact;
    private ResourcesFactory m_RfDeptProp;
    private TabView m_TabView;
    //String compid; //ThảoDD sửa không dùng biến này, dùng m_S_Company
    private String m_sDeleteMsg;
    private String[] m_ArrCompanyTypeIDSelected;//select trên listbox
    private List<S_Company_Type> m_LstCompanyType;//list các loại hình công ty
    private List<S_Company_Type> m_LstCompanyTypeForCombo;//list các loại hình công ty cho combo box
    private String selectedTypeCompany = "";//Loại hình công ty chọn từ combobox
    private boolean view = false;
    //RequestContext context ;
    //Check nhập
    String m_Style_TxtCMID;
    String m_Style_TxtORGID;
    String m_Style_TxtMeterID;
    String m_MsgCheck;
    
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
    public Sys_Comp_Details() {
        m_Parent = new S_Company();
        if (m_RfDeptProp == null) {
            m_RfDeptProp = new ResourcesFactory("main/web/shared/system/prop/SystemProp");
        }
        String compid;
        if (!isPostback()) {
            compid = WorkingContext.getRequestQueryString("COMPID");            
            if (compid != null) {
                LoadCompDetail(compid);
                view = true;
                getAllS_Company_Contact();
                getListCompanyType();
                getListCompanyTypeForComboBox();
                fDtShowEditCOMP(compid);
                setPageDetailInfo(enumResultSetPosition.eInit);
            } 
            else {
                AddNewCompDetail();
                getListCompanyType();
                getListCompanyTypeForComboBox();
            }
        }
        //context = RequestContext.getCurrentInstance();
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Functions">

    //Lấy danh sách các liên hệ của công ty đối tác hiển thị lên grid
    public List<S_Company_Contact> getAllS_Company_Contact(){
        try {
            if (m_S_Company==null)
                return null;
                m_LstContact = getISysRemote().getAllS_Company_Contact(m_S_Company.getCompid());
                SaveListContactToSession(m_LstContact);
                return  m_LstContact;
        } catch (Exception e) {
            showErrorFromException(e, Sys_Comp_Details.class.getName() + ".getAllCompanyContact()");
            e.printStackTrace();
            return null;
        }
    }

    public void CleanScreen() {
        m_S_Company = new S_Company();
        
        m_Parent = new S_Company();
        resetInputRequire();
    }

    /**
     * Load cong ty doi tac
     * @param compid
     */
    public void LoadCompDetail(String compid) {
        try {
            CleanScreen();
            setFormMode(enumFormMode.eView);
            //Caller
            m_S_Company = (S_Company) getISysRemote().findById(compid, S_Company.class);
            if (m_S_Company != null) {
                m_Parent = m_S_Company.getsCompidParent();
                if (m_Parent==null)
                    m_Parent = new S_Company();
            } else {
                m_bDelete = true;
                m_bSave = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            WorkingContext.showMessages(enumMessageMode.eError, null, ex.getMessage());
        }
    }

    public void AddNewCompDetail() {
        CleanScreen();
        setFormMode(enumFormMode.eAddNew);
        view = false;
        //m_bSave = false;
        if(!(Tools.fSQLConvertLstStrValForIn(WorkingContext.getWorkingInfo().getOrgid(), ",").contains(","))) {
            m_S_Company.setOrgid(WorkingContext.getWorkingInfo().getOrgid());
        }
        m_eActionCurrent=enumAction.eAdd;
    }    

    private boolean CheckInput() {
        try {
            resetInputRequire();
            if (m_S_Company == null) {
                return false;
            }
            boolean bCheck = true, b;
            S_Company comp = m_S_Company;
            //Check nhap desc cho Phong ban/Bo phan
            if (comp.getCompdesc() == null || comp.getCompdesc().trim().isEmpty()) {
                bCheck = false;
                m_Style_TxtCMID = "error";
            }
            //Check nhap ORGID
            if (comp.getOrgid() == null || comp.getOrgid().trim().isEmpty()) {
                bCheck = false;
                m_Style_TxtORGID = "error";
            }            
            
            if (!bCheck) {
                m_MsgCheck = m_rfCommon.getMessage("msgNotEnoughDataInput");
                return false;
            }

            //Kiem tra Mã ORG đã tồn tại
            b=false;
            if (m_S_Company.getOrgid()!=null && !m_S_Company.getOrgid().isEmpty()) {
                S_Organization Temp = (S_Organization) getISysRemote().findById(m_S_Company.getOrgid(), S_Organization.class);
                if (Temp != null) {
                    b=true;
                    m_S_Company.setsOrganization(Temp);
                }
            }
            if (!b) {
                m_Style_TxtORGID = "error";
                m_MsgCheck = m_RfDeptProp.getMessage("detail_MsgOrgNotExists");
                return false;
            }

            /*
             * Valid du lieu nhap
             */

            //ManyList Loại hình công ty
            if (m_LstCompanyType != null) {
                List<S_Company_Type> target = (List<S_Company_Type>) m_LstCompanyType;
                String strCompanyTypeidlist = "";
                String temp = "";
                for (S_Company_Type selectItem : target) {
                    strCompanyTypeidlist += temp + selectItem.getComptypeid();
                    temp = ",";
                }
                m_S_Company.setTypeidlist(strCompanyTypeidlist);
            }


            //Kiểm tra nhóm cha
            if(m_S_Company.getCompidParent()!=null)
                if(m_S_Company.getCompidParent().trim().isEmpty()) //Nếu mã công ty cha là chuỗi rỗng
                    m_S_Company.setCompidParent(null);
            if(m_S_Company.getCompidParent()!=null){
                if(m_S_Company.getCompid().equals(m_S_Company.getCompidParent())){ //Nếu nhập mã công ty cha trùng với mã công ty con
                    m_Style_TxtMeterID = "error";
                    m_MsgCheck = m_RfDeptProp.getMessage("detail_MsgCompInvalid");
                    return false;
                }else{
                    S_Company or = (S_Company) getISysRemote().findById(m_S_Company.getCompidParent(), S_Company.class);
                    if(or==null){
                        m_Style_TxtMeterID = "error";
                        m_MsgCheck = m_RfDeptProp.getMessage("detail_MsgCompNotExists");
                        return false;
                    }
                    // chọn cấp con bên dưới làm cấp cha cho node cha
                    if (getISysRemote().doesCompHasChild(m_S_Company.getCompid(),m_S_Company.getCompidParent())){
                        m_Style_TxtMeterID = "error";
                        WorkingContext.showMessages(enumMessageMode.eError, null, "Không được chọn cấp con làm cấp cha");
                        return false;
                    }
                    m_S_Company.setsCompidParent(or);
                }
            }
            
            //Vùng thay đổi
            if (getFormMode() == enumFormMode.eAddNew) {                
                m_S_Company.setUserCreateId(WorkingContext.getUserName());
                m_S_Company.setUserCreateDtime(new Date());
                /*
                 * Sinh mã
                 */
                //Sinh key
                ISystemCommonRemote iscr = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
                S_Key_ControlInfo kc = new S_Key_ControlInfo(m_S_Company.getCompid(), S_Company.class.getSimpleName());
                kc = iscr.getGenKeyID(kc);
                if (kc == null || kc.getGenStatus() != 0) //Không thành công
                {
                    showErrorFromGenKey(kc, Sys_Comp_Details.class.getName() + ".CheckInput()");
                    return false;
                }
                m_S_Company.setCompid(kc.getOutputValue());
            } 
            return bCheck;
        } catch (Exception e) {
            showErrorFromException(e, Sys_Comp_Details.class.getName() + ".CheckInput()");
            return false;
        }
    }

    /**
     * Cập nhật mảng id khi thêm mới, xóa
     * @param type 0: thêm mới; 1: xóa
     */
    private void UpdateListComp(int type,String cmID){
        List<String> lstCMID = (List<String>) WorkingContext.getSessionValue(WorkingContext.LIST_S_COMPANY);
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
                        LoadCompDetail(lstCMID.get(idxNew));
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
        WorkingContext.setSessionValue(WorkingContext.LIST_S_COMPANY,lstCMID);
    }

    public void onTabChange(TabChangeEvent event) {
    }

    public void SaveListContactToSession(List<S_Company_Contact> lst) {
        try {
            if (lst != null) {
                List<String> listID = new ArrayList<String>();
                for (int i = 0; i < lst.size(); i++) {
                    listID.add(lst.get(i).getContactid());
                }
                WorkingContext.setSessionValue(WorkingContext.LIST_S_COMPANY_CONTACT, listID);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
            return !checkRightEditOrg(m_S_Company.getOrgid());
        }
        return true;
    }

    public boolean getIsAddNew() {
        if (getFormMode().equals(enumFormMode.eAddNew)) {
            return true;
        }
        return false;
    }

    protected final void fDtShowEditCOMP(String sCOMPID) {
        try {
            //ISafetyRemote ipm = getICollectionRemote();
            S_Company m_dtCurrentNew = (S_Company) getISysRemote().findById(sCOMPID, S_Company.class);
            if (m_dtCurrentNew == null) {
                showError(getISysRemote().getLastActionInfo(), Sys_Comp_Details.class.getName() + ".fDtShowEditCOMP()");
                setFormMode(enumFormMode.eView);
                return;
            }
            //Kiem tra quyen truy cap
            if (!checkRightAccessOrg(m_dtCurrentNew.getOrgid())) {
                showError(m_rfCommon.getMessage("msgNotAccessOnOrg") + " (ID=" + m_dtCurrentNew.getOrgid() + ")", Sys_Comp_Details.class.getName() + ".fDtShowEditCOMP()");
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
            showErrorFromException(ex, Sys_Comp_Details.class.getName() + ".fDtShowEditCOMP()");
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Override">
    @Override
    protected void setPageDetailInfo(enumResultSetPosition resultSetPosition) {
        try {
            List<String> lstCMID = (List<String>) WorkingContext.getSessionValue(WorkingContext.LIST_S_COMPANY);
            setDisableNavigator(true);
            if (lstCMID != null && m_S_Company != null) {
                if (!lstCMID.isEmpty() && m_S_Company.getCompid() != null) {
                    //Tìm vị trí hiện tại
                    String cmID = m_S_Company.getCompid();
                    int idx = lstCMID.indexOf(cmID);
                    if (idx >= 0 && idx < lstCMID.size()) {
                        if (resultSetPosition.equals(enumResultSetPosition.eInit)) {
                            fDtShowEditCOMP(lstCMID.get(idx));
                            updateNavigator(idx, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eFirst)) {
                            LoadCompDetail(lstCMID.get(0));
                            getListCompanyType();
                            getListCompanyTypeForComboBox();
                            fDtShowEditCOMP(lstCMID.get(idx));
                            updateNavigator(0, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.ePrevious)) {
                            idx--;
                            if (idx >= 0) {
                                LoadCompDetail(lstCMID.get(idx));
                                getListCompanyType();
                                getListCompanyTypeForComboBox();
                            }
                            fDtShowEditCOMP(lstCMID.get(idx));
                            updateNavigator(idx, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eNext)) {
                            idx++;
                            if (idx < lstCMID.size()) {
                                LoadCompDetail(lstCMID.get(idx));
                                getListCompanyType();
                                getListCompanyTypeForComboBox();
                            }
                            fDtShowEditCOMP(lstCMID.get(idx));
                            updateNavigator(idx, lstCMID);
                        } else {//last
                            LoadCompDetail(lstCMID.get(lstCMID.size() - 1));
                            getListCompanyType();
                            getListCompanyTypeForComboBox();
                            fDtShowEditCOMP(lstCMID.get(idx));
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
        m_Style_TxtORGID = "";
        m_Style_TxtMeterID = "";
        m_MsgCheck = "";
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Events">
    public void onAddNewComp(ActionEvent event) {
        try {
            AddNewCompDetail();
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_Comp_Details.class.getName() + ".onAddNewCompany()");
        }
    }

    public void onSaveComp(ActionEvent event) {
        try {
            if (!CheckInput()) {
                if(!m_MsgCheck.isEmpty())
                    WorkingContext.showMessages(enumMessageMode.eWarn, null, m_MsgCheck);
                return;
            }
            if (!checkID_Standard(m_S_Company.getCompid()))
                return;
            //Check quyen du lieu
            if (!checkRightEditOrg(m_S_Company.getOrgid())) {
                String s;
                s = m_rfCommon.getMessage("msgNotRightOnOrg") + " (ID=" + m_S_Company.getOrgid() + ")";
                WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, s);
                return;
            }
            //Lưu
            if (getFormMode() == enumFormMode.eAddNew) {
                if (getISysRemote() != null) {
                    if (getISysRemote().insert(m_S_Company)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                        view = true;
                        //List
                        UpdateListComp(0,m_S_Company.getCompid());
                        //LoadMeterDataCurrent();
                    }else
                        WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgSaveError"));

                }
            }else{
                if (getISysRemote() != null) {
                    m_S_Company.setUserModifyId(WorkingContext.getUserName());
                    m_S_Company.setUserModifyDtime(new Date());
                    if (getISysRemote().update(m_S_Company)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                        //LoadMeterDataCurrent();
                    }else
                        WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgSaveError"));
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_Comp_Details.class.getName() + ".onSaveCompany()");
        }
    }

    public void onDeleteComp(ActionEvent event) {
        try {
            if (m_S_Company.getCompid()!=null && getISysRemote()!=null) {
                String cmid = m_S_Company.getCompid();
                //if (getISysRemote().delete(cmid, S_Company.class)) {
                if (getISysRemote().deleteCompany(cmid)) {
                    WorkingContext.showMessages(enumMessageMode.eInfo,null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    CleanScreen();
                    UpdateListComp(1,cmid);
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

    public void onAcceptCompClick(ActionEvent event) {

        List<S_Company> lst = CompanyDialog.getSelected();
        if(lst!=null)
            if(!lst.isEmpty()){
                S_Company comp = lst.get(0);
                m_S_Company.setCompidParent(comp.getCompid());
                m_S_Company.setsCompidParent(comp);
            }
    }
    public void onAcceptOrganizationClick(ActionEvent event) {

        List<S_Organization> lst = OrganizationDialog.getSelected();
        if(lst!=null)
            if(!lst.isEmpty()){
                S_Organization org = lst.get(0);
                m_S_Company.setOrgid(org.getOrgid());
                m_S_Company.setsOrganization(org);
            }
    }

    public void onCloseContact(CloseEvent event){
        refreshGrid();
    }

    private void refreshGrid(){
    }

    public List getListCompanyType() {
        //List<S_Company_Type> listBean = null;
        String a ="";
        try {
            if(m_S_Company.getCompid()!=null)
                a = getISysRemote().getTypeListByCompID(m_S_Company.getCompid());

                m_LstCompanyType = getISysRemote().getCompanyTypeDescByTypeIDList(a);

            if (m_LstCompanyType == null) {
                return null;
            }
            List result = new ArrayList();
            if (m_LstCompanyType != null) {
                for (int i = 0; i < m_LstCompanyType.size(); i++) {
                    result.add(new SelectItem(m_LstCompanyType.get(i).getComptypeid(), m_LstCompanyType.get(i).getComptypedesc().trim()));
                }
            }
            return result;

        } catch (Exception e) {
            //ShowMessages(false, m_rfCommon.getMessage("msgLoadDataError") + "<br/>" + e.toString());
            return null;
        }
    }

    public List getListCompanyTypeForComboBox() {
        //List<S_Company_Type> listBean = null;
        String a ="";
        try {
            if(m_S_Company.getCompid()!=null)
                //listBean = getISysRemote().getTypeListForComboBox();
                a = getISysRemote().getTypeListByCompID(m_S_Company.getCompid());

                m_LstCompanyTypeForCombo = getISysRemote().getCompanyTypeDescByTypeIDListForComboBox(a);

            if (m_LstCompanyTypeForCombo == null) {
                return null;
            }
            List result = new ArrayList();
            if (m_LstCompanyTypeForCombo != null) {
                for (int i = 0; i < m_LstCompanyTypeForCombo.size(); i++) {
                    result.add(new SelectItem(m_LstCompanyTypeForCombo.get(i).getComptypeid(), m_LstCompanyTypeForCombo.get(i).getComptypedesc().trim()));
                }
            }
            return result;

        } catch (Exception e) {
            //ShowMessages(false, m_rfCommon.getMessage("msgLoadDataError") + "<br/>" + e.toString());
            return null;
        }
    }

    //Xóa đi loại hình công ty trên SelectManyList
    public void onRemoveCompanyTypeInListBox(ActionEvent event) {
        
        if(m_LstCompanyType!=null && m_ArrCompanyTypeIDSelected !=null)
            if(!m_LstCompanyType.isEmpty() && m_ArrCompanyTypeIDSelected.length>0){                
                for(String typeid: m_ArrCompanyTypeIDSelected){
                    for(S_Company_Type type: m_LstCompanyType)
                        if(type.getComptypeid().equalsIgnoreCase(typeid)){
                            m_LstCompanyType.remove(type);
                            m_LstCompanyTypeForCombo.add(type);
                            break;
                        }                        
                }               
            }
    }

    //Thêm loại hình công ty vào SelectManyList
    public void onAddCompanyTypeInListBox(ActionEvent event) {
        if(m_LstCompanyType!=null && selectedTypeCompany !=null)
            {       
                //Tim company_type
                S_Company_Type type = (S_Company_Type) getISysRemote().findById(selectedTypeCompany, S_Company_Type.class);
                if(type!=null){
                    m_LstCompanyType.add(type);
                    m_LstCompanyTypeForCombo.remove(type);
                }
            }
    }

    //Lay Compid dang chon hien thi len trong truong hop them moi Contact
    public void onAddNewContact(ActionEvent event) {
        try {
            if(!checkSelectionCM()){
                //Trả về client để điều khiển dialog
//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "AddNew");
//                context.addCallbackParam("compid", m_S_Company.getCompid());
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "AddNew");
                PrimeFaces.current().ajax().addCallbackParam("compid", m_S_Company.getCompid());
            }
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_Comp_Details.class.getName() + ".onAddNewContact()");
        }
    }

    public void onEditContact(ActionEvent event) {
        try {
            if(checkSelectionCM()){
//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "Edit");
//                context.addCallbackParam("contactid", arrSelectContact[0].getContactid());        
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "Edit");
                PrimeFaces.current().ajax().addCallbackParam("contactid", arrSelectContact[0].getContactid());
                
            }
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_Comp_Details.class.getName() + ".onEditContact()");
        }
    }

     public void onDeleteContactStart(ActionEvent event) {
        try {
            if(checkSelectionCM()){
                m_sDeleteMsg = m_rfCommon.getMessage("msgDlgDeleteInfo");
                m_sDeleteMsg=m_sDeleteMsg.replaceAll("@count",String.valueOf(arrSelectContact.length));
                if (arrSelectContact.length==1)
                    m_sDeleteMsg=m_sDeleteMsg.replaceAll("@desc"," ('" + arrSelectContact[0].getContactid() + "')");
                else
                    m_sDeleteMsg=m_sDeleteMsg.replaceAll("@desc","");

//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "Delete");
//                context.addCallbackParam("contactid", arrSelectContact[0].getContactid());
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "Delete");
                PrimeFaces.current().ajax().addCallbackParam("contactid", arrSelectContact[0].getContactid());
            }
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_Comp_Details.class.getName() + ".onDeleteContact()");
        }
    }

    public void onDeleteContactEnd(ActionEvent event){
       try
        {
            if (getISysRemote() != null)
            {
                if (arrSelectContact !=null)
                {
                    for(int i=0;i<arrSelectContact.length;i++)
                    {
                        if (!getISysRemote().delete(arrSelectContact[i].getContactid(),S_Company_Contact.class))
                        {
                            WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgDeleteError"));
                            //showError(getISysRemote().getLastActionInfo(), S_Organization.class.getName() + ".onDeleteOrganizationEnd()");
                            return;
                        }
                    }
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    //refreshGridCM();
                }
            }
        }
        catch(Exception e)
        {
            WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgDeleteError"));
            //showErrorFromException(e, Sys_OrganizationBean.class.getName() + ".onDeleteOrganizationEnd()");
        }
    }

    private boolean checkSelectionCM(){
        if (arrSelectContact != null)
                if (arrSelectContact.length > 0)
                    return true;
        //Thong bao chon hang
        WorkingContext.showMessages(enumMessageMode.eWarn, null,
                                m_rfCommon.getMessage("msgNotCheckRecord"));
        return false;
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

    public S_Company getParent() {
        return m_Parent;
    }

    public void setParent(S_Company m_Parent) {
        this.m_Parent = m_Parent;
    }

    public S_Company getCompany() {
        return m_S_Company;
    }

    public void setDept(S_Company m_S_Company) {
        this.m_S_Company = m_S_Company;
    }

    public TabView getTabView() {
        return m_TabView;
    }

    public void setTabView(TabView m_TabView) {
        this.m_TabView = m_TabView;
    }

    public Boolean getTxtCMIDReadOnly() {
        if (getFormMode() == enumFormMode.eAddNew) {
            return false;
        }
        return true;
    }

    public String getStyle_TxtORGID() {
        return m_Style_TxtORGID;
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

    public S_Company_Contact[] getArrSelectContact() {
        return arrSelectContact;
    }

    public void setArrSelectContact(S_Company_Contact[] arrSelectContact) {
        this.arrSelectContact = arrSelectContact;
    }

    public String getM_sDeleteMsg() {
        return m_sDeleteMsg;
    }

    public void setM_sDeleteMsg(String m_sDeleteMsg) {
        this.m_sDeleteMsg = m_sDeleteMsg;
    }

    public String[] getCompanyTypeIDSelected() {
        return m_ArrCompanyTypeIDSelected;
    }

    public void setCompanyTypeIDSelected(String[] m_ArrCompanyTypeIDSelected) {
        this.m_ArrCompanyTypeIDSelected = m_ArrCompanyTypeIDSelected;
    }

    public List<S_Company_Type> getDsCompanyTypeForListBox() {
        if(m_LstCompanyType == null)
            m_LstCompanyType = new ArrayList<S_Company_Type>();
        return m_LstCompanyType;
    }

    public List<S_Company_Type> getDsCompanyTypeForCombo() {
        if(m_LstCompanyTypeForCombo == null)
            m_LstCompanyTypeForCombo = new ArrayList<S_Company_Type>();
        return m_LstCompanyTypeForCombo;
    }

    public String getSelectedTypeCompany() {
        return selectedTypeCompany;
    }

    public void setSelectedTypeCompany(String selectedTypeCompany) {
        this.selectedTypeCompany = selectedTypeCompany;
    }

    public String getOrgDesc() {
        if(m_S_Company.getsOrganization()!=null) {
            return m_S_Company.getsOrganization().getOrgdesc();
        }
        return null;
    }

    public String getCompDesc() {
        if (m_S_Company.getsCompidParent()!=null){
            return m_S_Company.getsCompidParent().getCompdesc();
        }
        return null;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }
    
    //}}</editor-fold>
    public S_Company_ContactDataModel getS_Company_ContactDataModel() {
        getAllS_Company_Contact();
        if(m_LstContact!=null && m_LstContact.size()>0)
            return new S_Company_ContactDataModel(m_LstContact);
         return new S_Company_ContactDataModel();
    }

    public void onOpenContactDetail(ActionEvent ae)
    {
        SaveListContactToSession(m_LstContact);
    }
}
class S_Company_ContactDataModel extends ListDataModel<S_Company_Contact> implements SelectableDataModel<S_Company_Contact> {

     public S_Company_ContactDataModel() {
     }

     public S_Company_ContactDataModel(List<S_Company_Contact> data) {
         super(data);
     }

    @Override
    public String getRowKey(S_Company_Contact t) {
        return Tools.fStandardPdataTableID(t.getContactid());
    }

    @Override
    public S_Company_Contact getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

        List<S_Company_Contact> list = (List<S_Company_Contact>) getWrappedData();

        for(S_Company_Contact lst : list) {
            if(Tools.fStandardPdataTableID(lst.getContactid()).equals(rowKey))
                return lst;
        }
        return null;
    }
}
