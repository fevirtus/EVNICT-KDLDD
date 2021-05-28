/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.admin;

import main.entity.shared.system.S_Lst_Obj;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author thaodd
 */
@Entity
@Table(name = "Q_PQOBJ_ROLE")
public class Q_Pqobj_Role implements Serializable {

    @Column(name = "R_VIEW")
    private Boolean rView;
    @Column(name = "R_GRANT")
    private Boolean rGrant;
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
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Q_Pqobj_RolePK id;
    @Column(name = "CREATOR")
    private Boolean creator;
    @Column(name = "R_EDIT")
    private Boolean r_edit;
    @Column(name = "R_DELETE")
    private Boolean r_delete;
    @Column(name = "R_FULL")
    private Boolean r_full;
    @JoinColumn(name = "OBJTYPEID", referencedColumnName = "OBJTYPEID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private S_Lst_Obj s_lst_obj;
    @JoinColumn(name = "ROLEID", referencedColumnName = "ROLEID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Q_Role q_role;
    @Column(name = "OP")
    private Boolean op;
    @Column(name = "PM")
    private Boolean pm;
    @Column(name = "PPR")
    private Boolean ppr;
    @Column(name = "LABASSET")
    private Boolean labAsset;
    @Column(name = "VOP")
    private Boolean vop;
    @Transient
    private Boolean inherit;

    public Q_Pqobj_Role() {
    }

    public Q_Pqobj_Role(Q_Pqobj_RolePK q_Pqobj_RolePK) {
        this.id = q_Pqobj_RolePK;
    }

    public Q_Pqobj_Role(String roleid, String objid, String objtypeid) {
        this.id = new Q_Pqobj_RolePK(roleid, objid, objtypeid);
    }

    public Q_Pqobj_RolePK getId() {
        return id;
    }

    public void setId(Q_Pqobj_RolePK id) {
        this.id = id;
    }

    public Boolean getCreator() {
        return creator;
    }

    public void setCreator(Boolean creator) {
        this.creator = creator;
    }

    public Q_Role getQ_role() {
        return q_role;
    }

    public void setQ_role(Q_Role q_role) {
        this.q_role = q_role;
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

    public S_Lst_Obj getS_lst_obj() {
        return s_lst_obj;
    }

    public void setS_lst_obj(S_Lst_Obj s_lst_obj) {
        this.s_lst_obj = s_lst_obj;
    }

    public Boolean getOp() {
        return op;
    }

    public void setOp(Boolean op) {
        this.op = op;
    }

    public Boolean getPm() {
        return pm;
    }

    public void setPm(Boolean pm) {
        this.pm = pm;
    }

    public Boolean getPpr() {
        return ppr;
    }

    public void setPpr(Boolean ppr) {
        this.ppr = ppr;
    }

    public Boolean getLabAsset() {
        return labAsset;
    }

    public void setLabAsset(Boolean labAsset) {
        this.labAsset = labAsset;
    }

    public Boolean getVop() {
        return vop;
    }

    public void setVop(Boolean vop) {
        this.vop = vop;
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
        if (!(object instanceof Q_Pqobj_Role)) {
            return false;
        }
        Q_Pqobj_Role other = (Q_Pqobj_Role) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.admin.Q_Pqobj_Role[q_Pqobj_RolePK=" + id + "]";
    }

    public Boolean getRView() {
        return rView;
    }

    public void setRView(Boolean rView) {
        this.rView = rView;
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

    public Boolean getRGrant() {
        return rGrant;
    }

    public void setRGrant(Boolean rGrant) {
        this.rGrant = rGrant;
    }

    public Boolean getInherit() {
        return inherit;
    }

    public void setInherit(Boolean inherit) {
        this.inherit = inherit;
    }

}
