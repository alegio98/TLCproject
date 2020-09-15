package it.uniroma3.tesi.utils;

import static org.junit.Assert.*;

import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Test;

public class SignalProcessingTest {

	private SignalProcessing sp;
	
	@Before
	public void setUp() throws Exception {
		this.sp = new SignalProcessing();
	}

	@Test
	public void testCalcolaEnergiaSegnale() {
		Complex[] cmpx = new Complex[2];
		
		cmpx[0] = new Complex(1,2);
		cmpx[1] = new Complex(1,1);
		
		assertEquals(3.5,SignalProcessing.calcolaEnergiaSegnale(cmpx),0.00001);
	}

}
