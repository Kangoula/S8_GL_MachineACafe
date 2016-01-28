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
		this.type = type;
		this.quantite = quantite;
	}

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
