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
	private HashMap<String, Stock> listeIngrédients;
	
	public Machine() {
		this.listeBoissons = new HashMap<String, Boisson>();
		this.listeIngrédients = new HashMap<String, Stock>();
	}
	
	/**
	 * Ajoute une nouvelle boisson à la machine
	 * @param boisson
	 * @return la taille de la liste de boissons
	 */
	public int ajouterBoisson(Boisson boisson){

		if(this.listeBoissons.size() < 3){
			this.listeBoissons.put(boisson.getNom(), boisson);	
		}
		
		return this.listeBoissons.size();
	}
	
	/**
	 * Modifie la recette d'une boisson existante dans la machine
	 * @param nomBoisson
	 * @return la boisson modifiée
	 */
	public Boisson modifierRecetteBoisson(String nomBoisson, int qteCafe, int qteLait, int qteSucre, int qteChocolat){
		Boisson b = this.listeBoissons.get(nomBoisson);
		
		if(b != null){
			Recette r = new Recette(qteCafe, qteLait, qteSucre, qteChocolat);
			b.setRecette(r);
		}
		
		return b;
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
		this.listeBoissons.remove(nomBoisson);
		
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
	
}
