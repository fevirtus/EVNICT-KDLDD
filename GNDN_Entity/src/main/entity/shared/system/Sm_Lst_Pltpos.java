/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.system;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author thaodd
 */
@Entity
@Table(name = "SM_LST_PLTPOS")
//@NamedQueries({
//    @NamedQuery(name = "Sm_Lst_Pltpos.findAll", query = "SELECT s FROM Sm_Lst_Pltpos s"),
//    @NamedQuery(name = "Sm_Lst_Pltpos.findByPposid", query = "SELECT s FROM Sm_Lst_Pltpos s WHERE s.pposid = :pposid"),
//    @NamedQuery(name = "Sm_Lst_Pltpos.findByPposdesc", query = "SELECT s FROM Sm_Lst_Pltpos s WHERE s.pposdesc = :pposdesc"),
//    @NamedQuery(name = "Sm_Lst_Pltpos.findByPposord", query = "SELECT s FROM Sm_Lst_Pltpos s WHERE s.pposord = :pposord")})
public class Sm_Lst_Pltpos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PPOSID")
    private String pposid;
    @Column(name = "PPOSDESC")
    private String pposdesc;
    @Column(name = "PPOSORD")
    private Integer pposord;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "smLstPltpos")
//    private List<Sm_Homepage_Plt> smHomepagePltList;
    @Column(name = "POSGROUP")
    private String posgroup;

    public Sm_Lst_Pltpos() {
    }

    public Sm_Lst_Pltpos(String pposid) {
        this.pposid = pposid;
    }

    public Sm_Lst_Pltpos(String pposid, String pposdesc) {
        this.pposid = pposid;
        this.pposdesc = pposdesc;
    }

    public String getPposid() {
        return pposid;
    }

    public void setPposid(String pposid) {
        this.pposid = pposid;
    }

    public String getPposdesc() {
        return pposdesc;
    }

    public void setPposdesc(String pposdesc) {
        this.pposdesc = pposdesc;
    }

    public Integer getPposord() {
        return pposord;
    }

    public void setPposord(Integer pposord) {
        this.pposord = pposord;
    }

//    public List<Sm_Homepage_Plt> getSmHomepagePltList() {
//        return smHomepagePltList;
//    }
//
//    public void setSmHomepagePltList(List<Sm_Homepage_Plt> smHomepagePltList) {
//        this.smHomepagePltList = smHomepagePltList;
//    }
    public String getPosgroup() {
        return posgroup;
    }

    public void setPosgroup(String posgroup) {
        this.posgroup = posgroup;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pposid != null ? pposid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sm_Lst_Pltpos)) {
            return false;
        }
        Sm_Lst_Pltpos other = (Sm_Lst_Pltpos) object;
        if ((this.pposid == null && other.pposid != null) || (this.pposid != null && !this.pposid.equals(other.pposid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.Sm_Lst_Pltpos[pposid=" + pposid + "]";
    }

}
