/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.admin;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "Q_USER_GROUP")
@NamedQueries({
    @NamedQuery(name = "Q_User_Group.findAll", query = "SELECT s FROM Q_User_Group s")})
public class Q_User_Group implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GROUPUSERID")
    private String groupuserid;
    @Basic(optional = false)
    @Column(name = "GROUPUSERDESC")
    private String groupuserdesc;
    @Column(name = "GROUPUSERORD")
    private Integer groupuserord;

    public Q_User_Group() {
    }    

    public String getGroupuserid() {
        return groupuserid;
    }

    public void setGroupuserid(String groupuserid) {
        this.groupuserid = groupuserid;
    }

    public String getGroupuserdesc() {
        return groupuserdesc;
    }

    public void setGroupuserdesc(String groupuserdesc) {
        this.groupuserdesc = groupuserdesc;
    }

    public Integer getGroupuserord() {
        return groupuserord;
    }

    public void setGroupuserord(Integer groupuserord) {
        this.groupuserord = groupuserord;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupuserid != null ? groupuserid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Q_User_Group)) {
            return false;
        }
        Q_User_Group other = (Q_User_Group) object;
        if ((this.groupuserid == null && other.groupuserid != null) || (this.groupuserid != null && !this.groupuserid.equals(other.groupuserid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.admin.Q_User_Group[comptypeid=" + groupuserid + "]";
    }

}
