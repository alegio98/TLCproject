package it.uniroma3.tesi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.complex.Complex;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
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
		ThreadClass sim8 = new ThreadClass();
		ThreadClass sim9 = new ThreadClass();
		ThreadClass sim10 = new ThreadClass();
		ThreadClass sim11 = new ThreadClass();
		ThreadClass sim12 = new ThreadClass();
		ThreadClass sim13 = new ThreadClass();
		ThreadClass sim14 = new ThreadClass();
		ThreadClass sim15 = new ThreadClass();

		sim1.start();
		sim2.start();
		sim3.start();
		sim4.start();
		sim5.start();
		sim6.start();
		sim7.start();
		sim8.start();
		sim9.start();
		sim10.start();
		sim11.start();
		sim12.start();
		sim13.start();
		sim14.start();
		sim15.start();
	
		
		try {
			
			sim1.join();
			sim2.join();
			sim3.join();
			sim4.join();
			sim5.join();
			sim6.join();
			sim7.join();
			sim8.join();
			sim9.join();
			sim10.join();
			sim11.join();
			sim12.join();
			sim13.join();
			sim14.join();
			sim15.join();
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		XYChart chart1 = new XYChartBuilder().width(800).height(600).title(getClass().getSimpleName()).xAxisTitle("SNR in dB").yAxisTitle("Probabilità Detection").build();

		XYChart chart2 = new XYChartBuilder().width(800).height(600).title(getClass().getSimpleName()).xAxisTitle("SNR in dB").yAxisTitle("Probabilità Detection").build();

	//	XYChart chart3 = new XYChartBuilder().width(800).height(600).title(getClass().getSimpleName()).xAxisTitle("SNR in dB").yAxisTitle("Probabilità Detection").build();
	    
		chart1.setTitle("DETECTION");
		chart1.addSeries("Detector1", sim1.getSNR_dB(), sim1.getPD_sim());
		chart1.addSeries("Detector2", sim2.getSNR_dB(), sim2.getPD_sim());
		chart1.addSeries("Detector3", sim3.getSNR_dB(), sim3.getPD_sim());
		
		chart2.addSeries("Detector1", sim1.getSNR_dB(), sim1.getPD_sim());
		chart2.addSeries("Detector2", sim2.getSNR_dB(), sim2.getPD_sim());
		chart2.addSeries("Detector3", sim3.getSNR_dB(), sim3.getPD_sim());
		chart2.addSeries("Detector4", sim4.getSNR_dB(), sim4.getPD_sim());
		chart2.addSeries("Detector5", sim5.getSNR_dB(), sim5.getPD_sim());
		chart2.addSeries("Detector6", sim6.getSNR_dB(), sim6.getPD_sim());
		chart2.addSeries("Detector7", sim7.getSNR_dB(), sim7.getPD_sim());
		
//		chart3.addSeries("Detector1", sim1.getSNR_dB(), sim1.getPD_sim());
//		chart3.addSeries("Detector2", sim2.getSNR_dB(), sim2.getPD_sim());
//		chart3.addSeries("Detector3", sim3.getSNR_dB(), sim3.getPD_sim());
//		chart3.addSeries("Detector4", sim4.getSNR_dB(), sim4.getPD_sim());
//		chart3.addSeries("Detector5", sim5.getSNR_dB(), sim5.getPD_sim());
//		chart3.addSeries("Detector6", sim6.getSNR_dB(), sim6.getPD_sim());
//		chart3.addSeries("Detector7", sim7.getSNR_dB(), sim7.getPD_sim());
//		chart3.addSeries("Detector8", sim8.getSNR_dB(), sim8.getPD_sim());
//		chart3.addSeries("Detector9", sim9.getSNR_dB(), sim9.getPD_sim());
//		chart3.addSeries("Detector10", sim10.getSNR_dB(), sim10.getPD_sim());
//		chart3.addSeries("Detector11", sim11.getSNR_dB(), sim11.getPD_sim());
//		chart3.addSeries("Detector12", sim12.getSNR_dB(), sim12.getPD_sim());
//		chart3.addSeries("Detector13", sim13.getSNR_dB(), sim13.getPD_sim());
//		chart3.addSeries("Detector14", sim14.getSNR_dB(), sim14.getPD_sim());
//		chart3.addSeries("Detector15", sim15.getSNR_dB(), sim15.getPD_sim());


		new SwingWrapper<XYChart>(chart1).displayChart();
		new SwingWrapper<XYChart>(chart2).displayChart();
//		new SwingWrapper<XYChart>(chart3).displayChart();

		//risultati per ogni ordine di simulazione

		String[] risultati1={Simulatore.detector(sim1.getConvertiti()),Simulatore.detector(sim2.getConvertiti()),Simulatore.detector(sim3.getConvertiti())};
		String[] risultati2={Simulatore.detector(sim1.getConvertiti()),Simulatore.detector(sim2.getConvertiti()),Simulatore.detector(sim3.getConvertiti()),Simulatore.detector(sim4.getConvertiti()),Simulatore.detector(sim5.getConvertiti()),Simulatore.detector(sim6.getConvertiti()),Simulatore.detector(sim7.getConvertiti())};
	//	String[] risultati3 ={Simulatore.detector(sim1.getConvertiti()),Simulatore.detector(sim2.getConvertiti()),Simulatore.detector(sim3.getConvertiti()),Simulatore.detector(sim4.getConvertiti()),Simulatore.detector(sim5.getConvertiti()),Simulatore.detector(sim6.getConvertiti()),Simulatore.detector(sim7.getConvertiti()),Simulatore.detector(sim8.getConvertiti()),Simulatore.detector(sim9.getConvertiti()),Simulatore.detector(sim10.getConvertiti()),Simulatore.detector(sim11.getConvertiti()),Simulatore.detector(sim12.getConvertiti()),Simulatore.detector(sim13.getConvertiti()),Simulatore.detector(sim14.getConvertiti()),Simulatore.detector(sim15.getConvertiti())};
		
		double[][] adSNR3= {sim1.convertiti, sim2.convertiti, sim3.convertiti};
		double[] risultatiSNRmagg3 = Simulatore.SNRmaggioranza(adSNR3);
		double[] risultatiSNRor3 = Simulatore.SNRor(adSNR3);
		double[] risultatiSNRand3 = Simulatore.SNRand(adSNR3);
		
		XYChart chartS3 = new XYChartBuilder().width(800).height(600).title(getClass().getSimpleName()).xAxisTitle("SNR in dB").yAxisTitle("ON - OFF").build();
		chartS3.setTitle("calcolo con 3 simulatori");
		chartS3.addSeries("maggioranza", sim1.getSNR_dB(), risultatiSNRmagg3);
		chartS3.addSeries("or", sim1.getSNR_dB(), risultatiSNRor3);
		chartS3.addSeries("and", sim1.getSNR_dB(), risultatiSNRand3);
		
		new SwingWrapper<XYChart>(chartS3).displayChart();
		
		
		
		
		double[][] adSNR7= {sim1.convertiti, sim2.convertiti, sim3.convertiti, sim4.convertiti, sim5.convertiti, sim6.convertiti,sim7.convertiti};
		double[] risultatiSNRmagg7 = Simulatore.SNRmaggioranza(adSNR7);
		double[] risultatiSNRor7 = Simulatore.SNRor(adSNR7);
		double[] risultatiSNRand7 = Simulatore.SNRand(adSNR7);
		
		
		XYChart chartS7 = new XYChartBuilder().width(800).height(600).title(getClass().getSimpleName()).xAxisTitle("SNR in dB").yAxisTitle("ON - OFF").build();
		chartS7.setTitle("calcolo con 7 simulatori");
		chartS7.addSeries("maggioranza", sim1.getSNR_dB(), risultatiSNRmagg7);
		chartS7.addSeries("or", sim1.getSNR_dB(), risultatiSNRor7);
		chartS7.addSeries("and", sim1.getSNR_dB(), risultatiSNRand7);
		
		new SwingWrapper<XYChart>(chartS7).displayChart();
		
		
//		double[][] adSNR15= {sim1.convertiti, sim2.convertiti, sim3.convertiti, sim4.convertiti, sim5.convertiti, sim6.convertiti,sim7.convertiti,sim8.convertiti,sim9.convertiti, sim10.convertiti,sim11.convertiti,sim12.convertiti,sim13.convertiti,sim14.convertiti,sim15.convertiti};
//		double[] risultatiSNRmagg15 = Simulatore.SNRmaggioranza(adSNR15);
//		double[] risultatiSNRor15 = Simulatore.SNRor(adSNR15);
//		double[] risultatiSNRand15 = Simulatore.SNRand(adSNR15);
//		
//		
//		XYChart chartS15 = new XYChartBuilder().width(800).height(600).title(getClass().getSimpleName()).xAxisTitle("SNR in dB").yAxisTitle("ON - OFF").build();
//		chartS15.setTitle("calcolo con 13 simulatori");
//		chartS15.addSeries("maggioranza", sim1.getSNR_dB(), risultatiSNRmagg15);
//		chartS15.addSeries("or", sim1.getSNR_dB(), risultatiSNRor15);
//		chartS15.addSeries("and", sim1.getSNR_dB(), risultatiSNRand15);
//		
//		new SwingWrapper<XYChart>(chartS15).displayChart();
		
		XYChart chartZ = new XYChartBuilder().width(800).height(600).title(getClass().getSimpleName()).xAxisTitle("SNR in dB").yAxisTitle("ON - OFF").build();
		chartZ.setTitle("confronto AND");
		chartZ.addSeries("and7", sim1.getSNR_dB(), risultatiSNRand7);
		chartZ.addSeries("and3", sim1.getSNR_dB(), risultatiSNRand3);
	
		
		new SwingWrapper<XYChart>(chartZ).displayChart();
		
		
		
		XYChart chartY = new XYChartBuilder().width(800).height(600).title(getClass().getSimpleName()).xAxisTitle("SNR in dB").yAxisTitle("ON - OFF").build();
		chartY.setTitle("confronto OR");
		chartY.addSeries("or7", sim1.getSNR_dB(), risultatiSNRor7);
		chartY.addSeries("or3", sim1.getSNR_dB(), risultatiSNRor3);
	
		
		new SwingWrapper<XYChart>(chartY).displayChart();
		
		XYChart chartH = new XYChartBuilder().width(800).height(600).title(getClass().getSimpleName()).xAxisTitle("SNR in dB").yAxisTitle("ON - OFF").build();
		chartH.setTitle("confronto MAGGIORANZA");
		chartH.addSeries("magg7", sim1.getSNR_dB(), risultatiSNRmagg7);
		chartH.addSeries("magg3", sim1.getSNR_dB(), risultatiSNRmagg3);
	
		
		new SwingWrapper<XYChart>(chartH).displayChart();
		
		
//		System.out.println(Arrays.toString(risultatiSNRmagg));
		
//		//risultati che mi servono per far generare il grafico che riguarda la differenza delle 3 regole
//		String[] stR1 = {Simulatore.regolaMaggioranza(risultati1),Simulatore.regolaAND(risultati1), Simulatore.regolaOR(risultati1) };
//		String[] stR2 = {Simulatore.regolaMaggioranza(risultati2),Simulatore.regolaAND(risultati2), Simulatore.regolaOR(risultati2) };
//		String[] stR3 = {Simulatore.regolaMaggioranza(risultati3),Simulatore.regolaAND(risultati3), Simulatore.regolaOR(risultati3) };
//		
//		double[] onOFF3= Simulatore.onOffsingle(stR1);
//		double[] onOFF5= Simulatore.onOffsingle(stR2);
//		double[] onOFF7= Simulatore.onOffsingle(stR3);
//		double[][] forFinal = {onOFF3,onOFF5,onOFF7}; 
//	
//		
//		List<Number> list1 = Arrays.stream(Simulatore.ooMagg(forFinal)).boxed().collect(Collectors.toList());
//		List<Number> list2 = Arrays.stream(Simulatore.ooAnd(forFinal)).boxed().collect(Collectors.toList());
//		List<Number> list3 = Arrays.stream(Simulatore.ooOr(forFinal)).boxed().collect(Collectors.toList());
//		
		
//		//GENERAZIONE DEL 2 GRAFICO 
//		 CategoryChart chartONOFF = new CategoryChartBuilder().width(800).height(600).title("Dati decisione centro stella al variare delle 3 regole").xAxisTitle("").yAxisTitle("OFF/ON").build();
//		 chartONOFF.addSeries("MAGGIORANZA", new ArrayList<String>(Arrays.asList(new String[] { "Simulazione1", "Simulazione2", "Simulazione3"})), new ArrayList<Number>(list1));
//		 chartONOFF.addSeries("AND", new ArrayList<String>(Arrays.asList(new String[] { "Simulazione1", "Simulazione2", "Simulazione3"})), new ArrayList<Number>(list2));
//		 chartONOFF.addSeries("OR", new ArrayList<String>(Arrays.asList(new String[] { "Simulazione1", "Simulazione2", "Simulazione3"})), new ArrayList<Number>(list3));
//
//		 chartONOFF.setXAxisTitle(Arrays.toString(sim1.getSNR_dB()));
//		 new SwingWrapper<CategoryChart>(chartONOFF).displayChart(); 
//		
		 System.out.println("Valori detector n°1\n");
		 System.out.println(Arrays.toString(sim1.getPD_sim()));
		 System.out.println(Arrays.toString(sim1.convertiti));
		 System.out.println(Simulatore.detector(sim1.convertiti));
		 System.out.println(Simulatore.contaPositivi(sim1.convertiti));
		 System.out.println("\n");
		 
		 System.out.println("Valori detector n°2\n");
		 System.out.println(Arrays.toString(sim2.getPD_sim()));
		 System.out.println(Arrays.toString(sim2.convertiti));
		 System.out.println(Simulatore.detector(sim2.convertiti));
		 System.out.println(Simulatore.contaPositivi(sim2.convertiti));
		 System.out.println("\n");
		 
		 System.out.println("Valori detector n°3\n");
		 System.out.println(Arrays.toString(sim3.getPD_sim()));
		 System.out.println(Arrays.toString(sim3.convertiti));
		 System.out.println(Simulatore.detector(sim3.convertiti));
		 System.out.println(Simulatore.contaPositivi(sim3.convertiti));
		 System.out.println("\n");
		 
		 System.out.println("Valori detector n°4\n");
		 System.out.println(Arrays.toString(sim4.getPD_sim()));
		 System.out.println(Arrays.toString(sim4.convertiti));
		 System.out.println(Simulatore.detector(sim4.convertiti));
		 System.out.println(Simulatore.contaPositivi(sim4.convertiti));
		 System.out.println("\n");
		 
		 System.out.println("Valori detector n°5\n");
		 System.out.println(Arrays.toString(sim5.getPD_sim()));
		 System.out.println(Arrays.toString(sim5.convertiti));
		 System.out.println(Simulatore.detector(sim5.convertiti));
		 System.out.println(Simulatore.contaPositivi(sim5.convertiti));
		 System.out.println("\n");
		 
		 System.out.println("Valori detector n°6\n");
		 System.out.println(Arrays.toString(sim6.getPD_sim()));
		 System.out.println(Arrays.toString(sim6.convertiti));
		 System.out.println(Simulatore.detector(sim6.convertiti));
		 System.out.println(Simulatore.contaPositivi(sim6.convertiti));
		 System.out.println("\n");
		 
		 System.out.println("Valori detector n°7\n");
		 System.out.println(Arrays.toString(sim7.getPD_sim()));
		 System.out.println(Arrays.toString(sim7.convertiti));
		 System.out.println(Simulatore.detector(sim7.convertiti));
		 System.out.println(Simulatore.contaPositivi(sim7.convertiti));
		 System.out.println("\n");
		 
//		 System.out.println("Valori detector n°8\n");
//		 System.out.println(Arrays.toString(sim8.getPD_sim()));
//		 System.out.println(Arrays.toString(sim8.convertiti));
//		 System.out.println(Simulatore.detector(sim8.convertiti));
//		 System.out.println(Simulatore.contaPositivi(sim8.convertiti));
//		 System.out.println("\n");
//		 
//		 System.out.println("Valori detector n°9\n");
//		 System.out.println(Arrays.toString(sim9.getPD_sim()));
//		 System.out.println(Arrays.toString(sim9.convertiti));
//		 System.out.println(Simulatore.detector(sim9.convertiti));
//		 System.out.println(Simulatore.contaPositivi(sim9.convertiti));
//		 System.out.println("\n");
//		 
//		 System.out.println("Valori detector n°10\n");
//		 System.out.println(Arrays.toString(sim10.getPD_sim()));
//		 System.out.println(Arrays.toString(sim10.convertiti));
//		 System.out.println(Simulatore.detector(sim10.convertiti));
//		 System.out.println(Simulatore.contaPositivi(sim10.convertiti));
//		 System.out.println("\n");
//		 
//		 System.out.println("Valori detector n°11\n");
//		 System.out.println(Arrays.toString(sim11.getPD_sim()));
//		 System.out.println(Arrays.toString(sim11.convertiti));
//		 System.out.println(Simulatore.detector(sim11.convertiti));
//		 System.out.println(Simulatore.contaPositivi(sim11.convertiti));
//		 System.out.println("\n");
//		 
//		 System.out.println("Valori detector n°12\n");
//		 System.out.println(Arrays.toString(sim12.getPD_sim()));
//		 System.out.println(Arrays.toString(sim12.convertiti));
//		 System.out.println(Simulatore.detector(sim12.convertiti));
//		 System.out.println(Simulatore.contaPositivi(sim12.convertiti));
//		 System.out.println("\n");
//		 
//		 System.out.println("Valori detector n°13\n");
//		 System.out.println(Arrays.toString(sim13.getPD_sim()));
//		 System.out.println(Arrays.toString(sim13.convertiti));
//		 System.out.println(Simulatore.detector(sim13.convertiti));
//		 System.out.println(Simulatore.contaPositivi(sim13.convertiti));
//		 System.out.println("\n");
//		 
//		 System.out.println("Valori detector n°14\n");
//		 System.out.println(Arrays.toString(sim14.getPD_sim()));
//		 System.out.println(Arrays.toString(sim14.convertiti));
//		 System.out.println(Simulatore.detector(sim14.convertiti));
//		 System.out.println(Simulatore.contaPositivi(sim14.convertiti));
//		 System.out.println("\n");
//		 
//		 System.out.println("Valori detector n°15\n");
//		 System.out.println(Arrays.toString(sim15.getPD_sim()));
//		 System.out.println(Arrays.toString(sim15.convertiti));
//		 System.out.println(Simulatore.detector(sim15.convertiti));
//		 System.out.println(Simulatore.contaPositivi(sim15.convertiti));
//		 System.out.println("\n");
//		 
			
		 
		System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
		System.out.println("---------------------------------------------------- CENTRO STELLA ------------------------------------------------------------------");
		System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>\n\n");
	
		
		System.out.println("Risultati che riguardano 3 implementazioni di Detector  \n");
		System.out.println(Simulatore.regolaMaggioranza(risultati1));        //APPLICAZIONE DELLA REGOLA DELLA MAGGIORANZA
		System.out.println(Simulatore.regolaAND(risultati1));                //APPLICAZIONE REGOLA DELL' END
		System.out.println(Simulatore.regolaOR(risultati1));		         //APPLICAZIONE REGOLA DELL'OR
		
		System.out.println("\n");
		
		System.out.println("Risultati che riguardano 7 implementazioni di Detector  \n");
		System.out.println(Simulatore.regolaMaggioranza(risultati2));        //APPLICAZIONE DELLA REGOLA DELLA MAGGIORANZA
		System.out.println(Simulatore.regolaAND(risultati2));                //APPLICAZIONE REGOLA DELL' END
		System.out.println(Simulatore.regolaOR(risultati2));		         //APPLICAZIONE REGOLA DELL'OR
		
		System.out.println("\n");
		
//		System.out.println("Risultati che riguardano 15 implementazioni di Detector  \n");
//		System.out.println(Simulatore.regolaMaggioranza(risultati3));        //APPLICAZIONE DELLA REGOLA DELLA MAGGIORANZA
//		System.out.println(Simulatore.regolaAND(risultati3));                //APPLICAZIONE REGOLA DELL' END
//		System.out.println(Simulatore.regolaOR(risultati3));		         //APPLICAZIONE REGOLA DELL'OR
	}

	public static void main(String[] args) throws IOException {
		new Main().GUI();	
	}
}
