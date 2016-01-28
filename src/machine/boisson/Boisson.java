package machine.boisson;

/**
 * Représente une boisson disponible dans la machine
 * @author Guillaume Denis
 *
 */
public class Boisson extends Recette {

	private String nom;
	private int prix;
	
	public Boisson(int qteCafe, int qteLait, int qteSucre, int qteChocolat, String nom, int prix) {
		super(qteCafe, qteLait, qteSucre, qteChocolat);
		this.nom = nom;
		this.prix = prix;
	}

	public String getNom() {
		return nom;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	
}
