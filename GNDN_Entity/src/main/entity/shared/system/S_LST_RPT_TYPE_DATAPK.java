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
 * @author khiemvk
 */
@Embeddable
public class S_LST_RPT_TYPE_DATAPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "RPTTYPEID")
    private String rpttypeid;
    @Column(name = "RPTDATAID")
    private String rptdataid;

    public String getRpttypeid() {
        return rpttypeid;
    }

    public void setRpttypeid(String rpttypeid) {
        this.rpttypeid = rpttypeid;
    }

    public String getRptdataid() {
        return rptdataid;
    }

    public void setRptdataid(String rptdataid) {
        this.rptdataid = rptdataid;
    }

    
    public S_LST_RPT_TYPE_DATAPK() {
    }

    public S_LST_RPT_TYPE_DATAPK(String rpttypeid, String rptdataid) {
        this.rpttypeid = rpttypeid;
        this.rptdataid = rptdataid;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rpttypeid != null ? rpttypeid.hashCode() : 0);
        hash += (rptdataid != null ? rptdataid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_LST_RPT_TYPE_DATAPK)) {
            return false;
        }
        S_LST_RPT_TYPE_DATAPK other = (S_LST_RPT_TYPE_DATAPK) object;
        if ((this.rpttypeid == null && other.rpttypeid != null) || (this.rpttypeid != null && !this.rpttypeid.equals(other.rpttypeid))) {
            return false;
        }
        if ((this.rptdataid == null && other.rptdataid != null) || (this.rptdataid != null && !this.rptdataid.equals(other.rptdataid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Shared.S_Attribute_Group_AssobjPK[rpttypeid=" + rpttypeid + ", rptdataid=" + rptdataid + "]";
    }

}
