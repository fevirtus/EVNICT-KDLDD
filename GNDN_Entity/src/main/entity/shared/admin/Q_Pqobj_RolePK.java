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
public class Q_Pqobj_RolePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ROLEID")
    private String roleid;
    @Basic(optional = false)
    @Column(name = "OBJID")
    private String objid;
    @Basic(optional = false)
    @Column(name = "OBJTYPEID")
    private String objtypeid;

    public Q_Pqobj_RolePK() {
    }

    public Q_Pqobj_RolePK(String roleid, String objid, String objtypeid) {
        this.roleid = roleid;
        this.objid = objid;
        this.objtypeid = objtypeid;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getObjid() {
        return objid;
    }

    public void setObjid(String objid) {
        this.objid = objid;
    }

    public String getObjtypeid() {
        return objtypeid;
    }

    public void setObjtypeid(String objtypeid) {
        this.objtypeid = objtypeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleid != null ? roleid.hashCode() : 0);
        hash += (objid != null ? objid.hashCode() : 0);
        hash += (objtypeid != null ? objtypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Q_Pqobj_RolePK)) {
            return false;
        }
        Q_Pqobj_RolePK other = (Q_Pqobj_RolePK) object;
        if ((this.roleid == null && other.roleid != null) || (this.roleid != null && !this.roleid.equals(other.roleid))) {
            return false;
        }
        if ((this.objid == null && other.objid != null) || (this.objid != null && !this.objid.equals(other.objid))) {
            return false;
        }
        if ((this.objtypeid == null && other.objtypeid != null) || (this.objtypeid != null && !this.objtypeid.equals(other.objtypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.admin.Q_Pqobj_RolePK[roleid=" + roleid + ", objid=" + objid + ", objtypeid=" + objtypeid + "]";
    }

    public String idString() {
        return roleid + "." + objid + "." + objtypeid;
    }

}
