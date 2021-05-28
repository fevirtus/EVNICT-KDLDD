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
import javax.persistence.Table;

/**
 *
 * @author thaodd
 */
@Entity
@Table(name = "Q_ORG_GRANT_ROLE")
//@NamedQueries({
//    @NamedQuery(name = "Q_Org_Grant_Role.findAll", query = "SELECT q FROM Q_Org_Grant_Role q"),
//    @NamedQuery(name = "Q_Org_Grant_Role.findByRoleid", query = "SELECT q FROM Q_Org_Grant_Role q WHERE q.q_Org_Grant_RolePK.roleid = :roleid"),
//    @NamedQuery(name = "Q_Org_Grant_Role.findByOrgid", query = "SELECT q FROM Q_Org_Grant_Role q WHERE q.q_Org_Grant_RolePK.orgid = :orgid"),
//    @NamedQuery(name = "Q_Org_Grant_Role.findByReadonly", query = "SELECT q FROM Q_Org_Grant_Role q WHERE q.readonly = :readonly")})
public class Q_Org_Grant_Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Q_Org_Grant_RolePK id;
    @Column(name = "READONLY")
    private Boolean readonly;
    @JoinColumn(name = "ORGID", referencedColumnName = "ORGID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private S_Organization sOrganization;
    @JoinColumn(name = "ROLEID", referencedColumnName = "ROLEID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Q_Role qRole;

    public Q_Org_Grant_Role() {
    }

    public Q_Org_Grant_Role(Q_Org_Grant_RolePK q_Org_Grant_RolePK) {
        this.id = q_Org_Grant_RolePK;
    }

    public Q_Org_Grant_Role(String roleid, String orgid) {
        this.id = new Q_Org_Grant_RolePK(roleid, orgid);
    }

    public Q_Org_Grant_RolePK getId() {
        return id;
    }

    public void setId(Q_Org_Grant_RolePK q_Org_Grant_RolePK) {
        this.id = q_Org_Grant_RolePK;
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

    public Q_Role getQRole() {
        return qRole;
    }

    public void setQRole(Q_Role qRole) {
        this.qRole = qRole;
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
        if (!(object instanceof Q_Org_Grant_Role)) {
            return false;
        }
        Q_Org_Grant_Role other = (Q_Org_Grant_Role) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shared.Q_Org_Grant_Role[q_Org_Grant_RolePK=" + id + "]";
    }

}
