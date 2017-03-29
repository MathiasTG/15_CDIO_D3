package businessLayer;

import java.util.List;

import DTO.OperatoerDTO;
import exceptions.DALException;

public interface IBusinessLayer {

	OperatoerDTO getUser(int userId) throws DALException;
	List<OperatoerDTO> getUserList() throws DALException;
	void createUser(OperatoerDTO user) throws DALException;
	void updateUser(OperatoerDTO user) throws DALException;
	void deleteUser(int userId) throws DALException;
	
}
