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
@Table(name = "S_SITE", uniqueConstraints = {})
public class S_Site implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SITEID")
    private String siteid;
    @Column(name = "ORGID")
    private String orgid;
    @Basic(optional = false)
    @Column(name = "SITEDESC")
    private String sitedesc;
    @Basic(optional = false)
    @Column(name = "ACTIVE")
    private boolean active;
    @Column(name = "SITEID_PARENT")
    private String siteidParent;
    @Column(name = "SITEORD")
    private Integer siteord;
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ORGID", unique = false, insertable = false, updatable = false, nullable = true)
    private S_Organization sOrganization;
    @Transient
    private Boolean bChecked;

    @JoinColumn(name = "SITEID_PARENT", referencedColumnName = "SITEID", unique = false, insertable = false, updatable = false, nullable = true)
    @ManyToOne(optional = false)
    private S_Site sSiteidParent;

    @Transient
    private Integer plevel;
    public Integer getPlevel() {
        return plevel;
    }

    //Khai báo các trường liên quan phân quyền
    @Transient
    private Boolean grant;
    @Transient
    private Boolean readonly;

    /**
     * Hàm ghép giữa grant và readonly để kiểm tra có được quyền cập nhật dữ liệu trên site này hay không
     * @return
     */
    public boolean getEditable()
    {
       if (grant==null || !grant)
           return false;
       if (readonly!=null && readonly)
           return false;
       return true;
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
    
    public void setPlevel(Integer ilevel) {
        this.plevel = ilevel;
    }
    public int getPaddingLeft(){
        int padding_left =0;
        if(plevel!=null && plevel >= 0){
            return 10 + plevel*20;
        }
        return padding_left < 0 ? 0: padding_left;
    }

    public S_Site() {
    }

    public S_Site(String siteid) {
        this.siteid = siteid;
    }

    public S_Site(String siteid, String sitedesc, boolean active) {
        this.siteid = siteid;
        this.sitedesc = sitedesc;
        this.active = active;
    }

    public S_Site getsSiteidParent() {
        return sSiteidParent;
    }

    public void setsSiteidParent(S_Site sSiteidParent) {
        this.sSiteidParent = sSiteidParent;
    }

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public String getSitedesc() {
        return sitedesc;
    }

    public void setSitedesc(String sitedesc) {
        this.sitedesc = sitedesc;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getSiteidParent() {
        return siteidParent;
    }

    public void setSiteidParent(String siteidParent) {
        this.siteidParent = siteidParent;
    }

    public Integer getSiteord() {
        return siteord;
    }

    public void setSiteord(Integer siteord) {
        this.siteord = siteord;
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

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (siteid != null ? siteid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Site)) {
            return false;
        }
        S_Site other = (S_Site) object;
        if ((this.siteid == null && other.siteid != null) || (this.siteid != null && !this.siteid.equals(other.siteid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return sitedesc;
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
