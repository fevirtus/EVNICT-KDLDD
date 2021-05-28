package main.entity.shared.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.persistence.Basic;

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
@Table(name = "Q_USER", uniqueConstraints = {})
public class Q_User implements Serializable {

    private static final long serialVersionUID = 1L;
    // Fields
    @Id
    @Basic(optional = false)
    @Column(name = "USERID")
    private String userid;
    @Column(name = "USERID_DOMAIN")
    private String useridDomain;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "TEL")
    private String tel;
    @Column(name = "DESCRIPT")
    private String descript;
    @Column(name = "ENABLE")
    private Boolean enable;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LASTACCESSTIME")
    private Date lastaccesstime;
    @Column(name = "ENABLEAUTOMAIL")
    private Boolean enableautomail;
    @Column(name = "ENABLEAUTOMAILREPORT")
    private Boolean enableautomailreport;
    @Column(name = "CHANNEL")
    private String channel;
    @Column(name = "ISROOT")
    private Boolean isRoot;
    @Column(name = "ISCUSTOMER")
    private Boolean iscustomer;
    @Column(name = "GROUPUSERID")
    private String groupuserid;
    @Column(name = "URL_DEFAULT")
    private String urlDefault;
    @Transient
    private Boolean bChecked;
    //Join - Cẩn thận với cascade
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "quser")
    private List<Q_User_Role> user_roles = new Vector<Q_User_Role>();
    @Transient
    private int countLogInWeek;
    @Transient
    private int countLogInMonth;
    @Transient
    private String lstDept;
    @Column(name = "AUTHEN_TYPE")
    private Boolean authenType;

    // Constructors
    /**
     * default constructor
     */
    public Q_User() {
    }

    /**
     * minimal constructor
     */
    public Q_User(String userid, String username, Boolean enable) {
        this.userid = userid;
        this.username = username;
        this.enable = enable;
    }

    /**
     * full constructor
     *
     * @param userid
     */
    public Q_User(String userid, String username, String password,
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

    public String getUseridDomain() {
        return useridDomain;
    }

    public void setUseridDomain(String useridDomain) {
        this.useridDomain = useridDomain;
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

    public void setLastaccesstime(Date lastaccesstime) {
        this.lastaccesstime = lastaccesstime;
    }

    public Date getLastaccesstime() {
        return lastaccesstime;
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

    public int getCountLogInMonth() {
        return countLogInMonth;
    }

    public void setCountLogInMonth(int countLogInMonth) {
        this.countLogInMonth = countLogInMonth;
    }

    public int getCountLogInWeek() {
        return countLogInWeek;
    }

    public void setCountLogInWeek(int countLogInWeek) {
        this.countLogInWeek = countLogInWeek;
    }

    public String getLstDept() {
        return lstDept;
    }

    public void setLstDept(String lstDept) {
        this.lstDept = lstDept;
    }

    /*
     * Hàm ghép userid - username
     */
    public String getUserIDName() {
        String s;
        s = getUserid();
        if (getUsername() == null || getUsername().isEmpty()) {
            return s;
        }

        return s + " - " + getUsername();
    }

    public Boolean getEnableautomail() {
        return enableautomail;
    }

    public void setEnableautomail(Boolean enableautomail) {
        this.enableautomail = enableautomail;
    }

    public Boolean getEnableautomailreport() {
        return enableautomailreport;
    }

    public void setEnableautomailreport(Boolean enableautomailreport) {
        this.enableautomailreport = enableautomailreport;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Boolean getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(Boolean isRoot) {
        this.isRoot = isRoot;
    }

    public Boolean getIscustomer() {
        return iscustomer;
    }

    public void setIscustomer(Boolean iscustomer) {
        this.iscustomer = iscustomer;
    }

    
    
    
    public String getGroupuserid() {
        return groupuserid;
    }

    public void setGroupuserid(String groupuserid) {
        this.groupuserid = groupuserid;
    }

    public String getUrlDefault() {
        return urlDefault;
    }

    public void setUrlDefault(String urlDefault) {
        this.urlDefault = urlDefault;
    }

    public Boolean getAuthenType() {
        return authenType;
    }

    public void setAuthenType(Boolean authenType) {
        this.authenType = authenType;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Q_User)) {
            return false;
        }
        Q_User other = (Q_User) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }
}
