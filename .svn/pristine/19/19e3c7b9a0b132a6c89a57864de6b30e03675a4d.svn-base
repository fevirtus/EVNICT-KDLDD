/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diemdo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TUYEN EVN
 */
@Entity
@Table(name = "A_DIEMDO_NHOM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ADiemdoNhom.findAll", query = "SELECT a FROM ADiemdoNhom a"),
    @NamedQuery(name = "ADiemdoNhom.findByMaNhomDd", query = "SELECT a FROM ADiemdoNhom a WHERE a.maNhomDd = :maNhomDd"),
    @NamedQuery(name = "ADiemdoNhom.findByTenNhomDd", query = "SELECT a FROM ADiemdoNhom a WHERE a.tenNhomDd = :tenNhomDd"),
    @NamedQuery(name = "ADiemdoNhom.findByStt", query = "SELECT a FROM ADiemdoNhom a WHERE a.stt = :stt"),
    @NamedQuery(name = "ADiemdoNhom.findByCategoryid", query = "SELECT a FROM ADiemdoNhom a WHERE a.categoryid = :categoryid"),
    @NamedQuery(name = "ADiemdoNhom.findByTypeid", query = "SELECT a FROM ADiemdoNhom a WHERE a.typeid = :typeid"),
    @NamedQuery(name = "ADiemdoNhom.findByUserCrId", query = "SELECT a FROM ADiemdoNhom a WHERE a.userCrId = :userCrId"),
    @NamedQuery(name = "ADiemdoNhom.findByUserCrDtime", query = "SELECT a FROM ADiemdoNhom a WHERE a.userCrDtime = :userCrDtime"),
    @NamedQuery(name = "ADiemdoNhom.findByUserMdfId", query = "SELECT a FROM ADiemdoNhom a WHERE a.userMdfId = :userMdfId"),
    @NamedQuery(name = "ADiemdoNhom.findByUserMdfDtime", query = "SELECT a FROM ADiemdoNhom a WHERE a.userMdfDtime = :userMdfDtime"),
    @NamedQuery(name = "ADiemdoNhom.findByOrgid", query = "SELECT a FROM ADiemdoNhom a WHERE a.orgid = :orgid"),
    @NamedQuery(name = "ADiemdoNhom.findByUlevelid", query = "SELECT a FROM ADiemdoNhom a WHERE a.ulevelid = :ulevelid"),
    @NamedQuery(name = "ADiemdoNhom.findByMoTa", query = "SELECT a FROM ADiemdoNhom a WHERE a.moTa = :moTa"),
    @NamedQuery(name = "ADiemdoNhom.findByTrangThai", query = "SELECT a FROM ADiemdoNhom a WHERE a.trangThai = :trangThai")})
public class ADiemdoNhom implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MA_NHOM_DD")
    private String maNhomDd;
    @Basic(optional = false)
    @Column(name = "TEN_NHOM_DD")
    private String tenNhomDd;
    @Column(name = "STT")
    private BigInteger stt;
    @Column(name = "CATEGORYID")
    private String categoryid;
    @Column(name = "TYPEID")
    private String typeid;
    @Column(name = "USER_CR_ID")
    private String userCrId;
    @Column(name = "USER_CR_DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userCrDtime;
    @Column(name = "USER_MDF_ID")
    private String userMdfId;
    @Column(name = "USER_MDF_DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userMdfDtime;
    @Column(name = "ORGID")
    private String orgid;
    @Column(name = "ULEVELID")
    private String ulevelid;
    @Column(name = "MO_TA")
    private String moTa;
    @Column(name = "TRANG_THAI")
    private BigInteger trangThai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maNhomDd")
    private Collection<ADiemdo> aDiemdoCollection;

    public ADiemdoNhom() {
    }

    public ADiemdoNhom(String maNhomDd) {
        this.maNhomDd = maNhomDd;
    }

    public ADiemdoNhom(String maNhomDd, String tenNhomDd) {
        this.maNhomDd = maNhomDd;
        this.tenNhomDd = tenNhomDd;
    }

    public String getMaNhomDd() {
        return maNhomDd;
    }

    public void setMaNhomDd(String maNhomDd) {
        this.maNhomDd = maNhomDd;
    }

    public String getTenNhomDd() {
        return tenNhomDd;
    }

    public void setTenNhomDd(String tenNhomDd) {
        this.tenNhomDd = tenNhomDd;
    }

    public BigInteger getStt() {
        return stt;
    }

    public void setStt(BigInteger stt) {
        this.stt = stt;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getUserCrId() {
        return userCrId;
    }

    public void setUserCrId(String userCrId) {
        this.userCrId = userCrId;
    }

    public Date getUserCrDtime() {
        return userCrDtime;
    }

    public void setUserCrDtime(Date userCrDtime) {
        this.userCrDtime = userCrDtime;
    }

    public String getUserMdfId() {
        return userMdfId;
    }

    public void setUserMdfId(String userMdfId) {
        this.userMdfId = userMdfId;
    }

    public Date getUserMdfDtime() {
        return userMdfDtime;
    }

    public void setUserMdfDtime(Date userMdfDtime) {
        this.userMdfDtime = userMdfDtime;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getUlevelid() {
        return ulevelid;
    }

    public void setUlevelid(String ulevelid) {
        this.ulevelid = ulevelid;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public BigInteger getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(BigInteger trangThai) {
        this.trangThai = trangThai;
    }

    @XmlTransient
    public Collection<ADiemdo> getADiemdoCollection() {
        return aDiemdoCollection;
    }

    public void setADiemdoCollection(Collection<ADiemdo> aDiemdoCollection) {
        this.aDiemdoCollection = aDiemdoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maNhomDd != null ? maNhomDd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ADiemdoNhom)) {
            return false;
        }
        ADiemdoNhom other = (ADiemdoNhom) object;
        if ((this.maNhomDd == null && other.maNhomDd != null) || (this.maNhomDd != null && !this.maNhomDd.equals(other.maNhomDd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diemdo.ADiemdoNhom[ maNhomDd=" + maNhomDd + " ]";
    }
    
}
