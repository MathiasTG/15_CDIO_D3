package dalSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.RoleDTO;
import exceptions.DALException;
import interfacesDAO.IRoleDAO;

public class MySQLRoleDAO implements IRoleDAO {

	@Override
	public RoleDTO getRole(int roleId) throws DALException {
		ResultSet rs = Connector.doQuery(SQLMapper.getStatement(3)+ roleId);
		try {
			if (!rs.first()) {
				throw new DALException("Role " + roleId + " not found");
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

		ResultSet rs = Connector.doQuery(SQLMapper.getStatement(4));

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
				SQLMapper.getStatement(5)+ "(" + role.getRoleId() + ",'" + role.getRoleName() + "')");

	}

	@Override
	public void updateRole(RoleDTO role) throws DALException {
		Connector.doUpdate(
				SQLMapper.getStatement(6)+role.getRoleName()+"SQLMapper.getStatement(7)+"+role.getRoleId());
		
	}

	@Override
	public void deleteRole(RoleDTO role) throws DALException {
		Connector.doUpdate(SQLMapper.getStatement(8)+role.getRoleId()); 
	}

}
