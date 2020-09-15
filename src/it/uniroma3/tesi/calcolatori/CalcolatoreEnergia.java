package it.uniroma3.tesi.calcolatori;

import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;

import org.apache.commons.math3.complex.Complex;

public class CalcolatoreEnergia {
	
	/**
	 * @param segnale Il segnale
	 * @return L'energia del segnale
	 * */
	
	public double calcolaEnergiaSegnale(Complex[] segnale) {
		int N_campioni = segnale.length;
		List<Complex> segnaleList = Arrays.asList(segnale);
		double[] moduliQuadri = segnaleList.stream().mapToDouble(elemento -> Math.pow(elemento.abs(), 2)).toArray();
		
		return DoubleStream.of(moduliQuadri).sum()/N_campioni;
	}
	
	/**
	 * @param segnali Matrice dei segnali. Ogni riga rappresenta un segnale nel tempo
	 * @return Un vettore contenente le energie di tutti i segnali
	 * */
	public double[] calcolaEnergieSegnali(Complex[][] segnali) {
		double[] energie = new double[segnali.length];
		// per ogni segnale calcola l'energia. Riporta in un vettore
		for(int i = 0;i<segnali.length;i++) energie[i] = this.calcolaEnergiaSegnale(segnali[i]);		
		return energie;
	}
	
	/**
	 * @param segnali Insieme di matrici. Ogni matrice contiene più segnali. Una matrice rappresenta un tentativo
	 * @return Un insieme di vettori (matrice) che contengono le energie dei segnali contenuti in ogni matrice
	 * */
	public double[][] calcolaEnergieSegnali(Complex[][][] segnali) {
		double[][] energie = new double[segnali.length][segnali[0].length];
		// per ogni segnale calcola l'energia. Riporta in un vettore
		for(int i = 0;i<segnali.length;i++) energie[i] = this.calcolaEnergieSegnali(segnali[i]);		
		return energie;
	}

}
