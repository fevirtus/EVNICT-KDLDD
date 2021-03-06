/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dulieuthuthap;

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
public class MHistorySanluongPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "MA_DIEMDO")
    private String maDiemdo;
    @Basic(optional = false)
    @Column(name = "THANG")
    private BigInteger thang;
    @Basic(optional = false)
    @Column(name = "NAM")
    private BigInteger nam;

    public MHistorySanluongPK() {
    }

    public MHistorySanluongPK(String maDiemdo, BigInteger thang, BigInteger nam) {
        this.maDiemdo = maDiemdo;
        this.thang = thang;
        this.nam = nam;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maDiemdo != null ? maDiemdo.hashCode() : 0);
        hash += (thang != null ? thang.hashCode() : 0);
        hash += (nam != null ? nam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MHistorySanluongPK)) {
            return false;
        }
        MHistorySanluongPK other = (MHistorySanluongPK) object;
        if ((this.maDiemdo == null && other.maDiemdo != null) || (this.maDiemdo != null && !this.maDiemdo.equals(other.maDiemdo))) {
            return false;
        }
        if ((this.thang == null && other.thang != null) || (this.thang != null && !this.thang.equals(other.thang))) {
            return false;
        }
        if ((this.nam == null && other.nam != null) || (this.nam != null && !this.nam.equals(other.nam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dulieuthuthap.MHistorySanluongPK[ maDiemdo=" + maDiemdo + ", thang=" + thang + ", nam=" + nam + " ]";
    }
    
}
