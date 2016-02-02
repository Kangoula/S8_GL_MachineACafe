package machine.caisse;

/**
 * Représente les opérations financières de la machine a café
 * @author Guillaume Denis
 *
 */
public class Caisse {
	
	/**
	 * Rends la monnaie à l'utilisateur
	 * @param montantInsere le montant inséré par l'utilisateur
	 * @param prix le prix de la boisson
	 * @return la montant rendu
	 */
	public static int rendreMonnaie(int montantInsere, int prix){
		
		return montantInsere - prix;
	}
	
	/**
	 * Vérifie si le montant inséré par l'utilisateur est valide
	 * @param montant le montant inséré par l'utilisateur
	 * @return true si le montant est valide, false sinon
	 */
	public static boolean verifierMontant(int montant){
		return (montant > 0);
	}
	
}
