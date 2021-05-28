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
public class MHistoryBdddPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "MA_DIEMDO")
    private String maDiemdo;
    @Basic(optional = false)
    @Column(name = "DATEID")
    private BigInteger dateid;
    @Basic(optional = false)
    @Column(name = "LOAI")
    private String loai;

    public MHistoryBdddPK() {
    }

    public MHistoryBdddPK(String maDiemdo, BigInteger dateid, String loai) {
        this.maDiemdo = maDiemdo;
        this.dateid = dateid;
        this.loai = loai;
    }

    public String getMaDiemdo() {
        return maDiemdo;
    }

    public void setMaDiemdo(String maDiemdo) {
        this.maDiemdo = maDiemdo;
    }

    public BigInteger getDateid() {
        return dateid;
    }

    public void setDateid(BigInteger dateid) {
        this.dateid = dateid;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maDiemdo != null ? maDiemdo.hashCode() : 0);
        hash += (dateid != null ? dateid.hashCode() : 0);
        hash += (loai != null ? loai.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MHistoryBdddPK)) {
            return false;
        }
        MHistoryBdddPK other = (MHistoryBdddPK) object;
        if ((this.maDiemdo == null && other.maDiemdo != null) || (this.maDiemdo != null && !this.maDiemdo.equals(other.maDiemdo))) {
            return false;
        }
        if ((this.dateid == null && other.dateid != null) || (this.dateid != null && !this.dateid.equals(other.dateid))) {
            return false;
        }
        if ((this.loai == null && other.loai != null) || (this.loai != null && !this.loai.equals(other.loai))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dulieuthuthap.MHistoryBdddPK[ maDiemdo=" + maDiemdo + ", dateid=" + dateid + ", loai=" + loai + " ]";
    }
    
}
