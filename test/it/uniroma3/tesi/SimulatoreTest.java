package it.uniroma3.tesi;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class SimulatoreTest {

	@Before
	public void setUp() throws Exception {
	}
//	
//	@Test
//	public void testOrdine() {
//		double[] vet1 = {2.4,7.8,1.2};
//		double[] vetOrd = {1.2,2.4,7.8};
//		
//		assertTrue(Arrays.equals(vetOrd,Simulatore.calcoloSogliaSimulazione(vet1)));
//	}
//
	
	@Test 
	public void testOrdinamento() {
		double[] vet1= {3.2,3,3.9,3.7,5,2};
		double[] vetOrd= {2,3,3.2,3.7,3.9,5};
		assertTrue(Arrays.equals(vetOrd,Simulatore.ordinamento(vet1)));
	}
}
