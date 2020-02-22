package br.com.vinicius.tests.selenium.test;

import static br.com.vinicius.tests.selenium.core.DriverFactory.getDriver;
import static br.com.vinicius.tests.selenium.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.vinicius.tests.selenium.core.DSL;

public class AjaxTest {
		
	private DSL dsl;
	
	@Before
	public void initSelenium() {
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL();
	}
	
	@After
	public void afterInitSelenium() {
		killDriver();
	}

	@Test
	public void ajaxTest() {
		
		dsl.escreve("j_idt720:name", "It Works dude?");
		dsl.clickButton("j_idt720:j_idt723");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt792_start")));
		Assert.assertEquals("It Works dude?", dsl.getText("j_idt720:display"));
	}
}
