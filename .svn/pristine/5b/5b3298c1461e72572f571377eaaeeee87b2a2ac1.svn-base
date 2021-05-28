/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "S_DEPT_TYPE")
@NamedQueries({
    @NamedQuery(name = "S_Dept_Type.findAll", query = "SELECT s FROM S_Dept_Type s")})
public class S_Dept_Type implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DEPTTYPEID")
    private String depttypeid;
    @Basic(optional = false)
    @Column(name = "DEPTTYPEDESC")
    private String depttypedesc;
    @Column(name = "DEPTTYPEORD")
    private Integer depttypeord;

    public S_Dept_Type() {
    }

    public S_Dept_Type(String depttypeid) {
        this.depttypeid = depttypeid;
    }

    public S_Dept_Type(String depttypeid, String depttypedesc) {
        this.depttypeid = depttypeid;
        this.depttypedesc = depttypedesc;
    }

    public String getDepttypedesc() {
        return depttypedesc;
    }

    public void setDepttypedesc(String depttypedesc) {
        this.depttypedesc = depttypedesc;
    }

    public String getDepttypeid() {
        return depttypeid;
    }

    public void setDepttypeid(String depttypeid) {
        this.depttypeid = depttypeid;
    }

    public Integer getDepttypeord() {
        return depttypeord;
    }

    public void setDepttypeord(Integer depttypeord) {
        this.depttypeord = depttypeord;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depttypeid != null ? depttypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Company_Type)) {
            return false;
        }
        S_Dept_Type other = (S_Dept_Type) object;
        if ((this.depttypeid == null && other.depttypeid != null) || (this.depttypeid != null && !this.depttypeid.equals(other.depttypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.S_Company_Type[comptypeid=" + depttypeid + "]";
    }

}
