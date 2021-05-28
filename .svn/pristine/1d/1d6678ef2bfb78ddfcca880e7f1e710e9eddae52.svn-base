/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author khiemvk
 */
@Embeddable
public class S_Uom_ExchangePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "FROMUOMID")
    private String fromuomid;
    @Basic(optional = false)
    @Column(name = "TOUOMID")
    private String touomid;

    public S_Uom_ExchangePK() {
    }

    public S_Uom_ExchangePK(String fromuomid, String touomid) {
        this.fromuomid = fromuomid;
        this.touomid = touomid;
    }

    public String getFromuomid() {
        return fromuomid;
    }

    public void setFromuomid(String fromuomid) {
        this.fromuomid = fromuomid;
    }

    public String getTouomid() {
        return touomid;
    }

    public void setTouomid(String touomid) {
        this.touomid = touomid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fromuomid != null ? fromuomid.hashCode() : 0);
        hash += (touomid != null ? touomid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Uom_ExchangePK)) {
            return false;
        }
        S_Uom_ExchangePK other = (S_Uom_ExchangePK) object;
        if ((this.fromuomid == null && other.fromuomid != null) || (this.fromuomid != null && !this.fromuomid.equals(other.fromuomid))) {
            return false;
        }
        if ((this.touomid == null && other.touomid != null) || (this.touomid != null && !this.touomid.equals(other.touomid))) {
            return false;
        }
        return true;
    }

    public boolean equals(String object) {
        return this.toString().equals(object);
    }

    public String getIdStr()
    {
        return fromuomid + touomid;
    }

    @Override
    public String toString() {
        return "Shared.Entity.S_Uom_ExchangePK[fromuomid=" + fromuomid + ", touomid=" + touomid + "]";
    }

}
