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
@Table(name = "A_LST_TC_DD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ALstTcDd.findAll", query = "SELECT a FROM ALstTcDd a"),
    @NamedQuery(name = "ALstTcDd.findByMaTcDd", query = "SELECT a FROM ALstTcDd a WHERE a.maTcDd = :maTcDd"),
    @NamedQuery(name = "ALstTcDd.findByTenTcDd", query = "SELECT a FROM ALstTcDd a WHERE a.tenTcDd = :tenTcDd"),
    @NamedQuery(name = "ALstTcDd.findByStt", query = "SELECT a FROM ALstTcDd a WHERE a.stt = :stt")})
public class ALstTcDd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MA_TC_DD")
    private String maTcDd;
    @Column(name = "TEN_TC_DD")
    private String tenTcDd;
    @Column(name = "STT")
    private BigInteger stt;

    public ALstTcDd() {
    }

    public ALstTcDd(String maTcDd) {
        this.maTcDd = maTcDd;
    }

    public String getMaTcDd() {
        return maTcDd;
    }

    public void setMaTcDd(String maTcDd) {
        this.maTcDd = maTcDd;
    }

    public String getTenTcDd() {
        return tenTcDd;
    }

    public void setTenTcDd(String tenTcDd) {
        this.tenTcDd = tenTcDd;
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
        hash += (maTcDd != null ? maTcDd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ALstTcDd)) {
            return false;
        }
        ALstTcDd other = (ALstTcDd) object;
        if ((this.maTcDd == null && other.maTcDd != null) || (this.maTcDd != null && !this.maTcDd.equals(other.maTcDd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diemdo.ALstTcDd[ maTcDd=" + maTcDd + " ]";
    }
    
}
