/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dulieuthuthap;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author Admin
 */
@Entity
public class MHistorySanluongMix implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MHistorySanluongPK mHistorySanluongPK;
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
    @Column(name = "SL_KDD_G")
    private BigDecimal slKddG;
    @Column(name = "SL_KDD_N")
    private BigDecimal slKddN;
    @Column(name = "TEN_DIEMDO")
    private String ten_diemdo;
    @Column(name = "MA_TC_DD")
    private String ma_tc_diemdo;
    @Column(name = "ORGID")
    private String orgid;

    public MHistorySanluongMix() {
    }
    
    public MHistorySanluongMix(MHistorySanluongPK mHistorySanluongPK) {
        this.mHistorySanluongPK = mHistorySanluongPK;
    }
    
    public MHistorySanluongMix(String maDiemdo, BigInteger thang, BigInteger nam) {
        this.mHistorySanluongPK = new MHistorySanluongPK(maDiemdo, thang, nam);
    }

    public MHistorySanluongPK getmHistorySanluongPK() {
        return mHistorySanluongPK;
    }

    public void setmHistorySanluongPK(MHistorySanluongPK mHistorySanluongPK) {
        this.mHistorySanluongPK = mHistorySanluongPK;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
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

    public BigDecimal getSlKddG() {
        return slKddG;
    }

    public void setSlKddG(BigDecimal slKddG) {
        this.slKddG = slKddG;
    }

    public BigDecimal getSlKddN() {
        return slKddN;
    }

    public void setSlKddN(BigDecimal slKddN) {
        this.slKddN = slKddN;
    }

    public String getTen_diemdo() {
        return ten_diemdo;
    }

    public void setTen_diemdo(String ten_diemdo) {
        this.ten_diemdo = ten_diemdo;
    }

    public String getMa_tc_diemdo() {
        return ma_tc_diemdo;
    }

    public void setMa_tc_diemdo(String ma_tc_diemdo) {
        this.ma_tc_diemdo = ma_tc_diemdo;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MHistorySanluongMix)) {
            return false;
        }
        MHistorySanluongMix other = (MHistorySanluongMix) object;
        if ((this.mHistorySanluongPK == null && other.mHistorySanluongPK != null) || (this.mHistorySanluongPK != null && !this.mHistorySanluongPK.equals(other.mHistorySanluongPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dulieuthuthap.MHistorySanluongMix[ mHistorySanluongPK=" + mHistorySanluongPK + " ]";
    }
}
