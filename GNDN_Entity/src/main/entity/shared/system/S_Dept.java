/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author khiemvk
 */
@Entity
@Table(name = "S_DEPT",uniqueConstraints={})
public class S_Dept implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DEPTID")
    private String deptid;
    @Basic(optional = false)
    @Column(name = "DEPTDESC")
    private String deptdesc;
    @Column(name = "DEPTORD")
    private Integer deptord;
    @Column(name = "DEPTID_PARENT")
    private String deptidParent;
    @Column(name = "ORGID")
    private String orgid;
    @Column(name = "TYPEIDLIST")
    private String typeidlist;
    @Column(name = "DEPTCODE")
    private String deptcode;
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ORGID", unique = false, insertable = false, updatable = false, nullable = true)
    private S_Organization sOrganization;

    @JoinColumn(name = "DEPTID_PARENT", referencedColumnName = "DEPTID", unique = false, insertable = false, updatable = false, nullable = true)
    @ManyToOne(optional = false)
    private S_Dept sDeptidParent;

    @Transient
    private Boolean bChecked;

    @Transient
    private Integer plevel;
    
    @Transient
    private Boolean grant;
    @Transient
    private Boolean readonly;
    @Transient
    private Boolean updateProblemid;
    @Transient
    private Boolean updateAssetid;
    
    public Integer getPlevel() {
        return plevel;
    }

    public void setPlevel(Integer ilevel) {
        this.plevel = ilevel;
    }
    public int getPaddingLeft(){
        int padding_left =0;
        if(plevel!=null){
            return (plevel-1)*30;
        }
        return padding_left < 0 ? 0: padding_left;
    }

    public S_Dept() {
    }

    public S_Dept(String deptid) {
        this.deptid = deptid;
    }

    public S_Dept getsDeptidParent() {
        return sDeptidParent;
    }

    public void setsDeptidParent(S_Dept sDeptidParent) {
        this.sDeptidParent = sDeptidParent;
    }

    public S_Dept(String deptid, String deptdesc) {
        this.deptid = deptid;
        this.deptdesc = deptdesc;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getDeptdesc() {
        return deptdesc;
    }

    public void setDeptdesc(String deptdesc) {
        this.deptdesc = deptdesc;
    }

    public Integer getDeptord() {
        return deptord;
    }

    public void setDeptord(Integer deptord) {
        this.deptord = deptord;
    }

    public String getDeptidParent() {
        return deptidParent;
    }

    public void setDeptidParent(String deptidParent) {
        this.deptidParent = deptidParent;
    }

    public S_Organization getSOrganization() {
        return sOrganization;
    }

    public void setSOrganization(S_Organization sOrganization) {
        this.sOrganization = sOrganization;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public S_Organization getsOrganization() {
        return sOrganization;
    }

    public void setsOrganization(S_Organization sOrganization) {
        this.sOrganization = sOrganization;
    }

    public String getTypeidlist() {
        return typeidlist;
    }

    public void setTypeidlist(String typeidlist) {
        this.typeidlist = typeidlist;
    }

    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    public Boolean getGrant() {
        return grant;
    }

    public void setGrant(Boolean grant) {
        this.grant = grant;
    }

    public Boolean getReadonly() {
        return readonly;
    }

    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deptid != null ? deptid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Dept)) {
            return false;
        }
        S_Dept other = (S_Dept) object;
        if ((this.deptid == null && other.deptid != null) || (this.deptid != null && !this.deptid.equals(other.deptid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Shared.Entity.S_Dept[deptid=" + deptid + "]";
    }

    public Boolean getbChecked() {
        if (bChecked != null) {
            return bChecked;
        }
        return false;
    }

    public void setbChecked(Boolean bChecked) {
        this.bChecked = bChecked;
    }

    public Boolean getUpdateAssetid() {
        return updateAssetid;
    }

    public void setUpdateAssetid(Boolean updateAssetid) {
        this.updateAssetid = updateAssetid;
    }

    public Boolean getUpdateProblemid() {
        return updateProblemid;
    }

    public void setUpdateProblemid(Boolean updateProblemid) {
        this.updateProblemid = updateProblemid;
    }

}
