/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.system.SListGroupAll;
import main.web.common.bean.BasePageBean;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant;
import evnit.util.common.BaseConstant.enumFormMode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import main.remote.shared.system.IDanhMucDungChungRemote;

/**
 *
 * @author HaNV
 */
@ManagedBean
@ViewScoped
public final class DanhMucDungChungGroupDetailBean extends BasePageBean implements Serializable {
    
    private SListGroupAll m_SListGroupALL;
    private SListGroupAll m_SListGroupParentALL;
    private IDanhMucDungChungRemote m_IDanhMucDungChungRemote;
    
    private Boolean m_bSave;
    String m_sDeleteMsg;
    private ResourcesFactory m_RfAssetProp;
    
    String m_Style_TxtID;
    String m_Style_TxtDesc;
    String m_Style_TxtOrg;
    String m_MsgCheck;
    
    String g_ListID = "";
    List<String> strSListFielDALL;
    private List<String[]> lstGroup;
    
    
    //bien luu khi chon luoi
    
    public DanhMucDungChungGroupDetailBean() {
        if (m_RfAssetProp == null) {
            m_RfAssetProp = new ResourcesFactory("main/web/eam/asset/prop/AssetProp");
        }
        if (!isPostback()) {
             
            String strId = WorkingContext.getRequestQueryString("id");
            String strGrouptId = WorkingContext.getRequestQueryString("parentId");
            if (strGrouptId != null) {
                m_SListGroupParentALL = (SListGroupAll) getIDanhMucDungChungRemote().findById(strGrouptId, SListGroupAll.class);
            }
            if (strId != null) {
                LoadDetail(strId);
            } else {
//                KhoiTao(strListid);
                AddNewDetail();
            }
            
            
        }
        lstGroup = getIDanhMucDungChungRemote().getListGroupForSearch();
    }
    
    
   
    public List<SListGroupAll> completeSearchNameGroup(String query) {
        List<SListGroupAll> results = new ArrayList<SListGroupAll>();
        results = getMatchingNameGroup(lstGroup, query, 25);
        return results;
    }
    
    List<SListGroupAll> getMatchingNameGroup(List<String[]> list, String regex, int maxResult) {
        ArrayList<SListGroupAll> matches = new ArrayList<SListGroupAll>();
        int iCount = 0;
        for (Object[] s : list) {
            if (s[0].toString().toUpperCase().indexOf(regex.toUpperCase()) >= 0 || s[1].toString().toUpperCase().indexOf(regex.toUpperCase()) >= 0) {
                matches.add(new SListGroupAll(s[0].toString(), s[1].toString()));
                iCount++;
                if (iCount >= maxResult) {
                    break;
                }
            }
        }
        return matches;
    }

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
    
    @Override
    
    protected void setPageDetailInfo(BaseConstant.enumResultSetPosition resultSetPosition) {
        try {
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    protected void resetInputRequire() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private enum enumAction {
        
        eNone,
        eCAATDelete,
        eCAMTDelete
    }
    private enumAction m_eActionCurrent = enumAction.eNone;
    //Sua

    private void KhoiTao(String strListID) {
        try {
            SListGroupAll temp = new SListGroupAll();
            temp = (SListGroupAll) getIDanhMucDungChungRemote().findById(strListID, SListGroupAll.class);
            if (temp != null) {
                m_SListGroupALL = temp;
            }
        } catch (Exception ex) {
//            ex.printStackTrace();
//            showErrorFromException(ex, DanhMucDungChungDetailBean.class.getName() + "LoadDetail()");
        }
    }
    
    private void LoadDetail(String strId) {
        try {
            SListGroupAll temp = new SListGroupAll();
            temp = (SListGroupAll) getIDanhMucDungChungRemote().findById(strId, SListGroupAll.class);
            if (temp != null) {
                m_SListGroupALL = temp;
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean getDisabledDeleteData() {
        if (getFormMode() == enumFormMode.eAddNew) {
            return true;
        }
        return false;
    }
    
    public boolean getDisabledSaveData() {
        if (getFormMode() == enumFormMode.eAddNew) {
            return false;
        }
        return false;
    }
    
    public void onAddNewDetail(ActionEvent event) {
        try {
            AddNewDetail();
        } catch (Exception ex) {
            showErrorFromException(ex, DanhMucDungChungGroupDetailBean.class.getName() + ".onAddNewDetail()");
        }
    }
    
    public void AddNewDetail() {
        CleanScreen();
        setFormMode(enumFormMode.eAddNew);
        m_bSave = false;
    }
    
    public void CleanScreen() {
        m_SListGroupALL = new SListGroupAll();        
        m_SListGroupALL.setGrouplistParent(m_SListGroupParentALL);
        m_SListGroupALL.setGrouplistord(0);
//        m_SListFieldALLParent = null;
//        listCAAT = null;
//        listCAMT = null;
//        resetInputRequire();
    }

    private boolean checkData() {
        if (m_SListGroupALL.getGrouplistid() == null || m_SListGroupALL.getGrouplistid().equals("")) {
            m_Style_TxtID = "error";
            return false;
        }
        if (m_SListGroupALL.getGrouplistdesc()== null || m_SListGroupALL.getGrouplistdesc().equals("")) {
            m_Style_TxtDesc = "error";
            return false;
        }
        
        if (getIDanhMucDungChungRemote().findById(m_SListGroupALL.getGrouplistid(), SListGroupAll.class)!=null)
        {
            m_Style_TxtID = "error";
            return false;
        }
        
        return true;
    }
    //Save
    public void onSaveDetail(ActionEvent event) {
        try {
            //Lưu
            if (getFormMode() == enumFormMode.eAddNew) {
                if (getIDanhMucDungChungRemote() != null) {
                    if (checkData()) {
                        if (m_IDanhMucDungChungRemote.insert(m_SListGroupALL)) {
                            WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                            setFormMode(enumFormMode.eView);
                        }
                    } else {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, "Bạn chưa nhập đủ dữ liệu");
                    }
                }
            } else {
                if (getIDanhMucDungChungRemote() != null) {
                    if (m_IDanhMucDungChungRemote.update(m_SListGroupALL)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                    }
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, DanhMucDungChungGroupDetailBean.class.getName() + ".onSaveDetail()");
        }
    }

    //Delete 
    public void onDeleteDetail(ActionEvent event) {
        try {
            m_IDanhMucDungChungRemote.delete(m_SListGroupALL.getGrouplistid(), SListGroupAll.class);
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, "Xóa nhóm danh mục thành công");
            AddNewDetail();
        } catch (Exception e) {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, "Xóa nhóm danh mục thất bại có thể do nhóm danh mục đã được sử dụng ở nơi khác");
        }
    }

   
   
    
    public SListGroupAll getM_SListGroupALL() {
        return m_SListGroupALL;
    }
    
    public void setM_SListGroupALL(SListGroupAll m_SListGroupALL) {
        this.m_SListGroupALL = m_SListGroupALL;
    }
    
    public SListGroupAll getM_SListGroupParentALL() {
        return m_SListGroupParentALL;
    }
    
    public void setM_SListGroupParentALL(SListGroupAll m_SListGroupParentALL) {
        this.m_SListGroupParentALL = m_SListGroupParentALL;
    }
    
    public String getM_sDeleteMsg() {
        return m_sDeleteMsg;
    }
    
    public void setM_sDeleteMsg(String m_sDeleteMsg) {
        this.m_sDeleteMsg = m_sDeleteMsg;
    }
    
    public ResourcesFactory getM_RfAssetProp() {
        return m_RfAssetProp;
    }
    
    public void setM_RfAssetProp(ResourcesFactory m_RfAssetProp) {
        this.m_RfAssetProp = m_RfAssetProp;
    }
    
    public String getM_Style_TxtID() {
        return m_Style_TxtID;
    }
    
    public void setM_Style_TxtID(String m_Style_TxtID) {
        this.m_Style_TxtID = m_Style_TxtID;
    }
    
    public String getM_Style_TxtOrg() {
        return m_Style_TxtOrg;
    }
    
    public void setM_Style_TxtOrg(String m_Style_TxtOrg) {
        this.m_Style_TxtOrg = m_Style_TxtOrg;
    }
    
    public String getM_MsgCheck() {
        return m_MsgCheck;
    }
    
    public void setM_MsgCheck(String m_MsgCheck) {
        this.m_MsgCheck = m_MsgCheck;
    }
    
    public SListGroupAll getM_SListALL() {
        return m_SListGroupALL;
    }
    
    public void setM_SListALL(SListGroupAll m_SListGroupALL) {
        this.m_SListGroupALL = m_SListGroupALL;
    }
    
    public Boolean getTxtReadOnly() {
        if (getFormMode() == evnit.util.common.BaseConstant.enumFormMode.eAddNew) {
            return Boolean.valueOf(false);
        } else {
            return Boolean.valueOf(true);
        }
    }
    
    
}
