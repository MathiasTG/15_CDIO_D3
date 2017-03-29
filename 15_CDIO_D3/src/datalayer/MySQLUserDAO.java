package datalayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.OperatoerDTO;
import DTO.RoleDTO;
import exceptions.DALException;

public class OperatoerDAO implements IOperatoerDAO {

	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM alleOperatoere WHERE opr_id =" + oprId);
		List<RoleDTO> roles = new ArrayList<RoleDTO>();
		int id;
		String name, ini, cpr, password;

		try {
			if (!rs.first()) {
				throw new DALException("Operatoeren " + oprId + " findes ikke eller har ikke nogen roller");
			} else {
				id = rs.getInt("opr_id");
				name = rs.getString("opr_navn");
				ini = rs.getString("ini");
				cpr = rs.getString("cpr");
				password = rs.getString("password");
				roles.add(new RoleDTO(rs.getInt("role_id"), rs.getString("role")));
			}

			while (rs.next()) {
				roles.add(new RoleDTO(rs.getInt("role_id"), rs.getString("role")));
			}

			OperatoerDTO opr = new OperatoerDTO(id, name, ini, cpr, password, roles);

			return opr;
		} catch (SQLException e) {
			throw new DALException(e);
		}

	}

	public List<OperatoerDTO> getOperatoerList() throws DALException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>(1);
		int id;
		String name, ini, cpr, password;
		List<RoleDTO> roles = new ArrayList<RoleDTO>();

		ResultSet rs = Connector.doQuery("SELECT * FROM allUsers order by opr_id");
		int tempID = 0;
		try {
			while (rs.next()) {
				if (rs.getInt("opr_id") == tempID) {
					list.get(list.size() - 1).addRole(new RoleDTO(rs.getInt("role_id"), rs.getString("role")));
				} else {
					id = rs.getInt("opr_id");
					name = rs.getString("opr_navn");
					ini = rs.getString("ini");
					cpr = rs.getString("cpr");
					password = rs.getString("password");
					roles.add(new RoleDTO(rs.getInt("role_id"), rs.getString("role")));

					OperatoerDTO opr = new OperatoerDTO(id, name, ini, cpr, password, roles);

					list.add(opr);
					tempID = rs.getInt("opr_id");
				}
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	public void updateOperatoer(OperatoerDTO opr) throws DALException {

		Connector.doQuery("call updateUser(" + opr.getOprId() + ",'" + opr.getOprNavn() + "', '" + opr.getIni() + "','"
				+ opr.getCpr() + "', '" + opr.getPassword() + "'" + opr.getRoles().get(0));
		if (opr.getRoles().size() > 1) {
			for (int i = 1; i < opr.getRoles().size(); i++) {
				Connector.doQuery("CALL addRoles(" + opr.getOprId() + "," + opr.getRoles().get(i).getRoleId() + ")");
			}
		}
	}

	@Override
	public void createOperatoer(OperatoerDTO opr) throws DALException {
		Connector.doQuery("CALL createUser(" + opr.getOprId() + ",'" + opr.getOprNavn() + "', '" + opr.getIni() + "','"
				+ opr.getCpr() + "', '" + opr.getPassword() + "'" + opr.getRoles().get(0));
		if (opr.getRoles().size() > 1) {
			for (int i = 1; i < opr.getRoles().size(); i++) {
				Connector.doQuery("CALL addRoles(" + opr.getOprId() + "," + opr.getRoles().get(i).getRoleId() + ")");
			}
		}
	}

	@Override
	public void deleteOperatoer(int id) throws DALException {
		Connector.doQuery("CALL deleteUser(" + id + ")");
	}
}
