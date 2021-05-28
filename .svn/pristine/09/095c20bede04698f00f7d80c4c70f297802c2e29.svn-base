/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.admin;

import main.entity.shared.system.S_Dept;
import main.entity.shared.system.S_Lst_Obj;
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
@Table(name = "Q_PQOBJ_DEPT")
//@NamedQueries({
//    @NamedQuery(name = "Q_Pqobj_Dept.findAll", query = "SELECT q FROM Q_Pqobj_Dept q"),
//    @NamedQuery(name = "Q_Pqobj_Dept.findByDeptid", query = "SELECT q FROM Q_Pqobj_Dept q WHERE q.q_Pqobj_DeptPK.deptid = :deptid"),
//    @NamedQuery(name = "Q_Pqobj_Dept.findByObjid", query = "SELECT q FROM Q_Pqobj_Dept q WHERE q.q_Pqobj_DeptPK.objid = :objid"),
//    @NamedQuery(name = "Q_Pqobj_Dept.findByObjtypeid", query = "SELECT q FROM Q_Pqobj_Dept q WHERE q.q_Pqobj_DeptPK.objtypeid = :objtypeid"),
//    @NamedQuery(name = "Q_Pqobj_Dept.findByREdit", query = "SELECT q FROM Q_Pqobj_Dept q WHERE q.rEdit = :rEdit"),
//    @NamedQuery(name = "Q_Pqobj_Dept.findByRDelete", query = "SELECT q FROM Q_Pqobj_Dept q WHERE q.rDelete = :rDelete"),
//    @NamedQuery(name = "Q_Pqobj_Dept.findByRFull", query = "SELECT q FROM Q_Pqobj_Dept q WHERE q.rFull = :rFull")})
public class Q_Pqobj_Dept implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Q_Pqobj_DeptPK id;
    @Column(name = "R_EDIT")
    private Boolean r_edit;
    @Column(name = "R_DELETE")
    private Boolean r_delete;
    @Column(name = "R_FULL")
    private Boolean r_full;
    @JoinColumn(name = "OBJTYPEID", referencedColumnName = "OBJTYPEID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private S_Lst_Obj s_lst_obj;
    @JoinColumn(name = "DEPTID", referencedColumnName = "DEPTID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private S_Dept s_dept;

    public Q_Pqobj_Dept() {
    }

    public Q_Pqobj_Dept(Q_Pqobj_DeptPK q_Pqobj_DeptPK) {
        this.id = q_Pqobj_DeptPK;
    }

    public Q_Pqobj_Dept(String deptid, String objid, String objtypeid) {
        this.id = new Q_Pqobj_DeptPK(deptid, objid, objtypeid);
    }

    public Q_Pqobj_DeptPK getId() {
        return id;
    }

    public void setId(Q_Pqobj_DeptPK id) {
        this.id = id;
    }

    public Boolean getR_delete() {
        return r_delete;
    }

    public void setR_delete(Boolean r_delete) {
        this.r_delete = r_delete;
    }

    public Boolean getR_edit() {
        return r_edit;
    }

    public void setR_edit(Boolean r_edit) {
        this.r_edit = r_edit;
    }

    public Boolean getR_full() {
        return r_full;
    }

    public void setR_full(Boolean r_full) {
        this.r_full = r_full;
    }

    public S_Dept getS_dept() {
        return s_dept;
    }

    public void setS_dept(S_Dept s_dept) {
        this.s_dept = s_dept;
    }

    public S_Lst_Obj getS_lst_obj() {
        return s_lst_obj;
    }

    public void setS_lst_obj(S_Lst_Obj s_lst_obj) {
        this.s_lst_obj = s_lst_obj;
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
        if (!(object instanceof Q_Pqobj_Dept)) {
            return false;
        }
        Q_Pqobj_Dept other = (Q_Pqobj_Dept) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.admin.Q_Pqobj_Dept[q_Pqobj_DeptPK=" + id + "]";
    }

}
