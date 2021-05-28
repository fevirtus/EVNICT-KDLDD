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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TUYEN EVN
 */
@Entity
@Table(name = "A_LST_LOAI_DD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ALstLoaiDd.findAll", query = "SELECT a FROM ALstLoaiDd a"),
    @NamedQuery(name = "ALstLoaiDd.findByMaLoaiDd", query = "SELECT a FROM ALstLoaiDd a WHERE a.maLoaiDd = :maLoaiDd"),
    @NamedQuery(name = "ALstLoaiDd.findByTenLoaiDd", query = "SELECT a FROM ALstLoaiDd a WHERE a.tenLoaiDd = :tenLoaiDd"),
    @NamedQuery(name = "ALstLoaiDd.findByStt", query = "SELECT a FROM ALstLoaiDd a WHERE a.stt = :stt")})
public class ALstLoaiDd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MA_LOAI_DD")
    private String maLoaiDd;
    @Column(name = "TEN_LOAI_DD")
    private String tenLoaiDd;
    @Column(name = "STT")
    private BigInteger stt;

    public ALstLoaiDd() {
    }

    public ALstLoaiDd(String maLoaiDd) {
        this.maLoaiDd = maLoaiDd;
    }

    public ALstLoaiDd(String maLoaiDd, String tenLoaiDd) {
        this.maLoaiDd = maLoaiDd;
        this.tenLoaiDd = tenLoaiDd;
    }
    
    public String getMaLoaiDd() {
        return maLoaiDd;
    }

    public void setMaLoaiDd(String maLoaiDd) {
        this.maLoaiDd = maLoaiDd;
    }

    public String getTenLoaiDd() {
        return tenLoaiDd;
    }

    public void setTenLoaiDd(String tenLoaiDd) {
        this.tenLoaiDd = tenLoaiDd;
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
        hash += (maLoaiDd != null ? maLoaiDd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ALstLoaiDd)) {
            return false;
        }
        ALstLoaiDd other = (ALstLoaiDd) object;
        if ((this.maLoaiDd == null && other.maLoaiDd != null) || (this.maLoaiDd != null && !this.maLoaiDd.equals(other.maLoaiDd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diemdo.ALstLoaiDd[ maLoaiDd=" + maLoaiDd + " ]";
    }
    
}
