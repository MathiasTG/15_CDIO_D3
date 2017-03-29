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

		ResultSet rs = Connector.doQuery("SELECT * FROM alleOperatoere order by opr_id");
		List<OperatoerDTO> oprList = new ArrayList<OperatoerDTO>();
		List<RoleDTO> roles = new ArrayList<RoleDTO>();
		int id;
		String name, ini, cpr, password;

		int tempId=0;

		while (rs.next()) {

			if (tempId != rs.getInt("opr_id") && tempId!= 0) {

				OperatoerDTO opr = new OperatoerDTO(id, name, ini, cpr, password, roles);

				oprList.add(opr);
				
				oprList == null; 
				
				id = rs.getInt("opr_id");
				name = rs.getString("opr_navn");
				ini = rs.getString("ini");
				cpr = rs.getString("cpr");
				password = rs.getString("password");
				roles.add(new RoleDTO(rs.getInt("role_id"), rs.getString("role")));

				tempId = opr.getOprId();


			}else if(tempId == rs.getInt("opr_id")){
				roles.add(new RoleDTO(rs.getInt("role_id"), rs.getString("role")));
			}

			else {

				try {

					id = rs.getInt("opr_id");
					name = rs.getString("opr_navn");
					ini = rs.getString("ini");
					cpr = rs.getString("cpr");
					password = rs.getString("password");
					roles.add(new RoleDTO(rs.getInt("role_id"), rs.getString("role")));

					tempId = opr.getOprId();

				} catch (SQLException e) {
					throw new DALException(e);
				}

			}

		}

	}

	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		List<String> rigtigeRoller = new ArrayList<String>();
		List<String> insertKo = new ArrayList<String>();
		for (int i = 0; i < opr.getRoles().size(); i++) {
			rigtigeRoller.add(opr.getRoles().get(i));
			insertKo.add(opr.getRoles().get(i));
		}
		List<String> deleteKo = new ArrayList<String>(0);
		Connector.doUpdate(
				"UPDATE operatoer SET  opr_navn = '" + opr.getOprNavn() + "', ini =  '" + opr.getIni() + "', cpr = '"
						+ opr.getCpr() + "', password = '" + opr.getPassword() + "' WHERE opr_id = " + opr.getOprId());
		ResultSet rs = Connector.doQuery("SELECT * FROM roles WHERE opr_id=" + opr.getOprId());
		try {
			rs.first();
			for (int i = 0; i < rigtigeRoller.size(); i++) {
				if (!(rigtigeRoller.get(i).contains(rs.getString("role")))) {
					deleteKo.add(rs.getString("role"));
				} else {
					insertKo.remove(rs.getString("role"));
				}
			}
			while (rs.next()) {
				for (int i = 0; i < rigtigeRoller.size(); i++) {
					if (!(rigtigeRoller.get(i).contains(rs.getString("role")))) {
						deleteKo.add(rs.getString("role"));
					} else {
						insertKo.remove(rs.getString("role"));
					}
				}
			}
			if (!deleteKo.isEmpty()) {
				for (int i = 0; i < deleteKo.size(); i++) {
					Connector.doUpdate(
							"DELETE FROM roles WHERE opr_id=" + opr.getOprId() + " and role='" + deleteKo.get(i) + "'");
				}
			}
			if (!insertKo.isEmpty()) {
				for (int i = 0; i < insertKo.size(); i++) {
					Connector.doUpdate("INSERT INTO roles(opr_id,role) VALUES (" + opr.getOprId() + ",'"
							+ opr.getRoles().get(i) + "')");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DALException(e);
		}
	}

	@Override
	public void createOperatoer(OperatoerDTO opr) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteOperatoer(int id) throws DALException {
		// TODO Auto-generated method stub

	}
}
