/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.entity.shared.attach;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Ganbatte
 */
@Entity
@Table(name = "Af_A_Asset_Img_File",uniqueConstraints={})
public class Af_A_Asset_Img_File implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AFFILEID")
    private String affileid;
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "FILEDATA")
    private byte[] filedata;

    public Af_A_Asset_Img_File() {
    }

    public Af_A_Asset_Img_File(String affileid) {
        this.affileid = affileid;
    }

    public String getAffileid() {
        return affileid;
    }

    public void setAffileid(String affileid) {
        this.affileid = affileid;
    }

    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
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
        if (!(object instanceof Af_A_Asset_Img_File)) {
            return false;
        }
        Af_A_Asset_Img_File other = (Af_A_Asset_Img_File) object;
        if ((this.affileid == null && other.affileid != null) || (this.affileid != null && !this.affileid.equals(other.affileid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.attach.Af_A_Asset_Img_File[id=" + affileid + "]";
    }

}
