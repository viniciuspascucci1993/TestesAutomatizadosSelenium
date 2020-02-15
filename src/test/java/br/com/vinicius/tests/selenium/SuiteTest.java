package br.com.vinicius.tests.selenium;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	DesafioCadastroSucessoTest.class,
	RegrasCadastroGenerico.class,
	CampoTreinamentoTest.class
})
public class SuiteTest {  }
