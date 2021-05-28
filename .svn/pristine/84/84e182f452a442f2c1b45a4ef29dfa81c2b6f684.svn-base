/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.admin;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author sypv
 */
@Entity
@Table(name = "Q_FUNCTION_WFLOW_PARAM")
public class Q_Function_Wflow_Param implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WFLOWDATAID")
    private String wflowdataid;
    @Column(name = "WFLOWDATAID_PARENT")
    private String wflowdataidParent;
    @Column(name = "DATANAME")
    private String dataname;
    @Column(name = "DATACODE")
    private String datacode;
    @Column(name = "DATATYPEID")
    private String datatypeid;
    @Column(name = "TYPE")
    private Integer type;
    @Column(name = "VALUE")
    private String value;
    @Column(name = "DATAORD")
    private Integer dataord;
    @JoinColumn(name = "WFLOWID", referencedColumnName = "WFLOWID", nullable = false)
    @ManyToOne(optional = false)
    private Q_Function_Wflow wflowid;

    public Q_Function_Wflow_Param() {
    }

    public Q_Function_Wflow_Param(String wflowdataid) {
        this.wflowdataid = wflowdataid;
    }

    public String getWflowdataid() {
        return wflowdataid;
    }

    public void setWflowdataid(String wflowdataid) {
        this.wflowdataid = wflowdataid;
    }

    public String getWflowdataidParent() {
        return wflowdataidParent;
    }

    public void setWflowdataidParent(String wflowdataidParent) {
        this.wflowdataidParent = wflowdataidParent;
    }

    public String getDataname() {
        return dataname;
    }

    public void setDataname(String dataname) {
        this.dataname = dataname;
    }

    public String getDatatypeid() {
        return datatypeid;
    }

    public void setDatatypeid(String datatypeid) {
        this.datatypeid = datatypeid;
    }
  

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getDataord() {
        return dataord;
    }

    public void setDataord(Integer dataord) {
        this.dataord = dataord;
    }

    public Q_Function_Wflow getWflowid() {
        return wflowid;
    }

    public void setWflowid(Q_Function_Wflow wflowid) {
        this.wflowid = wflowid;
    }

    public String getDatacode() {
        return datacode;
    }

    public void setDatacode(String datacode) {
        this.datacode = datacode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wflowdataid != null ? wflowdataid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Q_Function_Wflow_Param)) {
            return false;
        }
        Q_Function_Wflow_Param other = (Q_Function_Wflow_Param) object;
        if ((this.wflowdataid == null && other.wflowdataid != null) || (this.wflowdataid != null && !this.wflowdataid.equals(other.wflowdataid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.admin.Q_Function_Wflow_Param[ wflowdataid=" + wflowdataid + " ]";
    }

}
