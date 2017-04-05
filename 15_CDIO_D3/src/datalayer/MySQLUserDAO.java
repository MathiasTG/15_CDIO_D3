package datalayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.UserDTO;
import DTO.RoleDTO;
import exceptions.DALException;

public class MySQLUserDAO implements IUserDAO {

	public UserDTO getUser(int userId) throws DALException {
		ResultSet rs = Connector.doQuery(SQLMapper.getStatement(2) + userId);
		List<RoleDTO> roles = new ArrayList<RoleDTO>();
		int id;
		String name, ini, cpr, password;

		try {
			if (!rs.first()) {
				throw new DALException("Bruger " + userId + " findes ikke eller har ikke nogen roller");
			} else {
				id = rs.getInt("user_id");
				name = rs.getString("user_navn");
				ini = rs.getString("ini");
				cpr = rs.getString("cpr");
				password = rs.getString("password");
				roles.add(new RoleDTO(rs.getInt("role_id"), rs.getString("role")));
			}

			while (rs.next()) {
				roles.add(new RoleDTO(rs.getInt("role_id"), rs.getString("role")));
			}

			UserDTO opr = new UserDTO(id, name, ini, cpr, password, roles);

			return opr;
		} catch (SQLException e) {
			throw new DALException(e);
		}

	}

	public List<UserDTO> getUserList() throws DALException {
		List<UserDTO> list = new ArrayList<UserDTO>(1);
		int id;
		String name, ini, cpr, password;
		List<RoleDTO> roles = new ArrayList<RoleDTO>();

		ResultSet rs = Connector.doQuery(SQLMapper.getStatement(1));
		int tempID = 0;
		try {
			while (rs.next()) {
				if (rs.getInt("user_id") == tempID) {
					list.get(list.size() - 1).addRole(new RoleDTO(rs.getInt("role_id"), rs.getString("role")));
				} else {
					id = rs.getInt("user_id");
					name = rs.getString("user_navn");
					ini = rs.getString("ini");
					cpr = rs.getString("cpr");
					password = rs.getString("password");
					roles.add(new RoleDTO(rs.getInt("role_id"), rs.getString("role")));

					UserDTO opr = new UserDTO(id, name, ini, cpr, password, roles);

					list.add(opr);
					tempID = rs.getInt("opr_id");
				}
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	public void updateUser(UserDTO user) throws DALException {

		Connector.doQuery(SQLMapper.getStatement(9)+"(" + user.getUserId() + ",'" + user.getUserName() + "', '" + user.getIni() + "','"
				+ user.getCpr() + "', '" + user.getPassword() + "'," + user.getRoles().get(0).getRoleId()+")");
		if (user.getRoles().size() > 1) {
			for (int i = 1; i < user.getRoles().size(); i++) {
				Connector.doQuery(SQLMapper.getStatement(10)+"(" + user.getUserId() + "," + user.getRoles().get(i).getRoleId() + ")");
			}
		}
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		Connector.doQuery(SQLMapper.getStatement(11)+"(" + user.getUserId() + ", '" + user.getUserName() + "', '" + user.getIni() + "', '"
				+ user.getCpr() + "', '" + user.getPassword() + "', " + user.getRoles().get(0).getRoleId()+")");
		if (user.getRoles().size() > 1) {
			for (int i = 1; i < user.getRoles().size(); i++) {
				Connector.doQuery(SQLMapper.getStatement(10)+"(" + user.getUserId() + "," + user.getRoles().get(i).getRoleId() + ")");
			}
		}
	}

	@Override
	public void deleteUser(int id) throws DALException {
		Connector.doQuery(SQLMapper.getStatement(12)+"(" + id + ")");
	}
}
