package dalSerializable;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import DTO.RoleDTO;
import exceptions.DALException;
import interfacesDAO.IRoleDAO;

public class SerRoleDAO implements IRoleDAO{
	private List<RoleDTO> roles = new ArrayList<RoleDTO>();
	private final String pathName = "RoleDB.ser";

	@Override
	public RoleDTO getRole(int roleId) throws DALException {
		loadInfo();
		if (roles.size() == 0)
			throw new DALException("The database is empty.");
		for (int i = 0; i < roles.size(); i++) {
			if (roles.get(i).getRoleId() == roleId) {
				return roles.get(i);
			}
		}
		throw new DALException("No role has been found with id: " + roleId);
	}

	@Override
	public List<RoleDTO> getRoleList() throws DALException {
		loadInfo();
		if (roles.size() == 0)
			throw new DALException("There are no roles in the database.");
		return roles;
	}

	@Override
	public void createRole(RoleDTO role) throws DALException {

		loadInfo();

//		user.setPassword(pwg());

		if (roles.size() == 88) {
			throw new DALException("Database is full");
		}
//		checkUser(user);
		roles.add(role);
		saveInfo();
	}

	@Override
	public void updateRole(RoleDTO role) throws DALException {
		loadInfo();
		for (int i = 0; i < roles.size(); i++) {
			if (role.getRoleId() == roles.get(i).getRoleId()) {
				roles.remove(i);
				roles.add(role);
			}
		}
		saveInfo();	
	}

	@Override
	public void deleteRole(int roleId) throws DALException {
		loadInfo();
		boolean found = false;
		int index = 0;
		for (int i = 0; i < roles.size(); i++) {
			if (roleId == roles.get(i).getRoleId()) {
				found = true;
				index = i;
			}
		}
		if (found == true) {
			roles.remove(index);
			saveInfo();
		} else
			throw new DALException("No role was found with id: " + roleId);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void loadInfo() {

		try {
			InputStream file = new FileInputStream(pathName);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			roles = (ArrayList<RoleDTO>) input.readObject();
			if (roles.equals(null))
				roles = new ArrayList<RoleDTO>();
			input.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EOFException e) {
			roles = new ArrayList<RoleDTO>();
		} catch (StreamCorruptedException e) {
			System.out.println("The file is currupted.");
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void saveInfo() {
		try {
			OutputStream file = new FileOutputStream(pathName);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			// ObjectOutputStream oos = new ObjectOutputStream(new
			// FileOutputStream(new File("UserInfo.ser")));
			output.writeObject(roles);
			// close the writing.
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
