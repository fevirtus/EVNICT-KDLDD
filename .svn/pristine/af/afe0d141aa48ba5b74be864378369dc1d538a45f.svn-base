package main.web.shared.report.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.report.S_Report;
import main.entity.shared.report.S_Report_Attr;
import main.entity.shared.report.S_Report_Datalist;
import main.entity.shared.report.S_Report_Form;
import main.entity.shared.report.S_Report_Group;
import main.entity.shared.system.S_Attribute_Group;
import main.remote.shared.report.IReportConfigRemote;
import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageBean;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import main.web.shared.dialog.bean.AttributeGroupDialog;
import main.web.shared.dialog.bean.ReportGroupDialogBean;
import main.web.shared.system.bean.AttGroupBean;
import evnit.util.common.BaseConstant;
import evnit.util.common.BaseConstant.enumFormMode;
import evnit.util.common.BaseConstant.enumMessageMode;
import evnit.util.common.BaseConstant.enumResultSetPosition;
import evnit.util.common.S_Key_ControlInfo;
import evnit.util.common.Tools;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;
import main.web.upload.Download;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CloseEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.util.SerializableSupplier;

/**
 *
 * @author hanv
 */
@ManagedBean
@ViewScoped
public class ReportDetailBean extends BasePageBean implements Serializable {

    private IReportConfigRemote m_IReportConfigRemote;
    private S_Report m_S_Report;
    private S_Report_Group m_S_ReportGroup;
    private ResourcesFactory m_RfAssetProp;
    //Check nhập
    String m_Style_TxtReportID;
    String m_Style_TxtReportGroup;
    String m_Style_TxtUomInput;
    String m_Style_TxtColumn;
    String m_MsgCheck;
    String reportgroupidfrom;

//    @ManagedProperty(name = "assetReportBean", value = "#{assetReportBean}")
//    private AssetReportBean assetReportBean;
    List<String> m_LstReportID = null;

    private String m_sDeleteMsg;

    private List<S_Report_Form> lstReportForm;
    private List<S_Report_Datalist> lstReportDataList;
    private S_Report_Form[] selectedReportForm;
    private S_Report_Datalist[] selectedReportDataList;
    String g_rptID;
    private StreamedContent fileDownload;
    private InputStream stream = null;

    private enum enumAction {

        eNone,
        eRATTDelete
    }
    private enumAction m_eActionCurrent = enumAction.eNone;

    //{{<editor-fold defaultstate="collapsed" desc="Contructor">
    public ReportDetailBean() {
        if (m_RfAssetProp == null) {
            m_RfAssetProp = new ResourcesFactory("main/web/shared/report/prop/ReportProp");
            //new ResourcesFactory("main/web/eam/asset/prop/AssetProp");
        }
        if (!isPostback()) {
            String reportid = WorkingContext.getRequestQueryString("rptid");
            g_rptID = reportid;
            if (reportid != null) {
                LoadDetail(reportid);
                loadReportDataList();
                loadReportForm();
                WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, null);
            } else {

                reportgroupidfrom = WorkingContext.getRequestQueryString("rptgroupid");
                AddNewDetail();

            }
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Functions">    
    public void preFileDownload(ActionEvent event) {
        stream = null;
        S_Report_Form tempLib = null;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        //edit by thinhnd
        SerializableSupplier<InputStream> temp_stream = new SerializableSupplier<InputStream>() {
            @Override
            public InputStream get() {
                return stream;
            }
        };
        try {
            if (externalContext.getRequestParameterMap().containsKey("rptformid")) {
                //Tải file về server
                Download download = new Download();
                stream = download.downloadExcelTemplate(externalContext.getRequestParameterMap().get("rptformid"));
                tempLib = (S_Report_Form) getIReportConfigRemote().findById(externalContext.getRequestParameterMap().get("rptformid"), S_Report_Form.class);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(ReportDetailBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stream == null) {
                byte[] bytes = "error file".getBytes();
                stream = new ByteArrayInputStream(bytes);
                //fileDownload = new DefaultStreamedContent(stream);
                fileDownload = new DefaultStreamedContent();
            } else {
                String app = "video";
                String[] strTemp = tempLib.getFilename().split("\\.");
                if (strTemp.length > 1) {
                    app = strTemp[strTemp.length - 1];
                }
                fileDownload = DefaultStreamedContent.builder().name(tempLib.getFilename()).contentType("application/" + app).stream(temp_stream).build();
            }
        }
    }

    public StreamedContent getFileDownload() {
        return fileDownload;
    }

    private boolean checkSelectionReportForm() {
        if (selectedReportForm != null) {
            if (selectedReportForm.length > 0) {
                return true;
            }
        }
        //Thong bao chon hang
        WorkingContext.showMessages(enumMessageMode.eWarn, null, "Chưa chọn mẫu báo cáo");
        return false;
    }

    private boolean checkSelectionReportDataList() {
        if (selectedReportDataList != null) {
            if (selectedReportDataList.length > 0) {
                return true;
            }
        }
        //Thong bao chon hang
        WorkingContext.showMessages(enumMessageMode.eWarn, null, "Chưa chọn mẫu báo cáo");
        return false;
    }

    public void onDeleteReportDataListStart(ActionEvent event) {
        try {
            if (checkSelectionReportDataList()) {
                m_sDeleteMsg = m_rfCommon.getMessage("msgDlgDeleteInfo");
                m_sDeleteMsg = m_sDeleteMsg.replaceAll("@count", String.valueOf(selectedReportDataList.length));
                if (selectedReportDataList.length == 1) {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", " ('" + selectedReportDataList[0].getDatalistid() + "')");
                } else {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", "");
                }

                //RequestContext context = RequestContext.getCurrentInstance();
                //context.addCallbackParam("Method", "DeleteReportDataList");
                PrimeFaces.current().ajax().addCallbackParam("Method", "DeleteReportDataList");
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportDetailBean.class.getName() + ".onDeleteReportDataListStart()");
        }
    }

    public void onDeleteReportDataListEnd(ActionEvent event) {

        try {
            if (checkSelectionReportDataList()) {
                if (selectedReportDataList != null) {
                    for (int i = 0; i < selectedReportDataList.length; i++) {

                        if (!getIReportConfigRemote().delete(selectedReportDataList[i].getDatalistid(), S_Report_Datalist.class)) {
                            showError(m_IReportConfigRemote.getLastActionInfo(), ReportDetailBean.class.getName() + ".onDeleteReportDataListEnd()");
                            return;
                        }
                    }
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    loadReportDataList();
                    selectedReportForm = null;
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportDetailBean.class.getName() + ".onDeleteReportFormEnd()");
        }
    }

    public void onDeleteReportFormStart(ActionEvent event) {
        try {
            if (checkSelectionReportForm()) {
                m_sDeleteMsg = m_rfCommon.getMessage("msgDlgDeleteInfo");
                m_sDeleteMsg = m_sDeleteMsg.replaceAll("@count", String.valueOf(selectedReportForm.length));
                if (selectedReportForm.length == 1) {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", " ('" + selectedReportForm[0].getRptformid() + "')");
                } else {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", "");
                }

                //RequestContext context = RequestContext.getCurrentInstance();
                //context.addCallbackParam("Method", "DeleteReportForm");
                PrimeFaces.current().ajax().addCallbackParam("Method", "DeleteReportForm");
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportDetailBean.class.getName() + ".onDeleteReportFormStart()");
        }
    }

    public void onCloseReportDataList(CloseEvent event) {
        if (getIReportConfigRemote() == null) {
            return;
        }
        loadReportDataList();
    }

    public void onCloseReportForm(CloseEvent event) {
        if (getIReportConfigRemote() == null) {
            return;
        }
        loadReportForm();
    }

    public void onDeleteEnd(ActionEvent event) {
        switch (m_eActionCurrent) {
            case eRATTDelete:
                onDeleteReportAttEnd(event);
                break;

        }
    }

    public void onDeleteReportFormEnd(ActionEvent event) {

        try {
            if (checkSelectionReportForm()) {
                if (selectedReportForm != null) {
                    for (int i = 0; i < selectedReportForm.length; i++) {

                        if (!getIReportConfigRemote().delete(selectedReportForm[i].getRptformid(), S_Report_Form.class)) {
                            showError(m_IReportConfigRemote.getLastActionInfo(), ReportDetailBean.class.getName() + ".onDeleteReportFormEnd()");
                            return;
                        }
                    }
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    loadReportForm();
                    selectedReportForm = null;
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportDetailBean.class.getName() + ".onDeleteReportFormEnd()");
        }
    }

    private void loadReportDataList() {
        try {
            lstReportDataList = null;
            if (getIReportConfigRemote() == null) {
                return;
            }
            lstReportDataList = getIReportConfigRemote().getListReportDataList(g_rptID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadReportForm() {
        try {
            lstReportForm = null;
            if (getIReportConfigRemote() == null) {
                return;
            }

            lstReportForm = getIReportConfigRemote().getListReportForm(g_rptID);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void CleanScreen() {
        m_S_Report = new S_Report();
        m_S_Report.setEnable(true);
        m_S_ReportGroup = null;
        //m_LstReportID = null;
        resetInputRequire();
    }

    /**
     * Load
     *
     * @param optabid
     */
    public void LoadDetail(String reportid) {
        try {
            CleanScreen();
            setFormMode(enumFormMode.eView);
            //Caller
            m_S_Report = (S_Report) getIReportConfigRemote().findById(reportid, S_Report.class);
            if (m_S_Report != null) {
                //Load nhom
                m_S_ReportGroup = (S_Report_Group) getIReportConfigRemote().findById(m_S_Report.getRptgroupid(), S_Report_Group.class);
                String sRight = BaseConstant.getObjRightFull();
                //Save quyền đã check vào session để dùng cho form thuộc tính mở rộng
                WorkingContext.setSessionValue(WorkingContext.OBJ_RIGHT, sRight);

            } else {
                m_bDelete = true;
                m_bSave = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            showErrorFromException(ex, ReportDetailBean.class.getName() + "LoadDetail()");
        }
    }

    public void AddNewDetail() {
        CleanScreen();
        m_S_Report.setRptgroupid(reportgroupidfrom);
        m_S_ReportGroup = (S_Report_Group) getIReportConfigRemote().findById(reportgroupidfrom, S_Report_Group.class);
        setFormMode(enumFormMode.eAddNew);
        m_bSave = false;
        String sRight = BaseConstant.getObjRightFull();
        //Save quyền đã check vào session để dùng cho form thuộc tính mở rộng
        WorkingContext.setSessionValue(WorkingContext.OBJ_RIGHT, sRight);
        WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, "AddNew");
    }

    private boolean CheckInput() {
        try {
            resetInputRequire();
            if (m_S_Report == null) {
                return false;
            }
            boolean bCheck = true;
            S_Report tg = m_S_Report;
            //Check nhap
            if (!checkID_Standard(tg.getRptid())) {
                bCheck = false;
                m_Style_TxtReportID = "error";
            }
            if (tg.getRptdesc() == null || tg.getRptdesc().trim().isEmpty()) {
                bCheck = false;
                m_Style_TxtReportID = "error";
            }
            if (tg.getRptgroupid() == null || tg.getRptgroupid().trim().isEmpty()) {
                bCheck = false;
                m_Style_TxtReportGroup = "error";
            }
            if (!bCheck) {
                m_MsgCheck = m_rfCommon.getMessage("msgNotEnoughDataInput");
                return false;
            }
            /*
             * Valid du lieu nhap
             */

            //Kiểm tra ID
            if (getFormMode() == enumFormMode.eAddNew) {
                if (tg.getRptid() != null && !tg.getRptdesc().trim().isEmpty()) {
                    S_Report m_S_Report1 = (S_Report) getIReportConfigRemote().findById(m_S_Report.getRptid(), S_Report.class);
                    if (m_S_Report1 != null) {
                        m_MsgCheck = m_RfAssetProp.getMessage("msgReportExists");
                        return false;
                    }
                }
            }
            //Kiểm tra nhóm
            m_S_ReportGroup = (S_Report_Group) getIReportConfigRemote().findById(m_S_Report.getRptgroupid(), S_Report_Group.class);
            if (m_S_ReportGroup == null) {
                m_MsgCheck = m_RfAssetProp.getMessage("msgReportGroupNotExists");
                return false;
            }

            //Vùng thay đổi
            if (getFormMode() == enumFormMode.eAddNew) {
                m_S_Report.setUserCrId(WorkingContext.getUserName());
                m_S_Report.setUserCrDtime(new Date());

                /*
                 * Sinh mã
                 */
                //Sinh key
                ISystemCommonRemote iscr = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
                S_Key_ControlInfo kc = new S_Key_ControlInfo(m_S_Report.getRptid(), S_Report.class.getSimpleName());
                kc = iscr.getGenKeyID(kc);
                if (kc == null || kc.getGenStatus() != 0) //Không thành công
                {
                    showErrorFromGenKey(kc, ReportDetailBean.class.getName() + ".CheckInput()");
                    return false;
                }
                m_S_Report.setRptid(kc.getOutputValue());
            } else {
                m_S_Report.setUserMdfId(WorkingContext.getUserName());
                m_S_Report.setUserMdfDtime(new Date());
            }
            return bCheck;
        } catch (Exception e) {
            showErrorFromException(e, ReportDetailBean.class.getName() + ".CheckInput()");
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
    private void UpdateListDetails(int type, String id) {
        //getLstReportID();
        m_LstReportID = (List<String>) WorkingContext.getSessionValue("LIST_METER");
        if (m_LstReportID == null) {
            return;
        }
        switch (type) {
            case 0://Them moi
                m_LstReportID.add(id);
                updateNavigator(m_LstReportID.size() - 1, m_LstReportID);
                break;
            case 1://Delete
                if (m_LstReportID.contains(id)) {
                    int idx = m_LstReportID.indexOf(id);
                    m_LstReportID.remove(id);
                    //Mảng còn
                    int idxNew = 0;
                    if (idx < m_LstReportID.size() - 1) {
                        idxNew = idx + 1;
                    } else if (idx >= m_LstReportID.size() - 1) {
                        idxNew = m_LstReportID.size() - 1;
                    } else if (idx > 0) {
                        idxNew = idx - 1;
                    }
                    if (idxNew >= 0 && idxNew < m_LstReportID.size()) {
                        LoadDetail(m_LstReportID.get(idxNew));
                        updateNavigator(idxNew, m_LstReportID);
                    } else {
                        setFormMode(enumFormMode.eInit);
                    }
                }
                break;
            default:
                break;
        }
        WorkingContext.setSessionValue("LIST_METER", m_LstReportID);
    }

    /**
     * Lấy mảng phần tử để duyệt
     *
     * @return
     */
//    public List<String> getLstReportID(){
//        if(m_LstReportID!=null)
//            return m_LstReportID;
//        //Lay lai list
//        if(assetReportBean!=null){
//            List<S_Report> lstTabs = assetReportBean.getM_LstReports();
//            if(lstTabs!=null){
//                m_LstReportID = new ArrayList<String>();
//                for(S_Report tab:lstTabs)
//                    m_LstReportID.add(tab.getRptid());
//            }
//        }
//        return m_LstReportID;
//    }
    @Override
    protected void setPageDetailInfo(enumResultSetPosition resultSetPosition) {
        try {
            //getLstReportID();
            m_LstReportID = (List<String>) WorkingContext.getSessionValue("LIST_METER");
            setDisableNavigator(true);
            if (m_LstReportID != null && m_S_Report != null) {
                if (!m_LstReportID.isEmpty() && m_S_Report.getRptid() != null) {
                    //Tìm vị trí hiện tại
                    String id = m_S_Report.getRptid();
                    int idx = m_LstReportID.indexOf(id);
                    if (idx >= 0 && idx < m_LstReportID.size()) {
                        if (resultSetPosition.equals(enumResultSetPosition.eInit)) {
                            updateNavigator(idx, m_LstReportID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eFirst)) {
                            LoadDetail(m_LstReportID.get(0));
                            updateNavigator(0, m_LstReportID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.ePrevious)) {
                            idx--;
                            if (idx >= 0) {
                                LoadDetail(m_LstReportID.get(idx));
                            }
                            updateNavigator(idx, m_LstReportID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eNext)) {
                            idx++;
                            if (idx < m_LstReportID.size()) {
                                LoadDetail(m_LstReportID.get(idx));
                            }
                            updateNavigator(idx, m_LstReportID);
                        } else {//last
                            LoadDetail(m_LstReportID.get(m_LstReportID.size() - 1));
                            updateNavigator(m_LstReportID.size() - 1, m_LstReportID);
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
        m_Style_TxtReportID = "";
        m_Style_TxtReportGroup = "";
        m_Style_TxtUomInput = "";
        m_Style_TxtColumn = "";
        m_MsgCheck = "";
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Events">
    public void onAcceptChooseReportGroup(ActionEvent event) {
        List<S_Report_Group> lst = ReportGroupDialogBean.getSelected();
        if (lst != null) {
            if (!lst.isEmpty()) {
                m_S_ReportGroup = lst.get(0);
                m_S_Report.setRptgroupid(m_S_ReportGroup.getRptgroupid());
            }
        }
    }

    public void onAddNewDetail(ActionEvent event) {
        try {
            AddNewDetail();
        } catch (Exception ex) {
            showErrorFromException(ex, ReportDetailBean.class.getName() + ".onAddNewDetail()");
        }
    }

    private boolean checkSelectionReport() {
        if (selectedReportAtt != null) {
            if (selectedReportAtt.length > 0) {
                return true;
            }
        }
        //Thong bao chon hang
        WorkingContext.showMessages(enumMessageMode.eWarn, null, m_RfAssetProp.getMessage("msgNotSelectAT"));
        return false;
    }

    public void onDeleteReportAttStart(ActionEvent event) {
        try {
            if (checkSelectionReport()) {
                m_eActionCurrent = ReportDetailBean.enumAction.eRATTDelete;
                m_sDeleteMsg = m_rfCommon.getMessage("msgDlgDeleteInfo");
                m_sDeleteMsg = m_sDeleteMsg.replaceAll("@count", String.valueOf(selectedReportAtt.length));
                if (selectedReportAtt.length == 1) {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", " ('" + selectedReportAtt[0].getId().getAttrgroupid() + "')");
                } else {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", "");
                }

//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "DeleteReportAtt");
//                
                PrimeFaces.current().ajax().addCallbackParam("Method", "DeleteReportAtt");
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportDetailBean.class.getName() + ".onDeleteReportAttStart()");
        }
    }

    public void onDeleteReportAttEnd(ActionEvent event) {

        try {
            if (checkSelectionReport()) {
                if (selectedReportAtt != null) {
                    for (int i = 0; i < selectedReportAtt.length; i++) {
                        if (!getIReportConfigRemote().delete(selectedReportAtt[0].getId(), S_Report.class)) {
                            showError(getIReportConfigRemote().getLastActionInfo(), ReportDetailBean.class.getName() + ".onDeleteReportAttEnd()");
                            return;
                        }
                    }
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    listReportAtt = null;
                    getListReportAtt();
                    ReportAttDataModel = null;
                    ReportAttDataModel = new ListReportAttDataModel(listReportAtt);
                    selectedReportAtt = null;
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportDetailBean.class.getName() + ".onDeleteCAATEnd()");
        }
    }

    public void onSaveReportAtt(ActionEvent event) {
        try {
            if (getIReportConfigRemote() != null && listReportAtt != null) {
                if (!listReportAtt.isEmpty()) {
                    //Load lai
                    for (S_Report_Attr o : listReportAtt) {
                        getIReportConfigRemote().update(o);
                    }
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportDetailBean.class.getName() + ".onSaveReportAtt()");
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

            //Check quyen du lieu
            String s;
            if (!checkRightEditOrg(m_S_ReportGroup.getOrgid())) {
                s = m_rfCommon.getMessage("msgNotRightOnOrg") + " (ID=" + m_S_ReportGroup.getOrgid() + ")";
                WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, s);
                return;
            }

            //Lưu
            if (getFormMode() == enumFormMode.eAddNew) {
                if (getIReportConfigRemote() != null) {
                    if (m_IReportConfigRemote.insert(m_S_Report)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                        //List
                        UpdateListDetails(0, m_S_Report.getRptid());
                        AttGroupBean.SaveAttGroupDefaultForObject(getISystemCommonRemote(), m_S_Report.getRptid(), "RPT");
                        String sRight = BaseConstant.getObjRightFull();
                        //Save quyền đã check vào session để dùng cho form thuộc tính mở rộng
                        WorkingContext.setSessionValue(WorkingContext.OBJ_RIGHT, sRight);
                        WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, "AddNew");
                    }
                }
            } else {
                if (getIReportConfigRemote() != null) {
                    if (m_IReportConfigRemote.update(m_S_Report)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                        String sRight = BaseConstant.getObjRightFull();
                        //Save quyền đã check vào session để dùng cho form thuộc tính mở rộng
                        WorkingContext.setSessionValue(WorkingContext.OBJ_RIGHT, sRight);
                        WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, "Update");
                    }
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportDetailBean.class.getName() + ".onSaveDetail()");
        }
    }

    public void onDeleteDetail(ActionEvent event) {
        try {
            if (m_S_ReportGroup.getRptgroupid() != null && getIReportConfigRemote() != null) {
                String id = m_S_Report.getRptid();
                boolean bDel = getIReportConfigRemote().delete(id, S_Report.class);
                if (bDel) {
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    CleanScreen();
                    UpdateListDetails(1, id);
                    WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, "Del");
                } else {
                    showError(m_rfCommon.getMessage("msgDeleteError") + BaseConstant.CRLF() + getIReportConfigRemote().getLastActionInfo(),
                            ReportDetailBean.class.getName() + ".onDeleteDetail()");
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportDetailBean.class.getName() + ".onDeleteDetail()");
        }
    }

    /*
     * Chọn nhóm bảng
     */
    public boolean checkExistsATInListReportAtt(List<S_Report_Attr> lst, String atid) {
        for (S_Report_Attr obj : lst) {
            if (obj.getId().getAttrgroupid().equals(atid)) {
                return true;
            }
        }
        return false;
    }

    public void onAcceptChooseReportAttributeGroup(ActionEvent event) {
        try {
            List<S_Attribute_Group> lstATDialog = AttributeGroupDialog.getSelected();
            if (lstATDialog != null) {
                if (!lstATDialog.isEmpty()) {
                    int count = 0;
                    for (S_Attribute_Group at : lstATDialog) {
                        if (!checkExistsATInListReportAtt(listReportAtt, at.getAttrgroupid())) {
                            getIReportConfigRemote().insert(new S_Report_Attr(m_S_Report.getRptid(), at.getAttrgroupid()));
                            count++;
                        }
                    }
                    String msg = m_RfAssetProp.getMessage("msgAddATSuccessful");
                    msg = msg.replaceAll("@count", String.valueOf(count));
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, msg);
                    listReportAtt = null;
                    ReportAttDataModel = null;
                    getListReportAtt();
                    ReportAttDataModel = new ListReportAttDataModel(listReportAtt);
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportDetailBean.class.getName() + ".onDeleteCAATStart()");
        }
    }

    public void onCancelChooseAttributeGroup(CloseEvent event) {
        AttributeGroupDialog.remove();
    }

    //}}</editor-fold>
    //{{<editor-fold defaultstate="collapsed" desc="Properties">
    public S_Report_Attr[] getSelectedReportAtt() {
        return selectedReportAtt;
    }

    public void setSelectedReportAtt(S_Report_Attr[] selectedReportAtt) {
        this.selectedReportAtt = selectedReportAtt;
    }

    public S_Report_Form[] getSelectedReportForm() {
        return selectedReportForm;
    }

    public void setSelectedReportForm(S_Report_Form[] selectedReportForm) {
        this.selectedReportForm = selectedReportForm;
    }

    public S_Report_Datalist[] getSelectedReportDataList() {
        return selectedReportDataList;
    }

    public void setSelectedReportDataList(S_Report_Datalist[] selectedReportDataList) {
        this.selectedReportDataList = selectedReportDataList;
    }

    private ISystemCommonRemote m_ISystemCommonRemote = null;

    public ISystemCommonRemote getISystemCommonRemote() {
        if (m_ISystemCommonRemote == null) {
            try {
                m_ISystemCommonRemote = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
            } catch (Exception ex) {
            }
        }
        return m_ISystemCommonRemote;
    }

    public IReportConfigRemote getIReportConfigRemote() {
        try {
            if (m_IReportConfigRemote == null) {
                m_IReportConfigRemote = (IReportConfigRemote) EjbContext.getLocalEJBRemote(IReportConfigRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login lại khi gọi ejb
            }
        } catch (Exception e) {
            return null;
        }
        return m_IReportConfigRemote;
    }

    public S_Report getReport() {
        if (m_S_Report == null) {
            m_S_Report = new S_Report();
        }
        return m_S_Report;
    }

    public void setReport(S_Report report_Tab) {
        this.m_S_Report = report_Tab;
    }

    public String getReportGroupDesc() {
        if (m_S_ReportGroup != null) {
            return m_S_ReportGroup.getRptgroupdesc();
        }
        return null;
    }

    public Boolean getTxtIDReadOnly() {
        if (getFormMode() == enumFormMode.eAddNew) {
            return false;
        }
        return true;
    }

    public Boolean getDisableBtnAddNewReport() {
        if (getFormMode() == enumFormMode.eAddNew) {
            return true;
        }
        return false;
    }

    public String getStyle_TxtReportID() {
        return m_Style_TxtReportID;
    }

    public String getStyle_TxtReportGroup() {
        return m_Style_TxtReportGroup;
    }

    public String getStyle_TxtUomInput() {
        return m_Style_TxtUomInput;
    }

    public String getStyle_TxtColumn() {
        return m_Style_TxtColumn;
    }

//    public AssetReportBean getAssetReportBean() {
//        return assetReportBean;
//    }
//
//    public void setAssetReportBean(AssetReportBean assetReportBean) {
//        this.assetReportBean = assetReportBean;
//        if (getFormMode() != enumFormMode.eAddNew)
//            setPageDetailInfo(enumResultSetPosition.eInit);
//    }
    public String getM_sDeleteMsg() {
        return m_sDeleteMsg;
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

    public ReportFormDataModel getReportFormDataModel() {
        if (lstReportForm != null) {
            return new ReportFormDataModel(lstReportForm);
        }
        return null;
    }

    public ReportDataListDataModel getReportDataListDataModel() {
        if (lstReportDataList != null) {
            return new ReportDataListDataModel(lstReportDataList);
        }
        return null;
    }
    //{{<editor-fold defaultstate="collapsed" desc="Thay doi STT">
    private int m_AttRowCount = 0;

    public int getAttRowCount() {
        m_AttRowCount = 0;
        if (listReportAtt != null) {
            m_AttRowCount = listReportAtt.size();
        }
        return m_AttRowCount;
    }

    public boolean getDisableButtonRowSort() {
        if (getAttRowCount() <= 0) {
            return true;
        }
        return false;
    }

    public void MoveAttribute(int action) {
        try {

            //Lấy nút chọn            
            if (selectedReportAtt != null) {
                if (selectedReportAtt.length == 1) {
                    getIReportConfigRemote().OrderOnGroup(selectedReportAtt[0].getId().getAttrgroupid(), selectedReportAtt[0].getId().getRptid(), action);
                    listReportAtt = getIReportConfigRemote().getAllReportByAtt(selectedReportAtt[0].getId().getRptid());
                    WorkingContext.showMessages(enumMessageMode.eInfo, null,
                            m_rfCommon.getMessage("msgMoveRecordSuccess"));
                } else if (selectedReportAtt.length > 1) {
                    WorkingContext.showMessages(enumMessageMode.eWarn, null,
                            m_rfCommon.getMessage("msgChooseOneRecord"));
                    return;
                }
            } else {
                //Thông báo chưa chọn hàng
                WorkingContext.showMessages(enumMessageMode.eWarn, null,
                        m_rfCommon.getMessage("msgNotCheckRecord"));
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportDetailBean.class.getName() + ".MoveAttribute()");
        }
    }

    public void onMoveAttributeFirst(ActionEvent event) {
        MoveAttribute(0);
    }

    public void onMoveAttributeUp(ActionEvent event) {
        MoveAttribute(1);
    }

    public void onMoveAttributeDown(ActionEvent event) {
        MoveAttribute(2);
    }

    public void onMoveAttributeLast(ActionEvent event) {
        MoveAttribute(3);
    }

    //}}</editor-fold>
    //
    private List<S_Report_Attr> listReportAtt;
    private S_Report_Attr[] selectedReportAtt;
    private ListReportAttDataModel ReportAttDataModel;

    public List<S_Report_Attr> getListReportAtt() {
        if (listReportAtt == null && m_S_Report != null) {
            if (m_S_Report.getRptid() != null && getIReportConfigRemote() != null) {
                listReportAtt = getIReportConfigRemote().getAllReportByAtt(m_S_Report.getRptid());
            } else {
                listReportAtt = new ArrayList<S_Report_Attr>();
            }
        }
        return listReportAtt;
    }

    public ListReportAttDataModel getTableReportAttDataModel() {
        getListReportAtt();
        ReportAttDataModel = new ListReportAttDataModel(listReportAtt);
        return ReportAttDataModel;
    }
}

class ListReportAttDataModel extends ListDataModel<S_Report_Attr> implements SelectableDataModel<S_Report_Attr> {

    public ListReportAttDataModel() {
    }

    public ListReportAttDataModel(List<S_Report_Attr> data) {
        super(data);
    }

    @Override
    public String getRowKey(S_Report_Attr t) {
        return Tools.fStandardPdataTableID(t.getId().getRptid()
                + t.getId().getAttrgroupid());
    }

    @Override
    public S_Report_Attr getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

        List<S_Report_Attr> list = (List<S_Report_Attr>) getWrappedData();

        for (S_Report_Attr lst : list) {
            if (Tools.fStandardPdataTableID(lst.getId().getRptid()
                    + lst.getId().getAttrgroupid()).equals(rowKey)) {
                return lst;
            }
        }
        return null;
    }
}

class ReportFormDataModel extends ListDataModel<S_Report_Form> implements SelectableDataModel<S_Report_Form> {

    public ReportFormDataModel() {
    }

    public ReportFormDataModel(List<S_Report_Form> data) {
        super(data);
    }

    @Override
    public String getRowKey(S_Report_Form t) {
        return Tools.fStandardPdataTableID(t.getRptformid());
    }

    @Override
    public S_Report_Form getRowData(String rowKey) {
        List<S_Report_Form> list = (List<S_Report_Form>) getWrappedData();

        for (S_Report_Form lst : list) {
            if (Tools.fStandardPdataTableID(lst.getRptformid()).equals(rowKey)) {
                return lst;
            }
        }
        return null;
    }
}

class ReportDataListDataModel extends ListDataModel<S_Report_Datalist> implements SelectableDataModel<S_Report_Datalist> {

    public ReportDataListDataModel() {
    }

    public ReportDataListDataModel(List<S_Report_Datalist> data) {
        super(data);
    }

    @Override
    public String getRowKey(S_Report_Datalist t) {
        return Tools.fStandardPdataTableID(t.getDatalistid());
    }

    @Override
    public S_Report_Datalist getRowData(String rowKey) {
        List<S_Report_Datalist> list = (List<S_Report_Datalist>) getWrappedData();

        for (S_Report_Datalist lst : list) {
            if (Tools.fStandardPdataTableID(lst.getDatalistid()).equals(rowKey)) {
                return lst;
            }
        }
        return null;
    }
}
