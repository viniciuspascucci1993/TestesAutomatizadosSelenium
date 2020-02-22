package br.com.vinicius.tests.selenium.suite;

import static br.com.vinicius.tests.selenium.core.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.vinicius.tests.selenium.RegrasCadastroGenerico;
import br.com.vinicius.tests.selenium.test.DesafioCadastroSucessoTest;

@RunWith(Suite.class)
@SuiteClasses({
	DesafioCadastroSucessoTest.class,
	RegrasCadastroGenerico.class,
})
/**
 * Suite de tests para assumir a ordem dos testes.
 * @author Vinicius-PC.
 */
public class SuiteTest {  
	
	@AfterClass
	public static void closeAll() {
		killDriver();
	}
}
