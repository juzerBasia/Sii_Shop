package Factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Arrays;

@Slf4j
public class DriverFactory {

    private WebDriver driver;

    public WebDriver getDriver(String browser) {

        switch (browser) {

            case "chrome":
                log.info("Browser = " + browser);
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
                break;
            case "firefox":
                log.info("Browser = " + browser);
                WebDriverManager.firefoxdriver().setup();
                //Options are not working and were removed !!!!!!

                driver = new FirefoxDriver();
                //WAITS strategy [ predkosci na roznych srodowiskach, testowe, produkcyje .. sa rozne ]
                //1. implicit waits ->
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));//czekanie na interakcje z webElement-ami
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));//ladowanie sie strony na poczatku -> driver.get(url)
                //2. explicit waits ->
                //3. fluent   waits ->
                break;
            default:
                log.info("Browser = " + browser);
                WebDriverManager.chromedriver().setup();//nie potrzebny SystemsetUp Properties, sciaga pliki/drivery,pojawil sie obiekt teraz selenium manager bo selenium sie spodobal web driver manager

                //ChromeOptions
                ChromeOptions optionsChromeDefault = new ChromeOptions();
                //optionsChrome.addArguments("start-maximized");//od razu przegladarka bedzie duza a nie krok po kroku driver.manage().window().maximize()...
                //optionsChrome.addArguments("incognito");//zyskujemy ok 7% czasu bo nie ma cookies
                optionsChromeDefault.addArguments("headless");//nie widzimy graficznych okien przegladarki - incognito i maximized nie ma znaczenia
                optionsChromeDefault.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking", "enable-automation"));//to remove info bar
                optionsChromeDefault.addArguments("--ignore-certificate-errors");//certyfikaty https sa ignorowane
                driver = new ChromeDriver(optionsChromeDefault);

                //WAITS strategy [ predkosci na roznych srodowiskach, testowe, produkcyje .. sa rozne ]
                //1. implicit waits ->
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));//czekanie na interakcje z webElement-ami
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));//ladowanie sie strony na poczatku -> driver.get(url)
                //2. explicit waits ->
                //3. fluent   waits ->

        }
        return this.driver;
    }
}


