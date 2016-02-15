package fr.miage.machine.view;


import fr.miage.machine.MainApp;
import fr.miage.machine.modele.boisson.Boisson;
import fr.miage.machine.outils.AlertCustom;
import fr.miage.machine.outils.Caisse;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OrderOverviewController {

	@FXML
	private TableView<Boisson> boissonList;
	@FXML
	private TableColumn<Boisson, String> nameColumn;
	@FXML
	private TableColumn<Boisson, Integer> priceColumn;
	@FXML
	private Slider suggarQte;
	@FXML
	private TextField amountPaid;
	@FXML
	private Button buttonOk;
	
	private boolean okClicked = false;
	private Boisson boisson;
	private MainApp mainApp;

	public OrderOverviewController() {

	}

	@FXML
	private void initialize() {
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
		
		showDrinkDetails(null);
		
		//initialisation du listener
		boissonList.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showDrinkDetails(newValue));
	}

	/**
	 * Remplis les champs avec les propriétés de la boisson sélectionnée. 
	 * Vide les champs si aucune boisson n'est sélectionnée
	 * @param boisson
	 */
	 private void showDrinkDetails(Boisson boisson){
		 if(boisson == null){
			 toggleInputs(true);
			 this.amountPaid.clear();
		 }
		 else {
			 toggleInputs(false);
			 this.suggarQte.setValue(boisson.getRecette().getQte("sucre"));
		 }
	 }
	
	 @FXML
	 private void handleOk(){
		 if(isValidInput()){
			 
		 }
	 }
	 
	 private boolean isValidInput(){
		 boolean rep = false;
		 String message = "";
		 
		 if(this.amountPaid.getText() != null || this.amountPaid.getText().length() != 0){
			 try {
				 rep = Caisse.verifierMontant(Integer.parseInt(this.amountPaid.getText()));
			 }
			 catch(NumberFormatException e){
				 rep = false;
			 }	 
		 }
		 if(!rep){
			 message = "Montant du paiement invalide";
			 AlertCustom.showErrorAlert("Erreur lors du paiement","Veuillez vérifier les champs ci-dessous",message,this.mainApp.getPrimaryStage());
		 }
		 return rep;
	 }
	 
	 /**
	  * Active ou désactive les inputs
	  * @param setDisable true si on veut les désactiver, false sinon
	  */
	 private void toggleInputs(boolean setDisable){
		 this.suggarQte.setDisable(setDisable);
		 this.amountPaid.setDisable(setDisable);
		 this.buttonOk.setDisable(setDisable);
	 }
	 
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		//System.out.println(mainApp.getBoissonData());
		this.boissonList.setItems(mainApp.getBoissonData());
	}
}
