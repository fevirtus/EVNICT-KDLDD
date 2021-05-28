/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author thaodd
 */
@Entity
//@NamedQueries({
//    @NamedQuery(name = "Bb_Message_Rcv.findAll", query = "SELECT b FROM Bb_Message_Rcv b"),
//    @NamedQuery(name = "Bb_Message_Rcv.findByBbmid", query = "SELECT b FROM Bb_Message_Rcv b WHERE b.bb_Message_RcvPK.bbmid = :bbmid"),
//    @NamedQuery(name = "Bb_Message_Rcv.findByBbrcvtypeid", query = "SELECT b FROM Bb_Message_Rcv b WHERE b.bb_Message_RcvPK.bbrcvtypeid = :bbrcvtypeid"),
//    @NamedQuery(name = "Bb_Message_Rcv.findByRcvid", query = "SELECT b FROM Bb_Message_Rcv b WHERE b.bb_Message_RcvPK.rcvid = :rcvid")})
public class Bb_Message_Rcv_Ext implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Bb_Message_RcvPK bb_Message_RcvPK;
//  Tạm bỏ các biến bên dưới vì chưa sử dụng
//    @JoinColumn(name = "BBMID", referencedColumnName = "BBMID", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
//    private Bb_Message bbMessage;
//    @JoinColumn(name = "BBRCVTYPEID", referencedColumnName = "BBRCVTYPEID", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
//    private Bb_Lst_Receivertype bbLstReceivertype;


    public Bb_Message_Rcv_Ext() {
    }

    public Bb_Message_Rcv_Ext(Bb_Message_RcvPK bb_Message_RcvPK) {
        this.bb_Message_RcvPK = bb_Message_RcvPK;
    }

    public Bb_Message_Rcv_Ext(String bbmid, String bbrcvtypeid, String rcvid) {
        this.bb_Message_RcvPK = new Bb_Message_RcvPK(bbmid, bbrcvtypeid, rcvid);
    }

    public Bb_Message_RcvPK getId() {
        return bb_Message_RcvPK;
    }

    public void setId(Bb_Message_RcvPK bb_Message_RcvPK) {
        this.bb_Message_RcvPK = bb_Message_RcvPK;
    }

//    public Bb_Message getBbMessage() {
//        return bbMessage;
//    }
//
//    public void setBbMessage(Bb_Message bbMessage) {
//        this.bbMessage = bbMessage;
//    }
//
//    public Bb_Lst_Receivertype getBbLstReceivertype() {
//        return bbLstReceivertype;
//    }
//
//    public void setBbLstReceivertype(Bb_Lst_Receivertype bbLstReceivertype) {
//        this.bbLstReceivertype = bbLstReceivertype;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bb_Message_RcvPK != null ? bb_Message_RcvPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bb_Message_Rcv_Ext)) {
            return false;
        }
        Bb_Message_Rcv_Ext other = (Bb_Message_Rcv_Ext) object;
        if ((this.bb_Message_RcvPK == null && other.bb_Message_RcvPK != null) || (this.bb_Message_RcvPK != null && !this.bb_Message_RcvPK.equals(other.bb_Message_RcvPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.Bb_Message_Rcv[bb_Message_RcvPK=" + bb_Message_RcvPK + "]";
    }

    //Bổ sung thêm
    @Column(name="OBJDESC",insertable=false,updatable=false)
    private String objdesc;

    public String getObjdesc() {
        return objdesc;
    }
}
