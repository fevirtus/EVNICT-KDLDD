/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diemdo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TUYEN EVN
 */
@Entity
@Table(name = "A_DIEMDO_AX")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ADiemdoAx.findAll", query = "SELECT a FROM ADiemdoAx a"),
    @NamedQuery(name = "ADiemdoAx.findByMaDiemdo", query = "SELECT a FROM ADiemdoAx a WHERE a.aDiemdoAxPK.maDiemdo = :maDiemdo"),
    @NamedQuery(name = "ADiemdoAx.findByMaDiemdoRef", query = "SELECT a FROM ADiemdoAx a WHERE a.aDiemdoAxPK.maDiemdoRef = :maDiemdoRef"),
    @NamedQuery(name = "ADiemdoAx.findByOrgidRef", query = "SELECT a FROM ADiemdoAx a WHERE a.aDiemdoAxPK.orgidRef = :orgidRef"),
    @NamedQuery(name = "ADiemdoAx.findByDateid", query = "SELECT a FROM ADiemdoAx a WHERE a.aDiemdoAxPK.dateid = :dateid"),
    @NamedQuery(name = "ADiemdoAx.findByNgay", query = "SELECT a FROM ADiemdoAx a WHERE a.ngay = :ngay"),
    @NamedQuery(name = "ADiemdoAx.findByMoTa", query = "SELECT a FROM ADiemdoAx a WHERE a.moTa = :moTa"),
    @NamedQuery(name = "ADiemdoAx.findByNgayKt", query = "SELECT a FROM ADiemdoAx a WHERE a.ngayKt = :ngayKt"),
    @NamedQuery(name = "ADiemdoAx.findByUserCrId", query = "SELECT a FROM ADiemdoAx a WHERE a.userCrId = :userCrId"),
    @NamedQuery(name = "ADiemdoAx.findByUserCrTime", query = "SELECT a FROM ADiemdoAx a WHERE a.userCrTime = :userCrTime"),
    @NamedQuery(name = "ADiemdoAx.findByUserMdfId", query = "SELECT a FROM ADiemdoAx a WHERE a.userMdfId = :userMdfId"),
    @NamedQuery(name = "ADiemdoAx.findByUserMdfTime", query = "SELECT a FROM ADiemdoAx a WHERE a.userMdfTime = :userMdfTime")})
public class ADiemdoAx implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ADiemdoAxPK aDiemdoAxPK;
    @Column(name = "NGAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngay;
    @Column(name = "MO_TA")
    private String moTa;
    @Column(name = "NGAY_KT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayKt;
    @Column(name = "USER_CR_ID")
    private String userCrId;
    @Column(name = "USER_CR_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userCrTime;
    @Column(name = "USER_MDF_ID")
    private String userMdfId;
    @Column(name = "USER_MDF_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userMdfTime;
    @JoinColumn(name = "MA_DIEMDO", referencedColumnName = "MA_DIEMDO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ADiemdo aDiemdo;

    public ADiemdoAx() {
    }

    public ADiemdoAx(ADiemdoAxPK aDiemdoAxPK) {
        this.aDiemdoAxPK = aDiemdoAxPK;
    }

    public ADiemdoAx(String maDiemdo, String maDiemdoRef, String orgidRef, BigInteger dateid) {
        this.aDiemdoAxPK = new ADiemdoAxPK(maDiemdo, maDiemdoRef, orgidRef, dateid);
    }

    public ADiemdoAxPK getADiemdoAxPK() {
        return aDiemdoAxPK;
    }

    public void setADiemdoAxPK(ADiemdoAxPK aDiemdoAxPK) {
        this.aDiemdoAxPK = aDiemdoAxPK;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getNgayKt() {
        return ngayKt;
    }

    public void setNgayKt(Date ngayKt) {
        this.ngayKt = ngayKt;
    }

    public String getUserCrId() {
        return userCrId;
    }

    public void setUserCrId(String userCrId) {
        this.userCrId = userCrId;
    }

    public Date getUserCrTime() {
        return userCrTime;
    }

    public void setUserCrTime(Date userCrTime) {
        this.userCrTime = userCrTime;
    }

    public String getUserMdfId() {
        return userMdfId;
    }

    public void setUserMdfId(String userMdfId) {
        this.userMdfId = userMdfId;
    }

    public Date getUserMdfTime() {
        return userMdfTime;
    }

    public void setUserMdfTime(Date userMdfTime) {
        this.userMdfTime = userMdfTime;
    }

    public ADiemdo getADiemdo() {
        return aDiemdo;
    }

    public void setADiemdo(ADiemdo aDiemdo) {
        this.aDiemdo = aDiemdo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aDiemdoAxPK != null ? aDiemdoAxPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ADiemdoAx)) {
            return false;
        }
        ADiemdoAx other = (ADiemdoAx) object;
        if ((this.aDiemdoAxPK == null && other.aDiemdoAxPK != null) || (this.aDiemdoAxPK != null && !this.aDiemdoAxPK.equals(other.aDiemdoAxPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diemdo.ADiemdoAx[ aDiemdoAxPK=" + aDiemdoAxPK + " ]";
    }
    
}
