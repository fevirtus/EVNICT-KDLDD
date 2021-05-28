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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "M_HISTORY_SANLUONG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MHistorySanluong.findAll", query = "SELECT m FROM MHistorySanluong m"),
    @NamedQuery(name = "MHistorySanluong.findByMaDiemdo", query = "SELECT m FROM MHistorySanluong m WHERE m.mHistorySanluongPK.maDiemdo = :maDiemdo"),
    @NamedQuery(name = "MHistorySanluong.findByThang", query = "SELECT m FROM MHistorySanluong m WHERE m.mHistorySanluongPK.thang = :thang"),
    @NamedQuery(name = "MHistorySanluong.findByNam", query = "SELECT m FROM MHistorySanluong m WHERE m.mHistorySanluongPK.nam = :nam"),
    @NamedQuery(name = "MHistorySanluong.findBySgg", query = "SELECT m FROM MHistorySanluong m WHERE m.sgg = :sgg"),
    @NamedQuery(name = "MHistorySanluong.findByB1g", query = "SELECT m FROM MHistorySanluong m WHERE m.b1g = :b1g"),
    @NamedQuery(name = "MHistorySanluong.findByB2g", query = "SELECT m FROM MHistorySanluong m WHERE m.b2g = :b2g"),
    @NamedQuery(name = "MHistorySanluong.findByB3g", query = "SELECT m FROM MHistorySanluong m WHERE m.b3g = :b3g"),
    @NamedQuery(name = "MHistorySanluong.findByVcg", query = "SELECT m FROM MHistorySanluong m WHERE m.vcg = :vcg"),
    @NamedQuery(name = "MHistorySanluong.findBySgn", query = "SELECT m FROM MHistorySanluong m WHERE m.sgn = :sgn"),
    @NamedQuery(name = "MHistorySanluong.findByB1n", query = "SELECT m FROM MHistorySanluong m WHERE m.b1n = :b1n"),
    @NamedQuery(name = "MHistorySanluong.findByB2n", query = "SELECT m FROM MHistorySanluong m WHERE m.b2n = :b2n"),
    @NamedQuery(name = "MHistorySanluong.findByB3n", query = "SELECT m FROM MHistorySanluong m WHERE m.b3n = :b3n"),
    @NamedQuery(name = "MHistorySanluong.findByVcn", query = "SELECT m FROM MHistorySanluong m WHERE m.vcn = :vcn"),
    @NamedQuery(name = "MHistorySanluong.findBySlKddG", query = "SELECT m FROM MHistorySanluong m WHERE m.slKddG = :slKddG"),
    @NamedQuery(name = "MHistorySanluong.findBySlKddN", query = "SELECT m FROM MHistorySanluong m WHERE m.slKddN = :slKddN")})
public class MHistorySanluong implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MHistorySanluongPK mHistorySanluongPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
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

    public MHistorySanluong() {
    }

    public MHistorySanluong(MHistorySanluongPK mHistorySanluongPK) {
        this.mHistorySanluongPK = mHistorySanluongPK;
    }

    public MHistorySanluong(String maDiemdo, BigInteger thang, BigInteger nam) {
        this.mHistorySanluongPK = new MHistorySanluongPK(maDiemdo, thang, nam);
    }

    public MHistorySanluongPK getMHistorySanluongPK() {
        return mHistorySanluongPK;
    }

    public void setMHistorySanluongPK(MHistorySanluongPK mHistorySanluongPK) {
        this.mHistorySanluongPK = mHistorySanluongPK;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mHistorySanluongPK != null ? mHistorySanluongPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MHistorySanluong)) {
            return false;
        }
        MHistorySanluong other = (MHistorySanluong) object;
        if ((this.mHistorySanluongPK == null && other.mHistorySanluongPK != null) || (this.mHistorySanluongPK != null && !this.mHistorySanluongPK.equals(other.mHistorySanluongPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dulieuthuthap.MHistorySanluong[ mHistorySanluongPK=" + mHistorySanluongPK + " ]";
    }
    
}
