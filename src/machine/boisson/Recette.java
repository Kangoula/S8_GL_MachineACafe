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
	 * @param nom
	 * @param qte
	 */
	public void ajouterIngredient(String nom, int qte){
		this.ingredients.put(nom.toLowerCase(), qte);
	}
	
	/**
	 * Recupère la quantite d'un ingredient
	 * @param nom
	 * @return
	 */
	public int getQte(String nom){
		String request = nom.toLowerCase();
		int res = this.ingredients.containsKey(request) ? this.ingredients.get(request) : -1 ;
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
