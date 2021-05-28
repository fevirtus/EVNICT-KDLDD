/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.portlet;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "SM_PORTLET_GROUP")
public class Sm_Portlet_Group implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PLTGROUP_ID")
    private String pltGroupid;
    @Column(name = "PLTGROUPDESC")
    private String pltGroupDesc;
    @Column(name = "ORGID")
    private String orgId;
    @Transient
    private Boolean bChecked;
    
    public Sm_Portlet_Group() {
    }

    public Sm_Portlet_Group(String pltGroupid) {
        this.pltGroupid = pltGroupid;
    }

    public String getPltGroupid() {
        return pltGroupid;
    }

    public void setPltGroupid(String pltGroupid) {
        this.pltGroupid = pltGroupid;
    }

    public String getPltGroupDesc() {
        return pltGroupDesc;
    }

    public void setPltGroupDesc(String pltGroupDesc) {
        this.pltGroupDesc = pltGroupDesc;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Boolean getbChecked() {
        return bChecked;
    }

    public void setbChecked(Boolean bChecked) {
        this.bChecked = bChecked;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pltGroupid != null ? pltGroupid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sm_Portlet_Group)) {
            return false;
        }
        Sm_Portlet_Group other = (Sm_Portlet_Group) object;
        if ((this.pltGroupid == null && other.pltGroupid != null) || (this.pltGroupid != null && !this.pltGroupid.equals(other.pltGroupid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.Sm_Portlet_Group[pltid=" + pltGroupid + "]";
    }

}
