package main.entity.shared.attach;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author khiemvk
 */
@Entity
@Table(name = "AF_OTHER",uniqueConstraints={})
public class Af_Other implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AFFILEID")
    private String affileid;
    @Column(name = "OBJTYPEID")
    private String objtypeid;
    @Column(name = "OBJID")
    private String objid;
    @Column(name = "SUMDESC")
    private String sumdesc;
    @Column(name = "URL")
    private String url;
    @Column(name = "LIBITEMID")
    private String libitemid;
    @Basic(optional = false)
    @Column(name = "FILENAME")
    private String filename;
    @Basic(optional = false)
    @Column(name = "FILESIZEB")
    private Long filesizeb;
    @Column(name = "CRDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crdtime;
    @Column(name = "MDFDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mdfdtime;
    @Column(name = "UPDDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date upddtime;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "FILEDATA")
    private byte[] datafile;
    @Column(name = "USERID")
    private String userid;
    @Column(name = "FILETYPEID")
    private String filetypeid;
    @Column(name = "ATTTYPE")
    private String atttype;
    @Column(name = "FILEPATH")
    private String filePath;

    @JoinColumn(name = "ATTTYPE", referencedColumnName = "ATTYPE", unique = false, insertable = false, updatable = false, nullable = true)
    @ManyToOne(optional = false)
    private Af_Lst_Attachtype sAttachType;

    public Af_Other() {
    }

    public Af_Other(String affileid) {
        this.affileid = affileid;
    }

    public Af_Other(String affileid, String filename, Long filesizeb) {
    //public Af_Other(String affileid, String filename, Long filesizeb, byte[] filedata) {
        this.affileid = affileid;
        this.filename = filename;
        this.filesizeb = filesizeb;
        //this.filedata = filedata;
    }

    public Af_Lst_Attachtype getsAttachType() {
        return sAttachType;
    }

    public void setsAttachType(Af_Lst_Attachtype sAttachType) {
        this.sAttachType = sAttachType;
    }

    public String getAffileid() {
        return affileid;
    }

    public void setAffileid(String affileid) {
        this.affileid = affileid;
    }

    public String getObjtypeid() {
        return objtypeid;
    }

    public void setObjtypeid(String objtypeid) {
        this.objtypeid = objtypeid;
    }

    public String getObjid() {
        return objid;
    }

    public void setObjid(String objid) {
        this.objid = objid;
    }

    public String getSumdesc() {
        return sumdesc;
    }

    public void setSumdesc(String sumdesc) {
        this.sumdesc = sumdesc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLibitemid() {
        return libitemid;
    }

    public void setLibitemid(String libitemid) {
        this.libitemid = libitemid;
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

    public byte[] getDatafile() {
        return datafile;
    }

    public void setDatafile(byte[] datafile) {
        this.datafile = datafile;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAtttype() {
        return atttype;
    }

    public void setAtttype(String atttype) {
        this.atttype = atttype;
    }

    public String getFiletypeid() {
        return filetypeid;
    }

    public void setFiletypeid(String filetypeid) {
        this.filetypeid = filetypeid;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
        if (!(object instanceof Af_Other)) {
            return false;
        }
        Af_Other other = (Af_Other) object;
        if ((this.affileid == null && other.affileid != null) || (this.affileid != null && !this.affileid.equals(other.affileid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Shared.attach.Af_Other[affileid=" + affileid + "]";
    }

}
