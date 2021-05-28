/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.system;

import main.entity.shared.admin.Q_User;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Id;

public class SystemUserData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Q_User user;
    private Date dateLogin;
    private Date dateRequest;
    private long countRequest;
    private String functionRequest;
    private List<String> logFunctionRequest;
    private String ipRequest;
    private String browserRequest;
    private HashMap<String, SystemSessionData> lstSessionId;
    private HashMap<String, String> lstAssetCache;

    public SystemUserData() {
    }

    public Q_User getUser() {
        return user;
    }

    public void setUser(Q_User user) {
        this.user = user;
    }

    public Date getDateLogin() {
        return dateLogin;
    }

    public void setDateLogin(Date dateLogin) {
        this.dateLogin = dateLogin;
    }

    public String getFunctionRequest() {
        return functionRequest;
    }

    public void setFunctionRequest(String functionRequest) {
        this.functionRequest = functionRequest;
    }

    public Date getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(Date dateRequest) {
        this.dateRequest = dateRequest;
    }

    public long getCountRequest() {
        return countRequest;
    }

    public void setCountRequest(long countRequest) {
        this.countRequest = countRequest;
    }

    public List<String> getLogFunctionRequest() {
        return logFunctionRequest;
    }

    public void setLogFunctionRequest(List<String> logFunctionRequest) {
        this.logFunctionRequest = logFunctionRequest;
    }

    public String getIpRequest() {
        return ipRequest;
    }

    public void setIpRequest(String ipRequest) {
        this.ipRequest = ipRequest;
    }

    public String getBrowserRequest() {
        return browserRequest;
    }

    public void setBrowserRequest(String browserRequest) {
        this.browserRequest = browserRequest;
    }

    public HashMap<String, SystemSessionData> getLstSessionId() {
        return lstSessionId;
    }

    public void setLstSessionId(HashMap<String, SystemSessionData> lstSessionId) {
        this.lstSessionId = lstSessionId;
    }

    public HashMap<String, String> getLstAssetCache() {
        return lstAssetCache;
    }

    public void setLstAssetCache(HashMap<String, String> lstAssetCache) {
        this.lstAssetCache = lstAssetCache;
    }

}
