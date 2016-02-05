package machine;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import machine.boisson.Boisson;
import machine.boisson.Recette;
import machine.menu.Menu;
import machine.stock.Stock;

public class Main {

	public static void main(String[] args) {

		// lancement de l'interface
		Menu.montrerMenuPrincipal(initialiserMachine());
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
		Stock s5 = new Stock("thé", 50);

		Recette r1 = new Recette();
		Recette r2 = new Recette();
		Recette r3 = new Recette();
		Recette r4 = new Recette();
		Recette r5 = new Recette();

		r1.ajouterIngredient("Café", 2);
		r1.ajouterIngredient("LAit", 2);
		r1.ajouterIngredient("Chocolat", 0);
		r1.ajouterIngredient("SUCRE", 1);
		r1.ajouterIngredient("thé", 0);
		
		r2.ajouterIngredient("Café", 0);
		r2.ajouterIngredient("LAit", 4);
		r2.ajouterIngredient("Chocolat", 3);
		r2.ajouterIngredient("SUCRE", 0);
		r2.ajouterIngredient("thé", 0);

		r3.ajouterIngredient("Café", 1);
		r3.ajouterIngredient("LAit", 4);
		r3.ajouterIngredient("Chocolat", 1);
		r3.ajouterIngredient("SUCRE", 1);
		r3.ajouterIngredient("thé", 0);
		
		r4.ajouterIngredient("thé", 2);
		r4.ajouterIngredient("café", 0);
		r4.ajouterIngredient("lait", 0);
		r4.ajouterIngredient("chocolat", 0);
		r4.ajouterIngredient("sucre", 1);
		
		r5.ajouterIngredient("thé", 2);
		r5.ajouterIngredient("café", 0);
		r5.ajouterIngredient("lait", 1);
		r5.ajouterIngredient("chocolat", 0);
		r5.ajouterIngredient("sucre", 1);

		Boisson b1 = new Boisson(r1, "Café Au Lait", 3);
		Boisson b2 = new Boisson(r2, "Chocolat Chaud", 2);
		Boisson b3 = new Boisson(r3, "Capuccino", 4);
		Boisson b4 = new Boisson(r4, "Thé à la Menthe", 2);
		Boisson b5 = new Boisson(r5, "Thé avec Lait", 3);

		m.setNombreBoissonMax(5);
		
		m.ajouterBoisson(b1);
		m.ajouterBoisson(b2);
		m.ajouterBoisson(b3);
		m.ajouterBoisson(b4);
		m.ajouterBoisson(b5);

		m.ajouterStock(s1);
		m.ajouterStock(s2);
		m.ajouterStock(s3);
		m.ajouterStock(s4);
		m.ajouterStock(s5);
		
		return m;
	}

}
