/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.portlet;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author sypv
 */
@Entity
@Table(name = "SM_PORTLET_WFLOW_PARAM")
public class Sm_Portlet_Wflow_Param implements Serializable {

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
    private Sm_Portlet_Wflow wflowid;
    @Lob
    @Column(name = "VALUE_FILE")
    private byte[] valueFile;
    @Column(name = "NAMEFILE")
    private String namefile;

    public Sm_Portlet_Wflow_Param() {
    }

    public Sm_Portlet_Wflow_Param(String wflowdataid) {
        this.wflowdataid = wflowdataid;
    }

    public String getWflowdataid() {
        return wflowdataid;
    }

    public void setWflowdataid(String wflowdataid) {
        this.wflowdataid = wflowdataid;
    }

    public String getNamefile() {
        return namefile;
    }

    public void setNamefile(String namefile) {
        this.namefile = namefile;
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

    public Sm_Portlet_Wflow getWflowid() {
        return wflowid;
    }

    public void setWflowid(Sm_Portlet_Wflow wflowid) {
        this.wflowid = wflowid;
    }

    public String getDatacode() {
        return datacode;
    }

    public void setDatacode(String datacode) {
        this.datacode = datacode;
    }

    public byte[] getValueFile() {
        return valueFile;
    }

    public void setValueFile(byte[] valueFile) {
        this.valueFile = valueFile;
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
        if (!(object instanceof Sm_Portlet_Wflow_Param)) {
            return false;
        }
        Sm_Portlet_Wflow_Param other = (Sm_Portlet_Wflow_Param) object;
        if ((this.wflowdataid == null && other.wflowdataid != null) || (this.wflowdataid != null && !this.wflowdataid.equals(other.wflowdataid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.portlet.Sm_Portlet_Wflow_Param[ wflowdataid=" + wflowdataid + " ]";
    }

}
