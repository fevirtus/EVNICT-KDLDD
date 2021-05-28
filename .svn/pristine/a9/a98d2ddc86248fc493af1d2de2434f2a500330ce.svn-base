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
public class Q_Dept_Grant_RolePK implements Serializable {
    // Fields
	@Column(name = "ROLEID", nullable = false, length = 25)
	private String roleid;
	@Column(name = "DEPTID", nullable = false, length = 25)
	private String deptid;

	// Constructors

	/** default constructor */
	public Q_Dept_Grant_RolePK() {
	}

	/** full constructor */
	public Q_Dept_Grant_RolePK(String roleid, String deptid) {
		this.roleid = roleid;
		this.deptid = deptid;
	}

	// Property accessors

	public String getRoleid() {
		return this.roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

        public String getDeptid() {
            return deptid;
        }

        public void setDeptid(String deptid) {
            this.deptid = deptid;
        }

	

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRoleid() == null ? 0 : this.getRoleid().hashCode());
		result = 37 * result
				+ (getDeptid() == null ? 0 : this.getDeptid().hashCode());
		return result;
	}

}
