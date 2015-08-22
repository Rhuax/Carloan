package business.model;



import java.util.List;

import integration.DAO.DaoFactory;
import integration.DAO.entity.DAOCliente;
import business.entity.Cliente;
import business.entity.Entity;

public class ModelCliente implements Model{
	private DaoFactory daofactory;

	@Override
	public void Inserimento(Entity parameter) {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			((DAOCliente) daofactory.getDao("DAOCliente")).creazione(parameter);;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void aggiornamento(Entity parameter) {
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			((DAOCliente) daofactory.getDao("DAOCliente")).aggiornamento(parameter);;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void lettura() {
		
	}
	
	
	@Override
	public void ricerca() {
		// TODO Auto-generated method stub
		
	}
	
	public List<Cliente> getAll(){
		try {
			if(daofactory==null)
				daofactory= DaoFactory.getDaoFactory(1);
			return ((DAOCliente) daofactory.getDao("DAOCliente")).getAll();	
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;	
	}
}
