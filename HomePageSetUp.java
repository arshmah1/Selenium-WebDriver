package YouTubeThing;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageSetUp {
    WebDriver driver;

    public HomePageSetUp(WebDriver driver) {
        this.driver=driver;
    }

    public void goToHomePage(WebDriver driver){
        driver.get(Xpaths.youTubeHomePageURL);
    }

    public PageAfterSearch searchForC(WebDriver driver){
        WebElement webElement= driver.findElement(By.xpath(Xpaths.searchInputXpath));
        webElement.sendKeys("c++");
        driver.findElement(By.xpath(Xpaths.searchButtonXpath)).sendKeys(Keys.RETURN);
        waitConditions.waitForVisibility(driver, Xpaths.filtersButtonXpath,10);
        return new PageAfterSearch(driver);
    }


}
