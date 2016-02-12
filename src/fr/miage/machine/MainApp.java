package fr.miage.machine;

import java.io.IOException;

import fr.miage.machine.modele.boisson.Boisson;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	/**
     * Les boissons
     */
    private ObservableList<Boisson> boissonData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
        // Ajoute des données
        
    }

    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Boisson> getBoissonData() {
        return boissonData;
    }
	
	
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("La Machine a Café");
		
		this.primaryStage.getIcons().add(new Image("file:resources/images/coffee9.png"));

		initRootLayout();

		//showMenuOverview();

	}

	/**
	 * Affiche la PersonOverview dans le layout
	 */
	private void showMenuOverview() {
		try {
			// chargement du fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			
			//ajoute le PersonOverview dans le layout
			rootLayout.setCenter(personOverview);
			
			// Donne au controleur l'accès à l'application
	       // PersonOverviewController controller = loader.getController();
	      //  controller.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 *
	 * @param person the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showBoissonEditDialog(Boisson boisson) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Person");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	      //  PersonEditDialogController controller = loader.getController();
	      //  controller.setDialogStage(dialogStage);
	       // controller.setPerson(person);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return false ;//controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	/**
	 * Initialise la vue racine
	 */
	private void initRootLayout() {
		try {
			// chargement du fxml associé
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/rootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// affiche la scène
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	 /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
