package main.entity.shared.admin;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Khiemvk
 */

@Embeddable
public class Q_PqFunction_UserPK implements java.io.Serializable {

	// Fields
	@Column(name = "FUNCTIONID", nullable = false, length = 50)
	private String functionid;
	@Column(name = "USERID", nullable = false, length = 25)
	private String userid;

	// Constructors

	/** default constructor */
	public Q_PqFunction_UserPK() {
	}

	/** full constructor */
	public Q_PqFunction_UserPK(String functionid, String userid) {
		this.functionid = functionid;
		this.userid = userid;
	}

	// Property accessors

	public String getFunctionid() {
		return this.functionid;
	}

	public void setFunctionid(String functionid) {
		this.functionid = functionid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Q_PqFunction_UserPK))
			return false;
		Q_PqFunction_UserPK castOther = (Q_PqFunction_UserPK) other;

		return ((this.getFunctionid() == castOther.getFunctionid()) || (this
				.getFunctionid() != null
				&& castOther.getFunctionid() != null && this.getFunctionid()
				.equals(castOther.getFunctionid())))
				&& ((this.getUserid() == castOther.getUserid()) || (this
						.getUserid() != null
						&& castOther.getUserid() != null && this.getUserid()
						.equals(castOther.getUserid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getFunctionid() == null ? 0 : this.getFunctionid()
						.hashCode());
		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		return result;
	}

}