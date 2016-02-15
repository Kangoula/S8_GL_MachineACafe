package fr.miage.machine.outils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;

public class AlertCustom {

	public static void showErrorAlert(String title, String header, String message, Window owner){
		 Alert alert = new Alert(AlertType.ERROR);
         alert.initOwner(owner);
         alert.setTitle(title);
         alert.setHeaderText(header);
         alert.setContentText(message);

         alert.showAndWait();
	}

}
