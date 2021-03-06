package machine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import machine.boisson.Boisson;
import machine.boisson.Recette;
import machine.stock.Stock;

/**
 * Représente la machine a café et ses interactions avec l'utilisateur
 * 
 * @author Guillaume Denis
 *
 */
public class Machine {

	private HashMap<String, Boisson> listeBoissons;
	private HashMap<String, Stock> stockIngredients;
	public int nombreBoissonMax;

	public Machine() {
		this.listeBoissons = new HashMap<String, Boisson>();
		this.setStockIngredients(new HashMap<String, Stock>());
		this.nombreBoissonMax = 3;
	}

	/**
	 * Ajoute une nouvelle boisson à la machine si la taille de la liste de
	 * boissons est plus petite que 3
	 * 
	 * @param boisson
	 *            la boisson à ajouter à la machine
	 * @return la taille de la liste de boissons après insertion
	 */
	public int ajouterBoisson(Boisson boisson) {

		if (this.listeBoissons.size() < nombreBoissonMax) {
			this.listeBoissons.put(boisson.getNom().toLowerCase(), boisson);

		}

		return this.listeBoissons.size();
	}

	/**
	 * Modifie la recette d'une boisson existante dans la machine
	 * 
	 * @param nomBoisson
	 *            le nom de la boisson dont on veut modifier la recette
	 * @param nomIngredient
	 *            le nom de l'ingredient à modifier ou ajouter à la recette
	 * @param quantite
	 *            la quantite de l'ingrédient à ajouter à la recette
	 * @return la nouvelle recette de la boisson, null si la boisson n'est pas
	 *         trouvée
	 */
	public Recette modifierRecetteBoisson(String nomBoisson, Recette recette) {
		Recette res = null;

		if (this.listeBoissons.containsKey(nomBoisson)) {
			Boisson b = this.listeBoissons.get(nomBoisson);
			b.setRecette(recette);
			res = b.getRecette();
		}

		return res;
	}

	/**
	 * Modifie le prix d'une boisson existante dans la machine
	 * 
	 * @param nomBoisson
	 *            le nom de la boisson dont on veut modifier le prix
	 * @param prix
	 *            le nouveau prix de la boisson
	 * @return la boisson modifée ou null si la boisson n'est pas trouvée
	 */
	public Boisson modifierPrixBoisson(String nomBoisson, int prix) {
		Boisson b = this.listeBoissons.get(nomBoisson.toLowerCase());

		if (b != null)
			b.setPrix(prix);

		return b;
	}

	/**
	 * Supprime une boisson de la machine
	 * 
	 * @param nomBoisson
	 *            le nom de la boisson à supprimer
	 * @return la taille de la liste de boissons de la machine
	 */
	public int supprimerBoisson(String nomBoisson) {
		this.listeBoissons.remove(nomBoisson.toLowerCase());

		return this.listeBoissons.size();
	}

	/**
	 * Méthode permettant de vérifier que les ingrédients d'une boisson sont en
	 * stock
	 * 
	 * @param boisson
	 *            la boisson sélectionnée
	 * @return true si les ingrédients sont en stock, false sinon
	 */
	public boolean verifierStocksBoisson(Boisson boisson) {
		// On récupère la recette
		Recette r = boisson.getRecette();
		// On prépare l'itération sur les ingrédients de la recette de la
		// boisson en paramètre
		Set<String> set = r.getIngredients().keySet();
		Iterator<String> it = set.iterator();
		// Pour chaque ingrédient
		while (it.hasNext()) {
			String i = (String) it.next();
			// Quel est son stock ?
			int stockDisponible = this.getStockIngredient(i);
			// Combien en a-t-on besoin ?
			int besoin = r.getIngredients().get(i);
			// OK ?
			if (stockDisponible >= besoin) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Ajoute un ingrédient au stock de la machine Si l'ingrédient existe, sa
	 * quantité est modifiée S'il n'existe pas il est ajouté à la liste de
	 * ingrédients en sotck
	 * 
	 * @param stock
	 *            le stock à ajouter à la machine
	 * @return le stock ajouté à la liste, null si le stock n'a pas été ajouté
	 */
	public Stock ajouterStock(Stock stock) {

		if (this.stockIngredients.containsKey(stock.getType())) {
			this.stockIngredients.get(stock.getType()).augmenterStock(stock.getQuantite());
		} else {
			this.stockIngredients.put(stock.getType(), stock);
		}

		return this.stockIngredients.get(stock.getType());
	}

	/**
	 * Renvoit le Stock d'un ingréient donné
	 * 
	 * @param nomIngredient
	 *            le nom de l'ingrédient dont on souhaite consulter le stock
	 * @return la valeur actuelle du stock, -1 si le stock n'existe pas
	 */
	public int getStockIngredient(String nomIngredient) {
		int res = -1;
		String request = nomIngredient.toLowerCase();

		if (this.stockIngredients.containsKey(request)) {
			res = this.stockIngredients.get(request).getQuantite();
		}

		return res;
	}

	/**
	 * Modifie le stock d'un ingrédient donné
	 * 
	 * @param ingredient
	 *            l'ingrédient sont on veut modifier le stock
	 * @param nouveauStock
	 *            la nouvelle valeur du stock
	 */
	public void setStockIngredient(String ingredient, int nouveauStock) {
		this.stockIngredients.get(ingredient).setQuantite(nouveauStock);
	}

	public int getNombreBoissonMax() {
		return nombreBoissonMax;
	}

	public void setNombreBoissonMax(int nombreBoissonMax) {
		this.nombreBoissonMax = nombreBoissonMax;
	}

	public HashMap<String, Stock> getStockIngredients() {
		return stockIngredients;
	}

	public void setStockIngredients(HashMap<String, Stock> stockIngredients) {
		this.stockIngredients = stockIngredients;
	}

	public HashMap<String, Boisson> getListeBoissons() {
		return listeBoissons;
	}

	public void setListeBoissons(HashMap<String, Boisson> listeBoissons) {
		this.listeBoissons = listeBoissons;
	}
}
