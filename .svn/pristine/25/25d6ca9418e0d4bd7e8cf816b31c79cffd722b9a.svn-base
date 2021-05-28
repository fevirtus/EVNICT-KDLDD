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
public class Q_Pqobj_DeptPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "DEPTID")
    private String deptid;
    @Basic(optional = false)
    @Column(name = "OBJID")
    private String objid;
    @Basic(optional = false)
    @Column(name = "OBJTYPEID")
    private String objtypeid;

    public Q_Pqobj_DeptPK() {
    }

    public Q_Pqobj_DeptPK(String deptid, String objid, String objtypeid) {
        this.deptid = deptid;
        this.objid = objid;
        this.objtypeid = objtypeid;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
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
        hash += (deptid != null ? deptid.hashCode() : 0);
        hash += (objid != null ? objid.hashCode() : 0);
        hash += (objtypeid != null ? objtypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Q_Pqobj_DeptPK)) {
            return false;
        }
        Q_Pqobj_DeptPK other = (Q_Pqobj_DeptPK) object;
        if ((this.deptid == null && other.deptid != null) || (this.deptid != null && !this.deptid.equals(other.deptid))) {
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
        return "main.entity.shared.admin.Q_Pqobj_DeptPK[deptid=" + deptid + ", objid=" + objid + ", objtypeid=" + objtypeid + "]";
    }

    public String idString() {
        return deptid + "." + objid + "." + objtypeid;
    }
}
