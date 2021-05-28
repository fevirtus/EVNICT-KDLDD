/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.system;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author abato
 */
@Entity
@Table(name = "S_PLANT", uniqueConstraints = {})
public class S_Plant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PLANTID")
    private String plantid;
    @Column(name = "ORGID")
    private String orgid;
    @Basic(optional = false)
    @Column(name = "PLANTDESC")
    private String plantdesc;
    @Column(name = "PLANTORD")
    private Integer plantord;
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ORGID", unique = false, insertable = false, updatable = false, nullable = true)
    private S_Organization sOrganization;
    
    public S_Plant() {
    }

    public S_Plant(String plantid) {
        this.plantid = plantid;
    }

    public S_Plant(String plantid, String plantdesc) {
        this.plantid = plantid;
        this.plantdesc = plantdesc;
    }

    public String getPlantdesc() {
        return plantdesc;
    }

    public void setPlantdesc(String plantdesc) {
        this.plantdesc = plantdesc;
    }

    public String getPlantid() {
        return plantid;
    }

    public void setPlantid(String plantid) {
        this.plantid = plantid;
    }

    public Integer getPlantord() {
        return plantord;
    }

    public void setPlantord(Integer plantord) {
        this.plantord = plantord;
    }
   
    public S_Organization getSOrganization() {
        return sOrganization;
    }

    public void setSOrganization(S_Organization sOrganization) {
        this.sOrganization = sOrganization;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public S_Organization getsOrganization() {
        return sOrganization;
    }

    public void setsOrganization(S_Organization sOrganization) {
        this.sOrganization = sOrganization;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plantid != null ? plantid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Site)) {
            return false;
        }
        S_Plant other = (S_Plant) object;
        if ((this.plantid == null && other.plantid != null) || (this.plantid != null && !this.plantid.equals(other.plantid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return plantdesc;
    }
}
