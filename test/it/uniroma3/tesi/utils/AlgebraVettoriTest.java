package it.uniroma3.tesi.utils;

import static org.junit.Assert.*;

import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Test;

public class AlgebraVettoriTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMoltVN() {
		Complex[] vettore = new Complex[2];
		vettore[0] = new Complex(1,1);
		vettore[1] = new Complex(2,2);
		
		Complex[] ris = AlgebraVettori.moltVN(vettore, 2);
		assertEquals(new Complex(2,2),ris[0]);
		assertEquals(new Complex(4,4),ris[1]);		
	}
	
	@Test
	public void testSommaVettoriComplessi() {
		Complex[] vettore1 = new Complex[2];
		vettore1[0] = new Complex(1,1);
		vettore1[1] = new Complex(2,2);
		Complex[] vettore2 = new Complex[2];
		vettore2[0] = new Complex(1,1);
		vettore2[1] = new Complex(2,2);
		
		Complex[] ris = AlgebraVettori.sommaVettoriComplessi(vettore1, vettore2);
		
		assertEquals(new Complex(2,2),ris[0]);
		assertEquals(new Complex(4,4),ris[1]);		
	}

}
