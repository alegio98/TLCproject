package it.uniroma3.tesi.generatori;

import org.apache.commons.math3.complex.Complex;

/* I generatori generano numeri casuali tra 0 e 1 */
public abstract class Generatore {
	public abstract double[] generaDouble(int n_campioni);
	public abstract Complex[] generaComplex(int n_campioni);
	
	public static double[] generaDouble(int begin,int end,double jump) {
		double[] campioni = new double[25];        // prima era  21 con jump settato a 1
		int j = begin;
		for(int i=0;i<=(int)Math.ceil((end-begin)/jump);i++) {
			campioni[i] = j;
			j+=jump;
		}
		return campioni;
	}
}
