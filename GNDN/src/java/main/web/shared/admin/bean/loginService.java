/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.web.shared.admin.bean;

import main.ContextResources.EjbContext;
import main.entity.shared.system.SystemData;
import main.entity.shared.system.SystemSessionData;
import main.entity.shared.system.SystemUserData;
import main.remote.shared.admin.IAdmin;
import main.web.common.bean.CryptoLibrary;
import main.web.common.bean.WorkingContext;
import evnit.util.common.BaseVariable;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.primefaces.shaded.json.JSONObject;


/**
 *
 * @author sypv
 */
@WebService(serviceName = "pmisGateway")
@Stateful
public class loginService {

    @Resource
    private WebServiceContext context;

    @WebMethod(operationName = "login")
    public String login(@WebParam(name = "userName") String txtUserName, @WebParam(name = "password") String txtPassWord) {
        IAdmin caller = null;
        try {
            caller = (IAdmin) EjbContext.getLocalEJBRemote(IAdmin.class.getSimpleName());
        } catch (Exception e) {
        }

        HttpServletRequest req = (HttpServletRequest) this.context.getMessageContext()
                .get(MessageContext.SERVLET_REQUEST);
        HttpSession appSession = req.getSession(true);
        if (txtUserName.trim().isEmpty() || txtPassWord.isEmpty()) {
            return null;
        } else {
            String userID = txtUserName.trim();
            String password = CryptoLibrary.getInstance().encrypt(txtPassWord);
            BaseVariable bvLogin = caller.checkLogin(userID, password);
            if (bvLogin != null) {
//                    OrganizationBean.setInHomePage(true);
                if (bvLogin.getBoolean()) {
                    appSession.setAttribute("UserId", userID);
                    appSession.setAttribute("TypeLogin", "loginService");
                    //Luu tap quyen nguoi dung
                    String sessionId = appSession.getId();
                    return sessionId;
                    //m_MsgError= rfFactory.getMessage("msgUserDisable");
                } else if (bvLogin.getInteger().equals(1)) {
                    return null;
                } else if (bvLogin.getInteger().equals(2)) {
                    return null;
                } else //if(bvLogin.getInteger().equals(3))
                {
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    @WebMethod(operationName = "logout")
    public String logout(@WebParam(name = "pJSessionId") String txtJSESSIONID) {
        //Tam thoi chua dung duoc        
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        ExternalContext externalContext = facesContext.getExternalContext();
//        HttpSession session = (HttpSession) externalContext.getSession(true);
//        if (session.getId().equals(txtJSESSIONID)) {
//            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//            return "OK";
//        } else {
//            return null;
//        }
        return null;
    }

    public int loginStatus(@WebParam(name = "tid") String tid) {
        int iResult = 2;
        HttpServletRequest req = (HttpServletRequest) this.context.getMessageContext()
                .get(MessageContext.SERVLET_REQUEST);
        HttpSession appSession = req.getSession(true);
        SystemData tempSysData = (SystemData) appSession.getServletContext().getAttribute(WorkingContext.systemData);

        HashMap<String, SystemUserData> userInfo = tempSysData.getUserInfo();
        HashMap<String, SystemSessionData> lstSessionId;
        SystemSessionData userSessonData;
        long iTimeOut = 0;
        for (Entry<String, SystemUserData> entry : userInfo.entrySet()) {
            lstSessionId = entry.getValue().getLstSessionId();
            if (lstSessionId.containsKey(tid)) {
                userSessonData = lstSessionId.get(tid);
                switch (userSessonData.getStatus()) {
                    case 0:
                        iResult=0;
                        break;
                    case 1:
                        if (userSessonData.getTimeStamp() != null && userSessonData.getTimeStampLogin() != null) {
                            iTimeOut = (userSessonData.getTimeStamp().getTime() - userSessonData.getTimeStampLogin().getTime()) / 1000;
                            if (iTimeOut > 3600) {
                                iResult = 0;
                            } else {
                                iResult = 1;
                            }
                        }
                        break;
                }
                break;
            }
        }
        return iResult;
    }

    public String loginInfo(@WebParam(name = "tid") String tid) {
        JSONObject jResult = new JSONObject();
        HttpServletRequest req = (HttpServletRequest) this.context.getMessageContext()
                .get(MessageContext.SERVLET_REQUEST);
        HttpSession appSession = req.getSession(true);
        SystemData tempSysData = (SystemData) appSession.getServletContext().getAttribute(WorkingContext.systemData);
        HashMap<String, SystemUserData> userInfo = tempSysData.getUserInfo();
        HashMap<String, SystemSessionData> lstSessionId;
        SystemUserData systemUserData = new SystemUserData();
        for (Entry<String, SystemUserData> entry : userInfo.entrySet()) {
            lstSessionId = entry.getValue().getLstSessionId();
            if (lstSessionId.containsKey(tid)) {
                systemUserData = entry.getValue();
                break;
            }
        }
        if (systemUserData != null) {
            try {
                if (systemUserData.getUser() != null) {
                    jResult.put("userid", systemUserData.getUser().getUserid());
                    jResult.put("username", systemUserData.getUser().getUserIDName());
                    jResult.put("dvql", "Chưa xác định");
                    jResult.put("sdt", systemUserData.getUser().getTel());
                    jResult.put("email", systemUserData.getUser().getEmail());
                } else {
                    jResult.put("userid", "");
                    jResult.put("username", "");
                    jResult.put("dvql", "");
                    jResult.put("sdt", "");
                    jResult.put("email", "");
                }
            } catch (Exception ex) {
                try {
                    jResult.put("error", ex.getMessage());
                } catch (Exception ex1) {

                }
            }
        } else {

            try {
                jResult.put("userid", "");
                jResult.put("username", "");
                jResult.put("dvql", "");
                jResult.put("sdt", "");
                jResult.put("email", "");
            } catch (Exception ex) {

            }
        }
        return jResult.toString();
    }
}
