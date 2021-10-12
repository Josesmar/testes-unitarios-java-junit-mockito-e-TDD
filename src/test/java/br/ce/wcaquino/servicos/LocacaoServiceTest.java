package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.rmi.server.ExportException;
import java.util.Date;
import java.util.Locale;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	
	@Rule
	public ErrorCollector erro = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	
	@Test
	public void testeLocacao() throws Exception {
		//cen�rio
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Josesmar");
		Filme filme = new Filme("Filme 1", 2, 5.0);		
		
		//a��o
		Locacao locacao = service.alugarFilme(usuario, filme);
			
		//verifica��o
		Assert.assertEquals(5.0, locacao.getValor(), 0.01);
			
		//Feito import static
		erro.checkThat(locacao.getValor(), is(equalTo(5.0)));		
		erro.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		erro.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));					

	}
	
	@Test(expected = FilmeSemEstoqueException.class) //Esperado excecao "Filme sem estoque
	public void testLocacao_filmeSemEstoque() throws Exception{
		//cen�rio
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Josesmar");
		Filme filme = new Filme("Filme 1", 0, 5.0);			
		
		//a��o
		service.alugarFilme(usuario, filme);
		
	}
	
	
	@Test
	public void testeLocacao_usuarioVazio() throws FilmeSemEstoqueException {
		//cen�rio
		LocacaoService service = new LocacaoService();
		Filme filme = new Filme("Filme 2", 1, 4.0);		
		
		//a��o
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuario vazio"));
		}

		System.out.println("Forma robusta");
	}
	
	@Test
	public void testeLocacao_FilmeVazio() throws FilmeSemEstoqueException, LocadoraException {
		//cen�rio
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Josesmar");				
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		//a��o
		service.alugarFilme(usuario, null);
	
		
		System.out.println("Forma Nova");
	}
	
}
