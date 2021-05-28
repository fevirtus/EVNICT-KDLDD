package main.entity.shared.admin;

import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Khiemvk
 */
@Entity
@Table(name = "Q_ROLE", uniqueConstraints = {})
public class Q_Role implements java.io.Serializable {

    // Fields
    @Id
    @Column(name = "ROLEID", unique = true, nullable = false, length = 25)
    private String roleid;
    @Column(name = "ROLEDESC", nullable = false)
    private String roledesc;
    @Column(name = "ENABLE", nullable = false)
    private Boolean enable;
    @Column(name = "ISFIX", nullable = false)
    private Boolean isfix;
    @Transient
    private Boolean bChecked;
    //Join
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "qrole")
    private List<Q_User_Role> user_roles = new Vector<Q_User_Role>();

    // Constructors
    /** default constructor */
    public Q_Role() {
    }

    /** minimal constructor */
    public Q_Role(String roleid, String roledesc) {
        this.roleid = roleid;
        this.roledesc = roledesc;
    }

    // Property accessors
    public String getRoleid() {
        return this.roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getRoledesc() {
        return this.roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    public void setUser_roles(List<Q_User_Role> user_roles) {
        this.user_roles = user_roles;
    }

    public List<Q_User_Role> getUser_roles() {
        return user_roles;
    }

    public Boolean getbChecked() {
        if (bChecked != null) {
            return bChecked;
        }
        return false;
    }

    public void setbChecked(Boolean bChecked) {
        this.bChecked = bChecked;
    }

    public Boolean getEnable() {
        if (enable==null)
            return false;
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getIsfix() {
        if (isfix==null)
            return false;

        return isfix;
    }

    public void setIsfix(Boolean isfix) {
        this.isfix = isfix;
    }

}
