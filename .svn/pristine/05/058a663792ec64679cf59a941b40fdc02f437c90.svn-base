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
import main.entity.shared.system.S_Company;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class CompanyDialog implements Serializable {

    ExternalContext externalContext;
    String display = "";
    private DataTable dtCompany;
    private List<S_Company> lstCompany;
    private IQueryShareRemote m_IQueryRemote;
    private boolean m_bInited;
    private String m_sInitFilter = "";
    private boolean m_visibleChkAll;
    private boolean m_isPostback;

    public boolean getIsPostBack() {
        m_isPostback = isPostback();
        return m_isPostback;
    }

    public boolean isM_visibleChkAll() {
        return m_visibleChkAll;
    }

    public void setM_visibleChkAll(boolean m_visibleChkAll) {
        this.m_visibleChkAll = m_visibleChkAll;
    }

    /** Creates a new instance of CompanyDialog */
    public CompanyDialog() {
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
        if (lstCompany == null) {
            if (!isPostback()) {
                if (externalContext.getRequestParameterMap().containsKey("typeList")) {
                    String typeList = (String) externalContext.getRequestParameterMap().get("typeList");
                    if (typeList != null) {
                        lstCompany = getm_IQueryRemote().getAllS_CompanyByOrgIDAndTypeIDList(WorkingContext.getWorkingInfo(), typeList);
                    }
                } else {
                    lstCompany = getm_IQueryRemote().getAllS_CompanyByOrgID(WorkingContext.getWorkingInfo());
                }
            } else {
                lstCompany = getm_IQueryRemote().getAllS_CompanyByOrgID(WorkingContext.getWorkingInfo());
            }
        }
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
                    Logger.getLogger(CompanyDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                EjbContext.LoginEJB();
            }
            return m_IQueryRemote;
        } catch (Exception ex) {
            Logger.getLogger(CompanyDialog.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List getDsCompanyForGrid() {
        return lstCompany;
    }

    public DataTable getDtCompany() {
        return dtCompany;
    }

    public void setDtCompany(DataTable dtCompany) {
        this.dtCompany = dtCompany;
    }

    public void updateCheckAllCompany(AjaxBehaviorEvent event) {
        onCheckAllChange(null);
    }

    public void updateCompany(AjaxBehaviorEvent event) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            UISelectBoolean selectBoolean = (UISelectBoolean) event.getComponent();
            dtCompany = (DataTable) selectBoolean.getParent().getParent();
//            //Lấy vị trí
//            String temps[] = selectBoolean.getClientId().split("\\:");
//            if (temps.length > 2) {
//                int rowIndex = Integer.valueOf(temps[2]);
//                dtCompany.setRowIndex(rowIndex);
                S_Company company = (S_Company) dtCompany.getRowData();
//                company.setbChecked(!company.getbChecked());
//                S_Company company = lstCompany.get(rowIndex);
//                S_Company company = (S_Company) getm_IQueryRemote().findById(WorkingContext.getRequestQueryString("compid"), S_Company.class);
//                company.setbChecked(company.getbChecked());
                WorkingContext.setDialogObjSelect(company, company.getbChecked());
//                List<S_Company> lst = (List<S_Company>) WorkingContext.getSessionValue("company");
//                for (S_Company obj : lst) {
//                    if (!obj.getCompid().equals(company.getCompid())) {
//                        obj.setbChecked(false);
//                    }
//                }
                if (display.equals("one")) {
                    for (S_Company c : lstCompany) {
                        if (!c.getCompid().equals(company.getCompid())) {
                            c.setbChecked(false);
                            WorkingContext.setDialogObjSelect(c, c.getbChecked());
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

    public static List<S_Company> getSelectionCompanys() {
        List<S_Company> lst = (List<S_Company>) WorkingContext.getDialogObjSelect();
        return lst;
    }

    public static List<S_Company> getSelected() {
        List<S_Company> lst = getSelectionCompanys();
        remove();
        return lst;
    }

    public static void remove() {
        WorkingContext.resetDialogObjSelect();
    }

    public void onCheckAllChange(AjaxBehaviorEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (lstCompany != null) {
            //bCheckAll = Boolean.valueOf(event.getNewValue().toString());
            for (S_Company company : lstCompany) {
                company.setbChecked(bCheckAll);
                WorkingContext.setDialogObjSelect(company, company.getbChecked());
            }
        }
        facesContext.renderResponse();
    }
    private boolean bCheckAll = false;

    public boolean isbCheckAll() {
        return bCheckAll;
    }

    public void setbCheckAll(boolean bVal) {
        bCheckAll = bVal;
    }

    public boolean getbCheckAll() {
        return bCheckAll;
    }

    public boolean isPostback() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getRenderKit().getResponseStateManager().isPostback(facesContext);
    }
}
