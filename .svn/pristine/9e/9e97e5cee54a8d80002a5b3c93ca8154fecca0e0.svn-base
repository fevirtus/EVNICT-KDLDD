package main.entity.shared.attach;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author khiemvk
 */
@Entity
@Table(name = "AF_DOWNCONTROL",uniqueConstraints={})
public class Af_Downcontrol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PRFIXFILEID")
    private String prfixfileid;
    @Basic(optional = false)
    @Column(name = "TABLENAME")
    private String tablename;
    @Column(name = "DIR")
    private String dir;
    @Column(name = "NOTE")
    private String note;

    public Af_Downcontrol() {
    }

    public Af_Downcontrol(String prfixfileid) {
        this.prfixfileid = prfixfileid;
    }

    public Af_Downcontrol(String prfixfileid, String tablename) {
        this.prfixfileid = prfixfileid;
        this.tablename = tablename;
    }

    public String getPrfixfileid() {
        return prfixfileid;
    }

    public void setPrfixfileid(String prfixfileid) {
        this.prfixfileid = prfixfileid;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prfixfileid != null ? prfixfileid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Af_Downcontrol)) {
            return false;
        }
        Af_Downcontrol other = (Af_Downcontrol) object;
        if ((this.prfixfileid == null && other.prfixfileid != null) || (this.prfixfileid != null && !this.prfixfileid.equals(other.prfixfileid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Shared.attach.Af_Downcontrol[prfixfileid=" + prfixfileid + "]";
    }

}
