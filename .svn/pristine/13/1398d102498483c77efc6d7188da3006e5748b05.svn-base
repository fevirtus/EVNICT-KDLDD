/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.system.S_Uom;
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
public class Sys_UomBean extends BasePageBean implements Serializable {

    private List<S_Uom> lstS_Uom;
    private S_UomDataModel m_uomDataModel;
    private static ISysRemote m_ISysRemote;
    private S_Uom[] selectedUom;
    private S_Uom[] arrSelectUom;
    private boolean m_bInited;
    private String m_sDeleteMsg;

    /**
     * Creates a new instance of Sys_UomBean
     */
    public S_Uom[] getSelectedUom() {
        return selectedUom;
    }

    public void setSelectedUom(S_Uom[] selectedUom) {
        this.selectedUom = selectedUom;
    }

    public DataTable getDtUom() {
        return (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("formMain:dtUom");
    }

    public Sys_UomBean() {
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

    public List<S_Uom> getDsS_UomForGrid_Reload() {
        try {
            lstS_Uom = getISysRemote().getAllS_Uom();
            //SaveListKpiToSession(m_lstKpi); //ThảoDD sửa: khi gọi nút open mới gọi tới hàm này
            return lstS_Uom;
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_UomBean.class.getName() + ".getDsS_UomForGrid1()");
            return null;
        }
    }

    public S_UomDataModel getDsS_UomForGrid() {

//        if (m_uomDataModel != null) {
//            return m_uomDataModel;
//        }
//
//        try {
//            lstS_Uom = getISysRemote().getAllS_Uom();
//            //SaveListUomToSession(lstS_Uom);
//            m_uomDataModel = new S_UomDataModel(lstS_Uom);
//        } catch (Exception e) {
//            showErrorFromException(e, Sys_UomBean.class.getName() + ".getAllUom()");
//            e.printStackTrace();
//        }
        if (lstS_Uom != null && lstS_Uom.size() > 0) {
            m_uomDataModel = new S_UomDataModel(lstS_Uom);
        } else {
            m_uomDataModel = null;
        }
        return m_uomDataModel;
    }

    //{{<editor-fold defaultstate="collapsed" desc="Setup Organization">
    public void onEditUom(ActionEvent event) {
        try {
            if (checkSelectionCM()) {
                String uomId = arrSelectUom[0].getUomid();
//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "Edit");
//                context.addCallbackParam("uomid", uomId);
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "Edit");
                PrimeFaces.current().ajax().addCallbackParam("uomid", uomId);

                List<String> lstID = new ArrayList<String>();
                //lstID.add(String.valueOf(0));
                for (int i = 0; i < arrSelectUom.length; i++) {
                    S_Uom b = arrSelectUom[i];
                    if (b.getUomid().equals(uomId)) //Gán chỉ số đang chọn
                    {
                        lstID.add(0, uomId);
                    } else {
                        lstID.add(b.getUomid());
                    }
                }
                WorkingContext.setSessionValue(WorkingContext.LIST_S_UOM, lstID);
            }

        } catch (Exception ex) {
            showErrorFromException(ex, Sys_UomBean.class.getName() + ".onEditUom()");
        }
    }

    public void onDeleteUomStart(ActionEvent event) {
        try {
            if (checkSelectionCM()) {
                m_sDeleteMsg = m_rfCommon.getMessage("msgDlgDeleteUom");
                m_sDeleteMsg = m_sDeleteMsg.replaceAll("@count", String.valueOf(arrSelectUom.length));
                if (arrSelectUom.length == 1) {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", " ('" + arrSelectUom[0].getUomid() + "')");
                } else {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", "");
                }
//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "Delete");
//                context.addCallbackParam("uomid", arrSelectUom[0].getUomid());
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "Delete");
                PrimeFaces.current().ajax().addCallbackParam("uomid", arrSelectUom[0].getUomid());
            }

        } catch (Exception ex) {
            showErrorFromException(ex, Sys_UomBean.class.getName() + ".onDeleteUom()");
        }
    }

    public void onDeleteUomEnd(ActionEvent event) {
        try {
            if (getISysRemote() != null) {
                if (arrSelectUom != null) {
                    for (int i = 0; i
                            < arrSelectUom.length; i++) {
                        //if (!getISysRemote().delete(arrSelectSite[i].getSiteid(),S_Site.class))
                        if (getISysRemote().deleteUom(arrSelectUom[i].getUomid()) <= 0) {
                            WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgDeleteError"));
                            //showError(getISysRemote().getLastActionInfo(), S_Organization.class.getName() + ".onDeleteOrganizationEnd()");
                            if (i > 0) {
                                arrSelectUom = null;
                            }
                            return;
                        }
                    }
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    arrSelectUom = null;
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
        m_uomDataModel = null;
        lstS_Uom = null;
        arrSelectUom = null; //ThảoDD sửa: lệnh quan trọng trước khi refresh lại, nếu không sẽ bị lỗi null và lật trang không ra được dữ liệu
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

    public void SaveListUomToSession(List<S_Uom> lst) {
        try {
            if (lst != null) {
                List<String> listID = new ArrayList<String>();
                for (int i = 0; i
                        < lst.size(); i++) {
                    listID.add(lst.get(i).getUomid());
                }
                WorkingContext.setSessionValue(WorkingContext.LIST_S_UOM, listID);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void onOpenDtail(ActionEvent ae) {
        SaveListUomToSession(lstS_Uom);
    }

    //}}</editor-fold>
    //{{<editor-fold defaultstate="collapsed" desc="Properties">
    public S_Uom[] getArrSelectUom() {
        return arrSelectUom;
    }

    public void setArrSelectUom(S_Uom[] arrSelectUom) {
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

class S_UomDataModel extends ListDataModel<S_Uom> implements SelectableDataModel<S_Uom> {

    public S_UomDataModel() {
    }

    public S_UomDataModel(List<S_Uom> data) {
        super(data);
    }

    @Override
    public String getRowKey(S_Uom t) {
        return Tools.fStandardPdataTableID(t.getUomid());
    }

    @Override
    public S_Uom getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

        List<S_Uom> list = (List<S_Uom>) getWrappedData();

        for (S_Uom lst : list) {
            if (Tools.fStandardPdataTableID(lst.getUomid()).equals(rowKey)) {
                return lst;
            }
        }
        return null;
    }
}
