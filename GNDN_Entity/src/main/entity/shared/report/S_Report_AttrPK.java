package main.entity.shared.report;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author HANV
 */
@Embeddable
public class S_Report_AttrPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "RPTID")
    private String rptid;
    @Basic(optional = false)
    @Column(name = "ATTRGROUPID")
    private String attrgroupid;

    public S_Report_AttrPK() {
    }

    public S_Report_AttrPK(String rptid, String attrgroupid) {
        this.rptid = rptid;
        this.attrgroupid = attrgroupid;
    }

    public String getRptid() {
        return rptid;
    }

    public void setRptid(String rptid) {
        this.rptid = rptid;
    }

    

    public String getAttrgroupid() {
        return attrgroupid;
    }

    public void setAttrgroupid(String attrgroupid) {
        this.attrgroupid = attrgroupid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rptid != null ? rptid.hashCode() : 0);
        hash += (attrgroupid != null ? attrgroupid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Report_AttrPK)) {
            return false;
        }
        S_Report_AttrPK other = (S_Report_AttrPK) object;
        if ((this.rptid == null && other.rptid != null) || (this.rptid != null && !this.rptid.equals(other.rptid))) {
            return false;
        }
        if ((this.attrgroupid == null && other.attrgroupid != null) || (this.attrgroupid != null && !this.attrgroupid.equals(other.attrgroupid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.EAM.S_Report_AttrPK[rptid=" + rptid + ", attrgroupid=" + attrgroupid + "]";
    }

}
