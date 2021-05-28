/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.admin;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sir Minh
 */
@Entity
@Table(name = "S_ORGANIZATION_INIT")
@NamedQueries({
    @NamedQuery(name = "S_Organization_Init.findAll", query = "SELECT s FROM S_Organization_Init s"),
    @NamedQuery(name = "S_Organization_Init.findByOrgid", query = "SELECT s FROM S_Organization_Init s WHERE s.orgid = :orgid"),
    @NamedQuery(name = "S_Organization_Init.findByOrgdesc", query = "SELECT s FROM S_Organization_Init s WHERE s.orgdesc = :orgdesc"),
    @NamedQuery(name = "S_Organization_Init.findByOrgcode", query = "SELECT s FROM S_Organization_Init s WHERE s.orgcode = :orgcode"),
    @NamedQuery(name = "S_Organization_Init.findByActive", query = "SELECT s FROM S_Organization_Init s WHERE s.active = :active"),
    @NamedQuery(name = "S_Organization_Init.findByOrgdbid", query = "SELECT s FROM S_Organization_Init s WHERE s.orgdbid = :orgdbid"),
    @NamedQuery(name = "S_Organization_Init.findByOrgord", query = "SELECT s FROM S_Organization_Init s WHERE s.orgord = :orgord"),
    @NamedQuery(name = "S_Organization_Init.findByNote", query = "SELECT s FROM S_Organization_Init s WHERE s.note = :note")})
public class S_Organization_Init implements Serializable {
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
    @Column(name = "ACTIVE")
    private Boolean active;
    @Column(name = "ORGDBID")
    private String orgdbid;
    @Column(name = "ORGORD")
    private Integer orgord;
    @Column(name = "NOTE")
    private String note;

    public S_Organization_Init() {
    }

    public S_Organization_Init(String orgid) {
        this.orgid = orgid;
    }

    public S_Organization_Init(String orgid, String orgdesc) {
        this.orgid = orgid;
        this.orgdesc = orgdesc;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getOrgdbid() {
        return orgdbid;
    }

    public void setOrgdbid(String orgdbid) {
        this.orgdbid = orgdbid;
    }

    public Integer getOrgord() {
        return orgord;
    }

    public void setOrgord(Integer orgord) {
        this.orgord = orgord;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        if (!(object instanceof S_Organization_Init)) {
            return false;
        }
        S_Organization_Init other = (S_Organization_Init) object;
        if ((this.orgid == null && other.orgid != null) || (this.orgid != null && !this.orgid.equals(other.orgid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.admin.S_Organization_Init[orgid=" + orgid + "]";
    }

}
