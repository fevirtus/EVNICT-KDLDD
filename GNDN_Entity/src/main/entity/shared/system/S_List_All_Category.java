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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ChuotCong
 */
@Entity
@Table(name = "S_LIST_ALL_CATEGORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "S_List_All_Category.findAll", query = "SELECT s FROM S_List_All_Category s"),
    @NamedQuery(name = "S_List_All_Category.findByLstid", query = "SELECT s FROM S_List_All_Category s WHERE s.lstid = :lstid"),
    @NamedQuery(name = "S_List_All_Category.findByListid", query = "SELECT s FROM S_List_All_Category s WHERE s.listid = :listid"),
    @NamedQuery(name = "S_List_All_Category.findByCategoryid", query = "SELECT s FROM S_List_All_Category s WHERE s.categoryid = :categoryid")})
public class S_List_All_Category implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LSTID")
    private String lstid;
    @Column(name = "LISTID")
    private String listid;
    @Column(name = "CATEGORYID")
    private String categoryid;

    public S_List_All_Category() {
    }

    public S_List_All_Category(String lstid) {
        this.lstid = lstid;
    }

    public String getLstid() {
        return lstid;
    }

    public void setLstid(String lstid) {
        this.lstid = lstid;
    }

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lstid != null ? lstid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_List_All_Category)) {
            return false;
        }
        S_List_All_Category other = (S_List_All_Category) object;
        if ((this.lstid == null && other.lstid != null) || (this.lstid != null && !this.lstid.equals(other.lstid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.S_List_All_Category[ lstid=" + lstid + " ]";
    }
    
}
