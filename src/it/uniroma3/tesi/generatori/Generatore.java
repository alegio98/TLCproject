package it.uniroma3.tesi.generatori;

import org.apache.commons.math3.complex.Complex;

/* I generatori generano numeri casuali tra 0 e 1 */
public abstract class Generatore {
	public abstract double[] generaDouble(int n_campioni);
	public abstract Complex[] generaComplex(int n_campioni);
	
	public static double[] generaDouble(int begin,int end,int jump) {
		double[] campioni = new double[21];        // prima la lunghezza era -> (int)Math.ceil((end-begin)/jump) ma venivano 20 campioni me ne servono 21 
		int j = begin;
		for(int i=0;i<=(int)Math.ceil((end-begin)/jump);i++) {
			campioni[i] = j;
			j+=jump;
		}
		return campioni;
	}
	
//	public Complex[][] generaComplex2D(int righe, int n_campioni) {
//		Complex[][] campioni2D = new Complex[righe][n_campioni];
//		for(int i = 0;i<righe;i++) campioni2D[i] = this.generaComplex(n_campioni);
//		return campioni2D;
//	}
//
//	public Complex[][][] generaComplex3D(int numero_matrici, int righe, int n_campioni) {
//		Complex[][][] campioni3D = new Complex[numero_matrici][righe][n_campioni];
//		for(int i = 0;i<numero_matrici;i++) campioni3D[i] = this.generaComplex2D(righe,n_campioni);
//		return campioni3D;
//	}
}
