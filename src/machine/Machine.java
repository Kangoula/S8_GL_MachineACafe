package machine;

import java.util.HashMap;

import machine.boisson.Boisson;
import machine.caisse.Caisse;
import machine.stock.Stock;

/**
 * Représente la machine a café et ses interactions avec l'utilisateur
 * @author Guillaume Denis
 *
 */
public class Machine {

	private HashMap<String, Boisson> listeBoissons;
	
	public Machine() {
		this.listeBoissons = new HashMap<String, Boisson>();
	}
	
	/**
	 * 
	 * @param boisson
	 * @return la taille de la liste de boissons
	 */
	public int ajouterBoisson(Boisson boisson){

		if(this.listeBoissons.size() < 3){
			this.listeBoissons.put(boisson.getNom(), boisson);	
		}
		
		int size = this.listeBoissons.size();
		
		return size;
	}
	
	/**
	 * 
	 * @param nomBoisson
	 * @return
	 */
	public boolean modifierBoisson(String nomBoisson){
		boolean res = false;
		
		return res;
	}
	
	/**
	 * 
	 * @param nomBoisson
	 * @return
	 */
	public boolean supprimerBoisson(String nomBoisson){
		boolean res = false;
		
		return res;
	}
	
	/**
	 * 
	 * @param nomIngredient
	 * @param quantite
	 * @return
	 */
	public boolean ajoutIngredient(String nomIngredient, int quantite){
		boolean res = false;
				
		return res;
	}
	
	/**
	 * 
	 * @param nomIngredient
	 * @return
	 */
	public String verifierStockIngredient(String nomIngredient){
		String res = "";
		
		return res;
	}
	
}
