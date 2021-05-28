/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.report;

import java.io.Serializable;
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
 * @author ChuotCong
 */
@Entity
@Table(name = "S_REPORT_COMMANDTYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "S_Report_Commandtype.findAll", query = "SELECT s FROM S_Report_Commandtype s"),
    @NamedQuery(name = "S_Report_Commandtype.findByCommandtypeid", query = "SELECT s FROM S_Report_Commandtype s WHERE s.commandtypeid = :commandtypeid"),
    @NamedQuery(name = "S_Report_Commandtype.findByCommandtypedesc", query = "SELECT s FROM S_Report_Commandtype s WHERE s.commandtypedesc = :commandtypedesc")})
public class S_Report_Commandtype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COMMANDTYPEID")
    private String commandtypeid;
    @Column(name = "COMMANDTYPEDESC")
    private String commandtypedesc;

    public S_Report_Commandtype() {
    }

    public S_Report_Commandtype(String commandtypeid) {
        this.commandtypeid = commandtypeid;
    }

    public String getCommandtypeid() {
        return commandtypeid;
    }

    public void setCommandtypeid(String commandtypeid) {
        this.commandtypeid = commandtypeid;
    }

    public String getCommandtypedesc() {
        return commandtypedesc;
    }

    public void setCommandtypedesc(String commandtypedesc) {
        this.commandtypedesc = commandtypedesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commandtypeid != null ? commandtypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Report_Commandtype)) {
            return false;
        }
        S_Report_Commandtype other = (S_Report_Commandtype) object;
        if ((this.commandtypeid == null && other.commandtypeid != null) || (this.commandtypeid != null && !this.commandtypeid.equals(other.commandtypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.report.S_Report_Commandtype[ commandtypeid=" + commandtypeid + " ]";
    }
    
}
