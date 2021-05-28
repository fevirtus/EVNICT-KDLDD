/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.system;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import javax.persistence.Entity;
import javax.persistence.Id;

public class MessgengerData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private String userIdSend;
    private String userIdReceive;
    private int status;
    private Date sendDate;
    private Date Receive;
    private String messenger;
    public MessgengerData() {
    }
    private HashMap<String,SystemUserData> userInfo;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserIdSend() {
        return userIdSend;
    }

    public void setUserIdSend(String userIdSend) {
        this.userIdSend = userIdSend;
    }

    public String getUserIdReceive() {
        return userIdReceive;
    }

    public void setUserIdReceive(String userIdReceive) {
        this.userIdReceive = userIdReceive;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getReceive() {
        return Receive;
    }

    public void setReceive(Date Receive) {
        this.Receive = Receive;
    }

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }

    public HashMap<String, SystemUserData> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(HashMap<String, SystemUserData> userInfo) {
        this.userInfo = userInfo;
    }

    
}
