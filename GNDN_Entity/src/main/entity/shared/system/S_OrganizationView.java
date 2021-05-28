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

/**
 *
 * @author khiemvk
 */
@Entity
public class S_OrganizationView implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ORGID")
    private String orgid;
    @Basic(optional = false)
    @Column(name = "ORGDESC")
    private String orgdesc;
    @Column(name = "ORGCODE")
    private String orgcode;
    @Basic(optional = false)
    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "ORGID_PARENT")
    private String orgidParent;
    @Column(name = "ORGORD")
    private String orgord;
    
    @Column(name = "COUNT_CHILD")
    private String count_child;
    
    @Column(name = "TYPEID")
    private String typeid;
    
    public S_OrganizationView() {
    }

    public S_OrganizationView(String orgid) {
        this.orgid = orgid;
    }

    public S_OrganizationView(String orgid, String orgdesc, boolean active) {
        this.orgid = orgid;
        this.orgdesc = orgdesc;
        this.active = active;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    
    
    public String getOrgord() {
        return orgord;
    }

    public void setOrgord(String orgord) {
        this.orgord = orgord;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getOrgdesc() {
        return orgdesc;
    }

    public void setOrgdesc(String orgdesc) {
        this.orgdesc = orgdesc;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getOrgidParent() {
        return orgidParent;
    }

    public void setOrgidParent(String orgidParent) {
        this.orgidParent = orgidParent;
    }    

    public String getCount_child() {
        return count_child;
    }

    public void setCount_child(String count_child) {
        this.count_child = count_child;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orgid != null ? orgid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_OrganizationView)) {
            return false;
        }
        S_OrganizationView other = (S_OrganizationView) object;
        if ((this.orgid == null && other.orgid != null) || (this.orgid != null && !this.orgid.equals(other.orgid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Shared.Entity.S_Organization[orgid=" + orgid + "]";
    }    
}
