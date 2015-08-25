package presentation.mvp.view.controller.generale.contratto;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import business.entity.Entity;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.Noleggio;
import business.entity.Noleggio.StatoContratto;
import business.model.Exception.CommonException;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import utility.ParametriFXML;

public class ChiudiContratto extends NuovoContratto{
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@FXML
	public void btnConferma(ActionEvent event){
		SchermataGenerale scChiamante= (SchermataGenerale) this.getChiamante();
		contratto= (Contratto)scChiamante.getEntitaElementoSelezionato("Contratto");//ottengo le info sul cliente selezionato, ma ne cambio alcune
			try {

				contratto = prendiDatiDaView();
				presenter.processRequest("VerificaContratto", contratto);
				presenter.processRequest("ModificaContratto", contratto);
				//Prendo la schermata che ha chiamato questo metodo , li passo l'elemento selezionato , il cliente da modificare e la tabella su cui lavorare
				((SchermataGenerale)this.getChiamante()).caricaTabella((List<Contratto>)presenter.processRequest("getAllContratti",null), scChiamante.getTable("Contratto"));
				chiudiFinestra();
			}
			catch(CommonException e){
				e.showMessage();
			}
			catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalArgumentException
					| InvocationTargetException  e) {
				e.printStackTrace();
			}	
		}
	@SuppressWarnings("unchecked")
	@Override
	public Contratto prendiDatiDaView() throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, CommonException{
		contratto.setStato(StatoContratto.Chiuso.toString());
		contratto.setNote(textNote.getText());
		List<Noleggio> contrattiAperti= (List<Noleggio>)presenter.processRequest("getNoleggiAperti", contratto.getIDContratto());
			if(contrattiAperti.size()>0){
				throw new CommonException("Ci sono dei contratti aperti , non � possibile fare questa scelta");
			}
			else 
				contratto.setDataChiusura(LocalDate.now());//imposto la data di chiusura se il valore scelto � annullato	
		return contratto;
	}
	 
	@Override
	public void initData(Entity x){
		textNote.setText(((Contratto)x).getNote());
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
	}
	
}