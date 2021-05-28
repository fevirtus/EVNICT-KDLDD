/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "S_ATTRIBUTE_VAL_HIS")
public class S_Attribute_Val_His implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ATTRHISDATAID")
    private String attrhisdataid;
    @Column(name = "ATTRID")
    private String attrid;
    @Column(name = "OBJID")
    private String objid;
    @Column(name = "OBJTYPEID")
    private String objtypeid;
    @Column(name = "DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtime;
    @Column(name = "VAL_S")
    private String valS;

    @Transient
    private Object valSObject;

    public Object getValSObject() {
        return valSObject;
    }

    public void setValSObject(Object valSObject) {
        this.valSObject = valSObject;
    }

    public String getAttrid() {
        return attrid;
    }

    public void setAttrid(String attrid) {
        this.attrid = attrid;
    }

    public String getObjtypeid() {
        return objtypeid;
    }

    public void setObjtypeid(String objtypeid) {
        this.objtypeid = objtypeid;
    }

    public S_Attribute_Val_His() {
    }

    public S_Attribute_Val_His(String attrhisdataid) {
        this.attrhisdataid = attrhisdataid;
    }

    public String getAttrhisdataid() {
        return attrhisdataid;
    }

    public void setAttrhisdataid(String attrhisdataid) {
        this.attrhisdataid = attrhisdataid;
    }

    public String getObjid() {
        return objid;
    }

    public void setObjid(String objid) {
        this.objid = objid;
    }

    public Date getDtime() {
        return dtime;
    }

    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }

    public String getValS() {
        return valS;
    }

    public void setValS(String valS) {
        this.valS = valS;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attrhisdataid != null ? attrhisdataid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Attribute_Val_His)) {
            return false;
        }
        S_Attribute_Val_His other = (S_Attribute_Val_His) object;
        if ((this.attrhisdataid == null && other.attrhisdataid != null) || (this.attrhisdataid != null && !this.attrhisdataid.equals(other.attrhisdataid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.S_Attribute_Val_His[attrhisdataid=" + attrhisdataid + "]";
    }

}
