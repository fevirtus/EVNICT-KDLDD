package main.web.shared.report.bean;

import main.ContextResources.EjbContext;


import main.remote.shared.report.IReportConfigRemote;
import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant.enumMessageMode;
import evnit.util.common.Tools;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;
import main.entity.shared.report.S_Report;
import main.entity.shared.report.S_Report_Group;
import main.remote.shared.report.IReportGrantRemote;
import org.primefaces.PrimeFaces;
import org.primefaces.component.tree.Tree;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.TreeNode;

/**
 *
 * @author hanv
 */
@ManagedBean
@ViewScoped
public class ReportBean extends BasePageCommon implements Serializable {

    private TreeNode m_RootNode;
    private TreeNode m_SelectedNode;
    private int m_RowCount = 0;
    private String m_DetailHeaderGrd;
    private S_Report arrSelectReports;
    private List<S_Report> m_LstReports;
    private IReportConfigRemote m_IReportConfigRemote;
    private String m_sDeleteMsg;
    private ResourcesFactory m_RfTabProp;
    private ISystemCommonRemote m_ISystemCommonRemote;
    private IReportGrantRemote iReport;

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
    public ReportBean() {
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

    public void onRowSelect(SelectEvent evt) {
        if (arrSelectReports != null) {
//            RequestContext context = RequestContext.getCurrentInstance();
//            context.addCallbackParam("Method", "ReportView");
//            context.addCallbackParam("rId", arrSelectReports.getRptid());
            
            PrimeFaces.current().ajax().addCallbackParam("Method", "ReportView");
            PrimeFaces.current().ajax().addCallbackParam("rId", arrSelectReports.getRptid());
        }
    }

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
            lstParent = getIReportRemote().getLstReportGroupByUser(WorkingContext.getWorkingInfo().getUserid());
            //Load toàn bộ tập quyền của user//
            lstAllPrivilege = getIReportRemote().getListReportGroupByOrgID(WorkingContext.getWorkingInfo().getOrgid(), WorkingContext.getWorkingInfo().getUserid());
            String lstUrls = "";

            if (lstParent != null) {
                for (S_Report_Group groupParent : lstParent) {
                    if (lstUrls.equals("")) {
                        lstUrls = lstUrls + groupParent.getUrl();
                    } else {
                        lstUrls = lstUrls + groupParent.getRptgroupid() + "#";
                    }
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

    private void loadTreeTableGroup_Child_PQ(TreeNode nodeParent, String surl) {
        S_Report_Group groupParent = (S_Report_Group) nodeParent.getData();
        List<S_Report_Group> lstChild
                = getIReportRemote().getListReportGroupChild(groupParent.getRptgroupid(), WorkingContext.getWorkingInfo().getUserid(), surl);
        S_Report_Group sLeaf = new S_Report_Group();
//        String url = null;
//        String[] argU = null;
        S_Report_Group sGroup = null;
        if (lstChild != null) {
            for (S_Report_Group groupChild : lstChild) {
                if (!groupChild.equals(sLeaf)) {
                    //surl = groupParent.getUrl();
                    //argU = surl.split("#");
                    //sGroup = (S_Report_Group) getIReportRemote().findById(argU[0], S_Report_Group.class);
                    TreeNode nodeChild = new DefaultTreeNode(groupChild, nodeParent);
                    nodeChild.setExpanded(true);
                    sLeaf = groupChild;
                    loadTreeTableGroup_Child_PQ(nodeChild, surl);
                }
            }
        }
    }

    /**
     * Build child
     *
     * @param nodeParent
     */
    private void loadTreeTableGroup_Child(TreeNode nodeParent) {
        S_Report_Group groupParent = (S_Report_Group) nodeParent.getData();
        List<S_Report_Group> lstChild
                = getIReportConfigRemote().getListReportGroupChildView(groupParent.getRptgroupid());
        if (lstChild != null) {
            for (S_Report_Group groupChild : lstChild) {
                TreeNode nodeChild = new DefaultTreeNode(groupChild, nodeParent);
                nodeChild.setExpanded(true);
                loadTreeTableGroup_Child(nodeChild);
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
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.addCallbackParam("Method", "AddTabGroup");
            
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
            showErrorFromException(ex, ReportBean.class.getName() + ".onAddNewTabGroup()");
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
            showErrorFromException(ex, ReportBean.class.getName() + ".onEditTabGroup()");
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

    public IReportGrantRemote getIReportRemote() {
        try {
            if (iReport == null) {
                iReport = (IReportGrantRemote) EjbContext.getLocalEJBRemote(IReportGrantRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login lại khi gọi ejb
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportGrantBean.class.getName() + ".getIReportRemote()");
            return null;
        }
        return iReport;
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Vùng grid">
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
                m_LstReports = getIReportRemote().getListReportByGroupIDView(tab_Group.getRptgroupid(), WorkingContext.getWorkingInfo().getUserid());

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

    public void onEditTabFromGrid(ActionEvent event) {
        try {
            updateKEY_LSTID_FORNAV();
        } catch (Exception ex) {
            showErrorFromException(ex, ReportBean.class.getName() + ".onEditTab()");
        }
    }

    //}}</editor-fold>
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

    public S_Report getSelectReports() {
        return arrSelectReports;
    }

    public void setSelectReports(S_Report arrSelectReports) {
        this.arrSelectReports = arrSelectReports;
    }

    public List<S_Report> getDsReportsForGrid() {
        return m_LstReports;
    }

    public void setDsReportsForGrids(List<S_Report> m_LstReports) {
        this.m_LstReports = m_LstReports;
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

    public ReportDataModelView getReportDataModel() {
        if (m_LstReports != null) {
            return new ReportDataModelView(m_LstReports);
        }
        return null;
    }
}

class ReportDataModelView extends ListDataModel<S_Report> implements SelectableDataModel<S_Report> {

    public ReportDataModelView() {
    }

    public ReportDataModelView(List<S_Report> data) {
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
