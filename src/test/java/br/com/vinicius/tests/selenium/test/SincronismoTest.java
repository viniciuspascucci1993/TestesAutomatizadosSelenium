package br.com.vinicius.tests.selenium.test;

import static br.com.vinicius.tests.selenium.core.DriverFactory.getDriver;
import static br.com.vinicius.tests.selenium.core.DriverFactory.killDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.vinicius.tests.selenium.core.DSL;

public class SincronismoTest {
	
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
	public void deveUtilizarEsperaFixa() throws InterruptedException {
		
		dsl.clickButton("buttonDelay");
		Thread.sleep(5000);
		dsl.escreve("novoCampo", "It Works?");
	}
	
	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException {
		
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dsl.clickButton("buttonDelay");
		dsl.escreve("novoCampo", "It Works?");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	@Test
	public void deveUtilizarEsperaExplicita() throws InterruptedException {
		
		dsl.clickButton("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escreve("novoCampo", "It Works?");
	}
	
}
