/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.ejb.shared.admin;

import main.entity.shared.admin.Q_Function_Wflow;
import main.entity.shared.admin.Q_Function_Wflow_Param;
import main.remote.shared.admin.ISysFunctionFlowRemote;
import evnit.util.setting.DBSettings;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import main.ejb.shared.common.BaseCommonBL;

/**
 *
 * @author huongnd
 */
@Stateless
public class SysFunctionFlowBL extends BaseCommonBL implements ISysFunctionFlowRemote {

    //{{<editor-fold defaultstate="collapsed" desc="Flow">
    @Override
    public List<Q_Function_Wflow> getFlowByFunctionID(String functionid) {
        String queryString;
        if (DBSettings.getEnumDBMode() == DBSettings.enumDBMode.SQLServer) {
            queryString = "SELECT [WFLOWID], [WFLOWID_PARENT],[FUNCTIONID],[WFLOW_DESC],[WFLOW_CODE] "
                    + ", IIF(exists(select 1 from Q_FUNCTION_WFLOW f1 where f1.WFLOWID_PARENT = f.WFLOWID),'1','0') as NOTE "
                    + ", [USER_CR_ID],[USER_CR_DTIME],[ENABLE],[SYS] FROM Q_FUNCTION_WFLOW f WHERE FUNCTIONID = ? "
                    + "AND WFLOWID_PARENT IS NULL";
        } else {
            queryString = "SELECT WFLOWID, WFLOWID_PARENT,FUNCTIONID,WFLOW_DESC,WFLOW_CODE "
                    + ", DECODE ( (SELECT COUNT (*)\n"
                    + "                     FROM Q_FUNCTION_WFLOW f1\n"
                    + "                    WHERE f1.WFLOWID_PARENT = f.WFLOWID),0,'0','1')  NOTE "
                    + ", USER_CR_ID,USER_CR_DTIME,ENABLE,SYS FROM Q_FUNCTION_WFLOW f WHERE FUNCTIONID = ? "
                    + "AND WFLOWID_PARENT IS NULL";
        }
        Query query = entityManager.createNativeQuery(queryString, Q_Function_Wflow.class);
        query.setParameter(1, functionid);
        return query.getResultList();
    }

    @Override
    public List<Q_Function_Wflow> getFlowByFlowParentID(String flowid) {
        String queryString;
        if (DBSettings.getEnumDBMode() == DBSettings.enumDBMode.SQLServer) {
            queryString = "SELECT [WFLOWID], [WFLOWID_PARENT],[FUNCTIONID],[WFLOW_DESC],[WFLOW_CODE] "
                    + ", IIF(exists(select 1 from Q_FUNCTION_WFLOW f1 where f1.WFLOWID_PARENT = f.WFLOWID),'1','0') as NOTE "
                    + ", [USER_CR_ID],[USER_CR_DTIME],[ENABLE],[SYS] FROM Q_FUNCTION_WFLOW f WHERE WFLOWID_PARENT = ?";
        } else {
            queryString = "SELECT WFLOWID, WFLOWID_PARENT,FUNCTIONID,WFLOW_DESC,WFLOW_CODE "
                    + ", DECODE ( (SELECT COUNT (*)\n"
                    + "                     FROM Q_FUNCTION_WFLOW f1\n"
                    + "                    WHERE f1.WFLOWID_PARENT = f.WFLOWID),0,'0','1')  NOTE "
                    + ", USER_CR_ID,USER_CR_DTIME,ENABLE,SYS FROM Q_FUNCTION_WFLOW f WHERE WFLOWID_PARENT = ?";
        }
        Query query = entityManager.createNativeQuery(queryString, Q_Function_Wflow.class);
        query.setParameter(1, flowid);
        return query.getResultList();
    }

    @Override
    public boolean insertFlow(Q_Function_Wflow fl) {
        try {
            String queryString = "INSERT INTO Q_FUNCTION_WFLOW (WFLOWID,FUNCTIONID,WFLOW_DESC,WFLOW_CODE,USER_CR_ID,USER_CR_DTIME,WFLOWID_PARENT,ENABLE,SYS,NOTE) VALUES (?,?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(queryString);
            query.setParameter(1, fl.getWflowid());
            query.setParameter(2, fl.getSfunctionId().getFunctionid());
            query.setParameter(3, fl.getWflowDesc());
            query.setParameter(4, fl.getWflowCode());
            query.setParameter(5, fl.getUserCrId());
            query.setParameter(6, fl.getUserCrDtime());
            query.setParameter(7, fl.getWflowidParent());
            query.setParameter(8, fl.isEnable());
            query.setParameter(9, fl.isSys());
            query.setParameter(10, fl.getNote());
            query.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateFlow(Q_Function_Wflow fl) {
        try {
            String queryString = "UPDATE Q_FUNCTION_WFLOW SET WFLOW_DESC = ?,WFLOW_CODE = ? ,ENABLE = ?,SYS = ?,NOTE = ? WHERE WFLOWID = ?";
            Query query = entityManager.createNativeQuery(queryString);
            query.setParameter(1, fl.getWflowDesc());
            query.setParameter(2, fl.getWflowCode());
            query.setParameter(3, fl.isEnable());
            query.setParameter(4, fl.isSys());
            query.setParameter(5, fl.getNote());
            query.setParameter(6, fl.getWflowid());
            query.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteFlow(Q_Function_Wflow fl) {
        try {
            boolean ret;
            Query query;
            String queryMore = "WITH cte(WFLOWID) AS ( "
                    + "	SELECT WFLOWID FROM Q_FUNCTION_WFLOW WHERE WFLOWID = ? "
                    + "	UNION ALL "
                    + "	SELECT w.WFLOWID FROM Q_FUNCTION_WFLOW w "
                    + "	JOIN cte ON cte.WFLOWID = w.WFLOWID_PARENT "
                    + ") ";
            //check ton tai trong bang phan quyen ROLE chua
            String queryString = queryMore
                    + "SELECT count(*) FROM Q_PQFUNCTION_ROLE_WFLOW WHERE WFLOWID IN (select * from cte)";
            query = entityManager.createNativeQuery(queryString);
            query.setParameter(1, fl.getWflowid());
            ret = Integer.parseInt(query.getSingleResult().toString()) > 0; //Chua ton tai tra ve false

            //check ton tai trong bang phan quyen USER chua
            if (!ret) {
                queryString = queryMore
                        + "SELECT count(*) FROM Q_PQFUNCTION_USER_WFLOW WHERE WFLOWID IN (select * from cte)";
                query = entityManager.createNativeQuery(queryString);
                query.setParameter(1, fl.getWflowid());
                ret = Integer.parseInt(query.getSingleResult().toString()) > 0; //Chua ton tai tra ve false
            }

            //Xoa Param - Flow
            if (!ret) {

                if (DBSettings.getEnumDBMode() == DBSettings.enumDBMode.SQLServer) {
                    queryString = queryMore + " DELETE FROM Q_FUNCTION_WFLOW_PARAM WHERE WFLOWID IN (SELECT WFLOWID FROM cte)";
                } else {
                    queryString = "DELETE FROM Q_FUNCTION_WFLOW_PARAM WHERE WFLOWID IN "
                            + "( " + queryMore + " SELECT WFLOWID FROM cte)";
                }
                query = entityManager.createNativeQuery(queryString);
                query.setParameter(1, fl.getWflowid());
                query.executeUpdate(); //Thuc hien duoc tra ve true
                if (DBSettings.getEnumDBMode() == DBSettings.enumDBMode.SQLServer) {
                    queryString = queryMore + " DELETE FROM Q_FUNCTION_WFLOW WHERE WFLOWID IN (SELECT WFLOWID FROM cte)";
                } else {
                    queryString = "DELETE FROM Q_FUNCTION_WFLOW WHERE WFLOWID IN"
                            + "( " + queryMore + " SELECT WFLOWID FROM cte)";
                }
                query = entityManager.createNativeQuery(queryString);
                query.setParameter(1, fl.getWflowid());
                ret = query.executeUpdate() > 0; //Thuc hien duoc tra ve true
            } else {
                return false;
            }
            return ret;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    //}}</editor-fold>

    //{{<editor-fold defaultstate="collapsed" desc="Param">
    @Override
    public List<Q_Function_Wflow_Param> getParamByFlowID(String flowid) {
        String queryString = "SELECT WFLOWDATAID,WFLOWID,WFLOWDATAID_PARENT,DATANAME,DATACODE,DATATYPEID,TYPE,VALUE,DATAORD FROM Q_FUNCTION_WFLOW_PARAM WHERE WFLOWID = ? ORDER BY DATAORD";
        Query query = entityManager.createNativeQuery(queryString, Q_Function_Wflow_Param.class);
        query.setParameter(1, flowid);
        return query.getResultList();
    }

    @Override
    public boolean insertParam(Q_Function_Wflow_Param pr) {
        try {
            String queryString = "INSERT INTO Q_FUNCTION_WFLOW_PARAM (WFLOWDATAID,WFLOWID,DATANAME,DATACODE,DATATYPEID,TYPE,VALUE,DATAORD,WFLOWDATAID_PARENT) VALUES (?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(queryString);
            query.setParameter(1, pr.getWflowdataid());
            query.setParameter(2, pr.getWflowid().getWflowid());
            query.setParameter(3, pr.getDataname());
            query.setParameter(4, pr.getDatacode());
            query.setParameter(5, pr.getDatatypeid());
            query.setParameter(6, pr.getType());
            query.setParameter(7, pr.getValue());
            query.setParameter(8, pr.getDataord());
            query.setParameter(9, pr.getWflowdataidParent());
            query.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertLstParam(List<Q_Function_Wflow_Param> ls) {
        try {
            String queryString = "INSERT INTO Q_FUNCTION_WFLOW_PARAM (WFLOWDATAID,WFLOWID,DATANAME,DATACODE,DATATYPEID,TYPE,VALUE,DATAORD,WFLOWDATAID_PARENT) VALUES";
            for (Q_Function_Wflow_Param pr : ls) {
                queryString += "(";
                queryString += "'" + pr.getWflowdataid() + "',";
                queryString += "'" + pr.getWflowid().getWflowid() + "',";
                queryString += "N'" + pr.getDataname() + "',";
                queryString += "N'" + pr.getDatacode() + "',";
                queryString += "'" + pr.getDatatypeid() + "',";
                queryString += pr.getType().toString() + ",";
                queryString += "N'" + pr.getValue() + "',";
                queryString += pr.getDataord().toString() + ",";
                queryString += "'" + pr.getWflowdataidParent() + "'";
                queryString += "),";
            }
            queryString = queryString.substring(0, queryString.length() - 1);
            Query query = entityManager.createNativeQuery(queryString);

            query.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateParam(Q_Function_Wflow_Param pr) {
        try {
            String queryString = "UPDATE Q_FUNCTION_WFLOW_PARAM SET DATANAME = ?,DATACODE = ?,DATATYPEID = ?,TYPE = ?,VALUE = ?,DATAORD = ? WHERE WFLOWDATAID = ?";
            Query query = entityManager.createNativeQuery(queryString);
            query.setParameter(1, pr.getDataname());
            query.setParameter(2, pr.getDatacode());
            query.setParameter(3, pr.getDatatypeid());
            query.setParameter(4, pr.getType());
            query.setParameter(5, pr.getValue());
            query.setParameter(6, pr.getDataord());
            query.setParameter(7, pr.getWflowdataid());
            query.executeUpdate();
            queryString = "UPDATE Q_FUNCTION_WFLOW_PARAM SET DATANAME = ?,DATACODE = ?,DATATYPEID = ?,TYPE = ?,DATAORD = ? WHERE WFLOWDATAID_PARENT = ?";
            query = entityManager.createNativeQuery(queryString);
            query.setParameter(1, pr.getDataname());
            query.setParameter(2, pr.getDatacode());
            query.setParameter(3, pr.getDatatypeid());
            query.setParameter(4, pr.getType());
            query.setParameter(5, pr.getDataord());
            query.setParameter(6, pr.getWflowdataid());
            query.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteParam(Q_Function_Wflow_Param pr) {
        try {
            String queryString = "DELETE FROM Q_FUNCTION_WFLOW_PARAM WHERE WFLOWDATAID = ?";
            Query query = entityManager.createNativeQuery(queryString);
            query.setParameter(1, pr.getWflowdataid());
            query.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteLsParamByParamParent(String paramid) {
        try {
            String queryString = "DELETE FROM Q_FUNCTION_WFLOW_PARAM WHERE WFLOWDATAID_PARENT = ?";
            Query query = entityManager.createNativeQuery(queryString);
            query.setParameter(1, paramid);
            query.executeUpdate();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    //}}</editor-fold>
}
