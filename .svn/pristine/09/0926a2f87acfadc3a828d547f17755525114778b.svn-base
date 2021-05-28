package main.entity.shared.admin;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author Khiemvk
 */
@Entity
public class Q_User_Ext implements java.io.Serializable {

    // Fields
    @Id
    @Column(name = "USERID", unique = true, nullable = false, length = 25)
    private String userid;
    @Column(name = "USERNAME", nullable = false, length = 100)
    private String username;
    @Column(name = "PASSWORD", length = 100)
    private String password;
    @Column(name = "EMAIL", length = 50)
    private String email;
    @Column(name = "TEL", length = 50)
    private String tel;
    @Column(name = "DESCRIPT", length = 200)
    private String descript;
    @Column(name = "ENABLE", nullable = false)
    private Boolean enable;
    @Transient
    private Boolean bChecked;

    // Constructors
    /** default constructor */
    public Q_User_Ext() {
    }

    /** minimal constructor */
    public Q_User_Ext(String userid, String username, Boolean enable) {
        this.userid = userid;
        this.username = username;
        this.enable = enable;
    }

    /** full constructor */
    public Q_User_Ext(String userid, String username, String password,
            String email, String tel, String descript, Boolean enable) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.descript = descript;
        this.enable = enable;
    }

    // Property accessors
    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDescript() {
        return this.descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Boolean getEnable() {
        return this.enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
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

    //Các trường thêm thống kê đọc thông báo
    @Column(name = "BBLOGREADDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bblogreaddtime;

    @Column(name = "BBLOGISUPDATE")
    private Boolean bblogisupdate;

    public Boolean getBblogisupdate() {
        return bblogisupdate;
    }

    public void setBblogisupdate(Boolean bblogisupdate) {
        this.bblogisupdate = bblogisupdate;
    }

    public Date getBblogreaddtime() {
        return bblogreaddtime;
    }

    public void setBblogreaddtime(Date bblogreaddtime) {
        this.bblogreaddtime = bblogreaddtime;
    }

    
}
