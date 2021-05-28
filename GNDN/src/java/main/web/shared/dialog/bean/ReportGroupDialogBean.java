/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.web.shared.dialog.bean;

import main.ContextResources.EjbContext;
import main.remote.shared.report.IReportConfigRemote;
import main.web.common.bean.WorkingContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectBoolean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import main.entity.shared.report.S_Report_Group;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author hanv
 */
@ManagedBean
@ViewScoped
public class ReportGroupDialogBean implements Serializable{
    ExternalContext externalContext;
    String display = "";
    private DataTable dtOp_Meter;
    private List<S_Report_Group> lstReportGroup;
    private IReportConfigRemote m_IReportConfigRemote;
    private boolean m_bInited;
    private String m_sInitFilter = "";
    private boolean m_visibleChkAll;
    private String m_siteID;
    private boolean m_isPostback;

    /** Creates a new instance of OpMeterTabGroupDialog */
    public ReportGroupDialogBean() {
        WorkingContext.resetDialogObjSelect();
        m_visibleChkAll = true;
        externalContext = FacesContext.getCurrentInstance().getExternalContext();
        if (externalContext.getRequestParameterMap().containsKey("display")) {
            display = (String) externalContext.getRequestParameterMap().get("display");
            if (display.equals("one")) {
                m_visibleChkAll = false;
            }
        }
        if (externalContext.getRequestParameterMap().containsKey("siteid")) {
            m_siteID = (String) externalContext.getRequestParameterMap().get("siteid");
        }
        if (!m_bInited) {
            String s;
            if (externalContext.getRequestParameterMap().containsKey("id")) {
                s = (String) externalContext.getRequestParameterMap().get("id");
                m_sInitFilter = s;
            }
        }
        m_bInited = true;
        if (lstReportGroup == null) {
            lstReportGroup = getIReportConfigRemote().getLstRptGroup(WorkingContext.getOrganizationCurrent());
            
        }
    }

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

    public IReportConfigRemote getIReportConfigRemote() {
        try {
            if (m_IReportConfigRemote == null) {
                m_IReportConfigRemote = (IReportConfigRemote) EjbContext.getLocalEJBRemote(IReportConfigRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();
            }
        } catch (Exception e) {
            return null;
        }
        return m_IReportConfigRemote;
    }

    public String getInitFilter() {
        return m_sInitFilter;
    }

    public void setInitFilter(String sInitFilter) {
        this.m_sInitFilter = sInitFilter;
    }

    public List getLstReportGroup() {
        return lstReportGroup;
    }

    public DataTable getDtOp_Meter() {
        return dtOp_Meter;
    }

    public void setDtOp_Meter(DataTable dtOp_Meter) {
        this.dtOp_Meter = dtOp_Meter;
    }

    public void updateCheckAllOp_Meter(AjaxBehaviorEvent event) {
        onCheckAllChange(null);
    }

    public void updateReportGroup(AjaxBehaviorEvent event) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            UISelectBoolean selectBoolean = (UISelectBoolean) event.getComponent();
            dtOp_Meter = (DataTable) selectBoolean.getParent().getParent();
//            //Lấy vị trí
//            String temps[] = selectBoolean.getClientId().split("\\:");
//            if (temps.length > 2) {
//                int rowIndex = Integer.valueOf(temps[2]);
//                dtHazard.setRowIndex(rowIndex);
                S_Report_Group opm = (S_Report_Group) dtOp_Meter.getRowData();
//                hazard.setbChecked(!hazard.getbChecked());
//                S_Report_Group opm = lstReportGroup.get(rowIndex);
//                S_Report_Group opm = (S_Report_Group) getIReportConfigRemote().findById(WorkingContext.getRequestQueryString("optabgroupid"), S_Report_Group.class);
//                opm.setbChecked(opm.getbChecked());
                WorkingContext.setDialogObjSelect(opm, opm.getbChecked());
//                //Hien thi asset
//                //WorkingContext.setDialogObjSelect(hazard.getHazardid(), hazard.getbChecked());
//                List<Sf_Hazard> lst = (List<Sf_Hazard>) WorkingContext.getSessionValue("hazard");
//                for (Sf_Hazard obj : lst) {
//                    if (!obj.getHazardid().equals(hazard.getHazardid())) {
//                        obj.setbChecked(false);
//                    }
//                }
                if (display.equals("one")) {
                    for (S_Report_Group op : lstReportGroup) {
                        if (!op.getRptgroupid().equals(opm.getRptgroupid())) {
                            op.setbChecked(false);
                            WorkingContext.setDialogObjSelect(op,op.getbChecked());
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

    public static List<S_Report_Group> getSelected() {
        List<S_Report_Group> lst = new ArrayList<S_Report_Group>();
        lst = getSelectionOp_Meters();
        remove();
        return lst;
    }

    public static void remove() {
        WorkingContext.resetDialogObjSelect();
    }

    public static List<S_Report_Group> getSelectionOp_Meters() {
        List<S_Report_Group> lst = (List<S_Report_Group>) WorkingContext.getDialogObjSelect();
        return lst;
    }

    public void onCheckAllChange(AjaxBehaviorEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (lstReportGroup != null) {
            for (S_Report_Group obj : lstReportGroup) {
                obj.setbChecked(bCheckAll);
                WorkingContext.setDialogObjSelect(obj, obj.getbChecked());
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
