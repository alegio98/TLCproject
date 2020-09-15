package it.uniroma3.tesi.utils;

import java.util.List;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

public class StatisticaComplessa extends Statistica {
	
	/* riferimento: https://it.mathworks.com/matlabcentral/answers/471509-how-does-function-std-process-complex-numbers */
	public static double std(List<Complex> values) {
		/* due vettori, uno rappresenta tutte le parti reali, l'altro quelle immaginarie */
		double[] real = values.stream().mapToDouble(complex -> complex.getReal()).toArray();
		double[] compl = values.stream().mapToDouble(complex -> complex.getImaginary()).toArray();
		return Math.sqrt(Math.pow(std(real),2) + Math.pow(std(compl),2));
	}
	
}
