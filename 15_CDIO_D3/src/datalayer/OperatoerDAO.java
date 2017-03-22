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
	    	if (!rs.first()) throw new DALException("Operatoeren " + oprId + " findes ikke");
	    	OperatoerDTO t=new OperatoerDTO (rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), 
	    			new ArrayList<String>());
	    	t.addRole(rs.getString("role"));
	    	while(rs.next()){
	    		t.addRole(rs.getString("role"));
	    	}
	    	return t;
	    }
	    catch (SQLException e) {throw new DALException(e); }
		
	}
	
	public void createOperatoer(OperatoerDTO opr) throws DALException {		
			Connector.doUpdate(
				"INSERT INTO operatoer(opr_id, opr_navn, ini, cpr, password) VALUES " +
				"(" + opr.getOprId() + ", '" + opr.getOprNavn() + "', '" + opr.getIni() + "', '" + 
				opr.getCpr() + "', '" + opr.getPassword() + "')"
			);
	}
	
	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		Connector.doUpdate(
				"UPDATE operatoer SET  opr_navn = '" + opr.getOprNavn() + "', ini =  '" + opr.getIni() + 
				"', cpr = '" + opr.getCpr() + "', password = '" + opr.getPassword() + "' WHERE opr_id = " +
				opr.getOprId()
		);
	}
	
	public List<OperatoerDTO> getOperatoerList() throws DALException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>(1);
		ResultSet rs = Connector.doQuery("SELECT * FROM operatoer natural join roles order by opr_id");
		int tempID=0;
		try
		{
			while (rs.next()){
				if(rs.getInt("opr_id")==tempID){
					list.get(list.size()-1).addRole(rs.getString("role"));
				}else{
					OperatoerDTO t= new OperatoerDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), new ArrayList<String>());
					t.addRole(rs.getString("role"));
					list.add(t);
					tempID=rs.getInt("opr_id");
				}
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}
}
	
