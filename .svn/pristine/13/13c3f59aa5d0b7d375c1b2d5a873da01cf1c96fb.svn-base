package main.entity.shared.admin;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Khiemvk
 */

@Embeddable
public class Q_User_RolePK implements java.io.Serializable {

	// Fields
	@Column(name = "USERID", nullable = false, length = 25)
	private String userid;
	@Column(name = "ROLEID", nullable = false, length = 25)
	private String roleid;

	// Constructors

	/** default constructor */
	public Q_User_RolePK() {
	}

	/** full constructor */
	public Q_User_RolePK(String userid, String roleid) {
		this.userid = userid;
		this.roleid = roleid;
	}

	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRoleid() {
		return this.roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Q_User_RolePK))
			return false;
		Q_User_RolePK castOther = (Q_User_RolePK) other;

		return ((this.getUserid() == castOther.getUserid()) || (this
				.getUserid() != null
				&& castOther.getUserid() != null && this.getUserid().equals(
				castOther.getUserid())))
				&& ((this.getRoleid() == castOther.getRoleid()) || (this
						.getRoleid() != null
						&& castOther.getRoleid() != null && this.getRoleid()
						.equals(castOther.getRoleid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getRoleid() == null ? 0 : this.getRoleid().hashCode());
		return result;
	}

        public String getIdStr()
        {
            return userid + roleid;
        }
}