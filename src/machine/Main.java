package machine;

import java.util.Scanner;

import machine.boisson.Boisson;
import machine.boisson.Recette;
import machine.stock.Stock;

public class Main {

	public static Scanner sc = new Scanner(System.in);
	public static Machine m = initialiserMachine();

	public static void main(String[] args) {

		// lancement de l'interface
		montrerMenuPrincipal();

		sc.close();
	}

	public static void montrerMenuPrincipal() {
		System.out.println("-- Menu Principal --\n");
		System.out.println("1 - Commander");
		System.out.println("2 - Gestion de la machine");
		System.out.println("0 - Quitter");
		System.out.print("->");

		String res = sc.next();

		switch (res) {
		case "1":
			montrerMenuCommande();
			break;
		case "2":
			montrerMenuGestion();
			break;
		case "0":
			System.out.println("À bientôt !");
			break;
		default:
			afficherErreurSaisie();
			montrerMenuPrincipal();
		}
	}

	public static void montrerMenuCommande() {
		System.out.println("-- Commander une boisson --");
		montrerListeBoissons();
		montrerFleche();
	}

	public static void montrerListeBoissons() {
		System.out.println("..liste");
	}

	public static void montrerMenuGestion() {
		System.out.println("-- Gestion de la machine --\n");
		System.out.println("1 - Ajouter une boisson");
		System.out.println("2 - Supprimer une boisson");
		System.out.println("3 - Modifier une boisson");
		System.out.println("4 - Gestion des stocks");
		System.out.println("0 - Retour");
		montrerFleche();

		String res2 = sc.next();

		switch (res2) {
		case "0":
			montrerMenuPrincipal();
			break;
		case "1":
			// demander nom

			// demander recette

			// demander prix

			// afficher confirmation/information
			break;
		case "2":
			// choisir boisson
			String choix = "";
			m.supprimerBoisson(choix);
			break;
		case "3":
			montrerMenuModificationBoisson();
			break;
		case "4":
			montrerMenuGestionStocks();
			break;
		default:
			afficherErreurSaisie();
			montrerMenuGestion();
			break;
		}
	}

	private static void montrerMenuGestionStocks() {

	}

	private static void montrerMenuModificationBoisson() {
		// choisir boisson
		String choix = "";
		
		System.out.println("-- Modifier une boisson : " + choix + " --\n");
		System.out.println("1 - Modifier la recette");
		System.out.println("2 - Modifier le prix");
		System.out.println("0 - Retour");
		montrerFleche();

		String res4 = sc.next();

		switch (res4) {
		case "0":
			montrerMenuGestion();
			break;
		case "1":
			// Afficher modification recette

			break;
		case "2":
			montrerFormulaireGestionPrix(choix);
			break;
		default:
			afficherErreurSaisie();
			montrerMenuModificationBoisson();
			break;
		}
	}

	public static void montrerFormulaireGestionPrix(String nomBoisson){
		System.out.println("Veuillez préciser le nouveau prix de la boisson");
		montrerFleche();
		String montant = sc.next();
		
		try{
			m.modifierPrixBoisson(nomBoisson, Integer.parseInt(montant));
		}
		catch(NumberFormatException e){
			System.out.println("Le montant n'est pas valable, veuillez recommencer");
			montrerFleche();
			montant = sc.next();
		}
	}
	
	public static void montrerFleche() {
		System.out.println("-> ");
	}

	public static void afficherErreurSaisie() {
		System.out.println("Mauvais numéro, veuillez recommencer");
	}

	/**
	 * Initialise la machine a café avec des valeurs par défaut
	 * 
	 * @return la machine initialisée
	 */
	public static Machine initialiserMachine() {
		Machine m = new Machine();

		Stock s1 = new Stock("café", 12);
		Stock s2 = new Stock("LAIT", 50);
		Stock s3 = new Stock("Chocolat", 14);
		Stock s4 = new Stock("sucre", 43);

		Recette r1 = new Recette();
		Recette r2 = new Recette();
		Recette r3 = new Recette();

		r1.ajouterIngredient("Café", 2);
		r1.ajouterIngredient("LAit", 2);
		r1.ajouterIngredient("Chocolat", 0);
		r1.ajouterIngredient("SUCRE", 1);

		r2.ajouterIngredient("Café", 0);
		r2.ajouterIngredient("LAit", 4);
		r2.ajouterIngredient("Chocolat", 3);
		r2.ajouterIngredient("SUCRE", 0);

		r3.ajouterIngredient("Café", 1);
		r3.ajouterIngredient("LAit", 4);
		r3.ajouterIngredient("Chocolat", 1);
		r3.ajouterIngredient("SUCRE", 1);

		Boisson b1 = new Boisson(r1, "Café Au Lait", 3);
		Boisson b2 = new Boisson(r2, "Chocolat Chaud", 2);
		Boisson b3 = new Boisson(r3, "Capuccino", 4);

		m.ajouterBoisson(b1);
		m.ajouterBoisson(b2);
		m.ajouterBoisson(b3);

		m.ajouterStock(s1);
		m.ajouterStock(s2);
		m.ajouterStock(s3);
		m.ajouterStock(s4);

		return m;
	}

}
