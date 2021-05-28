/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

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

/**
 *
 * @author thaodd
 */
@Entity
@Table(name = "SM_INBOX")
//@NamedQueries({
//    @NamedQuery(name = "Sm_Inbox.findAll", query = "SELECT s FROM Sm_Inbox s"),
//    @NamedQuery(name = "Sm_Inbox.findByMsgid", query = "SELECT s FROM Sm_Inbox s WHERE s.msgid = :msgid"),
//    @NamedQuery(name = "Sm_Inbox.findByAppfunctionid", query = "SELECT s FROM Sm_Inbox s WHERE s.appfunctionid = :appfunctionid"),
//    @NamedQuery(name = "Sm_Inbox.findByUserid", query = "SELECT s FROM Sm_Inbox s WHERE s.userid = :userid"),
//    @NamedQuery(name = "Sm_Inbox.findByHidden", query = "SELECT s FROM Sm_Inbox s WHERE s.hidden = :hidden")})
public class Sm_Inbox implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MSGID")
    private String msgid;
    @Column(name = "APPFUNCTIONID")
    private String appfunctionid;
    @Column(name = "USERID")
    private String userid;
    @Basic(optional = false)
    @Column(name = "SUBJECT")
    private String subject;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "SENDDTIME")
    @Temporal(TemporalType.DATE)
    private Date senddtime;
    @Column(name = "READDTIME")
    @Temporal(TemporalType.DATE)
    private Date readdtime;
    @Column(name = "HIDDEN")
    private Boolean hidden;

    public Sm_Inbox() {
    }

    public Sm_Inbox(String msgid) {
        this.msgid = msgid;
    }

    public Sm_Inbox(String msgid, String subject) {
        this.msgid = msgid;
        this.subject = subject;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getAppfunctionid() {
        return appfunctionid;
    }

    public void setAppfunctionid(String appfunctionid) {
        this.appfunctionid = appfunctionid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSenddtime() {
        return senddtime;
    }

    public void setSenddtime(Date senddtime) {
        this.senddtime = senddtime;
    }

    public Date getReaddtime() {
        return readdtime;
    }

    public void setReaddtime(Date readdtime) {
        this.readdtime = readdtime;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (msgid != null ? msgid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sm_Inbox)) {
            return false;
        }
        Sm_Inbox other = (Sm_Inbox) object;
        if ((this.msgid == null && other.msgid != null) || (this.msgid != null && !this.msgid.equals(other.msgid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sm_Inbox[msgid=" + msgid + "]";
    }

    public String getHtmlNewImg()
    {
        if (readdtime == null)
            return "/images/new.gif";
        return "";
    }

    @Transient
    Boolean bChecked;

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
