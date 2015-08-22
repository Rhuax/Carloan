package presentation.mvp.view.controller.generale.contratto;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import MessaggiFinestra.AlertView;
import business.entity.Cliente;
import business.entity.Noleggio.Contratto;
import business.entity.Noleggio.StatoContratto;
import business.model.Exception.CommonException;
import presentation.mvp.view.Presenter;
import presentation.mvp.view.controller.generale.SchermataGenerale;
import utility.ParametriFXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class ModificaContratto extends NuovoContratto{
	@FXML
	private Button btnModifica;
	@FXML
	private Button btnConferma;	
	@FXML
	private ChoiceBox<String> choiceStato;
	@FXML
	private TextArea textNote;
	
	private Contratto contratto;
	
	private boolean Aggiornare=true;
	private int lunghezzaOld;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@FXML
	public void btnConferma(ActionEvent event){
		SchermataGenerale scChiamante= (SchermataGenerale) this.getChiamante();
		contratto= (Contratto)scChiamante.getEntitaElementoSelezionato("Contratto");//ottengo le info sul cliente selezionato, ma ne cambio alcune
	
		Aggiornare=true;
		contratto = prendiDatiDaView();
		
		if(Aggiornare==true){
			try {
				presenter.processRequest("VerificaContratto", contratto);
				presenter.processRequest("ModificaContratto", contratto);
				//Prendo la schermata che ha chiamato questo metodo , li passo l'elemento selezionato , il cliente da modificare e la tabella su cui lavorare
				((SchermataGenerale)this.getChiamante()).aggiornaElementotabella(scChiamante.getElemSelezionato("Contratto"),contratto,scChiamante.getTable("Contratto"));			
			
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
		else {
			AlertView.getAlertView("Nessuna modifica da apportare", AlertType.INFORMATION);
		}	
	
	}
	
	@Override
	public Contratto prendiDatiDaView(){
		contratto.setStato(choiceStato.getSelectionModel().getSelectedItem());
		contratto.setNote(textNote.getText());
		
		
		if(choiceStato.getSelectionModel().getSelectedItem().equals(StatoContratto.Annullato.toString())){
			contratto.setDataChiusura(LocalDate.now());//imposto la data di chiusura se il valore scelto � annullato
		}

		//controllo se bisogna aggiornare
		if(contratto.getStato().equals(StatoContratto.Aperto.toString()) && contratto.getNote().length()==lunghezzaOld){
			Aggiornare=false;
		}
		return contratto;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		presenter=new Presenter();
		FXMLParameter = new ParametriFXML(null,false);
		settaChoiceBox();	
	}
	
	public void settaChoiceBox(){
		ObservableList<String> choice = FXCollections.observableArrayList(StatoContratto.getAllStates());
		choice.remove(1); 
		choiceStato.setItems(choice);
		choiceStato.getSelectionModel().select(0);
	}
	
}
