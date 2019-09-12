package automation.example.test.webelement;

import automation.example.utils.CommonUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OpeningJobsTest {

    private static String PAGE_URL = "https://career.vng.com.vn/co-hoi-nghe-nghiep";
    private static String BANNER_TEXT = "Môi trường thúc đẩy sự sáng tạo,\n" +
            "con người VNG cởi mở chào đón\n" +
            "những ai dám đón nhận thách\n" +
            "thức để theo đuổi đam mê.";
    private static List<String> OPENING_JOBS = Arrays.asList(
            "Software Engineer (Go lang)",
            "Senior Business Analyst (ZaloPay)",
            "VFX Artist",
            "Biên Phiên Dịch tiếng Trung",
            "Graphic Designer",
            "Business Development Executive",
            "System Engineer (open level)",
            "Senior Software Engineer (Golang, Zalopay)"
    );

    private OpeningJobsTest page;
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='homeBannerTitle']")
    private WebElement bannerTitle;

    @FindBy(xpath = "//span[@class='NameCate']")
    private List<WebElement> jobNameElements;

    @BeforeClass
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        page = PageFactory.initElements(driver, OpeningJobsTest.class);
    }

    @Test
    public void verifyPageBanner() {
        openPage();
        Assert.assertEquals(page.bannerTitle.getText(), BANNER_TEXT);
    }

    @Test
    public void verifyOpeningJobsList() {
        openPage();
        List<String> names = new ArrayList<>();
        for (int i = 0; i < page.jobNameElements.size(); i++) {
             names.add(page.jobNameElements.get(i).getText());
        }
        Collections.sort(OPENING_JOBS);
        Collections.sort(names);
        Assert.assertEquals(OPENING_JOBS, names);
    }

    @Test
    public void verifyNewImageOnJobTitle() {
        openPage();
        List<WebElement> jobTitleElements = driver.findElements(By.xpath("//div[@class='jobBlock']"));
        for (WebElement element : jobTitleElements) {
            List<WebElement> elements = element.findElements(By.xpath(".//*[@class='newJob']"));
            Assert.assertFalse(element.findElements(By.xpath("//*[@class='newJob']")).isEmpty());
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    private void openPage() {
        driver.navigate().to(PAGE_URL);
        CommonUtils.sleep(3);
    }
}
