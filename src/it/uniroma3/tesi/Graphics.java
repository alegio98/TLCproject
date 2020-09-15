package it.uniroma3.tesi;

import org.apache.commons.math3.complex.Complex;

import it.uniroma3.tesi.generatori.Generatore;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.chart.CategoryAxis;
//import javafx.scene.chart.LineChart;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.XYChart;
//import javafx.scene.chart.XYChart.Series;
//import javafx.stage.Stage;

import org.apache.commons.math3.complex.Complex;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;


 
 
public class Graphics /*extends Application */{
 /*
	double Pfa = Math.pow(10, -2);
	double[] SNR_dB = Generatore.generaDouble(-25, -5, 1);
	int Ps = 1;
	int N_prove_h0 = 10000;
	int N_prove_h1 = 1000;
	int N_campioni = 100;
	
	//System.out.println(ArrayUtils.toString(SNR_dB));
	
	Simulatore sim = new Simulatore(Pfa, SNR_dB, Ps, N_prove_h0, N_prove_h1, N_campioni);

	Complex[] segnale_PU = sim.segnale_PU();
	double[] soglia_sim = sim.calcoloSogliaPerSimulazione();
   
	@Override public void start(Stage stage) {
        stage.setTitle("Line Chart Sample");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
         xAxis.setLabel("Month");
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis); 
       
        lineChart.setTitle("Stock Monitoring, 2010");
                          
      //  XYChart.Series series1 = new XYChart.Series();
        Series<Number, Number> series104 = new XYChart.Series();
        series104.getData().add(new XYChart.Data(SNR_dB, sim.calcolo_PdSim(segnale_PU, soglia_sim)));
                
        Scene scene  = new Scene(lineChart,800,600);       
        lineChart.getData().add(series104);
       
        stage.setScene(scene);
        stage.show();
    }
 
 
    public static void main(String[] args) {
        launch(args);
    }*/
}