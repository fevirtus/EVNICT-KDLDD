package main.web.shared.report.bean;

import main.ContextResources.EjbContext;
import main.remote.shared.report.IReportConfigRemote;
import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant.enumMessageMode;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import main.entity.shared.report.S_Report;
import main.entity.shared.report.S_Report_Group;
import main.remote.shared.report.IReportGrantRemote;
import org.primefaces.component.tree.Tree;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author hanv
 */
@ManagedBean
@ViewScoped
public class ReportMBean extends BasePageCommon implements Serializable {

    private MenuModel m_RootNode;
    private DefaultSubMenu m_SelectedNode;
    private int m_RowCount = 0;
    private String m_DetailHeaderGrd;
    private S_Report[] arrSelectReports;
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
    public ReportMBean() {
        if (m_RfTabProp == null) {
            m_RfTabProp = new ResourcesFactory("main/web/shared/report/prop/ReportProp");
        }
        if (!isPostback()) {
            loadTreeTableGroup();
            if (m_RootNode != null) {
                if (m_RootNode.getElements().size() > 0) {
                    Tree tree = (Tree) WorkingContext.findJSFComponent("formMain:trvMeterGroup", false);
                    if (tree != null) {
                        tree.setSelection(m_SelectedNode);
                    }
                    reloadGridReportTable(null);
                }
            }
        }
    }

    private void loadTreeTableGroup() {
        m_RootNode = new DefaultMenuModel();
        if (getIReportConfigRemote() == null) {
            return;
        }
        //Load cha truong hop
        List<S_Report_Group> lstParent = null;
        List<S_Report_Group> lstAllPrivilege = null;
        if (WorkingContext.getWorkingInfo().getUserid().toLowerCase().equals("admin")) {
            lstParent = getIReportConfigRemote().getListReportGroupByOrgID(WorkingContext.getOrganizationCurrent());
            if (lstParent != null) {
                for (S_Report_Group groupParent : lstParent) {
                    DefaultSubMenu nodeParent = new DefaultSubMenu();
                    //edit by thinhnd
                    nodeParent.setLabel(groupParent.getRptgroupdesc());
                    nodeParent.setId(groupParent.getRptgroupid());
                    //Need edit
                    m_RootNode.getElements().add(nodeParent);
                    //m_RootNode.addElement(nodeParent);
                    loadTreeTableGroup_Child(nodeParent);
                }
            }
        } else {
            lstParent
                    = getIReportRemote().getLstReportGroupByUser(WorkingContext.getWorkingInfo().getUserid());

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
                        DefaultSubMenu nodeParent = new DefaultSubMenu();
                        nodeParent.setLabel(sGroup.getRptgroupdesc());
                        nodeParent.setId(sGroup.getRptgroupid());
                        sLeaf = sGroup;
                        //need edit
                        //m_RootNode.addElement(nodeParent);
                        m_RootNode.getElements().add(nodeParent);
                        loadTreeTableGroup_Child_PQ(nodeParent, lstUrls);
                    }
                }
            }
        }
    }

    private void loadTreeTableGroup_Child_PQ(DefaultSubMenu nodeParent, String surl) {
        List<S_Report_Group> lstChild
                = getIReportRemote().getListReportGroupChild(nodeParent.getId(), WorkingContext.getWorkingInfo().getUserid(), surl);
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
                    DefaultMenuItem nodeChild = new DefaultMenuItem().builder().title(groupChild.getRptgroupdesc()).build();
                    nodeChild.setId(groupChild.getRptgroupid());
                    nodeChild.setAjax(true);
                    nodeChild.setUpdate("dtReportTab");
                    nodeChild.setOncomplete("PF('dlgReportSelect').show();");
                    nodeChild.setCommand("#{reportMBean.onNodeSelect}");
                    nodeChild.setParam("rptGroupid", groupChild.getRptgroupid());
                    nodeParent.getElements().add(nodeChild);
                    sLeaf = groupChild;
                    //loadTreeTableGroup_Child_PQ(nodeChild, surl);
                }

            }
        }
    }

    /**
     * Build child
     *
     * @param nodeParent
     */
    private void loadTreeTableGroup_Child(DefaultSubMenu nodeParent) {
        List<S_Report_Group> lstChild
                = getIReportConfigRemote().getListReportGroupChildView(nodeParent.getId());
        if (lstChild != null) {
            for (S_Report_Group groupChild : lstChild) {
                DefaultMenuItem nodeChild = new DefaultMenuItem().builder().title(groupChild.getRptgroupdesc()).build();
                nodeChild.setId(groupChild.getRptgroupid());
                nodeChild.setAjax(true);
                nodeChild.setUpdate("dtReportTab");
                nodeChild.setOncomplete("PF('dlgReportSelect').show();");
                nodeChild.setCommand("#{reportMBean.onNodeSelect}");
                nodeChild.setParam("rptGroupid", groupChild.getRptgroupid());
                nodeParent.getElements().add(nodeChild);
                //loadTreeTableGroup_Child(nodeChild);
            }
        }
    }

    public void onNodeSelect(MenuActionEvent event) {
        MenuItem menuItem = event.getMenuItem();
        String id = menuItem.getParams().get("rptGroupid").get(0);
        reloadGridReportTable(id);
    }

    private boolean checkSelectionTabGroup() {
        if (m_SelectedNode != null) {
            if (!m_SelectedNode.getId().isEmpty()) {
                return true;
            }
        }
        //Thong bao chon hang
        WorkingContext.showMessages(enumMessageMode.eWarn, null,
                m_RfTabProp.getMessage("msgNotChooseTabGroup"));
        return false;
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
    private void reloadGridReportTable(String id) {
        m_LstReports = null;
        loadGridReportTable(id);
    }

    private void loadGridReportTable(String id) {
        try {

            if (getIReportConfigRemote() == null) {
                return;
            }
            if (id != null && !id.isEmpty()) {
                m_LstReports = getIReportRemote().getListReportByGroupIDView(id, WorkingContext.getWorkingInfo().getUserid());

            }
        } catch (Exception ex) {
            ex.printStackTrace();
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

    public MenuModel getM_RootNode() {
        return m_RootNode;
    }

    public void setM_RootNode(MenuModel m_RootNode) {
        this.m_RootNode = m_RootNode;
    }

    public DefaultSubMenu getSelectedNode() {
        return m_SelectedNode;
    }

    public void setSelectedNode(DefaultSubMenu m_SelectedNode) {
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

    public String getDetailHeaderGrd() {
        m_DetailHeaderGrd = null;
        if (m_SelectedNode != null) {
            if (!m_SelectedNode.getId().isEmpty()) {
                m_DetailHeaderGrd = m_SelectedNode.getId();
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
            if (!m_SelectedNode.getId().isEmpty()) {
                return m_SelectedNode.getId();
            }
        }
        return null;
    }
    //}}</editor-fold>

}
