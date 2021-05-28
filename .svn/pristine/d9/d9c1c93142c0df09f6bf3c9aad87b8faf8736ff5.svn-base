/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author thaodd
 */
@Entity
@Table(name = "SM_SHORTCUTGROUP")
//@NamedQueries({
//    @NamedQuery(name = "Sm_Shortcutgroup.findAll", query = "SELECT s FROM Sm_Shortcutgroup s"),
//    @NamedQuery(name = "Sm_Shortcutgroup.findByShortcutgrpid", query = "SELECT s FROM Sm_Shortcutgroup s WHERE s.shortcutgrpid = :shortcutgrpid"),
//    @NamedQuery(name = "Sm_Shortcutgroup.findByUserid", query = "SELECT s FROM Sm_Shortcutgroup s WHERE s.userid = :userid"),
//    @NamedQuery(name = "Sm_Shortcutgroup.findByAppfunctionid", query = "SELECT s FROM Sm_Shortcutgroup s WHERE s.appfunctionid = :appfunctionid")
//})
public class Sm_Shortcutgroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SHORTCUTGRPID")
    private String shortcutgrpid;
    @Column(name = "USERID")
    private String userid;
    @Column(name = "SHORTCUTGRPDESC")
    private String shortcutgrpdesc;
    @Column(name = "SHORTCUTGRPORD")
    private Integer shortcutgrpord;
    @Column(name = "APPFUNCTIONID")
    private String appfunctionid;
    @OneToMany(mappedBy = "smShortcutgroup")
    private Collection<Sm_Shortcut> smShortcutCollection;

    public Sm_Shortcutgroup() {
    }

    public Sm_Shortcutgroup(String shortcutgrpid) {
        this.shortcutgrpid = shortcutgrpid;
    }

    public String getShortcutgrpid() {
        return shortcutgrpid;
    }

    public void setShortcutgrpid(String shortcutgrpid) {
        this.shortcutgrpid = shortcutgrpid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getShortcutgrpdesc() {
        return shortcutgrpdesc;
    }

    public void setShortcutgrpdesc(String shortcutgrpdesc) {
        this.shortcutgrpdesc = shortcutgrpdesc;
    }

    public Integer getShortcutgrpord() {
        return shortcutgrpord;
    }

    public void setShortcutgrpord(Integer shortcutgrpord) {
        this.shortcutgrpord = shortcutgrpord;
    }

    public String getAppfunctionid() {
        return appfunctionid;
    }

    public void setAppfunctionid(String appfunctionid) {
        this.appfunctionid = appfunctionid;
    }

    public Collection<Sm_Shortcut> getSmShortcutCollection() {
        return smShortcutCollection;
    }

    public void setSmShortcutCollection(Collection<Sm_Shortcut> smShortcutCollection) {
        this.smShortcutCollection = smShortcutCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shortcutgrpid != null ? shortcutgrpid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sm_Shortcutgroup)) {
            return false;
        }
        Sm_Shortcutgroup other = (Sm_Shortcutgroup) object;
        if ((this.shortcutgrpid == null && other.shortcutgrpid != null) || (this.shortcutgrpid != null && !this.shortcutgrpid.equals(other.shortcutgrpid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sm_Shortcutgroup[shortcutgrpid=" + shortcutgrpid + "]";
    }

}
