package it.uniroma3.tesi.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.util.DoubleArray;

public class SignalProcessing {
	
	// TESTATO
	public static double calcolaEnergiaSegnale(Complex[] segnale) {
		int N_campioni = segnale.length;
		List<Complex> segnaleList = Arrays.asList(segnale);
		double[] moduliQuadri = segnaleList.stream().mapToDouble(elemento -> Math.pow(elemento.abs(), 2)).toArray();
		
		return DoubleStream.of(moduliQuadri).sum()/N_campioni;
	}
	
}
