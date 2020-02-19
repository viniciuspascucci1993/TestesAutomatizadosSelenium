package br.com.vinicius.tests.selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Classe responsável por conter método para reutilização de código nas classes de teste
 * @author Vinicius-PC.
 */
public class DSL {
	
	private WebDriver driver;
	
	public DSL(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	/********* TextField e TextArea  ( INICIO )************/
	
	public void escreveById( By by, String text) {
		
		driver.findElement(by).clear();
		
		driver.findElement(by).sendKeys(text);
	}

	public void escreve( String idCampo,  String text) {
		escreveById(By.id(idCampo), text);
	}
	
	public String getValueFromTextField( String idCampo ) {
		return driver.findElement(By.id(idCampo)).getAttribute("value");
	}
	
	/********* TextField e TextArea  ( FIM )************/
	
	/********* Radio e Check  ( INICIO ) ************/
	
	public void clickRadioButtonBy( By by ) {
		driver.findElement(by).click();
	}
	
	public void clickRadioButton( String idRadio ) {
		clickRadioButtonBy(By.id(idRadio));
	}
	
	public void clickCheckBox( String id ) {
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isCheckBoxSelected( String id ) {
		return driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected();
	}
	
	public boolean isRadioButtonSelected( String id ) {
		return driver.findElement(By.id(id)).isSelected();
	}
	
	/********* Radio e Check  ( FIM ) ************/
	
	/********* Select combo box  ( INICIO ) ************/
	
	public void selectCombobox( String id, String value ) {
		
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(value);
	}
	
	public void deselecionarCombo(String id, String valor) {
		
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);
	}
	
	public String getValueCombobox( String id ) {
		
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public List<String> getValuesCombobox(String id) {
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public int getQuantidadesCombo(String id){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public boolean checkCombo(String id, String opcao){
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(opcao)){
				return true;
			}
		}
		return false;
	}
	
	public void selecionarComboPrimeFaces( String radical, String valor ) {
		
		clickRadioButtonBy(By.xpath("//*[@id='"+ radical +"_input']/../..//span"));
		clickRadioButtonBy(By.xpath("//*[@id='"+ radical +"_items']//li[.='"+ valor +"']"));
	}
	
	/********* Select combo box  ( FIM ) ************/
	
	/********* Button ( INICIO ) ************/
	
	public void clickButton( String idButton ) {
		
		driver.findElement(By.id(idButton)).click();
	}
	
	public String obterValueElemento(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");
	}
	
	/********* Button ( FIM ) ************/
	
	/********* Link ( INICIO ) ************/
	
	public void clickLink( String link ) {
		
		driver.findElement(By.linkText(link)).click();
	}
	
	/********* Link ( FIM ) ************/
	
	/********* Textos ( INICIO ) ************/
	
	public String getTextLinkById( By by ) {
		
		return driver.findElement(by).getText();
	}
	
	public String getText( String id ) {
		
		return getTextLinkById(By.id(id));
	}
	
	/********* Textos ( FIM ) ************/
	
	/********* Alert ( INICIO ) ************/
	
	public String alertaObterText() {
		
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}
	
	public String obterTextAlertAndAccept() {
		
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		
		return valor;
	}
	
	public String obterTextAlertAndDenied() {
		
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		
		return valor;
	}
	
	public void alertaEscrever(String valor) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}
	
	/********* Alert ( FIM ) ************/
	
	/********* Frames e Janelas ( INICIO ) ************/
	
	public void entrarFrame(String id) {
		driver.switchTo().frame(id);
	}
	
	public void sairFrame(){
		driver.switchTo().defaultContent();
	}
	
	public void trocarJanela(String id) {
		driver.switchTo().window(id);
	}
	
	/********* Frames e Janelas ( FIM ) ************/
	
	/********* Manipulando e iterando elementos com JS ***********/
	public Object executarJavaScript( String comando, Object... param) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;		
		return js.executeScript(comando, param);
		
	}
	
	/*************** Tabela *********************/
	
	public void clicarBotaoTabela( String colunaBusca, String valor, String colunaBotao, String idTabela) {
		
		//procurar coluna do registro
		WebElement tabela = driver.findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		// encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		// em que coluna esta o botão para clicar
		int idColunaButton = obterIndiceColuna(colunaBotao, tabela);
		
		//clicar no botão da celular encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr["+ idLinha+ "]/td[" + idColunaButton + "]"));
		celula.findElement(By.xpath(".//input")).click();
	}

	private int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td[" + idColuna + "]"));
		int idLinha = -1;
		for (int i = 0; i < linhas.size(); i++) {
			if (linhas.get(i).getText().equals(valor)) {
				
				idLinha = i + 1;
				break;
			}
		}
		
		return idLinha;
	}

	private int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));		
		
		int idColuna = -1;
		
		for (int i = 0; i < colunas.size(); i++) {
			if (colunas.get(i).getText().equals(coluna)) {
				
				idColuna = i + 1;
				break;
			}
		}
		
		return idColuna;
	}
	
}
