/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.admin;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author sypv
 */
@Embeddable
public class Q_Pqfunction_User_WflowPK implements Serializable {
    @Basic(optional = false)    
    @Column(name = "USERID")
    private String userid;
    @Basic(optional = false)    
    @Column(name = "WFLOWID")
    private String wflowid;

    public Q_Pqfunction_User_WflowPK() {
    }

    public Q_Pqfunction_User_WflowPK(String userid, String wflowid) {
        this.userid = userid;
        this.wflowid = wflowid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getWflowid() {
        return wflowid;
    }

    public void setWflowid(String wflowid) {
        this.wflowid = wflowid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        hash += (wflowid != null ? wflowid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Q_Pqfunction_User_WflowPK)) {
            return false;
        }
        Q_Pqfunction_User_WflowPK other = (Q_Pqfunction_User_WflowPK) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        if ((this.wflowid == null && other.wflowid != null) || (this.wflowid != null && !this.wflowid.equals(other.wflowid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.admin.Q_Pqfunction_User_FlowPK[ userid=" + userid + ", wflowid=" + wflowid + " ]";
    }
    
}
