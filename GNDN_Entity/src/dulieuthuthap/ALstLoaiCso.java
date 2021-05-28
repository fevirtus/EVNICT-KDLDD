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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "A_LST_LOAI_CSO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ALstLoaiCso.findAll", query = "SELECT a FROM ALstLoaiCso a"),
    @NamedQuery(name = "ALstLoaiCso.findByMaLoaiCso", query = "SELECT a FROM ALstLoaiCso a WHERE a.maLoaiCso = :maLoaiCso"),
    @NamedQuery(name = "ALstLoaiCso.findByTenLoaiCso", query = "SELECT a FROM ALstLoaiCso a WHERE a.tenLoaiCso = :tenLoaiCso"),
    @NamedQuery(name = "ALstLoaiCso.findByStt", query = "SELECT a FROM ALstLoaiCso a WHERE a.stt = :stt")})
public class ALstLoaiCso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MA_LOAI_CSO")
    private String maLoaiCso;
    @Column(name = "TEN_LOAI_CSO")
    private String tenLoaiCso;
    @Column(name = "STT")
    private BigInteger stt;

    public ALstLoaiCso() {
    }

    public ALstLoaiCso(String maLoaiCso) {
        this.maLoaiCso = maLoaiCso;
    }

    public String getMaLoaiCso() {
        return maLoaiCso;
    }

    public void setMaLoaiCso(String maLoaiCso) {
        this.maLoaiCso = maLoaiCso;
    }

    public String getTenLoaiCso() {
        return tenLoaiCso;
    }

    public void setTenLoaiCso(String tenLoaiCso) {
        this.tenLoaiCso = tenLoaiCso;
    }

    public BigInteger getStt() {
        return stt;
    }

    public void setStt(BigInteger stt) {
        this.stt = stt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maLoaiCso != null ? maLoaiCso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ALstLoaiCso)) {
            return false;
        }
        ALstLoaiCso other = (ALstLoaiCso) object;
        if ((this.maLoaiCso == null && other.maLoaiCso != null) || (this.maLoaiCso != null && !this.maLoaiCso.equals(other.maLoaiCso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dulieuthuthap.ALstLoaiCso[ maLoaiCso=" + maLoaiCso + " ]";
    }
    
}
