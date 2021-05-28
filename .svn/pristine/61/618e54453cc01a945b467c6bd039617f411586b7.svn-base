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
import javax.persistence.Table;

/**
 *
 * @author khiemvk
 */
@Entity
@Table(name = "S_CURRENCY",uniqueConstraints={})
public class S_Currency implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CURRENCYID")
    private String currencyid;
    @Column(name = "CURRENCYDESC")
    private String currencydesc;
    @Column(name = "CURRENCYORD")
    private Integer currencyord;

    public S_Currency() {
    }

    public S_Currency(String currencyid) {
        this.currencyid = currencyid;
    }

    public String getCurrencyid() {
        return currencyid;
    }

    public void setCurrencyid(String currencyid) {
        this.currencyid = currencyid;
    }

    public String getCurrencydesc() {
        return currencydesc;
    }

    public void setCurrencydesc(String currencydesc) {
        this.currencydesc = currencydesc;
    }

    public Integer getCurrencyord() {
        return currencyord;
    }

    public void setCurrencyord(Integer currencyord) {
        this.currencyord = currencyord;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (currencyid != null ? currencyid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Currency)) {
            return false;
        }
        S_Currency other = (S_Currency) object;
        if ((this.currencyid == null && other.currencyid != null) || (this.currencyid != null && !this.currencyid.equals(other.currencyid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Shared.Entity.S_Currency[currencyid=" + currencyid + "]";
    }

}
