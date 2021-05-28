package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.system.S_Company;
import main.remote.shared.system.ISysRemote;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.ResourcesFactory;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant;
import evnit.util.common.BaseConstant.enumMessageMode;
import evnit.util.common.Tools;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CloseEvent;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author LanTH
 */
@ManagedBean
@ViewScoped
public class Sys_CompBean extends BasePageCommon implements Serializable {

    private ISysRemote iSys = null;
    private List<S_Company> m_LstComp;
    private S_Company[] arrSelectComp;
    private boolean m_bInited;
    private String m_sDeleteMsg;
    protected ResourcesFactory m_rf;

    //{{<editor-fold defaultstate="collapsed" desc="Contructor">   
    public Sys_CompBean() {
        m_rf = new ResourcesFactory("main/web/shared/system/prop/SystemProp");
        if (!m_bInited) {
            getAllS_Company();
            //DataTable grdData = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("formMain:dtCondition");
        }
        m_bInited = true;
    }

    //Lấy danh sách các công ty đối tác hiển thị lên grid
    public List<S_Company> getAllS_Company() {
        try {
            m_LstComp = getISysRemote().getAllS_Company(WorkingContext.getWorkingInfo());
//            SaveListCompanyToSession(m_LstComp);
            return m_LstComp;
        } catch (Exception e) {
            showErrorFromException(e, Sys_CompBean.class.getName() + ".getAllCompany()");
            e.printStackTrace();
            return null;
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Setup Organization">
    public void onEditCompany(ActionEvent event) {
        try {
            if (checkSelectionCM()) {
                String compId=arrSelectComp[0].getCompid();
//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "Edit");
//                context.addCallbackParam("compid", compId);
                
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "Edit");
                PrimeFaces.current().ajax().addCallbackParam("compid", compId);
                
                List<String> lstID = new ArrayList<String>();
                //lstID.add(String.valueOf(0));
                for (int i = 0; i < arrSelectComp.length; i++) {
                    S_Company b = arrSelectComp[i];
                    if (b.getCompid().equals(compId)) //Gán chỉ số đang chọn
                    {
                        lstID.add(0, compId);
                    } else {
                        lstID.add(b.getCompid());
                    }
                }
                WorkingContext.setSessionValue(WorkingContext.LIST_S_COMPANY, lstID);
            }
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_CompBean.class.getName() + ".onEditCompany()");
        }
    }

    public void onDeleteCompanyStart(ActionEvent event) {
        try {
            if (checkSelectionCM()) {
                m_sDeleteMsg = m_rfCommon.getMessage("msgDlgDeleteComp");
                m_sDeleteMsg = m_sDeleteMsg.replaceAll("@count", String.valueOf(arrSelectComp.length));
                if (arrSelectComp.length == 1) {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", " ('" + arrSelectComp[0].getCompid() + "')");
                } else {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", "");
                }

//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "Delete");
//                context.addCallbackParam("compid", arrSelectComp[0].getCompid());
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "Delete");
                PrimeFaces.current().ajax().addCallbackParam("compid", arrSelectComp[0].getCompid());
            }
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_CompBean.class.getName() + ".onDeleteCompany()");
        }
    }

    public void onDeleteCompanyEnd(ActionEvent event) {
        try {
            if (getISysRemote() != null) {
                if (arrSelectComp != null) {
                    String pmID = getLstCOMPIDChooseForURL(true);
                    if (pmID == null || pmID.isEmpty()) {
                        return;
                    }
                    for (int i = 0; i < arrSelectComp.length; i++) {
                        //if (!getISysRemote().delete(arrSelectComp[i].getCompid(),S_Company.class))
                        if (!getISysRemote().deleteCompany(arrSelectComp[i].getCompid())) {
                            WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgDeleteError"));
                            //showError(getISysRemote().getLastActionInfo(), S_Organization.class.getName() + ".onDeleteOrganizationEnd()");
                            if (i > 0)
                                arrSelectComp=null;
                            return;
                        }
                    }
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    arrSelectComp=null;
                    refreshGridCM();
                }
            }
        } catch (Exception e) {
            WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgDeleteError"));
            //showErrorFromException(e, Sys_OrganizationBean.class.getName() + ".onDeleteOrganizationEnd()");
        }
    }

    public void onCloseCompanyDetail(CloseEvent event) {
        refreshGridCM();
    }

    private void refreshGridCM() {
        m_LstComp=null;
        arrSelectComp=null;
        getAllS_Company();
//        DataTable dataTable = (DataTable) WorkingContext.findJSFComponent("formMain:dtCondition",false);
//        if(dataTable!=null)
    }

    private boolean checkSelectionCM() {
        if (arrSelectComp != null) {
            if (arrSelectComp.length > 0) {
                return true;
            }
        }
        //Thong bao chon hang
        WorkingContext.showMessages(enumMessageMode.eWarn, null,
                m_rfCommon.getMessage("msgNotCheckRecord"));
        return false;
    }

    public void SaveListCompanyToSession(List<S_Company> lst) {
        try {
            if (lst != null) {
                List<String> listID = new ArrayList<String>();
                for (int i = 0; i < lst.size(); i++) {
                    listID.add(lst.get(i).getCompid());
                }
                WorkingContext.setSessionValue(WorkingContext.LIST_S_COMPANY, listID);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void onOpenCompCmdClick(ActionEvent ev) {
        SaveListCompanyToSession(m_LstComp);
    }

    protected String getLstCOMPIDChooseForURL(boolean bOnlyEditRight) {
        //ArrayList<S_Dept> lstKey = (ArrayList<S_Dept>) WorkingContext.getDialogObjSelect();
        if (arrSelectComp == null) {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rfCommon.getMessage("msgNotCheckRecord"));
            return null;
        }

        //Lưu mảng ID vào danh sách
        String pmID = "", pmIDNot = "";
        try {
            for (int i = 0; i < arrSelectComp.length; i++) {
                if (bOnlyEditRight) {
                    if (checkRightEditOrg(arrSelectComp[i].getOrgid())) {
                        if (!pmID.isEmpty()) {
                            pmID += ",";
                        }
                        pmID += arrSelectComp[i].getCompid();
                    } else {
                        if (!pmIDNot.isEmpty()) {
                            pmIDNot += ",";
                        }
                        pmIDNot += arrSelectComp[i].getCompid();
                    }
                } else {
                    if (!pmID.isEmpty()) {
                        pmID += ",";
                    }
                    pmID += arrSelectComp[i].getCompid();
                }

            }
            if (!pmIDNot.isEmpty()) {
                WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rf.getMessage("MsgORGIDNotRight"));
            }
            return pmID;
        } catch (Exception ex) {
            //throw ex;
            showErrorFromException(ex, Sys_CompBean.class.getName() + ".getLstCOMPIDChooseForURL()");
        }
        return null;
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

    public S_Company[] getArrSelectCompany() {
        return arrSelectComp;
    }

    public void setArrSelectCompany(S_Company[] arrSelectComp) {
        this.arrSelectComp = arrSelectComp;
    }

    public String getM_sDeleteMsg() {
        return m_sDeleteMsg;
    }

    public void setM_sDeleteMsg(String m_sDeleteMsg) {
        this.m_sDeleteMsg = m_sDeleteMsg;
    }

    //}}</editor-fold>
    public S_CompanyDataModel getS_CompanyDataModel() {
//        getAllS_Company();
        if(m_LstComp!=null && m_LstComp.size()>0){
            return new S_CompanyDataModel(m_LstComp);
        }
        return null;
    }
}

class S_CompanyDataModel extends ListDataModel<S_Company> implements SelectableDataModel<S_Company> {

    public S_CompanyDataModel() {
    }

    public S_CompanyDataModel(List<S_Company> data) {
        super(data);
    }

    @Override
    public String getRowKey(S_Company t) {
        return Tools.fStandardPdataTableID(t.getCompid());
    }

    @Override
    public S_Company getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

        List<S_Company> list = (List<S_Company>) getWrappedData();

        for (S_Company lst : list) {
            if (Tools.fStandardPdataTableID(lst.getCompid()).equals(rowKey)) {
                return lst;
            }
        }
        return null;
    }
}
