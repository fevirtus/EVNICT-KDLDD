/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.system.SListAll;
import main.entity.shared.system.SListGroupAll;
import main.entity.shared.system.S_List_All_Category;
import main.web.common.bean.BasePageBean;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant;
import evnit.util.common.BaseConstant.enumFormMode;
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
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author HaNV
 */
@ManagedBean
@ViewScoped
public final class DanhMucDungChungFunDetailBean extends BasePageBean implements Serializable {
    
    private List<S_List_All_Category> lstALLCate;
    private SListAll m_SListALL;
    private SListGroupAll m_SListGroupParentALL;
    private IDanhMucDungChungRemote m_IDanhMucDungChungRemote;
    
    private Boolean m_bSave;
    String m_sDeleteMsg;
    private ResourcesFactory m_RfAssetProp;
    
    String m_Style_TxtID;
    String m_Style_TxtOrg;
    String m_MsgCheck;
    
    String g_ListID = "";
    List<String> strSListFielDALL;
    private List<String[]> lstGroup;
    private S_List_All_Category[] SelectionLstALLCate;
    
    public DanhMucDungChungFunDetailBean() {
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
            
            g_ListID = strId;
            LoadDataGrid(g_ListID);
            
        }
        lstGroup = getIDanhMucDungChungRemote().getListGroupForSearch();
    }
public void onDeleteTabDtStart(ActionEvent event) {
        try {
            if (checkSelectionTabDt()) {
                m_sDeleteMsg = m_rfCommon.getMessage("msgDlgDeleteInfo");
                m_sDeleteMsg = m_sDeleteMsg.replaceAll("@count", String.valueOf(SelectionLstALLCate.length));
                if (SelectionLstALLCate.length == 1) {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", " ('" + SelectionLstALLCate[0].getLstid() + "')");
                } else {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", "");
                }

//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "DeleteTabDt");
                PrimeFaces.current().ajax().addCallbackParam("Method", "DeleteTabDt");
            }
        } catch (Exception ex) {
//            showErrorFromException(ex, Oper_MeterTabMgtBean.class.getName() + ".onDeleteTabDtStart()");
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eError, null, m_rfCommon.getMessage("msgDeleteError"));
        }
    }

    public void onDeleteTabDtEnd(ActionEvent event) {
        try {
            if (checkSelectionTabDt()) {
                if (SelectionLstALLCate != null) {
                    for (int i = 0; i < SelectionLstALLCate.length; i++) {
                        if (!getIDanhMucDungChungRemote().delete(SelectionLstALLCate[i].getLstid(), S_List_All_Category.class)) {
                            showError(getIDanhMucDungChungRemote().getLastActionInfo(), S_List_All_Category.class.getName() + ".onDeleteTabDtEnd()");
                            return;
                        }
                    }
                    WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
//                    m_LstMeter_Tab_Dts = null;
                    LoadDataGrid(g_ListID);
                }
            }
        } catch (Exception ex) {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eError, null, m_rfCommon.getMessage("msgDeleteError"));
//            showErrorFromException(ex, Oper_MeterTabMgtBean.class.getName() + ".onDeleteTabDtEnd()");
        }
    }
public void LoadDataGrid(String strListID) {
        lstALLCate=null;
        lstALLCate = m_IDanhMucDungChungRemote.getLstCategory(strListID);
    }
private boolean checkSelectionTabDt() {
        if (SelectionLstALLCate != null) {
            if (SelectionLstALLCate.length > 0) {
                return true;
            }
        }
        //Thong bao chon hang
        WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null,"Bạn chưa chọn");
        return false;
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
            SListAll temp = new SListAll();
            temp = (SListAll) getIDanhMucDungChungRemote().findById(strListID, SListAll.class);
            if (temp != null) {
                m_SListALL = temp;
            }
        } catch (Exception ex) {
//            ex.printStackTrace();
//            showErrorFromException(ex, DanhMucDungChungDetailBean.class.getName() + "LoadDetail()");
        }
    }
    
    private void LoadDetail(String strId) {
        try {
            SListAll temp = new SListAll();
            temp = (SListAll) getIDanhMucDungChungRemote().findById(strId, SListAll.class);
            if (temp != null) {
                m_SListALL = temp;
                
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
            showErrorFromException(ex, DanhMucDungChungFunDetailBean.class.getName() + ".onAddNewDetail()");
        }
    }
    
    public void AddNewDetail() {
        CleanScreen();
        setFormMode(enumFormMode.eAddNew);
        m_bSave = false;
    }
    
    public void CleanScreen() {
        m_SListALL = new SListAll();        
        m_SListALL.setGrouplistid(m_SListGroupParentALL);
        m_SListALL.setListord(0);
//        m_SListFieldALLParent = null;
//        listCAAT = null;
//        listCAMT = null;
//        resetInputRequire();
    }

    //Save
    public void onSaveDetail(ActionEvent event) {
        try {
            //Lưu
            if (getFormMode() == enumFormMode.eAddNew) {
                if (getIDanhMucDungChungRemote() != null) {
                    if (m_IDanhMucDungChungRemote.insert(m_SListALL)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                    }
                }
            } else {
                if (getIDanhMucDungChungRemote() != null) {
                    if (m_IDanhMucDungChungRemote.update(m_SListALL)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                    }
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, DanhMucDungChungFunDetailBean.class.getName() + ".onSaveDetail()");
        }
    }

    //Delete 
    public void onDeleteDetail(ActionEvent event) {
        try {
            m_IDanhMucDungChungRemote.delete(m_SListALL.getListid(), SListAll.class);
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, "Xóa danh mục thành công");
            AddNewDetail();
        } catch (Exception e) {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, "Xóa danh mục thất bại có thể do nhóm danh mục đã được sử dụng ở nơi khác");
        }
    }

    public SListAll getM_SListALL() {
        return m_SListALL;
    }

    public void setM_SListALL(SListAll m_SListALL) {
        this.m_SListALL = m_SListALL;
    }

    public List<S_List_All_Category> getLstALLCate() {
        return lstALLCate;
    }

    public void setLstALLCate(List<S_List_All_Category> lstALLCate) {
        this.lstALLCate = lstALLCate;
    }

    public S_List_All_Category[] getSelectionLstALLCate() {
        return SelectionLstALLCate;
    }

    public void setSelectionLstALLCate(S_List_All_Category[] SelectionLstALLCate) {
        this.SelectionLstALLCate = SelectionLstALLCate;
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
    
    
    
    public Boolean getTxtReadOnly() {
        if (getFormMode() == evnit.util.common.BaseConstant.enumFormMode.eAddNew) {
            return Boolean.valueOf(false);
        } else {
            return Boolean.valueOf(true);
        }
    }
public ListCategoryDataModel getTableDataModel() {
        return new ListCategoryDataModel(lstALLCate);
    }
}
//{{<editor-fold defaultstate="collapsed" desc="Class hien thi luoi">

class ListCategoryDataModel extends ListDataModel<S_List_All_Category> implements SelectableDataModel<S_List_All_Category> {

    public ListCategoryDataModel() {
    }

    public ListCategoryDataModel(List<S_List_All_Category> data) {
        super(data);
    }

    @Override
    public String getRowKey(S_List_All_Category t) {
        return Tools.fStandardPdataTableID(t.getLstid());
    }

    @Override
    public S_List_All_Category getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

        List<S_List_All_Category> list = (List<S_List_All_Category>) getWrappedData();

        for (S_List_All_Category lst : list) {
            if (Tools.fStandardPdataTableID(lst.getLstid()).equals(rowKey)) {
                return lst;
            }
        }
        return null;
    }
}
//}}</editor-fold>
