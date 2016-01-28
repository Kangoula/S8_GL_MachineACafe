package machine.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import machine.Machine;
import machine.boisson.Boisson;
import machine.stock.Stock;

public class MachineTest {

	public Machine m;
	public Boisson b1, b2, b3, b4;
	public Stock s1, s2, s3, s4;
	
	@Before
	public void setUp() throws Exception {
		this.m = new Machine();
		this.b1 = new Boisson(1, 4, 1, 0, "Café Au Lait", 3);
		this.b2 = new Boisson(0,4,2,2,"Chocolat Chaud",2);
		this.b3 = new Boisson(1, 4, 2, 1, "Capuccino", 4);
		this.b4 = new Boisson(1, 1, 1, 1, "Je sais pas", 12);
	}

	@Test
	public void testAjoutBoisson() {
		assertSame(this.m.ajouterBoisson(this.b1), 1);
	}

	@Test
	public void testModifierBoisson() {
		fail("Not yet implemented");
	}

	@Test
	public void testSupprimerBoisson() {
		fail("Not yet implemented");
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
