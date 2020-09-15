package it.uniroma3.tesi;

import static org.junit.Assert.*;

import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.tesi.utils.AlgebraVettori;

import java.lang.Math;
import java.util.Arrays;

public class VettoriTest {

	double[] v1 = {1,2,3};
	double[] v2 = {3,4,2};
	double[] v3 = {1,16,9};
	double[] v4 = {1,8,27};
	double[] v5 = {6,36,216};
	double[] v6 = {3,18,108};
	double[] v7 = {1,2,4};
	double[] v8 = {8,4,2};
	double[] v9 = {4,16,49};
	double[] v10 = {2,4,7};
	double[] v11 = {8,16,24};
	double[] v12 = {2,4,8};
	double[] v13 = {4,4,3};
	double[] v14 = {9,12,6};
	double[] v15 = {11,22,44};
	double[] v16 = {-11,22,-44};
	
	
	@Test
	public void testPotenzeFraVettori() {
		assertTrue(Arrays.equals(v3,AlgebraVettori.powVV(v1,v2)));
	}
	
	@Test 
	public void testPotenzaVettoreNumero() {
		assertTrue(Arrays.equals(v4, AlgebraVettori.powVN(v1,3)));
	}
	
	@Test
	public void testPotenzaNumeroVettore() {
		assertTrue(Arrays.equals(v5, AlgebraVettori.powNV(6,v1)));
	}
	
	@Test
	public void testDivisioneVettorePerNumero() {
		assertTrue(Arrays.equals(v6,AlgebraVettori.divVN(v5,2)));
	}
	
	@Test
	public void testDivisioneNumeroPerVettore() {
		assertTrue(Arrays.equals(v7,AlgebraVettori.divNV(8,v8)));
	}
	
	@Test
	public void testRadiceElementiDelVettore() {
		assertTrue(Arrays.equals(v10,AlgebraVettori.sqrtV(v9)));
	}
	
	@Test
	public void testDivisioneFraVettori() {
		assertTrue(Arrays.equals(v13, AlgebraVettori.divVV(v11,v12)));
	}
	
	@Test
	public void testMoltiplicazioneVettorePerNumero() {
		assertTrue(Arrays.equals(v14,AlgebraVettori.moltVN(v2,3)));
	}
	
	@Test
	public void testMoltiplicazioneNumeroPerVettore() {
		assertTrue(Arrays.equals(v15,AlgebraVettori.moltNV(11,v7)));
	}
	
	@Test
	public void testVettoreCheRitornaTuttiValoriAssoluti() {
		assertTrue(Arrays.equals(v15,AlgebraVettori.absV(v16)));
	}
	
}
