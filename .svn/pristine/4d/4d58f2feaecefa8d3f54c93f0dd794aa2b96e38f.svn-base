/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.system.bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import main.ContextResources.EjbContext;
import main.entity.shared.system.S_Organization;
import main.remote.shared.system.IChonDonViRemote;
import main.remote.shared.system.IOrganization;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.WorkingContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class ChonDonViQuanLyInlineBean extends BasePageCommon implements Serializable {
    
    private TreeNode rootTreeNode;
    private TreeNode selectedTreeNode;
    private String m_MaDVQLMoi;
    private String m_TenDVQLMoi;
    private String m_MaDVQLHienTai;
    private String m_TenDVQLHienTai;
    private IChonDonViRemote m_IChonDonVi;
    private String m_AssetID;
    private IOrganization m_IOrganization;
    List<S_Organization> lstObjOrg;
    
    public ChonDonViQuanLyInlineBean() {
        khoiTao();
    }
    
    public IChonDonViRemote getIChonDonVi() {
        try {
            if (m_IChonDonVi == null) {
                m_IChonDonVi = (IChonDonViRemote) EjbContext.getLocalEJBRemote(IChonDonViRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login lại khi gọi ejb
            }
            
        } catch (Exception e) {
            return null;
        }
        return m_IChonDonVi;
    }
    
    public IOrganization getIOrganization() {
        try {
            if (m_IOrganization == null) {
                m_IOrganization = (IOrganization) EjbContext.getLocalEJBRemote(IOrganization.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login lại khi gọi ejb
            }
            
        } catch (Exception e) {
            return null;
        }
        return m_IOrganization;
    }
    
    public void khoiTao() {
        m_MaDVQLHienTai = WorkingContext.getOrganizationCurrent();
        if (m_MaDVQLHienTai == null) {
            //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            WorkingContext.redirect(WorkingContext.urlL);
            return;
        }
        S_Organization infoOrg = new S_Organization();
        infoOrg = getIOrganization().findS_OrganizationById(m_MaDVQLHienTai);
        
        m_TenDVQLHienTai = infoOrg.getOrgdesc();
        
        m_MaDVQLMoi = m_MaDVQLHienTai;
        m_TenDVQLMoi = m_TenDVQLHienTai;
        
        InitTree();
        
    }
    
    private void InitTree() {
        WorkingContext.resetDialogObjSelect();
        rootTreeNode = new DefaultTreeNode("root", null);
        TreeNode node0 = null;
        String strUserID = WorkingContext.getUserName();
        lstObjOrg = getIChonDonVi().SelectOrgShort(strUserID);
        boolean bFlag = true;
        for (S_Organization sorgParent : lstObjOrg) {
            bFlag = true;
            for (S_Organization sorg : lstObjOrg) {
                if (sorgParent.getsOrgidParent() != null && sorgParent.getsOrgidParent().getOrgid().equals(sorg.getOrgid())) {
                    bFlag = false;
                }
            }
            if (bFlag) {
                node0 = new DefaultTreeNode(sorgParent, rootTreeNode);
                node0.setExpanded(true);
                LoadTreeChild(sorgParent.getOrgid(), node0, strUserID);
            }
        }
    }
    
    private void LoadTreeChild(String strMaDVQLCapTren, TreeNode _nodeParent, String strUserID) {
        TreeNode nodeChild = null;
        for (int i = 0; i < lstObjOrg.size(); i++) {
            if (lstObjOrg.get(i).getsOrgidParent() != null && lstObjOrg.get(i).getsOrgidParent().getOrgid().equals(strMaDVQLCapTren)) {
                nodeChild = new DefaultTreeNode(lstObjOrg.get(i), _nodeParent);
                //nodeChild.setExpanded(true);
                LoadTreeChild(lstObjOrg.get(i).getOrgid(), nodeChild, strUserID);
            }
        }
        
    }
    
    public void onSearchAsset() {
        if (m_AssetID == null) {
            return;
        }
        WorkingContext.resetDialogObjSelect();
        rootTreeNode = new DefaultTreeNode("root", null);
        String strMaDVQL = WorkingContext.getOrganizationCurrent();
        
        List<S_Organization> objOrg = getIChonDonVi().SelectOrgByAssetID(strMaDVQL, m_AssetID);
        TreeNode nodeChild = null;
        if (objOrg.size() > 0) {
            for (int i = 0; i < objOrg.size(); i++) {
                nodeChild = new DefaultTreeNode(objOrg.get(i), rootTreeNode);
                
            }
            m_MaDVQLMoi = objOrg.get(0).getOrgid();
            m_TenDVQLMoi = objOrg.get(0).getOrgdesc();
        } else {
            m_MaDVQLMoi = "";
            m_TenDVQLMoi = "";
        }
    }
    
    public void onChangeOrg() {
        if (selectedTreeNode.getData() != null) {
            WorkingContext.setOrganizationCurrent(((S_Organization) selectedTreeNode.getData()).getOrgid());
            
            PrimeFaces.current().ajax().addCallbackParam("Method", "reload");

//            RequestContext context = RequestContext.getCurrentInstance();
//            context.addCallbackParam("Method", "reload");
        }
    }
    
    public void onChangeOrgCurrent() {
        
        WorkingContext.setOrganizationCurrent(m_MaDVQLHienTai);
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.addCallbackParam("Method", "reload");
        PrimeFaces.current().ajax().addCallbackParam("Method", "reload");
    }
    
    public void displaySelectedSingle(NodeSelectEvent event) {
        if (selectedTreeNode != null) {
            S_Organization node = (S_Organization) selectedTreeNode.getData();
            m_MaDVQLMoi = node.getOrgid();
            m_TenDVQLMoi = node.getOrgdesc();
        }
        
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
    
    public String getM_MaDVQLMoi() {
        return m_MaDVQLMoi;
    }
    
    public void setM_MaDVQLMoi(String m_MaDVQLMoi) {
        this.m_MaDVQLMoi = m_MaDVQLMoi;
    }
    
    public String getM_TenDVQLMoi() {
        return m_TenDVQLMoi;
    }
    
    public void setM_TenDVQLMoi(String m_TenDVQLMoi) {
        this.m_TenDVQLMoi = m_TenDVQLMoi;
    }
    
    public String getM_MaDVQLHienTai() {
        return m_MaDVQLHienTai;
    }
    
    public void setM_MaDVQLHienTai(String m_MaDVQLHienTai) {
        this.m_MaDVQLHienTai = m_MaDVQLHienTai;
    }
    
    public String getM_TenDVQLHienTai() {
        return m_TenDVQLHienTai;
    }
    
    public void setM_TenDVQLHienTai(String m_TenDVQLHienTai) {
        this.m_TenDVQLHienTai = m_TenDVQLHienTai;
    }
    
    public IChonDonViRemote getM_IChonDonVi() {
        return m_IChonDonVi;
    }
    
    public void setM_IChonDonVi(IChonDonViRemote m_IChonDonVi) {
        this.m_IChonDonVi = m_IChonDonVi;
    }
    
    public String getM_AssetID() {
        return m_AssetID;
    }
    
    public void setM_AssetID(String m_AssetID) {
        this.m_AssetID = m_AssetID;
    }
    
}
