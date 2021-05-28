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
@Table(name = "M_HISTORY_BDDD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MHistoryBddd.findAll", query = "SELECT m FROM MHistoryBddd m"),
    @NamedQuery(name = "MHistoryBddd.findByMaDiemdo", query = "SELECT m FROM MHistoryBddd m WHERE m.mHistoryBdddPK.maDiemdo = :maDiemdo"),
    @NamedQuery(name = "MHistoryBddd.findByDateid", query = "SELECT m FROM MHistoryBddd m WHERE m.mHistoryBdddPK.dateid = :dateid"),
    @NamedQuery(name = "MHistoryBddd.findByNgay", query = "SELECT m FROM MHistoryBddd m WHERE m.ngay = :ngay"),
    @NamedQuery(name = "MHistoryBddd.findByThang", query = "SELECT m FROM MHistoryBddd m WHERE m.thang = :thang"),
    @NamedQuery(name = "MHistoryBddd.findByNam", query = "SELECT m FROM MHistoryBddd m WHERE m.nam = :nam"),
    @NamedQuery(name = "MHistoryBddd.findByLoai", query = "SELECT m FROM MHistoryBddd m WHERE m.mHistoryBdddPK.loai = :loai"),
    @NamedQuery(name = "MHistoryBddd.findByHsn", query = "SELECT m FROM MHistoryBddd m WHERE m.hsn = :hsn"),
    @NamedQuery(name = "MHistoryBddd.findBySgg", query = "SELECT m FROM MHistoryBddd m WHERE m.sgg = :sgg"),
    @NamedQuery(name = "MHistoryBddd.findByB1g", query = "SELECT m FROM MHistoryBddd m WHERE m.b1g = :b1g"),
    @NamedQuery(name = "MHistoryBddd.findByB2g", query = "SELECT m FROM MHistoryBddd m WHERE m.b2g = :b2g"),
    @NamedQuery(name = "MHistoryBddd.findByB3g", query = "SELECT m FROM MHistoryBddd m WHERE m.b3g = :b3g"),
    @NamedQuery(name = "MHistoryBddd.findByVcg", query = "SELECT m FROM MHistoryBddd m WHERE m.vcg = :vcg"),
    @NamedQuery(name = "MHistoryBddd.findBySgn", query = "SELECT m FROM MHistoryBddd m WHERE m.sgn = :sgn"),
    @NamedQuery(name = "MHistoryBddd.findByB1n", query = "SELECT m FROM MHistoryBddd m WHERE m.b1n = :b1n"),
    @NamedQuery(name = "MHistoryBddd.findByB2n", query = "SELECT m FROM MHistoryBddd m WHERE m.b2n = :b2n"),
    @NamedQuery(name = "MHistoryBddd.findByB3n", query = "SELECT m FROM MHistoryBddd m WHERE m.b3n = :b3n"),
    @NamedQuery(name = "MHistoryBddd.findByVcn", query = "SELECT m FROM MHistoryBddd m WHERE m.vcn = :vcn"),
    @NamedQuery(name = "MHistoryBddd.findBySlKddSgg", query = "SELECT m FROM MHistoryBddd m WHERE m.slKddSgg = :slKddSgg"),
    @NamedQuery(name = "MHistoryBddd.findBySlKddB1g", query = "SELECT m FROM MHistoryBddd m WHERE m.slKddB1g = :slKddB1g"),
    @NamedQuery(name = "MHistoryBddd.findBySlKddB2g", query = "SELECT m FROM MHistoryBddd m WHERE m.slKddB2g = :slKddB2g"),
    @NamedQuery(name = "MHistoryBddd.findBySlKddB3g", query = "SELECT m FROM MHistoryBddd m WHERE m.slKddB3g = :slKddB3g"),
    @NamedQuery(name = "MHistoryBddd.findBySlKddVcg", query = "SELECT m FROM MHistoryBddd m WHERE m.slKddVcg = :slKddVcg"),
    @NamedQuery(name = "MHistoryBddd.findBySlKddSgn", query = "SELECT m FROM MHistoryBddd m WHERE m.slKddSgn = :slKddSgn"),
    @NamedQuery(name = "MHistoryBddd.findBySlKddB1n", query = "SELECT m FROM MHistoryBddd m WHERE m.slKddB1n = :slKddB1n"),
    @NamedQuery(name = "MHistoryBddd.findBySlKddB2n", query = "SELECT m FROM MHistoryBddd m WHERE m.slKddB2n = :slKddB2n"),
    @NamedQuery(name = "MHistoryBddd.findBySlKddB3n", query = "SELECT m FROM MHistoryBddd m WHERE m.slKddB3n = :slKddB3n"),
    @NamedQuery(name = "MHistoryBddd.findBySlKddVcn", query = "SELECT m FROM MHistoryBddd m WHERE m.slKddVcn = :slKddVcn"),
    @NamedQuery(name = "MHistoryBddd.findByUserCrId", query = "SELECT m FROM MHistoryBddd m WHERE m.userCrId = :userCrId"),
    @NamedQuery(name = "MHistoryBddd.findByUserCrDtime", query = "SELECT m FROM MHistoryBddd m WHERE m.userCrDtime = :userCrDtime"),
    @NamedQuery(name = "MHistoryBddd.findByUserMdfId", query = "SELECT m FROM MHistoryBddd m WHERE m.userMdfId = :userMdfId"),
    @NamedQuery(name = "MHistoryBddd.findByUserMdfDtime", query = "SELECT m FROM MHistoryBddd m WHERE m.userMdfDtime = :userMdfDtime"),
    @NamedQuery(name = "MHistoryBddd.findByIsLock", query = "SELECT m FROM MHistoryBddd m WHERE m.isLock = :isLock")})
public class MHistoryBddd implements Serializable {
    @Column(name = "NOTE")
    private String note;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MHistoryBdddPK mHistoryBdddPK;
    @Column(name = "NGAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngay;
    @Column(name = "THANG")
    private BigInteger thang;
    @Column(name = "NAM")
    private BigInteger nam;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HSN")
    private BigDecimal hsn;
    @Column(name = "SGG")
    private BigDecimal sgg;
    @Column(name = "B1G")
    private BigDecimal b1g;
    @Column(name = "B2G")
    private BigDecimal b2g;
    @Column(name = "B3G")
    private BigDecimal b3g;
    @Column(name = "VCG")
    private BigDecimal vcg;
    @Column(name = "SGN")
    private BigDecimal sgn;
    @Column(name = "B1N")
    private BigDecimal b1n;
    @Column(name = "B2N")
    private BigDecimal b2n;
    @Column(name = "B3N")
    private BigDecimal b3n;
    @Column(name = "VCN")
    private BigDecimal vcn;
    @Column(name = "SL_KDD_SGG")
    private BigDecimal slKddSgg;
    @Column(name = "SL_KDD_B1G")
    private BigDecimal slKddB1g;
    @Column(name = "SL_KDD_B2G")
    private BigDecimal slKddB2g;
    @Column(name = "SL_KDD_B3G")
    private BigDecimal slKddB3g;
    @Column(name = "SL_KDD_VCG")
    private BigDecimal slKddVcg;
    @Column(name = "SL_KDD_SGN")
    private BigDecimal slKddSgn;
    @Column(name = "SL_KDD_B1N")
    private BigDecimal slKddB1n;
    @Column(name = "SL_KDD_B2N")
    private BigDecimal slKddB2n;
    @Column(name = "SL_KDD_B3N")
    private BigDecimal slKddB3n;
    @Column(name = "SL_KDD_VCN")
    private BigDecimal slKddVcn;
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
    @Column(name = "IS_LOCK")
    private BigInteger isLock;

    public MHistoryBddd() {
    }

    public MHistoryBddd(MHistoryBdddPK mHistoryBdddPK) {
        this.mHistoryBdddPK = mHistoryBdddPK;
    }

    public MHistoryBddd(String maDiemdo, BigInteger dateid, String loai) {
        this.mHistoryBdddPK = new MHistoryBdddPK(maDiemdo, dateid, loai);
    }

    public MHistoryBdddPK getMHistoryBdddPK() {
        return mHistoryBdddPK;
    }

    public void setMHistoryBdddPK(MHistoryBdddPK mHistoryBdddPK) {
        this.mHistoryBdddPK = mHistoryBdddPK;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public BigInteger getThang() {
        return thang;
    }

    public void setThang(BigInteger thang) {
        this.thang = thang;
    }

    public BigInteger getNam() {
        return nam;
    }

    public void setNam(BigInteger nam) {
        this.nam = nam;
    }

    public BigDecimal getHsn() {
        return hsn;
    }

    public void setHsn(BigDecimal hsn) {
        this.hsn = hsn;
    }

    public BigDecimal getSgg() {
        return sgg;
    }

    public void setSgg(BigDecimal sgg) {
        this.sgg = sgg;
    }

    public BigDecimal getB1g() {
        return b1g;
    }

    public void setB1g(BigDecimal b1g) {
        this.b1g = b1g;
    }

    public BigDecimal getB2g() {
        return b2g;
    }

    public void setB2g(BigDecimal b2g) {
        this.b2g = b2g;
    }

    public BigDecimal getB3g() {
        return b3g;
    }

    public void setB3g(BigDecimal b3g) {
        this.b3g = b3g;
    }

    public BigDecimal getVcg() {
        return vcg;
    }

    public void setVcg(BigDecimal vcg) {
        this.vcg = vcg;
    }

    public BigDecimal getSgn() {
        return sgn;
    }

    public void setSgn(BigDecimal sgn) {
        this.sgn = sgn;
    }

    public BigDecimal getB1n() {
        return b1n;
    }

    public void setB1n(BigDecimal b1n) {
        this.b1n = b1n;
    }

    public BigDecimal getB2n() {
        return b2n;
    }

    public void setB2n(BigDecimal b2n) {
        this.b2n = b2n;
    }

    public BigDecimal getB3n() {
        return b3n;
    }

    public void setB3n(BigDecimal b3n) {
        this.b3n = b3n;
    }

    public BigDecimal getVcn() {
        return vcn;
    }

    public void setVcn(BigDecimal vcn) {
        this.vcn = vcn;
    }

    public BigDecimal getSlKddSgg() {
        return slKddSgg;
    }

    public void setSlKddSgg(BigDecimal slKddSgg) {
        this.slKddSgg = slKddSgg;
    }

    public BigDecimal getSlKddB1g() {
        return slKddB1g;
    }

    public void setSlKddB1g(BigDecimal slKddB1g) {
        this.slKddB1g = slKddB1g;
    }

    public BigDecimal getSlKddB2g() {
        return slKddB2g;
    }

    public void setSlKddB2g(BigDecimal slKddB2g) {
        this.slKddB2g = slKddB2g;
    }

    public BigDecimal getSlKddB3g() {
        return slKddB3g;
    }

    public void setSlKddB3g(BigDecimal slKddB3g) {
        this.slKddB3g = slKddB3g;
    }

    public BigDecimal getSlKddVcg() {
        return slKddVcg;
    }

    public void setSlKddVcg(BigDecimal slKddVcg) {
        this.slKddVcg = slKddVcg;
    }

    public BigDecimal getSlKddSgn() {
        return slKddSgn;
    }

    public void setSlKddSgn(BigDecimal slKddSgn) {
        this.slKddSgn = slKddSgn;
    }

    public BigDecimal getSlKddB1n() {
        return slKddB1n;
    }

    public void setSlKddB1n(BigDecimal slKddB1n) {
        this.slKddB1n = slKddB1n;
    }

    public BigDecimal getSlKddB2n() {
        return slKddB2n;
    }

    public void setSlKddB2n(BigDecimal slKddB2n) {
        this.slKddB2n = slKddB2n;
    }

    public BigDecimal getSlKddB3n() {
        return slKddB3n;
    }

    public void setSlKddB3n(BigDecimal slKddB3n) {
        this.slKddB3n = slKddB3n;
    }

    public BigDecimal getSlKddVcn() {
        return slKddVcn;
    }

    public void setSlKddVcn(BigDecimal slKddVcn) {
        this.slKddVcn = slKddVcn;
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

    public BigInteger getIsLock() {
        return isLock;
    }

    public void setIsLock(BigInteger isLock) {
        this.isLock = isLock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mHistoryBdddPK != null ? mHistoryBdddPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MHistoryBddd)) {
            return false;
        }
        MHistoryBddd other = (MHistoryBddd) object;
        if ((this.mHistoryBdddPK == null && other.mHistoryBdddPK != null) || (this.mHistoryBdddPK != null && !this.mHistoryBdddPK.equals(other.mHistoryBdddPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dulieuthuthap.MHistoryBddd[ mHistoryBdddPK=" + mHistoryBdddPK + " ]";
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
}
