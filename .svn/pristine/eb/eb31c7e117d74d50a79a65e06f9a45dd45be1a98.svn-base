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
 * @author khiemvk
 */
@Embeddable
public class S_Attribute_Group_AssobjPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "OBJID")
    private String objid;
    @Basic(optional = false)
    @Column(name = "OBJTYPEID")
    private String objtypeid;
    @Basic(optional = false)
    @Column(name = "ATTRGROUPID")
    private String attrgroupid;

    public S_Attribute_Group_AssobjPK() {
    }

    public S_Attribute_Group_AssobjPK(String objid, String objtypeid, String attrgroupid) {
        this.objid = objid;
        this.objtypeid = objtypeid;
        this.attrgroupid = attrgroupid;
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

    public String getAttrgroupid() {
        return attrgroupid;
    }

    public void setAttrgroupid(String attrgroupid) {
        this.attrgroupid = attrgroupid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (objid != null ? objid.hashCode() : 0);
        hash += (objtypeid != null ? objtypeid.hashCode() : 0);
        hash += (attrgroupid != null ? attrgroupid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Attribute_Group_AssobjPK)) {
            return false;
        }
        S_Attribute_Group_AssobjPK other = (S_Attribute_Group_AssobjPK) object;
        if ((this.objid == null && other.objid != null) || (this.objid != null && !this.objid.equals(other.objid))) {
            return false;
        }
        if ((this.objtypeid == null && other.objtypeid != null) || (this.objtypeid != null && !this.objtypeid.equals(other.objtypeid))) {
            return false;
        }
        if ((this.attrgroupid == null && other.attrgroupid != null) || (this.attrgroupid != null && !this.attrgroupid.equals(other.attrgroupid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Shared.S_Attribute_Group_AssobjPK[objid=" + objid + ", objtypeid=" + objtypeid + ", attrgroupid=" + attrgroupid + "]";
    }

}
