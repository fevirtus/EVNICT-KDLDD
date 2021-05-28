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
public class MHistoryChisoPK implements Serializable {
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
    @Column(name = "LOAI")
    private String loai;
    @Basic(optional = false)
    @Column(name = "BIEU")
    private String bieu;
    @Basic(optional = false)
    @Column(name = "DATEID")
    private BigInteger dateid;

    public MHistoryChisoPK() {
    }

    public MHistoryChisoPK(String maDiemdo, BigInteger thang, BigInteger nam, String loai, String bieu, BigInteger dateid) {
        this.maDiemdo = maDiemdo;
        this.thang = thang;
        this.nam = nam;
        this.loai = loai;
        this.bieu = bieu;
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

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getBieu() {
        return bieu;
    }

    public void setBieu(String bieu) {
        this.bieu = bieu;
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
        hash += (loai != null ? loai.hashCode() : 0);
        hash += (bieu != null ? bieu.hashCode() : 0);
        hash += (dateid != null ? dateid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MHistoryChisoPK)) {
            return false;
        }
        MHistoryChisoPK other = (MHistoryChisoPK) object;
        if ((this.maDiemdo == null && other.maDiemdo != null) || (this.maDiemdo != null && !this.maDiemdo.equals(other.maDiemdo))) {
            return false;
        }
        if ((this.thang == null && other.thang != null) || (this.thang != null && !this.thang.equals(other.thang))) {
            return false;
        }
        if ((this.nam == null && other.nam != null) || (this.nam != null && !this.nam.equals(other.nam))) {
            return false;
        }
        if ((this.loai == null && other.loai != null) || (this.loai != null && !this.loai.equals(other.loai))) {
            return false;
        }
        if ((this.bieu == null && other.bieu != null) || (this.bieu != null && !this.bieu.equals(other.bieu))) {
            return false;
        }
        if ((this.dateid == null && other.dateid != null) || (this.dateid != null && !this.dateid.equals(other.dateid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dulieuthuthap.MHistoryChisoPK[ maDiemdo=" + maDiemdo + ", thang=" + thang + ", nam=" + nam + ", loai=" + loai + ", bieu=" + bieu + ", dateid=" + dateid + " ]";
    }
    
}
