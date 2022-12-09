package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CommonMethods {

    public static WebDriver driver;

    public static void openBrowserAndLaunchApplication() {

        ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);

        switch (ConfigReader.getPropertyValue("browser")) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            default -> throw new RuntimeException("Invalid Browser name");
        }

        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);

    }

    public static void closeBrowser() {

        driver.quit();
    }

public static void sendText(WebElement element, String textToSend) {

        element.clear();
        element.sendKeys(textToSend);

}

//to get webdriver wait

    public static WebDriverWait getWait() {

        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);

        return wait;
    }

    public static void waitForClickability(WebElement element){

        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void click(WebElement element) {

        waitForClickability(element);
        element.click();
    }

    public static JavascriptExecutor getJSExecutor() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    public static void jsClick(WebElement element) {

        getJSExecutor().executeScript("arguments[0].click();", element);

    }

    public static void selectDropDown(WebElement element, String text) {

        Select s = new Select(element);
        s.selectByVisibleText(text);
    }
}
