/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author thaodd
 */
@Embeddable
public class Sm_Homepage_PltPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "HOMEPAGEID")
    private String homepageid;
    @Basic(optional = false)
    @Column(name = "PLTID")
    private String pltid;
    @Basic(optional = false)
    @Column(name = "USERID")
    private String userid;

    public Sm_Homepage_PltPK() {
    }

    public Sm_Homepage_PltPK(String homepageid, String pltid, String userid) {
        this.homepageid = homepageid;
        this.pltid = pltid;
        this.userid = userid;
    }

    public String getHomepageid() {
        return homepageid;
    }

    public void setHomepageid(String homepageid) {
        this.homepageid = homepageid;
    }

    public String getPltid() {
        return pltid;
    }

    public void setPltid(String pltid) {
        this.pltid = pltid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (homepageid != null ? homepageid.hashCode() : 0);
        hash += (pltid != null ? pltid.hashCode() : 0);
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sm_Homepage_PltPK)) {
            return false;
        }
        Sm_Homepage_PltPK other = (Sm_Homepage_PltPK) object;
        if ((this.homepageid == null && other.homepageid != null) || (this.homepageid != null && !this.homepageid.equals(other.homepageid))) {
            return false;
        }
        if ((this.pltid == null && other.pltid != null) || (this.pltid != null && !this.pltid.equals(other.pltid))) {
            return false;
        }
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.Sm_Homepage_PltPK[homepageid=" + homepageid + ", pltid=" + pltid + ", userid=" + userid + "]";
    }

}
