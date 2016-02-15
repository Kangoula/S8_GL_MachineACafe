package fr.miage.machine;

import java.io.IOException;
import java.util.HashMap;

import fr.miage.machine.modele.Machine;
import fr.miage.machine.modele.boisson.Boisson;
import fr.miage.machine.modele.boisson.Recette;
import fr.miage.machine.modele.stock.Stock;
import fr.miage.machine.view.MenuOverviewController;
import fr.miage.machine.view.OrderOverviewController;
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
		Machine m = new Machine();

		Stock s1 = new Stock("café", 12);
		Stock s2 = new Stock("LAIT", 50);
		Stock s3 = new Stock("Chocolat", 14);
		Stock s4 = new Stock("sucre", 43);
		Stock s5 = new Stock("thé", 50);

		Recette r1 = new Recette();
		Recette r2 = new Recette();
		Recette r3 = new Recette();
		Recette r4 = new Recette();
		Recette r5 = new Recette();

		r1.ajouterIngredient("Café", 2);
		r1.ajouterIngredient("LAit", 2);
		r1.ajouterIngredient("Chocolat", 0);
		r1.ajouterIngredient("SUCRE", 1);
		r1.ajouterIngredient("thé", 0);
		
		r2.ajouterIngredient("Café", 0);
		r2.ajouterIngredient("LAit", 4);
		r2.ajouterIngredient("Chocolat", 3);
		r2.ajouterIngredient("SUCRE", 0);
		r2.ajouterIngredient("thé", 0);

		r3.ajouterIngredient("Café", 1);
		r3.ajouterIngredient("LAit", 4);
		r3.ajouterIngredient("Chocolat", 1);
		r3.ajouterIngredient("SUCRE", 1);
		r3.ajouterIngredient("thé", 0);
		
		r4.ajouterIngredient("thé", 2);
		r4.ajouterIngredient("café", 0);
		r4.ajouterIngredient("lait", 0);
		r4.ajouterIngredient("chocolat", 0);
		r4.ajouterIngredient("sucre", 1);
		
		r5.ajouterIngredient("thé", 2);
		r5.ajouterIngredient("café", 0);
		r5.ajouterIngredient("lait", 1);
		r5.ajouterIngredient("chocolat", 0);
		r5.ajouterIngredient("sucre", 1);

		Boisson b1 = new Boisson(r1, "Café Au Lait", 3);
		Boisson b2 = new Boisson(r2, "Chocolat Chaud", 2);
		Boisson b3 = new Boisson(r3, "Capuccino", 4);
		Boisson b4 = new Boisson(r4, "Thé à la Menthe", 2);
		Boisson b5 = new Boisson(r5, "Thé avec Lait", 3);

		m.setNombreBoissonMax(5);
		
		m.ajouterBoisson(b1);
		m.ajouterBoisson(b2);
		m.ajouterBoisson(b3);
		m.ajouterBoisson(b4);
		m.ajouterBoisson(b5);

		m.ajouterStock(s1);
		m.ajouterStock(s2);
		m.ajouterStock(s3);
		m.ajouterStock(s4);
		m.ajouterStock(s5);
		
		HashMap<String, Boisson> map = m.getListeBoissons();
		
		for(String key : map.keySet()){
			this.boissonData.add(map.get(key));
		}	
	}

	/**
	 * Returns the data as an observable list of Persons.
	 * 
	 * @return
	 */
	public ObservableList<Boisson> getBoissonData() {
		return this.boissonData;
	}

	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("La Machine a Café");

		this.primaryStage.getIcons().add(new Image("file:resources/images/coffee9.png"));

		initRootLayout();

		showMenuOverview();

	}

	/**
	 * Affiche la PersonOverview dans le layout
	 */
	private void showMenuOverview() {
		try {
			// chargement du fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/menuOverview.fxml"));
			AnchorPane menuOverview = (AnchorPane) loader.load();

			// ajoute le PersonOverview dans le layout
			rootLayout.setLeft(menuOverview);

			// Donne au controleur l'accès à l'application
			MenuOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Montrer la vue permettant de commander une boisson
	 */
	public void showOrderOverview() {
		try {
			// chargement du fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/orderOverview.fxml"));
			AnchorPane menuOverview = (AnchorPane) loader.load();

			// ajoute le PersonOverview dans le layout
			rootLayout.setCenter(menuOverview);

			// Donne au controleur l'accès à l'application
			OrderOverviewController controller = loader.getController();

			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
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
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
