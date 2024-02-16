import YouTubeThing.HomePageSetUp;
import YouTubeThing.PageAfterSearch;
import YouTubeThing.Xpaths;
import YouTubeThing.waitConditions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import static org.testng.Assert.assertEquals;

public class Tests {
    WebDriver driver = new ChromeDriver();
    HomePageSetUp homePageSetUp1 = new HomePageSetUp(driver);
    PageAfterSearch pageAfterSearch;
    ExtentReports extentReports ;
    ExtentTest test;

    @BeforeClass
    public void homePageSetupTest() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        homePageSetUp1.goToHomePage(driver);
        waitConditions.waitForVisibility(driver, Xpaths.searchInputXpath, 6);
        assertEquals(driver.getTitle(), "YouTube", "Title not matched");
    }

    @Test(priority = 0)
    public void searchTest() {
        pageAfterSearch = homePageSetUp1.searchForC(driver);
        WebElement filterButton = driver.findElement(By.xpath(Xpaths.filtersButtonXpath));
        test.log(Status.INFO,"Performing search test");
        Assert.assertTrue(filterButton.isDisplayed(), "Filters Button not displayed");
        test.pass("search test passed");
    }

    @Test(priority = 1)
    public void applyFilter1Test() {
        test.log(Status.INFO,"Applying filter 'This year' - 'Video' filter is automatically applied when this filter is selected");
        pageAfterSearch.applyFilters("This year");
        test.pass("filters applied");
    }

//   @Test(priority = 2)
//   public void applyFilter2Test() {
//        pageAfterSearch.applyFilters("Video");
//    }

    @Test(priority = 2)
    public void applyFilter2Test() {
        test.log(Status.INFO,"Applying filter 'Over 20 minutes'");
        pageAfterSearch.applyFilters("Over 20 minutes");
        test.pass("filter applied");
    }

    @Test(priority = 3)
    public void applyFilter3Test() {
        test.log(Status.INFO,"Applying filter '4K'");
        pageAfterSearch.applyFilters("4K");
        test.pass("filter applied");
    }

    @Test(priority = 4)
    public void selectFirstVideo(){
        test.log(Status.INFO,"selecting the first video");
        pageAfterSearch.clickOnVideo();
        test.pass("selected");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @BeforeSuite
    public void initialiseExtentReports(){
        ExtentSparkReporter sparkReporter_all = new ExtentSparkReporter("AllTests.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter_all);
    }

    @AfterSuite
    public void generateExtentReports(){
        extentReports.flush();
        try {
            Desktop.getDesktop().browse(new File("AllTests.html").toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @BeforeMethod
    public void beforeMethod(Method method){
        test = extentReports.createTest(method.getName());
    }
}

