package br.com.vinicius.tests.selenium.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.vinicius.tests.selenium.CampoTreinamentoTest;
import br.com.vinicius.tests.selenium.DesafioCadastroSucessoTest;
import br.com.vinicius.tests.selenium.RegrasCadastroGenerico;

@RunWith(Suite.class)
@SuiteClasses({
	DesafioCadastroSucessoTest.class,
	RegrasCadastroGenerico.class,
	CampoTreinamentoTest.class
})
/**
 * Suite de tests para assumir a ordem dos testes.
 * @author Vinicius-PC.
 */
public class SuiteTest {  }
