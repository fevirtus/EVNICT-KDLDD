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
@Table(name = "Q_DEPT_GRANT_USER", uniqueConstraints = {})
public class Q_Dept_Grant_User implements Serializable {
    // Fields

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "userid", column =
        @Column(name = "USERID", nullable = false, length = 25)),
        @AttributeOverride(name = "deptid", column =
        @Column(name = "DEPTID", nullable = false, length = 25))})
    private Q_Dept_Grant_UserPK id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID", nullable = false, insertable = false, updatable = false)
    private Q_User quser;
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
    public Q_Dept_Grant_User() {
    }

    /**
     * full constructor
     */
    public Q_Dept_Grant_User(Q_Dept_Grant_UserPK id) {
        this.id = id;
    }

    // Property accessors
    public Q_Dept_Grant_UserPK getId() {
        return this.id;
    }

    public void setId(Q_Dept_Grant_UserPK id) {
        this.id = id;
    }

    public void setQuser(Q_User quser) {
        this.quser = quser;
    }

    public Q_User getQuser() {
        return quser;
    }
}
