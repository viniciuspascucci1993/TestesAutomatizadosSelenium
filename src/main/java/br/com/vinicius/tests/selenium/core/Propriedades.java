package br.com.vinicius.tests.selenium.core;

/**
 * Classe Propriedades que contem constantes.
 * @author Vinicius-PC
 */
public class Propriedades {

	public static final boolean CLOSE_BROWSER = false; 
	
	public static final Browsers brroser = Browsers.CHROME;
	
	public enum Browsers {
		CHROME,
		FIREFOX
	}
}
