/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diemdo;

import java.io.Serializable;
import java.math.BigInteger;
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
 * @author TUYEN EVN
 */
@Entity
@Table(name = "A_LST_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ALstType.findAll", query = "SELECT a FROM ALstType a"),
    @NamedQuery(name = "ALstType.findByTypeid", query = "SELECT a FROM ALstType a WHERE a.typeid = :typeid"),
    @NamedQuery(name = "ALstType.findByTypedesc", query = "SELECT a FROM ALstType a WHERE a.typedesc = :typedesc"),
    @NamedQuery(name = "ALstType.findByTypeord", query = "SELECT a FROM ALstType a WHERE a.typeord = :typeord"),
    @NamedQuery(name = "ALstType.findByIcon", query = "SELECT a FROM ALstType a WHERE a.icon = :icon")})
public class ALstType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TYPEID")
    private String typeid;
    @Column(name = "TYPEDESC")
    private String typedesc;
    @Column(name = "TYPEORD")
    private BigInteger typeord;
    @Column(name = "ICON")
    private String icon;

    public ALstType() {
    }

    public ALstType(String typeid) {
        this.typeid = typeid;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getTypedesc() {
        return typedesc;
    }

    public void setTypedesc(String typedesc) {
        this.typedesc = typedesc;
    }

    public BigInteger getTypeord() {
        return typeord;
    }

    public void setTypeord(BigInteger typeord) {
        this.typeord = typeord;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeid != null ? typeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ALstType)) {
            return false;
        }
        ALstType other = (ALstType) object;
        if ((this.typeid == null && other.typeid != null) || (this.typeid != null && !this.typeid.equals(other.typeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "diemdo.ALstType[ typeid=" + typeid + " ]";
    }
    
}
