/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dulieuthuthap;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "M_HISTORY_CHISO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MHistoryChiso.findAll", query = "SELECT m FROM MHistoryChiso m"),
    @NamedQuery(name = "MHistoryChiso.findByMaDiemdo", query = "SELECT m FROM MHistoryChiso m WHERE m.mHistoryChisoPK.maDiemdo = :maDiemdo"),
    @NamedQuery(name = "MHistoryChiso.findByThang", query = "SELECT m FROM MHistoryChiso m WHERE m.mHistoryChisoPK.thang = :thang"),
    @NamedQuery(name = "MHistoryChiso.findByNam", query = "SELECT m FROM MHistoryChiso m WHERE m.mHistoryChisoPK.nam = :nam"),
    @NamedQuery(name = "MHistoryChiso.findByLoai", query = "SELECT m FROM MHistoryChiso m WHERE m.mHistoryChisoPK.loai = :loai"),
    @NamedQuery(name = "MHistoryChiso.findByBieu", query = "SELECT m FROM MHistoryChiso m WHERE m.mHistoryChisoPK.bieu = :bieu"),
    @NamedQuery(name = "MHistoryChiso.findByDateid", query = "SELECT m FROM MHistoryChiso m WHERE m.mHistoryChisoPK.dateid = :dateid"),
    @NamedQuery(name = "MHistoryChiso.findByCsoDk", query = "SELECT m FROM MHistoryChiso m WHERE m.csoDk = :csoDk"),
    @NamedQuery(name = "MHistoryChiso.findByCsoCk", query = "SELECT m FROM MHistoryChiso m WHERE m.csoCk = :csoCk"),
    @NamedQuery(name = "MHistoryChiso.findBySlKdd", query = "SELECT m FROM MHistoryChiso m WHERE m.slKdd = :slKdd"),
    @NamedQuery(name = "MHistoryChiso.findByHsn", query = "SELECT m FROM MHistoryChiso m WHERE m.hsn = :hsn"),
    @NamedQuery(name = "MHistoryChiso.findBySanLuong", query = "SELECT m FROM MHistoryChiso m WHERE m.sanLuong = :sanLuong"),
    @NamedQuery(name = "MHistoryChiso.findByUserid", query = "SELECT m FROM MHistoryChiso m WHERE m.userid = :userid"),
    @NamedQuery(name = "MHistoryChiso.findBySysDate", query = "SELECT m FROM MHistoryChiso m WHERE m.sysDate = :sysDate"),
    @NamedQuery(name = "MHistoryChiso.findByOrgidRef", query = "SELECT m FROM MHistoryChiso m WHERE m.orgidRef = :orgidRef"),
    @NamedQuery(name = "MHistoryChiso.findByMaDiemdoRef", query = "SELECT m FROM MHistoryChiso m WHERE m.maDiemdoRef = :maDiemdoRef"),
    @NamedQuery(name = "MHistoryChiso.findByStt", query = "SELECT m FROM MHistoryChiso m WHERE m.stt = :stt"),
    @NamedQuery(name = "MHistoryChiso.findByNgayDk", query = "SELECT m FROM MHistoryChiso m WHERE m.ngayDk = :ngayDk"),
    @NamedQuery(name = "MHistoryChiso.findByNgayCk", query = "SELECT m FROM MHistoryChiso m WHERE m.ngayCk = :ngayCk"),
    @NamedQuery(name = "MHistoryChiso.findByIsLock", query = "SELECT m FROM MHistoryChiso m WHERE m.isLock = :isLock")})
public class MHistoryChiso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MHistoryChisoPK mHistoryChisoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CSO_DK")
    private BigDecimal csoDk;
    @Column(name = "CSO_CK")
    private BigDecimal csoCk;
    @Column(name = "SL_KDD")
    private BigDecimal slKdd;
    @Column(name = "HSN")
    private BigDecimal hsn;
    @Column(name = "SAN_LUONG")
    private BigDecimal sanLuong;
    @Column(name = "USERID")
    private String userid;
    @Column(name = "SYS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sysDate;
    @Column(name = "ORGID_REF")
    private String orgidRef;
    @Column(name = "MA_DIEMDO_REF")
    private String maDiemdoRef;
    @Column(name = "STT")
    private BigInteger stt;
    @Column(name = "NGAY_DK")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayDk;
    @Column(name = "NGAY_CK")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayCk;
    @Column(name = "IS_LOCK")
    private BigInteger isLock;

    public MHistoryChiso() {
    }

    public MHistoryChiso(MHistoryChisoPK mHistoryChisoPK) {
        this.mHistoryChisoPK = mHistoryChisoPK;
    }

    public MHistoryChiso(String maDiemdo, BigInteger thang, BigInteger nam, String loai, String bieu, BigInteger dateid) {
        this.mHistoryChisoPK = new MHistoryChisoPK(maDiemdo, thang, nam, loai, bieu, dateid);
    }

    public MHistoryChisoPK getMHistoryChisoPK() {
        return mHistoryChisoPK;
    }

    public void setMHistoryChisoPK(MHistoryChisoPK mHistoryChisoPK) {
        this.mHistoryChisoPK = mHistoryChisoPK;
    }

    public BigDecimal getCsoDk() {
        return csoDk;
    }

    public void setCsoDk(BigDecimal csoDk) {
        this.csoDk = csoDk;
    }

    public BigDecimal getCsoCk() {
        return csoCk;
    }

    public void setCsoCk(BigDecimal csoCk) {
        this.csoCk = csoCk;
    }

    public BigDecimal getSlKdd() {
        return slKdd;
    }

    public void setSlKdd(BigDecimal slKdd) {
        this.slKdd = slKdd;
    }

    public BigDecimal getHsn() {
        return hsn;
    }

    public void setHsn(BigDecimal hsn) {
        this.hsn = hsn;
    }

    public BigDecimal getSanLuong() {
        return sanLuong;
    }

    public void setSanLuong(BigDecimal sanLuong) {
        this.sanLuong = sanLuong;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Date getSysDate() {
        return sysDate;
    }

    public void setSysDate(Date sysDate) {
        this.sysDate = sysDate;
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

    public BigInteger getStt() {
        return stt;
    }

    public void setStt(BigInteger stt) {
        this.stt = stt;
    }

    public Date getNgayDk() {
        return ngayDk;
    }

    public void setNgayDk(Date ngayDk) {
        this.ngayDk = ngayDk;
    }

    public Date getNgayCk() {
        return ngayCk;
    }

    public void setNgayCk(Date ngayCk) {
        this.ngayCk = ngayCk;
    }

    public BigInteger getIsLock() {
        return isLock;
    }

    public void setIsLock(BigInteger isLock) {
        this.isLock = isLock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mHistoryChisoPK != null ? mHistoryChisoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MHistoryChiso)) {
            return false;
        }
        MHistoryChiso other = (MHistoryChiso) object;
        if ((this.mHistoryChisoPK == null && other.mHistoryChisoPK != null) || (this.mHistoryChisoPK != null && !this.mHistoryChisoPK.equals(other.mHistoryChisoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dulieuthuthap.MHistoryChiso[ mHistoryChisoPK=" + mHistoryChisoPK + " ]";
    }
    
}
