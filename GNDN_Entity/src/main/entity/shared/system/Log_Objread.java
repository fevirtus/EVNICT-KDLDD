/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author thaodd
 */
@Entity
@Table(name = "LOG_OBJREAD")
//@NamedQueries({
//    @NamedQuery(name = "Log_Objread.findAll", query = "SELECT l FROM Log_Objread l"),
//    @NamedQuery(name = "Log_Objread.findByObjid", query = "SELECT l FROM Log_Objread l WHERE l.log_ObjreadPK.objid = :objid"),
//    @NamedQuery(name = "Log_Objread.findByObjtypeid", query = "SELECT l FROM Log_Objread l WHERE l.log_ObjreadPK.objtypeid = :objtypeid"),
//    @NamedQuery(name = "Log_Objread.findByUserid", query = "SELECT l FROM Log_Objread l WHERE l.log_ObjreadPK.userid = :userid"),
//    @NamedQuery(name = "Log_Objread.findByReaddtime", query = "SELECT l FROM Log_Objread l WHERE l.readdtime = :readdtime"),
//    @NamedQuery(name = "Log_Objread.findByIsreset", query = "SELECT l FROM Log_Objread l WHERE l.isreset = :isreset")})
public class Log_Objread implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Log_ObjreadPK id;
    @Column(name = "READDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date readdtime;
    @Column(name = "ISUPDATE")
    private Boolean isupdate;

    public Log_Objread() {
    }

    public Log_Objread(Log_ObjreadPK log_ObjreadPK) {
        this.id = log_ObjreadPK;
    }

    public Log_Objread(String objid, String objtypeid, String userid) {
        this.id = new Log_ObjreadPK(objid, objtypeid, userid);
    }

    public Log_ObjreadPK getId() {
        return id;
    }

    public void setId(Log_ObjreadPK log_ObjreadPK) {
        this.id = log_ObjreadPK;
    }

    public Date getReaddtime() {
        return readdtime;
    }

    public void setReaddtime(Date readdtime) {
        this.readdtime = readdtime;
    }

    public Boolean getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(Boolean isreset) {
        this.isupdate = isreset;
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
        if (!(object instanceof Log_Objread)) {
            return false;
        }
        Log_Objread other = (Log_Objread) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evnit.tms.ejb.shared.system.Log_Objread[log_ObjreadPK=" + id + "]";
    }

}
