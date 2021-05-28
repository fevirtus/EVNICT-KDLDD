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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Q_FUNCTION_WFLOW")
@NamedQueries({
    @NamedQuery(name = "Q_Function_Wflow.findAll", query = "SELECT q FROM Q_Function_Wflow q")})
public class Q_Function_Wflow implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WFLOWID")
    private String wflowid;
    @Column(name = "WFLOWID_PARENT")
    private String wflowidParent;
    @Column(name = "WFLOW_DESC")
    private String wflowDesc;
    @Column(name = "WFLOW_CODE")
    private String wflowCode;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "USER_CR_ID")
    private String userCrId;
    @Column(name = "USER_CR_DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userCrDtime;
    @JoinColumn(name = "FUNCTIONID", referencedColumnName = "FUNCTIONID")
    @ManyToOne(optional = false)
    private Q_Function sfunctionId;
    @Column(name = "ENABLE")
    private Boolean enable;
    @Column(name = "SYS")
    private Boolean sys;

    public Q_Function_Wflow() {
    }

    public Q_Function_Wflow(String wflowid) {
        this.wflowid = wflowid;
    }

    public String getWflowid() {
        return wflowid;
    }

    public void setWflowid(String wflowid) {
        this.wflowid = wflowid;
    }

    public String getWflowDesc() {
        return wflowDesc;
    }

    public void setWflowDesc(String wflowDesc) {
        this.wflowDesc = wflowDesc;
    }

    public String getUserCrId() {
        return userCrId;
    }

    public void setUserCrId(String userCrId) {
        this.userCrId = userCrId;
    }

    public Date getUserCrDtime() {
        return userCrDtime;
    }

    public void setUserCrDtime(Date userCrDtime) {
        this.userCrDtime = userCrDtime;
    }

    public Q_Function getSfunctionId() {
        return sfunctionId;
    }

    public void setSfunctionId(Q_Function sfunctionId) {
        this.sfunctionId = sfunctionId;
    }

    public String getWflowCode() {
        return wflowCode;
    }

    public void setWflowCode(String wflowCode) {
        this.wflowCode = wflowCode;
    }

    public String getWflowidParent() {
        return wflowidParent;
    }

    public void setWflowidParent(String wflowidParent) {
        this.wflowidParent = wflowidParent;
    }

    public Boolean isEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean isSys() {
        return sys;
    }

    public void setSys(Boolean sys) {
        this.sys = sys;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wflowid != null ? wflowid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Q_Function_Wflow)) {
            return false;
        }
        Q_Function_Wflow other = (Q_Function_Wflow) object;
        if ((this.wflowid == null && other.wflowid != null) || (this.wflowid != null && !this.wflowid.equals(other.wflowid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.admin.Q_Function_Wflow[ wflowid=" + wflowid + " ]";
    }

}
