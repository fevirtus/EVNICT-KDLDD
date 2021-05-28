/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author SYPV
 */
@Entity
@Table(name = "THAMSO_CHUNG")
public class Thamso_Chung implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MA_THAMSO")
    private String maThamso;
    @Basic(optional = false)
    @Column(name = "GIA_TRI")
    private String giaTri;
    @Column(name = "HIEN_THI")
    private String hienThi;
    @Column(name = "TEN_BANG")
    private String tenBang;
    @Column(name = "TEN_TRUONG")
    private String tenTruong;
    @Column(name = "KH_THAMSO")
    private String kyHieuThamSo;
  
    public String getKyHieuThamSo() {
        return kyHieuThamSo;
    }

    public void setKyHieuThamSo(String kyHieuThamSo) {
        this.kyHieuThamSo = kyHieuThamSo;
    }

    
    public Thamso_Chung() {
    }

    public Thamso_Chung(String maThamso) {
        this.maThamso = maThamso;
    }

    public Thamso_Chung(String maThamso, String giaTri) {
        this.maThamso = maThamso;
        this.giaTri = giaTri;
    }

    public String getMaThamso() {
        return maThamso;
    }

    public void setMaThamso(String maThamso) {
        this.maThamso = maThamso;
    }

    public String getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(String giaTri) {
        this.giaTri = giaTri;
    }

    public String getHienThi() {
        return hienThi;
    }

    public void setHienThi(String hienThi) {
        this.hienThi = hienThi;
    }

    public String getTenBang() {
        return tenBang;
    }

    public void setTenBang(String tenBang) {
        this.tenBang = tenBang;
    }

    public String getTenTruong() {
        return tenTruong;
    }

    public void setTenTruong(String tenTruong) {
        this.tenTruong = tenTruong;
    }
  
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maThamso != null ? maThamso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Thamso_Chung)) {
            return false;
        }
        Thamso_Chung other = (Thamso_Chung) object;
        if ((this.maThamso == null && other.maThamso != null) || (this.maThamso != null && !this.maThamso.equals(other.maThamso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.Thamso_Chung[maThamso=" + maThamso + "]";
    }

}
