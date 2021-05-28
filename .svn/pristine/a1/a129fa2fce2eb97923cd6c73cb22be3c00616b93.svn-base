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
@Table(name = "S_LIST_PROVINCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SListProvince.findAll", query = "SELECT s FROM SListProvince s"),
    @NamedQuery(name = "SListProvince.findByProvinceid", query = "SELECT s FROM SListProvince s WHERE s.provinceid = :provinceid"),
    @NamedQuery(name = "SListProvince.findByProvincedesc", query = "SELECT s FROM SListProvince s WHERE s.provincedesc = :provincedesc"),
    @NamedQuery(name = "SListProvince.findByProvincecode", query = "SELECT s FROM SListProvince s WHERE s.provincecode = :provincecode"),
    @NamedQuery(name = "SListProvince.findByActive", query = "SELECT s FROM SListProvince s WHERE s.active = :active"),
    @NamedQuery(name = "SListProvince.findByProvinceord", query = "SELECT s FROM SListProvince s WHERE s.provinceord = :provinceord")})
public class SListProvince implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PROVINCEID")
    private String provinceid;
    @Basic(optional = false)
    @Column(name = "PROVINCEDESC")
    private String provincedesc;
    @Column(name = "PROVINCECODE")
    private String provincecode;
    @Basic(optional = false)
    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "PROVINCEORD")
    private Integer provinceord;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provinceid")
    private List<SListDistrict> sListDistrictList;

    public SListProvince() {
    }

    public SListProvince(String provinceid) {
        this.provinceid = provinceid;
    }

    public SListProvince(String provinceid, String provincedesc, boolean active) {
        this.provinceid = provinceid;
        this.provincedesc = provincedesc;
        this.active = active;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }

    public String getProvincedesc() {
        return provincedesc;
    }

    public void setProvincedesc(String provincedesc) {
        this.provincedesc = provincedesc;
    }

    public String getProvincecode() {
        return provincecode;
    }

    public void setProvincecode(String provincecode) {
        this.provincecode = provincecode;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getProvinceord() {
        return provinceord;
    }

    public void setProvinceord(Integer provinceord) {
        this.provinceord = provinceord;
    }

    @XmlTransient
    public List<SListDistrict> getSListDistrictList() {
        return sListDistrictList;
    }

    public void setSListDistrictList(List<SListDistrict> sListDistrictList) {
        this.sListDistrictList = sListDistrictList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (provinceid != null ? provinceid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SListProvince)) {
            return false;
        }
        SListProvince other = (SListProvince) object;
        if ((this.provinceid == null && other.provinceid != null) || (this.provinceid != null && !this.provinceid.equals(other.provinceid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.SListProvince[ provinceid=" + provinceid + " ]";
    }
    
}
