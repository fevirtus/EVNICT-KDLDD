/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ChuotCong
 */
@Entity
@Table(name = "S_LIST_GROUP_ALL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SListGroupAll.findAll", query = "SELECT s FROM SListGroupAll s"),
    @NamedQuery(name = "SListGroupAll.findByGrouplistid", query = "SELECT s FROM SListGroupAll s WHERE s.grouplistid = :grouplistid"),
    @NamedQuery(name = "SListGroupAll.findByGrouplistdesc", query = "SELECT s FROM SListGroupAll s WHERE s.grouplistdesc = :grouplistdesc"),
    @NamedQuery(name = "SListGroupAll.findByGrouplistParent", query = "SELECT s FROM SListGroupAll s WHERE s.grouplistParent = :grouplistParent"),
    @NamedQuery(name = "SListGroupAll.findByGrouplistord", query = "SELECT s FROM SListGroupAll s WHERE s.grouplistord = :grouplistord")})
public class SListGroupAll implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GROUPLISTID")
    private String grouplistid;
    @Column(name = "GROUPLISTDESC")
    private String grouplistdesc;
    @Column(name = "GROUPLISTORD")
    private Integer grouplistord;
   
    
    @JoinColumn(name = "GROUPLIST_PARENT", referencedColumnName = "GROUPLISTID")
    @ManyToOne
    private SListGroupAll grouplistParent;

    public SListGroupAll() {
    }

    public SListGroupAll(String grouplistid) {
        this.grouplistid = grouplistid;
    }

    public SListGroupAll(String grouplistid, String grouplistdesc) {
        this.grouplistid = grouplistid;
        this.grouplistdesc = grouplistdesc;
    }

   
    
    public String getGrouplistid() {
        return grouplistid;
    }

    public void setGrouplistid(String grouplistid) {
        this.grouplistid = grouplistid;
    }

    public String getGrouplistdesc() {
        return grouplistdesc;
    }

    public void setGrouplistdesc(String grouplistdesc) {
        this.grouplistdesc = grouplistdesc;
    }

    public SListGroupAll getGrouplistParent() {
        return grouplistParent;
    }

    public void setGrouplistParent(SListGroupAll grouplistParent) {
        this.grouplistParent = grouplistParent;
    }



    public Integer getGrouplistord() {
        return grouplistord;
    }

    public void setGrouplistord(Integer grouplistord) {
        this.grouplistord = grouplistord;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grouplistid != null ? grouplistid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SListGroupAll)) {
            return false;
        }
        SListGroupAll other = (SListGroupAll) object;
        if ((this.grouplistid == null && other.grouplistid != null) || (this.grouplistid != null && !this.grouplistid.equals(other.grouplistid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.SListGroupAll[ grouplistid=" + grouplistid + " ]";
    }
    
}
