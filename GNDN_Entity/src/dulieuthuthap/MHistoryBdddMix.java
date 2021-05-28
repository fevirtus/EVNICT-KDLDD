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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Admin
 */
@Entity
public class MHistoryBdddMix implements Serializable {

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
    @Column(name = "TEN_LOAI_CSO")
    private String tenLoaiCso;
    @Column(name = "NOTE")
    private String note;

    public MHistoryBdddMix() {
    }

    public MHistoryBdddMix(MHistoryBdddPK mHistoryBdddPK) {
        this.mHistoryBdddPK = mHistoryBdddPK;
    }

    public MHistoryBdddMix(String maDiemdo, BigInteger dateid, String loai) {
        this.mHistoryBdddPK = new MHistoryBdddPK(maDiemdo, dateid, loai);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTenLoaiCso() {
        return tenLoaiCso;
    }

    public void setTenLoaiCso(String tenLoaiCso) {
        this.tenLoaiCso = tenLoaiCso;
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

}
