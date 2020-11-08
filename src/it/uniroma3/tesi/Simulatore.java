package it.uniroma3.tesi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.special.Erf;

import it.uniroma3.tesi.calcolatori.CalcolatoreEnergia;
import it.uniroma3.tesi.generatori.GeneratoreGaussianoPotenzaUnitaria;
import it.uniroma3.tesi.generatori.GeneratoreUniformePotenzaUnitaria;
import it.uniroma3.tesi.utils.AlgebraVettori;
import it.uniroma3.tesi.utils.Statistica;
import it.uniroma3.tesi.utils.StatisticaComplessa;

public class Simulatore{

	private double pfa;                  //Probabilità di falso allarme
	private ArrayRealVector SNR_dB;      //Rapporto segnale rumore espresso in decibel
	private ArrayRealVector SNR_L;       //Rapporto segnale rumore forma lineare                    attributo derivato
	private double Ps;                   //Potenza segnale utile
	private ArrayRealVector Pn;          //Potenza rumore                                           attributo derivato
	private ArrayRealVector std_rumore;  //Deviazione standard rumore                              attributo derivato
	
	private int num_prove_H0;
	private int num_prove_H1;
	private int n_campioni;

	public Simulatore(double pfa, double[] SNR_dB, double Ps, int num_prove_H0, int num_prove_H1, int n_campioni) {
		this.pfa = pfa;
		this.SNR_dB = new ArrayRealVector(SNR_dB);
		this.Ps = Ps;
		this.num_prove_H0 = num_prove_H0;
		this.num_prove_H1 = num_prove_H1;
		this.n_campioni = n_campioni;

		// // SNR_L = 10.^(SNR_dB./10);
		this.SNR_L = (ArrayRealVector) this.SNR_dB.mapDivide(10).map(elemento -> Math.pow(10, elemento));
		// cosa rappresenta il 10? Creare una costante */
		
		// Pn = Ps./SNR_L; % potenza ovvero varianza di rumore
		this.Pn = (ArrayRealVector) this.SNR_L.map(elemento -> Ps / elemento);

		this.std_rumore = (ArrayRealVector) this.Pn.map(elemento -> Math.sqrt(elemento));

	}

	public Complex[] segnale_PU() {    
		Complex[] segnale_PU = new GeneratoreUniformePotenzaUnitaria().generaComplexSignum(n_campioni, 0.5);
		//double devStandardSegnale = StatisticaComplessa.std(Arrays.asList(segnale_PU));
		//segnale_PU = AlgebraVettori.divVN(segnale_PU, devStandardSegnale);
		return segnale_PU;
	}

	private Complex[] generazioneRumoreGaussianoUnitario() {
		return new GeneratoreGaussianoPotenzaUnitaria().generaComplex(n_campioni);
	}


	public double[] calcoloSogliaPerSimulazione() {
		int sogliaDim = this.SNR_dB.getDimension();
		double[] soglia_sim = new double[sogliaDim];
		int indiceSoglia = (int)Math.ceil(num_prove_H0*pfa);
		
		for(int i=0;i<SNR_dB.getDimension();i++) {
			double[] energiaH0 = new double[this.num_prove_H0];
			for(int j=0;j< this.num_prove_H0;j++) {
				Complex[] rumore = this.generazioneRumoreGaussianoUnitario();		
				rumore = AlgebraVettori.moltVN(rumore, std_rumore.getEntry(i));
				energiaH0[j] = new CalcolatoreEnergia().calcolaEnergiaSegnale(rumore);
			}
			double[] energiaH0sorted = new double[energiaH0.length];
			energiaH0sorted= ordinamento(energiaH0);
			soglia_sim[i] = energiaH0sorted[energiaH0sorted.length-indiceSoglia];		}
		return soglia_sim;
	}


	public double[] calcolo_PdSim(Complex[] segnale_PU, double[] soglia_sim) {
		double[] pd_sim = new double[SNR_dB.getDimension()];

		for (int i = 0; i < SNR_dB.getDimension(); i++) {
			double[] energiaH1 = new double[this.num_prove_H1];
			for (int j = 0; j < this.num_prove_H1; j++) {
				Complex[] rumore = this.generazioneRumoreGaussianoUnitario();
				rumore = AlgebraVettori.moltVN(rumore, this.std_rumore.getEntry(i));

				Complex[] segnale_ricevuto = AlgebraVettori.sommaVettoriComplessi(segnale_PU, rumore);
				energiaH1[j] = CalcolatoreEnergia.calcolaEnergiaSegnale(segnale_ricevuto);
			}

			double cont_sim = AlgebraVettori.cont_sim(energiaH1,soglia_sim[i]);
			pd_sim[i] = cont_sim/num_prove_H1;
		}
		ArrayUtils.reverse(pd_sim);
		return pd_sim;
	}

	
	public double[] convertitorePdSim(double[] PD_sim) {
		double[] risultati = new double[PD_sim.length];
		for (int i=0; i<risultati.length; i++) {
			if(PD_sim[i]>0.76) {
				risultati[i]=1;
			}
			else{
				risultati[i]=0;
			}
		}
		return risultati;
	}

	
	//A questo centro stella viene applicata la regola della maggioranza 
	
	public static String detector(double[] PD_sim) {
		int cont1=0;
		int cont0=0;
		for(int i=0;i<PD_sim.length;i++) {
			if(PD_sim[i]==1) {
				cont1++;
			}
			else cont0++;
		}
		if(cont1>cont0)
			return "Il segnale per questo singolo detector risulta essere presente!";
		else return "Il segnale per questo singolo detector risulta essere assente!";
	}
	
	public static String regolaMaggioranza(String[] PD_sim) {
		String str = new String("Il segnale per questo singolo detector risulta essere presente!");
		int cont1=0;
		int cont0=0;
		for(String s : PD_sim) {
		if(s.equals(str))
			cont1++;
		else cont0++;
		}
		if(cont1>cont0)
			return "Applicando la regola della MAGGIORANZA Il segnale risulta essere PRESENTE.";
		else return "Applicando la regola della MAGGIORANZA il segnale risulta essere ASSENTE.";
	}
	
	
	public static String regolaAND(String[] stringa) {
		String str = new String("Il segnale per questo singolo detector risulta essere assente!");
		int contNO=0;
		for(String s : stringa) {
			if(s.equals(str))
			contNO++;
		}
		if (contNO>=1)
			return "Applicando la regola dell'AND il segnale risulta essere ASSENTE.";
		else return  "Applicando la regola dell'AND il segnale risulta essere PRESENTE.";
	}
	
	
	public static String regolaOR(String[] stringa) {
		String str = new String("Il segnale per questo singolo detector risulta essere presente!");
		int contSI=0;
		for(String s : stringa) {
			if(s.equals(str))
			contSI++;
		}
		if (contSI>=1)
			return "Applicando la regola dell'OR il segnale risulta essere PRESENTE.";
		else return  "Applicando la regola dell'OR il segnale risulta essere ASSENTE.";
	}
	
	public static String contaPositivi (double[] index) {
		int numero=0;
		for(int i=0;i<index.length;i++) {
			if(index[i]==1)
				numero++;
		}
		return "Sono presenti "+ numero + " indici positivi di Probabilità di Detection ";
	}
	
	
//	public static double[][] traspostaSNR(double[][] convertitiPD){
//		
//		double[][] trasposta = new double[4][3];
//			for(int i=0;i<convertitiPD.length;i++) {
//			for(int j=0;j<convertitiPD.length;j++) {
//				trasposta[j][i]= convertitiPD[i][j]; 
//			}
//		}	
//		return trasposta;
//	}
	
public static double[] SNRmaggioranza(double[][] convertitiPD){	
		double[] finale = new double[21];
		int cont1 = 0;
		int cont0 = 0;
		for(int i=0;i<convertitiPD[0].length;i++) {
			for(int j=0;j<convertitiPD.length;j++) {
				if(convertitiPD[j][i]==1) 
					cont1++;
				else cont0++;
			}
						if (cont1>cont0)
					    finale[i]=1;
						else finale[i]=0;
						cont1=0;
						cont0=0;
		}
		return finale;
	}
	
public static double[] SNRor(double[][] convertitiPD){
	double[] finale = new double[21];
	int cont1 = 0;
	for(int i=0;i<convertitiPD[0].length;i++) {
		for(int j=0;j<convertitiPD.length;j++) {
			if(convertitiPD[j][i]==1) 
			cont1++;
		}
			if (cont1>=1)
				finale[i]=1;
			else finale[i]=0;
			cont1=0;
	}	
	return finale;
}

public static double[] SNRand(double[][] convertitiPD){
	double[] finale = new double[21];
	int cont0 = 0;
	for(int i=0;i<convertitiPD[0].length;i++) {
		for(int j=0;j<convertitiPD.length;j++) {
			if(convertitiPD[j][i]==0) 
			cont0++;
		}
			if (cont0>=1)
				finale[i]=0;
			else finale[i]=1;
			cont0=0;
	}	
	return finale;
}



	public static double[] onOffsingle(String[] stringhe) {
		double[] vettore = new double[stringhe.length];
			if(stringhe[0].equals("Applicando la regola della MAGGIORANZA Il segnale risulta essere PRESENTE."))
				vettore[0]=1;
			else vettore[0]=-1;
			
			if(stringhe[1].equals("Applicando la regola dell'AND il segnale risulta essere PRESENTE."))
				vettore[1]=1;
			else vettore[1]=-1;
			
			if(stringhe[2].equals("Applicando la regola dell'OR il segnale risulta essere PRESENTE."))
				vettore[2]=1;
			else vettore[2]=-1;
			
		return vettore;
	}
	
	public static double[] ooMagg (double[][] start) {
		double[] maggioranza = new double[3];
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=start[i].length;j++) {
			maggioranza[i]=start[i][0]; 
			}
		}
		return maggioranza;
	}
	
	public static double[] ooAnd (double[][] start) {
		double[] end = new double[3];
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=start[i].length;j++) {
			end[i]=start[i][1]; 
			}
		}
		return end;
	}
	
	public static double[] ooOr (double[][] start) {
		double[] or = new double[3];
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=start[i].length;j++) {
			or[i]=start[i][2]; 
			}
		}
		return or;
	}
	

	public static double[] ordinamento(double[] arr) {  
		   Arrays.parallelSort(arr);
		   return arr;
		}  

	/* getters e setters */
	public double getPfa() {
		return pfa;
	}

	public void setPfa(double pfa) {
		this.pfa = pfa;
	}

	public ArrayRealVector getSNR_dB() {
		return SNR_dB;
	}

	public void setSNR_dB(ArrayRealVector sNR_dB) {
		SNR_dB = sNR_dB;
	}

	public ArrayRealVector getSNR_L() {
		return SNR_L;
	}

	public void setSNR_L(ArrayRealVector sNR_L) {
		SNR_L = sNR_L;
	}

	public double getPs() {
		return Ps;
	}

	public void setPs(double ps) {
		Ps = ps;
	}

	public ArrayRealVector getPn() {
		return Pn;
	}

	public void setPn(ArrayRealVector pn) {
		Pn = pn;
	}

	public ArrayRealVector getStd_rumore() {
		return std_rumore;
	}

	public void setStd_rumore(ArrayRealVector std_rumore) {
		this.std_rumore = std_rumore;
	}

	public int getNum_prove_H0() {
		return num_prove_H0;
	}

	public void setNum_prove_H0(int num_prove_H0) {
		this.num_prove_H0 = num_prove_H0;
	}

	public int getNum_prove_H1() {
		return num_prove_H1;
	}

	public void setNum_prove_H1(int num_prove_H1) {
		this.num_prove_H1 = num_prove_H1;
	}

	public int getN_campioni() {
		return n_campioni;
	}

	public void setN_campioni(int n_campioni) {
		this.n_campioni = n_campioni;
	}

}
