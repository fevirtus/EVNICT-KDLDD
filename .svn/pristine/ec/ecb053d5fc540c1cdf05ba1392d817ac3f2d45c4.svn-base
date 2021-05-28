/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.ejb.shared.report;


import main.entity.shared.admin.Q_Role;
import main.entity.shared.admin.Q_User;
import main.entity.shared.report.Q_PQRPort_Role;
import main.entity.shared.report.Q_PQRPort_User;
import main.entity.shared.report.S_Report;
import main.entity.shared.report.S_ReportTTPhutaiThang;
import main.entity.shared.report.S_ReportTTVHNgay;
import main.entity.shared.report.S_Report_Group;

import evnit.util.setting.DBSettings;
import java.util.Date;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import main.ejb.shared.common.BaseCommonBL;
import main.remote.shared.report.IReportGrantRemote;

/**
 *
 * @author NAMCV
 */
@Stateless
public class ReportGrantBL extends BaseCommonBL implements IReportGrantRemote {

    @Override
    public List<Q_PQRPort_Role> getLstRoleGrantReport(S_Report_Group sreport) {
        try {
            String s = "SELECT a.* FROM Q_PQRPORT_ROLE a WHERE a.OBJID = '" + sreport.getRptgroupid() + "'";
            Query query = entityManager.createNativeQuery(s, Q_PQRPort_Role.class);
            List<Q_PQRPort_Role> lst = query.getResultList();

            return lst;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Q_PQRPort_Role> getLstRoleGrantReport(S_Report sreport) {
        try {
            String s = "SELECT a.* FROM Q_PQRPORT_ROLE a WHERE a.OBJID = '" + sreport.getRptid() + "'";
            Query query = entityManager.createNativeQuery(s, Q_PQRPort_Role.class);
            List<Q_PQRPort_Role> lst = query.getResultList();

            return lst;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Q_PQRPort_User> getLstUserGrantReport(S_Report_Group suser) {
        try {
            //String s = "SELECT a.* FROM Q_PQRPORT_USER a WHERE a.OBJID = '" + suser.getRptgroupid() + "'";
            Query query = entityManager.createNativeQuery("{call PKG_REPORT_PQREPORT(?,?)}", Q_PQRPort_User.class);
            query.setParameter(1, suser.getRptgroupid());
            query.setParameter(2, suser.getOrgid());
            List<Q_PQRPort_User> lst = query.getResultList();

            return lst;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<S_Report_Group> getAllS_Report_Group(int i) {
        try {
            String s = "SELECT a.* FROM S_Report_Group a";
            Query query = entityManager.createNativeQuery(s, S_Report_Group.class);
//            query.setParameter(1, suser.getRptgroupid());
//            query.setParameter(2, suser.getOrgid());
            List<S_Report_Group> lst = query.getResultList();

            return lst;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Q_PQRPort_User> getLstUserGrantReport(S_Report suser) {
        try {
            String s = "SELECT a.* FROM Q_PQRPORT_USER a WHERE a.OBJID = '" + suser.getRptid() + "'";
            Query query = entityManager.createNativeQuery(s, Q_PQRPort_User.class);
            List<Q_PQRPort_User> lst = query.getResultList();

            return lst;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<S_Report_Group> getListReportGroupChild(String rptgroupid, String suser, String surl) {
        try {
            String sql = "";
            Query query;
            if (DBSettings.getEnumDBMode() == DBSettings.enumDBMode.SQLServer) {
                sql = "{call PKG_REPORT_GROUPCHILD(?,?,?)}";
                query = entityManager.createNativeQuery(sql, S_Report_Group.class);
                query.setParameter(1, rptgroupid);
                query.setParameter(2, suser);
                query.setParameter(3, surl);
            } else {
//                sql = "SELECT a.*\n"
//                        + "    FROM S_REPORT_GROUP a LEFT JOIN Q_PQRPORT_USER b ON a.RPTGROUPID = b.OBJID\n"
//                        + "   WHERE     a.RPTGROUPID_PARENT = ?\n"
//                        + "   and B.USERID = ?    \n"
//                        + "ORDER BY a.RPTGROUPORD";
                
                sql = "SELECT a.*\n"
                        + "    FROM S_REPORT_GROUP a"
                        + "   WHERE     a.RPTGROUPID_PARENT = ?\n"                        
                        + "ORDER BY a.RPTGROUPORD";
                query = entityManager.createNativeQuery(sql, S_Report_Group.class);
                query.setParameter(1, rptgroupid);
                query.setParameter(2, suser);
            }

            List<S_Report_Group> lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    @Override
    public List<S_Report> getListReportByGroupID(String sGroup
    ) {
        try {
            String sql = "SELECT a.* FROM S_REPORT a WHERE a.RPTGROUPID = ? ORDER BY rptord ";
            Query query = entityManager.createNativeQuery(sql, S_Report.class);
            query.setParameter(1, sGroup);
            List<S_Report> lst = query.getResultList();

            return lst;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<S_Report> getListReportByGroupID(String sGroup, String user
    ) {
        try {

            Query query;
            if (DBSettings.getEnumDBMode() == DBSettings.enumDBMode.SQLServer) {
                query = entityManager.createNativeQuery("{call PKG_REPORT_CHILD(?,?)}", S_Report.class);
            } else {
                String sql = "SELECT a.* FROM S_REPORT a INNER JOIN Q_PQRPORT_USER b\n"
                        + "					ON a.RPTID = b.OBJID\n"
                        + "				WHERE a.RPTGROUPID = ? AND b.USERID = ?";
                query = entityManager.createNativeQuery(sql, S_Report.class);
            }
            query.setParameter(1, sGroup);
            query.setParameter(2, user);
            List<S_Report> lst = query.getResultList();

            return lst;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<S_Report_Group> getLstReportGroupByUser(String suser
    ) {
        try {
//            String s = "SELECT b.* from Q_PQRPORT_USER a LEFT JOIN S_REPORT_GROUP b\n"
//                     + "ON a.OBJID = b.RPTGROUPID\n"
//                     + "WHERE b.GLEVEL = (SELECT MIN(GLEVEL) FROM S_REPORT_GROUP a LEFT JOIN Q_PQRPORT_USER b ON a.RPTGROUPID = b.OBJID WHERE b.USERID = '" + suser + "')\n"
//                     + "AND a.USERID = '" + suser + "'";
            String sql = "";
            if (DBSettings.getEnumDBMode() == DBSettings.enumDBMode.SQLServer) {
                sql = "{call PKG_REPORT_GROUP(?)}";
            } else {
                sql = "SELECT * FROM S_REPORT_GROUP WHERE RPTGROUPID IN (\n"
                        + "                    SELECT distinct RPTGROUPID FROM S_REPORT  a\n"
                        + "                    WHERE EXISTS(SELECT 1 FROM Q_PQRPORT_USER WHERE OBJID = 'R.'|| a.RPTID AND USERID = ?))\n"
                        + "                    ORDER BY RPTGROUPORD";
                sql = "SELECT * FROM S_REPORT_GROUP";                
            }
            
            Query query = entityManager.createNativeQuery(sql, S_Report_Group.class);
            query.setParameter(1, suser);
            List<S_Report_Group> lst = query.getResultList();

            return lst;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<S_Report_Group> getLstReportGroupByParent(String sdataParent, String suser
    ) {
        try {
            String s = "SELECT b.* from Q_PQRPORT_USER a LEFT JOIN S_REPORT_GROUP b\n"
                    + "ON a.OBJID = b.RPTGROUPID\n"
                    + "WHERE a.USERID = '" + suser + "' AND b.RPTGROUPID_PARENT = '" + sdataParent + "'";
            
            
            s = "SELECT b.* from  S_REPORT_GROUP b WHERE b.RPTGROUPID_PARENT = '" + sdataParent + "'";
            
            Query query = entityManager.createNativeQuery(s, S_Report_Group.class);
            List<S_Report_Group> lst = query.getResultList();

            return lst;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<S_Report_Group> getListReportGroupByOrgID(String sOrgId, String user
    ) {
        try {
            String s = "SELECT * FROM S_REPORT_GROUP a LEFT JOIN Q_PQRPORT_USER b\n"
                    + "ON a.RPTGROUPID = b.OBJID\n"
                    + "WHERE b.USERID = '" + user + "' AND b.OBJTYPEID = 'G'";
            
            s = "SELECT * FROM S_REPORT_GROUP ";
            
            
            Query query = entityManager.createNativeQuery(s, S_Report_Group.class);
            List<S_Report_Group> lst = query.getResultList();

            return lst;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Q_User> getAllQ_UserByOrg(java.lang.String orgid
    ) {
        try {
            String queryString = "SELECT a FROM Q_User a WHERE a.enable=true "
                    + " and a.userid in (select b.id.userid from Q_Org_Grant_User b where b.sOrganization.orgid=:orgid) order by a.username";
            Query query = entityManager.createQuery(queryString, Q_User.class
            );
            query.setParameter(
                    "orgid", orgid);
            return query.getResultList();

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Q_Role> getAllQ_RoleByOrg(java.lang.String orgid
    ) {
        List<Q_Role> lst = null;
        return lst;
    }

    @Override
    public List<S_Report_Group> getListReportGroupRoot(String orgid
    ) {
        try {
            String queryString = "SELECT a.* FROM S_REPORT_GROUP a WHERE a.ORGID = '" + orgid + "'"
                    + "		AND GLEVEL = (SELECT MIN(GLEVEL) FROM S_REPORT_GROUP WHERE ORGID = '" + orgid + "')";

            queryString = "SELECT a.* FROM S_REPORT_GROUP a ORDER BY RPTGROUPORD";
            Query query = entityManager.createNativeQuery(queryString, S_Report_Group.class);

            return query.getResultList();

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<S_Report_Group> getListReportChild(S_Report_Group sparent
    ) {
        try {
            String queryString;
            if (DBSettings.getEnumDBMode() == DBSettings.enumDBMode.SQLServer) {
                queryString = " SELECT * FROM (\n"
                        + "                    SELECT a.RPTGROUPID, a.RPTGROUPDESC, a.RPTGROUPID_PARENT,\n"
                        + "                           0 RPTGROUPORD, a.NOTE, a.ORGID, a.ENABLE,\n"
                        + "                           a.USER_CR_ID, a.USER_CR_DTIME, a.USER_MDF_ID,\n"
                        + "                           a.USER_MDF_DTIME, a.URL, a.GLEVEL\n"
                        + "                      FROM S_REPORT_GROUP  a\n"
                        + "                    UNION ALL                    \n"
                        + "                    SELECT 'R.'+ b.RPTID, b.RPTDESC, b.RPTGROUPID,  1, b.NOTE, b.ORGID, b.ENABLE,\n"
                        + "                           b.USER_CR_ID, b.USER_CR_DTIME, b.USER_MDF_ID,\n"
                        + "                           b.USER_MDF_DTIME,  b.URL, null  \n"
                        + "                      FROM S_REPORT b ) aa WHERE aa.RPTGROUPID_PARENT = '" + sparent.getRptgroupid() + "' ORDER BY RPTGROUPORD";
            } else {
                queryString = " SELECT * FROM (\n"
                        + "                    SELECT a.RPTGROUPID, a.RPTGROUPDESC, a.RPTGROUPID_PARENT,\n"
                        + "                           0 RPTGROUPORD, a.NOTE, a.ORGID, a.ENABLE,\n"
                        + "                           a.USER_CR_ID, a.USER_CR_DTIME, a.USER_MDF_ID,\n"
                        + "                           a.USER_MDF_DTIME, a.URL, a.GLEVEL\n"
                        + "                      FROM S_REPORT_GROUP  a\n"
                        + "                    UNION ALL                    \n"
                        + "                    SELECT 'R.' || b.RPTID, b.RPTDESC, b.RPTGROUPID,  1, b.NOTE, b.ORGID, b.ENABLE,\n"
                        + "                           b.USER_CR_ID, b.USER_CR_DTIME, b.USER_MDF_ID,\n"
                        + "                           b.USER_MDF_DTIME,  b.URL, null  \n"
                        + "                      FROM S_REPORT b ) aa WHERE aa.RPTGROUPID_PARENT = '" + sparent.getRptgroupid() + "' ORDER BY RPTGROUPORD";
            }
            Query query = entityManager.createNativeQuery(queryString, S_Report_Group.class);

            return query.getResultList();

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Q_PQRPort_User> getLstPQGrantReport(String suser
    ) {
        try {
            String queryString = "SELECT * FROM Q_PQRPORT_USER\n"
                    + "                      WHERE USERID = '" + suser + "' AND OBJTYPEID = 'A'";
            Query query = entityManager.createNativeQuery(queryString, Q_PQRPort_User.class);

            return query.getResultList();
        } catch (Exception ex) {
            return null;
        }

    }

    @Override
    public void delPQGranUser(String quser) {
        try {
            String sql;
            if (DBSettings.getEnumDBMode() == DBSettings.enumDBMode.SQLServer) {
                sql = "EXEC PKG_REPORT_DEL_PQGRANT ?";
            } else {
                sql = "DELETE FROM Q_PQRPORT_USER WHERE USERID = ?";
            }

            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, quser);
            query.executeUpdate();
        } catch (Exception ex) {

        }
    }

    @Override
    public void inPQGranUser(Q_PQRPort_User quser) {
        try {

            String sql = "";
            if (DBSettings.getEnumDBMode() == DBSettings.enumDBMode.SQLServer) {
                sql = "EXEC PKG_REPORT_INS_PQGRANT ?, ?, ?, ?, ?, ?, ?, ?";
            } else {
                sql = "INSERT INTO Q_PQRPORT_USER\n"
                        + "				   (USERID\n"
                        + "				   ,OBJID\n"
                        + "				   ,OBJTYPEID\n"
                        + "				   ,R_VIEW\n"
                        + "				   ,USER_CR_ID\n"
                        + "				   ,USER_CR_DTIME\n"
                        + "				   ,USER_MDF_ID\n"
                        + "				   ,USER_MDF_DTIME)\n"
                        + "			 VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            }

            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1, quser.getQ_user().getUserid());
            query.setParameter(2, quser.getId().getObjid());
            query.setParameter(3, quser.getId().getObjtypeid());
            query.setParameter(4, 1);
            query.setParameter(5, quser.getUserCrId());
            query.setParameter(6, quser.getUserCrDtime());
            query.setParameter(7, quser.getUserMdfId());
            query.setParameter(8, quser.getUserMdfDtime());
            query.executeUpdate();

        } catch (Exception ex) {

        }
    }

    @Override
    public List<S_Report> getListReportByGroupIDView(String sgroup, String suser
    ) {
        try {
            String queryString = "with cte(RPTGROUPID,RPTGROUPID_PARENT) as (\n"
                    + "    select t.RPTGROUPID, t.RPTGROUPID_PARENT\n"
                    + "    from S_REPORT_GROUP t \n"
                    + "    where t.RPTGROUPID='" + sgroup + "'"
                    + "    union all\n"
                    + "    select t.RPTGROUPID, t.RPTGROUPID_PARENT\n"
                    + "    from S_REPORT_GROUP t\n"
                    + "    inner join cte c on (c.RPTGROUPID=t.RPTGROUPID_PARENT)\n"
                    + ")"
                    + "SELECT b.RPTID, b.RPTGROUPID, b.RPTDESC, RPTORD,\n"
                    + "                    					       ENABLE, b.NOTE, b.USER_CR_ID,\n"
                    + "                    					       b.USER_CR_DTIME, b.USER_MDF_ID,\n"
                    + "                    					       b.USER_MDF_DTIME, b.ORGID, b.URL FROM Q_PQRPORT_USER a JOIN S_REPORT b\n"
                    + "					ON RTRIM(REPLACE(a.OBJID,'R.','')) = b.RPTID\n"
                    + "					WHERE b.ENABLE =1 AND a.USERID = '" + suser + "' and b.RPTGROUPID = '" + sgroup + "' ORDER BY b.RPTORD";
            
            queryString = "with cte(RPTGROUPID,RPTGROUPID_PARENT) as (\n"
                    + "    select t.RPTGROUPID, t.RPTGROUPID_PARENT\n"
                    + "    from S_REPORT_GROUP t \n"
                    + "    where t.RPTGROUPID='" + sgroup + "'"
                    + "    union all\n"
                    + "    select t.RPTGROUPID, t.RPTGROUPID_PARENT\n"
                    + "    from S_REPORT_GROUP t\n"
                    + "    inner join cte c on (c.RPTGROUPID=t.RPTGROUPID_PARENT)\n"
                    + ")"
                    + "SELECT b.RPTID, b.RPTGROUPID, b.RPTDESC, RPTORD,\n"
                    + "                    					       ENABLE, b.NOTE, b.USER_CR_ID,\n"
                    + "                    					       b.USER_CR_DTIME, b.USER_MDF_ID,\n"
                    + "                    					       b.USER_MDF_DTIME, b.ORGID, b.URL FROM S_REPORT b\n"                    
                    + "					WHERE b.ENABLE =1 and b.RPTGROUPID = '" + sgroup + "' ORDER BY b.RPTORD";
            
            Query query = entityManager.createNativeQuery(queryString, S_Report.class);

            return query.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    

    public List<S_ReportTTVHNgay> getListTTVHNgayData(Date ngay, String dvql) {
        try {
            String queryString = "PKG_IMIS_REPORT.getThuThapTSVH_Gio";
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery(queryString, S_ReportTTVHNgay.class);
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(3, void.class, ParameterMode.REF_CURSOR);
            query.setParameter(1, dvql);
            query.setParameter(2, ngay);
            List<S_ReportTTVHNgay> lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EJBException(e);
        }
    }

    public List<S_ReportTTVHNgay> getListTHTT3PhaData(String dvql, int sophut) {
        try {
            String queryString = "PKG_OPERATION.GetTongHopThuThapDL";
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery(queryString, S_ReportTTVHNgay.class);
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(3, void.class, ParameterMode.REF_CURSOR);
            query.setParameter(1, dvql);
            query.setParameter(2, sophut);
            List<S_ReportTTVHNgay> lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EJBException(e);
        }
    }

    public List<S_ReportTTVHNgay> getListTHTTDLDocData(String dvql, Date ngay) {
        try {
            String queryString = "PKG_IMIS_REPORT.GETTHUTHAPTSVH_GIO";
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery(queryString, S_ReportTTVHNgay.class);
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(3, void.class, ParameterMode.REF_CURSOR);
            query.setParameter(1, dvql);
            query.setParameter(2, ngay);
            List<S_ReportTTVHNgay> lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EJBException(e);
        }
    }

    public List<S_ReportTTPhutaiThang> getListTTPhutaiThangData(Date tngay, Date dngay, String dvql) {
        try {
            String queryString = "PKG_IMIS_REPORT.getThuThapthieu48_Ngay";
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery(queryString, S_ReportTTPhutaiThang.class);
            query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(3, Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(4, void.class, ParameterMode.REF_CURSOR);
            query.setParameter(1, dvql);
            query.setParameter(2, tngay);
            query.setParameter(3, dngay);
            List<S_ReportTTPhutaiThang> lst = query.getResultList();
            return lst;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EJBException(e);
        }
    }

}
