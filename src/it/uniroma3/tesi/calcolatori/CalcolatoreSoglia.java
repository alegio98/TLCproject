package it.uniroma3.tesi.calcolatori;

import java.util.Arrays;

public class CalcolatoreSoglia {

	public double[] calcolaSoglia(double[][] energiaH0,int indiceSoglia) {
		double[] sogliaSim = new double[energiaH0.length];		
		
		for (int i = 0; i < energiaH0.length; i++) {
			Arrays.sort(energiaH0[i]);
			sogliaSim[i] = energiaH0[i][energiaH0[i].length - indiceSoglia];
		}

		return sogliaSim;
	}
	
}
