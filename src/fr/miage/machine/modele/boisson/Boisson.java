package fr.miage.machine.modele.boisson;

import javax.script.SimpleScriptContext;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Représente une boisson disponible dans la machine
 * @author Guillaume Denis
 *
 */
public class Boisson{

	private final StringProperty name;
	private final IntegerProperty price;
	private Recette recipe;
	
	public Boisson(Recette recette, String nom, int prix) {
		this.name = new SimpleStringProperty(nom);
		this.price = new SimpleIntegerProperty(prix);
		this.recipe = recette;
	}

	//GETTERS & SETTERS
	public Recette getRecette() {
		return recipe;
	}

	public void setRecette(Recette recette) {
		this.recipe = recette;
	}

	public void setNom(String nom) {
		this.name.set(nom);
	}

	public String getNom() {
		return name.get();
	}

	public int getPrix() {
		return price.get();
	}

	public void setPrix(int prix) {
		this.price.set(prix);
	}

    @Override
    public String toString() {
        String res = name.get() + " (" + price.get() + "€)";
        //res += "\n[Recette]\n"+recette;
        return res;
    }

	public StringProperty nameProperty() {
		return this.name;
	}

	public IntegerProperty priceProperty(){
		return this.price;
	}
}
