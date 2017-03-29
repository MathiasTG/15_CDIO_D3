package businessLayer;

import java.util.List;

import DTO.UserDTO;
import exceptions.DALException;

public interface IBusinessLayer {

	UserDTO getUser(int userId) throws DALException;
	List<UserDTO> getUserList() throws DALException;
	void createUser(UserDTO user) throws DALException;
	void updateUser(UserDTO user) throws DALException;
	void deleteUser(int userId) throws DALException;
	
}
