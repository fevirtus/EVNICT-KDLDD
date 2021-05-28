/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.admin;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author thaodd
 */
@Embeddable
public class Q_Org_Grant_RolePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ROLEID")
    private String roleid;
    @Basic(optional = false)
    @Column(name = "ORGID")
    private String orgid;

    public Q_Org_Grant_RolePK() {
    }

    public Q_Org_Grant_RolePK(String roleid, String orgid) {
        this.roleid = roleid;
        this.orgid = orgid;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleid != null ? roleid.hashCode() : 0);
        hash += (orgid != null ? orgid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Q_Org_Grant_RolePK)) {
            return false;
        }
        Q_Org_Grant_RolePK other = (Q_Org_Grant_RolePK) object;
        if ((this.roleid == null && other.roleid != null) || (this.roleid != null && !this.roleid.equals(other.roleid))) {
            return false;
        }
        if ((this.orgid == null && other.orgid != null) || (this.orgid != null && !this.orgid.equals(other.orgid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shared.Q_Org_Grant_RolePK[roleid=" + roleid + ", orgid=" + orgid + "]";
    }

}
