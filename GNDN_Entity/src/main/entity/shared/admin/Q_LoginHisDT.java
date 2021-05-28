/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.admin;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author ba
 */
@Entity
public class Q_LoginHisDT implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "LOGID")
    private Long logid;
    @Column(name = "USERID")
    private String userid;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "LOGINTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logintime;
    @Column(name = "LASTACCESSTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastaccesstime;
    @Column(name = "LOGOUTTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logouttime;

    public Date getLastaccesstime() {
        return lastaccesstime;
    }

    public void setLastaccesstime(Date lastaccesstime) {
        this.lastaccesstime = lastaccesstime;
    }

    public Long getLogid() {
        return logid;
    }

    public void setLogid(Long logid) {
        this.logid = logid;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Date getLogouttime() {
        return logouttime;
    }

    public void setLogouttime(Date logouttime) {
        this.logouttime = logouttime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
