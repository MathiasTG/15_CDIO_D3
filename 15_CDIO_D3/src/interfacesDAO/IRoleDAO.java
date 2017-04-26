package interfacesDAO;

import java.util.List;


import DTO.RoleDTO;
import exceptions.DALException;

public interface IRoleDAO {
	
	RoleDTO getRole(int roleId) throws DALException;
	List<RoleDTO> getRoleList() throws DALException;
	void createRole(RoleDTO role) throws DALException;
	void updateRole(RoleDTO role) throws DALException;
	void deleteRole(int roleId) throws DALException;
}
