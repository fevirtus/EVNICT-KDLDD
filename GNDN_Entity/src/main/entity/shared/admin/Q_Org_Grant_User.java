/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.admin;

import main.entity.shared.system.S_Organization;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author thaodd
 */
@Entity
@Table(name = "Q_ORG_GRANT_USER")
//@NamedQueries({
//    @NamedQuery(name = "Q_Org_Grant_User.findAll", query = "SELECT q FROM Q_Org_Grant_User q"),
//    @NamedQuery(name = "Q_Org_Grant_User.findByUserid", query = "SELECT q FROM Q_Org_Grant_User q WHERE q.q_Org_Grant_UserPK.userid = :userid"),
//    @NamedQuery(name = "Q_Org_Grant_User.findByOrgid", query = "SELECT q FROM Q_Org_Grant_User q WHERE q.q_Org_Grant_UserPK.orgid = :orgid"),
//    @NamedQuery(name = "Q_Org_Grant_User.findByReadonly", query = "SELECT q FROM Q_Org_Grant_User q WHERE q.readonly = :readonly")})
public class Q_Org_Grant_User implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Q_Org_Grant_UserPK id;
    @Column(name = "READONLY")
    private Boolean readonly;
    @JoinColumn(name = "ORGID", referencedColumnName = "ORGID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private S_Organization sOrganization;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Q_User qUser;

    public Q_Org_Grant_User() {
    }

    public Q_Org_Grant_User(Q_Org_Grant_UserPK q_Org_Grant_UserPK) {
        this.id = q_Org_Grant_UserPK;
    }

    public Q_Org_Grant_User(String userid, String orgid) {
        this.id = new Q_Org_Grant_UserPK(userid, orgid);
    }

    public Q_Org_Grant_UserPK getId() {
        return id;
    }

    public void setId(Q_Org_Grant_UserPK q_Org_Grant_UserPK) {
        this.id = q_Org_Grant_UserPK;
    }

    public Boolean getReadonly() {
        return readonly;
    }

    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }

    public S_Organization getSOrganization() {
        return sOrganization;
    }

    public void setSOrganization(S_Organization sOrganization) {
        this.sOrganization = sOrganization;
    }

    public Q_User getQUser() {
        return qUser;
    }

    public void setQUser(Q_User qUser) {
        this.qUser = qUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Q_Org_Grant_User)) {
            return false;
        }
        Q_Org_Grant_User other = (Q_Org_Grant_User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shared.Q_Org_Grant_User[q_Org_Grant_UserPK=" + id + "]";
    }

}
