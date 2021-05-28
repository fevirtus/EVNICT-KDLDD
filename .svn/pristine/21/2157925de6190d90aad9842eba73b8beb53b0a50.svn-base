/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diemdo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "A_LST_CATEGORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ALstCategory.findAll", query = "SELECT a FROM ALstCategory a"),
    @NamedQuery(name = "ALstCategory.findByCategoryid", query = "SELECT a FROM ALstCategory a WHERE a.categoryid = :categoryid"),
    @NamedQuery(name = "ALstCategory.findByCategorydesc", query = "SELECT a FROM ALstCategory a WHERE a.categorydesc = :categorydesc"),
    @NamedQuery(name = "ALstCategory.findByCategoryidParent", query = "SELECT a FROM ALstCategory a WHERE a.categoryidParent = :categoryidParent"),
    @NamedQuery(name = "ALstCategory.findByOrgid", query = "SELECT a FROM ALstCategory a WHERE a.orgid = :orgid"),
    @NamedQuery(name = "ALstCategory.findByCategoryord", query = "SELECT a FROM ALstCategory a WHERE a.categoryord = :categoryord"),
    @NamedQuery(name = "ALstCategory.findByAssetidprefix", query = "SELECT a FROM ALstCategory a WHERE a.assetidprefix = :assetidprefix"),
    @NamedQuery(name = "ALstCategory.findByUserCrId", query = "SELECT a FROM ALstCategory a WHERE a.userCrId = :userCrId"),
    @NamedQuery(name = "ALstCategory.findByUserCrDtime", query = "SELECT a FROM ALstCategory a WHERE a.userCrDtime = :userCrDtime"),
    @NamedQuery(name = "ALstCategory.findByUserMdfId", query = "SELECT a FROM ALstCategory a WHERE a.userMdfId = :userMdfId"),
    @NamedQuery(name = "ALstCategory.findByUserMdfDtime", query = "SELECT a FROM ALstCategory a WHERE a.userMdfDtime = :userMdfDtime"),
    @NamedQuery(name = "ALstCategory.findByTypeid", query = "SELECT a FROM ALstCategory a WHERE a.typeid = :typeid"),
    @NamedQuery(name = "ALstCategory.findByUlevelid", query = "SELECT a FROM ALstCategory a WHERE a.ulevelid = :ulevelid")})
public class ALstCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CATEGORYID")
    private String categoryid;
    @Column(name = "CATEGORYDESC")
    private String categorydesc;
    @Column(name = "CATEGORYID_PARENT")
    private String categoryidParent;
    @Column(name = "ORGID")
    private String orgid;
    @Column(name = "CATEGORYORD")
    private BigInteger categoryord;
    @Column(name = "ASSETIDPREFIX")
    private String assetidprefix;
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
    @Column(name = "TYPEID")
    private String typeid;
    @Column(name = "ULEVELID")
    private String ulevelid;

    public ALstCategory() {
    }

    public ALstCategory(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategorydesc() {
        return categorydesc;
    }

    public void setCategorydesc(String categorydesc) {
        this.categorydesc = categorydesc;
    }

    public String getCategoryidParent() {
        return categoryidParent;
    }

    public void setCategoryidParent(String categoryidParent) {
        this.categoryidParent = categoryidParent;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public BigInteger getCategoryord() {
        return categoryord;
    }

    public void setCategoryord(BigInteger categoryord) {
        this.categoryord = categoryord;
    }

    public String getAssetidprefix() {
        return assetidprefix;
    }

    public void setAssetidprefix(String assetidprefix) {
        this.assetidprefix = assetidprefix;
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

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getUlevelid() {
        return ulevelid;
    }

    public void setUlevelid(String ulevelid) {
        this.ulevelid = ulevelid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryid != null ? categoryid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ALstCategory)) {
            return false;
        }
        ALstCategory other = (ALstCategory) object;
        if ((this.categoryid == null && other.categoryid != null) || (this.categoryid != null && !this.categoryid.equals(other.categoryid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diemdo.ALstCategory[ categoryid=" + categoryid + " ]";
    }
    
}
