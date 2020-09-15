package it.uniroma3.tesi.generatori;

import java.util.Arrays;

import org.apache.commons.math3.complex.Complex;

import it.uniroma3.tesi.utils.Statistica;
import it.uniroma3.tesi.utils.StatisticaComplessa;

public class GeneratoreUniformePotenzaUnitaria extends GeneratoreUniforme {

	@Override
	public double[] generaDouble(int n_campioni) {
		double[] campioniCasuali = new GeneratoreUniforme().generaDouble(n_campioni);
		double std = Statistica.std(campioniCasuali);
		for(int i=0;i<campioniCasuali.length;i++) campioniCasuali[i]/=std;
		return campioniCasuali;
	}

	@Override
	public Complex[] generaComplex(int n_campioni) {
		Complex[] campioniCasuali = new GeneratoreUniforme().generaComplex(n_campioni);
		double std = StatisticaComplessa.std(Arrays.asList(campioniCasuali));
		for(int i=0;i<campioniCasuali.length;i++) campioniCasuali[i] = campioniCasuali[i].divide(std);
		return campioniCasuali;
	}

}
