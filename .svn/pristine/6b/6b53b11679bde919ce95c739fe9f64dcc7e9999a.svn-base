package main.entity.shared.admin;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Basic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Khiemvk
 */
@Entity
@Table(name = "Q_LOGINOUT", uniqueConstraints = {})
public class Q_Loginout implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LOGID")
    private Integer logid;
    // Fields
//    @Id
//    @Column(name = "LOGID", unique = true, nullable = false)
//    private Integer logid;
    @Column(name = "USERID", nullable = false)
    private String userid;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOGINTIME", nullable = false)
    private Date logintime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LASTACCESSTIME", nullable = false)
    private Date lastaccesstime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOGOUTTIME", nullable = false)
    private Date logouttime;

    // Constructors
    /** default constructor */
    public Q_Loginout() {
    }

    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    public Integer getLogid() {
        return logid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLastaccesstime(Date lastaccesstime) {
        this.lastaccesstime = lastaccesstime;
    }

    public Date getLastaccesstime() {
        return lastaccesstime;
    }

    public void setLogouttime(Date logouttime) {
        this.logouttime = logouttime;
    }

    public Date getLogouttime() {
        return logouttime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logid != null ? logid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Q_Loginout)) {
            return false;
        }
        Q_Loginout other = (Q_Loginout) object;
        if ((this.logid == null && other.logid != null) || (this.logid != null && !this.logid.equals(other.logid))) {
            return false;
        }
        return true;
    }
}
