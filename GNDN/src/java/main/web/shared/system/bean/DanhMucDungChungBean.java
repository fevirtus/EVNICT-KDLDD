/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.system.SListAll;
import main.entity.shared.system.SListFieldAll;
import main.entity.shared.system.SListGroupAll;
import main.web.common.bean.BasePageCommon;
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
import main.remote.shared.system.IDanhMucDungChungRemote;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class DanhMucDungChungBean extends BasePageCommon implements Serializable {

    private SListAll g_SListALL;

    private String m_sDeleteMsg;

    private TreeNode rootTreeNode;
    private TreeNode selectedTreeNode;
    private List<SListAll> m_SListALL;
    private List<SListFieldAll> filterValue;
    

    private List<SListGroupAll> m_SListGroupAll;

    IDanhMucDungChungRemote m_IDanhMucDungChungRemote;

    private List<SListFieldAll> m_SListFieldALL;
    //bien luu khi chon luoi
    private SListFieldAll[] SelectionSListFieldALL;

    private boolean m_bInited = false;
    
    String m_listDesc="";
    String m_filter="";
    
    public DanhMucDungChungBean() {
        if (!m_bInited) {
            if (getIDanhMucDungChungRemote() == null) {
                return;
            }
            m_SListALL = m_IDanhMucDungChungRemote.SelectListALL();
            m_SListGroupAll = m_IDanhMucDungChungRemote.SelectListGroupALL();
            if (m_SListALL != null) {
                LoadTree(m_SListALL, m_SListGroupAll);
            }
        }
        m_bInited = true;

    }
    
    public void onFilter(ActionEvent event)
    {
        
    }

    public void onAddNewGroupInTree(ActionEvent event) {
        try {
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.addCallbackParam("Method", "AddNewGroupInTree");
            
            PrimeFaces.current().ajax().addCallbackParam("Method", "AddNewGroupInTree");
            
            if (selectedTreeNode.getType().equals("GroupList")) {
                SListGroupAll temp = (SListGroupAll) selectedTreeNode.getData();
//                context.addCallbackParam("Status", true);
//                context.addCallbackParam("parentId", temp.getGrouplistid());
                
                PrimeFaces.current().ajax().addCallbackParam("Status", true);
                PrimeFaces.current().ajax().addCallbackParam("parentId", temp.getGrouplistid());
            } else {
                //context.addCallbackParam("Status", false);
                PrimeFaces.current().ajax().addCallbackParam("Status", false);
            }

        } catch (Exception ex) {

        }
    }
    public void onAddNewInTree(ActionEvent event) {
        try {
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.addCallbackParam("Method", "AddNewInTree");
            
            PrimeFaces.current().ajax().addCallbackParam("Method", "AddNewInTree");
            
            if (selectedTreeNode.getType().equals("GroupList")) {
                SListGroupAll temp = (SListGroupAll) selectedTreeNode.getData();
                //context.addCallbackParam("Status", true);
                //context.addCallbackParam("parentId", temp.getGrouplistid());
                
                PrimeFaces.current().ajax().addCallbackParam("Status", true);
                PrimeFaces.current().ajax().addCallbackParam("parentId", temp.getGrouplistid());
            } else {
                //context.addCallbackParam("Status", false);
                PrimeFaces.current().ajax().addCallbackParam("Status", false);
            }

        } catch (Exception ex) {

        }
    }

    public void onEditInTree(ActionEvent event) {
        try {
            //RequestContext context = RequestContext.getCurrentInstance();
            //context.addCallbackParam("Method", "EditGroupInTree");
            
            PrimeFaces.current().ajax().addCallbackParam("Method", "EditGroupInTree");
            
            
            if (selectedTreeNode.getType().equals("GroupList")) {
                SListGroupAll temp = (SListGroupAll) selectedTreeNode.getData();
//                context.addCallbackParam("type", selectedTreeNode.getType());
//                context.addCallbackParam("id", temp.getGrouplistid());
//                context.addCallbackParam("parentId", temp.getGrouplistid());
                
                PrimeFaces.current().ajax().addCallbackParam("type", selectedTreeNode.getType());
                PrimeFaces.current().ajax().addCallbackParam("id", temp.getGrouplistid());
                PrimeFaces.current().ajax().addCallbackParam("parentId", temp.getGrouplistid());
            } else {
                SListAll temp = (SListAll) selectedTreeNode.getData();
//                context.addCallbackParam("type", selectedTreeNode.getType());
//                context.addCallbackParam("id", temp.getListid());
//                context.addCallbackParam("parentId", temp.getGrouplistid());
                
                PrimeFaces.current().ajax().addCallbackParam("type", selectedTreeNode.getType());
                PrimeFaces.current().ajax().addCallbackParam("id", temp.getListid());
                PrimeFaces.current().ajax().addCallbackParam("parentId", temp.getGrouplistid());
            }

        } catch (Exception ex) {

        }
    }

    //{{<editor-fold defaultstate="collapsed" desc="Hàm">
    public IDanhMucDungChungRemote getIDanhMucDungChungRemote() {
        try {
            if (m_IDanhMucDungChungRemote == null) {
                m_IDanhMucDungChungRemote = (IDanhMucDungChungRemote) EjbContext.getLocalEJBRemote(IDanhMucDungChungRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login lại khi gọi ejb
            }

        } catch (Exception e) {
            return null;
        }
        return m_IDanhMucDungChungRemote;
    }

    public void LoadTree(List<SListAll> m_SListALL, List<SListGroupAll> m_SListGroupALL) {
        rootTreeNode = new DefaultTreeNode("root", null);
        TreeNode node0 = null;
        TreeNode node1 = null;
        SListAll temp = null;
        String m_GroupID = "";
        for (int i = 0; i < m_SListGroupALL.size(); i++) {
            if (m_SListGroupALL.get(i).getGrouplistParent() == null || m_SListGroupALL.get(i).getGrouplistParent().equals("")) {
                node0 = new DefaultTreeNode("GroupList", m_SListGroupALL.get(i), rootTreeNode);
            }
        }
        //        if (m_SListALL.size() > 0) {
        //            for (int i = 0; i < m_SListALL.size(); i++) {
        //                node0 = new DefaultTreeNode(m_SListALL.get(i), rootTreeNode);
        //            }
        //        }
    }

    public void displaySelectedSingle(NodeSelectEvent event) {
        if (selectedTreeNode != null & selectedTreeNode.getType().equals("GroupList")) {
            selectedTreeNode.setExpanded(true);
            SListGroupAll node = (SListGroupAll) selectedTreeNode.getData();
            TreeNode node0 = null;
            selectedTreeNode.getChildren().clear();
            for (int i = 0; i < m_SListGroupAll.size(); i++) {
                if (m_SListGroupAll.get(i).getGrouplistParent() != null && m_SListGroupAll.get(i).getGrouplistParent().getGrouplistid().equals(node.getGrouplistid())) {
                    node0 = new DefaultTreeNode("GroupList", m_SListGroupAll.get(i), selectedTreeNode);
                    
                    //node0.setExpanded(true);

                }
            }
            for (int i = 0; i < m_SListALL.size(); i++) {
                if (m_SListALL.get(i).getGrouplistid() != null & m_SListALL.get(i).getGrouplistid().getGrouplistid().equals(node.getGrouplistid())) {
                    node0 = new DefaultTreeNode("List", m_SListALL.get(i), selectedTreeNode);
                    //node0.setExpanded(true);

                }
            }

        }
        if (selectedTreeNode != null & selectedTreeNode.getType().equals("List")) {
            SListAll node = (SListAll) selectedTreeNode.getData();
            String strListID = node.getListid();
            g_SListALL = new SListAll();
            g_SListALL.setListid(strListID);
            g_SListALL.setListdesc(node.getListdesc());
            m_listDesc = node.getListdesc();
            LoadDataGrid(strListID);
        }
    }

    public void LoadDataGrid(String strListID) {
        m_SListFieldALL=null;
        m_SListFieldALL = m_IDanhMucDungChungRemote.SelectListFieldALL(strListID);
    }
    //}}</editor-fold>
    //{{<editor-fold defaultstate="collapsed" desc="Sự kiện">

    public void onCloseAssetCategoryDetail(CloseEvent event) {
        LoadDataGrid(g_SListALL.getListid());
    }

    public void onCloseListGroup(CloseEvent event) {
        
        m_SListALL = m_IDanhMucDungChungRemote.SelectListALL();
        m_SListGroupAll = m_IDanhMucDungChungRemote.SelectListGroupALL();
        if (selectedTreeNode.getType().equals("GroupList")) {
            SListGroupAll temp = (SListGroupAll) selectedTreeNode.getData();
            
            TreeNode tempNode = selectedTreeNode;
            SListGroupAll temp1 = (SListGroupAll) getIDanhMucDungChungRemote().findById(temp.getGrouplistid(), SListGroupAll.class);
            if (temp1 != null) {
                if (temp1.getGrouplistParent().equals(temp.getGrouplistParent())) {
                    ((SListGroupAll) selectedTreeNode.getData()).setGrouplistid(temp1.getGrouplistid());
                    ((SListGroupAll) selectedTreeNode.getData()).setGrouplistdesc(temp1.getGrouplistdesc());
                } else {
                    selectedTreeNode = selectedTreeNode.getParent();
                    selectedTreeNode.getChildren().remove(tempNode);
                }

            } else {
                selectedTreeNode = selectedTreeNode.getParent();
                selectedTreeNode.getChildren().remove(tempNode);

            }
        }
        if (selectedTreeNode.getType().equals("List")) {
            SListAll temp = (SListAll) selectedTreeNode.getData();
            TreeNode tempNode = selectedTreeNode;
            SListAll temp1 = (SListAll) getIDanhMucDungChungRemote().findById(temp.getListid(),SListAll.class);
            if (temp1 != null) {
                if (temp1.getGrouplistid().equals(temp.getGrouplistid())) {
                    ((SListAll) selectedTreeNode.getData()).setListid(temp1.getListid());
                    ((SListAll) selectedTreeNode.getData()).setListdesc(temp1.getListdesc());
                } else {
                    selectedTreeNode = selectedTreeNode.getParent();
                    selectedTreeNode.getChildren().remove(tempNode);
                }

            } else {
                selectedTreeNode = selectedTreeNode.getParent();
                selectedTreeNode.getChildren().remove(tempNode);

            }
        }
        displaySelectedSingle(null);
    }

    public void editSListFieldALL(ActionEvent event) {
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("Method", "Edit");
//        context.addCallbackParam("listid", SelectionSListFieldALL[0].getListid());
//        context.addCallbackParam("fieldid", SelectionSListFieldALL[0].getFieldid());
        
        PrimeFaces.current().ajax().addCallbackParam("Method", "Edit");
        PrimeFaces.current().ajax().addCallbackParam("listid", SelectionSListFieldALL[0].getListid());
        PrimeFaces.current().ajax().addCallbackParam("fieldid", SelectionSListFieldALL[0].getFieldid());

        List<String> lstFieldIDSelected = new ArrayList<String>();
        for (SListFieldAll o : SelectionSListFieldALL) {
            lstFieldIDSelected.add(o.getFieldid());
        }

        WorkingContext.setSessionValue("S_LIST_FIELD_ALL", lstFieldIDSelected);
    }

    public void addNewSListFieldALL(ActionEvent event) {

        if (g_SListALL == null || selectedTreeNode==null || selectedTreeNode.getType().equals("GroupList")) {
            m_sDeleteMsg = "Bạn chưa chọn danh mục";
            return;
        }
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("Method", "AddNew");
//        context.addCallbackParam("listid", g_SListALL.getListid());
        
        PrimeFaces.current().ajax().addCallbackParam("Method", "AddNew");
        PrimeFaces.current().ajax().addCallbackParam("listid", g_SListALL.getListid());

    }

    private boolean checkSelection() {
        if (SelectionSListFieldALL != null) {
            if (SelectionSListFieldALL.length > 0) {
                return true;
                //Thong bao chon hang
            }
        }
        WorkingContext.showMessages(enumMessageMode.eInfo, null,
                m_rfCommon.getMessage("msgNotCheckRecord"));
        return false;
    }

    public void deleteSListFieldALLStart(ActionEvent event) {
        try {
            if (checkSelection()) {
                m_sDeleteMsg = m_rfCommon.getMessage("msgDlgDeleteInfo");
                m_sDeleteMsg = m_sDeleteMsg.replaceAll("@count", String.valueOf(SelectionSListFieldALL.length));
                if (SelectionSListFieldALL.length == 1) {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", " ('" + SelectionSListFieldALL[0].getFieldcode() + "')");
                } else {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", "");
                }

//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "Delete");
//                context.addCallbackParam("id", SelectionSListFieldALL[0].getFieldid());
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "Delete");
                PrimeFaces.current().ajax().addCallbackParam("id", SelectionSListFieldALL[0].getFieldid());
            }
        } catch (Exception ex) {
            showErrorFromException(ex, SListFieldAll.class.getName() + ".onDeleteAssetRouteStart()");
        }
    }

    public void deleteSListFieldALLEnd(ActionEvent event) {
        try {
            String g_ListID = "";
            if (getIDanhMucDungChungRemote() != null) {
                if (SelectionSListFieldALL != null) {
                    for (int i = 0; i < SelectionSListFieldALL.length; i++) {
                        g_ListID = SelectionSListFieldALL[i].getListid();
                        if (!m_IDanhMucDungChungRemote.DeleteSListFieldALL(SelectionSListFieldALL[i].getFieldid())) {
                            showError(m_IDanhMucDungChungRemote.getLastActionInfo(), SListFieldAll.class.getName() + ".deleteSListFieldALLEnd()");
                            return;
                        }
                    }
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    LoadDataGrid(g_ListID);
                }
            }
        } catch (Exception e) {
            showErrorFromException(e, SListFieldAll.class.getName() + ".deleteSListFieldALLEnd()");
        }
    }

    //}}</editor-fold>
    //{{<editor-fold defaultstate="collapsed" desc="Get Set">
    
    public List<SListFieldAll> getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(List<SListFieldAll> filterValue) {
        this.filterValue = filterValue;
    }

    public String getM_filter() {
        return m_filter;
    }

    public void setM_filter(String m_filter) {
        this.m_filter = m_filter;
    }
    
    
    public String getM_listDesc() {
        return m_listDesc;
    }

    public void setM_listDesc(String m_listDesc) {
        this.m_listDesc = m_listDesc;
    }
    
    
    public String getM_sDeleteMsg() {
        return m_sDeleteMsg;
    }

    public void setM_sDeleteMsg(String m_sDeleteMsg) {
        this.m_sDeleteMsg = m_sDeleteMsg;
    }

    public TreeNode getRootTreeNode() {
        return rootTreeNode;
    }

    public void setRootTreeNode(TreeNode rootTreeNode) {
        this.rootTreeNode = rootTreeNode;
    }

    public TreeNode getSelectedTreeNode() {
        return selectedTreeNode;
    }

    public void setSelectedTreeNode(TreeNode selectedTreeNode) {
        this.selectedTreeNode = selectedTreeNode;
    }

    public List<SListAll> getM_SListALL() {
        return m_SListALL;
    }

    public void setM_SListALL(List<SListAll> m_SListALL) {
        this.m_SListALL = m_SListALL;
    }

    public List<SListFieldAll> getM_SListFieldALL() {
        return m_SListFieldALL;
    }

    public void setM_SListFieldALL(List<SListFieldAll> m_SListFieldALL) {
        this.m_SListFieldALL = m_SListFieldALL;
    }

    public SListFieldAll[] getSelectionSListFieldALL() {
        return SelectionSListFieldALL;
    }

    public void setSelectionSListFieldALL(SListFieldAll[] SelectionSListFieldALL) {
        this.SelectionSListFieldALL = SelectionSListFieldALL;
    }

    public SListAll getG_SListALL() {
        return g_SListALL;
    }

    public void setG_SListALL(SListAll g_SListALL) {
        this.g_SListALL = g_SListALL;
    }

    //}}</editor-fold>
    public ListDMDungChungDataModel getTableDataModel() {
        return new ListDMDungChungDataModel(m_SListFieldALL);
    }

}
//{{<editor-fold defaultstate="collapsed" desc="Class hien thi luoi">

class ListDMDungChungDataModel extends ListDataModel<SListFieldAll> implements SelectableDataModel<SListFieldAll> {

    public ListDMDungChungDataModel() {
    }

    public ListDMDungChungDataModel(List<SListFieldAll> data) {
        super(data);
    }

    @Override
    public String getRowKey(SListFieldAll t) {
        return Tools.fStandardPdataTableID(t.getFieldid());
    }

    @Override
    public SListFieldAll getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

        List<SListFieldAll> list = (List<SListFieldAll>) getWrappedData();

        for (SListFieldAll lst : list) {
            if (Tools.fStandardPdataTableID(lst.getFieldid()).equals(rowKey)) {
                return lst;
            }
        }
        return null;
    }
}
//}}</editor-fold>
