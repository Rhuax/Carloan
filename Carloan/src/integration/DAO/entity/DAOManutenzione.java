package integration.DAO.entity;

import integration.DAO.DaoFactory;
import integration.DAO.connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import MessaggiFinestra.AlertView;
import business.entity.Entity;
import business.entity.Auto.manutenzione.*;
import business.model.Exception.CommonException;
import static utility.QueryStringReplacer.queryReplaceFirst;
public class DAOManutenzione implements DAO{

	private  DaoFactory daofactory;
	
	
	public DAOManutenzione(DaoFactory dao){
		this.daofactory = dao;		
	}
	
	
	@Override
	public ResultSet creazione(Entity x) throws CommonException {
		Manutenzione m = null;
		String query="Insert into ? (DataInizio,Note,IDAuto) values('?','?',?)";
		if(x instanceof ManutenzioneOrdinaria){
			query=queryReplaceFirst(query, "manutenzioneordinaria");
			m=(ManutenzioneOrdinaria)x;
		}
		else if(x instanceof ManutenzioneStraordinaria){
			query=queryReplaceFirst(query, "manutenzionestraordinaria");
			m=(ManutenzioneStraordinaria)x;
		}
		
		query=queryReplaceFirst(query, m.getDatainizio().toString());
		query=queryReplaceFirst(query, m.getNote());
		query=queryReplaceFirst(query, String.valueOf(m.getIDAuto()));
		Connection conn=Connection.getConnection(daofactory);
		ResultSet r=null;
		try {
			r=conn.executeUpdate(query);
			if(r!=null)
				AlertView.getAlertView("Manutenzione inserita con successo", AlertType.INFORMATION);
			else
				throw new CommonException("Manutenzione non inserita");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public void aggiornamento(Entity entity) throws CommonException {
		Manutenzione m = null;
		String query="Update ? Set Note='?',DataFine='?'";
		if(entity instanceof ManutenzioneOrdinaria){
			query=queryReplaceFirst(query, "manutenzioneordinaria");
			m=(ManutenzioneOrdinaria)entity;
		}
		else if(entity instanceof ManutenzioneStraordinaria){
			query=queryReplaceFirst(query, "manutenzionestraordinaria");
			m=(ManutenzioneStraordinaria)entity;
		}
		query=queryReplaceFirst(query, m.getNote());
		query=queryReplaceFirst(query, m.getDataFine().toString());
		Connection conn=Connection.getConnection(this.daofactory);
		ResultSet r=null;
		try {
			r=conn.executeUpdate(query);
			if(r!=null)
				AlertView.getAlertView("Manutenzione chuisa con successo", AlertType.INFORMATION);
			else
				throw new CommonException("Manutenzione non chiusa");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Entity lettura(int id) throws CommonException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Lettura di tutte le manutenzioni
	 * @param id_auto
	 * @return
	 */
	public List<ManutenzioneOrdinaria> getAll_ordinarie(int id_auto){
		String query="Select * from manutenzioneordinaria where IDAuto="+String.valueOf(id_auto);
		ResultSet r=null;
		Connection conn=Connection.getConnection(this.daofactory);
		try {
			r=conn.executeRead(query);
			return (creaElencoManutenzioni_ordinarie(r));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new LinkedList<ManutenzioneOrdinaria>();
	}
	private List<ManutenzioneOrdinaria> creaElencoManutenzioni_ordinarie(ResultSet r) {
		List<ManutenzioneOrdinaria> m=new LinkedList<ManutenzioneOrdinaria>();
		try {
			if(r!=null){
			while(r.next()){
				m.add(new ManutenzioneOrdinaria(r.getInt(1), r.getDate(2).toLocalDate(), r.getDate(3).toLocalDate(), r.getString(4),r.getInt(5)));
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}


	public List<ManutenzioneStraordinaria> getAll_straordinarie(int id_auto){
		String query="Select * from manutenzionestraordinaria where IDAuto="+String.valueOf(id_auto);
		ResultSet r=null;
		Connection conn=Connection.getConnection(this.daofactory);
		try {
			r=conn.executeRead(query);
			return (creaElencoManutenzioni_straordinarie(r));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new LinkedList<ManutenzioneStraordinaria>();
	}
	private List<ManutenzioneStraordinaria> creaElencoManutenzioni_straordinarie(ResultSet r) {
		List<ManutenzioneStraordinaria> m=new LinkedList<ManutenzioneStraordinaria>();
		try {
			if(r!=null){
			while(r.next()){
				m.add(new ManutenzioneStraordinaria(r.getInt(1), r.getDate(2).toLocalDate(), r.getDate(3).toLocalDate(), r.getString(4),r.getInt(5)));
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	
	/**
	 * lettura manutenzioni aperte
	 * @param id_auto
	 * @return
	 */
	public List<ManutenzioneOrdinaria> getAll_ordinarie_aperte(int id_auto){
		String query="Select * from manutenzioneordinaria where IDAuto="+String.valueOf(id_auto)+" and DataFine is null";
		ResultSet r=null;
		Connection conn=Connection.getConnection(this.daofactory);
		try {
			r=conn.executeRead(query);
			return (creaElencoManutenzioni_ordinarie_aperte(r));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new LinkedList<ManutenzioneOrdinaria>();
	}
	private List<ManutenzioneOrdinaria> creaElencoManutenzioni_ordinarie_aperte(ResultSet r) {
		List<ManutenzioneOrdinaria> m=new LinkedList<ManutenzioneOrdinaria>();
		try {
			if(r!=null){
			while(r.next()){
				m.add(new ManutenzioneOrdinaria(r.getInt(1), r.getDate(2).toLocalDate(),null, r.getString(4),r.getInt(5)));
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}


	public List<ManutenzioneStraordinaria> getAll_straordinarie_aperte(int id_auto){
		String query="Select * from manutenzionestraordinaria where IDAuto="+String.valueOf(id_auto)+" and DataFine is null";
		ResultSet r=null;
		Connection conn=Connection.getConnection(this.daofactory);
		try {
			r=conn.executeRead(query);
			return (creaElencoManutenzioni_straordinarie_aperte(r));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new LinkedList<ManutenzioneStraordinaria>();
	}
	private List<ManutenzioneStraordinaria> creaElencoManutenzioni_straordinarie_aperte(ResultSet r) {
		List<ManutenzioneStraordinaria> m=new LinkedList<ManutenzioneStraordinaria>();
		try {
			if(r!=null){
			while(r.next()){
				m.add(new ManutenzioneStraordinaria(r.getInt(1), r.getDate(2).toLocalDate(), null, r.getString(4),r.getInt(5)));
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
}
