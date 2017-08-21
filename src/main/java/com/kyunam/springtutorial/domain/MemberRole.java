package com.kyunam.springtutorial.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class MemberRole {
	@Id
	@GeneratedValue
	private Long uno;

	private String roleName;

	public Long getUno() {
		return uno;
	}

	public void setUno(Long uno) {
		this.uno = uno;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "MemberRole [uno=" + uno + ", roleName=" + roleName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
		result = prime * result + ((uno == null) ? 0 : uno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberRole other = (MemberRole) obj;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		if (uno == null) {
			if (other.uno != null)
				return false;
		} else if (!uno.equals(other.uno))
			return false;
		return true;
	}
	
	
}
