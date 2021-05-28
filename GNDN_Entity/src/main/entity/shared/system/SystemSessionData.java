/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.system;

import java.io.Serializable;
import java.util.Date;

public class SystemSessionData implements Serializable {

    private String sessionId;
    private Date timeStamp;
    private Date timeStampLogin;
    private Date timeStampLogout;
    private Integer status;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Date getTimeStampLogin() {
        return timeStampLogin;
    }

    public void setTimeStampLogin(Date timeStampLogin) {
        this.timeStampLogin = timeStampLogin;
    }

    public Date getTimeStampLogout() {
        return timeStampLogout;
    }

    public void setTimeStampLogout(Date timeStampLogout) {
        this.timeStampLogout = timeStampLogout;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
