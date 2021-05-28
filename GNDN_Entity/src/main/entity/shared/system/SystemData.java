/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.system;

import java.io.Serializable;
import java.util.HashMap;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SystemData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;

    public SystemData() {
    }
    private HashMap<String, SystemUserData> userInfo;
    private HashMap<String, MessgengerData> messengerData;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HashMap<String, SystemUserData> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(HashMap<String, SystemUserData> userInfo) {
        this.userInfo = userInfo;
    }

    public HashMap<String, MessgengerData> getMessengerData() {
        return messengerData;
    }

    public void setMessengerData(HashMap<String, MessgengerData> messengerData) {
        this.messengerData = messengerData;
    }
}
