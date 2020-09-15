package it.uniroma3.tesi.calcolatori;

import static org.junit.Assert.*;

import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Test;

public class CalcolatoreEnergiaTest {
	
	private CalcolatoreEnergia ce= new CalcolatoreEnergia();
	private Complex[] vett= new Complex[2];

	@Before
	public void setUp() throws Exception {
		vett[0]= new Complex(1,1);
		vett[1]= new Complex(1,1);
	}

	@Test
	public void testCalcolaEnergiaSegnale1() {
		assertEquals(2,ce.calcolaEnergiaSegnale(vett),0.001);
	}
	
	@Test
	public void testCalcolaEnergiaSegnale2() {
		vett[0]= new Complex(1,2);
		vett[1]= new Complex(1,1);
		assertEquals(3.5,ce.calcolaEnergiaSegnale(vett),0.001);
	}

}
