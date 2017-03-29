package DTO;

import java.util.List;

/**
 * Operatoer Data Access Objekt
 * 
 * @author mn/tb
 * @version 1.2
 */

public class UserDTO {
	/**
	 * Bruger-identifikationsnummer (user_id) i omraadet 11-99. Vaelges
	 * af brugerne
	 */
	private int userId;
	/** Bruger navn (user_name) min. 2 max. 20 karakterer */
	private String userName;
	/** Bruger-initialer min. 2 max. 3 karakterer */
	private String ini;
	/** Brugers cpr-nr 10 karakterer */
	private String cpr;
	/** Bruger password min. 7 max. 8 karakterer */
	private String password;
	private List<RoleDTO> roles;

	public UserDTO(int userId, String userName, String ini, String cpr, String password, List<RoleDTO> roles) {
		this.userId = userId;
		this.userName = userName;
		this.ini = ini;
		this.cpr = cpr;
		this.password = password;
		this.roles = roles;
	}

//	public UserDTO(UserDTO user) {
//		this.userId = user.getUserId();
//		this.userName = user.getUserName();
//		this.ini = user.getIni();
//		this.cpr = user.getCpr();
//		this.password = user.getPassword();
//		this.roles = user.getRoles();
//	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIni() {
		return ini;
	}

	public void setIni(String ini) {
		this.ini = ini;
	}

	public String getCpr() {
		return cpr;
	}

	public void setCpr(String cpr) {
		this.cpr = cpr;
	}

	public String getPassword() {
		return password;
	}

	public void addRole(RoleDTO role) {
		this.roles.add(role);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public String toString() {
		String roleString="["+roles.get(0).getRoleName();
		if(roles.size()>1){
			for(int i=1;i<roles.size();i++){
				roleString=roleString+", "+roles.get(i).getRoleName();
			}
		}
		roleString=roleString+"]";
		return "User ID:	" + userId + "\n" + "Username:	" + userName + "\n" + "Initials:	" + ini + "\n"
				+ "Role(s):\t" + roleString + "\n" + "CPR:		" + cpr + "\n" + "Password:	" + password + "\n \n";
	}
}
