package machine;

import java.util.HashMap;

import machine.boisson.Boisson;
import machine.boisson.Recette;
import machine.stock.Stock;

/**
 * Représente la machine a café et ses interactions avec l'utilisateur
 * @author Guillaume Denis
 *
 */
public class Machine {

	private HashMap<String, Boisson> listeBoissons;
	private HashMap<String, Stock> stockIngredients;
	
	public Machine() {
		this.listeBoissons = new HashMap<String, Boisson>();
		this.setStockIngredients(new HashMap<String, Stock>());
	}
	
	/**
	 * Ajoute une nouvelle boisson à la machine
	 * @param boisson
	 * @return la taille de la liste de boissons
	 */
	public int ajouterBoisson(Boisson boisson){

		if(this.listeBoissons.size() < 3){
			this.listeBoissons.put(boisson.getNom().toLowerCase(), boisson);	
		}
		
		return this.listeBoissons.size();
	}
	
	/**
	 * Modifie la recette d'une boisson existante dans la machine
	 * @param nomBoisson
	 * @return la boisson modifiée
	 */
	public Recette modifierRecetteBoisson(String nomBoisson, String nomIngredient, int quantite){
		
		Recette r = null;
		
		if(this.listeBoissons.containsKey(nomBoisson)){
			Boisson b = this.listeBoissons.get(nomBoisson);
			r = b.getRecette();
			r.ajouterIngredient(nomIngredient, quantite);
			b.setRecette(r);
		}
		
		return r;
	}
	
	/**
	 * Modifie le prix d'une boisson existante dans la machine
	 * @param nomBoisson
	 * @param prix
	 * @return la boisson modifée
	 */
	public Boisson modifierPrixBoisson(String nomBoisson, int prix){
		Boisson b = this.listeBoissons.get(nomBoisson);
		
		if(b != null)
			b.setPrix(prix);

		return b;
	}
	
	/**
	 * Supprime une boisson de la machine
	 * @param nomBoisson
	 * @return
	 */
	public int supprimerBoisson(String nomBoisson){
		this.listeBoissons.remove(nomBoisson.toLowerCase());
		
		return this.listeBoissons.size();
	}
	
	/**
	 * Ajoute un ingrédient au stock de la machine
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

	
	public HashMap<String, Stock> getStockIngredients() {
		return stockIngredients;
	}

	public void setStockIngredients(HashMap<String, Stock> stockIngredients) {
		this.stockIngredients = stockIngredients;
	}
	
}
