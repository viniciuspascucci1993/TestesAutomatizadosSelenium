package br.com.vinicius.tests.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AjaxTest {
	
	private WebDriver driver;
	
	private DSL dsl;
	
	@Before
	public void initSelenium() {
		System.setProperty("webdriver.chrome.driver", "C:\\driversSE\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL(driver);
	}
	
	@After
	public void afterInitSelenium() {
		driver.quit();
	}

	@Test
	public void ajaxTest() {
		
		dsl.escreve("j_idt720:name", "It Works dude?");
		dsl.clickButton("j_idt720:j_idt723");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt792_start")));
		Assert.assertEquals("It Works dude?", dsl.getText("j_idt720:display"));
	}
}
