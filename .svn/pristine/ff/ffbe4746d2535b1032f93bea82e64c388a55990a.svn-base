package main.entity.shared.system;

import java.io.Serializable;
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
@Table(name = "S_LST_OBJ",uniqueConstraints={})
public class S_Lst_Obj implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "OBJTYPEID")
    private String objtypeid;
    @Basic(optional = false)
    @Column(name = "OBJNAME")
    private String objname;
    @Column(name = "ISATTRGROUP")
    private Boolean isattrgroup;
    @Column(name = "ISPQOBJ")
    private Boolean ispqobj;
    @Column(name = "ISLOGOBJREAD")
    private Boolean islogobjread;
    @Column(name = "ISAFOTHER")
    private Boolean isafother;
    @Column(name = "OBJTABLENAME")
    private String objtablename;
    @Column(name = "OBJKEYFIELDNAME")
    private String objkeyfieldname;
    @Column(name = "OBJDESCFIELDNAME")
    private String objdescfieldname;

    @Column(name = "OBJORD")
    private String objord;
    
    public S_Lst_Obj() {
    }

    public S_Lst_Obj(String objtypeid) {
        this.objtypeid = objtypeid;
    }

    public S_Lst_Obj(String objtypeid, String objname) {
        this.objtypeid = objtypeid;
        this.objname = objname;
    }

    public String getObjord() {
        return objord;
    }

    public void setObjord(String objord) {
        this.objord = objord;
    }

    public String getObjtypeid() {
        return objtypeid;
    }

    public void setObjtypeid(String objtypeid) {
        this.objtypeid = objtypeid;
    }

    public String getObjname() {
        return objname;
    }

    public void setObjname(String objname) {
        this.objname = objname;
    }

    public Boolean getIsattrgroup() {
        return isattrgroup;
    }

    public void setIsattrgroup(Boolean isattrgroup) {
        this.isattrgroup = isattrgroup;
    }

    public Boolean getIspqobj() {
        return ispqobj;
    }

    public void setIspqobj(Boolean ispqobj) {
        this.ispqobj = ispqobj;
    }

    public Boolean getIslogobjread() {
        return islogobjread;
    }

    public void setIslogobjread(Boolean islogobjread) {
        this.islogobjread = islogobjread;
    }

    public Boolean getIsafother() {
        return isafother;
    }

    public void setIsafother(Boolean isafother) {
        this.isafother = isafother;
    }

    public String getObjtablename() {
        return objtablename;
    }

    public void setObjtablename(String objtablename) {
        this.objtablename = objtablename;
    }

    public String getObjkeyfieldname() {
        return objkeyfieldname;
    }

    public void setObjkeyfieldname(String objkeyfieldname) {
        this.objkeyfieldname = objkeyfieldname;
    }

    public String getObjdescfieldname() {
        return objdescfieldname;
    }

    public void setObjdescfieldname(String objdescfieldname) {
        this.objdescfieldname = objdescfieldname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (objtypeid != null ? objtypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof S_Lst_Obj)) {
            return false;
        }
        S_Lst_Obj other = (S_Lst_Obj) object;
        if ((this.objtypeid == null && other.objtypeid != null) || (this.objtypeid != null && !this.objtypeid.equals(other.objtypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return objtypeid;
    }

}

