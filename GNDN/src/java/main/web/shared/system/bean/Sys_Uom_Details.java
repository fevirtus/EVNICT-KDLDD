package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.system.S_Uom;
import main.remote.shared.system.ISysRemote;
import main.web.common.bean.BasePageBean;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import main.web.converters.DoubleConverter;
import evnit.util.common.BaseConstant;
import evnit.util.common.BaseConstant.enumFormMode;
import evnit.util.common.BaseConstant.enumMessageMode;
import evnit.util.common.BaseConstant.enumResultSetPosition;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author LanTH
 */
@ManagedBean
@ViewScoped
public class Sys_Uom_Details extends BasePageBean implements Serializable {

    private ISysRemote iSys = null;
    private S_Uom m_S_Uom;
    List<String> m_lstCMID; //ThảoDD sửa, thêm vào để giải phóng session

    public S_Uom getUom() {
        return m_S_Uom;
    }

    public void setUom(S_Uom m_S_Uom) {
        this.m_S_Uom = m_S_Uom;
    }
    private ResourcesFactory m_RfSiteProp;
    //Check nhập
    String m_Style_TxtCMID;
    String m_Style_TxtAssetID;
    String m_Style_TxtMeterID;
    String m_MsgCheck;
    //{{<editor-fold defaultstate="collapsed" desc="Contructor">

    public Sys_Uom_Details() {
        if (m_RfSiteProp == null) {
            m_RfSiteProp = new ResourcesFactory("main/web/shared/system/prop/SystemProp");
        }
        if (!isPostback()) {
            m_lstCMID = (List<String>) WorkingContext.getSessionValueAndRemove(WorkingContext.LIST_S_UOM);
            String uomid = WorkingContext.getRequestQueryString("uomid");
            if (uomid != null) {
                LoadUomDetail(uomid);
                setPageDetailInfo(enumResultSetPosition.eInit);
            } else {
                AddNewUomDetail();
            }
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Functions">
    public void CleanScreen() {
        m_S_Uom = new S_Uom();        
        resetInputRequire();
    }

    /**
     * Load địa điểm
     * @param siteid
     */
    public void LoadUomDetail(String uomid) {
        try {
            CleanScreen();
            setFormMode(enumFormMode.eView);
            //Caller
            m_S_Uom = (S_Uom) getISysRemote().findById(uomid, S_Uom.class);
            if (m_S_Uom != null) {
            } else {
                m_bDelete = true;
                m_bSave = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            WorkingContext.showMessages(enumMessageMode.eError, null, ex.getMessage());
        }
    }

    public void AddNewUomDetail() {
        CleanScreen();
        setFormMode(enumFormMode.eAddNew);
        m_bSave = false;
    }

    private boolean CheckInput() {
        try {
            resetInputRequire();
            if (m_S_Uom == null) {
                return false;
            }
            boolean bCheck = true;
            S_Uom uom = m_S_Uom;
             if (uom.getUomid() == null || uom.getUomid().trim().isEmpty()) {
                bCheck = false;
                m_Style_TxtCMID = "error";
            }
            //Check nhap desc cho Vung dia diem
            if (uom.getUomdesc() == null || uom.getUomdesc().trim().isEmpty()) {
                bCheck = false;
                m_Style_TxtMeterID = "error";
            }

            if (!bCheck) {
                m_MsgCheck = m_rfCommon.getMessage("msgNotEnoughDataInput");
                return false;
            }
            if (getFormMode().equals(enumFormMode.eAddNew)) {
                S_Uom Temp = (S_Uom) getISysRemote().findById(m_S_Uom.getUomid(), S_Uom.class);
                if (Temp != null) {
                    WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgIDExist"));
                    return false;
                }
            }
            return bCheck;
        } catch (Exception e) {
            
            return false;
        }
    }

    /**
     * Cập nhật mảng id khi thêm mới, xóa
     * @param type 0: thêm mới; 1: xóa
     */
    private void UpdateListUom(int type, String cmID) {
        List<String> lstCMID = m_lstCMID;// (List<String>) WorkingContext.getSessionValue(WorkingContext.LIST_S_UOM);
        if (lstCMID == null) {
            lstCMID = new ArrayList<String>();
        }
        switch (type) {
            case 0://Them moi
                lstCMID.add(cmID);
                updateNavigator(lstCMID.size() - 1, lstCMID);
                break;
            case 1://Delete
                if (lstCMID.contains(cmID)) {
                    int idx = lstCMID.indexOf(cmID);
                    lstCMID.remove(cmID);
                    //Mảng còn
                    int idxNew = 0;
                    if (idx < lstCMID.size() - 1) {
                        idxNew = idx + 1;
                    } else if (idx >= lstCMID.size() - 1) {
                        idxNew = lstCMID.size() - 1;
                    } else if (idx > 0) {
                        idxNew = idx - 1;
                    }
                    if (idxNew >= 0 && idxNew < lstCMID.size()) {
                        LoadUomDetail(lstCMID.get(idxNew));
                        updateNavigator(idxNew, lstCMID);
                    } else {
                        setFormMode(enumFormMode.eInit);
                    }
                } else {
                    setPageDetailInfo(enumResultSetPosition.eFirst);
                }
                break;
            default:
                break;
        }
        //WorkingContext.setSessionValue(WorkingContext.LIST_S_UOM, lstCMID);
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Override">
    @Override
    protected void setPageDetailInfo(enumResultSetPosition resultSetPosition) {
        try {
            List<String> lstCMID = m_lstCMID;// (List<String>) WorkingContext.getSessionValue(WorkingContext.LIST_S_UOM);
            setDisableNavigator(true);
            if (lstCMID != null && m_S_Uom != null) {
                if (!lstCMID.isEmpty() && m_S_Uom.getUomid() != null) {
                    //Tìm vị trí hiện tại
                    String cmID = m_S_Uom.getUomid();
                    int idx = lstCMID.indexOf(cmID);
                    if (idx >= 0 && idx < lstCMID.size()) {
                        if (resultSetPosition.equals(enumResultSetPosition.eInit)) {
                            updateNavigator(idx, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eFirst)) {
                            LoadUomDetail(lstCMID.get(0));
                            updateNavigator(0, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.ePrevious)) {
                            idx--;
                            if (idx >= 0) {
                                LoadUomDetail(lstCMID.get(idx));
                            }
                            updateNavigator(idx, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eNext)) {
                            idx++;
                            if (idx < lstCMID.size()) {
                                LoadUomDetail(lstCMID.get(idx));
                            }
                            updateNavigator(idx, lstCMID);
                        } else {//last
                            LoadUomDetail(lstCMID.get(lstCMID.size() - 1));
                            updateNavigator(lstCMID.size() - 1, lstCMID);
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
        m_Style_TxtCMID = "";
        m_Style_TxtAssetID = "";
        m_Style_TxtMeterID = "";
        m_MsgCheck = "";
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Events">
    public void onAddNewUom(ActionEvent event) {
        try {
            AddNewUomDetail();
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_Uom_Details.class.getName() + ".onAddNewUom()");
        }
    }

    public void onSaveUom(ActionEvent event) {
        try {
            if (!CheckInput()) {
                if (!m_MsgCheck.isEmpty()) {
                    WorkingContext.showMessages(enumMessageMode.eWarn, null, m_MsgCheck);
                }
                return;
            }
            if (!checkID_Standard(m_S_Uom.getUomid()))
                return;
            //Lưu
            if (getFormMode() == enumFormMode.eAddNew) {
                if (getISysRemote() != null) {
                    if (getISysRemote().insert(m_S_Uom)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                        //List
                        UpdateListUom(0, m_S_Uom.getUomid());
                        //LoadMeterDataCurrent();
                    } else {
                        WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgSaveError"));
                    }

                }
            } else {
                if (getISysRemote() != null) {
                    if (getISysRemote().update(m_S_Uom)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                        //LoadMeterDataCurrent();
                    } else {
                        WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgSaveError"));
                    }
                }
            }
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_Uom_Details.class.getName() + ".onSaveUom()");
        }
    }

    public void onDeleteUom(ActionEvent event) {
        try {
            if (m_S_Uom.getUomid() != null && getISysRemote() != null) {
                String cmid = m_S_Uom.getUomid();
                //if (getISysRemote().delete(cmid, S_Site.class)) {
                if (getISysRemote().deleteUom(cmid)>0) {
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    CleanScreen();
                    UpdateListUom(1, cmid);
                } else {
                    WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgDeleteError")
                            + BaseConstant.CRLF() + getISysRemote().getLastActionInfo());
                }
            }
        } catch (Exception ex) {
            WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgDeleteError"));
            //showErrorFromException(ex, Sys_Organization_Details.class.getName() + ".onDeleteOrganization()");
        }
    }

    //}}</editor-fold>
    //{{<editor-fold defaultstate="collapsed" desc="Properties">
    public ISysRemote getISysRemote() {
        try {
            if (iSys == null) {
                iSys = (ISysRemote) EjbContext.getLocalEJBRemote(ISysRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login lại khi gọi ejb
            }
        } catch (Exception ex) {
            return null;
        }
        return iSys;
    }

    public Boolean getTxtCMIDReadOnly() {
        if (getFormMode() == enumFormMode.eAddNew) {
            return false;
        }
        return true;
    }

    public String getStyle_TxtAssetID() {
        return m_Style_TxtAssetID;
    }

    public String getStyle_TxtCMID() {
        return m_Style_TxtCMID;
    }

    public String getStyle_TxtMeterID() {
        return m_Style_TxtMeterID;
    }

    public DoubleConverter getDoubleConverter() {
        DoubleConverter converter = new DoubleConverter();
        converter.setPatern_EN("#,##0.0###");
        return converter;
    }
    //}}</editor-fold>
}
