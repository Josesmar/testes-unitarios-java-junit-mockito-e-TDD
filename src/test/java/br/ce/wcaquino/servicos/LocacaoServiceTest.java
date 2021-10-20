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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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
import buildermaster.BuilderMaster;

public class LocacaoServiceTest {
	
	private LocacaoService service;			
	
	@Rule
	public ErrorCollector erro = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {				
		service = new LocacaoService();		
	}		
	
	@Test
	public void testeLocacao() throws Exception {
		//cenário		
		Usuario usuario = new Usuario("Josesmar");
		Filme filme = new Filme("Filme 1", 2, 5.0);				
		
		//ação
		Locacao locacao = service.alugarFilme(usuario, filme);
			
		//verificação
		Assert.assertEquals(5.0, locacao.getValor(), 0.01);
			
		//Feito import static
		erro.checkThat(locacao.getValor(), is(equalTo(5.0)));		
		erro.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		erro.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));					

	}
	
	@Test(expected = FilmeSemEstoqueException.class) //Esperado excecao "Filme sem estoque
	public void testLocacao_filmeSemEstoque() throws Exception{
		//cenário		
		Usuario usuario = new Usuario("Josesmar");
		Filme filme = new Filme("Filme 1", 0, 5.0);			
		
		//ação
		service.alugarFilme(usuario, filme);
		
	}
	
	
	@Test
	public void testeLocacao_usuarioVazio() throws FilmeSemEstoqueException {
		//cenário		
		Filme filme = new Filme("Filme 2", 1, 4.0);		
		
		//ação
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}
	
	@Test
	public void testeLocacao_FilmeVazio() throws FilmeSemEstoqueException, LocadoraException {
		//cenário		
		Usuario usuario = new Usuario("Josesmar");				
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		//ação
		service.alugarFilme(usuario, null);
	
	}
	

