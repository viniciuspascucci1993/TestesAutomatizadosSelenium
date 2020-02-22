package br.com.vinicius.tests.selenium.test;

import static br.com.vinicius.tests.selenium.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.com.vinicius.tests.selenium.core.DSL;
import br.com.vinicius.tests.selenium.core.DriverFactory;

public class TestPrimeFaces {
	
	private DSL dsl;
		
	@Before
	public void initSelenium() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void afterInitSelenium() {
		DriverFactory.killDriver();
	}

	@Test
	public void deveInteragirComRadioButtonPrimeFaces() {
		
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");

		dsl.clickRadioButtonBy(By.xpath("//input[@id='j_idt721:console:0']/../..//span"));
		Assert.assertTrue(dsl.isRadioButtonSelected("j_idt721:console:0"));
		
		dsl.clickRadioButtonBy(By.xpath("//label[.='PS4']/..//span"));
		Assert.assertTrue(dsl.isRadioButtonSelected("j_idt721:console:1"));
	}
	
	@Test
	public void primeFacesSelectComboTest() {
		
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");		
		dsl.selecionarComboPrimeFaces("j_idt721:console", "Xbox One");;
			
		Assert.assertEquals("Xbox One", dsl.getText("j_idt721:console_label"));
	}
}
