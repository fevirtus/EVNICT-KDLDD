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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author LANTRANHOANG
 */
@Entity
@Table(name = "S_COMPANY_CONTACT")
@NamedQueries({
    @NamedQuery(name = "S_Company_Contact.findAll", query = "SELECT s FROM S_Company_Contact s")})
public class S_Company_Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CONTACTID")
    private String contactid;
    @Basic(optional = false)
    @Column(name = "CONTACTDESC")
    private String contactdesc;
    @Column(name = "COMPID")
    private String compid;
    @Column(name = "POS")
    private String pos;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "NOTE")
    private String note;
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "COMPID", unique = false, insertable = false, updatable = false, nullable = true)
    private S_Company sCompany;

    public S_Company_Contact() {
    }

    public S_Company_Contact(String contactid) {
        this.contactid = contactid;
    }

    public S_Company_Contact(String contactid, String contactdesc) {
        this.contactid = contactid;
        this.contactdesc = contactdesc;
    }

    public S_Company getsCompany() {
        return sCompany;
    }

    public void setsCompany(S_Company sCompany) {
        this.sCompany = sCompany;
    }

    public String getCompid() {
        return compid;
    }

    public void setCompid(String compid) {
        this.compid = compid;
    }

    public String getContactid() {
        return contactid;
    }

    public void setContactid(String contactid) {
        this.contactid = contactid;
    }

    public String getContactdesc() {
        return contactdesc;
    }

    public void setContactdesc(String contactdesc) {
        this.contactdesc = contactdesc;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactid != null ? contactid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Company_Contact)) {
            return false;
        }
        S_Company_Contact other = (S_Company_Contact) object;
        if ((this.contactid == null && other.contactid != null) || (this.contactid != null && !this.contactid.equals(other.contactid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.S_Company_Contact[contactid=" + contactid + "]";
    }

}
