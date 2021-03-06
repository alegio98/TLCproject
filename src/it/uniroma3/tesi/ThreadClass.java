package it.uniroma3.tesi;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.pdfbox.contentstream.operator.text.NextLine;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

import it.uniroma3.tesi.generatori.Generatore;
import it.uniroma3.tesi.utils.AlgebraVettori;


public class ThreadClass extends Thread {

	double Pfa = Math.pow(10, -2);
	double[] SNR_dB = Generatore.generaDouble(-25, -5, 1);
	int Ps = 1;
	int N_prove_h0 = 10000;
	int N_prove_h1 = 1000;
	int N_campioni = 100;
	
	double[] PD_sim;
	double[] convertiti;
	
	@Override
	public void run() {	    //tutti i comandi dentro il run sono eseguiti quando si avvia il thread 	
		
		Simulatore sim = new Simulatore(Pfa, getSNR_dB(), Ps, N_prove_h0, N_prove_h1, N_campioni);
		Complex[] segnale_PU = sim.segnale_PU();
		double[] soglia_sim = sim.calcoloSogliaPerSimulazione();
		PD_sim = sim.calcolo_PdSim(segnale_PU, soglia_sim);
		double[] PD_simOrdinati = Simulatore.ordinamento(getPD_sim());
		convertiti = sim.convertitorePdSim(PD_simOrdinati);
		String finale = sim.detector(convertiti);

	}

	public double[] getSNR_dB() {
		return SNR_dB;
	}

	public double[] getPD_sim() {
		return PD_sim;
	}
	
	public double[] getConvertiti() {
		return convertiti;
	}
	
}
