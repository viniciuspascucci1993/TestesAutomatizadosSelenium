package br.com.vinicius.tests.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertTest {
	
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
	public void deveInteragirComAlertSimplesTest() {
	
		dsl.clickButton("alert");
		
		String text = dsl.obterTextAlertAndAccept();
		assertEquals("Alert Simples", text);
		
		dsl.escreve("elementosForm:nome", text);
		
	}
	
	@Test
	public void deveInteragirComAlertConfirmTest() {
		
		dsl.clickButton("confirm");
		
		assertEquals("Confirm Simples", dsl.obterTextAlertAndAccept());
		assertEquals("Confirmado", dsl.obterTextAlertAndAccept());
		
		dsl.clickButton("confirm");
		assertEquals("Confirm Simples", dsl.obterTextAlertAndDenied());
		assertEquals("Negado", dsl.obterTextAlertAndDenied());
		
	}
	
	@Test
	public void deveInteragirComAlertPromptTest() {
		
		dsl.clickButton("prompt");
		
		assertEquals("Digite um numero", dsl.alertaObterText());
		
		dsl.alertaEscrever("12");
		
		assertEquals("Era 12?", dsl.obterTextAlertAndAccept());
		assertEquals(":D", dsl.obterTextAlertAndAccept());
		
	}
}
