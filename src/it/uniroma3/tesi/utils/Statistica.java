	package it.uniroma3.tesi.utils;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.descriptive.summary.Sum;

import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.special.Erf;

public class Statistica {

	/*funzione che calcola la media di elementi presenti in un array
	 * utilizzata nel calcolo soglia per via teorica . 
	 * */
	/* la sostituisco con mean */
//	public float mediaArray(int array[]) {
//		float media;
//		int somma=0;
//		for(int elem : array) {
//			somma+=elem;
//		}
//		media = somma/array.length;
//		return media;
//	}
	
	/** @param values I valori di cui voglio calcolare la media
	 *  @return La media dei valori presenti in values
	 *  */
	public static double mean(double[] values) {
		return new Mean().evaluate(values);
	}

	/*funzione che calcola la varianza di elementi di un array 
	 * var in matlab*/
//	public float varianza(int[] v)
//	{
//		float m = mediaArray(v);
//		float sommaScartiQuad = 0;
//		for(int i=0; i<v.length; i++)
//			sommaScartiQuad += (v[i]-m)*(v[i]-m);
//		return sommaScartiQuad/v.length;
//	}
	
	/** @param values Vettore calcolare la @return varianza
	 * */	
	public static double variance(double[] values) {
		return new Variance().evaluate(values);
	}
	
	/*calcolo deviazione standard (std in matlab) essa ci consente di capire
	 * quanto sia affidabile la media che abbiamo calcolato , a che distanza
	 * si trova un campione dall'altro piu essa è vicina a zero e piu essa è
	 * affidabile*/ 
//	
//	public static double std(double numArray[])
//    {
//        double sum = 0.0, standardDeviation = 0.0;
//        int length = numArray.length;
//
//        for(double num : numArray) {
//            sum += num;
//        }
//
//        double mean = sum/length;
//
//        for(double num: numArray) {
//            standardDeviation += Math.pow(num - mean, 2);
//        }
//
//        return Math.sqrt(standardDeviation/length);
//    }
	
	/**
	 * @param values Vettore di cui voglio calcolare la deviazione standard @return deviazione standard
	 * */
	public static double std(double[] values) {
		return new StandardDeviation().evaluate(values);
	}	
	
	
	/**
	 * @param array Vettore contenente gli addendi
	 * @return La sommatoria degli elementi nell'array
	 *  */
	public static double sum(double[] values) {
	    return new Sum().evaluate(values);
	}
	
}
