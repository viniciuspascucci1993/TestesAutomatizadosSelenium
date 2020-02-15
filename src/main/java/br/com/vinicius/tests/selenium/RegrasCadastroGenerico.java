package br.com.vinicius.tests.selenium;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class RegrasCadastroGenerico {
	
	private static final String USER_DIR = System.getProperty("user.dir");
	
	private WebDriver driver;
	
	private DSL dsl;
	
	private CampoTreinamentoPage page;

	@Parameter
	public String nome;
	@Parameter(value = 1)
	public String sobrenome;
	@Parameter(value = 2)
	public String sexo;
	@Parameter(value = 3)
	public List<String> comidas;
	@Parameter(value = 4)
	public String[] esportes;
	@Parameter(value = 5)
	public String mensagem;
	
	@Before
	public void initSelenium() {
		System.setProperty("webdriver.chrome.driver", "C:\\driversSE\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + USER_DIR + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void afterInitSelenium() {
		driver.quit();
	}
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		
		return Arrays.asList(new Object[][] {

			{"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
			{"Vinicius", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
			{"Vinicius", "Pascucci", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
			{"Vinicius", "Pascucci", "Masculino", Arrays.asList("Carne", "Vegetariano"), 
							new String[]{}, "Tem certeza que voce eh vegetariano?"},
			{"Vinicius", "Pascucci", "Masculino", Arrays.asList("Carne"), 
								new String[]{"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
		});
	}

	@Test
	public void deveValidarRegras(){
		
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		
		if (sexo.equals("Masculino")) {
			page.getSexoMasculino();
			
		} if (sexo.equals("Feminino")) {
			page.getSexoFeminino();
		}
		
		if(comidas.contains("Carne")) page.getComidaFavoritaCarne();
		if(comidas.contains("Pizza")) page.getComidaFavoritaPizza();
		if(comidas.contains("Vegetariano")) page.getComidaVegetariano();
		
		page.setEsportes(esportes);
		page.cadastrar();
		System.out.println(mensagem);
		Assert.assertEquals(mensagem, dsl.obterTextAlertAndAccept());
	}
}
