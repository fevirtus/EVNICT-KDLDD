/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "S_LST_RPT_TYPE_DATA",uniqueConstraints={})
public class S_LST_RPT_TYPE_DATA implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected S_LST_RPT_TYPE_DATAPK id;   
    @Column(name = "RPTDATADESC")
    private String rptdatadesc;  
    @Column(name = "RPTDATAORD")
    private int rptdataord;
    @Column(name = "ACTIVE")
    private int active;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
    public String getRptdatadesc() {
        return rptdatadesc;
    }

    public void setRptdatadesc(String rptdatadesc) {
        this.rptdatadesc = rptdatadesc;
    }

    public int getRptdataord() {
        return rptdataord;
    }

    public void setRptdataord(int rptdataord) {
        this.rptdataord = rptdataord;
    }

    public S_LST_RPT_TYPE_DATAPK getId() {
        return id;
    }

    public void setId(S_LST_RPT_TYPE_DATAPK id) {
        this.id = id;
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
        if (!(object instanceof S_LST_RPT_TYPE_DATA)) {
            return false;
        }
        S_LST_RPT_TYPE_DATA other = (S_LST_RPT_TYPE_DATA) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.eam.asset.A_Route[id=" + id + "]";
    }

}
