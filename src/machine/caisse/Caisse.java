package machine.caisse;

/**
 * Représente les opérations financières de la machine a café
 * @author Guillaume Denis
 *
 */
public class Caisse {
	
	public Caisse() {
		
	}
	
	/**
	 * Rends la monnaie à l'utilisateur
	 * @param montantInsere
	 * @param prix
	 * @return
	 */
	public static int renduMonnaie(int montantInsere, int prix){
		
		return montantInsere - prix;
	}
	
	/**
	 * Vérifie si le montant inséré par l'utilisateur est valide
	 * @param montant
	 * @return
	 */
	public static boolean verificationMontant(int montant){
		return (montant <= 0);
	}
	
}
