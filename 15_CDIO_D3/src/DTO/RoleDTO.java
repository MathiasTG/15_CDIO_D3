package DTO;

import java.io.Serializable;

public class RoleDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9037583273597740464L;
	private int roleId;
	private String roleName;

	public RoleDTO(int roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public void setRoleId(int id) {
		roleId = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleId() {
		return roleId;
	}

}
