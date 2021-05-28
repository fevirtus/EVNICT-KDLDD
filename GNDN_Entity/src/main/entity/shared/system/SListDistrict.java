/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ChuotCong
 */
@Entity
@Table(name = "S_LIST_DISTRICT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SListDistrict.findAll", query = "SELECT s FROM SListDistrict s"),
    @NamedQuery(name = "SListDistrict.findByDistrictid", query = "SELECT s FROM SListDistrict s WHERE s.districtid = :districtid"),
    @NamedQuery(name = "SListDistrict.findByDistrictdesc", query = "SELECT s FROM SListDistrict s WHERE s.districtdesc = :districtdesc"),
    @NamedQuery(name = "SListDistrict.findByDistrictcode", query = "SELECT s FROM SListDistrict s WHERE s.districtcode = :districtcode"),
    @NamedQuery(name = "SListDistrict.findByActive", query = "SELECT s FROM SListDistrict s WHERE s.active = :active"),
    @NamedQuery(name = "SListDistrict.findByDistrictord", query = "SELECT s FROM SListDistrict s WHERE s.districtord = :districtord")})
public class SListDistrict implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DISTRICTID")
    private String districtid;
    @Basic(optional = false)
    @Column(name = "DISTRICTDESC")
    private String districtdesc;
    @Column(name = "DISTRICTCODE")
    private String districtcode;
    @Basic(optional = false)
    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "DISTRICTORD")
    private Integer districtord;
    @JoinColumn(name = "PROVINCEID", referencedColumnName = "PROVINCEID")
    @ManyToOne(optional = false)
    private SListProvince provinceid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "districtid")
    private List<SListVillage> sListVillageList;

    public SListDistrict() {
    }

    public SListDistrict(String districtid) {
        this.districtid = districtid;
    }

    public SListDistrict(String districtid, String districtdesc, boolean active) {
        this.districtid = districtid;
        this.districtdesc = districtdesc;
        this.active = active;
    }

    public String getDistrictid() {
        return districtid;
    }

    public void setDistrictid(String districtid) {
        this.districtid = districtid;
    }

    public String getDistrictdesc() {
        return districtdesc;
    }

    public void setDistrictdesc(String districtdesc) {
        this.districtdesc = districtdesc;
    }

    public String getDistrictcode() {
        return districtcode;
    }

    public void setDistrictcode(String districtcode) {
        this.districtcode = districtcode;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getDistrictord() {
        return districtord;
    }

    public void setDistrictord(Integer districtord) {
        this.districtord = districtord;
    }

    public SListProvince getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(SListProvince provinceid) {
        this.provinceid = provinceid;
    }

    @XmlTransient
    public List<SListVillage> getSListVillageList() {
        return sListVillageList;
    }

    public void setSListVillageList(List<SListVillage> sListVillageList) {
        this.sListVillageList = sListVillageList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (districtid != null ? districtid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SListDistrict)) {
            return false;
        }
        SListDistrict other = (SListDistrict) object;
        if ((this.districtid == null && other.districtid != null) || (this.districtid != null && !this.districtid.equals(other.districtid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.SListDistrict[ districtid=" + districtid + " ]";
    }
    
}
