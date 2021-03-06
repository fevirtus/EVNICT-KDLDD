package main.web.shared.system.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.system.S_Dept;
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
public class Sys_DeptBean extends BasePageCommon implements Serializable {

    private ISysRemote iSys = null;
    private List<S_Dept> m_LstDept;
    private S_Dept[] arrSelectDept;
    private boolean m_bInited;
    private String m_sDeleteMsg;
    protected ResourcesFactory m_rf;

    //{{<editor-fold defaultstate="collapsed" desc="Contructor">   
    public Sys_DeptBean() {
        m_rf = new ResourcesFactory("main/web/shared/system/prop/SystemProp");
        if (!m_bInited) {
            getAllS_Dept();
            //DataTable grdData = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("formMain:dtCondition");
        }
        m_bInited = true;
    }

    //Lấy danh sách các đơn vị hiển thị lên grid
    public List<S_Dept> getAllS_Dept() {
        try {
            m_LstDept = getISysRemote().getAllS_Dept(WorkingContext.getWorkingInfo());
//            SaveListDeptToSession(m_LstDept);
            return m_LstDept;
        } catch (Exception e) {
            showErrorFromException(e, Sys_DeptBean.class.getName() + ".getAllDept()");
            e.printStackTrace();
            return null;
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Setup Organization">
    public void onEditDept(ActionEvent event) {
        try {
            if (checkSelectionCM()) {
                String deptId=arrSelectDept[0].getDeptid();
//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "Edit");
//                context.addCallbackParam("deptid", deptId);
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "Edit");
                PrimeFaces.current().ajax().addCallbackParam("deptid", deptId);

                
                List<String> lstID = new ArrayList<String>();
                //lstID.add(String.valueOf(0));
                for (int i = 0; i < arrSelectDept.length; i++) {
                    S_Dept b = arrSelectDept[i];
                    if (b.getDeptid().equals(deptId)) //Gán chỉ số đang chọn
                    {
                        lstID.add(0, deptId);
                    } else {
                        lstID.add(b.getDeptid());
                    }
                }
                WorkingContext.setSessionValue(WorkingContext.LIST_S_DEPT, lstID);
            }
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_DeptBean.class.getName() + ".onEditDept()");
        }
    }

    public void onDeleteDeptStart(ActionEvent event) {
        try {
            if (checkSelectionCM()) {
                m_sDeleteMsg = m_rfCommon.getMessage("msgDlgDeleteDept");
                m_sDeleteMsg = m_sDeleteMsg.replaceAll("@count", String.valueOf(arrSelectDept.length));
                if (arrSelectDept.length == 1) {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", " ('" + arrSelectDept[0].getDeptid() + "')");
                } else {
                    m_sDeleteMsg = m_sDeleteMsg.replaceAll("@desc", "");
                }

//                RequestContext context = RequestContext.getCurrentInstance();
//                context.addCallbackParam("Method", "Delete");
//                context.addCallbackParam("deptid", arrSelectDept[0].getDeptid());
                
                PrimeFaces.current().ajax().addCallbackParam("Method", "Delete");
                PrimeFaces.current().ajax().addCallbackParam("deptid", arrSelectDept[0].getDeptid());
            }
        } catch (Exception ex) {
            showErrorFromException(ex, Sys_DeptBean.class.getName() + ".onDeleteDept()");
        }
    }

    public void onDeleteDeptEnd(ActionEvent event) {
        try {
            if (getISysRemote() != null) {
                if (arrSelectDept != null) {
                    String pmID = getLstDEPTIDChooseForURL(true);
                    if (pmID == null || pmID.isEmpty()) {
                        return;
                    }
                    for (int i = 0; i < arrSelectDept.length; i++) {
                        //if (!getISysRemote().delete(arrSelectDept[i].getDeptid(),S_Dept.class))
                        if (!getISysRemote().deleteDept(arrSelectDept[i].getDeptid())) {
                            WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgDeleteError"));
                            //showError(getISysRemote().getLastActionInfo(), S_Organization.class.getName() + ".onDeleteOrganizationEnd()");
                            return;
                        }
                    }
                    WorkingContext.showMessages(enumMessageMode.eInfo, null, m_rfCommon.getMessage("msgDeleteSuccess"));
                    refreshGridCM();
                }
            }
        } catch (Exception e) {
            WorkingContext.showMessages(enumMessageMode.eError, null, m_rfCommon.getMessage("msgDeleteError"));
            //showErrorFromException(e, Sys_OrganizationBean.class.getName() + ".onDeleteOrganizationEnd()");
        }
    }

    public void onCloseDeptDetail(CloseEvent event) {
        refreshGridCM();
    }

    private void refreshGridCM() {
        m_LstDept=null;
        arrSelectDept=null;
        getAllS_Dept();
//        DataTable dataTable = (DataTable) WorkingContext.findJSFComponent("formMain:dtCondition",false);
//        if(dataTable!=null)
    }

    private boolean checkSelectionCM() {
        if (arrSelectDept != null) {
            if (arrSelectDept.length > 0) {
                return true;
            }
        }
        //Thong bao chon hang
        WorkingContext.showMessages(enumMessageMode.eWarn, null,
                m_rfCommon.getMessage("msgNotCheckRecord"));
        return false;
    }

    public void SaveListDeptToSession(List<S_Dept> lst) {
        try {
            if (lst != null) {
                List<String> listID = new ArrayList<String>();
                for (int i = 0; i < lst.size(); i++) {
                    listID.add(lst.get(i).getDeptid());
                }
                WorkingContext.setSessionValue(WorkingContext.LIST_S_DEPT, listID);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    public void onOpenDeptCmdClick(ActionEvent ev) {
        SaveListDeptToSession(m_LstDept);
    }

    protected String getLstDEPTIDChooseForURL(boolean bOnlyEditRight) {
        //ArrayList<S_Dept> lstKey = (ArrayList<S_Dept>) WorkingContext.getDialogObjSelect();
        if (arrSelectDept == null) {
            WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rfCommon.getMessage("msgNotCheckRecord"));
            return null;
        }

        //Lưu mảng ID vào danh sách
        String pmID = "", pmIDNot = "";
        try {
            for (int i = 0; i < arrSelectDept.length; i++) {
                if (bOnlyEditRight) {
                    if (checkRightEditOrg(arrSelectDept[i].getOrgid())) {
                        if (!pmID.isEmpty()) {
                            pmID += ",";
                        }
                        pmID += arrSelectDept[i].getDeptid();
                    } else {
                        if (!pmIDNot.isEmpty()) {
                            pmIDNot += ",";
                        }
                        pmIDNot += arrSelectDept[i].getDeptid();
                    }
                } else {
                    if (!pmID.isEmpty()) {
                        pmID += ",";
                    }
                    pmID += arrSelectDept[i].getDeptid();
                }

            }
            if (!pmIDNot.isEmpty()) {
                WorkingContext.showMessages(BaseConstant.enumMessageMode.eWarn, null, m_rf.getMessage("MsgORGIDNotRight"));
            }
            return pmID;
        } catch (Exception ex) {
            //throw ex;
            showErrorFromException(ex, Sys_DeptBean.class.getName() + ".getLstDEPTIDChooseForURL()");
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

    public S_Dept[] getArrSelectDept() {
        return arrSelectDept;
    }

    public void setArrSelectDept(S_Dept[] arrSelectDept) {
        this.arrSelectDept = arrSelectDept;
    }

    public String getM_sDeleteMsg() {
        return m_sDeleteMsg;
    }

    public void setM_sDeleteMsg(String m_sDeleteMsg) {
        this.m_sDeleteMsg = m_sDeleteMsg;
    }

    //}}</editor-fold>
    public S_DeptDataModel getS_DeptDataModel() {
//        getAllS_Dept();
        if(m_LstDept!=null && m_LstDept.size()>0)
            return new S_DeptDataModel(m_LstDept);
        return null;
    }
}

class S_DeptDataModel extends ListDataModel<S_Dept> implements SelectableDataModel<S_Dept> {

    public S_DeptDataModel() {
    }

    public S_DeptDataModel(List<S_Dept> data) {
        super(data);
    }

    @Override
    public String getRowKey(S_Dept t) {
        return Tools.fStandardPdataTableID(t.getDeptid());
    }

    @Override
    public S_Dept getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

        List<S_Dept> list = (List<S_Dept>) getWrappedData();

        for (S_Dept lst : list) {
            if (Tools.fStandardPdataTableID(lst.getDeptid()).equals(rowKey)) {
                return lst;
            }
        }
        return null;
    }
}
