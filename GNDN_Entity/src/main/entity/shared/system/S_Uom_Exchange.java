/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.system;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author khiemvk
 */
@Entity
@Table(name = "S_UOM_EXCHANGE", uniqueConstraints = {})
public class S_Uom_Exchange implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected S_Uom_ExchangePK id;
    @Basic(optional = false)
    @Column(name = "MULTIPCOEFF")
    private Double multipcoeff;
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    @JoinColumn(name = "TOUOMID",referencedColumnName = "UOMID", unique = false, insertable = false, updatable = false, nullable = true)
    private S_Uom sToUom;
    @JoinColumn(name = "FROMUOMID",referencedColumnName = "UOMID", unique = false, insertable = false, updatable = false, nullable = true)
    @ManyToOne(cascade = {}, fetch = FetchType.EAGER)
    private S_Uom sFromUom;

    public S_Uom_Exchange() {
    }

    public S_Uom_Exchange(S_Uom_ExchangePK id) {
        this.id = id;
    }

    public S_Uom_Exchange(S_Uom_ExchangePK id, Double multipcoeff) {
        this.id = id;
        this.multipcoeff = multipcoeff;
    }

    public S_Uom_Exchange(String fromuomid, String touomid) {
        this.id = new S_Uom_ExchangePK(fromuomid, touomid);
    }

    public S_Uom_ExchangePK getId() {
        return id;
    }

    public void setId(S_Uom_ExchangePK id) {
        this.id = id;
    }

    public Double getMultipcoeff() {
        return multipcoeff;
    }

    public void setMultipcoeff(Double multipcoeff) {
        this.multipcoeff = multipcoeff;
    }

    public S_Uom getsFromUom() {
        return sFromUom;
    }

    public void setsFromUom(S_Uom sFromUom) {
        this.sFromUom = sFromUom;
    }

    public S_Uom getsToUom() {
        return sToUom;
    }

    public void setsToUom(S_Uom sToUom) {
        this.sToUom = sToUom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Uom_Exchange)) {
            return false;
        }
        S_Uom_Exchange other = (S_Uom_Exchange) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Shared.Entity.S_Uom_Exchange[s_Uom_ExchangePK=" + id + "]";
    }
}
