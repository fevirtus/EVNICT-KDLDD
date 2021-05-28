/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author thaodd
 */
@Entity
@Table(name = "BB_MESSAGE")
//@NamedQueries({
//    @NamedQuery(name = "Bb_Message.findAll", query = "SELECT b FROM Bb_Message b"),
//    @NamedQuery(name = "Bb_Message.findByBbmid", query = "SELECT b FROM Bb_Message b WHERE b.bbmid = :bbmid"),
//    @NamedQuery(name = "Bb_Message.findByBbmsum", query = "SELECT b FROM Bb_Message b WHERE b.bbmsum = :bbmsum"),
//    @NamedQuery(name = "Bb_Message.findByEdfrom", query = "SELECT b FROM Bb_Message b WHERE b.edfrom = :edfrom"),
//    @NamedQuery(name = "Bb_Message.findByEdto", query = "SELECT b FROM Bb_Message b WHERE b.edto = :edto"),
//    @NamedQuery(name = "Bb_Message.findByUserCrId", query = "SELECT b FROM Bb_Message b WHERE b.userCrId = :userCrId"),
//    @NamedQuery(name = "Bb_Message.findByUserCrDtime", query = "SELECT b FROM Bb_Message b WHERE b.userCrDtime = :userCrDtime"),
//    @NamedQuery(name = "Bb_Message.findByUserMdfId", query = "SELECT b FROM Bb_Message b WHERE b.userMdfId = :userMdfId"),
//    @NamedQuery(name = "Bb_Message.findByUserMdfDtime", query = "SELECT b FROM Bb_Message b WHERE b.userMdfDtime = :userMdfDtime")})
public class Bb_Message implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BBMID")
    private String bbmid;
    @Basic(optional = false)
    @Column(name = "BBMSUM")
    private String bbmsum;
    @Lob
    @Column(name = "BBMDETAIL")
    private String bbmdetail;
    @Column(name = "EDFROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date edfrom;
    @Column(name = "EDTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date edto;
    @Column(name = "USER_CR_ID")
    private String userCrId;
    @Column(name = "USER_CR_DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userCrDtime;
    @Column(name = "USER_MDF_ID")
    private String userMdfId;
    @Column(name = "USER_MDF_DTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userMdfDtime;
    @Column(name = "ENABLE")
    private Boolean enable;
    @Column(name = "ISPUBLIC")
    private Boolean ispublic;

    public Boolean getIspublic() {
        return ispublic;
    }

    public void setIspublic(Boolean ispublic) {
        this.ispublic = ispublic;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bbMessage")
//    private List<Bb_Message_Rcv> bbMessageRcvList;
//  Bỏ đi không dùng quan hệ một nhiều nữa mà là quan hệ nhiều nhiều
//    @JoinColumn(name = "RCVTYPEID", referencedColumnName = "BBRCVTYPEID")
//    @ManyToOne
//    private Bb_Lst_Receivertype bbLstReceivertype;

    public Bb_Message() {
    }

    public Bb_Message(String bbmid) {
        this.bbmid = bbmid;
    }

    public Bb_Message(String bbmid, String bbmsum) {
        this.bbmid = bbmid;
        this.bbmsum = bbmsum;
    }

    public String getBbmid() {
        return bbmid;
    }

    public void setBbmid(String bbmid) {
        this.bbmid = bbmid;
    }

    public String getBbmsum() {
        return bbmsum;
    }

    public void setBbmsum(String bbmsum) {
        this.bbmsum = bbmsum;
    }

    public String getBbmdetail() {
        return bbmdetail;
    }

    public void setBbmdetail(String bbmdetail) {
        this.bbmdetail = bbmdetail;
    }

    public Date getEdfrom() {
        return edfrom;
    }

    public void setEdfrom(Date edfrom) {
        this.edfrom = edfrom;
    }

    public Date getEdto() {
        return edto;
    }

    public void setEdto(Date edto) {
        this.edto = edto;
    }

    public String getUserCrId() {
        return userCrId;
    }

    public void setUserCrId(String userCrId) {
        this.userCrId = userCrId;
    }

    public Date getUserCrDtime() {
        return userCrDtime;
    }

    public void setUserCrDtime(Date userCrDtime) {
        this.userCrDtime = userCrDtime;
    }

    public String getUserMdfId() {
        return userMdfId;
    }

    public void setUserMdfId(String userMdfId) {
        this.userMdfId = userMdfId;
    }

    public Date getUserMdfDtime() {
        return userMdfDtime;
    }

    public void setUserMdfDtime(Date userMdfDtime) {
        this.userMdfDtime = userMdfDtime;
    }

//    public List<Bb_Message_Rcv> getBbMessageRcvList() {
//        return bbMessageRcvList;
//    }
//
//    public void setBbMessageRcvList(List<Bb_Message_Rcv> bbMessageRcvList) {
//        this.bbMessageRcvList = bbMessageRcvList;
//    }

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
        hash += (bbmid != null ? bbmid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bb_Message)) {
            return false;
        }
        Bb_Message other = (Bb_Message) object;
        if ((this.bbmid == null && other.bbmid != null) || (this.bbmid != null && !this.bbmid.equals(other.bbmid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.system.Bb_Message[bbmid=" + bbmid + "]";
    }

    @Transient
    private Integer attachcount; //Biến đếm số bản ghi file đính kèm cùng thông báo, cập nhật biến này ở bên ngoài

    public Integer getAttachcount() {
        return attachcount;
    }

    public void setAttachcount(Integer attachcount) {
        this.attachcount = attachcount;
    }

}
