package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.system.S_Uom;
import main.entity.shared.system.S_Uom_Exchange;
import main.entity.shared.system.S_Uom_ExchangePK;
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
public class Sys_Uom_ExchangeDetails extends BasePageBean implements Serializable {

    private ISysRemote iSys = null;
    private S_Uom_Exchange m_S_Uom;

    public S_Uom_Exchange getUom() {
        return m_S_Uom;
    }

    public void setUom(S_Uom_Exchange m_S_Uom) {
        this.m_S_Uom = m_S_Uom;
    }
    private ResourcesFactory m_RfSiteProp;
    //Check nhập
    String m_Style_TxtCMID;
    String m_Style_TxtAssetID;
    String m_Style_TxtMeterID;
    String m_MsgCheck;
    List<S_Uom> lstUom = null;

    public List getListUom() {
        lstUom = new ArrayList<S_Uom>();
        lstUom = getISysRemote().getAllS_Uom();
        S_Uom s = new S_Uom("0");
        s.setUomdesc("");
        lstUom.add(0, s);
        return lstUom;
    }
    //{{<editor-fold defaultstate="collapsed" desc="Contructor">

    public Sys_Uom_ExchangeDetails() {
        if (m_RfSiteProp == null) {
            m_RfSiteProp = new ResourcesFactory("main/web/shared/system/prop/SystemProp");
        }
        if (!isPostback()) {
            String fromid = WorkingContext.getRequestQueryString("fromid");
            String toid = WorkingContext.getRequestQueryString("toid");
            if (fromid != null && toid != null) {
                LoadUomExchangeDetail(fromid,toid);
                setPageDetailInfo(enumResultSetPosition.eInit);
            } else {
                AddNewUomExchangeDetail();
            }
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Functions">
    public void CleanScreen() {
        m_S_Uom = new S_Uom_Exchange(new S_Uom_ExchangePK());        
        resetInputRequire();
    }

    /**
     * Load địa điểm
     * @param siteid
     */
    public void LoadUomExchangeDetail(String fromid,String toid) {
        try {
            CleanScreen();
            setFormMode(enumFormMode.eView);
            //Caller
            m_S_Uom = (S_Uom_Exchange) getISysRemote().findById(new S_Uom_ExchangePK(fromid, toid), S_Uom_Exchange.class);
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

    public void AddNewUomExchangeDetail() {
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
            S_Uom_Exchange uom = m_S_Uom;
            //Check nhap desc cho Vung dia diem
            if (uom.getId() == null||uom.getId().getFromuomid().equals("0")||uom.getId().getTouomid().equals("0")) {
                bCheck = false;
                m_Style_TxtCMID = "error";
            }
            if (uom.getMultipcoeff() == null || uom.getMultipcoeff().toString().trim().isEmpty()) {
                bCheck = false;
                m_Style_TxtMeterID = "error";
            }
            if (!bCheck) {
                m_MsgCheck = m_rfCommon.getMessage("msgNotEnoughDataInput");
                return false;
            }
            if(m_S_Uom.getId().getFromuomid().equals(m_S_Uom.getId().getTouomid())&&!uom.getId().getFromuomid().equals("0")&&!uom.getId().getTouomid().equals("0")){
                WorkingContext.showMessages(enumMessageMode.eError, null, "Không thể quy đổi do trùng mã");
                    return false;
            }
            if (getFormMode().equals(enumFormMode.eAddNew)) {
                S_Uom_Exchange Temp1 = (S_Uom_Exchange) getISysRemote().findById(m_S_Uom.getId(), S_Uom_Exchange.class);
                S_Uom_Exchange Temp2 = (S_Uom_Exchange) getISysRemote().findById(new S_Uom_ExchangePK(m_S_Uom.getId().getTouomid(), m_S_Uom.getId().getFromuomid()), S_Uom_Exchange.class);
                if (Temp1 != null || Temp2 != null) {
                    WorkingContext.showMessages(enumMessageMode.eError, null, "Đã tồn tại quy đổi giữa ("+m_S_Uom.getId().getFromuomid()+") và ("+m_S_Uom.getId().getTouomid()+")");
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
    private void UpdateListUom(int type, S_Uom_ExchangePK cmID) {
        List<S_Uom_ExchangePK> lstCMID = (List<S_Uom_ExchangePK>) WorkingContext.getSessionValue(WorkingContext.LIST_S_UOM_EXCHANGE);
        if (lstCMID == null) {
            lstCMID = new ArrayList<S_Uom_ExchangePK>();
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
                        LoadUomExchangeDetail(lstCMID.get(idxNew).getFromuomid(),lstCMID.get(idxNew).getTouomid());
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
        WorkingContext.setSessionValue(WorkingContext.LIST_S_UOM_EXCHANGE, lstCMID);
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Override">
    @Override
    protected void setPageDetailInfo(enumResultSetPosition resultSetPosition) {
        try {
            List<S_Uom_ExchangePK> lstCMID = (List<S_Uom_ExchangePK>) WorkingContext.getSessionValue(WorkingContext.LIST_S_UOM_EXCHANGE);
            setDisableNavigator(true);
            if (lstCMID != null && m_S_Uom != null) {
                if (!lstCMID.isEmpty() && m_S_Uom.getId() != null) {
                    //Tìm vị trí hiện tại
                    S_Uom_ExchangePK cmID = m_S_Uom.getId();
                    int idx = lstCMID.indexOf(cmID);
                    if (idx >= 0 && idx < lstCMID.size()) {
                        if (resultSetPosition.equals(enumResultSetPosition.eInit)) {
                            updateNavigator(idx, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eFirst)) {
                            LoadUomExchangeDetail(lstCMID.get(0).getFromuomid(),lstCMID.get(0).getTouomid());
                            updateNavigator(0, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.ePrevious)) {
                            idx--;
                            if (idx >= 0) {
                                LoadUomExchangeDetail(lstCMID.get(idx).getFromuomid(),lstCMID.get(idx).getTouomid());
                            }
                            updateNavigator(idx, lstCMID);
                        } else if (resultSetPosition.equals(enumResultSetPosition.eNext)) {
                            idx++;
                            if (idx < lstCMID.size()) {
                                LoadUomExchangeDetail(lstCMID.get(idx).getFromuomid(),lstCMID.get(idx).getTouomid());
                            }
                            updateNavigator(idx, lstCMID);
                        } else {//last
                            LoadUomExchangeDetail(lstCMID.get(lstCMID.size() - 1).getFromuomid(),lstCMID.get(lstCMID.size() - 1).getTouomid());
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
            AddNewUomExchangeDetail();
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_Uom_Details.class.getName() + ".onAddNewUomExchange()");
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
            //Lưu
            if (getFormMode() == enumFormMode.eAddNew) {
                if (getISysRemote() != null) {
                    if (getISysRemote().insert(m_S_Uom)) {
                        WorkingContext.showMessages(BaseConstant.enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgSaveSuccess"));
                        setFormMode(enumFormMode.eView);
                        //List
                        UpdateListUom(0, m_S_Uom.getId());
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
            showErrorFromException(ex, Sys_Uom_Details.class.getName() + ".onSaveUomExchange()");
        }
    }

    public void onDeleteUom(ActionEvent event) {
        try {
            if (m_S_Uom.getId() != null && getISysRemote() != null) {
                S_Uom_ExchangePK cmid = m_S_Uom.getId();
                //if (getISysRemote().delete(cmid, S_Site.class)) {
                if (getISysRemote().deleteUomExchange(cmid.getFromuomid(),cmid.getTouomid())>0) {
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

