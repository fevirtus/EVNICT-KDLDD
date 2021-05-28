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
@Table(name = "S_ORGANIZATION",uniqueConstraints={})
public class S_Organization implements Serializable {
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
    
    @Column(name = "ISPM")
    private Boolean ispm;
    
    @Column(name = "ISOP")
    private Boolean isop;
    
    @Column(name = "ISLAB")
    private Boolean islab;
    
    @Column(name = "ISCD")
    private Boolean iscd;
    
    @Column(name = "ISIMP")
    private Boolean isimp;
    @Column(name = "ISEXP")
    private Boolean isexp;
  
    
    
    @Transient
    private Boolean bChecked;
    @Transient
    private Object exInfo;
    
    

    @JoinColumn(name = "ORGID_PARENT", referencedColumnName = "ORGID", unique = false, insertable = false, updatable = false, nullable = true)
    @ManyToOne(optional = false)
    private S_Organization sOrgidParent;

    @Transient
    private Integer plevel;

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

    public Boolean getIsimp() {
        return isimp;
    }

    public void setIsimp(Boolean isimp) {
        this.isimp = isimp;
    }

    public Boolean getIsexp() {
        return isexp;
    }

    public void setIsexp(Boolean isexp) {
        this.isexp = isexp;
    }

    
    public Boolean getIspm() {
        return ispm;
    }

    public void setIspm(Boolean ispm) {
        this.ispm = ispm;
    }

    public Boolean getIsop() {
        return isop;
    }

    public void setIsop(Boolean isop) {
        this.isop = isop;
    }

    public Boolean getIslab() {
        return islab;
    }

    public void setIslab(Boolean islab) {
        this.islab = islab;
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
    public int getPaddingLeft(){
        int padding_left =0;
        if(plevel!=null){
            return (plevel-1)*20;
        }
        return padding_left < 0 ? 0: padding_left;
    }
    
    public S_Organization() {
    }

    public S_Organization(String orgid) {
        this.orgid = orgid;
    }

    public S_Organization(String orgid, String orgdesc, boolean active) {
        this.orgid = orgid;
        this.orgdesc = orgdesc;
        this.active = active;
    }

    public S_Organization getsOrgidParent() {
        return sOrgidParent;
    }

    public void setsOrgidParent(S_Organization sOrgidParent) {
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

    public Object getExInfo() {
        return exInfo;
    }

    public void setExInfo(Object exInfo) {
        this.exInfo = exInfo;
    }

    public Boolean getIscd() {
        return iscd;
    }

    public void setIscd(Boolean iscd) {
        this.iscd = iscd;
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
        if (!(object instanceof S_Organization)) {
            return false;
        }
        S_Organization other = (S_Organization) object;
        if ((this.orgid == null && other.orgid != null) || (this.orgid != null && !this.orgid.equals(other.orgid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Shared.Entity.S_Organization[orgid=" + orgid + "]";
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
