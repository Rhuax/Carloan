package business.model;

import java.security.NoSuchAlgorithmException;
import java.util.List;


import utility.Crittografia;
import business.entity.Login;
import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOLogin;

public class ModelLogin implements Model {
	
	private DaoFactory daofactory;
	private DAOLogin daoLogin;
	
	@Override
	public void Inserimento(Object parameter){
		
	}
	



	@Override
	public void aggiorna(Object parameter) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void lettura() {
		// TODO Auto-generated method stub
		
	}
	public void autenticazione(Object parameter){
		try {
			daofactory= DaoFactory.getDaoFactory(1);
			
			daoLogin= (DAOLogin) daofactory.getDao("DAOLogin");
			
			Login login = populate(parameter);
			
			daoLogin.autenticazione(login);
				
		} catch (InstantiationException | IllegalAccessException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>Scompatta il parameter ricevuto e istanzia un entity popolata dai valori di quel parameter</p>
	 * @param parameter
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws CommonException
	 */
	private Login populate(Object parameter) throws NoSuchAlgorithmException{
		@SuppressWarnings("unchecked")
		List<String> param = (List<String>) parameter;
		String username = param.get(0);
		String password = param.get(1);
		String supS = param.get(2);// qui mi dice se � un opratore, amministratore ecc..
		String supA = param.get(3);
		String Amm = param.get(4);
		String operatore= param.get(5);
		//controllo se l'utente non � gi� esistente ovviamente..
		
        Login login = new Login(username,password,supS,supA,Amm,operatore);
        
        return login;
    }

	@Override
	public void ricerca() {
		// TODO Auto-generated method stub
		
	}

}
