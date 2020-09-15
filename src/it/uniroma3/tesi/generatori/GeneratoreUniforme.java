package it.uniroma3.tesi.generatori;

import java.util.Random;

import org.apache.commons.math3.complex.Complex;

public class GeneratoreUniforme extends Generatore {

	@Override
	public double[] generaDouble(int n_campioni) {
		double[] campioni = new double[n_campioni];
		for(int i = 0;i<n_campioni;i++) campioni[i] = new Random().nextDouble();
		return campioni;
	}

	@Override
	public Complex[] generaComplex(int n_campioni) {
		Complex[] campioni = new Complex[n_campioni];
		for(int i = 0;i<n_campioni;i++) 
			campioni[i] = new Complex(new Random().nextGaussian(),new Random().nextDouble());
		return campioni;
	}
	
	public Complex[] generaComplexSignum(int n_campioni,double sub) {
		Complex[] campioni = new Complex[n_campioni];
		for(int i = 0;i<n_campioni;i++) 
			campioni[i] = new Complex(Math.signum(sub-new Random().nextGaussian()),
					Math.signum(sub-new Random().nextDouble()));
		return campioni;
	}

}
