package SiiShop;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SiiShopTest {
    private WebDriver driver;
    //private static final Logger log = LoggerFactory.getLogger("SiiShopTest.class"); //->poniewaz dodalismy annotacje @Slf4j

    @BeforeAll
    static void setConfiguration() {
        log.info("Start database");
    }

    @BeforeEach
    void setUpDriver() {
        WebDriverManager.chromedriver().setup();//nie potrzebny SystemsetUp Properties, sciaga pliki/drivery,pojawil sie obiekt teraz selenium manager bo selenium sie spodobal web driver manager

        //ChromeOptions
        ChromeOptions optionsChrome = new ChromeOptions();
        //optionsChrome.addArguments("start-maximized");//od razu przegladarka bedzie duza a nie krok po kroku driver.manage().window().maximize()...
        //optionsChrome.addArguments("incognito");//zyskujemy ok 7% czasu bo nie ma cookies
        optionsChrome.addArguments("headless");//nie widzimy graficznych okien przegladarki - incognito i maximized nie ma znaczenia
        optionsChrome.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking", "enable-automation"));//to remove info bar
        optionsChrome.addArguments("--ignore-certificate-errors");//certyfikaty https sa ignorowane

        driver = new ChromeDriver(optionsChrome);

        //WAITS strategy [ predkosci na roznych srodowiskach, testowe, produkcyje .. sa rozne ]
        //1. implicit waits ->
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));//czekanie na interakcje z webElement-ami
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));//ladowanie sie strony na poczatku -> driver.get(url)
        //2. explicit waits ->
        //3. fluent   waits ->
    }

    @AfterEach
    void exit() {
        String stars = "* ".repeat(10);
        log.info(stars + "Test passed" + stars);
        driver.quit(); //cala przegladarka, a -> driver.close() to biezaca okno
        log.info("Driver was closed properly");
    }

    @AfterAll
    static void closeConnection() {
        log.info("Database connection closed");
    }

    @Test
    public void loginToSystem() throws InterruptedException {
        //Konfiguracja WebDriver-a
        String browser = "chrome";
        String url = "http://146.59.32.4/index.php";

        log.info("url naszej aplikacji to: " + url);
        log.info("Przegladarka: " + browser);

        driver.get(url);
        String actualTitle = driver.getTitle();
        log.info("Current title is: "+actualTitle);
        String expectedTitle = "TesterSii";
        assertThat(expectedTitle).isEqualTo(actualTitle);
        assertThat(actualTitle).startsWith("T");
        assertThat(actualTitle).isNotBlank();
        assertThat(22).isGreaterThan(55);
        assertThat(22).isGreaterThan(actualTitle.length());
    }

    @Test
    public void loginToPudelek() throws InterruptedException {
        //Konfiguracja WebDriver-a
        String browser = "chrome";
        String url = "http://pudelek.pl/";

        log.info("url naszej aplikacji to: " + url);
        log.info("Przegladarka: " + browser);

        driver.get(url);
        String actualTitle = driver.getTitle();
        log.info("Current title is: "+actualTitle);
        String expectedTitle = "Pudelek.pl";
        assertThat(actualTitle).contains(expectedTitle);
    }

    @Test
    public void loginToWpPl() throws InterruptedException {
        //Konfiguracja WebDriver-a
        String browser = "chrome";
        String url = "http://wp.pl";

        log.info("url naszej aplikacji to: " + url);
        log.info("Przegladarka: " + browser);

        driver.get(url);
        String actualTitle = driver.getTitle();
        log.info("Current title is: "+actualTitle);
        String expectedTitle = "Wirtualna Polska";
        assertThat(actualTitle).contains(expectedTitle);
    }
}
