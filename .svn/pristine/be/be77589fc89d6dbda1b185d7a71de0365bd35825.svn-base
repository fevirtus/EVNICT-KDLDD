/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ChuotCong
 */
@Entity
@Table(name = "S_LIST_VILLAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SListVillage.findAll", query = "SELECT s FROM SListVillage s"),
    @NamedQuery(name = "SListVillage.findByVillageid", query = "SELECT s FROM SListVillage s WHERE s.villageid = :villageid"),
    @NamedQuery(name = "SListVillage.findByVillagedesc", query = "SELECT s FROM SListVillage s WHERE s.villagedesc = :villagedesc"),
    @NamedQuery(name = "SListVillage.findByVillagecode", query = "SELECT s FROM SListVillage s WHERE s.villagecode = :villagecode"),
    @NamedQuery(name = "SListVillage.findByActive", query = "SELECT s FROM SListVillage s WHERE s.active = :active"),
    @NamedQuery(name = "SListVillage.findByVillageord", query = "SELECT s FROM SListVillage s WHERE s.villageord = :villageord")})
public class SListVillage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "VILLAGEID")
    private String villageid;
    @Basic(optional = false)
    @Column(name = "VILLAGEDESC")
    private String villagedesc;
    @Column(name = "VILLAGECODE")
    private String villagecode;
    @Basic(optional = false)
    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "VILLAGEORD")
    private Integer villageord;
    @JoinColumn(name = "DISTRICTID", referencedColumnName = "DISTRICTID")
    @ManyToOne(optional = false)
    private SListDistrict districtid;

    public SListVillage() {
    }

    public SListVillage(String villageid) {
        this.villageid = villageid;
    }

    public SListVillage(String villageid, String villagedesc, boolean active) {
        this.villageid = villageid;
        this.villagedesc = villagedesc;
        this.active = active;
    }

    public String getVillageid() {
        return villageid;
    }

    public void setVillageid(String villageid) {
        this.villageid = villageid;
    }

    public String getVillagedesc() {
        return villagedesc;
    }

    public void setVillagedesc(String villagedesc) {
        this.villagedesc = villagedesc;
    }

    public String getVillagecode() {
        return villagecode;
    }

    public void setVillagecode(String villagecode) {
        this.villagecode = villagecode;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getVillageord() {
        return villageord;
    }

    public void setVillageord(Integer villageord) {
        this.villageord = villageord;
    }

    public SListDistrict getDistrictid() {
        return districtid;
    }

    public void setDistrictid(SListDistrict districtid) {
        this.districtid = districtid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (villageid != null ? villageid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SListVillage)) {
            return false;
        }
        SListVillage other = (SListVillage) object;
        if ((this.villageid == null && other.villageid != null) || (this.villageid != null && !this.villageid.equals(other.villageid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.SListVillage[ villageid=" + villageid + " ]";
    }
    
}
