/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.attach;

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
@Table(name = "AF_LST_ATTACHTYPE")
@NamedQueries({
    @NamedQuery(name = "Af_Lst_Attachtype.findAll", query = "SELECT a FROM Af_Lst_Attachtype a"),
    @NamedQuery(name = "Af_Lst_Attachtype.findByAttype", query = "SELECT a FROM Af_Lst_Attachtype a WHERE a.attype = :attype"),
    @NamedQuery(name = "Af_Lst_Attachtype.findByAttdesc", query = "SELECT a FROM Af_Lst_Attachtype a WHERE a.attdesc = :attdesc")})
public class Af_Lst_Attachtype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ATTYPE")
    private String attype;
    @Column(name = "ATTDESC")
    private String attdesc;

    public Af_Lst_Attachtype() {
    }

    public Af_Lst_Attachtype(String attype) {
        this.attype = attype;
    }

    public String getAttype() {
        return attype;
    }

    public void setAttype(String attype) {
        this.attype = attype;
    }

    public String getAttdesc() {
        return attdesc;
    }

    public void setAttdesc(String attdesc) {
        this.attdesc = attdesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attype != null ? attype.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Af_Lst_Attachtype)) {
            return false;
        }
        Af_Lst_Attachtype other = (Af_Lst_Attachtype) object;
        if ((this.attype == null && other.attype != null) || (this.attype != null && !this.attype.equals(other.attype))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.attach.Af_Lst_Attachtype[attype=" + attype + "]";
    }

}
