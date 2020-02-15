package br.com.vinicius.tests.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Classe para verificar o titulo do google se está de acordo ( Selenium ).
 * @author Vinicius-PC.
 */
public class GoogleClass {
	
	
	private WebDriver driver;
	
	@Before
	public void initSelenium() {
		System.setProperty("webdriver.chrome.driver", "C:\\driversSE\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
	}
	
	@After
	public void afterInitSelenium() {
		driver.quit();
	}

	@Test
	public void testeBrowserAutomate() {
		
		driver.get("https://www.google.com");
						
		Assert.assertEquals("Google", driver.getTitle());
		
		// fechar o browser ( todas as abas ).
	}
}
