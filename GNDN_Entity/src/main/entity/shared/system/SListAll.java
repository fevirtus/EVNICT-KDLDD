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
@Table(name = "S_LIST_ALL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SListAll.findAll", query = "SELECT s FROM SListAll s"),
    @NamedQuery(name = "SListAll.findByListid", query = "SELECT s FROM SListAll s WHERE s.listid = :listid"),
    @NamedQuery(name = "SListAll.findByGrouplistid", query = "SELECT s FROM SListAll s WHERE s.grouplistid = :grouplistid"),
    @NamedQuery(name = "SListAll.findByListdesc", query = "SELECT s FROM SListAll s WHERE s.listdesc = :listdesc"),
    @NamedQuery(name = "SListAll.findByListcode", query = "SELECT s FROM SListAll s WHERE s.listcode = :listcode"),
    @NamedQuery(name = "SListAll.findByListord", query = "SELECT s FROM SListAll s WHERE s.listord = :listord")})
public class SListAll implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LISTID")
    private String listid;

    @Column(name = "LISTDESC")
    private String listdesc;
    @Column(name = "LISTCODE")
    private String listcode;
    @Column(name = "LISTORD")
    private Integer listord;
    @JoinColumn(name = "GROUPLISTID", referencedColumnName = "GROUPLISTID")
    @ManyToOne
    private SListGroupAll grouplistid;

    public SListAll() {
    }

    public SListAll(String listid) {
        this.listid = listid;
    }

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
    }

    public SListGroupAll getGrouplistid() {
        return grouplistid;
    }

    public void setGrouplistid(SListGroupAll grouplistid) {
        this.grouplistid = grouplistid;
    }

    public String getListdesc() {
        return listdesc;
    }

    public void setListdesc(String listdesc) {
        this.listdesc = listdesc;
    }

    public String getListcode() {
        return listcode;
    }

    public void setListcode(String listcode) {
        this.listcode = listcode;
    }

    public Integer getListord() {
        return listord;
    }

    public void setListord(Integer listord) {
        this.listord = listord;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listid != null ? listid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SListAll)) {
            return false;
        }
        SListAll other = (SListAll) object;
        if ((this.listid == null && other.listid != null) || (this.listid != null && !this.listid.equals(other.listid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.SListAll[ listid=" + listid + " ]";
    }

}
