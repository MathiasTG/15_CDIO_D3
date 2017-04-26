
package businessLayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import DTO.UserDTO;
import interfacesDAO.IRoleDAO;
import interfacesDAO.IUserDAO;
import DTO.RoleDTO;
import exceptions.DALException;


public class BusinessLayer implements IBusinessLayer, IRoleDAO {

	private IUserDAO userDAO;
	private IRoleDAO roleDAO;
	
	@Override 
	public UserDTO getUser(int userId) throws DALException {
		return userDAO.getUser(userId);
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		return userDAO.getUserList();
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		String cpr = user.getCpr();
		Date date;
	
		try {									//checking up on CPR
			String[] parts = cpr.split("-");
			String dateNumber = parts[0]; //
			String number = parts[1]; //
			
			if (dateNumber.length() == 6 && number.length() == 4) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
					date = sdf.parse(dateNumber);
					if (!dateNumber.equals(sdf.format(date))) {
						throw new DALException("invalid date");
					} else {
						userDAO.createUser(user);
					}
				} catch (ParseException ex) {
					throw new DALException("parsing error");
				}

			} else
				throw new DALException("Invalid CPR length");

		} catch (ArrayIndexOutOfBoundsException e) {
			throw new DALException("Invalid CPR from (missing -)");
		}
	}
	

	@Override
	public void updateUser(UserDTO user) throws DALException {
		userDAO.updateUser(user);

	}

	@Override
	public void deleteUser(int userId) throws DALException {
		userDAO.deleteUser(userId);

	}

	@Override
	public RoleDTO getRole(int roleId) throws DALException {
		// TODO Auto-generated method stub
		return roleDAO.getRole(roleId);
	}

	@Override
	public List<RoleDTO> getRoleList() throws DALException {
		// TODO Auto-generated method stub
		return roleDAO.getRoleList();
	}

	@Override
	public void createRole(RoleDTO role) throws DALException {
		roleDAO.createRole(role);
		
	}

	@Override
	public void updateRole(RoleDTO role) throws DALException {
		roleDAO.updateRole(role);
		
	}

	@Override
	public void deleteRole(int roleId) throws DALException {
		roleDAO.deleteRole(roleId);
		
	}

}
