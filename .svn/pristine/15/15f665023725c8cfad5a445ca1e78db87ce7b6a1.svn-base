/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.system;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Id;

public class ChannelSystemData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private Date dateCreate;
    private E_GroupInfo groupInfoData;
    private List<String> toUsers;
    private String fromUser;
    private String dataHeader;
    private String data;
    private E_MessageType msgType;

    public ChannelSystemData(Date dateCreate, E_GroupInfo groupInfoData, List<String> toUsers, String fromUser, String dataHeader, String data, E_MessageType msgType) {
        this.dateCreate = dateCreate;
        this.groupInfoData = groupInfoData;
        this.toUsers = toUsers;
        this.fromUser = fromUser;
        this.dataHeader = dataHeader;
        this.data = data;
        this.msgType = msgType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public E_GroupInfo getGroupInfoData() {
        return groupInfoData;
    }

    public void setGroupInfoData(E_GroupInfo groupInfoData) {
        this.groupInfoData = groupInfoData;
    }

    public List<String> getToUsers() {
        return toUsers;
    }

    public void setToUsers(List<String> toUsers) {
        this.toUsers = toUsers;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getDataHeader() {
        return dataHeader;
    }

    public void setDataHeader(String dataHeader) {
        this.dataHeader = dataHeader;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public E_MessageType getMsgType() {
        return msgType;
    }

    public void setMsgType(E_MessageType msgType) {
        this.msgType = msgType;
    }

}
