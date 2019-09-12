package automation.example.test.webelement;

import automation.example.utils.CommonUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SearchingOpeningJobsTest {

    private static String PAGE_URL = "https://career.vng.com.vn/co-hoi-nghe-nghiep";
    private WebDriver driver;

    @BeforeClass
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verifySearchingJobs() {
        driver.navigate().to(PAGE_URL);
        CommonUtils.sleep(2);

        Select categorySelect = new Select(driver.findElement(By.xpath("//select[@id='cate_id']")));
        categorySelect.selectByVisibleText("Kỹ thuật");

        Select citySelect = new Select(driver.findElement(By.xpath("//select[@id='city_id']")));
        citySelect.selectByValue("503");

        WebElement searchTextBox = driver.findElement(By.xpath("//input[@id='keyword']"));
        searchTextBox.clear();
        searchTextBox.sendKeys("System");

        driver.findElement(By.xpath("//input[@id='reg_btn_search']")).click();
        CommonUtils.sleep(1);

        List<WebElement> jobElements = driver.findElements(By.xpath("//span[@class='NameCate']"));
        List<String> names = new ArrayList<>();
        for (int i = 0; i < jobElements.size(); i++) {
            names.add(jobElements.get(i).getText());
        }

        Collections.sort(names);

        List<String> searchResult = Arrays.asList(
                "System Engineer (open level)",
                "Senior System Engineer",
                "System Engineer (Integration)");
        Collections.sort(searchResult);

        Assert.assertEquals(names, searchResult);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
