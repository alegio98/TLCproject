package it.uniroma3.tesi.utils;

import java.awt.image.RescaleOp;
import java.util.List;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.linear.ArrayRealVector;

public class AlgebraVettori {

	
	/*
	 * powVV = funzione che svolge l'operazione di .^ in Matlab ovvero svolge operazioni elemento per elemento 
	primo elemento del vettore1 elevato al primo elemento del vettore2 e cosi via 
	TESTATA*/	
	public static  double [] powVV(double [] vettore1 , double [] vettore2) {      //funzione .^ fra vettore e vettore 
		double [] ris = new double[vettore2.length];
		for(int i=0;i<vettore1.length;i++) {
				 ris[i] = Math.pow(vettore1[i],vettore2[i]);
		}
		return ris;
	}
	

	
	/*
	 * powVN = funzione che svolge l'operazione di .^ in Matlab ovvero svolge operazioni elemento per elemento 
	  tutti gli elementi del vettore a sono elevati ad un numero dato.
	  TESTATA */
	
	public static double [] powVN(double [] vettore , double b) {       //funzione .^ fra vettore e numero 
		double [] ris = new double[vettore.length];
		for(int i=0;i<vettore.length;i++) {
				 ris[i] = Math.pow(vettore[i],b);
		}
		
		return ris;
	}
	
	
	
	/*
	 * powNV = funzione che svolge l'operazione di .^ in Matlab ovvero svolge operazioni elemento per elemento 
	  tutti gli elementi del vettore sono gli esponenti per il numero dato , viene creato un nuovo vettore con
	  quella proprietà . 
	  Ex : numero=2 , vettore = {3,4,5}s
	       ris = {2^3 , 2^4 , 2^5}.
	  TESTATA*/
	
	public static double[] powNV(double numero, double[] vettore) {
		double[]ris = new double[vettore.length];
		for(int i=0;i<vettore.length;i++) {
			ris[i]=Math.pow(numero, vettore[i]);
		}
		return ris;
	}
	
	
	
	/*
	 * divVN = funzione che svolge l'operazione di ./ in Matlab ovvero svolge operazioni elemento per elemento
	 * tutti gli elementi del vettore sono divisi per il numero 
	 * TESTATA*/
	
	public static double[] divVN(double[] vettore , double numero) {
		double[] ris = new double [vettore.length];
		for(int i=0;i< vettore.length;i++) {
			ris[i]=(vettore[i]/numero);
		}
		return ris;
	}
		

	/*
	 * divVN = funzione che svolge l'operazione di ./ in Matlab ovvero svolge operazioni elemento per elemento
	 * tutti gli elementi del vettore sono dividono il numero
	 * Ex: numero = 8 , vettore = {1,2,4}
	 *     ris = {8,4,2} 
	 * TESTATA*/
	
	public static double[] divNV(double numero, double[] vettore) {
		double[] ris = new double [vettore.length];
		for(int i=0;i< vettore.length;i++) {
			ris[i]=(numero/vettore[i]);
		}
		return ris;
	}
	
	
	
	/*
	 * divVV = funzione che svolge l'operazione di ./ in Matlab ovvero svolge operazioni elemento per elemento
	 * tutti gli elementi del vettore1 sono divisi per gli elementi del vettore2 
	 * TESTATA*/
	
	public static double[] divVV(double[] vettore1 , double[] vettore2) {
		double[] ris = new double [vettore1.length];
		for(int i=0;i< vettore1.length;i++) {
			ris[i]=(vettore1[i]/vettore2[i]);
		}
		return ris;
	}	
	
	public static Complex[] divVN(Complex[] vettore , double numero) {
		Complex[] ris = new Complex[vettore.length];
		for(int i=0;i< vettore.length;i++) {
			ris[i]=(vettore[i].divide(numero));
		}
		return ris;
	}
	
	/*
	 * moltVN = funzione che svolge l'operazione di ./ in Matlab ovvero svolge operazioni elemento per elemento
	 * tutti gli elementi del vettore sono moltiplicati per il numero
	 * TESTATA*/
	
	public static double[] moltVN(double[] vettore , double numero) {
		double[] ris = new double [vettore.length];
		for(int i=0;i< vettore.length;i++) {
			ris[i]=(vettore[i]*numero);
		}
		return ris;
	}
	
	public static Complex[] moltVN(Complex[] vettore , double numero) {
		Complex[] ris = new Complex[vettore.length];
		for(int i=0;i< vettore.length;i++) {
			ris[i]=(vettore[i].multiply(numero));
		}
		return ris;
	}
	
	
	/*
	 * moltVN = funzione che svolge l'operazione di ./ in Matlab ovvero svolge operazioni elemento per elemento
	 * tutti gli elementi del vettore sono dividono il numero
	 * Ex: numero = 11 , vettore = {1,2,4}
	 *     ris = {11,22,44} 
	 * TESTATA*/
	
	public static double[] moltNV(double numero, double[] vettore) {
		double[] ris = new double [vettore.length];
		for(int i=0;i< vettore.length;i++) {
			ris[i]=(numero*vettore[i]);
		}
		return ris;
	}
	
	
	
	
	/*
	 * sqrtV = funzione che fa la radice quadrata di ogni elemento del vettore.
	 * TESTATA*/
	
	public static double[] sqrtV(double[] vettore) {
		double[] ris = new double [vettore.length];
		for(int i=0;i< vettore.length;i++) {
			ris[i]=Math.sqrt(vettore[i]);
		}
		return ris;
	}
	
	/**
	 * @param vettore Lista di numeri complessi
	 * @return vettore rappresentante i moduli dei numeri complessi passati per parametro
	 * */
	public static double[] absV(List<Complex> vettore) {
		return vettore.stream().mapToDouble(complex -> complex.abs()).toArray();
	}
	
	
	/*
	 * absV = funzione che dato un vettore ritorna un altro vettore con tutti gli elementi in valore assoluto
	 * TESTATA*/	
	public static double[] absV(double[] vettore) {
		double[] ris = new double [vettore.length];
		for(int i=0;i< vettore.length;i++) {
			ris[i]=Math.abs(vettore[i]);
		}
		return ris;
	}
	

	/* nuovo metodo */
	public static Complex[] sommaVettoriComplessi(Complex[] v1,Complex[] v2) {
		Complex[] risultato = new Complex[v1.length];
	
		for(int i=0;i<risultato.length;i++) {
			risultato[i] = v1[i].add(v2[i]);
		}
		
		return risultato;
	}
	
	public static Complex[][] sommaMatriciComplessi(Complex[][] v1,Complex[][] v2) {
		Complex[][] risultato = new Complex[v1.length][v1[0].length];
	
		for(int i=0;i<risultato.length;i++) {
			for(int j=0;j<risultato[0].length;j++)
				risultato[i][j] = v1[i][j].add(v2[i][j]);
		}
		
		return risultato;
	}
	
	public static Complex[][] sommaMatriceVettoreComplessi(Complex[] v,Complex[][] m) {
		Complex[][] risultato = new Complex[m.length][m[0].length];		
		for(int i = 0;i<m.length;i++) risultato[i] = AlgebraVettori.sommaVettoriComplessi(m[i], v);		
		return risultato;
	}
	
	public static Complex[][][] sommaMatrice3DVettoreComplessi(Complex[] v,Complex[][][] m) {
		Complex[][][] risultato = new Complex[m.length][m[0].length][m[0][0].length];		
		for(int i = 0;i<m.length;i++)
				risultato[i] = AlgebraVettori.sommaMatriceVettoreComplessi(v,m[i]);	
		return risultato;
	}
	
	/* sum elmeneti vettori maggiori di */
	
	public static double cont_sim(double[] vet1,double n) {
		double conta = 0;
		// quanti elementi di vet1 sono maggiori di n?
		for(int i=0;i<vet1.length;i++) {
			if(vet1[i]>n) conta++;
		}
		
		return conta;
	}
	
}
