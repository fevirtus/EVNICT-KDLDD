/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author khiemvk
 */
@Entity
@Table(name = "S_ATTRIBUTE_GROUP",uniqueConstraints={})
public class S_Attribute_Group implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ATTRGROUPID")
    private String attrgroupid;
    @Column(name = "ATTRGROUPDESC")
    private String attrgroupdesc;
    @Column(name = "ORGID")
    private String orgid;
    @Column(name = "USINGBY")
    private String usingby;
    @Column(name = "DEFAULTTOALL")
    private String defaulttoall;
    @Column(name = "TABLENAME_DATA")
    private String tablenameData;
    @Column(name = "ISFIX")
    private Boolean isfix;
    @Column(name = "ENABLE")
    private Boolean enable;
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
    
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ORGID", unique = false, insertable = false, updatable = false, nullable = true)
    private S_Organization sOrganization;

    //Join
//    @OneToMany(mappedBy = "attrid")
//    private List<S_Attribute> listAttribute = new Vector<S_Attribute>();

    @Transient
    private Boolean bChecked;

//    public Integer getCounta() {
//        return listAttribute.size();
//    }
//
//    public List<S_Attribute> getListAttribute() {
//        return listAttribute;
//    }
//
//    public void setListAttribute(List<S_Attribute> listAttribute) {
//        this.listAttribute = listAttribute;
//    }
    

   
    public S_Attribute_Group() {
    }

    public S_Attribute_Group(String attrgroupid) {
        this.attrgroupid = attrgroupid;
    }

    public String getAttrgroupid() {
        return attrgroupid;
    }

    public void setAttrgroupid(String attrgroupid) {
        this.attrgroupid = attrgroupid;
    }

    public String getAttrgroupdesc() {
        return attrgroupdesc;
    }

    public void setAttrgroupdesc(String attrgroupdesc) {
        this.attrgroupdesc = attrgroupdesc;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getUsingby() {
        return usingby;
    }

    public void setUsingby(String usingby) {
        this.usingby = usingby;
    }

    public String getDefaulttoall() {
        return defaulttoall;
    }

    public void setDefaulttoall(String defaulttoall) {
        this.defaulttoall = defaulttoall;
    }

    public String getTablenameData() {
        return tablenameData;
    }

    public void setTablenameData(String tablenameData) {
        this.tablenameData = tablenameData;
    }

    public Boolean getIsfix() {
        return isfix;
    }

    public void setIsfix(Boolean isfix) {
        this.isfix = isfix;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
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

     public S_Organization getsOrganization() {
        return sOrganization;
    }

    public void setsOrganization(S_Organization sOrganization) {
        this.sOrganization = sOrganization;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attrgroupid != null ? attrgroupid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Attribute_Group)) {
            return false;
        }
        S_Attribute_Group other = (S_Attribute_Group) object;
        if ((this.attrgroupid == null && other.attrgroupid != null) || (this.attrgroupid != null && !this.attrgroupid.equals(other.attrgroupid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Shared.S_Attribute_Group[attrgroupid=" + attrgroupid + "]";
    }
    public Boolean getbChecked() {
        if (bChecked != null) {
            return bChecked;
        }
        return false;
    }

    public void setbChecked(Boolean bChecked) {
        this.bChecked = bChecked;
    }
}
