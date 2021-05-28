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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "S_REPORT_FORM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "S_Report_Form.findAll", query = "SELECT s FROM S_Report_Form s"),
    @NamedQuery(name = "S_Report_Form.findByRptformid", query = "SELECT s FROM S_Report_Form s WHERE s.rptformid = :rptformid"),
    @NamedQuery(name = "S_Report_Form.findByRptformdesc", query = "SELECT s FROM S_Report_Form s WHERE s.rptformdesc = :rptformdesc"),
    @NamedQuery(name = "S_Report_Form.findByEnable", query = "SELECT s FROM S_Report_Form s WHERE s.enable = :enable"),
    @NamedQuery(name = "S_Report_Form.findByDefaultform", query = "SELECT s FROM S_Report_Form s WHERE s.defaultform = :defaultform"),
    @NamedQuery(name = "S_Report_Form.findByRptformord", query = "SELECT s FROM S_Report_Form s WHERE s.rptformord = :rptformord"),
    @NamedQuery(name = "S_Report_Form.findByFilename", query = "SELECT s FROM S_Report_Form s WHERE s.filename = :filename"),
    @NamedQuery(name = "S_Report_Form.findByNote", query = "SELECT s FROM S_Report_Form s WHERE s.note = :note")})
public class S_Report_Form implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RPTFORMID")
    private String rptformid;
    @Column(name = "RPTFORMDESC")
    private String rptformdesc;
    @Column(name = "ENABLE")
    private boolean enable;
    @Column(name = "DEFAULTFORM")
    private boolean defaultform;
    @Column(name = "RPTFORMORD")
    private Integer rptformord;
    @Column(name = "FILENAME")
    private String filename;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "RPTID")
    private String rptid;

    @Lob
    @Column(name = "FILEDATA")
    private byte[] filedata;

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

    public S_Report_Form() {
    }

    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }

    public S_Report_Form(String rptformid) {
        this.rptformid = rptformid;
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

    public String getRptformid() {
        return rptformid;
    }

    public void setRptformid(String rptformid) {
        this.rptformid = rptformid;
    }

    public String getRptformdesc() {
        return rptformdesc;
    }

    public void setRptformdesc(String rptformdesc) {
        this.rptformdesc = rptformdesc;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean getDefaultform() {
        return defaultform;
    }

    public void setDefaultform(boolean defaultform) {
        this.defaultform = defaultform;
    }

    public Integer getRptformord() {
        return rptformord;
    }

    public void setRptformord(Integer rptformord) {
        this.rptformord = rptformord;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRptid() {
        return rptid;
    }

    public void setRptid(String rptid) {
        this.rptid = rptid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rptformid != null ? rptformid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Report_Form)) {
            return false;
        }
        S_Report_Form other = (S_Report_Form) object;
        if ((this.rptformid == null && other.rptformid != null) || (this.rptformid != null && !this.rptformid.equals(other.rptformid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.S_Report_Form[ rptformid=" + rptformid + " ]";
    }

}
