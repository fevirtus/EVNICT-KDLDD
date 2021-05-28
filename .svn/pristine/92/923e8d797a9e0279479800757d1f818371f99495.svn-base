/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.admin.bean;

import main.ContextResources.EjbContext;

import main.remote.shared.system.ISystemCommonRemote;
import main.web.common.bean.BasePageCommon;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseConstant.enumMessageMode;
import evnit.util.common.S_Key_ControlInfo;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.event.ActionEvent;
import main.entity.shared.admin.Q_Function;
import main.entity.shared.admin.Q_Function_Wflow;
import main.entity.shared.admin.Q_Function_Wflow_Param;
import main.remote.shared.admin.ISysFunctionFlowRemote;

/**
 *
 * @author huongnd
 */
@ManagedBean
@ViewScoped
public final class SysFunctionFlowDetailBean extends BasePageCommon implements Serializable {

    //{{<editor-fold defaultstate="collapsed" desc="Khai báo">     
    private Q_Function_Wflow fl;
    
    private String flowidd;
    private String functionidd;
    private String flagg;
    private String wflowDesc;
    private String wflowCode;
    private String note;
    private boolean senable;
    private boolean ssys;
    
    private boolean isRoot;
    
    private ISysFunctionFlowRemote m_ISysFunctionFlowRemote;
    private ISystemCommonRemote m_ISystemCommonRemote;

    //{{<editor-fold defaultstate="collapsed" desc="Get Set">
    //}}</editor-fold>
    public boolean isIsRoot() {
        return isRoot;
    }
    
    
    public String getFlowidd() {
        return flowidd;
    }

    public void setFlowidd(String flowidd) {
        this.flowidd = flowidd;
    }

    public String getFunctionidd() {
        return functionidd;
    }

    public void setFunctionidd(String functionidd) {
        this.functionidd = functionidd;
    }

    public String getFlagg() {
        return flagg;
    }

    public void setFlagg(String flagg) {
        this.flagg = flagg;
    }
    
    public String getWflowDesc() {
        return wflowDesc;
    }

    public void setWflowDesc(String wflowDesc) {
        this.wflowDesc = wflowDesc;
    }

    public String getWflowCode() {
        return wflowCode;
    }

    public void setWflowCode(String wflowCode) {
        this.wflowCode = wflowCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isSenable() {
        return senable;
    }

    public void setSenable(boolean senable) {
        this.senable = senable;
    }

    public boolean isSsys() {
        return ssys;
    }

    public void setSsys(boolean ssys) {
        this.ssys = ssys;
    }
    
    public ISysFunctionFlowRemote getISysFunctionFlowRemote() {
        if (m_ISysFunctionFlowRemote == null) {
            try {
                m_ISysFunctionFlowRemote = (ISysFunctionFlowRemote) EjbContext.getLocalEJBRemote(ISysFunctionFlowRemote.class.getSimpleName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return m_ISysFunctionFlowRemote;
    }
    
    public ISystemCommonRemote getISystemCommonRemote()
    {
        try
        {
            if(m_ISystemCommonRemote == null)
                m_ISystemCommonRemote = (ISystemCommonRemote)EjbContext.getLocalEJBRemote(ISystemCommonRemote.class.getSimpleName());
            else
                EjbContext.LoginEJB();
        }
        catch(Exception ex) { }
        return m_ISystemCommonRemote;
    }
    //}}</editor-fold>

    public SysFunctionFlowDetailBean() {
        if (!isPostback()){
            flagg = getFlag();
            flowidd = getFlowID();
            functionidd = getFunID();
            switch(flagg){
                case "0": 
                    CleanScreen(); 
                    isRoot = true;
                    break;
                default:
                    fl = (Q_Function_Wflow)getISysFunctionFlowRemote().findById(flowidd, Q_Function_Wflow.class);
                    if ((fl.getWflowidParent() == null || fl.getWflowidParent().equals("")) && !fl.isSys() && !flagg.equals("2")) isRoot = true;
                    else isRoot = false;
                    EntityToScreen();
                    break;
            }
        }
    }
    
    public void btnSaveFlow(ActionEvent evt){
        fl = ScreenToEntity(); 
        if (flagg.equals("0")){
            S_Key_ControlInfo controlInfo = new S_Key_ControlInfo(null, Q_Function_Wflow.class.getSimpleName());
            controlInfo = getISystemCommonRemote().getGenKeyID(controlInfo);
            fl.setWflowid(controlInfo.getOutputValue());
            if(getISysFunctionFlowRemote().insertFlow(fl)) WorkingContext.showMessages(enumMessageMode.eInfo, null, "Thêm mới thành công!");
            else WorkingContext.showMessages(enumMessageMode.eWarn, null, "Có lỗi xảy ra!");
        }
        if (flagg.equals("1")){
            fl.setWflowid(flowidd);
            if(getISysFunctionFlowRemote().updateFlow(fl)) WorkingContext.showMessages(enumMessageMode.eInfo, null, "Cập nhật thành công!");
            else WorkingContext.showMessages(enumMessageMode.eWarn, null, "Có lỗi xảy ra!");
        }
        if (flagg.equals("2")){
            S_Key_ControlInfo controlInfo = new S_Key_ControlInfo(null, Q_Function_Wflow.class.getSimpleName());
            controlInfo = getISystemCommonRemote().getGenKeyID(controlInfo);
            fl.setWflowid(controlInfo.getOutputValue());
            fl.setWflowidParent(flowidd);
            if(getISysFunctionFlowRemote().insertFlow(fl)) 
            {                
                List<Q_Function_Wflow_Param> lsPR = getAllQ_Function_Wflow_ParamByWflow(flowidd);
                if (lsPR.size()>0){
                    S_Key_ControlInfo controlInfo1 = new S_Key_ControlInfo(null, Q_Function_Wflow_Param.class.getSimpleName());
                    for(Q_Function_Wflow_Param pr : lsPR){
                        pr.setWflowdataidParent(pr.getWflowdataid());
                        pr.setWflowdataid(getISystemCommonRemote().getGenKeyID(controlInfo1).getOutputValue());                        
                        pr.setWflowid(fl);                        
                    }
                    if (getISysFunctionFlowRemote().insertLstParam(lsPR)) WorkingContext.showMessages(enumMessageMode.eInfo, null, "Thêm mới thành công!");
                    else WorkingContext.showMessages(enumMessageMode.eWarn, null, "Đã thêm mới được Flow nhưng chưa copy được Param");
                }
            }
            else 
                WorkingContext.showMessages(enumMessageMode.eWarn, null, "Có lỗi xảy ra!");
        }
    }
    
    private void CleanScreen(){
        wflowDesc = "";
        wflowCode = "";
        note = "";
        senable = true;
        ssys = false;
    }
    
    private Q_Function_Wflow ScreenToEntity(){
        Q_Function_Wflow f = new Q_Function_Wflow();
        f.setEnable(senable);
        f.setNote(note);
        f.setSfunctionId((Q_Function)getISysFunctionFlowRemote().findById(functionidd, Q_Function.class));
        f.setSys(ssys);
        f.setUserCrDtime(new Date());
        f.setUserCrId(WorkingContext.getUserName());
        f.setWflowCode(wflowCode);
        f.setWflowDesc(wflowDesc);        
        return f;
    }
    
    private void EntityToScreen(){
        senable = fl.isEnable();
        note = fl.getNote();
        if(flagg.equals("2")) ssys = false;
        else ssys = fl.isSys();
        wflowCode = fl.getWflowCode();
        wflowDesc = fl.getWflowDesc();
    }
    
    private String getFlowID(){
        return WorkingContext.getRequestQueryString("id");
    } 
    
    private String getFunID(){
        return WorkingContext.getRequestQueryString("funid");
    }
    
    private String getFlag(){
        return WorkingContext.getRequestQueryString("flag");
    } 
}
