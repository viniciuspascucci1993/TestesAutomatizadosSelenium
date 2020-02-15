package br.com.vinicius.tests.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FrameeJanelasPopUpsTest {
	
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
		driver.close();
		dsl.trocarJanela("");
		dsl.escreveById(By.tagName("textarea"), "Ainda é melhor que Octane u.u");
	}
	
	@Test
	public void deveInteragirComFrameEscondidoTest() {
		
		WebElement frame = driver.findElement(By.id("frame2"));
		dsl.executarJavaScript("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		dsl.clickButton("frameButton");
		String msgAlert = dsl.obterTextAlertAndAccept();
		Assert.assertEquals("Frame OK!", msgAlert);
	}
	
	@Test
	public void deveInteragirComJanelasPopUpsSemTituloTest() {
		
		dsl.clickButton("buttonPopUpHard");
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[1]);
		dsl.escreveById(By.tagName("textarea"), "That's OK");
		
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[0]);
		dsl.escreveById(By.tagName("textarea"), "That's OK Dude");
		
	}
}
