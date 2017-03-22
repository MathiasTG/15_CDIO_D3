package datalayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import exceptions.DALException;

public class OperatoerDAO implements IOperatoerDAO {

	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM operatoer natural join roles WHERE opr_id = " + oprId);
		try {
			if (!rs.first())
				throw new DALException("Operatoeren " + oprId + " findes ikke");
			OperatoerDTO t = new OperatoerDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"),
					rs.getString("cpr"), rs.getString("password"), new ArrayList<String>());
			t.addRole(rs.getString("role"));
			while (rs.next()) {
				t.addRole(rs.getString("role"));
			}
			return t;
		} catch (SQLException e) {
			throw new DALException(e);
		}

	}

	public void createOperatoer(OperatoerDTO opr) throws DALException {
		Connector.doUpdate("INSERT INTO operatoer(opr_id, opr_navn, ini, cpr, password) VALUES " + "(" + opr.getOprId()
				+ ", '" + opr.getOprNavn() + "', '" + opr.getIni() + "', '" + opr.getCpr() + "', '" + opr.getPassword()
				+ "')");
		for (int i = 0; i < opr.getRoles().size(); i++) {
			Connector.doUpdate(
					"INSERT INTO roles(opr_id,role) VALUES (" + opr.getOprId() + ",'" + opr.getRoles().get(i) + "')");
		}
	}

	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		List<String> rigtigeRoller = opr.getRoles();
		List<String> insertKo = opr.getRoles();
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
			if(!deleteKo.isEmpty()){
				for(int i=0;i<deleteKo.size();i++){
					Connector.doUpdate("DELETE FROM ROLE WHERE opr_id="+opr.getOprId()+" and role='"+deleteKo.get(i)+"'");
				}
			}
			if(!insertKo.isEmpty()){
				for(int i=0;i<insertKo.size();i++){
					Connector.doUpdate(
							"INSERT INTO roles(opr_id,role) VALUES (" + opr.getOprId() + ",'" + opr.getRoles().get(i) + "')");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DALException(e);
		}
	}

	public List<OperatoerDTO> getOperatoerList() throws DALException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>(1);
		ResultSet rs = Connector.doQuery("SELECT * FROM operatoer natural join roles order by opr_id");
		int tempID = 0;
		try {
			while (rs.next()) {
				if (rs.getInt("opr_id") == tempID) {
					list.get(list.size() - 1).addRole(rs.getString("role"));
				} else {
					OperatoerDTO t = new OperatoerDTO(rs.getInt("opr_id"), rs.getString("opr_navn"),
							rs.getString("ini"), rs.getString("cpr"), rs.getString("password"),
							new ArrayList<String>());
					t.addRole(rs.getString("role"));
					list.add(t);
					tempID = rs.getInt("opr_id");
				}
			}
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return list;
	}
}
