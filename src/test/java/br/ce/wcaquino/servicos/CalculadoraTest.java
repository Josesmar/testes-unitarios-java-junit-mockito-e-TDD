package br.ce.wcaquino.servicos;

import br.ce.wcaquino.runners.ParallelRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.JUnit4;

@RunWith(ParallelRunner.class)
public class CalculadoraTest {
	
	private Calculadora calc;
	
	@Before
	public void setup(){
		calc = new Calculadora();
		System.out.println("iniciando...");
	}

	@After
	public void tearDow(){
		System.out.println("finalizando...");
	}

	@Test
	public void deveSomarDoisValores(){
		//cenario
		int a = 5;
		int b = 3;
		
		//acao
		int resultado = calc.somar(a, b);
		
		//verificacao
		Assert.assertEquals(8, resultado);
		
	}
	
	@Test
	public void deveSubtrairDoisValores(){
		//cenario
		int a = 8;
		int b = 5;
		
		//acao
		int resultado = calc.subtrair(a, b);
		
		//verificacao
		Assert.assertEquals(3, resultado);
		
	}
	
	@Test
	public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException{
		//cenario
		int a = 6;
		int b = 3;
		
		//acao
		int resultado = calc.divide(a, b);
		
		//verificacao
		Assert.assertEquals(2, resultado);
	}
	
	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException{
		int a = 10;
		int b = 0;
		
		calc.divide(a, b);
	}
	
	@Test
	public void deveDividir(){
		String a = "6";
		String b = "3";
		
		int resultado = calc.divide(a, b);
		
		Assert.assertEquals(2, resultado);
	}
}
