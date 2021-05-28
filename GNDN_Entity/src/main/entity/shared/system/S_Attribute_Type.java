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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author LANTRANHOANG
 */
@Entity
@Table(name = "S_ATTRIBUTE_TYPE")
@NamedQueries({
    @NamedQuery(name = "S_Attribute_Type.findAll", query = "SELECT s FROM S_Attribute_Type s"),
    @NamedQuery(name = "S_Attribute_Type.findByAttypeid", query = "SELECT s FROM S_Attribute_Type s WHERE s.attypeid = :attypeid"),
    @NamedQuery(name = "S_Attribute_Type.findByAttdesc", query = "SELECT s FROM S_Attribute_Type s WHERE s.attdesc = :attdesc"),
    @NamedQuery(name = "S_Attribute_Type.findByAttrord", query = "SELECT s FROM S_Attribute_Type s WHERE s.attrord = :attrord"),
    @NamedQuery(name = "S_Attribute_Type.findByEnable", query = "SELECT s FROM S_Attribute_Type s WHERE s.enable = :enable"),
    @NamedQuery(name = "S_Attribute_Type.findByNote", query = "SELECT s FROM S_Attribute_Type s WHERE s.note = :note")})
public class S_Attribute_Type implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ATTYPEID")
    private String attypeid;
    @Column(name = "ATTDESC")
    private String attdesc;
    @Column(name = "ATTRORD")
    private Integer attrord;
    @Column(name = "ENABLE")
    private Boolean enable;
    @Column(name = "NOTE")
    private String note;

    public S_Attribute_Type() {
    }

    public S_Attribute_Type(String attypeid) {
        this.attypeid = attypeid;
    }

    public String getAttypeid() {
        return attypeid;
    }

    public void setAttypeid(String attypeid) {
        this.attypeid = attypeid;
    }

    public String getAttdesc() {
        return attdesc;
    }

    public void setAttdesc(String attdesc) {
        this.attdesc = attdesc;
    }

    public Integer getAttrord() {
        return attrord;
    }

    public void setAttrord(Integer attrord) {
        this.attrord = attrord;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
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
        hash += (attypeid != null ? attypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Attribute_Type)) {
            return false;
        }
        S_Attribute_Type other = (S_Attribute_Type) object;
        if ((this.attypeid == null && other.attypeid != null) || (this.attypeid != null && !this.attypeid.equals(other.attypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.S_Attribute_Type[attypeid=" + attypeid + "]";
    }

}
