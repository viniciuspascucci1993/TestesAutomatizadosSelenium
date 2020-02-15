package br.com.vinicius.tests.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DesafioCadastroSucessoTest {

	private static final String USER_DIR = System.getProperty("user.dir");
	
	private WebDriver driver;
	
	private CampoTreinamentoPage page;
	
	@Before
	public void initSelenium() {
		System.setProperty("webdriver.chrome.driver", "C:\\driversSE\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + USER_DIR + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void afterInitSelenium() {
		driver.quit();
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
