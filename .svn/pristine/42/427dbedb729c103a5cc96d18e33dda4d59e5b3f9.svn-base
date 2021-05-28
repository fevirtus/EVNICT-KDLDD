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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author khiemvk
 */
@Entity
@Table(name = "S_ORGANIZATION", uniqueConstraints = {})
public class S_Assets implements Serializable {

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
    @Transient
    private Boolean bChecked;

    @JoinColumn(name = "ORGID_PARENT", referencedColumnName = "ORGID", unique = false, insertable = false, updatable = false, nullable = true)
    @ManyToOne(optional = false)
    private S_Assets sOrgidParent;

    @Transient
    private Integer plevel;

    @Column(name = "ORLEVEL")
    private Integer orlevel;

    //Khai báo các trường liên quan phân quyền
    @Transient
    private Boolean grant;
    @Transient
    private Boolean readonly;

    /**
     * Hàm ghép giữa grant và readonly để kiểm tra có được quyền cập nhật dữ
     * liệu trên site này hay không
     *
     * @return
     */
    public boolean getEditable() {
        if (grant == null || !grant) {
            return false;
        }
        if (readonly != null && readonly) {
            return false;
        }
        return true;
    }

    public Integer getOrlevel() {
        return orlevel;
    }

    public void setOrlevel(Integer orlevel) {
        this.orlevel = orlevel;
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

    public Integer getPlevel() {
        return plevel;
    }

    public void setPlevel(Integer ilevel) {
        this.plevel = ilevel;
    }

    public int getPaddingLeft() {
        int padding_left = 0;
        if (plevel != null) {
            return (plevel - 1) * 20;
        }
        return padding_left < 0 ? 0 : padding_left;
    }

    public S_Assets() {
    }

    public S_Assets(String orgid) {
        this.orgid = orgid;
    }

    public S_Assets(String orgid, String orgdesc, boolean active) {
        this.orgid = orgid;
        this.orgdesc = orgdesc;
        this.active = active;
    }

    public S_Assets getsOrgidParent() {
        return sOrgidParent;
    }

    public void setsOrgidParent(S_Assets sOrgidParent) {
        this.sOrgidParent = sOrgidParent;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orgid != null ? orgid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Assets)) {
            return false;
        }
        S_Assets other = (S_Assets) object;
        if ((this.orgid == null && other.orgid != null) || (this.orgid != null && !this.orgid.equals(other.orgid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Shared.Entity.S_Assets[orgid=" + orgid + "]";
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
}
