package dalSerializable;

import java.util.List;

import DTO.UserDTO;
import exceptions.DALException;
import interfacesDAO.IUserDAO;

public class SerUserDAO implements IUserDAO{
	
	
	@Override
	public UserDTO getUser(int oprId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createUser(UserDTO opr) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(UserDTO opr) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
