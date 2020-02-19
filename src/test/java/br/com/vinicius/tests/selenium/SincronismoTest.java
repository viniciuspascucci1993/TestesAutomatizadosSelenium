package br.com.vinicius.tests.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SincronismoTest {

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
	public void deveUtilizarEsperaFixa() throws InterruptedException {
		
		dsl.clickButton("buttonDelay");
		Thread.sleep(5000);
		dsl.escreve("novoCampo", "It Works?");
	}
	
	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dsl.clickButton("buttonDelay");
		dsl.escreve("novoCampo", "It Works?");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	@Test
	public void deveUtilizarEsperaExplicita() throws InterruptedException {
		
		dsl.clickButton("buttonDelay");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escreve("novoCampo", "It Works?");
	}
	
}
