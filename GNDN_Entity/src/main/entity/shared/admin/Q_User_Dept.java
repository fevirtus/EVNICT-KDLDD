/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.admin;

import main.entity.shared.system.S_Dept;
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
 * @author Administrator
 */
@Entity
@Table(name = "Q_USER_DEPT")
@NamedQueries({
    @NamedQuery(name = "Q_User_Dept.findAll", query = "SELECT q FROM Q_User_Dept q"),
    @NamedQuery(name = "Q_User_Dept.findByUserid", query = "SELECT q FROM Q_User_Dept q WHERE q.q_User_DeptPK.userid = :userid"),
    @NamedQuery(name = "Q_User_Dept.findByDeptid", query = "SELECT q FROM Q_User_Dept q WHERE q.q_User_DeptPK.deptid = :deptid"),
    @NamedQuery(name = "Q_User_Dept.findByNote", query = "SELECT q FROM Q_User_Dept q WHERE q.note = :note")})
public class Q_User_Dept implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Q_User_DeptPK q_User_DeptPK;
    @Column(name = "NOTE")
    private String note;

    @JoinColumn(name = "DEPTID", referencedColumnName = "DEPTID", unique = false, insertable = false, updatable = false, nullable = true)
    @ManyToOne(optional = false)
    private S_Dept sDept;

    public S_Dept getsDept() {
        return sDept;
    }

    public void setsDept(S_Dept sDept) {
        this.sDept = sDept;
    }


    public Q_User_Dept() {
    }

    public Q_User_Dept(Q_User_DeptPK q_User_DeptPK) {
        this.q_User_DeptPK = q_User_DeptPK;
    }

    public Q_User_Dept(String userid, String deptid) {
        this.q_User_DeptPK = new Q_User_DeptPK(userid, deptid);
    }

    public Q_User_DeptPK getQ_User_DeptPK() {
        return q_User_DeptPK;
    }

    public void setQ_User_DeptPK(Q_User_DeptPK q_User_DeptPK) {
        this.q_User_DeptPK = q_User_DeptPK;
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
        hash += (q_User_DeptPK != null ? q_User_DeptPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Q_User_Dept)) {
            return false;
        }
        Q_User_Dept other = (Q_User_Dept) object;
        if ((this.q_User_DeptPK == null && other.q_User_DeptPK != null) || (this.q_User_DeptPK != null && !this.q_User_DeptPK.equals(other.q_User_DeptPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.admin.Q_User_Dept[q_User_DeptPK=" + q_User_DeptPK + "]";
    }

}
