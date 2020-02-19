package br.com.vinicius.tests.selenium;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CampoTreinamentoTest {
	
	private static final String USER_DIR = System.getProperty("user.dir");

	private WebDriver driver;
	
	private DSL dsl;
	
	@Before
	public void initSelenium() {
		System.setProperty("webdriver.chrome.driver", "C:\\driversSE\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + USER_DIR + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void afterInitSelenium() {
		driver.quit();
	}
	
	
	@Test
	public void campoTreinamentoTextFieldTest() {
				
		dsl.escreve("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.getValueFromTextField("elementosForm:nome"));
		
	}
	
	@Test
	public void textFieldDuploTest() {
		
		dsl.escreve("elementosForm:nome", "BIOHAZARD");
		Assert.assertEquals("BIOHAZARD", dsl.getValueFromTextField("elementosForm:nome"));
		
		dsl.escreve("elementosForm:nome", "RESIDENT_EVIL");
		Assert.assertEquals("RESIDENT_EVIL", dsl.getValueFromTextField("elementosForm:nome"));
	}
	
	@Test
	public void campoTreinamentoTextAreaTest() {
		dsl.escreve("elementosForm:sugestoes", "Teste Selenium IDE");
		Assert.assertEquals("Teste Selenium IDE", dsl.getValueFromTextField("elementosForm:sugestoes"));
	}
	
	@Test
	public void campoTreinamentoRadioButtonTest() {
		
		dsl.clickRadioButton("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioButtonSelected("elementosForm:sexo:0"));
		
	}
	
	@Test
	public void campoTreinamentoCheckBoxTest() {
		
		dsl.clickCheckBox("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isCheckBoxSelected("elementosForm:comidaFavorita:2"));
		
	}
	
	@Test
	public void campoTreinamentoComboBoxTest() {
		
		dsl.selectCombobox("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.getValueCombobox("elementosForm:escolaridade"));
		
	}
	
	@Test
	public void campoTreinamentoGetValuesComboBoxTest() {
		
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean find = false;
		for (WebElement option : options) {
			
			if (option.getText().equals("Mestrado")) {
				
				find = true;
				break;
			}
		}
		
		Assert.assertTrue(find);
	}
	
	@Test
	public void campoTreinamentoGetValueSelectMultiploTest() {
		
		dsl.selectCombobox("elementosForm:esportes", "Natacao");
		dsl.selectCombobox("elementosForm:esportes", "Karate");
		dsl.selectCombobox("elementosForm:esportes", "O que eh esporte?");
		
		List<String> opcoesMarcadas = dsl.getValuesCombobox("elementosForm:esportes");		
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Karate");
		opcoesMarcadas = dsl.getValuesCombobox("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}
	
	@Test
	public void campoTreinamentoButtonTest() {
		
		dsl.clickButton("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
		
	}
	
	@Test
	public void campoTreinamentoLinksTest() {
		
		dsl.clickLink("Voltar");
		Assert.assertEquals("Voltou!",dsl.getText("resultado"));
		
	}
	
	@Test
	public void campoTreinamentoBuscaTextoPaginaTest() {
		
//		Assert.assertTrue(driver.findElement(By.tagName("body"))
//				.getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.getTextLinkById(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.getTextLinkById(By.className("facilAchar")));
	
	}
	
	@Test
	public void javaScriptTest() {
		
		WebElement elemnent = driver.findElement(By.id("elementosForm:nome"));
		dsl.escreveById(By.id("elementosForm:nome"), "Manipulando JS com Selenium");
		dsl.executarJavaScript("arguments[0].style.border = arguments[1]", elemnent, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabelsTest() {
		
		dsl.clicarBotaoTabela("Nome", "Francisco", "Botao", "elementosForm:tableUsuarios");
	}
	
}
