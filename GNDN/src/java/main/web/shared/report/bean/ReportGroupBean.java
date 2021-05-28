package main.web.shared.report.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.report.S_Report_Group;
import main.remote.shared.report.IReportConfigRemote;
import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageBean;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import main.web.shared.dialog.bean.ReportGroupDialogBean;
import evnit.util.common.BaseConstant;
import evnit.util.common.BaseConstant.enumFormMode;
import evnit.util.common.BaseConstant.enumMessageMode;
import evnit.util.common.BaseConstant.enumResultSetPosition;
import evnit.util.common.S_Key_ControlInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.event.CloseEvent;

/**
 *
 * @author hanv
 */
@ManagedBean
@ViewScoped
public class ReportGroupBean extends BasePageBean implements Serializable {

    private IReportConfigRemote m_IReportConfigRemote;
    private S_Report_Group m_TabGroup;
    private S_Report_Group m_TabGroupParent;
    private ResourcesFactory m_RfTabProp;
    //Check nhập
    String m_Style_TxtID;
    String m_Style_TxtSiteID;
    String m_MsgCheck;

    @ManagedProperty(name = "reportConfigBean", value = "#{reportConfigBean}")
    private ReportConfigBean reportConfigBean;
    List<String> m_LstTabGroupID = null;

    //{{<editor-fold defaultstate="collapsed" desc="Contructor">
    public ReportGroupBean() {
        try {
            
            if (!isPostback()) {
                String optabgroupid = WorkingContext.getRequestQueryString("rptgroupid");
                if (optabgroupid != null) {
                    LoadDetail(optabgroupid);
                    setPageDetailInfo(enumResultSetPosition.eInit);
                } else {
                    String optabgroupidParent = WorkingContext.getRequestQueryString("rptgroupidParent");
                    if (optabgroupidParent != null) {
                        m_TabGroupParent = (S_Report_Group) getIReportConfigRemote().findById(optabgroupidParent, S_Report_Group.class);
                    }
                    AddNewDetail();
                }
                WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            showErrorFromException(ex, ReportGroupBean.class.getName() + "ReportGroupBean()");
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Functions">
    public void copyReport() {
        try {
            ArrayList<Object> lstKey = (ArrayList<Object>) WorkingContext.getSessionValue("LISTREPORTSELECT");
            for (int i = 0; i < lstKey.size(); i++) {
                getIReportConfigRemote().copyReport(lstKey.get(i).toString(), m_TabGroupParent.getRptgroupid());
            }
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
        } catch (Exception ex) {
            ex.printStackTrace();
            showErrorFromException(ex, ReportFormBean.class.getName() + "copyReport()");
        }
    }

    public void CleanScreen() {
        m_TabGroup = new S_Report_Group();
        m_TabGroup.setEnable(true);
        if (m_TabGroupParent != null) {
            m_TabGroup.setRptgroupid_parent(m_TabGroupParent.getRptgroupid());
        }
        resetInputRequire();
    }

    /**
     * Load
     *
     * @param optabgroupid
     */
    public void LoadDetail(String optabgroupid) {
        try {
            CleanScreen();
            setFormMode(enumFormMode.eView);
            //Caller
            m_TabGroup
                    = (S_Report_Group) getIReportConfigRemote().findById(optabgroupid, S_Report_Group.class
                    );
            if (m_TabGroup
                    != null) {
                //Load cha
                if (m_TabGroup.getRptgroupid_parent() != null) {
                    m_TabGroupParent = (S_Report_Group) getIReportConfigRemote().findById(m_TabGroup.getRptgroupid_parent(), S_Report_Group.class);
                }
            } else {
                m_bDelete = true;
                m_bSave = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            showErrorFromException(ex, ReportGroupBean.class
                    .getName() + "LoadDetail()");
        }
    }

    public void AddNewDetail() {
        CleanScreen();
        setFormMode(enumFormMode.eAddNew);
        m_bSave = false;
    }

    private boolean CheckInput() {
        try {
            resetInputRequire();
            if (m_TabGroup == null) {
                return false;
            }
            boolean bCheck = true;
            S_Report_Group tg = m_TabGroup;
            //Check nhap            
            if (tg.getRptgroupdesc() == null || tg.getRptgroupdesc().trim().isEmpty()) {
                bCheck = false;
                m_Style_TxtID = "error";
            }
            if (!bCheck) {
                m_MsgCheck = m_rfCommon.getMessage("msgNotEnoughDataInput");
                return false;
            }
            m_TabGroup.setRptgroupdesc(m_TabGroup.getRptgroupdesc().trim());
            /*
             * Valid du lieu nhap
             */
            //Kiểm tra nhóm cha
            if (m_TabGroup.getRptgroupid_parent() != null) {
                if (m_TabGroup.getRptgroupid_parent().trim().isEmpty()) {
                    m_TabGroup.setRptgroupid_parent(null);
                }
            }
            if (m_TabGroup.getRptgroupid_parent() != null) {
                if (m_TabGroup.getRptgroupid().equals(m_TabGroup.getRptgroupid_parent())) {
                    m_MsgCheck = m_RfTabProp.getMessage("msgChooseTabGroupFalse");
                    return false;

                } else {
                    m_TabGroupParent = (S_Report_Group) getIReportConfigRemote().findById(m_TabGroup.getRptgroupid_parent(), S_Report_Group.class
                    );
                    if (m_TabGroupParent
                            == null) {
                        m_MsgCheck = m_RfTabProp.getMessage("msgTabGroupNotExists");
                        return false;
                    }

                    //Kiểm tra quan hệ vòng
                    if (getFormMode()
                            != enumFormMode.eAddNew) {
                        if (m_TabGroupParent.getRptgroupid_parent() != null) {
                            if (m_TabGroupParent.getRptgroupid_parent().equals(m_TabGroup.getRptgroupid())) {
                                m_MsgCheck = m_RfTabProp.getMessage("msgChooseTabGroupFalse");
                                return false;
                            }
                        }
                    }
                }
            } else {
                m_TabGroupParent = null;

            }
            //Kiểm tra site
            ISystemCommonRemote iscr = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class
                    .getSimpleName());
            //Vùng thay đổi
            if (getFormMode()
                    == enumFormMode.eAddNew) {
                m_TabGroup.setUserCrId(WorkingContext.getUserName());
                m_TabGroup.setUserCrDtime(new Date());

                /*
                 * Sinh mã
                 */
                //Sinh key                
                S_Key_ControlInfo kc = new S_Key_ControlInfo(m_TabGroup.getRptgroupid(), S_Report_Group.class.getSimpleName());
                kc = iscr.getGenKeyID(kc);
                if (kc == null || kc.getGenStatus() != 0) //Không thành công
                {
                    showErrorFromGenKey(kc, ReportGroupBean.class.getName() + ".CheckInput()");
                    return false;
                }
                m_TabGroup.setRptgroupid(kc.getOutputValue());
            } else {
                m_TabGroup.setUserMdfId(WorkingContext.getUserName());
                m_TabGroup.setUserMdfDtime(new Date());
            }
            return bCheck;
        } catch (Exception e) {
            showErrorFromException(e, ReportGroupBean.class
                    .getName() + ".CheckInput()");

            return false;
        }
    }

    //}}</editor-fold>
    //{{<editor-fold defaultstate="collapsed" desc="Override">
    /**
     * Cập nhật mảng khi thêm mới, xóa
     *
     * @param type 0: thêm mới; 1: xóa
     */
    private void UpdateListDetails(int type, String id, List<String> lstIDChild) {
        getLstTabGroupID();
        if (m_LstTabGroupID == null) {
            return;
        }
        switch (type) {
            case 0://Them moi
                m_LstTabGroupID.add(id);
                updateNavigator(m_LstTabGroupID.size() - 1, m_LstTabGroupID);
                break;
            case 1://Delete
                if (m_LstTabGroupID.contains(id)) {
                    int idx = m_LstTabGroupID.indexOf(id);
                    m_LstTabGroupID.remove(id);
                    if (lstIDChild != null) {
                        if (!lstIDChild.isEmpty()) {
                            for (String idChild : lstIDChild) {
                                if (m_LstTabGroupID.size() <= 0) {
                                    //Khi không còn phần tử nào được duyệt
                                    setFormMode(enumFormMode.eView);
                                    CleanScreen();
                                    m_bDelete = true;
                                    m_bSave = true;
                                    break;
                                } else if (m_LstTabGroupID.contains(idChild)) {
                                    m_LstTabGroupID.remove(idChild);
                                }
                            }
                        }
                    }
                    //Mảng còn
                    int idxNew = 0;
                    if (idx < m_LstTabGroupID.size() - 1) {
                        idxNew = idx + 1;
                    } else if (idx >= m_LstTabGroupID.size() - 1) {
                        idxNew = m_LstTabGroupID.size() - 1;
                    } else if (idx > 0) {
                        idxNew = idx - 1;
                    }
                    if (idxNew >= 0 && idxNew < m_LstTabGroupID.size()) {
                        LoadDetail(m_LstTabGroupID.get(idxNew));
                        updateNavigator(idxNew, m_LstTabGroupID);
                    } else {
                        setFormMode(enumFormMode.eInit);
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * Lấy mảng phần tử để duyệt
     *
     * @return
     */
    public List<String> getLstTabGroupID() {
        if (m_LstTabGroupID != null) {
            return m_LstTabGroupID;
        }
        //Lay lai list
        m_LstTabGroupID = (List<String>) WorkingContext.getSessionValue(WorkingContext.KEY_LSTID_FORNAV);
        WorkingContext.setSessionValue(WorkingContext.KEY_LSTID_FORNAV, null);
        return m_LstTabGroupID;
    }

    @Override
    protected void setPageDetailInfo(enumResultSetPosition resultSetPosition) {
        try {
            getLstTabGroupID();
            setDisableNavigator(true);
            if (m_LstTabGroupID != null && m_TabGroup != null) {
                if (!m_LstTabGroupID.isEmpty() && m_TabGroup.getRptgroupid() != null) {
                    //Tìm vị trí hiện tại
                    String id = m_TabGroup.getRptgroupid();
                    int idx = m_LstTabGroupID.indexOf(id);
                    if (idx >= 0 && idx < m_LstTabGroupID.size()) {
                        if (resultSetPosition.equals(enumResultSetPosition.eInit)) {
                            updateNavigator(idx, m_LstTabGroupID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eFirst)) {
                            LoadDetail(m_LstTabGroupID.get(0));
                            updateNavigator(0, m_LstTabGroupID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.ePrevious)) {
                            idx--;
                            if (idx >= 0) {
                                LoadDetail(m_LstTabGroupID.get(idx));
                            }
                            updateNavigator(idx, m_LstTabGroupID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eNext)) {
                            idx++;
                            if (idx < m_LstTabGroupID.size()) {
                                LoadDetail(m_LstTabGroupID.get(idx));
                            }
                            updateNavigator(idx, m_LstTabGroupID);
                        } else {//last
                            LoadDetail(m_LstTabGroupID.get(m_LstTabGroupID.size() - 1));
                            updateNavigator(m_LstTabGroupID.size() - 1, m_LstTabGroupID);
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
        m_Style_TxtID = "";
        m_Style_TxtSiteID = "";
        m_MsgCheck = "";
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Events">
    public void onAddNewDetail(ActionEvent event) {
        try {
            AddNewDetail();

        } catch (Exception ex) {
            showErrorFromException(ex, ReportGroupBean.class
                    .getName() + ".onAddNewDetail()");
        }
    }

    public void onSaveDetail(ActionEvent event) {
        try {
            if (!CheckInput()) {
                if (!m_MsgCheck.isEmpty()) {
                    WorkingContext.showMessages(enumMessageMode.eError, null, m_MsgCheck);
                }
                return;
            }
            if (!checkID_Standard(m_TabGroup.getRptgroupid())) {
                return;
            }
            //Lưu
            m_TabGroup.setOrgid(WorkingContext.getOrganizationCurrent());

            if (getFormMode() == enumFormMode.eAddNew) {
                if (getIReportConfigRemote() != null) {
                    if (getIReportConfigRemote().insert(m_TabGroup)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                        //List
                        UpdateListDetails(0, m_TabGroup.getRptgroupid(), null);
                        WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, "AddNew");
                    }

                }
            } else {
                if (getIReportConfigRemote() != null) {
                    if (getIReportConfigRemote().update(m_TabGroup)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                        WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, "Update");

                    }
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportGroupBean.class
                    .getName() + ".onSaveDetail()");
        }
    }

    public void onDeleteDetail(ActionEvent event) {
        try {
            if (m_TabGroup.getRptgroupid() != null && getIReportConfigRemote() != null) {
                String id = m_TabGroup.getRptgroupid();
                List<String> lst = getIReportConfigRemote().deleteReportGroup(id);
                if (lst != null) {
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    CleanScreen();
                    UpdateListDetails(1, id, lst);
                    WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, "Del");

                } else {
                    showError(m_rfCommon.getMessage("msgDeleteError") + BaseConstant.CRLF() + getIReportConfigRemote().getLastActionInfo(),
                            ReportGroupBean.class
                            .getName() + ".onDeleteDetail()");
                }

            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportGroupBean.class
                    .getName() + ".onDeleteDetail()");
        }
    }

    /*
     * Chọn nhóm bảng cha
     */
    public void onAcceptChooseTabGroupParent(ActionEvent event) {
        List<S_Report_Group> lst = ReportGroupDialogBean.getSelected();
        if (lst != null) {
            if (!lst.isEmpty()) {
                m_TabGroupParent = lst.get(0);
                m_TabGroup.setRptgroupid_parent(m_TabGroupParent.getRptgroupid());
            }
        }
    }

    public void onCancelChooseParent(CloseEvent event) {
        ReportGroupDialogBean.remove();
    }

    //}}</editor-fold>
    //{{<editor-fold defaultstate="collapsed" desc="Properties">
    public IReportConfigRemote getIReportConfigRemote() {
        try {
            if (m_IReportConfigRemote == null) {
                m_IReportConfigRemote = (IReportConfigRemote) EjbContext.getLocalEJBRemote(IReportConfigRemote.class
                        .getSimpleName());
            } else {
                EjbContext.LoginEJB();
            }
        } catch (Exception e) {
            return null;
        }
        return m_IReportConfigRemote;
    }

    public S_Report_Group getTabGroup() {
        if (m_TabGroup == null) {
            m_TabGroup = new S_Report_Group();
        }
        return m_TabGroup;
    }

    public void setTabGroup(S_Report_Group m_TabGroup) {
        this.m_TabGroup = m_TabGroup;
    }

    public String getTabGroupParentDesc() {
        if (m_TabGroupParent != null && m_TabGroup != null) {
            if (m_TabGroup.getRptgroupid_parent() != null) {
                if (!m_TabGroup.getRptgroupid_parent().isEmpty()) {
                    return m_TabGroupParent.getRptgroupdesc();
                }
            }
        }
        return null;
    }

    public Boolean getTxtIDReadOnly() {
        if (getFormMode() == enumFormMode.eAddNew) {
            return false;
        }
        return true;
    }

    public String getStyle_TxtID() {
        return m_Style_TxtID;
    }

    public ReportConfigBean getReportConfigBean() {
        return reportConfigBean;
    }

    public void setReportConfigBean(ReportConfigBean reportConfigBean) {
        this.reportConfigBean = reportConfigBean;
        if (getFormMode() != enumFormMode.eAddNew) {
            setPageDetailInfo(enumResultSetPosition.eInit);
        }
    }
    //}}</editor-fold>

    public boolean getDisabledSaveData() {
        if (getFormMode() == enumFormMode.eAddNew) {
            return false;
        }

        return false;
    }

    public boolean getDisabledDeleteData() {
        if (getFormMode() == enumFormMode.eAddNew) {
            return true;
        }
        return false;
    }
}
