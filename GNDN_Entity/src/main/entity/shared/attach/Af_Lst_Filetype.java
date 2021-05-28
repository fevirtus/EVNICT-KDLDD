package main.entity.shared.attach;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author khiemvk
 */
@Entity
@Table(name = "AF_LST_FILETYPE",uniqueConstraints={})
public class Af_Lst_Filetype implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AFFTID")
    private String afftid;
    @Column(name = "AFFTDESC")
    private String afftdesc;
    @Column(name = "AFORD")
    private Integer aford;
    @Column(name = "FILEEXTLIST")
    private String fileextlist;    

    public Af_Lst_Filetype() {
    }

    public Af_Lst_Filetype(String afftid) {
        this.afftid = afftid;
    }

    public Af_Lst_Filetype(String afftid,String afftdesc) {
        this.afftid = afftid;
        this.afftdesc = afftdesc;
    }

    public String getAfftid() {
        return afftid;
    }

    public void setAfftid(String afftid) {
        this.afftid = afftid;
    }

    public String getAfftdesc() {
        return afftdesc;
    }

    public void setAfftdesc(String afftdesc) {
        this.afftdesc = afftdesc;
    }

    public Integer getAford() {
        return aford;
    }

    public void setAford(Integer aford) {
        this.aford = aford;
    }

    public String getFileextlist() {
        return fileextlist;
    }

    public void setFileextlist(String fileextlist) {
        this.fileextlist = fileextlist;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (afftid != null ? afftid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Af_Lst_Filetype)) {
            return false;
        }
        Af_Lst_Filetype other = (Af_Lst_Filetype) object;
        if ((this.afftid == null && other.afftid != null) || (this.afftid != null && !this.afftid.equals(other.afftid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return afftid;
    }

}
