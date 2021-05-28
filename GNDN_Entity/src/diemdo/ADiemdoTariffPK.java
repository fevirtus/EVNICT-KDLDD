/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diemdo;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Admin
 */
@Embeddable
public class ADiemdoTariffPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "MA_DIEMDO")
    private String maDiemdo;
    @Basic(optional = false)
    @Column(name = "THANG")
    private BigInteger thang;
    @Basic(optional = false)
    @Column(name = "NAM")
    private BigInteger nam;
    @Basic(optional = false)
    @Column(name = "DATEID")
    private BigInteger dateid;

    public ADiemdoTariffPK() {
    }

    public ADiemdoTariffPK(String maDiemdo, BigInteger thang, BigInteger nam, BigInteger dateid) {
        this.maDiemdo = maDiemdo;
        this.thang = thang;
        this.nam = nam;
        this.dateid = dateid;
    }

    public String getMaDiemdo() {
        return maDiemdo;
    }

    public void setMaDiemdo(String maDiemdo) {
        this.maDiemdo = maDiemdo;
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

    public BigInteger getDateid() {
        return dateid;
    }

    public void setDateid(BigInteger dateid) {
        this.dateid = dateid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maDiemdo != null ? maDiemdo.hashCode() : 0);
        hash += (thang != null ? thang.hashCode() : 0);
        hash += (nam != null ? nam.hashCode() : 0);
        hash += (dateid != null ? dateid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ADiemdoTariffPK)) {
            return false;
        }
        ADiemdoTariffPK other = (ADiemdoTariffPK) object;
        if ((this.maDiemdo == null && other.maDiemdo != null) || (this.maDiemdo != null && !this.maDiemdo.equals(other.maDiemdo))) {
            return false;
        }
        if ((this.thang == null && other.thang != null) || (this.thang != null && !this.thang.equals(other.thang))) {
            return false;
        }
        if ((this.nam == null && other.nam != null) || (this.nam != null && !this.nam.equals(other.nam))) {
            return false;
        }
        if ((this.dateid == null && other.dateid != null) || (this.dateid != null && !this.dateid.equals(other.dateid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diemdo.ADiemdoTariffPK[ maDiemdo=" + maDiemdo + ", thang=" + thang + ", nam=" + nam + ", dateid=" + dateid + " ]";
    }
    
}
