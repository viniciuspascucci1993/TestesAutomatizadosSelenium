package br.com.vinicius.tests.selenium.core;

/**
 * Classe BasePage responsável por utilizar o construtor para a DSL.
 * @author Vinicius-PC
 */
public class BasePage {

	protected DSL dsl;

	public BasePage() {
		dsl = new DSL();
	}
}
