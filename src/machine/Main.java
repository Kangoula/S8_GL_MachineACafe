package machine;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	/**
	 * Affiche le premier niveau du menu : le menu principal
	 */
	public static void montrerMenuPrincipal() {
		System.out.println("-- Menu Principal --\n");
		System.out.println("1 - Commander");
		System.out.println("2 - Gestion de la machine");
		System.out.println("0 - Quitter");
		montrerFleche();

		String res = sc.next();

		switch (res) {
		case "1":
			montrerMenuCommande();
                        montrerMenuPrincipal();
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
		System.out.println("\n-- Commander votre boisson préférée --");
                
                // Choisir une boisson dans la liste des boissons disponibles
                String choix = choisirBoisson();
                montrerFleche();
                
                Boisson b = Main.m.getListeBoissons().get(choix);
                
                // Demander à l'utilisateur de payer
                System.out.println("Combien tu lâches ?");
                int montantPaye = fairePayer(b,0);
                
                // Vérifier que les stocks sont suffisants
                boolean stocksOK = verifierStocksBoisson(b);
                
                if(stocksOK) {
                    // Rendre la monnaie
                    int montantARendre = rendreMonnaie(b,montantPaye);
                    System.out.println("La machine va vous rendre "+montantARendre+" euros.");
                    
                    // Fabriquer la boisson (décrémenter les stocks + attente)
                    fabriquerBoisson(b);
                    System.out.println("\nLa machine vient de vous donner votre boisson. Ce serait une bonne idée de la prendre !\n");
                    
                } else {
                    // Afficher un message :-(
                    System.out.println("Plus de stock");
                    
                    // Rendre le montant payé
                    System.out.println("La machine va vous rendre"+montantPaye+"euros.");
                }
                
                // Retour à l'accueil
                
	}

        public static void fabriquerBoisson(Boisson b){
            // On récupère la recette 
            Recette r = b.getRecette();
            // On prépare l'itération sur les ingrédients de la recette de la boisson en paramètre
            Set set = r.getIngredients().keySet();
            Iterator it = set.iterator();
                // Pour chaque ingrédient
                while(it.hasNext()) {
                    String nomIngredient = (String)it.next();
                    // Quel est son stock ?
                    int stockDisponible = Main.m.getStockIngredient(nomIngredient);
                    // Combien en a-t-on besoin ?
                    int besoin = r.getIngredients().get(nomIngredient);
                    // On décrémente le stock de l'ingrédient
                    Main.m.setStockIngredient(nomIngredient, stockDisponible-besoin);
                }
            for(int i=0;i<3;i++) {
                System.out.print("...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        /**
         * Méthode qui permet de connaitre le montant à rendre en fonction
         * de la boisson choisie et du montant payé par l'utilisateur
         * @param b
         * @param montantPaye
         * @return 
         */
        public static int rendreMonnaie(Boisson b, int montantPaye) {
            return (montantPaye - b.getPrix());
        }
        
        /**
         * Méthode permettant de vérifier que les ingrédients d'une boisson
         * sont en stock
         * @param b
         * @return 
         */
        public static boolean verifierStocksBoisson(Boisson b) {
            // On récupère la recette 
            Recette r = b.getRecette();
            // On prépare l'itération sur les ingrédients de la recette de la boisson en paramètre
            Set set = r.getIngredients().keySet();
            Iterator it = set.iterator();
                // Pour chaque ingrédient
                while(it.hasNext()) {
                    String i = (String)it.next();
                    // Quel est son stock ?
                    int stockDisponible = Main.m.getStockIngredient(i);
                    // Combien en a-t-on besoin ?
                    int besoin = r.getIngredients().get(i);
                    // OK ?
                    if(stockDisponible>=besoin) {
                        return true;
                    }
                }
            return false;
        }
        
        /**
         * Méthode permettant de faire payer un utilisateur
         * (Boucle jusqu'à ce qu'il donne le bon montant).
         * @param b
         * @param montantPaye
         * @return 
         */
        public static int fairePayer(Boisson b, int montantPaye) {
            int prixBoisson = b.getPrix();
            if(montantPaye >= prixBoisson) {
                return montantPaye;
            } else {
                if(montantPaye != 0) {
                    System.out.println("Il va en falloir encore un peu si tu le veux ton truc... Combien tu lâches ?");
                }
                int montant = saisirEntier(0, 15);
                return fairePayer(b,montantPaye+montant);
            }
        }
        
        /**
         * Méthode qui permet à l'utilisateur de choisir une boisson
         * dans la liste des boissons de la machine
         * @return 
         */
	public static String choisirBoisson() {
		int nbBoissons = Main.m.getListeBoissons().size();
		String tab[] = new String[nbBoissons + 1];
		Set set = Main.m.getListeBoissons().keySet();
		Iterator it = set.iterator();
		int i = 1;
		System.out.println("0 - Retour");
		tab[0] = "Retour";
                String boisson;
		while (it.hasNext()) {
			tab[i] = (String) it.next();
                        boisson = Main.m.getListeBoissons().get(tab[i]).toString();
			System.out.println(i + " - " + boisson);
			i++;
		}
		i = Main.saisirEntier(0, tab.length - 1);
		return tab[i];
	}

	public static int saisirEntier() {
		while (true) {
			try {
				int nb = sc.nextInt();
				return nb;
			} catch (InputMismatchException ime) {
				System.out.println("Vous êtes sérieux ? Essayez encore...");
			}
		}
	}

	public static int saisirEntier(int min, int max) {
		while (true) {
			try {
				int nb = sc.nextInt();
				if ((nb >= min) && (nb <= max)) {
					return nb;
				} else {
					System.out.println("Vous êtes sérieux ? Essayez encore...");
				}
			} catch (InputMismatchException ime) {
				System.out.println("Vous êtes sérieux ? Essayez encore...");
				sc.next();
			}
		}
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
		String choix;

		switch (res2) {
		case "0":
			montrerMenuPrincipal();
			break;
		case "1":
			montrerFormulaireAjoutBoisson();
			montrerMenuGestion();
			break;
		case "2":
			choix = choisirBoisson();
			if (!choix.equals("Retour")) {
				m.supprimerBoisson(choix);
			}
			montrerMenuGestion();
			break;
		case "3":
			choix = choisirBoisson();
			if (!choix.equals("Retour")) {
				montrerMenuModificationBoisson(choix);
			}
			montrerMenuGestion();
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

		// demander prix
		System.out.println("Prix de la boisson : ");
		montrerFleche();
		prix = saisirEntier(1, 15);

		/*
		 * try { prix = sc.nextInt(); while (!Caisse.verifierMontant(prix)) {
		 * afficherErreurMontant(); montrerFleche(); prix = sc.nextInt(); } }
		 * catch (InputMismatchException e) { afficherErreurMontant();
		 * montrerFleche(); prix = sc.nextInt(); }
		 */

		// création et ajout de la boisson
		b = new Boisson(r, nom, prix);

		if (m.ajouterBoisson(b) == -1) {
			System.out.println("Le nombre maximum de boisson est déjà atteint,\nla boisson ne peut pas être ajoutée");
			montrerMenuGestion();
		}

	}

	public static int saisirPrixBoisson() {
		return saisirEntier(1, 15);
	}

	public static int saisirQuantiteIngredient() {
		return saisirEntier(0, 5000);
	}

	/**
	 * Formulaire permettant d'entrer une nouvelle recette dans la machine Il
	 * est utilisé uniquement dans le cadre de la création d'une nouvelle
	 * boisson
	 * 
	 * @return la nouvelle recette créée
	 */
	private static Recette demanderRecette() {

		// on indique la quantité de chaque ingrédient que l'on a en stock
		Set<String> keyset = m.getStockIngredients().keySet();

		return saisirRecette(keyset);
	}

	/**
	 * Saisie de la recette par l'utilisateur en fonction d'une liste d'ingrédients donnée
	 * @param listeIngredients la liste d'ingrédients possibles
	 * @return la recette complétée
	 */
	private static Recette saisirRecette(Set<String> listeIngredients){
		int montant;
		Recette r = new Recette();
		
		while (true) {
			for (String key : listeIngredients) {

				System.out.println("Quantité de " + key);
				montrerFleche();
				montant = saisirQuantiteIngredient();
				r.ajouterIngredient(key, montant);
			}

			// on vérifie qu'on a au moins 1 ingrédient dont la quantité est > 0
			if (!r.estValide()) {
				System.out.println("La recette doit avoir au moins un de ses ingrédients avec une quantité > 1");
			} else {
				return r;
			}
		}
	}
	
	/**
	 * Affiche le niveau 3 du menu : la gestion des stocks L'utilisateur pourra
	 * consulter les sotcks et/ou ajouter des ingrédients à la machine
	 */
	private static void montrerMenuGestionStocks() {
		
		System.out.println("-- Gestion des stocks --");
		System.out.println("1 - Vérifier l'état des stocks");
		System.out.println("2 - Ajouter du stock d'un ingrédient existant");
                System.out.println("3 - Ajouter un nouvel ingrédient");
		System.out.println("0 - Retour");
		montrerFleche();
		
		String res = sc.next();
		
		switch(res){
		case "0":
			montrerMenuGestion();
			break;
		case "1":
			montrerEtatStocks();
			break;
		case "2":
			montrerMenuAjouterStock();
			break;
                case "3":
                        montrerMenuAjouterIngredient();
                        break;
		default:
			montrerMenuGestion();
			break;
		}
	}

        public static void montrerMenuAjouterStock() {
            //TODO
        }
        
        public static void montrerMenuAjouterIngredient() {
            //TODO
            String nom;
            int quantite;

            // demander le nom
            System.out.println("Nom du nouvel ingrédient : ");
            montrerFleche();
            nom = sc.next();

            // demander quantité
            System.out.println("Quantité initiale ");
            montrerFleche();
            quantite = saisirQuantiteIngredient();
            
            Stock stock = new Stock(nom,quantite);
            
            // ajout
            Main.m.getStockIngredients().put(nom, stock);
        }
        
	/**
	 * Affiche l'état actuel des sotcks d'ingrédients de la machine
	 */
	private static void montrerEtatStocks() {
		
		System.out.println("-- Etat des stocks --");
		
		for(String key : m.getStockIngredients().keySet()){
			System.out.print(key);
			System.out.print(" : ");
			System.out.print(m.getStockIngredient(key));
			System.out.println(" unités");
		}
		System.out.println("0 - Retour");
		montrerFleche();
		
		while(!sc.next().equals("0")){
			System.out.println("Biiiiip, mauvaise réponse, essaie encore...");
			montrerFleche();
		}
		montrerMenuGestionStocks();
	}

	/**
	 * Affiche le niveau 3 du menu : la modification d'une boisson Après avoir
	 * sélectionné sa boisson, L'utilisateur choisi ce qu'il veut modifier
	 */
	private static void montrerMenuModificationBoisson(String choix) {

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
			montrerMenuModifierRecette(choix);
			break;
		case "2":
			montrerFormulaireGestionPrix(choix);
			break;
		default:
			afficherErreurSaisie();
			montrerMenuModificationBoisson(choix);
			break;
		}
	}

	/**
	 * Formulaire permettant à l'utilisateur de modifier le prix d'une boisson
	 * choisie
	 * 
	 * @param nomBoisson le nom de la boisson choisie
	 */
	public static void montrerMenuModifierRecette(String nomBoisson) {
		System.out.println("-- Modifier la recette de : "+ nomBoisson +"--");
		
		//récupération de ingrédients de la boisson choisie
		Set<String> keyset = m.getListeBoissons().get(nomBoisson).getRecette().getIngredients().keySet();
		
		Recette r = m.modifierRecetteBoisson(nomBoisson, saisirRecette(keyset));
		
		if(r == null){
			System.err.println("Erreur, la boisson n'existe pas");
		}
		else {
			System.out.println(r);
		}
		montrerMenuModificationBoisson(nomBoisson);
	}

	/**
	 * Formulaire permettant à l'utilisateur de modifier le prix d'une boisson
	 * donnée
	 * 
	 * @param nomBoisson le nom de la boisson à modifier
	 */
	public static void montrerFormulaireGestionPrix(String nomBoisson) {
		// saisie du montant
		System.out.println("Nouveau prix de la boisson : ");
		montrerFleche();
		int montant = saisirPrixBoisson();
	}

	public static void montrerFleche() {
		System.out.print("-> ");
	}

	public static void afficherErreurSaisie() {
		System.err.println("Mauvais numéro, veuillez recommencer");
	}

	public static void afficherErreurMontant() {
		System.err.println("Le montant n'est pas valide, veuillez recommencer");
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
