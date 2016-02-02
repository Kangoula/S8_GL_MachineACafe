package machine.stock;

/**
 * Représente le stock des ingrédients de la machine
 * @author Guillaume Denis
 *
 */
public class Stock {
	
	/**
	 * Indique le type de stock (café, lait, ...)
	 */
	private String type;
	private int quantite;
	
	public Stock(String type, int quantite) {
		this.type = type.toLowerCase();
		this.quantite = quantite;
	}

	/**
	 * Augmente la quantité en stock d'un montant donné
	 * @param qte la quantité à augmenter
	 */
	public void augmenterStock(int qte){
		if(qte >= 0){
			this.quantite += qte;
		}
	}
	
	//GETTERS & SETTERS
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	
}
