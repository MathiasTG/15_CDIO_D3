package datalayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.RoleDTO;
import exceptions.DALException;

public class MySQLRoleDAO implements IRoleDAO {

	@Override
	public RoleDTO getRole(int roleId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT role FROM roles WHERE role_id=" + roleId);
		try {
			if (!rs.first()) {
				throw new DALException("Role " + roleId + " findes ikke.");
			}
			rs.first();
			RoleDTO r = new RoleDTO(roleId, rs.getString("role"));

			return r;
		} catch (SQLException e) {
			throw new DALException(e);
		}

	}

	@Override
	public List<RoleDTO> getRoleList() throws DALException {

		List<RoleDTO> roles = new ArrayList<RoleDTO>();

		ResultSet rs = Connector.doQuery("SELECT * FROM roles");

		try {
			while (rs.next()) {
				int id = rs.getInt("role_id");
				String name = rs.getString("role");

				RoleDTO r = new RoleDTO(id, name);
				roles.add(r);

			}
		} catch (SQLException e) {
			throw new DALException("SQLException");
		}

		return roles;
	}

	@Override
	public void createRole(RoleDTO role) throws DALException {
		Connector.doUpdate(
				"INSERT INTO roles(role_id,role) VALUES " + "(" + role.getRoleId() + ",'" + role.getRoleName() + "')");

	}

	@Override
	public void updateRole(RoleDTO role) throws DALException {
		Connector.doUpdate(
				"UPDATE roles set role = '"+role.getRoleName()+"' WHERE role_id="+role.getRoleId());
		
	}

	@Override
	public void deleteRole(RoleDTO role) throws DALException {
		Connector.doUpdate("DELETE from roles WHERE role_id ="+role.getRoleId()); 
	}

}
