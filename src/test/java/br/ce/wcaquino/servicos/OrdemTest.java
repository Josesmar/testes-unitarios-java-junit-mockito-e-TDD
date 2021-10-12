package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OrdemTest {
	
	public static int contador = 0;
		
	public void inicia() {
		contador = 1;
		
	}
	
	public void verifica() {
		assertEquals(1, contador);
	}
	
	@Test
	public void testGeral() {
		inicia();
		verifica();
	}

}
