package it.uniroma3.tesi;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.tesi.utils.AlgebraVettori;

public class SimulatoreTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testOnOFF() {
		String[] stringhe = {"Applicando la regola della MAGGIORANZA Il segnale risulta essere PRESENTE.", 
				                   "Applicando la regola dell'END il segnale risulta essere PRESENTE.",
				                       "Applicando la regola dell'OR il segnale risulta essere ASSENTE."};
		double[] vettore = {1,1,-1};
		
		assertEquals(Arrays.toString(vettore),Arrays.toString(Simulatore.onOffsingle(stringhe)));
		}
	
	
	@Test 
	public void testOrdinamento() {
		double[] vet1= {3.2,3,3.9,3.7,5,2};
		double[] vetOrd= {2,3,3.2,3.7,3.9,5};
		assertTrue(Arrays.equals(vetOrd,Simulatore.ordinamento(vet1)));
	} 
	
	@Test
	public void testMaggioranza3() {
		double[][] start = {{1,-1,1}, {1,1,1}, {-1,-1,-1}};
		double[] finale = {1,1,-1};
		assertEquals(Arrays.toString(finale),Arrays.toString(Simulatore.ooMagg(start)));
	}
	
	@Test
	public void testSNRmaggioranza() {
		double[][] start= {{0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				           {0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				           {0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				           {0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				           {0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				           {0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						   {0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
		
		
		double[] end =     {0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		
		assertEquals(Arrays.toString(end), Arrays.toString(Simulatore.SNRmaggioranza(start)));

	}
	
	@Test
	public void testSNRand() {
		double[][] start= {{0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				           {0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				           {0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				           {0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				           {0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				           {0,0,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
						   {0,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
		
		
		double[] end =     {0,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		
		assertEquals(Arrays.toString(end), Arrays.toString(Simulatore.SNRand(start)));

	}
	
	@Test
	public void testSNRor() {
		double[][] start= {{0,0,1,0,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1},
				           {0,0,1,0,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1},
				           {0,0,1,0,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1},
				           {1,0,1,0,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1},
				           {0,0,1,0,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1},
				           {0,0,1,0,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1},
						   {0,0,0,0,0,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1}};
		
		
		double[] end =     {1,0,1,0,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1};
		
		assertEquals(Arrays.toString(end), Arrays.toString(Simulatore.SNRor(start)));

	}

	
	
}
