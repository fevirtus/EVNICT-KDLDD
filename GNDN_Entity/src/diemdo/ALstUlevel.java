/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diemdo;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "A_LST_ULEVEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ALstUlevel.findAll", query = "SELECT a FROM ALstUlevel a"),
    @NamedQuery(name = "ALstUlevel.findByUlevelid", query = "SELECT a FROM ALstUlevel a WHERE a.ulevelid = :ulevelid"),
    @NamedQuery(name = "ALstUlevel.findByUleveldesc", query = "SELECT a FROM ALstUlevel a WHERE a.uleveldesc = :uleveldesc"),
    @NamedQuery(name = "ALstUlevel.findByUlevelord", query = "SELECT a FROM ALstUlevel a WHERE a.ulevelord = :ulevelord"),
    @NamedQuery(name = "ALstUlevel.findByUlevelCode", query = "SELECT a FROM ALstUlevel a WHERE a.ulevelCode = :ulevelCode"),
    @NamedQuery(name = "ALstUlevel.findByUlevelNum", query = "SELECT a FROM ALstUlevel a WHERE a.ulevelNum = :ulevelNum"),
    @NamedQuery(name = "ALstUlevel.findByActive", query = "SELECT a FROM ALstUlevel a WHERE a.active = :active"),
    @NamedQuery(name = "ALstUlevel.findByUvalues", query = "SELECT a FROM ALstUlevel a WHERE a.uvalues = :uvalues")})
public class ALstUlevel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ULEVELID")
    private String ulevelid;
    @Column(name = "ULEVELDESC")
    private String uleveldesc;
    @Column(name = "ULEVELORD")
    private BigInteger ulevelord;
    @Column(name = "ULEVEL_CODE")
    private String ulevelCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ULEVEL_NUM")
    private BigDecimal ulevelNum;
    @Column(name = "ACTIVE")
    private Short active;
    @Column(name = "UVALUES")
    private BigInteger uvalues;

    public ALstUlevel() {
    }

    public ALstUlevel(String ulevelid) {
        this.ulevelid = ulevelid;
    }

    public String getUlevelid() {
        return ulevelid;
    }

    public void setUlevelid(String ulevelid) {
        this.ulevelid = ulevelid;
    }

    public String getUleveldesc() {
        return uleveldesc;
    }

    public void setUleveldesc(String uleveldesc) {
        this.uleveldesc = uleveldesc;
    }

    public BigInteger getUlevelord() {
        return ulevelord;
    }

    public void setUlevelord(BigInteger ulevelord) {
        this.ulevelord = ulevelord;
    }

    public String getUlevelCode() {
        return ulevelCode;
    }

    public void setUlevelCode(String ulevelCode) {
        this.ulevelCode = ulevelCode;
    }

    public BigDecimal getUlevelNum() {
        return ulevelNum;
    }

    public void setUlevelNum(BigDecimal ulevelNum) {
        this.ulevelNum = ulevelNum;
    }

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }

    public BigInteger getUvalues() {
        return uvalues;
    }

    public void setUvalues(BigInteger uvalues) {
        this.uvalues = uvalues;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ulevelid != null ? ulevelid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ALstUlevel)) {
            return false;
        }
        ALstUlevel other = (ALstUlevel) object;
        if ((this.ulevelid == null && other.ulevelid != null) || (this.ulevelid != null && !this.ulevelid.equals(other.ulevelid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diemdo.ALstUlevel[ ulevelid=" + ulevelid + " ]";
    }
    
}
