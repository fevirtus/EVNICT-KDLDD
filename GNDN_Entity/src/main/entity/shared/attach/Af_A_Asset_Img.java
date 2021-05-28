package main.entity.shared.attach;

import java.io.Serializable;
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
@Table(name = "AF_A_ASSET_IMG",uniqueConstraints={})
public class Af_A_Asset_Img implements Serializable {
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
    @Column(name = "CRDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crdate;
    @Column(name = "MDFDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mdfdate;
    @Column(name = "UPDDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date upddtime;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "FILEDATA")
    private byte[] filedata;
    @Column(name = "USERID")
    private String userid;
    @Column(name = "FILEPATH")
    private String filepath;

    public Af_A_Asset_Img() {
    }

    public Af_A_Asset_Img(String affileid) {
        this.affileid = affileid;
    }

    public Af_A_Asset_Img(String affileid, String filename, Long filesizeb, byte[] filedata) {
        this.affileid = affileid;
        this.filename = filename;
        this.filesizeb = filesizeb;
        this.filedata = filedata;
    }

    public String getAffileid() {
        return affileid;
    }

    public void setAffileid(String affileid) {
        this.affileid = affileid;
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

    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
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
        if (!(object instanceof Af_A_Asset_Img)) {
            return false;
        }
        Af_A_Asset_Img other = (Af_A_Asset_Img) object;
        if ((this.affileid == null && other.affileid != null) || (this.affileid != null && !this.affileid.equals(other.affileid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ShareAttach.Af_A_Asset_Img[affileid=" + affileid + "]";
    }

}
