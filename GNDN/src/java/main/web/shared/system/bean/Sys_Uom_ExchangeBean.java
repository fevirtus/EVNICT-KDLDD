/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.system.S_Uom;
import main.entity.shared.system.S_Uom_Exchange;
import main.entity.shared.system.S_Uom_ExchangePK;
import main.remote.shared.system.ISysRemote;
import main.web.common.bean.BasePageBean;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant.enumMessageMode;
import evnit.util.common.BaseConstant.enumResultSetPosition;
import evnit.util.common.Tools;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CloseEvent;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class Sys_Uom_ExchangeBean extends BasePageBean implements Serializable {

    private List<S_Uom_Exchange> lstS_Uom;
    private static ISysRemote m_ISysRemote;
    private S_Uom_Exchange[] selectedUom;
    private S_Uom_Exchange[] arrSelectUom;
    private boolean m_bInited;
    private String m_sDeleteMsg;

    /** Creates a new instance of Sys_UomBean */
    public S_Uom_Exchange[] getSelectedUom() {
        return selectedUom;
    }

    public void setSelectedUom(S_Uom_Exchange[] selectedUom) {
        this.selectedUom = selectedUom;
    }

    public DataTable getDtUom() {
        return (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("formMain:dtUom");
    }

    public Sys_Uom_ExchangeBean() {
        if (!m_bInited) {
            getDsS_UomForGrid_Reload();
        }
        m_bInited = true;
    }

    public ISysRemote getISysRemote() {
        try {
            if (m_ISysRemote == null) {
                m_ISysRemote = (ISysRemote) EjbContext.getLocalEJBRemote(ISysRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login lại khi gọi ejb
            }
        } catch (Exception ex) {
            return null;
        }
        return m_ISysRemote;
    }

    public List<S_Uom_Exchange> getDsS_UomForGrid_Reload() {
        try {
            lstS_Uom = getISysRemote().getAllS_Uom_Exchange();
//            SaveListUomToSession(lstS_Uom);
            return lstS_Uom;
        } catch (Exception e) {
            showErrorFromException(e, Sys_Uom_ExchangeBean.class.getName() + ".getAllUomExchange()");
            e.printStackTrace();
            return null;
        }
    }
    public S_Uom_ExchangeDataModel getDsS_UomForGrid() {
        if(lstS_Uom!=null && lstS_Uom.size()>0)
            return new S_Uom_ExchangeDataModel(lstS_Uom);
        else
            return null;
    }

    //{{<editor-fold defaultstate="collapsed" desc="Setup Organization">
    public void onEditUom(ActionEvent event) {
        try {
            if (checkSelectionCM()) {
                String fromUomId=arrSelectUom[0].getId().getFromuomid();
//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "Edit");
//                context.addCallbackParam("fromid", fromUomId);
//                context.addCallbackParam("toid", arrSelectUom[0].getId().getTouomid());
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "Edit");
                PrimeFaces.current().ajax().addCallbackParam("fromid", fromUomId);
                PrimeFaces.current().ajax().addCallbackParam("toid", arrSelectUom[0].getId().getTouomid());
                
                List<S_Uom_ExchangePK> lstID = new ArrayList<S_Uom_ExchangePK>();
                //lstID.add(String.valueOf(0));
                for (int i = 0; i < arrSelectUom.length; i++) {
                    S_Uom_Exchange b = arrSelectUom[i];
                    if (b.getId().getFromuomid().equals(fromUomId)) //Gán chỉ số đang chọn
                    {
                        lstID.add(0, b.getId());
                    } else {
                        lstID.add(b.getId());
                    }
                }
                WorkingContext.setSessionValue(WorkingContext.LIST_S_UOM_EXCHANGE, lstID);
            }

        } catch (Exception ex) {
            showErrorFromException(ex, Sys_UomBean.class.getName() + ".onEditUomExchange()");
        }
    }

    public void onDeleteUomStart(ActionEvent event) {
        try {
            if (checkSelectionCM()) {
                m_sDeleteMsg = m_rfCommon.getMessage("msgDlgDeleteUom");
                m_sDeleteMsg = m_sDeleteMsg.replaceAll("@count", String.valueOf(arrSelectUom.length));
                if (arrSelectUom.length == 1) {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", " ('" + arrSelectUom[0].getId().getFromuomid() + "-" + arrSelectUom[0].getId().getTouomid() + "')");
                } else {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", "");
                }
//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "Delete");
//                context.addCallbackParam("fromuomid", arrSelectUom[0].getId().getFromuomid());
//                context.addCallbackParam("touomid", arrSelectUom[0].getId().getTouomid());
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "Delete");
                PrimeFaces.current().ajax().addCallbackParam("fromuomid", arrSelectUom[0].getId().getFromuomid());
                PrimeFaces.current().ajax().addCallbackParam("touomid", arrSelectUom[0].getId().getTouomid());
            }

        } catch (Exception ex) {
            showErrorFromException(ex, Sys_UomBean.class.getName() + ".onDeleteUomExchange()");
        }
    }

    public void onDeleteUomEnd(ActionEvent event) {
        try {
            if (getISysRemote() != null) {
                if (arrSelectUom != null) {
                    for (int i = 0; i< arrSelectUom.length; i++) {
                        //if (!getISysRemote().delete(arrSelectSite[i].getSiteid(),S_Site.class))
                        if (getISysRemote().deleteUomExchange(arrSelectUom[i].getId().getFromuomid(), arrSelectUom[0].getId().getTouomid()) <= 0) {
                            WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgDeleteError"));
                            //showError(getISysRemote().getLastActionInfo(), S_Organization.class.getName() + ".onDeleteOrganizationEnd()");
                            if (i > 0)
                                arrSelectUom=null;
                            return;
                        }
                    }
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
//                    arrSelectUom=null;
                    refreshGridCM();
                }
            }
        } catch (Exception e) {
            WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgDeleteError"));
            //showErrorFromException(e, Sys_OrganizationBean.class.getName() + ".onDeleteOrganizationEnd()");
        }
    }

    public void onCloseUomDetail(CloseEvent event) {
        refreshGridCM();
    }

    private void refreshGridCM() {
        arrSelectUom=null;
        lstS_Uom = null;
        getDsS_UomForGrid_Reload();
    }

    private boolean checkSelectionCM() {
        if (arrSelectUom != null) {
            if (arrSelectUom.length > 0) {
                return true;
            }
        }
        //Thong bao chon hang
        WorkingContext.showMessages(enumMessageMode.eWarn, null,
                m_rfCommon.getMessage("msgNotCheckRecord"));
        return false;
    }

    public void SaveListUomToSession(List<S_Uom_Exchange> lst) {
        try {
            if (lst != null) {
                List<S_Uom_ExchangePK> listID = new ArrayList<S_Uom_ExchangePK>();
                for (int i = 0; i
                        < lst.size(); i++) {
                    listID.add(lst.get(i).getId());
                }
                WorkingContext.setSessionValue(WorkingContext.LIST_S_UOM_EXCHANGE, listID);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    public void onOpenUomExchangeCmdClick(ActionEvent ev) {
        SaveListUomToSession(lstS_Uom);
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Properties">
    public S_Uom_Exchange[] getArrSelectUom() {
        return arrSelectUom;
    }

    public void setArrSelectUom(S_Uom_Exchange[] arrSelectUom) {
        this.arrSelectUom = arrSelectUom;
    }

    public String getM_sDeleteMsg() {
        return m_sDeleteMsg;
    }

    public void setM_sDeleteMsg(String m_sDeleteMsg) {
        this.m_sDeleteMsg = m_sDeleteMsg;
    }

    //}}</editor-fold>
    @Override
    protected void setPageDetailInfo(enumResultSetPosition resultSetPosition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void resetInputRequire() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

class S_Uom_ExchangeDataModel extends ListDataModel<S_Uom_Exchange> implements SelectableDataModel<S_Uom_Exchange> {

    public S_Uom_ExchangeDataModel() {
    }

    public S_Uom_ExchangeDataModel(List<S_Uom_Exchange> data) {
        super(data);
    }

    @Override
    public String getRowKey(S_Uom_Exchange t) {
        return Tools.fStandardPdataTableID(t.getId().getIdStr());
    }

    @Override
    public S_Uom_Exchange getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

        List<S_Uom_Exchange> list = (List<S_Uom_Exchange>) getWrappedData();

        for (S_Uom_Exchange lst : list) {
            if (Tools.fStandardPdataTableID(lst.getId().getIdStr()).equals(rowKey)) {
                return lst;
            }
        }
        return null;
    }
}
