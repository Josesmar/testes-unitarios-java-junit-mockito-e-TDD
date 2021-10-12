package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {
	
	@Test
	public void test() {
		
		//Boolean
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		
		Assert.assertEquals("Erro de comparação", 1, 1);
		Assert.assertEquals(0.51234, 0.512, 0.001);
		Assert.assertEquals(Math.PI, 3.14, 0.01);
		
		//tipos primitivos para objeto e vice e versa
		int i =5;
		Integer i2 = 5;		
		Assert.assertEquals(Integer.valueOf(i), i2);
		Assert.assertEquals(i, i2.intValue());
		
		//Strings
		Assert.assertEquals("bola", "bola");
		Assert.assertNotEquals("casa", "bola");
		Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
		Assert.assertTrue("bola".startsWith("bo"));
		
		
		//Foi necessário criar o método Equals e hasCode para conseguir realizar a comparação por objeto
		Usuario u1 = new Usuario("Usuario 1");
		Usuario u2 = new Usuario("Usuario 1");
		Usuario u3 = u2;
		Usuario u4 = null;
		
		//Objtivos
		Assert.assertEquals(u1, u2);
		
		
		//Quando é da mesma instância
		Assert.assertSame(u3, u2);
		Assert.assertNotSame(u1, u2);
		
		//Verificando se é null
		Assert.assertNull(u4);
		Assert.assertNotNull(u1);
		
		
	}
	
}
