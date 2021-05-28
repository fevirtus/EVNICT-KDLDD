package main.entity.shared.admin;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Khiemvk
 */
@Entity
@Table(name = "Q_PQFUNCTION_USER", uniqueConstraints = {})
public class Q_PqFunction_User implements java.io.Serializable {

    // Fields
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "functionid", column = @Column(name = "FUNCTIONID", nullable = false, length = 50)),
        @AttributeOverride(name = "userid", column = @Column(name = "USERID", nullable = false, length = 25))})
    private Q_PqFunction_UserPK id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID", nullable = false, insertable = false, updatable = false)
    private Q_User quser;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUNCTIONID", nullable = false, insertable = false, updatable = false)
    private Q_Function qfunction;
    @Column(name = "R_VIEW", nullable = true)
    private Boolean rView;
    @Column(name = "R_CREATOR", nullable = true)
    private Boolean rCreator;
    @Column(name = "R_EDIT", nullable = true)
    private Boolean rEdit;
    @Column(name = "R_DELETE", nullable = true)
    private Boolean rDelete;

    @Column(name = "AUTH_LOCAL", nullable = true)
    private Boolean authLocal;
    @Column(name = "AUTH_INTERNET", nullable = true)
    private Boolean authInternet;
    // Constructors

    /**
     * default constructor
     */
    public Q_PqFunction_User() {
    }

    /**
     * full constructor
     */
    public Q_PqFunction_User(Q_PqFunction_UserPK id) {
        this.id = id;
    }

    // Property accessors
    public Q_PqFunction_UserPK getId() {
        return this.id;
    }

    public void setId(Q_PqFunction_UserPK id) {
        this.id = id;
    }

    public void setQuser(Q_User quser) {
        this.quser = quser;
    }

    public Q_User getQuser() {
        return quser;
    }

    public void setQfunction(Q_Function qfunction) {
        this.qfunction = qfunction;
    }

    public Q_Function getQfunction() {
        return qfunction;
    }

    public Boolean getrView() {
        return rView;
    }

    public void setrView(Boolean rView) {
        this.rView = rView;
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

}
