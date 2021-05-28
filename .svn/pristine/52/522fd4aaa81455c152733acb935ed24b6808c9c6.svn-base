/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.system;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author thaodd
 */
@Entity
public class Bb_Message_Ext implements Serializable {
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
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bbMessage")
//    private List<Bb_Message_Rcv> bbMessageRcvList;
//  Bỏ đi không dùng quan hệ một nhiều nữa mà là quan hệ nhiều nhiều
//    @JoinColumn(name = "RCVTYPEID", referencedColumnName = "BBRCVTYPEID")
//    @ManyToOne
//    private Bb_Lst_Receivertype bbLstReceivertype;
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
    
    public Bb_Message_Ext() {
    }

    public Bb_Message_Ext(String bbmid) {
        this.bbmid = bbmid;
    }

    public Bb_Message_Ext(String bbmid, String bbmsum) {
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

    //Phần thêm mới
    @Column(name = "ATTACHCOUNT")
    Integer attachCount;
    @Column(name="READDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date readdtime;
    @Column(name = "ISUPDATE")
    Boolean isupdate;

    public Boolean getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(Boolean isupdate) {
        this.isupdate = isupdate;
    }

    public Date getReaddtime() {
        return readdtime;
    }

    public void setReaddtime(Date readdtime) {
        this.readdtime = readdtime;
    }


    public Integer getAttachCount() {
        return attachCount;
    }

    public void setAttachCount(Integer attachCount) {
        this.attachCount = attachCount;
    }

    public String getHtmlEnableImg()
    {
        if (enable != null && enable)
            return "/images/DataEditing/check.gif";

        return "/images/DataEditing/cancel.gif";
    }

    public String getHtmlNewImg()
    {
        if (readdtime == null)
            return "/images/new.gif";
        else if (isupdate != null && isupdate)
            return "/images/update.gif";
        return "";
    }

    public String getSender()
    {
        String s=getUserCrId();
        if (getUserMdfId() != null && !getUserMdfId().equals(getUserCrId()))
        {
            if (s != null && !s.isEmpty())
                s += ", ";
            s+= getUserMdfId();
        }
        return s;
    }

    public Date getSendDTime()
    {
        return getUserMdfDtime()!=null?getUserMdfDtime():getUserCrDtime();
    }
}
    
