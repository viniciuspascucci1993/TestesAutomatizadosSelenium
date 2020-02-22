package br.com.vinicius.tests.selenium;

import static br.com.vinicius.tests.selenium.core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.com.vinicius.tests.selenium.core.BaseTest;
import br.com.vinicius.tests.selenium.core.DSL;
import br.com.vinicius.tests.selenium.page.CampoTreinamentoPage;

@RunWith(Parameterized.class)
public class RegrasCadastroGenerico extends BaseTest {
	
	private DSL dsl;
	
	private CampoTreinamentoPage page;

	@Parameter
	public String nome;
	@Parameter(value = 1)
	public String sobrenome;
	@Parameter(value = 2)
	public String sexo;
	@Parameter(value = 3)
	public List<String> comidas;
	@Parameter(value = 4)
	public String[] esportes;
	@Parameter(value = 5)
	public String mensagem;
	
	@Before
	public void initSelenium() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoTreinamentoPage();
	}
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		
		return Arrays.asList(new Object[][] {

			{"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
			{"Vinicius", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
			{"Vinicius", "Pascucci", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
			{"Vinicius", "Pascucci", "Masculino", Arrays.asList("Carne", "Vegetariano"), 
							new String[]{}, "Tem certeza que voce eh vegetariano?"},
			{"Vinicius", "Pascucci", "Masculino", Arrays.asList("Carne"), 
								new String[]{"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
		});
	}

	@Test
	public void deveValidarRegras(){
		
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		
		if (sexo.equals("Masculino")) {
			page.getSexoMasculino();
			
		} if (sexo.equals("Feminino")) {
			page.getSexoFeminino();
		}
		
		if(comidas.contains("Carne")) page.getComidaFavoritaCarne();
		if(comidas.contains("Pizza")) page.getComidaFavoritaPizza();
		if(comidas.contains("Vegetariano")) page.getComidaVegetariano();
		
		page.setEsportes(esportes);
		page.cadastrar();
		System.out.println(mensagem);
		Assert.assertEquals(mensagem, dsl.obterTextAlertAndAccept());
	}
}
