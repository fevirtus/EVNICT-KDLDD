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
@Table(name = "S_REPORT_DATALIST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "S_Report_Datalist.findAll", query = "SELECT s FROM S_Report_Datalist s"),
    @NamedQuery(name = "S_Report_Datalist.findByDatalistid", query = "SELECT s FROM S_Report_Datalist s WHERE s.datalistid = :datalistid"),
    @NamedQuery(name = "S_Report_Datalist.findByDatalistdesc", query = "SELECT s FROM S_Report_Datalist s WHERE s.datalistdesc = :datalistdesc"),
    @NamedQuery(name = "S_Report_Datalist.findByDatalistord", query = "SELECT s FROM S_Report_Datalist s WHERE s.datalistord = :datalistord"),
    @NamedQuery(name = "S_Report_Datalist.findByCommandtypeid", query = "SELECT s FROM S_Report_Datalist s WHERE s.commandtypeid = :commandtypeid"),
    @NamedQuery(name = "S_Report_Datalist.findByQuery", query = "SELECT s FROM S_Report_Datalist s WHERE s.query = :query"),
    @NamedQuery(name = "S_Report_Datalist.findByEnable", query = "SELECT s FROM S_Report_Datalist s WHERE s.enable = :enable")})
public class S_Report_Datalist implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DATALISTID")
    private String datalistid;
    @Column(name = "DATALISTDESC")
    private String datalistdesc;
    @Column(name = "DATALISTORD")
    private Integer datalistord;
    @Column(name = "COMMANDTYPEID")
    private String commandtypeid;
    @Column(name = "QUERY")
    private String query;
    @Column(name = "ENABLE")
    private boolean enable;
    @Column(name = "RPTID")
    private String rptid;

    @Column(name = "LOADTYPE")
    private boolean loadtype;
    
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
    public S_Report_Datalist() {
    }

    public S_Report_Datalist(String datalistid) {
        this.datalistid = datalistid;
    }

    public boolean getLoadtype() {
        return loadtype;
    }

    public void setLoadtype(boolean loadtype) {
        this.loadtype = loadtype;
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

    
    public String getDatalistid() {
        return datalistid;
    }

    public void setDatalistid(String datalistid) {
        this.datalistid = datalistid;
    }

    public String getDatalistdesc() {
        return datalistdesc;
    }

    public void setDatalistdesc(String datalistdesc) {
        this.datalistdesc = datalistdesc;
    }

    public Integer getDatalistord() {
        return datalistord;
    }

    public void setDatalistord(Integer datalistord) {
        this.datalistord = datalistord;
    }

    public String getCommandtypeid() {
        return commandtypeid;
    }

    public void setCommandtypeid(String commandtypeid) {
        this.commandtypeid = commandtypeid;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
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
        hash += (datalistid != null ? datalistid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Report_Datalist)) {
            return false;
        }
        S_Report_Datalist other = (S_Report_Datalist) object;
        if ((this.datalistid == null && other.datalistid != null) || (this.datalistid != null && !this.datalistid.equals(other.datalistid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.S_Report_Datalist[ datalistid=" + datalistid + " ]";
    }
    
}
