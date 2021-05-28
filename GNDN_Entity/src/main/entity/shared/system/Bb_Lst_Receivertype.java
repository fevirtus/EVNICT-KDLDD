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
 * @author thaodd
 */
@Entity
@Table(name = "BB_LST_RECEIVERTYPE")
//@NamedQueries({
//    @NamedQuery(name = "Bb_Lst_Receivertype.findAll", query = "SELECT b FROM Bb_Lst_Receivertype b"),
//    @NamedQuery(name = "Bb_Lst_Receivertype.findByBbrcvtypeid", query = "SELECT b FROM Bb_Lst_Receivertype b WHERE b.bbrcvtypeid = :bbrcvtypeid"),
//    @NamedQuery(name = "Bb_Lst_Receivertype.findByBbrcvtypedesc", query = "SELECT b FROM Bb_Lst_Receivertype b WHERE b.bbrcvtypedesc = :bbrcvtypedesc"),
//    @NamedQuery(name = "Bb_Lst_Receivertype.findByBbrcvtypeord", query = "SELECT b FROM Bb_Lst_Receivertype b WHERE b.bbrcvtypeord = :bbrcvtypeord")})
public class Bb_Lst_Receivertype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BBRCVTYPEID")
    private String bbrcvtypeid;
    @Basic(optional = false)
    @Column(name = "BBRCVTYPEDESC")
    private String bbrcvtypedesc;
    @Column(name = "BBRCVTYPEORD")
    private Integer bbrcvtypeord;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bbLstReceivertype")
//    private List<Bb_Message_Rcv> bbMessageRcvList;
//    @OneToMany(mappedBy = "bbLstReceivertype")
//    private List<Bb_Message> bbMessageList;

    public Bb_Lst_Receivertype() {
    }

    public Bb_Lst_Receivertype(String bbrcvtypeid) {
        this.bbrcvtypeid = bbrcvtypeid;
    }

    public Bb_Lst_Receivertype(String bbrcvtypeid, String bbrcvtypedesc) {
        this.bbrcvtypeid = bbrcvtypeid;
        this.bbrcvtypedesc = bbrcvtypedesc;
    }

    public String getBbrcvtypeid() {
        return bbrcvtypeid;
    }

    public void setBbrcvtypeid(String bbrcvtypeid) {
        this.bbrcvtypeid = bbrcvtypeid;
    }

    public String getBbrcvtypedesc() {
        return bbrcvtypedesc;
    }

    public void setBbrcvtypedesc(String bbrcvtypedesc) {
        this.bbrcvtypedesc = bbrcvtypedesc;
    }

    public Integer getBbrcvtypeord() {
        return bbrcvtypeord;
    }

    public void setBbrcvtypeord(Integer bbrcvtypeord) {
        this.bbrcvtypeord = bbrcvtypeord;
    }

//    public List<Bb_Message_Rcv> getBbMessageRcvList() {
//        return bbMessageRcvList;
//    }
//
//    public void setBbMessageRcvList(List<Bb_Message_Rcv> bbMessageRcvList) {
//        this.bbMessageRcvList = bbMessageRcvList;
//    }
//
//    public List<Bb_Message> getBbMessageList() {
//        return bbMessageList;
//    }
//
//    public void setBbMessageList(List<Bb_Message> bbMessageList) {
//        this.bbMessageList = bbMessageList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bbrcvtypeid != null ? bbrcvtypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bb_Lst_Receivertype)) {
            return false;
        }
        Bb_Lst_Receivertype other = (Bb_Lst_Receivertype) object;
        if ((this.bbrcvtypeid == null && other.bbrcvtypeid != null) || (this.bbrcvtypeid != null && !this.bbrcvtypeid.equals(other.bbrcvtypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.Bb_Lst_Receivertype[bbrcvtypeid=" + bbrcvtypeid + "]";
    }

}
