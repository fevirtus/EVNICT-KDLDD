package main.web.shared.report.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.report.S_Report;
import main.entity.shared.report.S_Report_Group;
import main.remote.shared.report.IReportConfigRemote;
import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant;
import evnit.util.common.BaseConstant.enumMessageMode;
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
import main.remote.shared.report.IReportGrantRemote;
import org.primefaces.PrimeFaces;
import org.primefaces.component.tree.Tree;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.TreeNode;

/**
 *
 * @author hanv
 */
@ManagedBean
@ViewScoped
public class ReportConfigBean extends BasePageCommon implements Serializable {

    private TreeNode m_RootNode;
    private TreeNode m_SelectedNode;
    private int m_RowCount = 0;
    private String m_DetailHeaderGrd;
    private S_Report[] arrSelectReports;
    private List<S_Report> m_LstReports;
    private IReportConfigRemote m_IReportConfigRemote;
    private IReportGrantRemote iReport;
    private String m_sDeleteMsg;
    private ResourcesFactory m_RfTabProp;
    private ISystemCommonRemote m_ISystemCommonRemote;
    private boolean bCheckAll = false;
    private String sUrl;

    private enum enumAction {

        eNone,
        eGroupAdd,
        eGroupEdit,
        eGroupDelete,
        eTabAdd,
        eTabEdit,
        eTabDelete
    }
    private enumAction m_eActionCurrent = enumAction.eNone; //Biến lưu thao tác hiện tại

    //{{<editor-fold defaultstate="collapsed" desc="Contructor">   
    public ReportConfigBean() {
        if (m_RfTabProp == null) {
            m_RfTabProp = new ResourcesFactory("main/web/shared/report/prop/ReportProp");
        }
        if (!isPostback()) {
            loadTreeTableGroup();
            if (m_RootNode != null) {
                if (m_RootNode.getChildCount() > 0) {
                    Tree tree = (Tree) WorkingContext.findJSFComponent("formMain:trvMeterGroup", false);
                    if (tree != null) {
                        tree.setSelection(m_SelectedNode);
                    }
                    reloadGridReportTable();
                }
            }
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Vùng cây">
    /**
     * Khởi tạo tree group table
     */
    private void loadTreeTableGroup() {
        m_RootNode = new DefaultTreeNode("root", null);
        m_RootNode.setExpanded(true);
        if (getIReportConfigRemote() == null) {
            return;
        }
        //Load cha truong hop
        List<S_Report_Group> lstParent = null;
        List<S_Report_Group> lstAllPrivilege = null;
        if (WorkingContext.getWorkingInfo().getUserid().equals("admin")) {
            lstParent = getIReportConfigRemote().getListReportGroupByOrgID(WorkingContext.getOrganizationCurrent());
            if (lstParent != null) {
                for (S_Report_Group groupParent : lstParent) {
                    TreeNode nodeParent = new DefaultTreeNode(groupParent, m_RootNode);
                    nodeParent.setExpanded(true);
                    loadTreeTableGroup_Child(nodeParent);
                }
            }
        } else {
            lstParent
                    = getIReportRemote().getLstReportGroupByUser(WorkingContext.getWorkingInfo().getUserid());

            //Load toàn bộ tập quyền của user//
            lstAllPrivilege = getIReportRemote().getListReportGroupByOrgID(WorkingContext.getWorkingInfo().getOrgid(), WorkingContext.getWorkingInfo().getUserid());
            String lstUrls = "";

            if (lstAllPrivilege != null) {
                for (S_Report_Group groupParent : lstAllPrivilege) {
                    lstUrls = lstUrls + groupParent.getUrl();
                }
            }

            String surl;
            String[] argU;
            S_Report_Group sGroup;
            S_Report_Group sLeaf = new S_Report_Group();
            TreeNode gNode = m_RootNode;
            if (lstParent != null) {
                /*Tạo danh sách các node của cây*/
                for (S_Report_Group groupParent : lstParent) {
                    surl = groupParent.getRptgroupid_parent();
                    //argU = surl.split("#");
                    if (surl == null) {
                        sGroup = groupParent;
                    } else {
                        sGroup = (S_Report_Group) getIReportRemote().findById(surl, S_Report_Group.class);
                    }

                    if (!sGroup.equals(sLeaf)) {
                        TreeNode nodeParent = new DefaultTreeNode(sGroup, m_RootNode);
                        nodeParent.setExpanded(true);
                        sLeaf = sGroup;
                        loadTreeTableGroup_Child_PQ(nodeParent, lstUrls);
                    }
//                    if (!checkExistsNodeInTreeNode(sGroup, m_RootNode)) {
//                        TreeNode nodeParent = new DefaultTreeNode(sGroup, gNode);
//                        nodeParent.setExpanded(true);
//                        gNode = nodeParent;
//                    }
//                    for (int i = 0; i < argU.length; i++) {
//                        sGroup = (S_Report_Group) getIReportRemote().findById(argU[i], S_Report_Group.class);
//                        if (!checkExistsNodeInTreeNode(sGroup, gNode)) {
//                            TreeNode nodeParent = new DefaultTreeNode(sGroup, gNode);
//                            nodeParent.setExpanded(true);
//                            gNode = nodeParent;
//                            //nodeParent.setSelected(true);
//                            //sLeaf = sGroup;
//                            //loadTreeTableGroup_Child_PQ(gNode,surl);
//                        }
//                    }
                }
            }
        }

        m_RootNode.setExpanded(true);
    }

    private boolean checkExistsNodeInTreeNode(S_Report_Group snode, TreeNode snodegrp) {
        for (TreeNode nod : snodegrp.getChildren()) {
            if (((S_Report_Group) nod.getData()).equals(snode)) {
                return true;
            } else {
                //nod.setSelected(true);
                checkExistsNodeInTreeNode(snode, nod);
            }
        }

        return false;
    }

    /**
     * Build child
     *
     * @param nodeParent
     */
    private void loadTreeTableGroup_Child(TreeNode nodeParent) {
        S_Report_Group groupParent = (S_Report_Group) nodeParent.getData();
        List<S_Report_Group> lstChild = null;
        lstChild = getIReportConfigRemote().getListReportGroupChild(groupParent.getRptgroupid());
        if (lstChild != null) {
            for (S_Report_Group groupChild : lstChild) {
                TreeNode nodeChild = new DefaultTreeNode(groupChild, nodeParent);
                nodeChild.setExpanded(true);
                loadTreeTableGroup_Child(nodeChild);
            }
        }
    }

    /*
     *Load Tree Child
     **/
    private void loadTreeReportGroup_Child(TreeNode nodeParent) {
        S_Report_Group groupParent = (S_Report_Group) nodeParent.getData();
        List<S_Report_Group> lstChild
                = getIReportRemote().getLstReportGroupByParent(groupParent.getRptgroupid(), WorkingContext.getWorkingInfo().getUserid());

        if (lstChild != null) {
            for (S_Report_Group groupChild : lstChild) {
                TreeNode nodeChild = new DefaultTreeNode(groupChild, nodeParent);
                nodeChild.setExpanded(true);
                loadTreeReportGroup_Child(nodeChild);
            }
        }
    }

    private void loadTreeTableGroup_Child_PQ(TreeNode nodeParent, String surl) {
        S_Report_Group groupParent = (S_Report_Group) nodeParent.getData();
        List<S_Report_Group> lstChild
                = getIReportRemote().getListReportGroupChild(groupParent.getRptgroupid(), WorkingContext.getWorkingInfo().getUserid(), surl);

//        String url = null;
//        String[] argU = null;
        S_Report_Group sGroup = null;
        if (lstChild != null) {
            for (S_Report_Group groupChild : lstChild) {
                //surl = groupParent.getUrl();
                //argU = surl.split("#");
                //sGroup = (S_Report_Group) getIReportRemote().findById(argU[0], S_Report_Group.class);
                TreeNode nodeChild = new DefaultTreeNode(groupChild, nodeParent);
                nodeChild.setExpanded(true);
                loadTreeTableGroup_Child_PQ(nodeChild, surl);
            }
        }
    }

    public void onNodeSelect(NodeSelectEvent event) {
        reloadGridReportTable();
    }

    public void onCloseDetail(CloseEvent event) {
        if (getIReportConfigRemote() == null) {
            return;
        }
        String s = (String) WorkingContext.getSessionValueAndRemove(WorkingContext.KEY_REFRESH_MAIN);
        if (s != null && !s.isEmpty()) {
            WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, null);
            if (m_SelectedNode != null) {
                S_Report_Group tab_Group = (S_Report_Group) m_SelectedNode.getData();
                S_Report_Group tab_Group1
                        = (S_Report_Group) getIReportConfigRemote().findById(tab_Group.getRptgroupid(), S_Report_Group.class);
                if (tab_Group1 == null) {
                    m_SelectedNode = null;
                }
            }
            loadTreeTableGroup();
            reloadGridReportTable();
        }
    }

    private boolean checkSelectionTabGroup() {
        if (m_SelectedNode != null) {
            S_Report_Group tab_Group = (S_Report_Group) m_SelectedNode.getData();
            if (!tab_Group.getRptgroupid().isEmpty()) {
                return true;
            }
        }
        //Thong bao chon hang
        WorkingContext.showMessages(enumMessageMode.eWarn, null,
                m_RfTabProp.getMessage("msgNotChooseTabGroup"));
        return false;
    }

    public void onAddNewTabGroup(ActionEvent event) {
        try {
//            RequestContext context = RequestContext.getCurrentInstance();
//            context.addCallbackParam("Method", "AddTabGroup");
            
            PrimeFaces.current().ajax().addCallbackParam("Method", "AddTabGroup");
                    
            if (m_SelectedNode != null) {
                S_Report_Group tab_Group = (S_Report_Group) m_SelectedNode.getData();
                if (!tab_Group.getRptgroupid().isEmpty()) {
                    //context.addCallbackParam("id", tab_Group.getRptgroupid());
                    PrimeFaces.current().ajax().addCallbackParam("id", tab_Group.getRptgroupid());
                }
            } else {
                //context.addCallbackParam("Method", "NewGroup");
                PrimeFaces.current().ajax().addCallbackParam("Method", "NewGroup");
            }

        } catch (Exception ex) {
            showErrorFromException(ex, ReportConfigBean.class.getName() + ".onAddNewTabGroup()");
        }
    }

    public void onEditTabGroup(ActionEvent event) {
        try {
            if (checkSelectionTabGroup()) {
                //Lưu mảng duyệt
                TreeNode node = getM_RootNode();
                if (getSelectedNode() != null) {
                    node = getSelectedNode().getParent();
                }
                if (node != null) {
                    List<String> lstTabGroupID = new ArrayList<String>();
                    for (TreeNode tn : node.getChildren()) {
                        S_Report_Group tg = (S_Report_Group) tn.getData();
                        lstTabGroupID.add(tg.getRptgroupid());
                    }
                    WorkingContext.setSessionValue(WorkingContext.KEY_LSTID_FORNAV, lstTabGroupID);
                }

//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "EditTabGroup");
//                context.addCallbackParam("id", ((S_Report_Group) m_SelectedNode.getData()).getRptgroupid());
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "EditTabGroup");
                PrimeFaces.current().ajax().addCallbackParam("id", ((S_Report_Group) m_SelectedNode.getData()).getRptgroupid());
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportConfigBean.class.getName() + ".onEditTabGroup()");
        }
    }

    public void onDeleteTabGroupStart(ActionEvent event) {
        try {
            if (checkSelectionTabGroup()) {
                S_Report_Group tab_Group = (S_Report_Group) m_SelectedNode.getData();
                m_sDeleteMsg = m_RfTabProp.getMessage("msgComfirmDeleteTabGroup");
                m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", " ('" + tab_Group.getRptgroupdesc() + "')");
                m_eActionCurrent = enumAction.eGroupDelete;
//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "DeleteTabGroup");
//                context.addCallbackParam("id", tab_Group.getRptgroupid());
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "DeleteTabGroup");
                PrimeFaces.current().ajax().addCallbackParam("id", tab_Group.getRptgroupid());
                
            }
        } catch (Exception ex) {
            WorkingContext.showMessages(enumMessageMode.eError, null,
                    m_RfTabProp.getMessage("msgDeleteError"));
            //showErrorFromException(ex, ReportConfigBean.class.getName() + ".onDeleteTabGroupStart()");
        }
    }

    private void onDeleteTabGroupEnd(ActionEvent event) {
        try {
            if (checkSelectionTabGroup()) {
                S_Report_Group tab_Group = (S_Report_Group) m_SelectedNode.getData();
                String id = tab_Group.getRptgroupid();

                List<String> lst = getIReportConfigRemote().deleteReportGroup(id);
                if (lst != null) {
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    loadTreeTableGroup();
                    if (m_RootNode != null) {
                        if (m_RootNode.getChildCount() > 0) {
                            m_SelectedNode = m_RootNode.getChildren().get(0);
                        }
                    }
                    reloadGridReportTable();
                } else {
//                    showError(m_rfCommon.getMessage("msgDeleteError") + BaseConstant.CRLF()+ getIReportConfigRemote().getLastActionInfo(),
//                            Oper_MeterTabGroupDTBean.class.getName() + ".onDeleteTabGroupEnd()");
                    WorkingContext.showMessages(enumMessageMode.eError, null,
                            m_rfCommon.getMessage("msgDeleteError"));
                }
            }
        } catch (Exception ex) {
            WorkingContext.showMessages(enumMessageMode.eError, null,
                    m_rfCommon.getMessage("msgDeleteError"));
//            showErrorFromException(ex, ReportConfigBean.class.getName() + ".onDeleteTabGroupEnd()");
        }
    }

    public void onDeleteEnd(ActionEvent event) {
        switch (m_eActionCurrent) {
            case eGroupDelete:
                onDeleteTabGroupEnd(event);
                break;
            case eTabDelete:
                onDeleteTabEnd(event);
                break;
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Vùng grid">
    public ArrayList<Object> CorrectListKey(S_Report[] arrSelectReports) {
        ArrayList<Object> lstKey1 = new ArrayList<Object>();
        for (S_Report a : arrSelectReports) {
            lstKey1.add(a.getRptid());
        }
        WorkingContext.setSessionValue("LISTREPORTSELECT", lstKey1);
        return lstKey1;
    }

    private ResourcesFactory m_rfAsset;

    public void onOpenCopy(ActionEvent event) {
        WorkingContext.setSessionValue(WorkingContext.CHANGE_INFO_ASSET, "YES");
        ArrayList<Object> lstKey = (ArrayList<Object>) WorkingContext.getDialogObjSelect();

        if (arrSelectReports.length == 0) {
            WorkingContext.showMessages(enumMessageMode.eError, null, m_rfAsset.getMessage("msgNotSelectAsset"));
            return;
        }
        lstKey = CorrectListKey(arrSelectReports);
        //Trả về client để điều khiển dialog
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("Method", "CopyReport");
        PrimeFaces.current().ajax().addCallbackParam("Method", "CopyReport");
    }

    private void reloadGridReportTable() {
        m_LstReports = null;
        loadGridReportTable();
    }

    private void loadGridReportTable() {
        try {

            if (getIReportConfigRemote() == null) {
                return;
            }
            //Lấy nút chọn
            if (m_SelectedNode == null && (m_RootNode != null && m_RootNode.getChildCount() > 0)) {
                m_SelectedNode = m_RootNode.getChildren().get(0);
            }
            S_Report_Group tab_Group = (S_Report_Group) m_SelectedNode.getData();
            if (!tab_Group.getRptgroupid().isEmpty()) {
                //HaNV 03 03 2016
                //m_LstReports = getIReportRemote().getListReportByGroupID(tab_Group.getRptgroupid(), WorkingContext.getUserName());
                m_LstReports = getIReportRemote().getListReportByGroupID(tab_Group.getRptgroupid());
            }
            //DataTable dt = (DataTable) WorkingContext.findJSFComponent("formMain:dtMeterTab", false);
//                if (dt != null)
//                dt.resetPagination();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateKEY_LSTID_FORNAV() {
        //Set lại mảng duyệt
        List<String> lstCMID = new ArrayList<String>();
        if (m_LstReports != null) {
            for (S_Report ocm : m_LstReports) {
                lstCMID.add(ocm.getRptid());
            }
        }
        WorkingContext.setSessionValue(WorkingContext.KEY_LSTID_FORNAV, lstCMID);
    }

    private boolean checkSelectionTab() {
        if (arrSelectReports != null) {
            if (arrSelectReports.length > 0) {
                return true;
            }
        }
        //Thong bao chon hang
        WorkingContext.showMessages(enumMessageMode.eWarn, null,
                m_RfTabProp.getMessage("msgNotChooseTab"));
        return false;
    }

    public void onAddTab(ActionEvent event) {
        try {
            updateKEY_LSTID_FORNAV();
//            RequestContext context = RequestContext.getCurrentInstance();
//            context.addCallbackParam("Method", "AddTab");
            
            PrimeFaces.current().ajax().addCallbackParam("Method", "AddTab");
            String id = getRptgroupid();
            if (id != null) {
                //context.addCallbackParam("id", id);
                PrimeFaces.current().ajax().addCallbackParam("id", id);
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportConfigBean.class.getName() + ".onEditTab()");
        }
    }

    public void onEditTab(ActionEvent event) {
        try {
            if (checkSelectionTab()) {
                updateKEY_LSTID_FORNAV();
                String rptid = arrSelectReports[0].getRptid();
//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "EditTab");
//                context.addCallbackParam("id", rptid);
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "EditTab");
                PrimeFaces.current().ajax().addCallbackParam("id", rptid);

                List<String> lstID = new ArrayList<String>();
                //lstID.add(String.valueOf(0));
                for (int i = 0; i < arrSelectReports.length; i++) {
                    S_Report b = arrSelectReports[i];
                    if (b.getRptid().equals(rptid)) //Gán chỉ số đang chọn
                    {
                        lstID.add(0, rptid);
                    } else {
                        lstID.add(b.getRptid());
                    }
                }
                WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, lstID);
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportConfigBean.class.getName() + ".onEditTab()");
        }
    }

    public void onEditTabFromGrid(ActionEvent event) {
        try {
            updateKEY_LSTID_FORNAV();
        } catch (Exception ex) {
            showErrorFromException(ex, ReportConfigBean.class.getName() + ".onEditTab()");
        }
    }

    public void onDeleteTabStart(ActionEvent event) {
        try {
            if (checkSelectionTab()) {
                m_sDeleteMsg = m_RfTabProp.getMessage("msgComfirmDeleteTab");
                m_sDeleteMsg = m_sDeleteMsg.replaceAll("@count", String.valueOf(arrSelectReports.length));
                if (arrSelectReports.length == 1) {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", " ('" + arrSelectReports[0].getRptid() + "')");
                } else {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", "");
                }
                m_eActionCurrent = enumAction.eTabDelete;
//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "DeleteTab");
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "DeleteTab");
                
            }
        } catch (Exception ex) {
            WorkingContext.showMessages(enumMessageMode.eError, null,
                    m_rfCommon.getMessage("msgDeleteError"));
//            showErrorFromException(ex, ReportConfigBean.class.getName() + ".onDeleteTabStart()");
        }
    }

    private void onDeleteTabEnd(ActionEvent event) {
        try {
            if (checkSelectionTab()) {
                if (arrSelectReports != null) {
                    for (int i = 0; i < arrSelectReports.length; i++) {

                        if (!getIReportConfigRemote().delete(arrSelectReports[i].getRptid(), S_Report.class)) {
                            showError(getIReportConfigRemote().getLastActionInfo(), ReportConfigBean.class.getName() + ".onDeleteTabEnd()");
                            return;
                        }
                        getISystemCommonRemote().DeleteAttribute_GroupByObj(arrSelectReports[i].getRptid(), "RPT");

                    }

                    WorkingContext.showMessages(enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    arrSelectReports = null; //ThảoDD sửa: khởi tạo lại mảng chọn
                    reloadGridReportTable();
                }
            }
        } catch (Exception ex) {
            WorkingContext.showMessages(enumMessageMode.eError, null,
                    m_rfCommon.getMessage("msgDeleteError"));
//            showErrorFromException(ex, ReportConfigBean.class.getName() + ".onDeleteTabEnd()");
        }
    }

    public void onCloseDetailMeterTab(CloseEvent event) {
        if (getIReportConfigRemote() == null) {
            return;
        }
        String s = (String) WorkingContext.getSessionValueAndRemove(WorkingContext.KEY_REFRESH_MAIN);
        if (s != null && !s.isEmpty()) {
            WorkingContext.setSessionValue(WorkingContext.KEY_REFRESH_MAIN, null);
            reloadGridReportTable();
        }
        arrSelectReports = null;
    }

    public void onCopyReportTab(ActionEvent event) {
        try {
            if (checkSelectionTab()) {
                String msgCopyOf = m_rfCommon.getMessage("msgCopyOf");
                String msgCopyCompleteInfo = m_rfCommon.getMessage("msgCopyCompleteInfo");
                int count = 0;
                //Sinh key
                ISystemCommonRemote iscr = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
                S_Key_ControlInfo kcInit = new S_Key_ControlInfo(null, S_Report.class.getSimpleName());
                for (int i = 0; i < arrSelectReports.length; i++) {

                    S_Key_ControlInfo kc = iscr.getGenKeyID(kcInit);
                    if (kc == null || kc.getGenStatus() != 0) { //Không thành công
                        showErrorFromGenKey(kc, ReportConfigBean.class.getName() + ".onCopyReportTab()");
                        return;
                    }
                    //Sao ban ghi
                    S_Report source = arrSelectReports[i];
                    S_Report target = new S_Report(kc.getOutputValue(), msgCopyOf + " " + source.getRptdesc());
                    target.setRptgroupid(source.getRptgroupid());
                    target.setEnable(false);
                    target.setRptord(source.getRptord());
                    target.setUserCrId(WorkingContext.getUserName());
                    target.setUserCrDtime(new Date());

//                    Ghi CSDL
                    if (getIReportConfigRemote().copyReportTab(source.getRptid(), target)) {
                        count++;
                    } else {
                        showError(getIReportConfigRemote().getLastActionInfo(), ReportConfigBean.class.getName() + ".onCopyReportTab()");
                        return;
                    }
                }
                //Thong bao
                msgCopyCompleteInfo = msgCopyCompleteInfo.replaceAll("@count", String.valueOf(count));
                WorkingContext.showMessages(enumMessageMode.eInfo, null, msgCopyCompleteInfo);
                reloadGridReportTable();
            }
        } catch (Exception e) {
            showErrorFromException(e, ReportConfigBean.class.getName() + ".onCopyReportTab()");
        }
    }

    //}}</editor-fold>
    public void onAssetGrant(ActionEvent event) {
        if (m_SelectedNode != null) {
//            RequestContext context = RequestContext.getCurrentInstance();
//            context.addCallbackParam("Method", "Grant");
//            context.addCallbackParam("reportId", ((S_Report_Group) m_SelectedNode.getData()).getRptgroupid());
//            context.addCallbackParam("typeId", "G");

            PrimeFaces.current().ajax().addCallbackParam("Method", "Grant");
            PrimeFaces.current().ajax().addCallbackParam("reportId", ((S_Report_Group) m_SelectedNode.getData()).getRptgroupid());
            PrimeFaces.current().ajax().addCallbackParam("typeId", "G");

        } else {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null,
                    "Bạn chưa chọn thiết bị/công trình");
        }

    }

    public void onAssetGrantCM(ActionEvent event) {
        ArrayList<Object> lstKey = (ArrayList<Object>) WorkingContext.getDialogObjSelect();

        if (arrSelectReports.length == 0) {
            WorkingContext.showMessages(enumMessageMode.eError, null, m_rfAsset.getMessage("msgNotSelectAsset"));
            return;
        }

        if (arrSelectReports.length > 1) {
            WorkingContext.showMessages(enumMessageMode.eError, null, "Bạn chỉ được chọn 1 báo cáo");
            return;
        }

        lstKey = CorrectListKey(arrSelectReports);

//        if (lstKey == null) {
//            WorkingContext.showMessages(enumMessageMode.eError, null, "Bạn chưa chọn thiết bị, công trình");
//            return;
//        } else if (lstKey.size() > 1 || lstKey.size() == 0) {
//            WorkingContext.showMessages(enumMessageMode.eError, null, "Bạn phải chọn 1 thiết bị/công trình");
//            return;
//        }
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("Method", "Grant");
//        context.addCallbackParam("reportId", lstKey.get(0).toString());
//        context.addCallbackParam("typeId", "A");
        PrimeFaces.current().ajax().addCallbackParam("Method", "Grant");
        PrimeFaces.current().ajax().addCallbackParam("reportId", lstKey.get(0).toString());
        PrimeFaces.current().ajax().addCallbackParam("typeId", "A");

    }

    //{{<editor-fold defaultstate="collapsed" desc="Properties">
    public IReportConfigRemote getIReportConfigRemote() {
        try {
            if (m_IReportConfigRemote == null) {
                m_IReportConfigRemote = (IReportConfigRemote) EjbContext.getLocalEJBRemote(IReportConfigRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();
            }
        } catch (Exception e) {
            return null;
        }
        return m_IReportConfigRemote;
    }

    public IReportGrantRemote getIReportRemote() {
        try {
            if (iReport == null) {
                iReport = (IReportGrantRemote) EjbContext.getLocalEJBRemote(IReportGrantRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login lại khi gọi ejb
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportConfigBean.class.getName() + ".getIReportRemote()");
            return null;
        }
        return iReport;
    }

    public ISystemCommonRemote getISystemCommonRemote() {
        try {
            if (m_ISystemCommonRemote == null) {
                m_ISystemCommonRemote = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();
            }
        } catch (Exception e) {
            return null;
        }
        return m_ISystemCommonRemote;
    }

    public String getM_sDeleteMsg() {
        return m_sDeleteMsg;
    }

    public void setM_sDeleteMsg(String m_sDeleteMsg) {
        this.m_sDeleteMsg = m_sDeleteMsg;
    }

    public TreeNode getM_RootNode() {
        return m_RootNode;
    }

    public void setM_RootNode(TreeNode m_RootNode) {
        this.m_RootNode = m_RootNode;
    }

    public TreeNode getSelectedNode() {
        return m_SelectedNode;
    }

    public void setSelectedNode(TreeNode m_SelectedNode) {
        this.m_SelectedNode = m_SelectedNode;
    }

    public S_Report[] getSelectReports() {
        return arrSelectReports;
    }

    public void setSelectReports(S_Report[] arrSelectReports) {
        this.arrSelectReports = arrSelectReports;
    }

    public List<S_Report> getDsReportsForGrid() {
        return m_LstReports;
    }

    public void setDsReportsForGrids(List<S_Report> m_LstReports) {
        this.m_LstReports = m_LstReports;
    }

    public boolean isbCheckAll() {
        return bCheckAll;
    }

    public void setbCheckAll(boolean bCheckAll) {
        this.bCheckAll = bCheckAll;
    }

    public String getDetailHeaderGrd() {
        m_DetailHeaderGrd = null;
        if (m_SelectedNode != null) {
            S_Report_Group group = (S_Report_Group) m_SelectedNode.getData();
            if (!group.getRptgroupid().isEmpty()) {
                m_DetailHeaderGrd = group.getRptgroupdesc();
            }
        }
        return m_DetailHeaderGrd;
    }

    public int getRowCount() {
        m_RowCount = 0;
        if (m_LstReports != null) {
            m_RowCount = m_LstReports.size();
        }
        return m_RowCount;
    }

    public String getRptgroupid() {
        if (m_SelectedNode != null) {
            S_Report_Group tab_Group = (S_Report_Group) m_SelectedNode.getData();
            if (!tab_Group.getRptgroupid().isEmpty()) {
                return tab_Group.getRptgroupid();
            }
        }
        return null;
    }
    //}}</editor-fold>
//{{<editor-fold defaultstate="collapsed" desc="Thay doi STT">
    private int m_AttRowCount = 0;

    public int getAttRowCount() {
        m_AttRowCount = 0;
        if (m_LstReports != null) {
            m_AttRowCount = m_LstReports.size();
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
            if (m_SelectedNode == null && (m_RootNode != null && m_RootNode.getChildCount() > 0)) {
                m_SelectedNode = m_RootNode.getChildren().get(0);
            }
            if (arrSelectReports != null) {
                if (arrSelectReports.length == 1) {
                    getIReportConfigRemote().OrderAssetOnGroup(arrSelectReports[0].getRptid(), arrSelectReports[0].getRptgroupid(), action);
                    loadGridReportTable();
                    WorkingContext.showMessages(enumMessageMode.eInfo, null,
                            m_rfCommon.getMessage("msgMoveRecordSuccess"));
                } else if (arrSelectReports.length > 1) {
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
            showErrorFromException(ex, ReportConfigBean.class.getName() + ".MoveAttribute()");
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
    public ReportDataModel getReportDataModel() {
        if (m_LstReports != null) {
            return new ReportDataModel(m_LstReports);
        }
        return null;
    }
}

class ReportDataModel extends ListDataModel<S_Report> implements SelectableDataModel<S_Report> {

    public ReportDataModel() {
    }

    public ReportDataModel(List<S_Report> data) {
        super(data);
    }

    @Override
    public String getRowKey(S_Report t) {
        return Tools.fStandardPdataTableID(t.getRptid());
    }

    @Override
    public S_Report getRowData(String rowKey) {
        List<S_Report> list = (List<S_Report>) getWrappedData();

        for (S_Report lst : list) {
            if (Tools.fStandardPdataTableID(lst.getRptid()).equals(rowKey)) {
                return lst;
            }
        }
        return null;
    }
}
