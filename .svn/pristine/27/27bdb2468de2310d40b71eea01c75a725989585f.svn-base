package main.entity.shared.admin;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author thaodd
 */
@Entity
@Table(name = "Q_FUNCTION")
public class Q_Function implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FUNCTIONID")
    private String functionid;
    @Basic(optional = false)
    @Column(name = "FUNCTIONNAME")
    private String functionname;
    @Column(name = "FUNCTION_PARENT_ID")
    private String functionparentid;
    @Column(name = "IS_LAST")
    private Boolean islast;
    @Basic(optional = false)
    @Column(name = "ENABLE")
    private boolean enable;
    @Column(name = "HAVING_UPDATE")
    private Boolean havingupdate;
    @Column(name = "URL")
    private String url;
    @Column(name = "URL_MOBILE")
    private String urlMobile;
    @Column(name = "ICON")
    private String icon;
    @Column(name = "ISAPP")
    private Boolean isapp;
    @Column(name = "ISPUPLIC")
    private Boolean isPUPLIC;
    @Column(name = "ISLOGIN")
    private Boolean isLogin;
    @Column(name = "ISMENU")
    private Boolean isMenu;
    @Column(name = "ISMOBILE")
    private Boolean isMobile;
    @JoinColumn(name = "FUNCTION_PARENT_ID", referencedColumnName = "FUNCTIONID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Q_Function sFuncidParent;

    @Column(name = "CALLFUNCTION")
    private String callFunction;
    @Column(name = "FUNCTIONORD")
    private int functionord;
    @Column(name = "ISFLOW")
    private Boolean isFlow;

    @Column(name = "ISEXT")
    private Boolean isExt;

    @Column(name = "ISEXTCHILD")
    private Boolean isExtChild;

    @Column(name = "URL_EXTCHILD")
    private String urlExtChild;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "AUTH_LOCAL")
    private Boolean authLocal;

    @Column(name = "AUTH_INTERNET")
    private Boolean authInternet;

    //Khai báo các trường liên quan phân quyền
    @Transient
    private Boolean grant;
    @Transient
    private Boolean rView;
    @Transient
    private Boolean rCreator;
    @Transient
    private Boolean rEdit;
    @Transient
    private Boolean rDelete;
    @Transient
    private int level;
    @Transient
    private Boolean bChecked;

    public Q_Function() {
    }

    public Q_Function(String functionid) {
        this.functionid = functionid;
    }

    public Q_Function(String functionid, String functionname, boolean enable) {
        this.functionid = functionid;
        this.functionname = functionname;
        this.enable = enable;
    }

    public String getFunctionid() {
        return functionid;
    }

    public void setFunctionid(String functionid) {
        this.functionid = functionid;
    }

    public String getFunctionname() {
        return functionname;
    }

    public void setFunctionname(String functionname) {
        this.functionname = functionname;
    }

    public String getFunctionparentid() {
        return functionparentid;
    }

    public void setFunctionparentid(String functionParentId) {
        this.functionparentid = functionParentId;
    }

    public Boolean getIslast() {
        return islast;
    }

    public void setIsLast(Boolean isLast) {
        this.islast = isLast;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Boolean getHavingupdate() {
        return havingupdate;
    }

    public void setHavingUpdate(Boolean havingUpdate) {
        this.havingupdate = havingUpdate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlMobile() {
        return urlMobile;
    }

    public void setUrlMobile(String urlMobile) {
        this.urlMobile = urlMobile;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getIsapp() {
        return isapp;
    }

    public void setIsapp(Boolean isapp) {
        this.isapp = isapp;
    }

    public Boolean getIsPUPLIC() {
        return isPUPLIC;
    }

    public void setIsPUPLIC(Boolean isPUPLIC) {
        this.isPUPLIC = isPUPLIC;
    }

    public Boolean getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Boolean isLogin) {
        this.isLogin = isLogin;
    }

    public Boolean getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Boolean isMenu) {
        this.isMenu = isMenu;
    }

    public Boolean getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(Boolean isMobile) {
        this.isMobile = isMobile;
    }

    public String getCallFunction() {
        return callFunction;
    }

    public void setCallFunction(String callFunction) {
        this.callFunction = callFunction;
    }

    public int getFunctionord() {
        return functionord;
    }

    public void setFunctionord(int functionord) {
        this.functionord = functionord;
    }

    public Boolean getrCreator() {
        return rCreator;
    }

    public void setrCreator(Boolean rCreator) {
        this.rCreator = rCreator;
    }

    public Boolean getrEdit() {
        return rEdit;
    }

    public void setrEdit(Boolean rEdit) {
        this.rEdit = rEdit;
    }

    public Boolean getrDelete() {
        return rDelete;
    }

    public void setrDelete(Boolean rDelete) {
        this.rDelete = rDelete;
    }

    public Boolean getIsFlow() {
        return isFlow;
    }

    public void setIsFlow(Boolean isFlow) {
        this.isFlow = isFlow;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getAuthLocal() {
        return authLocal;
    }

    public void setAuthLocal(Boolean authLocal) {
        this.authLocal = authLocal;
    }

    public Boolean getAuthInternet() {
        return authInternet;
    }

    public void setAuthInternet(Boolean authInternet) {
        this.authInternet = authInternet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (functionid != null ? functionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Q_Function)) {
            return false;
        }
        Q_Function other = (Q_Function) object;
        if ((this.functionid == null && other.functionid != null) || (this.functionid != null && !this.functionid.equals(other.functionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.entity.shared.admin.Q_Function[functionid=" + functionid + "]";
    }

    //Các trường thêm
    public Boolean getGrant() {
        return grant;
    }

    public void setGrant(Boolean grant) {
        this.grant = grant;
    }

    public Boolean getrView() {
        return rView;
    }

    public void setrView(Boolean rView) {
        this.rView = rView;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Boolean getbChecked() {
        return bChecked;
    }

    public void setbChecked(Boolean bChecked) {
        this.bChecked = bChecked;
    }

    public Q_Function getsFuncidParent() {
        return sFuncidParent;
    }

    public void setsFuncidParent(Q_Function sFuncidParent) {
        this.sFuncidParent = sFuncidParent;
    }

    public Boolean getIsExt() {
        return isExt;
    }

    public void setIsExt(Boolean isExt) {
        this.isExt = isExt;
    }

    public Boolean getIsExtChild() {
        return isExtChild;
    }

    public void setIsExtChild(Boolean isExtChild) {
        this.isExtChild = isExtChild;
    }

    public String getUrlExtChild() {
        return urlExtChild;
    }

    public void setUrlExtChild(String urlExtChild) {
        this.urlExtChild = urlExtChild;
    }

    /**
     * Hàm để hiển thị trên grid dưới dạng cây
     *
     * @return
     */
    public int getPaddingLeft() {
        int padding_left = 0;
        if (level >= 0) {
            padding_left = 10 + level * 20;
        }
        return padding_left < 0 ? 0 : padding_left;
    }
}
