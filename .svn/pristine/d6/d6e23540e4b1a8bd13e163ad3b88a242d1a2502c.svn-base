/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.admin;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sypv
 */
@Entity
@Table(name = "Q_PQFUNCTION_ROLE_WFLOW")
public class Q_Pqfunction_Role_Wflow implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Q_Pqfunction_Role_WflowPK q_Pqfunction_Role_FlowPK;
    @Column(name = "USER_CR_ID")
    private String userCrId;
    @Column(name = "USER_CR_DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userCrDtime;    
    @Column(name = "USER_MDF_ID")
    private String userMdfId;
    @Column(name = "USER_MDF_DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userMdfDtime;

    public Q_Pqfunction_Role_Wflow() {
    }

    public Q_Pqfunction_Role_Wflow(Q_Pqfunction_Role_WflowPK q_Pqfunction_Role_FlowPK) {
        this.q_Pqfunction_Role_FlowPK = q_Pqfunction_Role_FlowPK;
    }

    public Q_Pqfunction_Role_Wflow(String roleid, String wflowid) {
        this.q_Pqfunction_Role_FlowPK = new Q_Pqfunction_Role_WflowPK(roleid, wflowid);
    }

    public Q_Pqfunction_Role_WflowPK getQ_Pqfunction_Role_FlowPK() {
        return q_Pqfunction_Role_FlowPK;
    }

    public void setQ_Pqfunction_Role_FlowPK(Q_Pqfunction_Role_WflowPK q_Pqfunction_Role_FlowPK) {
        this.q_Pqfunction_Role_FlowPK = q_Pqfunction_Role_FlowPK;
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

    public String getUserMdfId() {
        return userMdfId;
    }

    public void setUserMdfId(String userMdfId) {
        this.userMdfId = userMdfId;
    }

    public Date getUserMdfDtime() {
        return userMdfDtime;
    }

    public void setUserMdfDtime(Date userMdfDtime) {
        this.userMdfDtime = userMdfDtime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (q_Pqfunction_Role_FlowPK != null ? q_Pqfunction_Role_FlowPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Q_Pqfunction_Role_Wflow)) {
            return false;
        }
        Q_Pqfunction_Role_Wflow other = (Q_Pqfunction_Role_Wflow) object;
        if ((this.q_Pqfunction_Role_FlowPK == null && other.q_Pqfunction_Role_FlowPK != null) || (this.q_Pqfunction_Role_FlowPK != null && !this.q_Pqfunction_Role_FlowPK.equals(other.q_Pqfunction_Role_FlowPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.admin.Q_Pqfunction_Role_Wflow[ q_Pqfunction_Role_WflowPK=" + q_Pqfunction_Role_FlowPK + " ]";
    }
    
}
