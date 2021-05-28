/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author thaodd
 */
@Embeddable
public class Log_ObjreadPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "OBJID")
    private String objid;
    @Basic(optional = false)
    @Column(name = "OBJTYPEID")
    private String objtypeid;
    @Basic(optional = false)
    @Column(name = "USERID")
    private String userid;

    public Log_ObjreadPK() {
    }

    public Log_ObjreadPK(String objid, String objtypeid, String userid) {
        this.objid = objid;
        this.objtypeid = objtypeid;
        this.userid = userid;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (objid != null ? objid.hashCode() : 0);
        hash += (objtypeid != null ? objtypeid.hashCode() : 0);
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Log_ObjreadPK)) {
            return false;
        }
        Log_ObjreadPK other = (Log_ObjreadPK) object;
        if ((this.objid == null && other.objid != null) || (this.objid != null && !this.objid.equals(other.objid))) {
            return false;
        }
        if ((this.objtypeid == null && other.objtypeid != null) || (this.objtypeid != null && !this.objtypeid.equals(other.objtypeid))) {
            return false;
        }
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evnit.tms.ejb.shared.system.Log_ObjreadPK[objid=" + objid + ", objtypeid=" + objtypeid + ", userid=" + userid + "]";
    }

}
