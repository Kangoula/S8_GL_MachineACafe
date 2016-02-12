package fr.miage.machine.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import fr.miage.machine.modele.Machine;
import fr.miage.machine.modele.boisson.Boisson;
import fr.miage.machine.modele.boisson.Recette;
import fr.miage.machine.modele.stock.Stock;

public class MachineTest {

	public Machine m;
	public Boisson b1, b2, b3, b4;
	public Stock s1, s2, s3, s4, s5, s6;
	public Recette r1, r2, r3, r4;
	
	@Before
	public void setUp() throws Exception {
		this.m = new Machine();
		
		this.s1 = new Stock("café", 12);
		this.s2 = new Stock("LAIT", 50);
		this.s3 = new Stock("Chocolat", 14);
		this.s4 = new Stock("sucre", 43);
		this.s5 = new Stock("café", 10);
		
		this.r1 = new Recette();
		this.r2 = new Recette();
		this.r3 = new Recette();
		this.r4 = new Recette();
		
		r1.ajouterIngredient("Café", 2);
		r1.ajouterIngredient("LAit", 4);
		r1.ajouterIngredient("Chocolat", 0);
		r1.ajouterIngredient("SUCRE", 1);
		
		r4.ajouterIngredient("Café", 0);
		r4.ajouterIngredient("LAit", 4);
		r4.ajouterIngredient("Chocolat", 4);
		r4.ajouterIngredient("SUCRE", 1);
		
		this.b1 = new Boisson(r1, "Café Au Lait", 3);
		this.b2 = new Boisson(r2, "Chocolat Chaud",2);
		this.b3 = new Boisson(r3, "Capuccino", 4);
		this.b4 = new Boisson(r4, "Je sais pas", 12);
		
		this.m.ajouterBoisson(b1);
		this.m.ajouterBoisson(b2);
		
		this.m.ajouterStock(s1);
		this.m.ajouterStock(s2);
		this.m.ajouterStock(s3);
	} 
	
	@Test
	public void testAjoutBoisson1() {
		assertSame(3, this.m.ajouterBoisson(this.b3));
	}
	
	@Test
	public void testAjoutBoisson2() {
		this.m.ajouterBoisson(this.b3);
		assertSame(3, this.m.ajouterBoisson(this.b4));
	}

	@Test
	public void testModifierRecetteBoisson1() {
		 assertEquals(4, this.m.modifierRecetteBoisson("café au lait", this.r4).getQte("Chocolat"));
	}
	
	@Test
	public void testModifierRecetteBoisson2() {
		 assertNull(this.m.modifierRecetteBoisson("test", this.r4));
	}
	
	@Test
	public void testModifierPrixBoisson1() {
		 assertSame(1, this.m.modifierPrixBoisson("Café Au Lait", 1).getPrix());
	}

	@Test
	public void testModifierPrixBoisson2() {
		 assertSame(1, this.m.modifierPrixBoisson("Café Au Lait", 1).getPrix());
	}
	
	@Test
	public void testSupprimerBoisson1() {
		assertSame(1, this.m.supprimerBoisson("Café Au Lait"));
	}
	
	@Test
	public void testSupprimerBoisson2() {
		assertSame(2, this.m.supprimerBoisson("yolo"));
	}

	@Test
	public void testAjouterStock1() {
		assertSame(22, this.m.ajouterStock(s5).getQuantite());
	}
	
	@Test
	public void testAjouterStock2() {
		assertSame(43, this.m.ajouterStock(s4).getQuantite());
	}

	@Test
	public void testVerifierStockIngredient1() {
		assertSame(50, this.m.getStockIngredient("lait"));
	}
	
	@Test
	public void testVerifierStockIngredient2() {
		assertSame(-1, this.m.getStockIngredient("yolo"));
	}

}
