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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "S_COMPANY_TYPE")
@NamedQueries({
    @NamedQuery(name = "S_Company_Type.findAll", query = "SELECT s FROM S_Company_Type s")})
public class S_Company_Type implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COMPTYPEID")
    private String comptypeid;
    @Basic(optional = false)
    @Column(name = "COMPTYPEDESC")
    private String comptypedesc;
    @Column(name = "STT")
    private Integer stt;
    @Column(name = "NOTE")
    private String note;

    public S_Company_Type() {
    }

    public S_Company_Type(String comptypeid) {
        this.comptypeid = comptypeid;
    }

    public S_Company_Type(String comptypeid, String comptypedesc) {
        this.comptypeid = comptypeid;
        this.comptypedesc = comptypedesc;
    }

    public String getComptypeid() {
        return comptypeid;
    }

    public void setComptypeid(String comptypeid) {
        this.comptypeid = comptypeid;
    }

    public String getComptypedesc() {
        return comptypedesc;
    }

    public void setComptypedesc(String comptypedesc) {
        this.comptypedesc = comptypedesc;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comptypeid != null ? comptypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Company_Type)) {
            return false;
        }
        S_Company_Type other = (S_Company_Type) object;
        if ((this.comptypeid == null && other.comptypeid != null) || (this.comptypeid != null && !this.comptypeid.equals(other.comptypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.S_Company_Type[comptypeid=" + comptypeid + "]";
    }

}
