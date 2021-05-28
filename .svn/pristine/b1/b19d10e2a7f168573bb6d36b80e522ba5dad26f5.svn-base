/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.admin.bean;

import main.ContextResources.EjbContext;


import main.remote.shared.admin.IAdmin;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import main.entity.shared.admin.Q_Pqobj_UserPK;
import main.entity.shared.admin.Q_User;
import main.entity.shared.report.Q_PQRPort_User;
import main.entity.shared.report.S_Report_Group;
import main.remote.shared.report.IReportGrantRemote;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author NAMCV
 */
@ManagedBean
@ViewScoped
public class GrantReportRightBean extends BasePageCommon
        implements Serializable {

    private List<S_Report_Group> m_lstRGroups;
    private List<SelectItem> m_dsUserRole;
    private String m_userRoleID;
    boolean m_bFncGrantAll;
    private IReportGrantRemote iReport;
    private TreeNode treeReports;
    private TreeNode[] selectedNodes;
    List<Q_PQRPort_User> lstPQUser;
    HashMap<String, Q_PQRPort_User> lstHUser = new HashMap();

    public GrantReportRightBean() throws Exception {
        if (!isPostback()) {
            this.m_dsUserRole = new ArrayList();
            IAdmin ia = getIAdmin();
            //List<Q_User> lst = ia.getAllUser();
            List<Q_User> lst = ia.getAllUser(WorkingContext.getUserName(), WorkingContext.getOrganizationCurrent());
            for (Q_User q : lst) {
                if ((q.getEnable() != null) && (q.getEnable().booleanValue())) {
                    SelectItem si = new SelectItem(q.getUserid(), "+ " + q.getUserid() + " (" + q.getUsername() + ")");
                    this.m_dsUserRole.add(si);
                }
            }
            
            if (m_userRoleID == null)
            {
                m_userRoleID = m_dsUserRole.get(0).getValue().toString();
            }

            loadParentTree();
        }
    }

    public void onCheckGrantAllFnc() {
        if (this.m_lstRGroups == null) {
            return;
        }
        for (S_Report_Group q : this.m_lstRGroups) {

        }
    }

    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }

    public TreeNode getTreeReports() {
        return treeReports;
    }

    public void setTreeReports(TreeNode treeReports) {
        this.treeReports = treeReports;
    }

    public List<SelectItem> getDsUserRole() {
        return this.m_dsUserRole;
    }

    public void onUserRoleChange() {
        loadParentTree();
    }

    public List<Q_PQRPort_User> getLstPQUser() {
        return lstPQUser;
    }

    public void setLstPQUser(List<Q_PQRPort_User> lstPQUser) {
        this.lstPQUser = lstPQUser;
    }

    public String getM_userRoleID() {
        return this.m_userRoleID;
    }

    public void setM_userRoleID(String m_userRoleID) {
        this.m_userRoleID = m_userRoleID;
    }

    public boolean isM_bFncGrantAll() {
        return this.m_bFncGrantAll;
    }

    public void setM_bFncGrantAll(boolean m_bFncGrantAll) {
        this.m_bFncGrantAll = m_bFncGrantAll;
    }

    private boolean checkReportEnablePrivillage(List<Q_PQRPort_User> lstQP, S_Report_Group sg) {
        for (Q_PQRPort_User user : lstQP) {
            //if(sg.)
            if ((sg.getRptgroupid()).equals(user.getId().getObjid())) {
                return true;
            }
        }
        return false;
    }

    private void loadParentTree() {
        treeReports = new CheckboxTreeNode("root", null);
        //Load cha truong hop

        List<S_Report_Group> lstParent = null;
        lstPQUser = new ArrayList();
        lstParent = getIReportRemote().getListReportGroupRoot(WorkingContext.getWorkingInfo().getOrgid());
        lstPQUser = getIReportRemote().getLstPQGrantReport(m_userRoleID);

        int i = 1;
        if (lstParent != null) {
            for (S_Report_Group groupParent : lstParent) {
                TreeNode nodeParent = new CheckboxTreeNode(groupParent, treeReports);
                if (i == 1) {
                    nodeParent.setExpanded(true);
                }

                if (checkReportEnablePrivillage(lstPQUser, groupParent)) {
                    nodeParent.isSelected();
                }
                i++;
                loadChildTree(nodeParent);
            }
        }
    }

    private void loadChildTree(TreeNode nodeParent) {
        S_Report_Group groupParent = (S_Report_Group) nodeParent.getData();
        List<S_Report_Group> lstChild
                = getIReportRemote().getListReportChild(groupParent);
        if (lstChild != null) {
            for (S_Report_Group groupChild : lstChild) {
                TreeNode nodeChild = new CheckboxTreeNode(groupChild, nodeParent);
                if (checkReportEnablePrivillage(lstPQUser, groupChild)) {
                    nodeChild.setSelected(true);
                    nodeChild.setExpanded(true);
                }
                loadChildTree(nodeChild);
            }
        }
    }

    public void onSave(ActionEvent ev) {
        try {
            String text = "";  
            getIReportRemote().delPQGranUser(m_userRoleID);            
            
            for (TreeNode sg : selectedNodes) {
                S_Report_Group gP = (S_Report_Group) sg.getData();
                Q_PQRPort_User ob = new Q_PQRPort_User();
                Q_Pqobj_UserPK ou = new Q_Pqobj_UserPK();
                ou.setObjid(gP.getRptgroupid());
                if (gP.getRptgroupord() == 1) {
                    ou.setObjtypeid("A");
                } else {
                    ou.setObjtypeid("G");
                }
                ou.setUserid(m_userRoleID);

                ob.setId(ou);
                ob.setRView(true);
                ob.setQ_user((Q_User) getIReportRemote().findById(m_userRoleID, Q_User.class));
                ob.setUserCrDtime(gP.getUserCrDtime());
                ob.setUserCrId(gP.getUserCrId());
                ob.setUserMdfId(gP.getUserMdfId());
                ob.setUserMdfDtime(gP.getUserMdfDtime());

                getIReportRemote().inPQGranUser(ob);
            }

            //ghi thành công
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, this.m_rfCommon.getMessage("msgSaveSuccess"));

        } catch (Exception e) {
            showErrorFromException(e, GrantReportRightBean.class.getName() + ".onSave()");
        }

    }

    public void onCheckGrantFnc() {

    }

    public IReportGrantRemote getIReportRemote() {
        try {
            if (iReport == null) {
                iReport = (IReportGrantRemote) EjbContext.getLocalEJBRemote(IReportGrantRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login lại khi gọi ejb
            }
        } catch (Exception ex) {
            showErrorFromException(ex, GrantReportRightBean.class.getName() + ".getIReportRemote()");
            return null;
        }
        return iReport;
    }

}
