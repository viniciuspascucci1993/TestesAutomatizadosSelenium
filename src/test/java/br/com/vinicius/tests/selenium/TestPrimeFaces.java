package br.com.vinicius.tests.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestPrimeFaces {
	
	private WebDriver driver;
	
	private DSL dsl;
		
	@Before
	public void initSelenium() {
		System.setProperty("webdriver.chrome.driver", "C:\\driversSE\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		dsl = new DSL(driver);
	}
	
	@After
	public void afterInitSelenium() {
		driver.quit();
	}

	@Test
	public void deveInteragirComRadioButtonPrimeFaces() {
		
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");

		dsl.clickRadioButtonBy(By.xpath("//input[@id='j_idt721:console:0']/../..//span"));
		Assert.assertTrue(dsl.isRadioButtonSelected("j_idt721:console:0"));
		
		dsl.clickRadioButtonBy(By.xpath("//label[.='PS4']/..//span"));
		Assert.assertTrue(dsl.isRadioButtonSelected("j_idt721:console:1"));
	}
	
	@Test
	public void primeFacesSelectComboTest() {
		
		driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");		
			
		dsl.selecionarComboPrimeFaces("j_idt721:console", "Xbox One");;
			
		Assert.assertEquals("Xbox One", dsl.getText("j_idt721:console_label"));
	}
}
