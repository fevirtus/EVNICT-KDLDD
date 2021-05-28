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
@Table(name = "AF_A_ASSET_ATT_ITEM",uniqueConstraints={})
public class Af_A_Asset_Att_Item implements Serializable {
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
    @Column(name = "CRDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crdtime;
    @Basic(optional = false)
    @Column(name = "MDFDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mdfdtime;
    @Basic(optional = false)
    @Column(name = "UPDDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date upddtime;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "FILEDATA")
    private byte[] filedata;
    @Column(name = "USERID")
    private String userid;
    @Column(name = "FILETYPEID")
    private String filetypeid;
    @Column(name = "FILEPATH")
    private String filepath;

    public Af_A_Asset_Att_Item() {
    }

    public Af_A_Asset_Att_Item(String affileid) {
        this.affileid = affileid;
    }

    public Af_A_Asset_Att_Item(String affileid, String filename, Long filesizeb, Date crdtime, Date mdfdtime, Date upddtime, byte[] filedata) {
        this.affileid = affileid;
        this.filename = filename;
        this.filesizeb = filesizeb;
        this.crdtime = crdtime;
        this.mdfdtime = mdfdtime;
        this.upddtime = upddtime;
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

    public Date getCrdtime() {
        return crdtime;
    }

    public void setCrdtime(Date crdtime) {
        this.crdtime = crdtime;
    }

    public Date getMdfdtime() {
        return mdfdtime;
    }

    public void setMdfdtime(Date mdfdtime) {
        this.mdfdtime = mdfdtime;
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

    public String getFiletypeid() {
        return filetypeid;
    }

    public void setFiletypeid(String filetypeid) {
        this.filetypeid = filetypeid;
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
        if (!(object instanceof Af_A_Asset_Att_Item)) {
            return false;
        }
        Af_A_Asset_Att_Item other = (Af_A_Asset_Att_Item) object;
        if ((this.affileid == null && other.affileid != null) || (this.affileid != null && !this.affileid.equals(other.affileid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Shared.attach.Af_A_Asset_Att_Item[affileid=" + affileid + "]";
    }

}
