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
import it.uniroma3.tesi.utils.SignalProcessing;
import it.uniroma3.tesi.utils.Statistica;
import it.uniroma3.tesi.utils.StatisticaComplessa;

public class Simulatore{

	private double pfa; // nei commenti mettere l'estensione della sigla, per non perdersi
	// private List<Double> lista = generaDouble(-25,-5,1); // preferirei passare la
	// lista/array al costruttore
	private ArrayRealVector SNR_dB;
	private ArrayRealVector SNR_L; // attributo derivato
	private double Ps; // mettere significato
	private ArrayRealVector Pn; // attributo derivato
	private ArrayRealVector std_rumore; // attributo derivato

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

		// SNR_L = 10.^(SNR_dB./10);
		this.SNR_L = (ArrayRealVector) this.SNR_dB.mapDivide(10).map(elemento -> Math.pow(10, elemento));
		// this.SNR_L = AlgebraVettori.powNV(10,AlgebraVettori.divVN(SNR_dB,10)); /*
		// cosa rappresenta il 10? Creare una costante */

		// Pn = Ps./SNR_L; % potenza ovvero varianza di rumore
		this.Pn = (ArrayRealVector) this.SNR_L.map(elemento -> Ps / elemento);


		this.std_rumore = (ArrayRealVector) this.Pn.map(elemento -> Math.sqrt(elemento));

	}

	public Complex[] segnale_PU() {
		Complex[] segnale_PU = new GeneratoreUniformePotenzaUnitaria().generaComplexSignum(n_campioni, 0.5);
		double devStandardSegnale = StatisticaComplessa.std(Arrays.asList(segnale_PU));
		segnale_PU = AlgebraVettori.divVN(segnale_PU, devStandardSegnale);
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
			soglia_sim[i] = energiaH0sorted[energiaH0sorted.length-indiceSoglia];
			System.out.println();
		}
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
				energiaH1[j] = SignalProcessing.calcolaEnergiaSegnale(segnale_ricevuto);
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
			if(PD_sim[i]>0.70) {
				risultati[i]=1;
			}
			else{
				risultati[i]=0;
			}
		}
		return risultati;
	}
	

	
	//A questo centro stella viene applicata la regola della maggioranza 
	
	public String centroStella(double[] PD_sim) {
		int cont1=0;
		int cont0=0;
		for(int i=0;i<PD_sim.length;i++) {
			if(PD_sim[i]==1) {
				cont1++;
			}
			else cont0++;
		}

		if(cont1>cont0)
			return "Il segnale risulta essere PRESENTE!";
		else return "il segnale risulta essere ASSENTE!";

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
