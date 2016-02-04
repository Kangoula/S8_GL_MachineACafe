package machine.caisse;

import machine.boisson.Boisson;

/**
 * Représente les opérations financières de la machine a café
 * 
 * @author Guillaume Denis
 *
 */
public class Caisse {

	/**
	 * Rends la monnaie à l'utilisateur
	 * 
	 * @param montantInsere
	 *            le montant inséré par l'utilisateur
	 * @param prix
	 *            le prix de la boisson
	 * @return la montant rendu
	 */
	public static int rendreMonnaie(int montantPaye, int prix) {

		return montantPaye - prix;
	}

	/**
	 * Méthode qui permet de connaitre le montant à rendre en fonction de la
	 * boisson choisie et du montant payé par l'utilisateur
	 * 
	 * @param boisson
	 *            la boisson que l'on veut acheter
	 * @param montantPaye
	 *            le montant inséré dans la machine
	 * @return la différence entre le montant payé et le prix de la boisson
	 */
	public static int rendreMonnaie(Boisson boisson, int montantPaye) {
		return (montantPaye - boisson.getPrix());
	}

	/**
	 * Vérifie si le montant inséré par l'utilisateur est valide
	 * 
	 * @param montant
	 *            le montant inséré par l'utilisateur
	 * @return true si le montant est valide, false sinon
	 */
	public static boolean verifierMontant(int montant) {
		return (montant > 0);
	}

}
