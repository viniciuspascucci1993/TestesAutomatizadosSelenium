package br.com.vinicius.tests.selenium.test;

import static br.com.vinicius.tests.selenium.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.vinicius.tests.selenium.core.BaseTest;
import br.com.vinicius.tests.selenium.page.CampoTreinamentoPage;

public class DesafioCadastroSucessoTest extends BaseTest {
	
	private CampoTreinamentoPage page;
	
	@Before
	public void initSelenium() {
		
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();
	}
	
	@Test
	public void cadastroSucessoTest() {
		
		page.setNome("Vinicius");
		page.setSobrenome("Pascucci");
		page.getSexoMasculino();
		page.getComidaFavoritaPizza();
		page.setEscolaridade("Mestrado");	
		page.setEsportes("Natacao");	
		page.cadastrar();
		
		Assert.assertEquals("Cadastrado!" , page.obterResultadoCadastro());
		Assert.assertEquals("Vinicius", page.obterNomeCadastro());
		Assert.assertEquals("Pascucci", page.obterSobrenomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Pizza", page.obterComidaCadastro());
		Assert.assertEquals("mestrado", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Natacao", page.obterEsporteCadastro());
	}
}
