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
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.internal.series.Series;

import it.uniroma3.tesi.calcolatori.CalcolatoreEnergia;
import it.uniroma3.tesi.generatori.Generatore;
import it.uniroma3.tesi.generatori.GeneratoreGaussianoPotenzaUnitaria;
import it.uniroma3.tesi.generatori.GeneratoreUniformePotenzaUnitaria;
import it.uniroma3.tesi.utils.AlgebraVettori;

public class Main {
	
	public void GUI() {
		
		ThreadClass sim1 = new ThreadClass();
		ThreadClass sim2 = new ThreadClass();
		ThreadClass sim3 = new ThreadClass();
		ThreadClass sim4 = new ThreadClass();
		ThreadClass sim5 = new ThreadClass();
		ThreadClass sim6 = new ThreadClass();
		ThreadClass sim7 = new ThreadClass();
		
		sim1.start();
		sim2.start();
		sim3.start();
		sim4.start();
		sim5.start();
		sim6.start();
		sim7.start();
		
		try {	
			sim1.join();
			sim2.join();
			sim3.join();
			sim4.join();
			sim5.join();
			sim6.join();
			sim7.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		XYChart chart = new XYChartBuilder().width(800).height(600).title(getClass().getSimpleName()).xAxisTitle("SNR in dB").yAxisTitle("Probabilità Detection").build();
		
		chart.setTitle("DETECTION");
		chart.addSeries("Simulatore1", sim1.getSNR_dB(), sim1.getPD_sim());
		chart.addSeries("Simulatore2", sim2.getSNR_dB(), sim2.getPD_sim());
		chart.addSeries("Simulatore3", sim3.getSNR_dB(), sim3.getPD_sim());
		chart.addSeries("Simulatore4", sim4.getSNR_dB(), sim4.getPD_sim());
		chart.addSeries("Simulatore5", sim5.getSNR_dB(), sim5.getPD_sim());
		chart.addSeries("Simulatore6", sim4.getSNR_dB(), sim4.getPD_sim());
		chart.addSeries("Simulatore7", sim4.getSNR_dB(), sim4.getPD_sim());


		new SwingWrapper<XYChart>(chart).displayChart();
		
		//per applicare la regola della maggioranza ho bisogno di una funzione che mi dia la possibilità di unire tutte le probabilità di detection
		double[][] unioneConvertiti = new double[][] {sim1.getConvertiti(), sim2.getConvertiti(), sim3.getConvertiti()};
		String[] risultati =  {Simulatore.centroStella(sim1.getConvertiti()),Simulatore.centroStella(sim2.getConvertiti()),Simulatore.centroStella(sim3.getConvertiti()),Simulatore.centroStella(sim4.getConvertiti()),Simulatore.centroStella(sim5.getConvertiti()),Simulatore.centroStella(sim6.getConvertiti()),Simulatore.centroStella(sim7.getConvertiti())};
		
		System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
		System.out.println("------------------------------------------------------CENTRO STELLA-------------------------------------------------------------------");
		System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>\n\n");
	
		//APPLICAZIONE DELLA REGOLA DELLA MAGGIORANZA
		System.out.println(Simulatore.regolaMaggioranza(unioneConvertiti));
		
		//APPLICAZIONE REGOLA DELL' END
		System.out.println(Simulatore.regolaEND(risultati));
				
		//APPLICAZIONE REGOLA DELL'OR
		System.out.println(Simulatore.regolaOR(risultati));
	}

	public static void main(String[] args) throws IOException {
		new Main().GUI();	
	}
}
