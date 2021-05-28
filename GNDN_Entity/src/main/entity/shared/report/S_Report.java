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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HANV
 */
@Entity
@Table(name = "S_REPORT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "S_Report.findAll", query = "SELECT s FROM S_Report s"),
    @NamedQuery(name = "S_Report.findByRptid", query = "SELECT s FROM S_Report s WHERE s.rptid = :rptid"),
    @NamedQuery(name = "S_Report.findByRptdesc", query = "SELECT s FROM S_Report s WHERE s.rptdesc = :rptdesc"),
    @NamedQuery(name = "S_Report.findByRptord", query = "SELECT s FROM S_Report s WHERE s.rptord = :rptord"),
    @NamedQuery(name = "S_Report.findByEnable", query = "SELECT s FROM S_Report s WHERE s.enable = :enable")})
public class S_Report implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RPTID")
    private String rptid;
    @Column(name = "RPTDESC")
    private String rptdesc;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "RPTORD")
    private Integer rptord;
    @Column(name = "ENABLE")
    private boolean enable;    
    @Column(name = "RPTGROUPID")
    private String rptgroupid;
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
    public S_Report() {
    }

    public S_Report(String rptid) {
        this.rptid = rptid;
    }
    
    public S_Report(String rptid, String rptdesc) {
        this.rptid = rptid;
        this.rptdesc = rptdesc;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    
    public String getRptid() {
        return rptid;
    }

    public void setRptid(String rptid) {
        this.rptid = rptid;
    }

    public String getRptdesc() {
        return rptdesc;
    }

    public void setRptdesc(String rptdesc) {
        this.rptdesc = rptdesc;
    }

    public Integer getRptord() {
        return rptord;
    }

    public void setRptord(Integer rptord) {
        this.rptord = rptord;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rptid != null ? rptid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Report)) {
            return false;
        }
        S_Report other = (S_Report) object;
        if ((this.rptid == null && other.rptid != null) || (this.rptid != null && !this.rptid.equals(other.rptid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.S_Report[ rptid=" + rptid + " ]";
    }
    
}
