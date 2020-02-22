package br.com.vinicius.tests.selenium.page;

import org.openqa.selenium.By;

import br.com.vinicius.tests.selenium.core.BasePage;

public class CampoTreinamentoPage extends BasePage {
	
	public void setNome( String nome ) {
		dsl.escreve("elementosForm:nome", nome);
	}
	
	public void setSobrenome( String sobrenome ) {
		dsl.escreve("elementosForm:sobrenome", sobrenome);
	}
	
	public void getSexoMasculino( ) {
		dsl.clickRadioButton("elementosForm:sexo:0");
	}
	
	public void getSexoFeminino( ) {
		dsl.clickButton("elementosForm:sexo:1");
	}
	
	public void getComidaFavoritaPizza() {
		dsl.clickCheckBox("elementosForm:comidaFavorita:2");
	}
	
	public void getComidaFavoritaCarne() {
		dsl.clickButton("elementosForm:comidaFavorita:0");
	}
	
	public void getComidaVegetariano() {
		dsl.clickButton("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(  String value ) {
		dsl.selectCombobox("elementosForm:escolaridade", value);
	}
	
	public void setEsportes( String... values ) {
		
		for (String valor: values) {
			
			dsl.selectCombobox("elementosForm:esportes", valor);
		}
		
	}
	
	public void cadastrar() {
		dsl.clickButton("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastro() {
		return dsl.getXPath(By.xpath("//*[@id='resultado']/span"));
	}
	
	public String obterNomeCadastro() {
		return dsl.getXPath(By.xpath("//*[@id='descNome']/span"));
	}
	
	public String obterSobrenomeCadastro() {
		return dsl.getXPath(By.xpath("//*[@id='descSobrenome']/span"));
	}
	
	public String obterSexoCadastro() {
		return dsl.getXPath(By.xpath("//*[@id='descSexo']/span"));
	}
	
	public String obterComidaCadastro() {
		return dsl.getXPath(By.xpath("//*[@id='descComida']/span"));
	}
	
	public String obterEscolaridadeCadastro() {
		return dsl.getXPath(By.xpath("//*[@id='descEscolaridade']/span"));
	}
	
	public String obterEsporteCadastro() {
		return dsl.getXPath(By.xpath("//*[@id='descEsportes']/span"));
	}
}
