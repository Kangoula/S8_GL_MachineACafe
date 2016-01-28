package machine.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import machine.caisse.Caisse;

public class CaisseTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testRenduMonnaie1() {
		assertSame(Caisse.renduMonnaie(3, 2), 1);
	}
	
	@Test
	public void testRenduMonnaie2() {
		assertSame(Caisse.renduMonnaie(1, 3), -2);
	}

}
