package businessLayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import datalayer.IOperatoerDAO;
import datalayer.OperatoerDTO;
import exceptions.DALException;

public class BusinessLayer implements IBusinessLayer {

	private IOperatoerDAO operatoerDAO;
	
	@Override //gadg
	public OperatoerDTO getUser(int userId) throws DALException {
		return operatoerDAO.getOperatoer(userId);
	}

	@Override
	public List<OperatoerDTO> getUserList() throws DALException {
		return operatoerDAO.getOperatoerList();
	}

	@Override
	public void createUser(OperatoerDTO user) throws DALException {
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
						operatoerDAO.createOperatoer(user);
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
	public void updateUser(OperatoerDTO user) throws DALException {
		operatoerDAO.updateOperatoer(user);

	}

	@Override
	public void deleteUser(int userId) throws DALException {
		//operatoerDAO.deleteUser(userID);

	}

}
