package datalayer;

import exceptions.DALException;

import java.sql.SQLException;
import java.util.List;

public class TempMain {
	public static void main(String[] args) {
		try {
			new Connector();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		OperatoerDAO opr = new OperatoerDAO();
		// System.out.println("Operatoer nummer 13:");
		// try { System.out.println(opr.getOperatoer(13)); }
		// catch (DALException e) { System.out.println(e.getMessage()); }
		//
		// System.out.println("Indsaettelse af ny operatoer med opr_id = 34");
		// OperatoerDTO oprDTO = new OperatoerDTO(34,"Don
		// Juan","DJ","000000-0000","iloveyou");
		// try { opr.createOperatoer(oprDTO); }
		// catch (DALException e) { System.out.println(e.getMessage()); }
		//
		System.out.println("Operatoer nummer 11:");
		OperatoerDTO t;
		try {
			t = opr.getOperatoer(11);
			System.out.println(opr.getOperatoer(11));

			System.out.println("Opdatering af initialer for operatoer nummer 34");
			List<String> tempList=t.getRoles();
			tempList.remove(1);
			t.setRoles(tempList);
			System.out.println(t.getRoles());
			opr.updateOperatoer(t);
			t=opr.getOperatoer(11);
			System.out.println(t);
		} catch (DALException e) {
			System.out.println(e.getMessage());
		}

		//
		// System.out.println("Operatoer nummer 34:");
		// try { System.out.println(opr.getOperatoer(34)); }
		// catch (DALException e) { System.out.println(e.getMessage()); }

		// System.out.println("Alle operatoerer:");
		// try { System.out.println(opr.getOperatoerList()); }
		// catch (DALException e) { System.out.println(e.getMessage()); }
		//
		// System.out.println("Operatoer nummer 5:");
		// try { System.out.println(opr.getOperatoer(5)); }
		// catch (DALException e) { System.out.println(e.getMessage()); }
		//
	}
}
