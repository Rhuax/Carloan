package business.model;

import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOAutoveicolo;
import integration.DAO.entity.DAOCartaDiCredito;
import business.entity.Entity;
import business.entity.pagamento.CartaDiCredito;
import business.model.Exception.CommonException;

public class ModelCartaDiCredito implements Model{

	private DaoFactory daofactory;



	@Override
	public void ricerca() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Inserimento(Entity parameter) {
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			((DAOCartaDiCredito)daofactory.getDao("DAOCartaDiCredito")).creazione(parameter);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void aggiornamento(Entity parameter) throws CommonException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Entity lettura(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CartaDiCredito> getAllByCliente(int id){
		try {
			daofactory=DaoFactory.getDaoFactory(1);
			return ((DAOCartaDiCredito)daofactory.getDao("DAOCartaDiCredito")).getAllByCliente(id);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
