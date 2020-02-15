package business;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ValidarRegraNegocioTest {
	
	private static final String USER_DIR = System.getProperty("user.dir");

	@Test
	public void deveValidaRegraNegocioNomeTest() {
		
		System.setProperty("webdriver.gecko.driver", "C:\\driversSE\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));		
		driver.get("file:///" + USER_DIR + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		driver.quit();
		
	}
	
	@Test
	public void deveValidaRegraNegocioSobrenomeTest() {
		
		System.setProperty("webdriver.gecko.driver", "C:\\driversSE\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));		
		driver.get("file:///" + USER_DIR + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Vinicius");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		driver.quit();
		
	}
	
	@Test
	public void deveValidaRegraNegocioSexoTest() {
		
		System.setProperty("webdriver.gecko.driver", "C:\\driversSE\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));		
		driver.get("file:///" + USER_DIR + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Vinicius");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Pascucci");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		driver.quit();
		
	}
	
	@Test
	public void deveValidaRegraNegocioComidaTest() {
		
		System.setProperty("webdriver.gecko.driver", "C:\\driversSE\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));		
		driver.get("file:///" + USER_DIR + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Vinicius");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Pascucci");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		driver.quit();
		
	}
	
	public void deveValidaRegraNegocioEsporteTest() {
		
		System.setProperty("webdriver.gecko.driver", "C:\\driversSE\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));		
		driver.get("file:///" + USER_DIR + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Vinicius");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Pascucci");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		Alert alert = driver.switchTo().alert();
		
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		driver.quit();
		
	}
	
	
}
