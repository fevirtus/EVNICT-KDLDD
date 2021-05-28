/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.system.SListAll;
import main.entity.shared.system.SListFieldAll;
import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageBean;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant;
import evnit.util.common.BaseConstant.enumFormMode;
import evnit.util.common.S_Key_ControlInfo;
import java.io.Serializable;
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
public final class DanhMucDungChungDetailBean extends BasePageBean implements Serializable {

    private SListAll m_SListALL;
    private SListFieldAll m_SListFieldALL;
    private IDanhMucDungChungRemote m_IDanhMucDungChungRemote;

    private Boolean m_bSave;
    String m_sDeleteMsg;
    private ResourcesFactory m_RfAssetProp;

    String m_Style_TxtID;
    String m_Style_TxtDesc;
    String m_MsgCheck;

    String g_ListID = "";
    List<String> strSListFielDALL;

    public DanhMucDungChungDetailBean() {
        if (m_RfAssetProp == null) {
            m_RfAssetProp = new ResourcesFactory("main/web/eam/asset/prop/AssetProp");
        }
        if (!isPostback()) {
            String strListid = WorkingContext.getRequestQueryString("listid");
            String strFieldid = WorkingContext.getRequestQueryString("fieldid");

            g_ListID = strListid;
            if (strFieldid != null) {
                LoadDetail(strListid, strFieldid);
            } else {
//                KhoiTao(strListid);
                AddNewDetail();
            }

        }
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
            strSListFielDALL = (List<String>) WorkingContext.getSessionValue("S_LIST_FIELD_ALL");
            setDisableNavigator(true);
            if (strSListFielDALL != null && m_SListFieldALL != null) {
                if (!strSListFielDALL.isEmpty() && m_SListFieldALL.getFieldid() != null) {
                    //Tìm vị trí hiện tại
                    String id = m_SListFieldALL.getFieldid();
                    int idx = strSListFielDALL.indexOf(id);
                    if (idx >= 0 && idx < strSListFielDALL.size()) {
                        if (resultSetPosition.equals(BaseConstant.enumResultSetPosition.eInit)) {
                            updateNavigator(idx, strSListFielDALL);
                        } else if (resultSetPosition.equals(BaseConstant.enumResultSetPosition.eFirst)) {
                            LoadDetail(g_ListID, strSListFielDALL.get(0));
                            updateNavigator(0, strSListFielDALL);
                        } else if (resultSetPosition.equals(BaseConstant.enumResultSetPosition.ePrevious)) {
                            idx--;
                            if (idx >= 0) {
                                LoadDetail(g_ListID, strSListFielDALL.get(idx));
                            }
                            updateNavigator(idx, strSListFielDALL);
                        } else if (resultSetPosition.equals(BaseConstant.enumResultSetPosition.eNext)) {
                            idx++;
                            if (idx < strSListFielDALL.size()) {
                                LoadDetail(g_ListID, strSListFielDALL.get(idx));
                            }
                            updateNavigator(idx, strSListFielDALL);
                        } else {//last
                            LoadDetail(g_ListID, strSListFielDALL.get(strSListFielDALL.size() - 1));
                            updateNavigator(strSListFielDALL.size() - 1, strSListFielDALL);
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
        m_Style_TxtDesc = "";
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

    private void LoadDetail(String strListID, String strFieldID) {
        try {
            SListFieldAll temp = new SListFieldAll();
            temp = (SListFieldAll) getIDanhMucDungChungRemote().findById(strFieldID, SListFieldAll.class);
            if (temp != null) {
                m_SListFieldALL = temp;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            showErrorFromException(ex, DanhMucDungChungDetailBean.class.getName() + "LoadDetail()");
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
            showErrorFromException(ex, DanhMucDungChungDetailBean.class.getName() + ".onAddNewDetail()");
        }
    }

    public void AddNewDetail() {
        CleanScreen();
        setFormMode(enumFormMode.eAddNew);
        m_bSave = false;
    }

    public void CleanScreen() {
        m_SListFieldALL = new SListFieldAll();        
        resetInputRequire();
    }

    //Save
    private boolean checkData() {
        if (m_SListFieldALL.getFieldcode() == null || m_SListFieldALL.getFieldcode().equals("")) {
            m_Style_TxtID = "error";
            return false;
        }
        if (m_SListFieldALL.getFielddesc() == null || m_SListFieldALL.getFielddesc().equals("")) {
            m_Style_TxtDesc = "error";
            return false;
        }
        if (getIDanhMucDungChungRemote().CheckKeys(g_ListID, m_SListFieldALL.getFieldcode())==true)
        {
            m_Style_TxtID = "error";
            return false;
        }
        
        return true;
    }

    public void onSaveDetail(ActionEvent event) {
        try {
            //Lưu
            if (getFormMode() == enumFormMode.eAddNew) {
                if (getIDanhMucDungChungRemote() != null) {
                    if (checkData()) {
                        ISystemCommonRemote iscr = (ISystemCommonRemote) EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
                        S_Key_ControlInfo kcInit = new S_Key_ControlInfo(null, "S_LIST_FIELD_ALL");
                        S_Key_ControlInfo keysc = iscr.getGenKeyID(kcInit);
                        m_SListFieldALL.setListid(g_ListID);
                        m_SListFieldALL.setFieldid(keysc.getOutputValue());
                        if (m_IDanhMucDungChungRemote.insert(m_SListFieldALL)) {
                            WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                            setFormMode(enumFormMode.eView);
                            //List
                            //UpdateListDetails(0, m_SListFieldALL.getCategoryid(), null);
                        }
                    } else {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, "Bạn chưa nhập đủ dữ liệu");
                    }

                }
            } else {
                if (getIDanhMucDungChungRemote() != null) {
                    if (m_IDanhMucDungChungRemote.update(m_SListFieldALL)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                    }
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, DanhMucDungChungDetailBean.class.getName() + ".onSaveDetail()");
        }
    }

    //Delete 
    public void onDeleteDetail(ActionEvent event) {

    }

    public SListFieldAll getM_SListFieldALL() {
        return m_SListFieldALL;
    }

    public void setM_SListFieldALL(SListFieldAll m_SListFieldALL) {
        this.m_SListFieldALL = m_SListFieldALL;
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

    public String getM_Style_TxtDesc() {
        return m_Style_TxtDesc;
    }

    public void setM_Style_TxtDesc(String m_Style_TxtDesc) {
        this.m_Style_TxtDesc = m_Style_TxtDesc;
    }

    public String getM_MsgCheck() {
        return m_MsgCheck;
    }

    public void setM_MsgCheck(String m_MsgCheck) {
        this.m_MsgCheck = m_MsgCheck;
    }

    public SListAll getM_SListALL() {
        return m_SListALL;
    }

    public void setM_SListALL(SListAll m_SListALL) {
        this.m_SListALL = m_SListALL;
    }
}
