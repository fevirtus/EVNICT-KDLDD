/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author thaodd
 */
@Entity
@Table(name = "S_COMMON",uniqueConstraints={})
public class S_Common implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "KEYID")
    private String keyid;
    @Column(name = "KEYDESC")
    private String keydesc;
    @Column(name = "INTVAL")
    private Integer intval;
    @Column(name = "STRVAL")
    private String strval;

    public Integer getIntval() {
        return intval;
    }

    public void setIntval(Integer intval) {
        this.intval = intval;
    }

    public String getKeydesc() {
        return keydesc;
    }

    public void setKeydesc(String keydesc) {
        this.keydesc = keydesc;
    }

    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    public String getStrval() {
        return strval;
    }

    public void setStrval(String strval) {
        this.strval = strval;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (keyid != null ? keyid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Common)) {
            return false;
        }
        S_Common other = (S_Common) object;
        if ((this.keyid == null && other.keyid != null) || (this.keyid != null && !this.keyid.equals(other.keyid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.S_Common[id=" + keyid + "]";
    }

}
