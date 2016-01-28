package machine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// création de la machine
		Machine m = new Machine();

		Scanner sc = new Scanner(System.in);
		String res;
		String arrow = "->";
		String menuAccueil = "1 - Demander une boisson\n2 - Gestion de la machine\n0 - Quitter :-(";
		String menuCommande = "Veuillez sélectionner votre boisson :\n";
		String menuGestion = "Que voulez-vous faire ?\n";
		
		System.out.println(menuAccueil);
		System.out.print(arrow);

		res = sc.next();

		while (res != "0") {

			switch (res) {
			case "1":
				System.out.println(menuCommande);
				break;
			case "2":
				System.out.println(menuGestion);
				break;
			default:
				System.out.println("Oups, mauvais numéro :/");
				System.out.println(menuAccueil);
				System.out.print(arrow);
				break;
			}
			res = sc.next();
		}

		System.out.println("À bientôt !");

	}

}
