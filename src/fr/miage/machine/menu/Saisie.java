package fr.miage.machine.menu;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import fr.miage.machine.modele.boisson.Recette;

public class Saisie {

	private static Scanner sc = new Scanner(System.in);

	/**
	 * Affiche un message indiquant que le numéro du menu saisi est le mauvais
	 */
	public static void afficherErreurSaisie() {
		System.err.println("Mauvais numéro, veuillez recommencer");
	}

	/**
	 * Affiche un message infiquant que le montant saisi est invalide
	 */
	public static void afficherErreurMontant() {
		System.err.println("Le montant n'est pas valide, veuillez recommencer");
	}

	/**
	 * Affiche un message indiquant que la chaine de caractères saisis est
	 * invalide
	 */
	public static void afficherErreurChaine() {
		System.err.println("Vous êtes sérieux ? Essayez encore...");
	}

	/**
	 * Permet à l'utilisateur de saisir le prix d'une boisson. Le montant saisi
	 * doit être compris entre 1 et 15
	 * 
	 * @return le montant saisi s'il est correct
	 */
	public static int saisirPrixBoisson() {
		return saisirEntier(1, 15);
	}

	/**
	 * Permet à l'utilisateur de saisie une quantité d'ingrédients. La quantité
	 * saisie doit être comprise entre 0 et 5000.
	 * 
	 * @return la quantité saisie si elle est correcte
	 */
	public static int saisirQuantiteIngredient() {
		return saisirEntier(0, 5000);
	}

	public static int saisirQuantiteSucre() {
		return saisirEntier(0, 5);
	}
	
	/**
	 * Vérifie si la chaine de caractères saisie par l'utilisateur est bien
	 * valide. Elle doit être différente d'un " " et a une taille maximum. Si la
	 * chaine n'est pas valide, on demande à nouveau à l'utilisateur de saisir à
	 * nouveau une chaine.
	 * 
	 * @param tailleMax
	 *            la taille maximum de la chaine saisie.
	 * @return la chaine saisie si elle est correcte
	 */
	public static String saisirChaine(int tailleMax) {
		String saisie;

		while (true) {
			saisie = sc.next();
			if (!saisie.equals(" ") && (saisie.length() <= tailleMax)) {
				return saisie;
			} else {
				afficherErreurChaine();
				Menu.montrerFleche();
			}
		}
	}

	/**
	 * Vérifie si la saisie de l'utilisateur est bien un entier. Tant que la
	 * saisie n'est pas correcte, on redemande à l'utilisateur d'entrer une
	 * valeur
	 * 
	 * @return l'entier saisi lorsqu'il est correct
	 */
	public static int saisirEntier() {
		int nb;

		while (true) {
			try {
				nb = sc.nextInt();
				return nb;
			} catch (InputMismatchException ime) {
				afficherErreurChaine();
				Menu.montrerFleche();
				nb = sc.nextInt();
			}
		}
	}

	/**
	 * Vérifie si l'entier saisi est compris entre deux valeurs. Tant que la
	 * saisie n'est pas correcte, on redemande à l'utilisateur de rentrer une
	 * valeur
	 * 
	 * @param min
	 *            le minimum qu'il est possible de saisir
	 * @param max
	 *            le maximum qu'il est possible de saisir
	 * @return le montant saisi lorsqu'il est correct
	 */
	public static int saisirEntier(int min, int max) {
		int nb;

		while (true) {
			try {
				nb = sc.nextInt();
				if ((nb >= min) && (nb <= max)) {
					return nb;
				} else {
					afficherErreurChaine();
					Menu.montrerFleche();
				}
			} catch (InputMismatchException ime) {
				afficherErreurChaine();
				Menu.montrerFleche();
				nb = sc.nextInt();
			}
		}
	}

	/**
	 * Saisie de la recette par l'utilisateur en fonction d'une liste
	 * d'ingrédients donnée
	 * 
	 * @param listeIngredients
	 *            la liste d'ingrédients possibles
	 * @return la recette complétée
	 */
	public static Recette saisirRecette(Set<String> listeIngredients) {
		int montant;
		Recette r = new Recette();

		while (true) {
			for (String key : listeIngredients) {

				System.out.println("Quantité de " + key);
				Menu.montrerFleche();
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
}
