package br.com.vinicius.tests.selenium.test;

import static br.com.vinicius.tests.selenium.core.DriverFactory.getDriver;
import static br.com.vinicius.tests.selenium.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.vinicius.tests.selenium.core.DSL;

public class FrameeJanelasPopUpsTest {	
	
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
	public void deveInteragirComIFrameTest() {
		
		dsl.entrarFrame("frame1");
		dsl.clickButton("frameButton");
		
		String msgAlert = dsl.obterTextAlertAndAccept();
		Assert.assertEquals("Frame OK!", msgAlert);
		
		dsl.sairFrame();
		dsl.escreve("elementosForm:nome", msgAlert);
	}
	
	@Test
	public void deveInteragirComJanelasPopUpsTest() {
		
		dsl.clickButton("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escreveById(By.tagName("textarea"), "Selenium é melhor que Octane KKK");
		getDriver().close();
		dsl.trocarJanela("");
		dsl.escreveById(By.tagName("textarea"), "Ainda é melhor que Octane u.u");
	}
	
	@Test
	public void deveInteragirComFrameEscondidoTest() {
		
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executarJavaScript("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		dsl.clickButton("frameButton");
		String msgAlert = dsl.obterTextAlertAndAccept();
		Assert.assertEquals("Frame OK!", msgAlert);
	}
	
	@Test
	public void deveInteragirComJanelasPopUpsSemTituloTest() {
		
		dsl.clickButton("buttonPopUpHard");
		System.out.println(getDriver().getWindowHandle());
		System.out.println(getDriver().getWindowHandles());
		
		dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[1]);
		dsl.escreveById(By.tagName("textarea"), "That's OK");
		
		dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[0]);
		dsl.escreveById(By.tagName("textarea"), "That's OK Dude");
		
	}
}
