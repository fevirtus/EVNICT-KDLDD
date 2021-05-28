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
@Table(name = "Q_USER_ROLE", uniqueConstraints = {})
public class Q_User_Role implements java.io.Serializable {

	// Fields
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "userid", column = @Column(name = "USERID", nullable = false, length = 25)),
			@AttributeOverride(name = "roleid", column = @Column(name = "ROLEID", nullable = false, length = 25)) })
	private Q_User_RolePK id;
                        
	@Column(name = "Enable")
	private Boolean enable;

	@JoinColumn(name = "USERID", referencedColumnName = "USERID", unique = false, insertable = false, updatable = false, nullable = true)
        @ManyToOne(optional = false)
	private Q_User quser;

        @JoinColumn(name = "ROLEID", referencedColumnName = "ROLEID", unique = false, insertable = false, updatable = false, nullable = true)
        @ManyToOne(optional = false)
	private Q_Role qrole;

	// Constructors

	/** default constructor */
	public Q_User_Role() {
	}

	/** minimal constructor */
	public Q_User_Role(Q_User_RolePK id) {
		this.id = id;
	}

	/** full constructor */
	public Q_User_Role(Q_User_RolePK id, boolean enable) {
		this.id = id;
		this.enable = enable;
	}
	
	// Property accessors

	public Q_User_RolePK getId() {
		return this.id;
	}

	public void setId(Q_User_RolePK id) {
		this.id = id;
	}

	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public void setQuser(Q_User quser) {
		this.quser = quser;
	}

	public Q_User getQuser() {
		return quser;
	}

	public void setQrole(Q_Role qrole) {
		this.qrole = qrole;
	}

	public Q_Role getQrole() {
		return qrole;
	}

}