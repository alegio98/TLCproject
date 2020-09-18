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
		sim1.start();
		sim2.start();
		sim3.start();
		
		try {
			sim1.join();
			sim2.join();
			sim3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		XYChart chart = new XYChartBuilder().width(800).height(600).title(getClass().getSimpleName()).xAxisTitle("SNR in dB").yAxisTitle("Probabilità Detection").build();
		chart.setTitle("DETECTION");
		chart.addSeries("Simulatore1", sim1.getSNR_dB(), sim1.getPD_sim());
		chart.addSeries("Simulatore2", sim2.getSNR_dB(), sim2.getPD_sim());
		chart.addSeries("Simulatore3", sim3.getSNR_dB(), sim3.getPD_sim());

		new SwingWrapper<XYChart>(chart).displayChart();
	}

	public static void main(String[] args) throws IOException {
		
		new Main().GUI();	
	}
}
