package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class InformationStateTest {
    private WebDriver driver;
    private WebElement state;

    @Before
    public void setUp() {
        // Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\55119\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // Navegar para a página da 1M2!
        driver.get("https://www.1m2.com.br/");

        // Clicar no filtro de texto Estado
        driver.findElement(By.cssSelector("#searchComponent > div.search-box.search-box--show > div:nth-child(1)"))
                .click();
    }

    @Test
    public void testFirstStateVerification() {
        // Clicar na opção com texto "Bahia"
        state = driver.findElement(By.cssSelector("div.filter-box__options.filter-box__options--show > div:nth-child(1)"));
        state.click();
    }
    @Test
    public void testSecondStateVerification() {
        // Clicar na opção com texto "Bahia"
        state = driver.findElement(By.cssSelector("div.filter-box__options.filter-box__options--show > div:nth-child(2)"));
        state.click();
    }

    @After
    public void tearDown() {
        // Pegar o texto da opção estado selecionada e trazer para maiuscula
        String textoNoElementostate = state.getText().toUpperCase();

        // Clicar no Botão que possui o texto "ENCONTRAR MEU LOTE"e navegar para a página do estado escolhido
        WebElement botaoEncontrarMeuLote = driver.findElement(By.xpath("//*[@id=\"searchComponent\"]/button"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();",botaoEncontrarMeuLote);

        // Validar que dentro do elemento com class "title" está o nome do estado selecionado na pagina anterior
        WebElement title = driver.findElement(By.className("title"));
        String textoNoElementoTitle = title.getText();
        assertEquals( textoNoElementostate, textoNoElementoTitle);

        // Fechar o browser
        driver.close();
    }

}
