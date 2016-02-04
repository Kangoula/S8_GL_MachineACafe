package machine.menu;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import machine.boisson.Recette;

public class Saisie {

	public static Scanner sc = new Scanner(System.in);
	
	public static void afficherErreurSaisie() {
		System.err.println("Mauvais numéro, veuillez recommencer");
	}

	public static void afficherErreurMontant() {
		System.err.println("Le montant n'est pas valide, veuillez recommencer");
	}
	
	public static void afficherErreurChaine(){
		System.out.println("Vous êtes sérieux ? Essayez encore...");
	}
	

	public static int saisirPrixBoisson() {
		return saisirEntier(1, 15);
	}

	public static int saisirQuantiteIngredient() {
		return saisirEntier(0, 5000);
	}
	
	public static String saisirChaine(int max){
		String saisie;
		
		while(true){
			saisie = sc.next();
			if(!saisie.equals(" ") && (saisie.length() <= max)){
				return saisie;
			}
			else {
				afficherErreurChaine();
				Menu.montrerFleche();
			}
		}
	}
	
	/**
	 * Vérifie si la saisie de l'utilisateur est bien un entier.
	 * Tant que la saisie n'est pas correcte, on redemande à l'utilisateur d'entrer une valeur
	 * @return l'entier saisi lorsqu'il est correct
	 */
	public static int saisirEntier() {
		
		while (true) {
			try {
				int nb = sc.nextInt();
				return nb;
			} catch (InputMismatchException ime) {
				afficherErreurChaine();
			}
		}
	}

	/**
	 * Vérifie si l'entier saisi est compris entre deux valeurs.
	 * Tant que la saisie n'est pas correcte, on redemande à l'utilisateur de rentrer une valeur
	 * @param min le minimum qu'il est possible de saisir
	 * @param max le maximum qu'il est possible de saisir
	 * @return le montant saisi lorsqu'il est correct
	 */
	public static int saisirEntier(int min, int max) {
		while (true) {
			try {
				int nb = sc.nextInt();
				if ((nb >= min) && (nb <= max)) {
					return nb;
				} else {
					afficherErreurChaine();
					Menu.montrerFleche();
				}
			} catch (InputMismatchException ime) {
				afficherErreurChaine();
				Menu.montrerFleche();
				sc.nextInt();
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
