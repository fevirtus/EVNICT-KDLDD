/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.report;

import main.entity.shared.admin.Q_Pqobj_UserPK;
import main.entity.shared.admin.Q_User;
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
 * @author NAMCV
 */
@Entity
@Table(name = "Q_PQRPORT_USER")
public class Q_PQRPort_User implements Serializable{
    @Column(name = "R_VIEW")
    private Boolean rView;
    @Column(name = "R_GRANT")
    private Boolean rGrant;
    @Column(name = "USER_CR_ID")
    private String userCrId;
    @Column(name = "USER_CR_DTIME")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date userCrDtime;
    @Column(name = "USER_MDF_ID")
    private String userMdfId;
    @Column(name = "USER_MDF_DTIME")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date userMdfDtime;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Q_Pqobj_UserPK id;
    @Column(name = "CREATOR")
    private Boolean creator;
    @JoinColumn(name = "OBJTYPEID", referencedColumnName = "OBJTYPEID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private S_Lst_Obj s_lst_obj;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Q_User q_user;    
    @Transient
    private Boolean inherit;
    
    public Q_PQRPort_User() {}
    
    public Q_PQRPort_User(Q_Pqobj_UserPK q_Pqobj_UserPK) {
        this.id = q_Pqobj_UserPK;
    }
    
    public Q_PQRPort_User(String userid, String objid, String objtypeid){
        this.id = new Q_Pqobj_UserPK(userid, objid, objtypeid);
    }

    public Boolean getRView() {
        return rView;
    }

    public void setRView(Boolean rView) {
        this.rView = rView;
    }

    public Boolean getRGrant() {
        return rGrant;
    }

    public void setRGrant(Boolean rGrant) {
        this.rGrant = rGrant;
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

    public Q_Pqobj_UserPK getId() {
        return id;
    }

    public void setId(Q_Pqobj_UserPK id) {
        this.id = id;
    }

    public Boolean getCreator() {
        return creator;
    }

    public void setCreator(Boolean creator) {
        this.creator = creator;
    }

    public S_Lst_Obj getS_lst_obj() {
        return s_lst_obj;
    }

    public void setS_lst_obj(S_Lst_Obj s_lst_obj) {
        this.s_lst_obj = s_lst_obj;
    }

    public Q_User getQ_user() {
        return q_user;
    }

    public void setQ_user(Q_User q_user) {
        this.q_user = q_user;
    }

    public Boolean getInherit() {
        return inherit;
    }

    public void setInherit(Boolean inherit) {
        this.inherit = inherit;
    }
    
    
    
}
