package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.system.S_Dept;
import main.entity.shared.system.S_Dept_Type;
import main.entity.shared.system.S_Organization;
import main.remote.shared.system.ISysRemote;
import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageBean;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import main.web.converters.DoubleConverter;
import main.web.shared.dialog.bean.DeptDialog;
import main.web.shared.dialog.bean.OrganizationDialog;
import evnit.util.common.BaseConstant;
import evnit.util.common.BaseConstant.enumFormMode;
import evnit.util.common.BaseConstant.enumMessageMode;
import evnit.util.common.BaseConstant.enumResultSetPosition;
import evnit.util.common.S_Key_ControlInfo;
import evnit.util.common.Tools;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author LanTH
 */
@ManagedBean
@ViewScoped
public class Sys_Dept_Details extends BasePageBean implements Serializable {

    private ISysRemote iSys = null;
    private S_Dept m_S_Dept;
    private S_Dept m_Parent;
    private ResourcesFactory m_RfDeptProp;
    //Check nhập
    String m_Style_TxtCMID;
    String m_Style_TxtORGID;
    String m_Style_TxtMeterID;
    String m_MsgCheck;

    private enum enumAction {

        eNone,
        eAdd,
        eEdit,
        eDelete,
        eChangeStatus,
        eDuplicate
    }
    private enumAction m_eActionCurrent = enumAction.eNone; //Biến lưu thao tác hiện tại

    //{{<editor-fold defaultstate="collapsed" desc="Contructor">
    public Sys_Dept_Details() {
        m_Parent = new S_Dept();
        if (m_RfDeptProp == null) {
            m_RfDeptProp = new ResourcesFactory("main/web/shared/system/prop/SystemProp");
        }
        if (!isPostback()) {
            String deptid = WorkingContext.getRequestQueryString("DEPTID");
            if (deptid != null) {
                LoadDeptDetail(deptid);
                fDtShowEditDEPT(deptid);
                setPageDetailInfo(enumResultSetPosition.eInit);
                getListDeptType();
                getListDeptTypeForComboBox();
            } else {
                AddNewDeptDetail();
            }
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Functions">
    public void CleanScreen() {
        m_S_Dept = new S_Dept();        
        m_Parent = new S_Dept();
        resetInputRequire();
    }

    /**
     * Load phong ban/bo phan
     *
     * @param siteid
     */
    public void LoadDeptDetail(String deptid) {
        try {
            CleanScreen();
            setFormMode(enumFormMode.eView);
            //Caller
            m_S_Dept = (S_Dept) getISysRemote().findById(deptid, S_Dept.class);
            if (m_S_Dept != null) {
                m_Parent = m_S_Dept.getsDeptidParent();
                if (m_Parent == null) {
                    m_Parent = new S_Dept();
                }
            } else {
                m_bDelete = true;
                m_bSave = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            WorkingContext.showMessages(enumMessageMode.eError, null, ex.getMessage());
        }
    }

    public void AddNewDeptDetail() {
        CleanScreen();
        setFormMode(enumFormMode.eAddNew);
        //m_bSave = false;
        if (!(Tools.fSQLConvertLstStrValForIn(WorkingContext.getWorkingInfo().getOrgid(), ",").contains(","))) {
            m_S_Dept.setOrgid(WorkingContext.getWorkingInfo().getOrgid());
        }
        m_eActionCurrent = enumAction.eAdd;
    }

    private boolean CheckInput() {
        try {
            resetInputRequire();
            if (m_S_Dept == null) {
                return false;
            }
            boolean bCheck = true;
            S_Dept dept = m_S_Dept;
            //Check nhap desc cho Phong ban/Bo phan
            if (dept.getDeptdesc() == null || dept.getDeptdesc().trim().isEmpty()) {
                bCheck = false;
                m_Style_TxtCMID = "error";
            }
            //Check nhap ORGID
            if (dept.getOrgid() == null || dept.getOrgid().trim().isEmpty()) {
                bCheck = false;
                m_Style_TxtORGID = "error";
            }

            if (!bCheck) {
                m_MsgCheck = m_rfCommon.getMessage("msgNotEnoughDataInput");
                return false;
            }

            //Kiem tra Mã ORG đã tồn tại
            if (getFormMode().equals(enumFormMode.eAddNew)) {
                S_Organization Temp = (S_Organization) getISysRemote().findById(m_S_Dept.getOrgid(), S_Organization.class);
                if (Temp == null) {
                    m_Style_TxtORGID = "error";
                    m_MsgCheck = m_RfDeptProp.getMessage("detail_MsgOrgNotExists");
                    return false;
                }
            }

            /*
             * Valid du lieu nhap
             */
            if (m_LstDeptType != null) {
                List<S_Dept_Type> target = (List<S_Dept_Type>) m_LstDeptType;
                String strDeptTypeidlist = "";
                String temp = "";
                for (S_Dept_Type selectItem : target) {
                    strDeptTypeidlist += temp + selectItem.getDepttypeid();
                    temp = ",";
                }
                m_S_Dept.setTypeidlist(strDeptTypeidlist);
            }

            //Kiểm tra nhóm cha
            if (m_S_Dept.getDeptidParent() != null) {
                if (m_S_Dept.getDeptidParent().trim().isEmpty()) //Nếu mã phòng cha là chuỗi rỗng
                {
                    m_S_Dept.setDeptidParent(null);
                }
            }
            if (m_S_Dept.getDeptidParent() != null) {
                if (m_S_Dept.getDeptid().equals(m_S_Dept.getDeptidParent())) { //Nếu nhập mã phòng cha trùng với mã phòng con
                    m_Style_TxtMeterID = "error";
                    m_MsgCheck = m_RfDeptProp.getMessage("detail_MsgDeptInvalid");
                    return false;
                } else {
                    S_Dept de = (S_Dept) getISysRemote().findById(m_S_Dept.getDeptidParent(), S_Dept.class);
                    if (de == null) {
                        m_Style_TxtMeterID = "error";
                        m_MsgCheck = m_RfDeptProp.getMessage("detail_MsgDeptNotExists");
                        return false;
                    }
                    // chọn cấp con bên dưới làm cấp cha cho node cha
                    if (getISysRemote().doesDeptHasChild(m_S_Dept.getDeptid(), m_S_Dept.getDeptidParent())) {
                        m_Style_TxtMeterID = "error";
                        WorkingContext.showMessages(enumMessageMode.eError, null, "Không được chọn cấp con làm cấp cha");
                        return false;
                    }
                }
            }

            //Vùng thay đổi
            if (getFormMode() == enumFormMode.eAddNew) {

                /*
                 * Sinh mã
                 */
                //Sinh key
                ISystemCommonRemote iscr = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
                S_Key_ControlInfo kc = new S_Key_ControlInfo(m_S_Dept.getDeptid(), S_Dept.class.getSimpleName());
                kc = iscr.getGenKeyID(kc);
                if (kc == null || kc.getGenStatus() != 0) //Không thành công
                {
                    showErrorFromGenKey(kc, Sys_Dept_Details.class.getName() + ".CheckInput()");
                    return false;
                }
                m_S_Dept.setDeptid(kc.getOutputValue());
            }
            return bCheck;
        } catch (Exception e) {
            showErrorFromException(e, Sys_Dept_Details.class.getName() + ".CheckInput()");
            return false;
        }
    }

    /**
     * Cập nhật mảng id khi thêm mới, xóa
     *
     * @param type 0: thêm mới; 1: xóa
     */
    private void UpdateListDept(int type, String cmID) {
        List<String> lstCMID = (List<String>) WorkingContext.getSessionValue(WorkingContext.LIST_S_DEPT);
        if (lstCMID == null) {
            lstCMID = new ArrayList<String>();
        }
        switch (type) {
            case 0://Them moi
                lstCMID.add(cmID);
                updateNavigator(lstCMID.size() - 1, lstCMID);
                break;
            case 1://Delete
                if (lstCMID.contains(cmID)) {
                    int idx = lstCMID.indexOf(cmID);
                    lstCMID.remove(cmID);
                    //Mảng còn
                    int idxNew = 0;
                    if (idx < lstCMID.size() - 1) {
                        idxNew = idx + 1;
                    } else if (idx >= lstCMID.size() - 1) {
                        idxNew = lstCMID.size() - 1;
                    } else if (idx > 0) {
                        idxNew = idx - 1;
                    }
                    if (idxNew >= 0 && idxNew < lstCMID.size()) {
                        LoadDeptDetail(lstCMID.get(idxNew));
                        updateNavigator(idxNew, lstCMID);
                    } else {
                        setFormMode(enumFormMode.eInit);
                    }
                } else {
                    setPageDetailInfo(enumResultSetPosition.eFirst);
                }
                break;
            default:
                break;
        }
        WorkingContext.setSessionValue(WorkingContext.LIST_S_DEPT, lstCMID);
    }

    public boolean getDtIsDisabledSaveMain() {
        if (m_eActionCurrent != enumAction.eAdd) {
            if (getDtIsDisabledEditDataMain()) {
                return true;
            }
        }

        return false;
    }

    public boolean getDtIsDisabledEditDataMain() {
        if (!getIsAddNew()) {
            return !checkRightEditOrg(m_S_Dept.getOrgid());
        }
        return true;
    }

    public boolean getIsAddNew() {
        if (getFormMode().equals(enumFormMode.eAddNew)) {
            return true;
        }
        return false;
    }

    protected final void fDtShowEditDEPT(String sDEPTID) {
        try {
            //ISafetyRemote ipm = getICollectionRemote();
            S_Dept m_dtCurrentNew = (S_Dept) getISysRemote().findById(sDEPTID, S_Dept.class);
            if (m_dtCurrentNew == null) {
                showError(getISysRemote().getLastActionInfo(), Sys_Dept_Details.class.getName() + ".fDtShowEditDEPT()");
                setFormMode(enumFormMode.eView);
                return;
            }
            //Kiem tra quyen truy cap
            if (!checkRightAccessOrg(m_dtCurrentNew.getOrgid())) {
                showError(m_rfCommon.getMessage("msgNotAccessOnOrg") + " (ID=" + m_dtCurrentNew.getOrgid() + ")", Sys_Dept_Details.class.getName() + ".fDtShowEditDEPT()");
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
            showErrorFromException(ex, Sys_Dept_Details.class.getName() + ".fDtShowEditDEPT()");
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Override">
    @Override
    protected void setPageDetailInfo(enumResultSetPosition resultSetPosition) {
        try {
            List<String> lstCMID = (List<String>) WorkingContext.getSessionValue(WorkingContext.LIST_S_DEPT);
            setDisableNavigator(true);
            if (lstCMID != null && m_S_Dept != null) {
                if (!lstCMID.isEmpty() && m_S_Dept.getDeptid() != null) {
                    //Tìm vị trí hiện tại
                    String cmID = m_S_Dept.getDeptid();
                    int idx = lstCMID.indexOf(cmID);
                    if (idx >= 0 && idx < lstCMID.size()) {
                        if (resultSetPosition.equals(enumResultSetPosition.eInit)) {
                            fDtShowEditDEPT(lstCMID.get(idx));
                            updateNavigator(idx, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eFirst)) {
                            LoadDeptDetail(lstCMID.get(0));
                            fDtShowEditDEPT(lstCMID.get(idx));
                            updateNavigator(0, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.ePrevious)) {
                            idx--;
                            if (idx >= 0) {
                                LoadDeptDetail(lstCMID.get(idx));
                            }
                            fDtShowEditDEPT(lstCMID.get(idx));
                            updateNavigator(idx, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eNext)) {
                            idx++;
                            if (idx < lstCMID.size()) {
                                LoadDeptDetail(lstCMID.get(idx));
                            }
                            fDtShowEditDEPT(lstCMID.get(idx));
                            updateNavigator(idx, lstCMID);
                        } else {//last
                            LoadDeptDetail(lstCMID.get(lstCMID.size() - 1));
                            fDtShowEditDEPT(lstCMID.get(idx));
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
    public void onAddNewDept(ActionEvent event) {
        try {
            AddNewDeptDetail();
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_Dept_Details.class.getName() + ".onAddNewDept()");
        }
    }

    public void onSaveDept(ActionEvent event) {
        try {
            if (!CheckInput()) {
                if (!m_MsgCheck.isEmpty()) {
                    WorkingContext.showMessages(enumMessageMode.eWarn, null, m_MsgCheck);
                }
                return;
            }
            if (!checkID_Standard(m_S_Dept.getDeptid())) {
                return;
            }
            //Check quyen du lieu
            if (!checkRightEditOrg(m_S_Dept.getOrgid())) {
                String s;
                s = m_rfCommon.getMessage("msgNotRightOnOrg") + " (ID=" + m_S_Dept.getOrgid() + ")";
                WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, s);
                return;
            }
            //Lưu
            if (getFormMode() == enumFormMode.eAddNew) {
                //if (m_eActionCurrent==enumAction.eAdd) {
                if (getISysRemote() != null) {
                    if (getISysRemote().insert(m_S_Dept)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                        //List
                        UpdateListDept(0, m_S_Dept.getDeptid());
                        getListDeptType();
                        //LoadMeterDataCurrent();
                    } else {
                        WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgSaveError"));
                    }

                }
            } else {
                if (getISysRemote() != null) {
                    if (getISysRemote().update(m_S_Dept)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                        //m_eActionCurrent=enumAction.eEdit;
                        //LoadMeterDataCurrent();
                    } else {
                        WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgSaveError"));
                    }
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_Dept_Details.class.getName() + ".onSaveDept()");
        }
    }

    public void onDeleteDept(ActionEvent event) {
        try {
            if (m_S_Dept.getDeptid() != null && getISysRemote() != null) {
                String cmid = m_S_Dept.getDeptid();
                //if (getISysRemote().delete(cmid, S_Dept.class)) {
                if (getISysRemote().deleteDept(cmid)) {
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    CleanScreen();
                    UpdateListDept(1, cmid);
                } else {
                    WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgDeleteError")
                            + BaseConstant.CRLF() + getISysRemote().getLastActionInfo());
                }
            }
        } catch (Exception ex) {
            WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgDeleteError"));
            //showErrorFromException(ex, Sys_Organization_Details.class.getName() + ".onDeleteOrganization()");
        }
    }

    public void onAcceptDeptClick(ActionEvent event) {

        List<S_Dept> lst = DeptDialog.getSelected();
        if (lst != null) {
            if (!lst.isEmpty()) {
                S_Dept dept = lst.get(0);
                m_S_Dept.setDeptidParent(dept.getDeptid());
            }
        }
    }

    public void onAcceptOrganizationClick(ActionEvent event) {

        List<S_Organization> lst = OrganizationDialog.getSelected();
        if (lst != null) {
            if (!lst.isEmpty()) {
                S_Organization org = lst.get(0);
                m_S_Dept.setOrgid(org.getOrgid());
            }
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Properties">
    public ISysRemote getISysRemote() {
        try {
            if (iSys == null) {
                iSys = (ISysRemote) EjbContext.getLocalEJBRemote(ISysRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login lại khi gọi ejb
            }
        } catch (Exception ex) {
            return null;
        }
        return iSys;
    }

    public S_Dept getParent() {
        return m_Parent;
    }

    public void setParent(S_Dept m_Parent) {
        this.m_Parent = m_Parent;
    }

    public S_Dept getDept() {
        return m_S_Dept;
    }

    public void setDept(S_Dept m_S_Dept) {
        this.m_S_Dept = m_S_Dept;
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

    public DoubleConverter getDoubleConverter() {
        DoubleConverter converter = new DoubleConverter();
        converter.setPatern_EN("#,##0.0###");
        return converter;
    }

    public String getOrgDesc() {
        if (m_S_Dept != null && m_S_Dept.getOrgid() != null) {
            if (Tools.fSQLConvertLstStrValForIn(WorkingContext.getWorkingInfo().getOrgid(), ",").contains(",")) {
                if (m_S_Dept.getOrgid() != null) {
                    if (!m_S_Dept.getOrgid().isEmpty()) {
                        S_Organization org = (S_Organization) getISysRemote().findById(m_S_Dept.getOrgid(), S_Organization.class);
                        if (org != null) {
                            return org.getOrgdesc();
                        }
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }
            S_Organization org = (S_Organization) getISysRemote().findById(m_S_Dept.getOrgid(), S_Organization.class);
            if (org != null) {
                return org.getOrgdesc();
            }
        }
        return null;
    }

    public String getDeptDesc() {
        if (m_S_Dept.getDeptidParent() != null) {
            if (!m_S_Dept.getDeptidParent().isEmpty()) {
                S_Dept de = (S_Dept) getISysRemote().findById(m_S_Dept.getDeptidParent(), S_Dept.class);
                if (de != null) {
                    return de.getDeptdesc();
                }
            }
        }
        return null;
    }
    //}}</editor-fold>
    private List<S_Dept_Type> m_LstDeptType;
    private List<S_Dept_Type> m_LstDeptTypeForCombo;
    private String[] m_ArrDeptTypeIDSelected;
    private String selectedTypeDept = "";

    public String[] getM_ArrDeptTypeIDSelected() {
        return m_ArrDeptTypeIDSelected;
    }

    public void setM_ArrDeptTypeIDSelected(String[] m_ArrDeptTypeIDSelected) {
        this.m_ArrDeptTypeIDSelected = m_ArrDeptTypeIDSelected;
    }

    public List<S_Dept_Type> getM_LstDeptType() {
        return m_LstDeptType;
    }

    public void setM_LstDeptType(List<S_Dept_Type> m_LstDeptType) {
        this.m_LstDeptType = m_LstDeptType;
    }

    public List<S_Dept_Type> getM_LstDeptTypeForCombo() {
        return m_LstDeptTypeForCombo;
    }

    public void setM_LstDeptTypeForCombo(List<S_Dept_Type> m_LstDeptTypeForCombo) {
        this.m_LstDeptTypeForCombo = m_LstDeptTypeForCombo;
    }

    public String getSelectedTypeDept() {
        return selectedTypeDept;
    }

    public void setSelectedTypeDept(String selectedTypeDept) {
        this.selectedTypeDept = selectedTypeDept;
    }

    public List getListDeptType() {
        String a = "";
        try {
            if (m_S_Dept.getDeptid() != null) {
                a = getISysRemote().getTypeListByDeptID(m_S_Dept.getDeptid());
            }
            m_LstDeptType = getISysRemote().getDeptTypeDescByTypeIDList(a);

            if (m_LstDeptType == null) {
                return null;
            }
            List result = new ArrayList();
            if (m_LstDeptType != null) {
                for (int i = 0; i < m_LstDeptType.size(); i++) {
                    result.add(new SelectItem(m_LstDeptType.get(i).getDepttypeid(), m_LstDeptType.get(i).getDepttypedesc().trim()));
                }
            }
            return result;

        } catch (Exception e) {
            //ShowMessages(false, m_rfCommon.getMessage("msgLoadDataError") + "<br/>" + e.toString());
            return null;
        }
    }

    public List getListDeptTypeForComboBox() {
        String a = "";
        try {
            if (m_S_Dept.getDeptid() != null) {
                a = getISysRemote().getTypeListByDeptID(m_S_Dept.getDeptid());
            }

            m_LstDeptTypeForCombo = getISysRemote().getDeptTypeDescByTypeIDListForComboBox(a);

            if (m_LstDeptTypeForCombo == null) {
                return null;
            }
            List result = new ArrayList();
            if (m_LstDeptTypeForCombo != null) {
                for (int i = 0; i < m_LstDeptTypeForCombo.size(); i++) {
                    result.add(new SelectItem(m_LstDeptTypeForCombo.get(i).getDepttypeid(), m_LstDeptTypeForCombo.get(i).getDepttypedesc().trim()));
                }
            }
            return result;

        } catch (Exception e) {
            //ShowMessages(false, m_rfCommon.getMessage("msgLoadDataError") + "<br/>" + e.toString());
            return null;
        }
    }

    public List<S_Dept_Type> getDsDeptTypeForListBox() {
        if (m_LstDeptType == null) {
            m_LstDeptType = new ArrayList<S_Dept_Type>();
        }
        return m_LstDeptType;
    }

    public List<S_Dept_Type> getDsDeptTypeForCombo() {
        if (m_LstDeptTypeForCombo == null) {
            m_LstDeptTypeForCombo = new ArrayList<S_Dept_Type>();
        }
        return m_LstDeptTypeForCombo;
    }

    public void onRemoveDeptTypeInListBox(ActionEvent event) {

        if (m_LstDeptType != null && m_ArrDeptTypeIDSelected != null) {
            if (!m_LstDeptType.isEmpty() && m_ArrDeptTypeIDSelected.length > 0) {
                for (String typeid : m_ArrDeptTypeIDSelected) {
                    for (S_Dept_Type type : m_LstDeptType) {
                        if (type.getDepttypeid().equalsIgnoreCase(typeid)) {
                            int index = 0;
                            for (S_Dept_Type dt : m_LstDeptType) {
                                if (dt.getDepttypeid().equals(type.getDepttypeid())) {
                                    break;
                                }
                                index++;
                            }
                            m_LstDeptType.remove(index);
                            m_LstDeptTypeForCombo.add(type);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void onAddDeptTypeInListBox(ActionEvent event) {
        if (m_LstDeptType != null && selectedTypeDept != null) {
            S_Dept_Type type = (S_Dept_Type) getISysRemote().findById(selectedTypeDept, S_Dept_Type.class);
            if (type != null) {
                m_LstDeptType.add(type);
                int index = 0;
                for (S_Dept_Type dt : m_LstDeptTypeForCombo) {
                    if (dt.getDepttypeid().equals(type.getDepttypeid())) {
                        break;
                    }
                    index++;
                }
                m_LstDeptTypeForCombo.remove(index);
            }
        }
    }
}
