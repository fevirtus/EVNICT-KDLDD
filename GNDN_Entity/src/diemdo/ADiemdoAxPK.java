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
 * @author TUYEN EVN
 */
@Embeddable
public class ADiemdoAxPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "MA_DIEMDO")
    private String maDiemdo;
    @Basic(optional = false)
    @Column(name = "MA_DIEMDO_REF")
    private String maDiemdoRef;
    @Basic(optional = false)
    @Column(name = "ORGID_REF")
    private String orgidRef;
    @Basic(optional = false)
    @Column(name = "DATEID")
    private BigInteger dateid;

    public ADiemdoAxPK() {
    }

    public ADiemdoAxPK(String maDiemdo, String maDiemdoRef, String orgidRef, BigInteger dateid) {
        this.maDiemdo = maDiemdo;
        this.maDiemdoRef = maDiemdoRef;
        this.orgidRef = orgidRef;
        this.dateid = dateid;
    }

    public String getMaDiemdo() {
        return maDiemdo;
    }

    public void setMaDiemdo(String maDiemdo) {
        this.maDiemdo = maDiemdo;
    }

    public String getMaDiemdoRef() {
        return maDiemdoRef;
    }

    public void setMaDiemdoRef(String maDiemdoRef) {
        this.maDiemdoRef = maDiemdoRef;
    }

    public String getOrgidRef() {
        return orgidRef;
    }

    public void setOrgidRef(String orgidRef) {
        this.orgidRef = orgidRef;
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
        hash += (maDiemdoRef != null ? maDiemdoRef.hashCode() : 0);
        hash += (orgidRef != null ? orgidRef.hashCode() : 0);
        hash += (dateid != null ? dateid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ADiemdoAxPK)) {
            return false;
        }
        ADiemdoAxPK other = (ADiemdoAxPK) object;
        if ((this.maDiemdo == null && other.maDiemdo != null) || (this.maDiemdo != null && !this.maDiemdo.equals(other.maDiemdo))) {
            return false;
        }
        if ((this.maDiemdoRef == null && other.maDiemdoRef != null) || (this.maDiemdoRef != null && !this.maDiemdoRef.equals(other.maDiemdoRef))) {
            return false;
        }
        if ((this.orgidRef == null && other.orgidRef != null) || (this.orgidRef != null && !this.orgidRef.equals(other.orgidRef))) {
            return false;
        }
        if ((this.dateid == null && other.dateid != null) || (this.dateid != null && !this.dateid.equals(other.dateid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diemdo.ADiemdoAxPK[ maDiemdo=" + maDiemdo + ", maDiemdoRef=" + maDiemdoRef + ", orgidRef=" + orgidRef + ", dateid=" + dateid + " ]";
    }
    
}
