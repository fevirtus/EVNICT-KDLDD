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
@Embeddable
public class Q_Dept_Grant_UserPK implements Serializable {
    // Fields
	@Column(name = "USERID", nullable = false, length = 25)
	private String userid;
	@Column(name = "DEPTID", nullable = false, length = 25)
	private String deptid;

	// Constructors

	/** default constructor */
	public Q_Dept_Grant_UserPK() {
	}

	/** full constructor */
	public Q_Dept_Grant_UserPK(String userid, String deptid) {
		this.userid = userid;
		this.deptid = deptid;
	}

	// Property accessors

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDeptid() {
		return this.deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}



	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserid() == null ? 0 : this.getUserid().hashCode());
		result = 37 * result
				+ (getDeptid() == null ? 0 : this.getDeptid().hashCode());
		return result;
	}
    
}
