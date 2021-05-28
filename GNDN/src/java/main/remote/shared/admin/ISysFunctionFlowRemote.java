/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.remote.shared.admin;
import main.entity.shared.admin.Q_Function_Wflow;
import main.entity.shared.admin.Q_Function_Wflow_Param;
import main.remote.shared.common.ICommon;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author huongnd
 */
@Local
public interface ISysFunctionFlowRemote extends ICommon {
    //{{<editor-fold defaultstate="collapsed" desc="Flow">
    public List<Q_Function_Wflow> getFlowByFunctionID(String functionid);
    public List<Q_Function_Wflow> getFlowByFlowParentID(String flowid);
    public boolean insertFlow(Q_Function_Wflow fl);
    public boolean updateFlow(Q_Function_Wflow fl);
    public boolean deleteFlow(Q_Function_Wflow fl);
    //}}</editor-fold>
    
    //{{<editor-fold defaultstate="collapsed" desc="Param">
    public List<Q_Function_Wflow_Param> getParamByFlowID(String flowid);
    public boolean insertParam(Q_Function_Wflow_Param pr);
    public boolean updateParam(Q_Function_Wflow_Param pr);
    public boolean deleteParam(Q_Function_Wflow_Param pr);
    public boolean insertLstParam(List<Q_Function_Wflow_Param> ls);
    public boolean deleteLsParamByParamParent(String paramid);
    //}}</editor-fold>
}
