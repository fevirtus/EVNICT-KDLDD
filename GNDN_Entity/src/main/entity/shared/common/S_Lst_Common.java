/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.common;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author thaodd
 */
@Entity
@Table(name = "S_LST_COMMON")
public class S_Lst_Common implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ROWID")
    private String rowid;
    @Basic(optional = false)
    @Column(name = "ROWGROUP")
    private String rowgroup;
    @Basic(optional = false)
    @Column(name = "ROWDESC")
    private String rowdesc;
    @Basic(optional = false)
    @Column(name = "ENABLE")
    private Boolean enable;
    @Column(name = "ROWORD")
    private Integer roword;

    public S_Lst_Common() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final S_Lst_Common other = (S_Lst_Common) obj;
        if ((this.rowid == null) ? (other.rowid != null) : !this.rowid.equals(other.rowid)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.rowid != null ? this.rowid.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "S_Lst_Common{" + "rowid=" + rowid + "rowdesc=" + rowdesc + '}';
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getRowdesc() {
        return rowdesc;
    }

    public void setRowdesc(String rowdesc) {
        this.rowdesc = rowdesc;
    }

    public String getRowgroup() {
        return rowgroup;
    }

    public void setRowgroup(String rowgroup) {
        this.rowgroup = rowgroup;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public Integer getRoword() {
        return roword;
    }

    public void setRoword(Integer roword) {
        this.roword = roword;
    }

    public S_Lst_Common(String rowid, String rowdesc) {
        this.rowid = rowid;
        this.rowdesc = rowdesc;
    }

    
}
