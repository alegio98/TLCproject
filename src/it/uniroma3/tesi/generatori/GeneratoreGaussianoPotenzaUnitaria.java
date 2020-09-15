package it.uniroma3.tesi.generatori;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.math3.complex.Complex;

import it.uniroma3.tesi.utils.AlgebraVettori;
import it.uniroma3.tesi.utils.Statistica;
import it.uniroma3.tesi.utils.StatisticaComplessa;

import org.apache.commons.lang3.ArrayUtils;

public class GeneratoreGaussianoPotenzaUnitaria extends GeneratoreGaussiano {

	@Override
	public double[] generaDouble(int n_campioni) {
		double[] campioniCasuali = new GeneratoreGaussiano().generaDouble(n_campioni);
		double std = Statistica.std(campioniCasuali);
		for(int i=0;i<campioniCasuali.length;i++) campioniCasuali[i]/=std;
		return campioniCasuali;
	}

	@Override
	public Complex[] generaComplex(int n_campioni) {
		Complex[] campioniCasuali = new GeneratoreGaussiano().generaComplex(n_campioni);
		double std = StatisticaComplessa.std(Arrays.asList(campioniCasuali));		
		campioniCasuali = AlgebraVettori.divVN(campioniCasuali, std);	
		
		return campioniCasuali;
	}

}
