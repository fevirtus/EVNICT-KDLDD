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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "A_DIEMDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ADiemdo.findAll", query = "SELECT a FROM ADiemdo a"),
    @NamedQuery(name = "ADiemdo.findByMaDiemdo", query = "SELECT a FROM ADiemdo a WHERE a.maDiemdo = :maDiemdo"),
    @NamedQuery(name = "ADiemdo.findByTenDiemdo", query = "SELECT a FROM ADiemdo a WHERE a.tenDiemdo = :tenDiemdo"),
    @NamedQuery(name = "ADiemdo.findByMaLoaiDd", query = "SELECT a FROM ADiemdo a WHERE a.maLoaiDd = :maLoaiDd"),
    @NamedQuery(name = "ADiemdo.findByMaTcDd", query = "SELECT a FROM ADiemdo a WHERE a.maTcDd = :maTcDd"),
    @NamedQuery(name = "ADiemdo.findByMoTa", query = "SELECT a FROM ADiemdo a WHERE a.moTa = :moTa"),
    @NamedQuery(name = "ADiemdo.findByNgayVh", query = "SELECT a FROM ADiemdo a WHERE a.ngayVh = :ngayVh"),
    @NamedQuery(name = "ADiemdo.findByNgayKt", query = "SELECT a FROM ADiemdo a WHERE a.ngayKt = :ngayKt"),
    @NamedQuery(name = "ADiemdo.findByItVh", query = "SELECT a FROM ADiemdo a WHERE a.itVh = :itVh"),
    @NamedQuery(name = "ADiemdo.findByOrgidRef", query = "SELECT a FROM ADiemdo a WHERE a.orgidRef = :orgidRef"),
    @NamedQuery(name = "ADiemdo.findByMaDiemdoRef", query = "SELECT a FROM ADiemdo a WHERE a.maDiemdoRef = :maDiemdoRef"),
    @NamedQuery(name = "ADiemdo.findByUserCrId", query = "SELECT a FROM ADiemdo a WHERE a.userCrId = :userCrId"),
    @NamedQuery(name = "ADiemdo.findByUserCrDtime", query = "SELECT a FROM ADiemdo a WHERE a.userCrDtime = :userCrDtime"),
    @NamedQuery(name = "ADiemdo.findByUserMdfId", query = "SELECT a FROM ADiemdo a WHERE a.userMdfId = :userMdfId"),
    @NamedQuery(name = "ADiemdo.findByUserMdfDtime", query = "SELECT a FROM ADiemdo a WHERE a.userMdfDtime = :userMdfDtime"),
    @NamedQuery(name = "ADiemdo.findByOrgid", query = "SELECT a FROM ADiemdo a WHERE a.orgid = :orgid"),
    @NamedQuery(name = "ADiemdo.findByUlevelid", query = "SELECT a FROM ADiemdo a WHERE a.ulevelid = :ulevelid"),
    @NamedQuery(name = "ADiemdo.findByTrangThai", query = "SELECT a FROM ADiemdo a WHERE a.trangThai = :trangThai"),
    @NamedQuery(name = "ADiemdo.findByStt", query = "SELECT a FROM ADiemdo a WHERE a.stt = :stt")})
public class ADiemdo implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aDiemdo")
    private Collection<ADiemdoAx> aDiemdoAxCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MA_DIEMDO")
    private String maDiemdo;
    @Column(name = "TEN_DIEMDO")
    private String tenDiemdo;
    @Basic(optional = false)
    @Column(name = "MA_LOAI_DD")
    private String maLoaiDd;
    @Column(name = "MA_TC_DD")
    private String maTcDd;
    @Column(name = "MO_TA")
    private String moTa;
    @Basic(optional = false)
    @Column(name = "NGAY_VH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayVh;
    @Column(name = "NGAY_KT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayKt;
    @Column(name = "IT_VH")
    private BigInteger itVh;
    @Column(name = "ORGID_REF")
    private String orgidRef;
    @Column(name = "MA_DIEMDO_REF")
    private String maDiemdoRef;
    @Basic(optional = false)
    @Column(name = "USER_CR_ID")
    private String userCrId;
    @Basic(optional = false)
    @Column(name = "USER_CR_DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userCrDtime;
    @Column(name = "USER_MDF_ID")
    private String userMdfId;
    @Column(name = "USER_MDF_DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userMdfDtime;
    @Basic(optional = false)
    @Column(name = "ORGID")
    private String orgid;
    @Column(name = "ULEVELID")
    private String ulevelid;
    @Column(name = "TRANG_THAI")
    private BigInteger trangThai;
    @Column(name = "STT")
    private BigInteger stt;
    @JoinColumn(name = "MA_CTO", referencedColumnName = "MA_CTO")
    @ManyToOne
    private ACongto maCto;
    @JoinColumn(name = "MA_NHOM_DD", referencedColumnName = "MA_NHOM_DD")
    @ManyToOne(optional = false)
    private ADiemdoNhom maNhomDd;

    public ADiemdo() {
    }

    public ADiemdo(String maDiemdo) {
        this.maDiemdo = maDiemdo;
    }

    public ADiemdo(String maDiemdo, String maLoaiDd, Date ngayVh, String userCrId, Date userCrDtime, String orgid) {
        this.maDiemdo = maDiemdo;
        this.maLoaiDd = maLoaiDd;
        this.ngayVh = ngayVh;
        this.userCrId = userCrId;
        this.userCrDtime = userCrDtime;
        this.orgid = orgid;
    }

    public String getMaDiemdo() {
        return maDiemdo;
    }

    public void setMaDiemdo(String maDiemdo) {
        this.maDiemdo = maDiemdo;
    }

    public String getTenDiemdo() {
        return tenDiemdo;
    }

    public void setTenDiemdo(String tenDiemdo) {
        this.tenDiemdo = tenDiemdo;
    }

    public String getMaLoaiDd() {
        return maLoaiDd;
    }

    public void setMaLoaiDd(String maLoaiDd) {
        this.maLoaiDd = maLoaiDd;
    }

    public String getMaTcDd() {
        return maTcDd;
    }

    public void setMaTcDd(String maTcDd) {
        this.maTcDd = maTcDd;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getNgayVh() {
        return ngayVh;
    }

    public void setNgayVh(Date ngayVh) {
        this.ngayVh = ngayVh;
    }

    public Date getNgayKt() {
        return ngayKt;
    }

    public void setNgayKt(Date ngayKt) {
        this.ngayKt = ngayKt;
    }

    public BigInteger getItVh() {
        return itVh;
    }

    public void setItVh(BigInteger itVh) {
        this.itVh = itVh;
    }

    public String getOrgidRef() {
        return orgidRef;
    }

    public void setOrgidRef(String orgidRef) {
        this.orgidRef = orgidRef;
    }

    public String getMaDiemdoRef() {
        return maDiemdoRef;
    }

    public void setMaDiemdoRef(String maDiemdoRef) {
        this.maDiemdoRef = maDiemdoRef;
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

    public BigInteger getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(BigInteger trangThai) {
        this.trangThai = trangThai;
    }

    public BigInteger getStt() {
        return stt;
    }

    public void setStt(BigInteger stt) {
        this.stt = stt;
    }

    public ACongto getMaCto() {
        return maCto;
    }

    public void setMaCto(ACongto maCto) {
        this.maCto = maCto;
    }

    public ADiemdoNhom getMaNhomDd() {
        return maNhomDd;
    }

    public void setMaNhomDd(ADiemdoNhom maNhomDd) {
        this.maNhomDd = maNhomDd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maDiemdo != null ? maDiemdo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ADiemdo)) {
            return false;
        }
        ADiemdo other = (ADiemdo) object;
        if ((this.maDiemdo == null && other.maDiemdo != null) || (this.maDiemdo != null && !this.maDiemdo.equals(other.maDiemdo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diemdo.ADiemdo[ maDiemdo=" + maDiemdo + " ]";
    }

    @XmlTransient
    public Collection<ADiemdoAx> getADiemdoAxCollection() {
        return aDiemdoAxCollection;
    }

    public void setADiemdoAxCollection(Collection<ADiemdoAx> aDiemdoAxCollection) {
        this.aDiemdoAxCollection = aDiemdoAxCollection;
    }
    
}
