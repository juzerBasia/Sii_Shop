package SiiShop;

import Base.BaseTest;
import Pages.HomePage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class SiiShopTest extends BaseTest {

    //private static final Logger log = LoggerFactory.getLogger("SiiShopTest.class"); //->poniewaz dodalismy annotacje @Slf4j

    @Test
    public void loginToSystem() throws InterruptedException {
        //Konfiguracja WebDriver-a
        String url = "http://146.59.32.4/index.php";
        log.info("url naszej aplikacji to: " + url);

        driver.get(url);
        String actualTitle = driver.getTitle();
        log.info("Current title is: "+actualTitle);
        String expectedTitle = "TesterSii";
        assertThat(expectedTitle).isEqualTo(actualTitle);
        assertThat(actualTitle).startsWith("T");
        assertThat(actualTitle).isNotBlank();
        assertThat(122).isGreaterThan(55);
        assertThat(22).isGreaterThan(actualTitle.length());
    }

    @Test
    public void loginToPudelek() throws InterruptedException {
        String url = "http://pudelek.pl/";
        log.info("url naszej aplikacji to: " + url);

        driver.get(url);
        String actualTitle = driver.getTitle();
        log.info("Current title is: "+actualTitle);
        String expectedTitle = "Pudelek.pl";
        assertThat(actualTitle).contains(expectedTitle);
    }

    @Test
    public void loginToWpPl() {
        String url = "http://wp.pl";
        log.info("url naszej aplikacji to: " + url);

        driver.get(url);
        String actualTitle = driver.getTitle();
        log.info("Current title is: "+actualTitle);
        String expectedTitle = "Wirtualna Polska";
        assertThat(actualTitle).contains(expectedTitle);
    }

/*    @Test
    public void loanTest(){
        HomePage homePage = new HomePage();
        homePage.loginAsRegisteredUser();
        homePage.navigateToLoanForm();
        homePage.fillFirstName();
        homePage.fillLastName();
        homePage.fillLoanAmount();
        homePage.submitLoanForm();
        String message = homePage.takeConfirmationMessage();
        assertThat(message).isEqualTo(expectedMessage);

    }*/
}
