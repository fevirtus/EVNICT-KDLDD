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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ChuotCong
 */
@Entity
@Table(name = "S_LIST_FIELD_ALL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SListFieldAll.findAll", query = "SELECT s FROM SListFieldAll s"),
    @NamedQuery(name = "SListFieldAll.findByFieldid", query = "SELECT s FROM SListFieldAll s WHERE s.fieldid = :fieldid"),
    @NamedQuery(name = "SListFieldAll.findByListid", query = "SELECT s FROM SListFieldAll s WHERE s.listid = :listid"),
    @NamedQuery(name = "SListFieldAll.findByFieldcode", query = "SELECT s FROM SListFieldAll s WHERE s.fieldcode = :fieldcode"),
    @NamedQuery(name = "SListFieldAll.findByFielddesc", query = "SELECT s FROM SListFieldAll s WHERE s.fielddesc = :fielddesc"),
    @NamedQuery(name = "SListFieldAll.findByFieldord", query = "SELECT s FROM SListFieldAll s WHERE s.fieldord = :fieldord"),
    @NamedQuery(name = "SListFieldAll.findByFieldexp", query = "SELECT s FROM SListFieldAll s WHERE s.fieldexp = :fieldexp")})
public class SListFieldAll implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FIELDID")
    private String fieldid;
    @Column(name = "LISTID")
    private String listid;
    @Column(name = "FIELDCODE")
    private String fieldcode;
    @Column(name = "FIELDDESC")
    private String fielddesc;
    @Column(name = "FIELDORD")
    private Integer fieldord;
    @Column(name = "FIELDEXP")
    private String fieldexp;
    @Column(name = "NOTE")
    private String note;
    @Transient
    private Boolean bChecked;
    @Transient
    private Integer countRecord;

    public SListFieldAll() {
    }

    public Integer getCountRecord() {
        return countRecord;
    }

    public void setCountRecord(Integer countRecord) {
        this.countRecord = countRecord;
    }

    
    
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    
    
    public SListFieldAll(String fieldid) {
        this.fieldid = fieldid;
    }

    public SListFieldAll(String fieldid, String fielddesc) {
        this.fieldid = fieldid;
        this.fielddesc = fielddesc;
    }

    public Boolean getbChecked() {
        return bChecked;
    }

    public void setbChecked(Boolean bChecked) {
        this.bChecked = bChecked;
    }

    public String getFieldid() {
        return fieldid;
    }

    public void setFieldid(String fieldid) {
        this.fieldid = fieldid;
    }

    public String getListid() {
        return listid;
    }

    public void setListid(String listid) {
        this.listid = listid;
    }

    public String getFieldcode() {
        return fieldcode;
    }

    public void setFieldcode(String fieldcode) {
        this.fieldcode = fieldcode;
    }

    public String getFielddesc() {
        return fielddesc;
    }

    public void setFielddesc(String fielddesc) {
        this.fielddesc = fielddesc;
    }

    public Integer getFieldord() {
        return fieldord;
    }

    public void setFieldord(Integer fieldord) {
        this.fieldord = fieldord;
    }

    public String getFieldexp() {
        return fieldexp;
    }

    public void setFieldexp(String fieldexp) {
        this.fieldexp = fieldexp;
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
        if (!(object instanceof SListFieldAll)) {
            return false;
        }
        SListFieldAll other = (SListFieldAll) object;
        if ((this.fieldid == null && other.fieldid != null) || (this.fieldid != null && !this.fieldid.equals(other.fieldid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.SListFieldAll[ fieldid=" + fieldid + " ]";
    }

}
