package YouTubeThing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PageAfterSearch extends HomePageSetUp{
    WebDriver driver;

    public PageAfterSearch(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void applyFilters(String string) {
        List<String> initialList = beforeFilterVideoTitleCheck();
        driver.findElement(By.xpath(Xpaths.filtersButtonXpath)).click();
        waitConditions.waitForVisibility(driver,Xpaths.filtersPresenceXpath,5);
        driver.findElement(By.xpath(Xpaths.xpathsForFilter(string))).click();
        List<String> finalList = afterFilterVideoTitleCheck();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(driver -> !initialList.equals(afterFilterVideoTitleCheck()));
    }

    public void clickOnVideo(){
        driver.findElement(By.xpath(Xpaths.firstVideo)).click();
    }

    public List<String> beforeFilterVideoTitleCheck() {
        List<WebElement> videoTitles = driver.findElements(By.xpath(Xpaths.videoTitle));
        List<String> initialTitleList = new ArrayList<>();
        for (int i = 0; i < Math.min(videoTitles.size(), 3); i++) {
            String title = videoTitles.get(i).getAttribute("title");
            initialTitleList.add(title);
        }
        return initialTitleList;
    }
    public List<String> afterFilterVideoTitleCheck(){
        List<WebElement> updatedVideoTitles = driver.findElements(By.xpath(Xpaths.videoTitle));
        List<String> updatedTitleList = new ArrayList<>();
        for (int i = 0; i < Math.min(updatedVideoTitles.size(), 3); i++) {
            String title = updatedVideoTitles.get(i).getAttribute("title");
            updatedTitleList.add(title);
        }
        return updatedTitleList;
    }
}
