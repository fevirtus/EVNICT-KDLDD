/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.entity.shared.admin;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author abato
 */
@Entity
@Table(name = "Q_DEPT_GRANT_ROLE", uniqueConstraints = {})
public class Q_Dept_Grant_Role implements Serializable {
    // Fields

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "roleid", column =
        @Column(name = "ROLEID", nullable = false, length = 25)),
        @AttributeOverride(name = "deptid", column =
        @Column(name = "DEPTID", nullable = false, length = 25))})
    private Q_Dept_Grant_RolePK id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLEID", nullable = false, insertable = false, updatable = false)
    private Q_Role qrole;
    @Column(name = "UPDATE_PROBLEMID", nullable = true)
    private Boolean updateProblemid;
    @Column(name = "UPDATE_ASSETID", nullable = true)
    private Boolean updateAssetid;

    public Boolean getUpdateAssetid() {
        return updateAssetid;
    }

    public void setUpdateAssetid(Boolean updateAssetid) {
        this.updateAssetid = updateAssetid;
    }

    public Boolean getUpdateProblemid() {
        return updateProblemid;
    }

    public void setUpdateProblemid(Boolean updateProblemid) {
        this.updateProblemid = updateProblemid;
    }

    // Constructors
    /**
     * default constructor
     */
    public Q_Dept_Grant_Role() {
    }

    /**
     * full constructor
     */
    public Q_Dept_Grant_Role(Q_Dept_Grant_RolePK id) {
        this.id = id;
    }

    // Property accessors
    public Q_Dept_Grant_RolePK getId() {
        return this.id;
    }

    public void setId(Q_Dept_Grant_RolePK id) {
        this.id = id;
    }

    public void setQrole(Q_Role qrole) {
        this.qrole = qrole;
    }

    public Q_Role getQrole() {
        return qrole;
    }
}
