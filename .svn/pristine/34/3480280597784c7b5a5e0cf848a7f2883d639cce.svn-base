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
public class Bb_Message_RcvPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "BBMID")
    private String bbmid;
    @Basic(optional = false)
    @Column(name = "BBRCVTYPEID")
    private String bbrcvtypeid;
    @Basic(optional = false)
    @Column(name = "RCVID")
    private String rcvid;

    public Bb_Message_RcvPK() {
    }

    public Bb_Message_RcvPK(String bbmid, String bbrcvtypeid, String rcvid) {
        this.bbmid = bbmid;
        this.bbrcvtypeid = bbrcvtypeid;
        this.rcvid = rcvid;
    }

    public String getBbmid() {
        return bbmid;
    }

    public void setBbmid(String bbmid) {
        this.bbmid = bbmid;
    }

    public String getBbrcvtypeid() {
        return bbrcvtypeid;
    }

    public void setBbrcvtypeid(String bbrcvtypeid) {
        this.bbrcvtypeid = bbrcvtypeid;
    }

    public String getRcvid() {
        return rcvid;
    }

    public void setRcvid(String rcvid) {
        this.rcvid = rcvid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bbmid != null ? bbmid.hashCode() : 0);
        hash += (bbrcvtypeid != null ? bbrcvtypeid.hashCode() : 0);
        hash += (rcvid != null ? rcvid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bb_Message_RcvPK)) {
            return false;
        }
        Bb_Message_RcvPK other = (Bb_Message_RcvPK) object;
        if ((this.bbmid == null && other.bbmid != null) || (this.bbmid != null && !this.bbmid.equals(other.bbmid))) {
            return false;
        }
        if ((this.bbrcvtypeid == null && other.bbrcvtypeid != null) || (this.bbrcvtypeid != null && !this.bbrcvtypeid.equals(other.bbrcvtypeid))) {
            return false;
        }
        if ((this.rcvid == null && other.rcvid != null) || (this.rcvid != null && !this.rcvid.equals(other.rcvid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.Bb_Message_RcvPK[bbmid=" + bbmid + ", bbrcvtypeid=" + bbrcvtypeid + ", rcvid=" + rcvid + "]";
    }

}
