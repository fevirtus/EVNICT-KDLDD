/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author khiemvk
 */
@Entity
@Table(name = "S_UOM", uniqueConstraints = {})
public class S_Uom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UOMID")
    private String uomid;
    @Column(name = "UOMDESC")
    private String uomdesc;    
    @Transient
    private Boolean bChecked;
    public S_Uom() {
    }

    public S_Uom(String uomid) {
        this.uomid = uomid;
    }

    public S_Uom(String uomid,String uomdesc) {
        this.uomid = uomid;
        this.uomdesc = uomdesc;
    }

    public String getUomid() {
        return uomid;
    }

    public void setUomid(String uomid) {
        this.uomid = uomid;
    }

    public String getUomdesc() {
        return uomdesc;
    }

    public void setUomdesc(String uomdesc) {
        this.uomdesc = uomdesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uomid != null ? uomid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Uom)) {
            return false;
        }
        S_Uom other = (S_Uom) object;
        if ((this.uomid == null && other.uomid != null) || (this.uomid != null && !this.uomid.equals(other.uomid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Shared.Entity.S_Uom[uomid=" + uomid + "]";
    }
     public Boolean getbChecked() {
        if (bChecked != null) {
            return bChecked;
        }
        return false;
    }

    public void setbChecked(Boolean bChecked) {
        this.bChecked = bChecked;
    }
}
