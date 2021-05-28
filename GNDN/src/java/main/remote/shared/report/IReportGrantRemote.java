/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.remote.shared.report;



import main.remote.shared.common.ICommon;

import java.util.List;
import javax.ejb.Local;
import main.entity.shared.admin.Q_Role;
import main.entity.shared.admin.Q_User;
import main.entity.shared.report.Q_PQRPort_Role;
import main.entity.shared.report.Q_PQRPort_User;
import main.entity.shared.report.S_Report;
import main.entity.shared.report.S_Report_Group;


/**
 *
 * @author NAMCV
 */
@Local
public interface IReportGrantRemote extends ICommon{
    public List<Q_PQRPort_Role> getLstRoleGrantReport(S_Report_Group sreport);
    public List<Q_PQRPort_Role> getLstRoleGrantReport(S_Report sreport);
    public List<Q_PQRPort_User> getLstUserGrantReport(S_Report_Group suser);
    public List<Q_PQRPort_User> getLstUserGrantReport(S_Report suser);
    public List<Q_User> getAllQ_UserByOrg(java.lang.String orgid);
    public List<Q_Role> getAllQ_RoleByOrg(java.lang.String orgid);
    public List<S_Report_Group> getAllS_Report_Group(int i);
    public List<S_Report> getListReportByGroupID(String sGroup, String user);
    public List<S_Report> getListReportByGroupID(String sGroup);
    public List<S_Report_Group> getListReportGroupByOrgID(String sOrgId, String user);
    public List<S_Report_Group> getListReportGroupChild(String string,String suser, String surl);
    public List<S_Report_Group> getLstReportGroupByUser(String suser);
    public List<S_Report_Group> getLstReportGroupByParent(String sdataParent, String suser);
    public List<S_Report_Group> getListReportGroupRoot(String orgid);
    public List<S_Report_Group> getListReportChild(S_Report_Group sparent);
        
    public void delPQGranUser(String quser);
    public List<Q_PQRPort_User> getLstPQGrantReport(String suser);
    public void inPQGranUser(Q_PQRPort_User quser);
    public List<S_Report> getListReportByGroupIDView(String sgroup, String suser);
    
   
    
}
