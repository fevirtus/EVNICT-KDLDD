package main.entity.shared.admin;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Khiemvk
 */

@Embeddable
public class Q_Pqfunction_RolePK implements java.io.Serializable {

	// Fields
	@Column(name = "FUNCTIONID", nullable = false, length = 50)
	private String functionid;
	@Column(name = "ROLEID", nullable = false, length = 25)
	private String roleid;

	// Constructors

	/** default constructor */
	public Q_Pqfunction_RolePK() {
	}

	/** full constructor */
	public Q_Pqfunction_RolePK(String functionid, String roleid) {
		this.functionid = functionid;
		this.roleid = roleid;
	}

	// Property accessors

	public String getFunctionid() {
		return this.functionid;
	}

	public void setFunctionid(String functionid) {
		this.functionid = functionid;
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
		if (!(other instanceof Q_Pqfunction_RolePK))
			return false;
		Q_Pqfunction_RolePK castOther = (Q_Pqfunction_RolePK) other;

		return ((this.getFunctionid() == castOther.getFunctionid()) || (this
				.getFunctionid() != null
				&& castOther.getFunctionid() != null && this.getFunctionid()
				.equals(castOther.getFunctionid())))
				&& ((this.getRoleid() == castOther.getRoleid()) || (this
						.getRoleid() != null
						&& castOther.getRoleid() != null && this.getRoleid()
						.equals(castOther.getRoleid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getFunctionid() == null ? 0 : this.getFunctionid()
						.hashCode());
		result = 37 * result
				+ (getRoleid() == null ? 0 : this.getRoleid().hashCode());
		return result;
	}

}