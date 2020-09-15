package it.uniroma3.tesi;

import static org.junit.Assert.*;

import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.tesi.utils.AlgebraVettori;
import it.uniroma3.tesi.utils.SignalProcessing;

public class SimulatorePhiTest {
	
//	private SimulatorePhi sp;
	
	private final int N_PROVE = 5;
	private final int SNR_DB_LEN = 2;
	
	private double[] SNR_dB = new double[SNR_DB_LEN];
	
	private Complex[][] signal = new Complex[SNR_dB.length][N_PROVE];
	private Complex[][] rumore = new Complex[SNR_dB.length][N_PROVE];
	
	
	/* uso valori a caso, non compresi tra 0 e 1 */
	
	private void generaSegnali() {		
		/* Riga 0 */
		signal[0][0] = new Complex(1,1); // 1+1i
		signal[0][1] = new Complex(1,2); // 1+2i
		signal[0][2] = new Complex(2,1); // 2+1i
		signal[0][3] = new Complex(1,3); // 1+3i
		signal[0][4] = new Complex(2,2); // 2+2i
		
		/* Riga 1 */
		signal[1][0] = new Complex(1,5); // 1+5i
		signal[1][1] = new Complex(5,4); // 5+4i
		signal[1][2] = new Complex(2,2); // 2+2i
		signal[1][3] = new Complex(4,3); // 4+3i
		signal[1][4] = new Complex(4,2); // 4+2i
	}
	
	private void generaRumori() {
		/* Riga 0 */
		rumore[0][0] = new Complex(1,1); 
		rumore[0][1] = new Complex(0.1,0.2); 
		rumore[0][2] = new Complex(0.22,0.14); 
		rumore[0][3] = new Complex(0.3,0.4); 
		rumore[0][4] = new Complex(0.2,0.2); 
		
		/* Riga 1 */
		rumore[1][0] = new Complex(0.1,0.3);
		rumore[1][1] = new Complex(0.3,0.5);
		rumore[1][2] = new Complex(0.42,0.25);
		rumore[1][3] = new Complex(0.49,0.12);
		rumore[1][4] = new Complex(0.3,0.56);
	}

	@Before
	public void setUp() throws Exception {
		SNR_dB[0] = 1.2;
		SNR_dB[1] = 3.5;
		
		generaSegnali();
		generaRumori();
	//	sp = new SimulatorePhi(1, SNR_dB, 1, 10, 10, 10);
	}

	@Test
	public void testCalcoloEnergiaH1Matrice() {
	//	assertEquals(2,sp.energiaH1(AlgebraVettori.sommaMatriciComplessi(signal, rumore)).length);
		// calcolato con matlab
//		assertEquals(8.6416,sp.energiaH1(signal, rumore)[0],0);
	}
	
	

}
