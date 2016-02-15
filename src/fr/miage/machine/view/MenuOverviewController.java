package fr.miage.machine.view;

import fr.miage.machine.MainApp;
import javafx.fxml.FXML;

public class MenuOverviewController {

	private MainApp mainApp;
	
	public MenuOverviewController() {
		
	}
	
	/**
	 * Initilaise le controleur, cette méthode est appellé automatiquement après que le fxml soit chargé
	 */
	@FXML
	private void initialize(){
		
	}

	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
	
	@FXML
	private void handleShowOrderMenu(){
		this.mainApp.showOrderOverview();
	}
}
