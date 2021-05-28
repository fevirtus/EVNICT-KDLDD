/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.report;

import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HANV
 */
@Entity
@Table(name = "S_REPORT_GROUP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "S_Report_Group.findAll", query = "SELECT s FROM S_Report_Group s"),
    @NamedQuery(name = "S_Report_Group.findByRptgroupid", query = "SELECT s FROM S_Report_Group s WHERE s.rptgroupid = :rptgroupid"),
    @NamedQuery(name = "S_Report_Group.findByRptgroupdesc", query = "SELECT s FROM S_Report_Group s WHERE s.rptgroupdesc = :rptgroupdesc"),
    @NamedQuery(name = "S_Report_Group.findByRptgroupord", query = "SELECT s FROM S_Report_Group s WHERE s.rptgroupord = :rptgroupord"),
    @NamedQuery(name = "S_Report_Group.findByNote", query = "SELECT s FROM S_Report_Group s WHERE s.note = :note"),
    @NamedQuery(name = "S_Report_Group.findByOrgid", query = "SELECT s FROM S_Report_Group s WHERE s.orgid = :orgid"),
    @NamedQuery(name = "S_Report_Group.findByEnable", query = "SELECT s FROM S_Report_Group s WHERE s.enable = :enable")})
public class S_Report_Group implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)    
    @Column(name = "RPTGROUPID")
    private String rptgroupid;
    
    
    @Column(name = "RPTGROUPID_PARENT")
    private String rptgroupid_parent;
    
    @Column(name = "RPTGROUPDESC")
    private String rptgroupdesc;
    @Column(name = "RPTGROUPORD")
    private Integer rptgroupord;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "ORGID")
    private String orgid;
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
    
    
    @Column(name = "URL")
    private String url;
    @Column(name = "GLEVEL")
    private Integer glevel;
    
    @Transient
    private Boolean bChecked;
    public Boolean getbChecked() {
        if (bChecked != null) {
            return bChecked;
        }
        return false;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getGlevel() {
        return glevel;
    }

    public void setGlevel(Integer glevel) {
        this.glevel = glevel;
    }

    
    
    public void setbChecked(Boolean bChecked) {
        this.bChecked = bChecked;
    }

    public S_Report_Group() {
    }

    public String getRptgroupid_parent() {
        return rptgroupid_parent;
    }

    public void setRptgroupid_parent(String rptgroupid_parent) {
        this.rptgroupid_parent = rptgroupid_parent;
    }

    
    public S_Report_Group(String rptgroupid) {
        this.rptgroupid = rptgroupid;
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

    
    public String getRptgroupid() {
        return rptgroupid;
    }

    public void setRptgroupid(String rptgroupid) {
        this.rptgroupid = rptgroupid;
    }

    public String getRptgroupdesc() {
        return rptgroupdesc;
    }

    public void setRptgroupdesc(String rptgroupdesc) {
        this.rptgroupdesc = rptgroupdesc;
    }

    public Integer getRptgroupord() {
        return rptgroupord;
    }

    public void setRptgroupord(Integer rptgroupord) {
        this.rptgroupord = rptgroupord;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rptgroupid != null ? rptgroupid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Report_Group)) {
            return false;
        }
        S_Report_Group other = (S_Report_Group) object;
        if ((this.rptgroupid == null && other.rptgroupid != null) || (this.rptgroupid != null && !this.rptgroupid.equals(other.rptgroupid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.S_Report_Group[ rptgroupid=" + rptgroupid + " ]";
    }
    
}
