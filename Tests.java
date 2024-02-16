import YouTubeThing.HomePageSetUp;
import YouTubeThing.PageAfterSearch;
import YouTubeThing.Xpaths;
import YouTubeThing.waitConditions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class Tests {
    WebDriver driver = new ChromeDriver();
    HomePageSetUp homePageSetUp1 = new HomePageSetUp(driver);
    PageAfterSearch pageAfterSearch;

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
        Assert.assertTrue(filterButton.isDisplayed(), "Filters Button not displayed");
    }

    @Test(priority = 1)
    public void applyFilter1Test() {
        pageAfterSearch.applyFilters("This year");
    }

//   @Test(priority = 2)
//   public void applyFilter2Test() {
//        pageAfterSearch.applyFilters("Video");
//    }

    @Test(priority = 2)
    public void applyFilter2Test() {
        pageAfterSearch.applyFilters("Over 20 minutes");
    }

    @Test(priority = 3)
    public void applyFilter3Test() {
        pageAfterSearch.applyFilters("4K");
    }

    @Test(priority = 4)
    public void selectFirstVideo(){
        pageAfterSearch.clickOnVideo();
    }


}

