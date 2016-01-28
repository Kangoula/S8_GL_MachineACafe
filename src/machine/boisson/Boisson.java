package machine.boisson;

/**
 * Représente une boisson disponible dans la machine
 * @author Guillaume Denis
 *
 */
public class Boisson{

	private String nom;
	private int prix;
	private Recette recette;
	
	public Boisson(Recette recette, String nom, int prix) {
		this.nom = nom;
		this.prix = prix;
		this.recette = recette;
	}

	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}

	public void setNom(String nom) {
		this.nom = nom;
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
