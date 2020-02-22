package br.com.vinicius.tests.selenium.test;

import static br.com.vinicius.tests.selenium.core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.com.vinicius.tests.selenium.core.DSL;
import br.com.vinicius.tests.selenium.core.DriverFactory;

public class CampoTreinamentoTest {
	
	private DSL dsl;
	
	@Before
	public void initSelenium() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finaliza(){
		DriverFactory.killDriver();
	}
	
	@Test
	public void campoTreinamentoTextFieldTest() {
				
		dsl.escreve("elementosForm:nome", "Teste de escrita");
		Assert.assertEquals("Teste de escrita", dsl.getValueFromTextField("elementosForm:nome"));
		
	}
	
	@Test
	public void textFieldDuploTest() {
		
		dsl.escreve("elementosForm:nome", "BIOHAZARD");
		Assert.assertEquals("BIOHAZARD", dsl.getValueFromTextField("elementosForm:nome"));
		
		dsl.escreve("elementosForm:nome", "RESIDENT_EVIL");
		Assert.assertEquals("RESIDENT_EVIL", dsl.getValueFromTextField("elementosForm:nome"));
	}
	
	@Test
	public void campoTreinamentoTextAreaTest() {
		dsl.escreve("elementosForm:sugestoes", "Teste Selenium IDE");
		Assert.assertEquals("Teste Selenium IDE", dsl.getValueFromTextField("elementosForm:sugestoes"));
	}
	
	@Test
	public void campoTreinamentoRadioButtonTest() {
		
		dsl.clickRadioButton("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioButtonSelected("elementosForm:sexo:0"));
		
	}
	
	@Test
	public void campoTreinamentoCheckBoxTest() {
		
		dsl.clickCheckBox("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isCheckBoxSelected("elementosForm:comidaFavorita:2"));
		
	}
	
	@Test
	public void campoTreinamentoComboBoxTest() {
		
		dsl.selectCombobox("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.getValueCombobox("elementosForm:escolaridade"));
		
	}
	
	@Test
	public void campoTreinamentoGetValuesComboBoxTest() {
		
		WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean find = false;
		for (WebElement option : options) {
			
			if (option.getText().equals("Mestrado")) {
				
				find = true;
				break;
			}
		}
		
		Assert.assertTrue(find);
	}
	
	@Test
	public void campoTreinamentoGetValueSelectMultiploTest() {
		
		dsl.selectCombobox("elementosForm:esportes", "Natacao");
		dsl.selectCombobox("elementosForm:esportes", "Karate");
		dsl.selectCombobox("elementosForm:esportes", "O que eh esporte?");
		
		List<String> opcoesMarcadas = dsl.getValuesCombobox("elementosForm:esportes");		
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "Karate");
		opcoesMarcadas = dsl.getValuesCombobox("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}
	
	@Test
	public void campoTreinamentoButtonTest() {
		
		dsl.clickButton("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
		
	}
	
	@Test
	public void campoTreinamentoLinksTest() {
		
		dsl.clickLink("Voltar");
		Assert.assertEquals("Voltou!",dsl.getText("resultado"));
		
	}
	
	@Test
	public void campoTreinamentoBuscaTextoPaginaTest() {
		
		Assert.assertEquals("Campo de Treinamento", dsl.getXPath(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.getXPath(By.className("facilAchar")));
	
	}
	
	@Test
	public void javaScriptTest() {
		
		WebElement elemnent = getDriver().findElement(By.id("elementosForm:nome"));
		dsl.escreveById(By.id("elementosForm:nome"), "Manipulando JS com Selenium");
		dsl.executarJavaScript("arguments[0].style.border = arguments[1]", elemnent, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabelsTest() {
		
		dsl.clicarBotaoTabela("Nome", "Francisco", "Botao", "elementosForm:tableUsuarios");
	}
	
}
