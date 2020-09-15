package it.uniroma3.tesi.generatori;
import java.util.Random;
import org.apache.commons.math3.complex.Complex;

public class GeneratoreGaussiano extends Generatore {

	@Override
	public double[] generaDouble(int n_campioni) {
		double[] campioni = new double[n_campioni];
		for(int i = 0;i<n_campioni;i++) campioni[i] = new Random().nextGaussian();
		return campioni;
	}

	@Override
	public Complex[] generaComplex(int n_campioni) {
		Complex[] campioni = new Complex[n_campioni];
		for(int i = 0;i<n_campioni;i++) 
			campioni[i] = new Complex(new Random().nextGaussian()*10,new Random().nextGaussian()*10);
		return campioni;
	}
	
}
