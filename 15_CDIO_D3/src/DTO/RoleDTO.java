package DTO;

public class RoleDTO {

	private int roleId;
	private String roleName;

	RoleDTO(int roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}
	
	public void setRoleId(int id){
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
