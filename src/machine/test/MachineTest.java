package machine.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import machine.Machine;
import machine.boisson.Boisson;
import machine.boisson.Recette;
import machine.stock.Stock;

public class MachineTest {

	public Machine m;
	public Boisson b1, b2, b3, b4;
	public Stock s1, s2, s3, s4;
	public Recette r1, r2, r3, r4;
	
	@Before
	public void setUp() throws Exception {
		this.m = new Machine();
		
		this.r1 = new Recette(1, 4, 1, 0);
		this.r2 = new Recette(0, 4, 2, 2);
		this.r3 = new Recette(1, 4, 2, 1);
		this.r4 = new Recette(1, 2, 3, 4);
		
		this.b1 = new Boisson(r1, "Café Au Lait", 3);
		this.b2 = new Boisson(r2, "Chocolat Chaud",2);
		this.b3 = new Boisson(r3, "Capuccino", 4);
		this.b4 = new Boisson(r4, "Je sais pas", 12);
		
		this.m.ajouterBoisson(b1);
		this.m.ajouterBoisson(b2);
	}

	@Test
	public void testAjoutBoisson() {
		assertSame(3, this.m.ajouterBoisson(this.b3));
	}

	@Test
	public void testModifierRecetteBoisson1() {
		 assertEquals(4, this.m.modifierRecetteBoisson("Café Au Lait", 1, 2, 3, 4).getRecette().getQteChocolat());
	}
	
	@Test
	public void testModifierRecetteBoisson2() {
		 assertNull(this.m.modifierRecetteBoisson("test", 1, 2, 3, 4));
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
	public void testAjoutIngredient() {
		fail("Not yet implemented");
	}

	@Test
	public void testVerifierStockIngredient() {
		fail("Not yet implemented");
	}

}
