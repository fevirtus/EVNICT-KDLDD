/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.dialog.bean;

import main.ContextResources.EjbContext;
import main.remote.shared.common.IQueryShareRemote;
import main.web.common.bean.WorkingContext;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectBoolean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import main.entity.shared.system.S_Uom;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class UomDialog implements Serializable {

    ExternalContext externalContext;
    String display = "";
    private String m_sInitFilter = "";
    private DataTable dtUom;
    private List<S_Uom> lstS_Uom;
    private IQueryShareRemote m_IQueryRemote;
    private boolean m_bInited;
    private boolean m_visibleChkAll;
    private boolean m_isPostback;
    /** Creates a new instance of UomDialog */
    public boolean getIsPostBack() {
        m_isPostback = isPostback();
        return m_isPostback;
    }
    public UomDialog() {
            WorkingContext.resetDialogObjSelect();
            m_visibleChkAll = true;
            externalContext = FacesContext.getCurrentInstance().getExternalContext();
            if (externalContext.getRequestParameterMap().containsKey("display")) {
                display = (String) externalContext.getRequestParameterMap().get("display");
                if (display.equals("one")) {
                    m_visibleChkAll = false;
                }
            }
            if (!m_bInited) {
                String s;
                if (externalContext.getRequestParameterMap().containsKey("id")) {
                    s = (String) externalContext.getRequestParameterMap().get("id");
                    m_sInitFilter = s;
                }
            }
            m_bInited = true;
            if (lstS_Uom == null) {
                lstS_Uom = getm_IQueryRemote().getAllS_Uom();
            }
    }

    public boolean isM_visibleChkAll() {
        return m_visibleChkAll;
    }

    public void setM_visibleChkAll(boolean m_visibleChkAll) {
        this.m_visibleChkAll = m_visibleChkAll;
    }

    public String getInitFilter() {
        return m_sInitFilter;
    }

    public void setInitFilter(String sInitFilter) {
        this.m_sInitFilter = sInitFilter;
    }

    public IQueryShareRemote getm_IQueryRemote() {
        try {
            if (m_IQueryRemote == null) {
                try {
                    m_IQueryRemote = (IQueryShareRemote) EjbContext.getLocalEJBRemote(IQueryShareRemote.class.getSimpleName());
                } catch (Exception ex) {
                    Logger.getLogger(UomDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                EjbContext.LoginEJB();
            }
            return m_IQueryRemote;
        } catch (Exception ex) {
            Logger.getLogger(UomDialog.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List getDsS_UomForGrid() {
        return lstS_Uom;
    }

    public DataTable getDtUom() {
        return dtUom;
    }

    public void setDtUom(DataTable dtUom) {
        this.dtUom = dtUom;
    }

    public void updateCheckAllUom(AjaxBehaviorEvent event) {
        onCheckAllChange(null);
    }

    public void updateUom(AjaxBehaviorEvent event) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            UISelectBoolean selectBoolean = (UISelectBoolean) event.getComponent();
            dtUom = (DataTable) selectBoolean.getParent().getParent();
            //Lấy vị trí
//            String temps[] = selectBoolean.getClientId().split("\\:");
//            if (temps.length > 2) {
//                int rowIndex = Integer.valueOf(temps[2]);
//                dtDept.setRowIndex(rowIndex);
                S_Uom uom = (S_Uom) dtUom.getRowData();
//                s_Dept.setbChecked(!s_Dept.getbChecked());
//                S_Uom uom = lstS_Uom.get(rowIndex);
//                S_Uom uom = (S_Uom) getm_IQueryRemote().findById(WorkingContext.getRequestQueryString("uomid"), S_Uom.class);
//                uom.setbChecked(uom.getbChecked());
                WorkingContext.setDialogObjSelect(uom, uom.getbChecked());
//                List<S_Dept> lst = (List<S_Dept>) WorkingContext.getSessionValue("dept");
//                for (S_Dept obj : lst) {
//                    if (!obj.getDeptid().equals(s_Dept.getDeptid())) {
//                        obj.setbChecked(false);
//                    }
//                }
                if (display.equals("one")) {
                    for (S_Uom s : lstS_Uom) {
                        if (!s.getUomid().equals(uom.getUomid())) {
                            s.setbChecked(false);
                            WorkingContext.setDialogObjSelect(s, s.getbChecked());
                        }
                        //dtHazard.getParent().clearInitialState();
                    }
                    facesContext.renderResponse();
                }
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static List<S_Uom> getSelectionUoms() {
        List<S_Uom> lst = (List<S_Uom>) WorkingContext.getDialogObjSelect();
        return lst;
    }

    public static List<S_Uom> getSelected() {
        List<S_Uom> lst = getSelectionUoms();
        remove();
        return lst;
    }

    public static void remove() {
        WorkingContext.resetDialogObjSelect();
    }

    public void onCheckAllChange(AjaxBehaviorEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (lstS_Uom != null) {
            //bCheckAll = Boolean.valueOf(event.getNewValue().toString());
            for (S_Uom uom : lstS_Uom) {
                uom.setbChecked(bCheckAll);
                WorkingContext.setDialogObjSelect(uom, uom.getbChecked());
            }
        }
        facesContext.renderResponse();
    }
    private boolean bCheckAll = false;

    public boolean isbCheckAll() {
        return bCheckAll;
    }

   public void setbCheckAll(boolean bVal) {
        bCheckAll=bVal;
    }

    public boolean getbCheckAll()
    {
        return bCheckAll;
    }

    public boolean isPostback() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getRenderKit().getResponseStateManager().isPostback(facesContext);
    }
}
