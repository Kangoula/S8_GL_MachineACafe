package machine;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import machine.boisson.Boisson;
import machine.boisson.Recette;
import machine.caisse.Caisse;
import machine.stock.Stock;

public class Main {

	public static Scanner sc = new Scanner(System.in);
	public static Machine m = initialiserMachine();

	public static void main(String[] args) {

		// lancement de l'interface
		montrerMenuPrincipal();

		sc.close();
	}

	/**
	 * Affiche le premier niveau du menu : le menu principal
	 */
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

	/**
	 * Affiche le niveau 2 du menu : Le menu de gestion de la machine
	 */
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
			montrerFormulaireAjoutBoisson();
			break;
		case "2":
			// TODO choisir boisson
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

	/**
	 * Formulaire à remplir par l'utilisateur permettant de créer une nouvelle
	 * boisson et de l'ajouter à la machine
	 */
	private static void montrerFormulaireAjoutBoisson() {
		String nom;
		Boisson b;
		int prix;

		// demander le nom
		System.out.println("Nom de la boisson : ");
		montrerFleche();
		nom = sc.next();

		// demander la recette
		Recette r = demanderRecette();
		while(r == null){
			r = demanderRecette();
		}

		// demander prix
		System.out.println("Prix de la boisson : ");
		montrerFleche();

		try {
			prix = sc.nextInt();
			while (!Caisse.verifierMontant(prix)) {
				afficherErreurMontant();
				montrerFleche();
				prix = sc.nextInt();
			}
		} catch (InputMismatchException e) {
			afficherErreurMontant();
			montrerFleche();
			prix = sc.nextInt();
		}

		// création et ajout de la boisson
		b = new Boisson(r, nom, prix);

		if (m.ajouterBoisson(b) == -1) {
			System.out.println("Le nombre maximum de boisson est déjà atteint,\nla boisson ne peut pas être ajoutée");
			montrerMenuGestion();
		}

	}

	/**
	 * Formulaire permettant d'entrer une nouvelle recette dans la machine Il
	 * est utilisé uniquement dans le cadre de la création d'une nouvelle
	 * boisson
	 * 
	 * @return la nouvelle recette créée
	 */
	private static Recette demanderRecette() {
		int montant;
		Recette r = new Recette();
		
		//on indique la quantité de chaque ingrédient que l'on a en stock
		Set<String> keyset = m.getStockIngredients().keySet();

		for (String key : keyset) {
			
			System.out.println("Quantité de " + key);
			montrerFleche();

			try {
				montant = sc.nextInt();
				
				while (montant < 0) {
					afficherErreurMontant();
					montrerFleche();
					montant = sc.nextInt();
				}

				r.ajouterIngredient(key, montant);
				
			} catch (InputMismatchException e) {
				afficherErreurMontant();
				montrerFleche();
				montant = sc.nextInt();
			}
		}
		
		//on vérifie qu'on a au moins 1 ingrédient dont la quantité est > 0
		if(!r.estValide()){
			System.out.println("La recette doit avoir au moins un de ses ingrédients avec une quantité > 1");
			r = null;
		}

		return r;
	}

	/**
	 * Affiche le niveau 3 du menu : la gestion des stocks L'utilisateur pourra
	 * consulter les sotcks et/ou ajouter des ingrédients à la machine
	 */
	private static void montrerMenuGestionStocks() {
		//TODO method body
	}

	/**
	 * Affiche le niveau 3 du menu : la modification d'une boisson Après avoir
	 * sélectionné sa boisson, L'utilisateur choisi ce qu'il veut modifier
	 */
	private static void montrerMenuModificationBoisson() {
		// TODO choisir boisson
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
			// TODO Afficher modification recette

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

	/**
	 * Formulaire permettant à l'utilisateur de modifier le prix d'une boisson
	 * donnée
	 * 
	 * @param nomBoisson
	 *            le nom de la boisson à modifier
	 */
	public static void montrerFormulaireGestionPrix(String nomBoisson) {
		// saisie du montant
		System.out.println("Nouveau prix de la boisson : ");
		montrerFleche();
		int montant = sc.nextInt();

		try {
			// vérification et modification
			while (!Caisse.verifierMontant(montant)) {
				afficherErreurMontant();
				montrerFleche();
				montant = sc.nextInt();
			}
			m.modifierPrixBoisson(nomBoisson, montant);
		} catch (InputMismatchException e) {
			afficherErreurMontant();
			montrerFleche();
			montant = sc.nextInt();
		}
	}

	public static void montrerFleche() {
		System.out.println("-> ");
	}

	public static void afficherErreurSaisie() {
		System.out.println("Mauvais numéro, veuillez recommencer");
	}

	public static void afficherErreurMontant() {
		System.out.println("Le montant n'est pas valide, veuillez recommencer");
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
