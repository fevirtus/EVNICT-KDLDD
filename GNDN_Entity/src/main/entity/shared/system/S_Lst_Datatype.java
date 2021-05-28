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
@Table(name = "S_LST_DATATYPE",uniqueConstraints={})
public class S_Lst_Datatype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DATATYPEID")
    private String datatypeid;
    @Column(name = "DATATYPEDESC")
    private String datatypedesc;
    @Column(name = "DATADORD")
    private Integer datadord;
    @Column(name = "ORACLETYPE")
    private String oracletype;
    @Column(name = "SQLTYPE")
    private String sqltype;    

    public S_Lst_Datatype() {
    }

    public S_Lst_Datatype(String datatypeid) {
        this.datatypeid = datatypeid;
    }

    public String getDatatypeid() {
        return datatypeid;
    }

    public void setDatatypeid(String datatypeid) {
        this.datatypeid = datatypeid;
    }

    public String getDatatypedesc() {
        return datatypedesc;
    }

    public void setDatatypedesc(String datatypedesc) {
        this.datatypedesc = datatypedesc;
    }

    public Integer getDatadord() {
        return datadord;
    }

    public void setDatadord(Integer datadord) {
        this.datadord = datadord;
    }

    public String getOracletype() {
        return oracletype;
    }

    public void setOracletype(String oracletype) {
        this.oracletype = oracletype;
    }

    public String getSqltype() {
        return sqltype;
    }

    public void setSqltype(String sqltype) {
        this.sqltype = sqltype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (datatypeid != null ? datatypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Lst_Datatype)) {
            return false;
        }
        S_Lst_Datatype other = (S_Lst_Datatype) object;
        if ((this.datatypeid == null && other.datatypeid != null) || (this.datatypeid != null && !this.datatypeid.equals(other.datatypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Shared.S_Lst_Datatype[datatypeid=" + datatypeid + "]";
    }

}
