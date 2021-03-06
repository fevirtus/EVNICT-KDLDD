/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.report.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.admin.Q_Role;
import main.entity.shared.admin.Q_User;
import main.entity.shared.report.Q_PQRPort_Role;
import main.entity.shared.report.Q_PQRPort_User;
import main.entity.shared.report.S_Report;
import main.entity.shared.report.S_Report_Group;
import main.remote.shared.admin.IAdmin;
import main.web.common.bean.BasePageBean;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant;
import evnit.util.common.Tools;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.ListDataModel;
import main.remote.shared.report.IReportGrantRemote;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author NAMCV
 */
@ManagedBean
@ViewScoped
public class ReportGrantBean extends BasePageBean implements Serializable {

    private IReportGrantRemote iReport;
    private IAdmin iAdmin;
    private Q_User iUser;
    private boolean isGrant = false;
    private List<Q_PQRPort_User> lstQPqobjUser;
    private List<Q_PQRPort_Role> lstQPqobjRole;
    private List<Q_User> lstQUser;
    private List<Q_Role> lstRole;
    private String strReportId;
    private String strTypeId;

    public ReportGrantBean() {
        strReportId = WorkingContext.getRequestQueryString("ReportID");
        strTypeId = WorkingContext.getRequestQueryString("TypeID");

        loadGrant(strReportId);
    }

    private void loadGrant(String strReportId) {
        List<Q_PQRPort_User> templstQPqobjUser = null;
        List<Q_PQRPort_Role> templstQPqobjRole = null;
        if (strTypeId.equals("A")) {
            S_Report tmpReport = (S_Report) getIReportRemote().findById(strReportId, S_Report.class);
            templstQPqobjUser = getIReportRemote().getLstUserGrantReport(tmpReport);
            templstQPqobjRole = getIReportRemote().getLstRoleGrantReport(tmpReport);
        } else {
            S_Report_Group tmpGReport = (S_Report_Group) getIReportRemote().findById(strReportId, S_Report_Group.class);
            templstQPqobjUser = getIReportRemote().getLstUserGrantReport(tmpGReport);
            templstQPqobjRole = getIReportRemote().getLstRoleGrantReport(tmpGReport);
        }
        //S_Report_Group tmpReportGrp = (S_Report_Group) getIReportRemote().findById(strReportGrpId, S_Report_Group.class);
        iUser = (Q_User) getIReportRemote().findById(WorkingContext.getUserName(), Q_User.class);
        lstQPqobjUser = new ArrayList<Q_PQRPort_User>();

        lstQUser = getIReportRemote().getAllQ_UserByOrg(WorkingContext.getWorkingInfo().getOrgid());

        String tempUser = null;
        String tempRole = null;
        if (templstQPqobjUser != null) {
            for (Q_PQRPort_User item : templstQPqobjUser) {
                if (tempUser == null || !tempUser.equals(item.getQ_user().getUserid())) {
                    if (!item.getId().getObjid().equals(strReportId)) {
                        item.setInherit(true);
                    }
                    if (lstQUser != null) {
                        for (int j = 0; j < lstQUser.size(); j++) {
                            if (item.getId().getUserid().equals(lstQUser.get(j).getUserid())) {
                                if (lstQUser.get(j).getIsRoot() != null && lstQUser.get(j).getIsRoot()) {
                                    item.setInherit(true);
                                    item.setCreator(true);
                                    item.setRView(true);
                                    item.setRGrant(true);
                                }
                                lstQUser.remove(j);
                                break;
                            }
                        }
                    }
                    lstQPqobjUser.add(item);
                }
                tempUser = item.getQ_user().getUserid();
            }
        }

        Q_PQRPort_User tempQ_PQRPort_User;
        if (lstQUser != null) {
            for (int j = 0; j < lstQUser.size(); j++) {
                tempQ_PQRPort_User = new Q_PQRPort_User(lstQUser.get(j).getUserid(), strReportId, "G");
                if (lstQUser.get(j).getIsRoot() != null && lstQUser.get(j).getIsRoot()) {
                    tempQ_PQRPort_User.setInherit(true);
                    tempQ_PQRPort_User.setCreator(true);
                    tempQ_PQRPort_User.setRView(true);
                    tempQ_PQRPort_User.setRGrant(true);
                }
                tempQ_PQRPort_User.setQ_user(lstQUser.get(j));
                tempQ_PQRPort_User.setInherit(true);
                lstQPqobjUser.add(tempQ_PQRPort_User);
            }
        }
        lstQPqobjRole = new ArrayList<Q_PQRPort_Role>();        
        lstRole = getIReportRemote().getAllQ_RoleByOrg(WorkingContext.getWorkingInfo().getOrgid());
        if (templstQPqobjRole != null) {
            for (Q_PQRPort_Role item : templstQPqobjRole) {
                if (tempRole == null || !tempRole.equals(item.getQ_role().getRoleid())) {
                    if (!item.getId().getObjid().equals(strReportId)) {
                        item.setInherit(true);
                    }
                    if (lstRole != null) {
                        for (int j = 0; j < lstRole.size(); j++) {
                            if (item.getId().getRoleid().equals(lstRole.get(j).getRoleid())) {
                                lstRole.remove(j);
                                break;
                            }
                        }
                    }
                    lstQPqobjRole.add(item);

                }
                tempRole = item.getQ_role().getRoleid();
            }
        }
        Q_PQRPort_Role tempQ_PQRPort_Role;
        if (lstRole != null) {
            for (int j = 0; j < lstRole.size(); j++) {
                tempQ_PQRPort_Role = new Q_PQRPort_Role(lstRole.get(j).getRoleid(), strReportId, "A");
                tempQ_PQRPort_Role.setQ_role(lstRole.get(j));
                tempQ_PQRPort_Role.setInherit(true);
                lstQPqobjRole.add(tempQ_PQRPort_Role);

            }
        }
        //Sap xep lai list
        Collections.sort(lstQPqobjUser, new Comparator<Q_PQRPort_User>() {
            @Override
            public int compare(Q_PQRPort_User o1, Q_PQRPort_User o2) {
                if (o1.getQ_user().getUsername() != null && o2.getQ_user().getUsername() != null) {
                    return o1.getQ_user().getUsername().compareTo(o2.getQ_user().getUsername());
                } else {
                    return 0;
                }
            }
        });
        //Sap xep lai list
        Collections.sort(lstQPqobjRole, new Comparator<Q_PQRPort_Role>() {
            @Override
            public int compare(Q_PQRPort_Role o1, Q_PQRPort_Role o2) {
                if (o1.getQ_role().getRoledesc() != null && o1.getQ_role().getRoledesc() != null) {
                    return o1.getQ_role().getRoledesc().compareTo(o2.getQ_role().getRoledesc());
                } else {
                    return 0;
                }
            }
        });
        //Kiem tra quyen nguoi dung
        if (iUser != null && iUser.getIsRoot() != null && iUser.getIsRoot()) {
            isGrant = true;
        }
        if (!isGrant) {
            for (int i = 0; i < lstQPqobjUser.size(); i++) {
                if (lstQPqobjUser.get(i).getId().getUserid().equals(iUser.getUserid())) {
                    if (lstQPqobjUser.get(i).getRGrant() != null && lstQPqobjUser.get(i).getRGrant()) {
                        isGrant = true;
                    }
                    break;
                }
            }
        }
        if (!isGrant) {
            List<Q_Role> lstRoleUser = getIAdmin().getAllRolesOfUser(iUser.getUserid());
            for (int i = 0; i < lstQPqobjRole.size(); i++) {
                for (int j = 0; j < lstRoleUser.size(); j++) {
                    if (lstQPqobjRole.get(i).getId().getRoleid().equals(lstRoleUser.get(j).getRoleid())) {
                        if (lstQPqobjRole.get(i).getRGrant() != null && lstQPqobjRole.get(i).getRGrant()) {
                            isGrant = true;
                        }
                        break;
                    }
                }
            }
        }
    }

    public void onSave(ActionEvent event) {
        try {
            ///Luu thong tin phan quyen nguoi dung
            for (int i = 0; i < lstQPqobjUser.size(); i++) {
                if ((lstQPqobjUser.get(i).getInherit() != null && lstQPqobjUser.get(i).getInherit())) {
                    if (lstQPqobjUser.get(i).getId().getObjid().equals(strReportId)) {
                        getIReportRemote().delete(lstQPqobjUser.get(i).getId(), Q_PQRPort_User.class);
                    }
                } else {
                    if ((lstQPqobjUser.get(i).getQ_user().getIsRoot() != null && lstQPqobjUser.get(i).getQ_user().getIsRoot())) {
                    } else {
                        if (lstQPqobjUser.get(i).getId().getObjid().equals(strReportId) && getIReportRemote().findById(lstQPqobjUser.get(i).getId(), Q_PQRPort_User.class) != null) {
                            getIReportRemote().update(lstQPqobjUser.get(i));
                        } else {
                            lstQPqobjUser.get(i).getId().setObjid(strReportId);
                            if(strTypeId.equals("G")){
                                lstQPqobjUser.get(i).getId().setObjtypeid(strTypeId);
                            }
                            getIReportRemote().insert(lstQPqobjUser.get(i));
                        }
                    }

                }
            }
            //Luu thong tin tap quyen nguoi dung
            for (int i = 0; i < lstQPqobjRole.size(); i++) {
                if (lstQPqobjRole.get(i).getInherit() != null && lstQPqobjRole.get(i).getInherit()) {
                    if (lstQPqobjRole.get(i).getId().getObjid().equals(strReportId)) {
                        getIReportRemote().delete(lstQPqobjRole.get(i).getId(), Q_PQRPort_Role.class);
                    }
                } else {
                    if (lstQPqobjRole.get(i).getId().getObjid().equals(strReportId) && getIReportRemote().findById(lstQPqobjRole.get(i).getId(), Q_PQRPort_Role.class) != null) {
                        getIReportRemote().update(lstQPqobjRole.get(i));
                    } else {
                        lstQPqobjRole.get(i).getId().setObjid(strReportId);
                        getIReportRemote().insert(lstQPqobjRole.get(i));
                    }
                }

            }
            loadGrant(strReportId);
            WorkingContext.showMessages(evnit.util.common.BaseConstant.enumMessageMode.eInfo, null, "L??u d??? li???u th??nh c??ng");
        } catch (Exception e) {
            WorkingContext.showMessages(evnit.util.common.BaseConstant.enumMessageMode.eError, null, "C???p nh???t th???t bai");
        }
    }

    public IReportGrantRemote getIReportRemote() {
        try {
            if (iReport == null) {
                iReport = (IReportGrantRemote) EjbContext.getLocalEJBRemote(IReportGrantRemote.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login l???i khi g???i ejb
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportGrantBean.class.getName() + ".getIReportRemote()");
            return null;
        }
        return iReport;
    }

    public IAdmin getIAdmin() {
        try {
            if (iAdmin == null) {
                iAdmin = (IAdmin) EjbContext.getLocalEJBRemote(IAdmin.class.getSimpleName());
            } else {
                EjbContext.LoginEJB();//Login l???i khi g???i ejb
            }
        } catch (Exception ex) {
            showErrorFromException(ex, ReportGrantBean.class.getName() + ".getIAdmin()");
            return null;
        }
        return iAdmin;
    }

    public boolean getIsGrant() {
        return isGrant;
    }

    public void setIsGrant(boolean isGrant) {
        this.isGrant = isGrant;
    }

    public QPqRePortUserModel getLsQPqRePortUserModel() {
        try {
            if (lstQPqobjUser != null && lstQPqobjUser.size() > 0) {
                return new QPqRePortUserModel(lstQPqobjUser);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public QPqReportRoleModel getLsQPqReportRoleModel() {
        try {
            if (lstQPqobjRole != null && lstQPqobjRole.size() > 0) {
                return new QPqReportRoleModel(lstQPqobjRole);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void setPageDetailInfo(BaseConstant.enumResultSetPosition resultSetPosition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void resetInputRequire() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class QPqRePortUserModel extends ListDataModel<Q_PQRPort_User> implements SelectableDataModel<Q_PQRPort_User> {

    public QPqRePortUserModel() {
    }

    public QPqRePortUserModel(List<Q_PQRPort_User> data) {
        super(data);
    }

    @Override
    public String getRowKey(Q_PQRPort_User t) {
        return Tools.fStandardPdataTableID(t.getId().getUserid() + '/' + t.getId().getObjtypeid() + "/" + t.getId().getObjid());
    }

    @Override
    public Q_PQRPort_User getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

        List<Q_PQRPort_User> list = (List<Q_PQRPort_User>) getWrappedData();

        for (Q_PQRPort_User lst : list) {
            if (Tools.fStandardPdataTableID(lst.getId().getUserid() + '/' + lst.getId().getObjtypeid() + "/" + lst.getId().getObjid()).equals(rowKey)) {
                return lst;
            }
        }
        return null;
    }
}

class QPqReportRoleModel extends ListDataModel<Q_PQRPort_Role> implements SelectableDataModel<Q_PQRPort_Role> {

    public QPqReportRoleModel() {
    }

    public QPqReportRoleModel(List<Q_PQRPort_Role> data) {
        super(data);
    }

    @Override
    public String getRowKey(Q_PQRPort_Role t) {
        return Tools.fStandardPdataTableID(t.getId().getRoleid() + '/' + t.getId().getObjtypeid() + "/" + t.getId().getObjid());
    }

    @Override
    public Q_PQRPort_Role getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data

        List<Q_PQRPort_Role> list = (List<Q_PQRPort_Role>) getWrappedData();

        for (Q_PQRPort_Role lst : list) {
            if (Tools.fStandardPdataTableID(lst.getId().getRoleid() + '/' + lst.getId().getObjtypeid() + "/" + lst.getId().getObjid()).equals(rowKey)) {
                return lst;
            }
        }
        return null;
    }
}
