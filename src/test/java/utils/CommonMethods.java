package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.PageInitializer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonMethods extends PageInitializer {

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
        initializePageObjects();

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

    public static byte[] takeScreenshot(String fileName) {

        TakesScreenshot ts= (TakesScreenshot)driver;
//        screenshot is taken at this point
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
//        save the file from your variable into the location into your computer

        try {
            FileUtils.copyFile(sourceFile,new File(Constants.SCREENSHOT_FILEPATH+ fileName+ " "+ getTimeStamp("yyyy-MM-dd-HH-mm-ss")+ ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
return picBytes;

    }

    public static String getTimeStamp(String pattern) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);

    }
}
