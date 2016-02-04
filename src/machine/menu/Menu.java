package machine.menu;

import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import machine.Machine;
import machine.Main;
import machine.boisson.Boisson;
import machine.boisson.Recette;
import machine.caisse.Caisse;
import machine.stock.Stock;

public class Menu {

	/**
	 * Affiche le premier niveau du menu : le menu principal
	 * 
	 * @param machine
	 *            la machine a café
	 */
	public static void montrerMenuPrincipal(Machine machine) {
		System.out.println("-- Menu Principal --\n");
		System.out.println("1 - Commander");
		System.out.println("2 - Gestion de la machine");
		System.out.println("0 - Quitter");
		montrerFleche();

		String res = Saisie.saisirChaine(30);

		switch (res) {
		case "1":
			montrerMenuCommande(machine);
			montrerMenuPrincipal(machine);
			break;
		case "2":
			montrerMenuGestion(machine);
			break;
		case "0":
			System.out.println("À bientôt !");
			break;
		default:
			Saisie.afficherErreurSaisie();
			montrerMenuPrincipal(machine);
		}
	}

	/**
	 * Montrer le dernier niveau du menu : celui qui permet de remplir le stock
	 * d'ingrédients déjà dans la machine.
	 * 
	 * @param machine
	 *            la machine a café
	 */
	public static void montrerMenuAjouterStock(Machine machine) {
		// TODO
	}

	/**
	 * Affiche le menu qui permet de commander une boisson
	 * 
	 * @param machine
	 *            la machine à café
	 */
	public static void montrerMenuCommande(Machine machine) {
		System.out.println("\n-- Commander votre boisson préférée --");

		// Choisir une boisson dans la liste des boissons disponibles
		String choix = choisirBoisson(machine);
		montrerFleche();

		Boisson b = machine.getListeBoissons().get(choix);

		// Demander à l'utilisateur de payer
		System.out.println("Combien tu lâches ?");
		int montantPaye = fairePayer(b, 0);

		// Vérifier que les stocks sont suffisants
		boolean stocksOK = machine.verifierStocksBoisson(b);

		if (stocksOK) {
			// Rendre la monnaie
			int montantARendre = Caisse.rendreMonnaie(b, montantPaye);
			System.out.println("La machine va vous rendre " + montantARendre + " euros.");

			// Fabriquer la boisson (décrémenter les stocks + attente)
			fabriquerBoisson(b, machine);
			System.out.println(
					"\nLa machine vient de vous donner votre boisson. Ce serait une bonne idée de la prendre !\n");

		} else {
			// Afficher un message :-(
			System.out.println("Plus de stock");

			// Rendre le montant payé
			System.out.println("La machine va vous rendre" + montantPaye + "euros.");
		}

		// Retour à l'accueil

	}

	/**
	 * Affiche le niveau 2 du menu : Le menu de gestion de la machine
	 * 
	 * @param machine
	 *            la machine a café
	 */
	public static void montrerMenuGestion(Machine machine) {
		System.out.println("-- Gestion de la machine --\n");
		System.out.println("1 - Ajouter une boisson");
		System.out.println("2 - Supprimer une boisson");
		System.out.println("3 - Modifier une boisson");
		System.out.println("4 - Gestion des stocks");
		System.out.println("0 - Retour");
		montrerFleche();

		String res2 = Saisie.saisirChaine(30);
		String choix;

		switch (res2) {
		case "0":
			montrerMenuPrincipal(machine);
			break;
		case "1":
			montrerFormulaireAjoutBoisson(machine);
			montrerMenuGestion(machine);
			break;
		case "2":
			choix = choisirBoisson(machine);
			if (!choix.equals("Retour")) {
				machine.supprimerBoisson(choix);
			}
			montrerMenuGestion(machine);
			break;
		case "3":
			choix = choisirBoisson(machine);
			if (!choix.equals("Retour")) {
				montrerMenuModificationBoisson(choix, machine);
			}
			montrerMenuGestion(machine);
			break;
		case "4":
			montrerMenuGestionStocks(machine);
			break;
		default:
			Saisie.afficherErreurSaisie();
			montrerMenuGestion(machine);
			break;
		}
	}

	/**
	 * Affiche l'état de la fabrication de la boisson.
	 * 
	 * @param boisson
	 *            la boisson à fabriquer
	 * @param machine
	 *            la machine à café
	 */
	public static void fabriquerBoisson(Boisson boisson, Machine machine) {
		// On récupère la recette
		Recette r = boisson.getRecette();
		// On prépare l'itération sur les ingrédients de la recette de la
		// boisson en paramètre
		Set<String> set = r.getIngredients().keySet();
		Iterator<String> it = set.iterator();
		// Pour chaque ingrédient
		while (it.hasNext()) {
			String nomIngredient = (String) it.next();
			// Quel est son stock ?
			int stockDisponible = machine.getStockIngredient(nomIngredient);
			// Combien en a-t-on besoin ?
			int besoin = r.getIngredients().get(nomIngredient);
			// On décrémente le stock de l'ingrédient
			machine.setStockIngredient(nomIngredient, stockDisponible - besoin);
		}
		for (int i = 0; i < 3; i++) {
			System.out.print("...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	/**
	 * Méthode qui permet à l'utilisateur de choisir une boisson dans la liste
	 * des boissons de la machine
	 * 
	 * @param machine
	 *            la machine a café
	 * 
	 * @return le nom de la boisson choisie par l'utilisateur
	 */
	public static String choisirBoisson(Machine machine) {

		int nbBoissons = machine.getListeBoissons().size();
		String tab[] = new String[nbBoissons + 1];

		Set<String> set = machine.getListeBoissons().keySet();
		Iterator<String> it = set.iterator();

		int i = 1;
		tab[0] = "Retour";
		String boisson;

		System.out.println("0 - Retour");

		while (it.hasNext()) {
			tab[i] = (String) it.next();
			boisson = machine.getListeBoissons().get(tab[i]).toString();
			System.out.println(i + " - " + boisson);
			i++;
		}

		montrerFleche();
		i = Saisie.saisirEntier(0, tab.length - 1);

		return tab[i];
	}

	/**
	 * Formulaire à remplir par l'utilisateur permettant de créer une nouvelle
	 * boisson et de l'ajouter à la machine
	 * 
	 * @param machine
	 *            la machine a café
	 */
	private static void montrerFormulaireAjoutBoisson(Machine machine) {

		// si on peut insérer des boissons, on le fait
		if (!(machine.getListeBoissons().size() >= machine.getNombreBoissonMax())) {
			String nom;
			Boisson b;
			int prix;

			// demander le nom
			System.out.println("Nom de la boisson : ");
			montrerFleche();
			nom = Saisie.saisirChaine(30);

			// demander la recette
			Recette r = demanderRecette(machine);

			// demander prix
			System.out.println("Prix de la boisson : ");
			montrerFleche();
			prix = Saisie.saisirEntier(1, 15);

			/*
			 * try { prix = sc.nextInt(); while (!Caisse.verifierMontant(prix))
			 * { afficherErreurMontant(); montrerFleche(); prix = sc.nextInt();
			 * } } catch (InputMismatchException e) { afficherErreurMontant();
			 * montrerFleche(); prix = sc.nextInt(); }
			 */

			// création et ajout de la boisson
			b = new Boisson(r, nom, prix);
			machine.ajouterBoisson(b);
		} else {
			System.out.println("Le nombre maximum de boisson est déjà atteint,\nla boisson ne peut pas être ajoutée");
			montrerMenuGestion(machine);
		}

	}

	/**
	 * Affiche le niveau 3 du menu : la gestion des stocks L'utilisateur pourra
	 * consulter les sotcks et/ou ajouter des ingrédients à la machine
	 * 
	 * @param machine
	 *            la machine a café
	 */
	private static void montrerMenuGestionStocks(Machine machine) {

		System.out.println("-- Gestion des stocks --");
		System.out.println("1 - Vérifier l'état des stocks");
		System.out.println("2 - Ajouter du stock d'un ingrédient existant");
		System.out.println("3 - Ajouter un nouvel ingrédient");
		System.out.println("0 - Retour");
		montrerFleche();

		String res = Saisie.saisirChaine(30);

		switch (res) {
		case "0":
			montrerMenuGestion(machine);
			break;
		case "1":
			montrerEtatStocks(machine);
			break;
		case "2":
			montrerMenuAjouterStock(machine);
			break;
		case "3":
			montrerMenuAjouterIngredient(machine);
			break;
		default:
			montrerMenuGestion(machine);
			break;
		}
	}

	/**
	 * Formulaire permettant d'entrer une nouvelle recette dans la machine Il
	 * est utilisé uniquement dans le cadre de la création d'une nouvelle
	 * boisson
	 * 
	 * @param machine
	 *            la machine a café
	 * 
	 * @return la nouvelle recette créée
	 */
	private static Recette demanderRecette(Machine machine) {

		// on indique la quantité de chaque ingrédient que l'on a en stock
		Set<String> keyset = machine.getStockIngredients().keySet();

		return Saisie.saisirRecette(keyset);
	}

	/**
	 * Affiche l'état actuel des sotcks d'ingrédients de la machine
	 * 
	 * @param machine
	 *            la machine a café
	 */
	private static void montrerEtatStocks(Machine machine) {

		System.out.println("-- Etat des stocks --");

		for (String key : machine.getStockIngredients().keySet()) {
			System.out.print(key);
			System.out.print(" : ");
			System.out.print(machine.getStockIngredient(key));
			System.out.println(" unités");
		}
		System.out.println("0 - Retour");
		montrerFleche();

		while (!Saisie.saisirChaine(30).equals("0")) {
			System.out.println("Biiiiip, mauvaise réponse, essaie encore...");
			montrerFleche();
		}
		montrerMenuGestionStocks(machine);
	}

	/**
	 * Affiche le niveau 3 du menu : la modification d'une boisson Après avoir
	 * sélectionné sa boisson, L'utilisateur choisi ce qu'il veut modifier
	 * 
	 * @param machine
	 *            la machine a café
	 */
	private static void montrerMenuModificationBoisson(String choix, Machine machine) {

		System.out.println("-- Modifier une boisson : " + choix + " --\n");
		System.out.println("1 - Modifier la recette");
		System.out.println("2 - Modifier le prix");
		System.out.println("0 - Retour");
		montrerFleche();

		String res4 = Saisie.saisirChaine(30);

		switch (res4) {
		case "0":
			montrerMenuGestion(machine);
			break;
		case "1":
			montrerMenuModifierRecette(choix, machine);
			break;
		case "2":
			montrerFormulaireGestionPrix(choix, machine);
			break;
		default:
			Saisie.afficherErreurSaisie();
			montrerMenuModificationBoisson(choix, machine);
			break;
		}
	}

	/**
	 * Formulaire permettant à l'utilisateur de modifier le prix d'une boisson
	 * choisie
	 * 
	 * @param nomBoisson
	 *            le nom de la boisson choisie
	 * @param machine
	 *            la machine a café
	 */
	public static void montrerMenuModifierRecette(String nomBoisson, Machine machine) {
		System.out.println("-- Modifier la recette de : " + nomBoisson + "--");

		// récupération de ingrédients de la boisson choisie
		Set<String> keyset = machine.getListeBoissons().get(nomBoisson).getRecette().getIngredients().keySet();

		Recette r = machine.modifierRecetteBoisson(nomBoisson, Saisie.saisirRecette(keyset));

		if (r == null) {
			System.err.println("Erreur, la boisson n'existe pas");
		} else {
			System.out.println(r);
		}
		montrerMenuModificationBoisson(nomBoisson, machine);
	}

	/**
	 * Montre le denrier niveau du menu : celui qui permet d'ajouter un nouvel
	 * ingrédient à la machine
	 * 
	 * @param machine
	 *            la machine a café
	 */
	public static void montrerMenuAjouterIngredient(Machine machine) {
		String nom;
		int quantite;

		// demander le nom
		System.out.println("Nom du nouvel ingrédient : ");
		montrerFleche();
		nom = Saisie.saisirChaine(30);

		// demander quantité
		System.out.println("Quantité initiale ");
		montrerFleche();
		quantite = Saisie.saisirQuantiteIngredient();

		Stock stock = new Stock(nom, quantite);

		// ajout
		machine.getStockIngredients().put(nom, stock);
	}

	/**
	 * Méthode récursive permettant de faire payer un utilisateur (Boucle
	 * jusqu'à ce qu'il donne le bon montant).
	 * 
	 * @param boisson
	 *            Le boisson commandée
	 * @param montantPaye
	 *            Le montant inséré dans la machine par l'utilisateur
	 * @param machine
	 *            la machine a café
	 * 
	 * @return le montant total payé par l'utilisateur
	 */
	public static int fairePayer(Boisson boisson, int montantPaye) {
		int prixBoisson = boisson.getPrix();

		if (montantPaye >= prixBoisson) {
			return montantPaye;
		} else {

			if (montantPaye != 0) {
				System.out.println("Il va en falloir encore un peu si tu le veux ton truc... Combien tu lâches ?");
			}

			int montant = Saisie.saisirEntier(0, 15);
			return fairePayer(boisson, montantPaye + montant);
		}
	}

	/**
	 * Formulaire permettant à l'utilisateur de modifier le prix d'une boisson
	 * donnée
	 * 
	 * @param machine
	 *            la machine a café
	 * @param nomBoisson
	 *            le nom de la boisson à modifier
	 */
	public static void montrerFormulaireGestionPrix(String nomBoisson, Machine machine) {
		// saisie du montant
		System.out.println("Nouveau prix de la boisson : ");
		montrerFleche();
		int montant = Saisie.saisirPrixBoisson();

		machine.modifierPrixBoisson(nomBoisson, montant);
	}

	public static void montrerFleche() {
		System.out.print("-> ");
	}
}
