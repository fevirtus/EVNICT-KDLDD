/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.admin;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sypv
 */
@Entity
@Table(name = "Q_USER_LOG")
public class Q_User_Log implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LOGID")
    private String logid;
    @Column(name = "USERID")
    private String userid;
    @Column(name = "FUNCTIONID")
    private String functionid;
    @Column(name = "TIMESTAMPREQ")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestampReq;
    @Column(name = "IPADDRESS")
    private String ipaddress;
    @Column(name = "BROWER")
    private String brower;
    @Column(name = "PARAMS")
    private String params;
    @Column(name = "TIMESTAMPRES")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestampRes;

    public Q_User_Log() {
    }

    public Q_User_Log(String logid) {
        this.logid = logid;
    }

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFunctionid() {
        return functionid;
    }

    public void setFunctionid(String functionid) {
        this.functionid = functionid;
    }

    public Date getTimestampReq() {
        return timestampReq;
    }

    public void setTimestampReq(Date timestampReq) {
        this.timestampReq = timestampReq;
    }

    public Date getTimestampRes() {
        return timestampRes;
    }

    public void setTimestampRes(Date timestampRes) {
        this.timestampRes = timestampRes;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getBrower() {
        return brower;
    }

    public void setBrower(String brower) {
        this.brower = brower;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logid != null ? logid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Q_User_Log)) {
            return false;
        }
        Q_User_Log other = (Q_User_Log) object;
        if ((this.logid == null && other.logid != null) || (this.logid != null && !this.logid.equals(other.logid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.admin.Q_User_Log[ logid=" + logid + " ]";
    }

}
