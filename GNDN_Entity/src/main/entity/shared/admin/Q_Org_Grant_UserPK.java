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
public class Q_Org_Grant_UserPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "USERID")
    private String userid;
    @Basic(optional = false)
    @Column(name = "ORGID")
    private String orgid;

    public Q_Org_Grant_UserPK() {
    }

    public Q_Org_Grant_UserPK(String userid, String orgid) {
        this.userid = userid;
        this.orgid = orgid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
        hash += (userid != null ? userid.hashCode() : 0);
        hash += (orgid != null ? orgid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Q_Org_Grant_UserPK)) {
            return false;
        }
        Q_Org_Grant_UserPK other = (Q_Org_Grant_UserPK) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        if ((this.orgid == null && other.orgid != null) || (this.orgid != null && !this.orgid.equals(other.orgid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shared.Q_Org_Grant_UserPK[userid=" + userid + ", orgid=" + orgid + "]";
    }

}
