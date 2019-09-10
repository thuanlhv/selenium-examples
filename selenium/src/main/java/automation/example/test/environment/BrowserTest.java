package automation.example.test.environment;

import automation.example.utils.CommonUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class BrowserTest {

    private WebDriver driver;

    private static String GOOGLE_URL = "http://google.com";

    @BeforeClass
    public void executeBeforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void openGooglePageTest() {
        driver = new ChromeDriver();
        driver.navigate().to(GOOGLE_URL);
        CommonUtils.sleep(2);
        String title = driver.getTitle();
        Assert.assertEquals(title, "Google");
    }

    @AfterClass
    public void executeAfterClass() {
        driver.quit();
    }
}
