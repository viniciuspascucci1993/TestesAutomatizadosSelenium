package br.com.vinicius.tests.selenium.test;

import static br.com.vinicius.tests.selenium.core.DriverFactory.getDriver;
import static br.com.vinicius.tests.selenium.core.DriverFactory.killDriver;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.vinicius.tests.selenium.core.DSL;

public class AlertTest {
	
	private DSL dsl;
	
	@Before
	public void initSelenium() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void afterInitSelenium() {
		killDriver();
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
