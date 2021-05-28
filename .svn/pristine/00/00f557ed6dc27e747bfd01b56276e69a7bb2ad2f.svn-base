package main.entity.shared.attach;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author khiemvk
 */
@Entity
@Table(name = "AF_LIB_ITEM",uniqueConstraints={})
public class Af_Lib_Item implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AFFILEID")
    private String affileid;
    @Basic(optional = false)
    @Column(name = "FILENAME")
    private String filename;
    @Basic(optional = false)
    @Column(name = "FILESIZEB")
    private Long filesizeb;
    @Basic(optional = false)
    @Column(name = "CRDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crdate;
    @Basic(optional = false)
    @Column(name = "MDFDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mdfdate;
    @Basic(optional = false)
    @Column(name = "UPDDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date upddtime;
    //@Basic(fetch = FetchType.LAZY)
    //@Column(name = "FILEDATA")
    //private byte[] filedata;
    @Column(name = "USERID")
    private String userid;
    @Column(name = "FILETYPEID")
    private String filetypeid;
    @Column(name = "STATUS")
    private Boolean status;
    @Column(name = "FILEPATH")
    private String filepath;

    public Af_Lib_Item() {
    }

    public Af_Lib_Item(String affileid) {
        this.affileid = affileid;
    }

    public Af_Lib_Item(String affileid, String filename, Long filesizeb, Date crdate, Date mdfdate, Date upddtime) {
    //public Af_Lib_Item(String affileid, String filename, Long filesizeb, Date crdate, Date mdfdate, Date upddtime, byte[] filedata) {
        this.affileid = affileid;
        this.filename = filename;
        this.filesizeb = filesizeb;
        this.crdate = crdate;
        this.mdfdate = mdfdate;
        this.upddtime = upddtime;
        //this.filedata = filedata;
    }

    public String getFilePath() {
        return filepath;
    }

    public void setFilePath(String filepath) {
        this.filepath = filepath;
    }

    public String getAffileid() {
        return affileid;
    }

    public void setAffileid(String affileid) {
        this.affileid = affileid;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getFilesizeb() {
        return filesizeb;
    }

    public void setFilesizeb(Long filesizeb) {
        this.filesizeb = filesizeb;
    }

    public Date getCrdate() {
        return crdate;
    }

    public void setCrdate(Date crdate) {
        this.crdate = crdate;
    }

    public Date getMdfdate() {
        return mdfdate;
    }

    public void setMdfdate(Date mdfdate) {
        this.mdfdate = mdfdate;
    }

    public Date getUpddtime() {
        return upddtime;
    }

    public void setUpddtime(Date upddtime) {
        this.upddtime = upddtime;
    }

//    public byte[] getFiledata() {
//        return filedata;
//    }
//
//    public void setFiledata(byte[] filedata) {
//        this.filedata = filedata;
//    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFiletypeid() {
        return filetypeid;
    }

    public void setFiletypeid(String filetypeid) {
        this.filetypeid = filetypeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (affileid != null ? affileid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Af_Lib_Item)) {
            return false;
        }
        Af_Lib_Item other = (Af_Lib_Item) object;
        if ((this.affileid == null && other.affileid != null) || (this.affileid != null && !this.affileid.equals(other.affileid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Shared.attach.Af_Lib_Item[affileid=" + affileid + "]";
    }

}
