/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.admin;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ba
 */
@Entity
@Table(name="S_MAIL_CONFIG")
public class S_Mail_Config implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "HOSTNAME")
    private String host;
    @Column(name = "PORT")
    private String port;
    @Column(name = "USERNAME")
    private String user;
    @Column(name = "PASS")
    private String pass;
    @Column(name = "EMAIL")
    private String mail;
    @Column(name = "NUM_OF_DAYS")
    private Integer numofdays;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "MESSAGE_CONTENT")
    private String messagecontent;
    @Column(name = "FIRSTWEEK")
    private int firstweek;
    @Column(name = "WEEKEND")
    private int weekend;
    @Column(name = "TITLE_REPORT")
    private String titlereport;
    @Column(name = "CONTENT_REPORT")
    private String contentreport;
    @Column(name = "FIRSTMONTH")
    private int firstmonth;
    @Column(name = "REPORT_DAY")
    private int reportday;
    @Column(name = "REPORT_PATH")
    private String reportpath;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getNumofdays() {
        return numofdays;
    }

    public void setNumofdays(Integer numofdays) {
        this.numofdays = numofdays;
    }

    public String getMessagecontent() {
        return messagecontent;
    }

    public void setMessagecontent(String messagecontent) {
        this.messagecontent = messagecontent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentreport() {
        return contentreport;
    }

    public void setContentreport(String contentreport) {
        this.contentreport = contentreport;
    }

    public int getFirstmonth() {
        return firstmonth;
    }

    public void setFirstmonth(int firstmonth) {
        this.firstmonth = firstmonth;
    }

    public int getFirstweek() {
        return firstweek;
    }

    public void setFirstweek(int firstweek) {
        this.firstweek = firstweek;
    }

    public String getTitlereport() {
        return titlereport;
    }

    public void setTitlereport(String titlereport) {
        this.titlereport = titlereport;
    }

    public int getWeekend() {
        return weekend;
    }

    public void setWeekend(int weekend) {
        this.weekend = weekend;
    }

    public int getReportday() {
        return reportday;
    }

    public void setReportday(int reportday) {
        this.reportday = reportday;
    }

    public String getReportpath() {
        return reportpath;
    }

    public void setReportpath(String reportpath) {
        this.reportpath = reportpath;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Mail_Config)) {
            return false;
        }
        S_Mail_Config other = (S_Mail_Config) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.admin.S_Mail_Config[ id=" + id + " ]";
    }
}
