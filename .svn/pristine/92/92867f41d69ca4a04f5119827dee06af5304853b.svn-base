/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import main.entity.shared.admin.Q_Function;
import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author thaodd
 */
@Entity
@Table(name = "SM_SHORTCUT")
//@NamedQueries({
//    @NamedQuery(name = "Sm_Shortcut.findAll", query = "SELECT s FROM Sm_Shortcut s"),
//    @NamedQuery(name = "Sm_Shortcut.findByShortcutid", query = "SELECT s FROM Sm_Shortcut s WHERE s.shortcutid = :shortcutid"),
//    @NamedQuery(name = "Sm_Shortcut.findByUserid", query = "SELECT s FROM Sm_Shortcut s WHERE s.userid = :userid"),
//    @NamedQuery(name = "Sm_Shortcut.findByAppfunctionid", query = "SELECT s FROM Sm_Shortcut s WHERE s.appfunctionid = :appfunctionid"),
//    @NamedQuery(name = "Sm_Shortcut.findByFunctionid", query = "SELECT s FROM Sm_Shortcut s WHERE s.functionid = :functionid")
//})
public class Sm_Shortcut implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SHORTCUTID")
    private String shortcutid;
    @Basic(optional = false)
    @Column(name = "SHORTCUTDESC")
    private String shortcutdesc;
    @Column(name = "USERID")
    private String userid;
    @Column(name = "APPFUNCTIONID")
    private String appfunctionid;
    @Column(name = "FUNCTIONID")
    private String functionid;
    @Basic(optional = false)
    @Column(name = "URL")
    private String url;
    @Column(name = "URLORD")
    private Integer urlord;
    @Column(name = "ICON")
    private String icon;
    @Column(name = "ONNEWWINDOW")
    private Boolean onnewwindow;
    @Column(name = "SHORTCUTGRPID")
    private String shortcutgrpid;
    @JoinColumn(name = "SHORTCUTGRPID", referencedColumnName = "SHORTCUTGRPID", unique = false, insertable = false, updatable = false, nullable = true)
    @ManyToOne
    private Sm_Shortcutgroup smShortcutgroup;
    @JoinColumn(name = "FUNCTIONID", referencedColumnName = "FUNCTIONID", unique = false, insertable = false, updatable = false, nullable = true)
    @ManyToOne
    private Q_Function qfunction;

    public Q_Function getqfunction() {
        return qfunction;
    }

    public void setqfunction(Q_Function qFunction) {
        this.qfunction = qFunction;
    }
    @Transient
    private Boolean bChecked;
    
    public Sm_Shortcut() {
    }

    public Sm_Shortcut(String shortcutid) {
        this.shortcutid = shortcutid;
    }

    public Sm_Shortcut(String shortcutid, String shorhcutdesc, String url) {
        this.shortcutid = shortcutid;
        this.shortcutdesc = shorhcutdesc;
        this.url = url;
    }

    public String getShortcutid() {
        return shortcutid;
    }

    public void setShortcutid(String shortcutid) {
        this.shortcutid = shortcutid;
    }

    public String getShortcutdesc() {
        return shortcutdesc;
    }

    public void setShortcutdesc(String shorhcutdesc) {
        this.shortcutdesc = shorhcutdesc;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAppfunctionid() {
        return appfunctionid;
    }

    public void setAppfunctionid(String appfunctionid) {
        this.appfunctionid = appfunctionid;
    }

    public String getFunctionid() {
        return functionid;
    }

    public void setFunctionid(String functionid) {
        this.functionid = functionid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUrlord() {
        return urlord;
    }

    public void setUrlord(Integer urlord) {
        this.urlord = urlord;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getOnnewwindow() {
        return onnewwindow;
    }

    public void setOnnewwindow(Boolean onnewwindow) {
        this.onnewwindow = onnewwindow;
    }


    public String getShortcutgrpid() {
        return shortcutgrpid;
    }

    public void setShortcutgrpid(String shortcutgrpid) {
        this.shortcutgrpid = shortcutgrpid;
    }

    public Sm_Shortcutgroup getSmShortcutgroup() {
        return smShortcutgroup;
    }

    public void setSmShortcutgroup(Sm_Shortcutgroup smShortcutgroup) {
        this.smShortcutgroup = smShortcutgroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shortcutid != null ? shortcutid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sm_Shortcut)) {
            return false;
        }
        Sm_Shortcut other = (Sm_Shortcut) object;
        if ((this.shortcutid == null && other.shortcutid != null) || (this.shortcutid != null && !this.shortcutid.equals(other.shortcutid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sm_Shortcut[shortcutid=" + shortcutid + "]";
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
