package interfacesDAO;

import java.util.List;

import DTO.UserDTO;
import exceptions.DALException;

public interface IUserDAO {
	UserDTO getUser(int oprId) throws DALException;
	List<UserDTO> getUserList() throws DALException;
	void createUser(UserDTO opr) throws DALException;
	void updateUser(UserDTO opr) throws DALException;
	void deleteUser(int id) throws DALException;
}
