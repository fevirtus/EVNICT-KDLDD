/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "SM_HOMEPAGE")
//@NamedQueries({
//    @NamedQuery(name = "Sm_Homepage.findAll", query = "SELECT s FROM Sm_Homepage s"),
//    @NamedQuery(name = "Sm_Homepage.findByHomepageid", query = "SELECT s FROM Sm_Homepage s WHERE s.homepageid = :homepageid"),
//    @NamedQuery(name = "Sm_Homepage.findByHomepagedesc", query = "SELECT s FROM Sm_Homepage s WHERE s.homepagedesc = :homepagedesc"),
//    @NamedQuery(name = "Sm_Homepage.findByAppfunctionid", query = "SELECT s FROM Sm_Homepage s WHERE s.appfunctionid = :appfunctionid")})
public class Sm_Homepage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "HOMEPAGEID")
    private String homepageid;
    @Basic(optional = false)
    @Column(name = "HOMEPAGEDESC")
    private String homepagedesc;
    @Basic(optional = false)
    @Column(name = "APPFUNCTIONID")
    private String appfunctionid;
    @Column(name = "PLTPOSGROUP")
    private String pltposgroup;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "smHomepage")
    private List<Sm_Homepage_Plt> smHomepagePltList;

    public Sm_Homepage() {
    }

    public Sm_Homepage(String homepageid) {
        this.homepageid = homepageid;
    }

    public Sm_Homepage(String homepageid, String homepagedesc, String appfunctionid) {
        this.homepageid = homepageid;
        this.homepagedesc = homepagedesc;
        this.appfunctionid = appfunctionid;
    }

    public String getHomepageid() {
        return homepageid;
    }

    public void setHomepageid(String homepageid) {
        this.homepageid = homepageid;
    }

    public String getHomepagedesc() {
        return homepagedesc;
    }

    public void setHomepagedesc(String homepagedesc) {
        this.homepagedesc = homepagedesc;
    }

    public String getAppfunctionid() {
        return appfunctionid;
    }

    public void setAppfunctionid(String appfunctionid) {
        this.appfunctionid = appfunctionid;
    }

    public List<Sm_Homepage_Plt> getSmHomepagePltList() {
        return smHomepagePltList;
    }

    public void setSmHomepagePltList(List<Sm_Homepage_Plt> smHomepagePltList) {
        this.smHomepagePltList = smHomepagePltList;
    }

    public String getPltposgroup() {
        return pltposgroup;
    }

    public void setPltposgroup(String pltposgroup) {
        this.pltposgroup = pltposgroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (homepageid != null ? homepageid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sm_Homepage)) {
            return false;
        }
        Sm_Homepage other = (Sm_Homepage) object;
        if ((this.homepageid == null && other.homepageid != null) || (this.homepageid != null && !this.homepageid.equals(other.homepageid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.Sm_Homepage[homepageid=" + homepageid + "]";
    }

}
