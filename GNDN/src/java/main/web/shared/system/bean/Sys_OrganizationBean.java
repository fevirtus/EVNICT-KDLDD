package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.system.S_Organization;
import main.remote.shared.system.ISysRemote;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.WorkingContext;
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
public class Sys_OrganizationBean extends BasePageCommon implements Serializable {

    private ISysRemote iSys = null;
    private List<S_Organization> m_LstOrganization;
    private S_Organization[] arrSelectOrganization;
    private boolean m_bInited;
    private String m_sDeleteMsg;

    //{{<editor-fold defaultstate="collapsed" desc="Contructor">   
    public Sys_OrganizationBean() {
        if (!m_bInited) {
            getAllS_Organization();
            //DataTable grdData = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("formMain:dtCondition");
        }
        m_bInited = true;
    }

    //Lấy danh sách các đơn vị hiển thị lên grid
    public List<S_Organization> getAllS_Organization() {
        try {
            m_LstOrganization = getISysRemote().getAllS_Organization();
//            SaveListOrganizationToSession(m_LstOrganization);
            return m_LstOrganization;
        } catch (Exception e) {
            showErrorFromException(e, Sys_OrganizationBean.class.getName() + ".getAllOrganization()");
            e.printStackTrace();
            return null;
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Setup Organization">
    public void onEditOrganization(ActionEvent event) {
        try {
            if (checkSelectionCM()) {
                String orgId=arrSelectOrganization[0].getOrgid();
//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "Edit");
//                context.addCallbackParam("orgid", orgId);
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "Edit");
                PrimeFaces.current().ajax().addCallbackParam("orgid", orgId);
                
                
                 List<String> lstID = new ArrayList<String>();
                //lstID.add(String.valueOf(0));
                for (int i = 0; i < arrSelectOrganization.length; i++) {
                    S_Organization b = arrSelectOrganization[i];
                    if (b.getOrgid().equals(orgId)) //Gán chỉ số đang chọn
                    {
                        lstID.add(0, orgId);
                    } else {
                        lstID.add(b.getOrgid());
                    }
                }
                WorkingContext.setSessionValue(WorkingContext.LIST_S_ORGANIZATION, lstID);
            }
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_OrganizationBean.class.getName() + ".onSiteOrganization()");
        }
    }

    public void onDeleteOrganizationStart(ActionEvent event) {
        try {
            if (checkSelectionCM()) {
                m_sDeleteMsg = m_rfCommon.getMessage("msgDlgDeleteOrg");
                m_sDeleteMsg = m_sDeleteMsg.replaceAll("@count", String.valueOf(arrSelectOrganization.length));
                if (arrSelectOrganization.length == 1) {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", " ('" + arrSelectOrganization[0].getOrgid() + "')");
                } else {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", "");
                }

//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "Delete");
//                context.addCallbackParam("orgid", arrSelectOrganization[0].getOrgid());
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "Delete");
                PrimeFaces.current().ajax().addCallbackParam("orgid", arrSelectOrganization[0].getOrgid());
                
            }
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_OrganizationBean.class.getName() + ".onDeleteOrganization()");
        }
    }

    public void onDeleteOrganizationEnd(ActionEvent event) {
        try {
            if (getISysRemote() != null) {
                if (arrSelectOrganization != null) {
                    for (int i = 0; i < arrSelectOrganization.length; i++) {
                        //if (!getISysRemote().delete(arrSelectOrganization[i].getOrgid(),S_Organization.class))
                        if (!getISysRemote().deleteOrganization(arrSelectOrganization[i].getOrgid())) {
                            WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgDeleteError"));
                            //showError(getISysRemote().getLastActionInfo(), S_Organization.class.getName() + ".onDeleteOrganizationEnd()");
                            if (i > 0)
                                arrSelectOrganization=null;//reset lại
                            return;
                        }
                    }
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    arrSelectOrganization=null;//reset lại
                    refreshGridCM();
                }
            }
        } catch (Exception e) {
            WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgDeleteError"));
            //showErrorFromException(e, Sys_OrganizationBean.class.getName() + ".onDeleteOrganizationEnd()");
        }
    }

    public void onCloseOrganizationDetail(CloseEvent event) {
        refreshGridCM();
    }

    private void refreshGridCM() {
        arrSelectOrganization=null;
        m_LstOrganization=null;
        getAllS_Organization();
//        DataTable dataTable = (DataTable) WorkingContext.findJSFComponent("formMain:dtCondition",false);
//        if(dataTable!=null)
    }

    private boolean checkSelectionCM() {
        if (arrSelectOrganization != null) {
            if (arrSelectOrganization.length > 0) {
                return true;
            }
        }
        //Thong bao chon hang
        WorkingContext.showMessages(enumMessageMode.eWarn, null,
                m_rfCommon.getMessage("msgNotCheckRecord"));
        return false;
    }

    public void SaveListOrganizationToSession(List<S_Organization> lst) {
        try {
            if (lst != null) {
                List<String> listID = new ArrayList<String>();
                for (int i = 0; i < lst.size(); i++) {
                    listID.add(lst.get(i).getOrgid());
                }
                WorkingContext.setSessionValue(WorkingContext.LIST_S_ORGANIZATION, listID);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    public void onOpenOrgCmdClick(ActionEvent ev) {
        SaveListOrganizationToSession(m_LstOrganization);
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

    public S_Organization[] getArrSelectOrganization() {
        return arrSelectOrganization;
    }

    public void setArrSelectOrganization(S_Organization[] arrSelectOrganization) {
        this.arrSelectOrganization = arrSelectOrganization;
    }

    public String getM_sDeleteMsg() {
        return m_sDeleteMsg;
    }

    public void setM_sDeleteMsg(String m_sDeleteMsg) {
        this.m_sDeleteMsg = m_sDeleteMsg;
    }

    public int getCountMonitorSelected() {
        if (arrSelectOrganization != null) {
            return arrSelectOrganization.length;
        }
        return 0;
    }

    //}}</editor-fold>
    public S_OrganizationDataModel getS_OrganizationDataModel() {
//        getAllS_Organization();
        if(m_LstOrganization!=null && m_LstOrganization.size()>0){
            return new S_OrganizationDataModel(m_LstOrganization);
        }else{
            return null;
        }
    }
}

class S_OrganizationDataModel extends ListDataModel<S_Organization> implements SelectableDataModel<S_Organization> {

    public S_OrganizationDataModel() {
    }

    public S_OrganizationDataModel(List<S_Organization> data) {
        super(data);
    }

    @Override
    public String getRowKey(S_Organization t) {
        return Tools.fStandardPdataTableID(t.getOrgid());
    }

    @Override
    public S_Organization getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

        List<S_Organization> list = (List<S_Organization>) getWrappedData();

        for (S_Organization lst : list) {
            if (Tools.fStandardPdataTableID(lst.getOrgid()).equals(rowKey)) {
                return lst;
            }
        }
        return null;
    }
}
