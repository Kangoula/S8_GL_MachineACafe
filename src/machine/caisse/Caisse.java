package machine.caisse;

/**
 * Représente les opérations financières de la machine a café
 * @author Guillaume Denis
 *
 */
public class Caisse {
	
	public Caisse() {
		
	}
	
	public static int renduMonnaie(int montant, int prix){
		int res = montant - prix;
		
		return res;
	}
	
	
}
