/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diemdo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TUYEN EVN
 */
@Entity
@Table(name = "A_CONGTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ACongto.findAll", query = "SELECT a FROM ACongto a"),
    @NamedQuery(name = "ACongto.findByMaCto", query = "SELECT a FROM ACongto a WHERE a.maCto = :maCto"),
    @NamedQuery(name = "ACongto.findByLoaiCto", query = "SELECT a FROM ACongto a WHERE a.loaiCto = :loaiCto"),
    @NamedQuery(name = "ACongto.findBySerialid", query = "SELECT a FROM ACongto a WHERE a.serialid = :serialid"),
    @NamedQuery(name = "ACongto.findByTu", query = "SELECT a FROM ACongto a WHERE a.tu = :tu"),
    @NamedQuery(name = "ACongto.findByTi", query = "SELECT a FROM ACongto a WHERE a.ti = :ti"),
    @NamedQuery(name = "ACongto.findByHsnCur", query = "SELECT a FROM ACongto a WHERE a.hsnCur = :hsnCur"),
    @NamedQuery(name = "ACongto.findByHsnLoad", query = "SELECT a FROM ACongto a WHERE a.hsnLoad = :hsnLoad"),
    @NamedQuery(name = "ACongto.findByHsnHis", query = "SELECT a FROM ACongto a WHERE a.hsnHis = :hsnHis"),
    @NamedQuery(name = "ACongto.findByIsDcCur", query = "SELECT a FROM ACongto a WHERE a.isDcCur = :isDcCur"),
    @NamedQuery(name = "ACongto.findByIsDcLoad", query = "SELECT a FROM ACongto a WHERE a.isDcLoad = :isDcLoad"),
    @NamedQuery(name = "ACongto.findByIsDcHis", query = "SELECT a FROM ACongto a WHERE a.isDcHis = :isDcHis"),
    @NamedQuery(name = "ACongto.findByMoTa", query = "SELECT a FROM ACongto a WHERE a.moTa = :moTa"),
    @NamedQuery(name = "ACongto.findByUserCrId", query = "SELECT a FROM ACongto a WHERE a.userCrId = :userCrId"),
    @NamedQuery(name = "ACongto.findByUserCrDtime", query = "SELECT a FROM ACongto a WHERE a.userCrDtime = :userCrDtime"),
    @NamedQuery(name = "ACongto.findByUserMdfId", query = "SELECT a FROM ACongto a WHERE a.userMdfId = :userMdfId"),
    @NamedQuery(name = "ACongto.findByUserMdfDtime", query = "SELECT a FROM ACongto a WHERE a.userMdfDtime = :userMdfDtime"),
    @NamedQuery(name = "ACongto.findByOrgid", query = "SELECT a FROM ACongto a WHERE a.orgid = :orgid")})
public class ACongto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MA_CTO")
    private String maCto;
    @Column(name = "LOAI_CTO")
    private String loaiCto;
    @Column(name = "SERIALID")
    private String serialid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TU")
    private BigDecimal tu;
    @Column(name = "TI")
    private BigDecimal ti;
    @Column(name = "HSN_CUR")
    private BigDecimal hsnCur;
    @Column(name = "HSN_LOAD")
    private BigDecimal hsnLoad;
    @Column(name = "HSN_HIS")
    private BigDecimal hsnHis;
    @Column(name = "IS_DC_CUR")
    private BigInteger isDcCur;
    @Column(name = "IS_DC_LOAD")
    private BigInteger isDcLoad;
    @Column(name = "IS_DC_HIS")
    private BigInteger isDcHis;
    @Column(name = "MO_TA")
    private String moTa;
    @Column(name = "USER_CR_ID")
    private String userCrId;
    @Column(name = "USER_CR_DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userCrDtime;
    @Column(name = "USER_MDF_ID")
    private String userMdfId;
    @Column(name = "USER_MDF_DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userMdfDtime;
    @Column(name = "ORGID")
    private String orgid;
    @OneToMany(mappedBy = "maCto")
    private Collection<ADiemdo> aDiemdoCollection;

    public ACongto() {
    }

    public ACongto(String maCto) {
        this.maCto = maCto;
    }

    public String getMaCto() {
        return maCto;
    }

    public void setMaCto(String maCto) {
        this.maCto = maCto;
    }

    public String getLoaiCto() {
        return loaiCto;
    }

    public void setLoaiCto(String loaiCto) {
        this.loaiCto = loaiCto;
    }

    public String getSerialid() {
        return serialid;
    }

    public void setSerialid(String serialid) {
        this.serialid = serialid;
    }

    public BigDecimal getTu() {
        return tu;
    }

    public void setTu(BigDecimal tu) {
        this.tu = tu;
    }

    public BigDecimal getTi() {
        return ti;
    }

    public void setTi(BigDecimal ti) {
        this.ti = ti;
    }

    public BigDecimal getHsnCur() {
        return hsnCur;
    }

    public void setHsnCur(BigDecimal hsnCur) {
        this.hsnCur = hsnCur;
    }

    public BigDecimal getHsnLoad() {
        return hsnLoad;
    }

    public void setHsnLoad(BigDecimal hsnLoad) {
        this.hsnLoad = hsnLoad;
    }

    public BigDecimal getHsnHis() {
        return hsnHis;
    }

    public void setHsnHis(BigDecimal hsnHis) {
        this.hsnHis = hsnHis;
    }

    public BigInteger getIsDcCur() {
        return isDcCur;
    }

    public void setIsDcCur(BigInteger isDcCur) {
        this.isDcCur = isDcCur;
    }

    public BigInteger getIsDcLoad() {
        return isDcLoad;
    }

    public void setIsDcLoad(BigInteger isDcLoad) {
        this.isDcLoad = isDcLoad;
    }

    public BigInteger getIsDcHis() {
        return isDcHis;
    }

    public void setIsDcHis(BigInteger isDcHis) {
        this.isDcHis = isDcHis;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getUserCrId() {
        return userCrId;
    }

    public void setUserCrId(String userCrId) {
        this.userCrId = userCrId;
    }

    public Date getUserCrDtime() {
        return userCrDtime;
    }

    public void setUserCrDtime(Date userCrDtime) {
        this.userCrDtime = userCrDtime;
    }

    public String getUserMdfId() {
        return userMdfId;
    }

    public void setUserMdfId(String userMdfId) {
        this.userMdfId = userMdfId;
    }

    public Date getUserMdfDtime() {
        return userMdfDtime;
    }

    public void setUserMdfDtime(Date userMdfDtime) {
        this.userMdfDtime = userMdfDtime;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    @XmlTransient
    public Collection<ADiemdo> getADiemdoCollection() {
        return aDiemdoCollection;
    }

    public void setADiemdoCollection(Collection<ADiemdo> aDiemdoCollection) {
        this.aDiemdoCollection = aDiemdoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maCto != null ? maCto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ACongto)) {
            return false;
        }
        ACongto other = (ACongto) object;
        if ((this.maCto == null && other.maCto != null) || (this.maCto != null && !this.maCto.equals(other.maCto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diemdo.ACongto[ maCto=" + maCto + " ]";
    }
    
}
