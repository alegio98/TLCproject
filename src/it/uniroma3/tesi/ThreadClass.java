package it.uniroma3.tesi;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

import it.uniroma3.tesi.generatori.Generatore;
import it.uniroma3.tesi.utils.AlgebraVettori;
import it.uniroma3.tesi.utils.SignalProcessing;

public class ThreadClass extends Thread {

	double Pfa = Math.pow(10, -2);
	double[] SNR_dB = Generatore.generaDouble(-25, -5, 1);
	int Ps = 1;
	int N_prove_h0 = 10000;
	int N_prove_h1 = 1000;
	int N_campioni = 100;

	Simulatore sim = new Simulatore(Pfa, SNR_dB, Ps, N_prove_h0, N_prove_h1, N_campioni);
	
	Complex[] segnale_PU = sim.segnale_PU();
	double[] soglia_sim = sim.calcoloSogliaPerSimulazione();
	double[] PD_sim = sim.calcolo_PdSim(segnale_PU, soglia_sim);
	double[] PD_simOrdinati = Simulatore.ordinamento(PD_sim);
	double[] convertiti = sim.convertitorePdSim(PD_simOrdinati);
	String finale = sim.centroStella(convertiti);	
	
	
	public void run() {
		
		System.out.println(Arrays.toString(PD_simOrdinati));
		System.out.println(Arrays.toString(convertiti));
		System.out.println(finale);
		
		
		// Create Chart
		XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", SNR_dB, PD_sim);
		
		// Show it
		new SwingWrapper(chart).displayChart();

		// Save it
		try {
			BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapFormat.PNG);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// or save it in high-res
		try {
			BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapFormat.PNG, 300);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
