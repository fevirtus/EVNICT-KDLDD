/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.system;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author DuongNM
 */
@Entity
@Table(name = "s_list_field_all")
public class S_list_field_all implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FIELDID")
    private String fieldid;
    @Column(name = "LISTID")
    private String listid;
    @Column(name = "FIELDCODE")
    private String fieldcode;
    @Column(name = "FIELDORD")
    private Integer fieldord;
    @Column(name = "FIELDDESC")
    private String fielddesc;
    @Column(name = "FIELDEXP")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fieldexp;
    @Column(name = "NOTE")
    private String note;

    public S_list_field_all() {
    }

    public S_list_field_all(String fieldid) {
        this.fieldid = fieldid;
    }

    public String getLstid() {
        return fieldid;
    }

    public void setLstid(String fieldid) {
        this.fieldid = fieldid;
    }

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
    }

    public String getFieldid() {
        return fieldid;
    }

    public void setFieldid(String fieldid) {
        this.fieldid = fieldid;
    }

    public String getFieldcode() {
        return fieldcode;
    }

    public void setFieldcode(String fieldcode) {
        this.fieldcode = fieldcode;
    }

    public Integer getFieldord() {
        return fieldord;
    }

    public void setFieldord(Integer fieldord) {
        this.fieldord = fieldord;
    }

    public String getFielddesc() {
        return fielddesc;
    }

    public void setFielddesc(String fielddesc) {
        this.fielddesc = fielddesc;
    }

    public Date getFieldexp() {
        return fieldexp;
    }

    public void setFieldexp(Date fieldexp) {
        this.fieldexp = fieldexp;
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
        hash += (fieldid != null ? fieldid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_list_field_all)) {
            return false;
        }
        S_list_field_all other = (S_list_field_all) object;
        if ((this.fieldid == null && other.fieldid != null) || (this.fieldid != null && !this.fieldid.equals(other.fieldid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.S_list_field_all[ fieldid=" + fieldid + " ]";
    }

}
