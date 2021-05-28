/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "S_COMPANY",uniqueConstraints={})
public class S_Company implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COMPID")
    private String compid;
    @Basic(optional = false)
    @Column(name = "COMPDESC")
    private String compdesc;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TEL")
    private String tel;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "COMPID_PARENT")
    private String compidParent;
    @Column(name = "ORGID")
    private String orgid;
    @Column(name = "TYPEIDLIST")
    private String typeidlist;
    @Column(name = "HOMEPAGE")
    private String homepage;
    @Column(name = "USER_CREATE_ID")
    private String userCreateId;
    @Column(name = "USER_CREATE_DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userCreateDtime;
    @Column(name = "USER_MODIFY_ID")
    private String userModifyId;
    @Column(name = "USER_MODIFY_DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userModifyDtime;
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ORGID", unique = false, insertable = false, updatable = false, nullable = true)
    private S_Organization sOrganization;

    @JoinColumn(name = "COMPID_PARENT", referencedColumnName = "COMPID", unique = false, insertable = false, updatable = false, nullable = true)
    @ManyToOne(optional = false)
    private S_Company sCompidParent;

    @Transient
    private Boolean bChecked;
    
    @Transient
    private Integer plevel;
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

    
    
    
    public S_Company() {
    }

    public S_Company(String compid) {
        this.compid = compid;
    }

    public S_Company getsCompidParent() {
        return sCompidParent;
    }

    public void setsCompidParent(S_Company sCompidParent) {
        this.sCompidParent = sCompidParent;
    }

    public S_Organization getsOrganization() {
        return sOrganization;
    }

    public void setsOrganization(S_Organization sOrganization) {
        this.sOrganization = sOrganization;
    }

    public S_Company(String compid, String compdesc) {
        this.compid = compid;
        this.compdesc = compdesc;
    }

    public String getCompid() {
        return compid;
    }

    public void setCompid(String compid) {
        this.compid = compid;
    }
    
    public String getCompdesc() {
        return compdesc;
    }

    public void setCompdesc(String compdesc) {
        this.compdesc = compdesc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCompidParent() {
        return compidParent;
    }

    public void setCompidParent(String compidParent) {
        this.compidParent = compidParent;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getTypeidlist() {
        return typeidlist;
    }

    public void setTypeidlist(String typeidlist) {
        this.typeidlist = typeidlist;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getUserCreateId() {
        return userCreateId;
    }

    public void setUserCreateId(String userCreateId) {
        this.userCreateId = userCreateId;
    }

    public Date getUserCreateDtime() {
        return userCreateDtime;
    }

    public void setUserCreateDtime(Date userCreateDtime) {
        this.userCreateDtime = userCreateDtime;
    }

    public String getUserModifyId() {
        return userModifyId;
    }

    public void setUserModifyId(String userModifyId) {
        this.userModifyId = userModifyId;
    }

    public Date getUserModifyDtime() {
        return userModifyDtime;
    }

    public void setUserModifyDtime(Date userModifyDtime) {
        this.userModifyDtime = userModifyDtime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compid != null ? compid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Company)) {
            return false;
        }
        S_Company other = (S_Company) object;
        if ((this.compid == null && other.compid != null) || (this.compid != null && !this.compid.equals(other.compid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.S_Company[compid=" + compid + "]";
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
