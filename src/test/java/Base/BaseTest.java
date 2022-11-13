package Base;

import Factory.DriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

@Slf4j
public class BaseTest  {
    protected WebDriver driver;
    protected static DriverFactory driverFactory;

    @BeforeAll
    static void setConfiguration() {
        driverFactory = new DriverFactory();
    }

    @BeforeEach
    void setUpDriver() {
        //FEATURE TOGGLE***
        driver = driverFactory.getDriver("chrome");//"kurier przynosi nam paczke z driverem, musimy ja odebrac dlatego przypisjemy do drivera, i ten driver jest protected"
        log.info("WebDriver started successfully");
    }

    @AfterEach
    void exit() {
        driver.quit(); //cala przegladarka, a -> driver.close() to biezaca okno
        log.info("Driver was closed properly");
    }

/*    @AfterAll
    static void closeConnection() {
        log.info("Database connection closed");
    }*/
}
