package it.uniroma3.tesi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.complex.Complex;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.internal.series.Series;

import it.uniroma3.tesi.calcolatori.CalcolatoreEnergia;
import it.uniroma3.tesi.generatori.Generatore;
import it.uniroma3.tesi.generatori.GeneratoreGaussianoPotenzaUnitaria;
import it.uniroma3.tesi.generatori.GeneratoreUniformePotenzaUnitaria;
import it.uniroma3.tesi.utils.AlgebraVettori;

public class Main {

	public static void main(String[] args) throws IOException {
		
		double Pfa = Math.pow(10, -2);
		double[] SNR_dB = Generatore.generaDouble(-25, -5, 1);
		int Ps = 1;
		int N_prove_h0 = 10000;
		int N_prove_h1 = 1000;
		int N_campioni = 100;
		
		//System.out.println(ArrayUtils.toString(SNR_dB));
		
//		Simulatore sim = new Simulatore(Pfa, SNR_dB, Ps, N_prove_h0, N_prove_h1, N_campioni);
//
//		Complex[] segnale_PU = sim.segnale_PU();
//		double[] soglia_sim = sim.calcoloSogliaPerSimulazione();
//		double[] PD_sim = sim.calcolo_PdSim(segnale_PU, soglia_sim);
//		double[] PD_sim2 = sim.calcolo_PdSim(segnale_PU, soglia_sim);
//	
		
		ThreadClass sim1 = new ThreadClass();
		ThreadClass sim2 = new ThreadClass();
		ThreadClass sim3 = new ThreadClass();
		sim1.start();
		sim2.start();
		sim3.start();
	
		
//		System.out.println(Arrays.toString(soglia_sim));

//		System.out.println(Arrays.toString(sim.calcolo_PdSim(segnale_PU, soglia_sim)));   ATTENZIONE in questo modo ne ho una randomica sempre!

//		sim.ordinamento(PD_sim);
//		System.out.println(Arrays.toString(PD_sim));  //ATTENZIONE se questa riga verr� ripetuta otterr� risultati random ma tutti uguali a quelli di PD_sim 

//		double[] convertiti = sim.convertitorePdSim(PD_sim);
//		sim.ordinamento(convertiti);
//		System.out.println(Arrays.toString(convertiti));		
//		String finale = sim.centroStella(convertiti);
//		System.out.println(finale);
		
//		// Create Chart
//		XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", SNR_dB, PD_sim);
//		
//		// Show it
//		new SwingWrapper(chart).displayChart();
//
//		// Save it
//		BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapFormat.PNG);
//
//		// or save it in high-res
//		BitmapEncoder.saveBitmapWithDPI(chart, "./Sample_Chart_300_DPI", BitmapFormat.PNG, 300);
	}
}
