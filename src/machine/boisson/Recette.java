package machine.boisson;

import java.util.HashMap;

/**
 * Représente la recette d'une boisson 
 * @author Guillaume Denis
 *
 */
public class Recette {
	
	private HashMap<String, Integer> ingredients;
	
	public Recette() {
		this.ingredients = new HashMap<String, Integer>();
	}

	/**
	 * Ajoute un ingrédient à la recette
	 * Si l'ingrédient existe, sa quantité est modifiée
	 * S'il n'existe pas, il est ajouté à la recette
	 * @param nom le nm de l'ingrédient à ajouter
	 * @param qte la quantité à ajouter
	 */
	public void ajouterIngredient(String nom, int qte){
		this.ingredients.put(nom.toLowerCase(), qte);
	}
	
	/**
	 * Recupère la quantite d'un ingredient
	 * @param nom le nom de l'ingrédient dont on veut obtenir la quantité
	 * @return la quantité de l'ingrédient demandé, -1 si l'ingrédient n'existe pas
	 */
	public int getQte(String nom){
		String request = nom.toLowerCase();
		int res = this.ingredients.containsKey(request) ? this.ingredients.get(request) : -1 ;
		return res;
	}
	
	/**
	 * Vérifie si au moins un ingrédient de la recette a une quantité > 1
	 * @return true si la recette est valide, false sinon
	 */
	public boolean estValide(){
		boolean res = false;
		int count = 0;
		
		for(String key : this.ingredients.keySet()){
			if(this.ingredients.get(key) == 0)
				count++;
		}
		
		if(count != this.ingredients.size()){
			res = true;
		}
			
		return res;
	}
	
	//GETTERS & SETTERS
	public HashMap<String, Integer> getIngredients() {
		return ingredients;
	}

	public void setIngredients(HashMap<String, Integer> ingredients) {
		this.ingredients = ingredients;
	}
}
