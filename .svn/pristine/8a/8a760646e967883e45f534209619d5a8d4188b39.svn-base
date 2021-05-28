/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author khiemvk
 */
@Entity
@Table(name = "S_ATTRIBUTE_GROUP_ASSOBJ",uniqueConstraints={})
public class S_Attribute_Group_Assobj implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected S_Attribute_Group_AssobjPK id;
    @Column(name = "ATTRGROUPDESC")
    private String attrgroupdesc;
    @Column(name = "ATTRGROUPORD")
    private Integer attrgroupord;
    @Column(name = "ATTRDATAID")
    private String attrdataid;
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "OBJTYPEID", unique = false, insertable = false, updatable = false, nullable = true)
    private S_Lst_Obj sLstObj;
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ATTRGROUPID", unique = false, insertable = false, updatable = false, nullable = true)
    private S_Attribute_Group sAttributeGroup;

    public S_Attribute_Group_Assobj() {
    }

    public S_Attribute_Group_Assobj(S_Attribute_Group_AssobjPK id) {
        this.id = id;
    }

    public S_Attribute_Group_Assobj(String objid, String objtypeid, String attrgroupid) {
        this.id = new S_Attribute_Group_AssobjPK(objid, objtypeid, attrgroupid);
    }

    public S_Attribute_Group_AssobjPK getId() {
        return id;
    }

    public void setId(S_Attribute_Group_AssobjPK id) {
        this.id = id;
    }

    public String getAttrgroupdesc() {
        return attrgroupdesc;
    }

    public void setAttrgroupdesc(String attrgroupdesc) {
        this.attrgroupdesc = attrgroupdesc;
    }

    public Integer getAttrgroupord() {
        return attrgroupord;
    }

    public void setAttrgroupord(Integer attrgroupord) {
        this.attrgroupord = attrgroupord;
    }

    public String getAttrdataid() {
        return attrdataid;
    }

    public void setAttrdataid(String attrdataid) {
        this.attrdataid = attrdataid;
    }

    public S_Lst_Obj getSLstObj() {
        return sLstObj;
    }

    public void setSLstObj(S_Lst_Obj sLstObj) {
        this.sLstObj = sLstObj;
    }

    public S_Attribute_Group getSAttributeGroup() {
        return sAttributeGroup;
    }

    public void setSAttributeGroup(S_Attribute_Group sAttributeGroup) {
        this.sAttributeGroup = sAttributeGroup;
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
        if (!(object instanceof S_Attribute_Group_Assobj)) {
            return false;
        }
        S_Attribute_Group_Assobj other = (S_Attribute_Group_Assobj) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Shared.S_Attribute_Group_Assobj[s_Attribute_Group_AssobjPK=" + id + "]";
    }

}
