/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "S_LST_RPT_TYPE",uniqueConstraints={})
public class S_LST_RPT_TYPE implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RPTTYPEID")
    private String rpttypeid;    
    @Basic(optional = false)
    @Column(name = "RPTTYPEDESC")
    private String rpttypedesc;  
    @Column(name = "RPTTYPEORD")
    private int rpttypeord;
    @Column(name = "ACTIVE")
    private int active;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    
    
    public String getRpttypeid() {
        return rpttypeid;
    }

    public void setRpttypeid(String rpttypeid) {
        this.rpttypeid = rpttypeid;
    }

    public String getRpttypedesc() {
        return rpttypedesc;
    }

    public void setRpttypedesc(String rpttypedesc) {
        this.rpttypedesc = rpttypedesc;
    }

    public int getRpttypeord() {
        return rpttypeord;
    }

    public void setRpttypeord(int rpttypeord) {
        this.rpttypeord = rpttypeord;
    }
    

    
    

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rpttypeid != null ? rpttypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_LST_RPT_TYPE)) {
            return false;
        }
        S_LST_RPT_TYPE other = (S_LST_RPT_TYPE) object;
        if ((this.rpttypeid == null && other.rpttypeid != null) || (this.rpttypeid != null && !this.rpttypeid.equals(other.rpttypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.eam.asset.A_Route[id=" + rpttypeid + "]";
    }

}
