package es.s2o.selenium;


import com.sun.java.swing.plaf.windows.resources.windows;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * - Window: get, getTitle, getCurrentUrl, getPageSource, close, quit
 * - Navigate: to, back, forward, refresh
 * - FindElement & FindElements
 * switchTo: frame, alert, window…
 * WebElement: click, clear, findElement/s, getAttribute, getText, sendkeys…
 * Locators según preferencia:
 * By Id
 * By name
 * By css: https://saucelabs.com/resources/articles/selenium-tips-css-selectors
 * By xpath
 * Wait: implicitlyWait vs explicitWait (expected conditions)
 */
public class SeleniumOptions {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static WebDriver driver;

    @Test
    public void testWebDrives() throws InterruptedException
    {
        LOGGER.debug("start testWebDrive");

        System.setProperty ("webdriver.chrome.driver","bin/chromedriver.exe" );
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        driver.manage().window().maximize() ;
        LOGGER.debug("driver started");

        driver.get("https://the-internet.herokuapp.com" );
        driver.getTitle();
        driver.getCurrentUrl();
        driver.getPageSource();

        driver.navigate().to("https://the-internet.herokuapp.com/abtest");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        driver.navigate().to("https://the-internet.herokuapp.com");
        driver.findElements(By.cssSelector("li"));
        driver.findElement(By.linkText("JavaScript Alerts")).click();
        List<WebElement> buttons = driver.findElements(By.cssSelector("button"));
        buttons.get(0).click();
        driver.switchTo().alert().accept();

        buttons.get(1).click();
        driver.switchTo().alert().dismiss();

        driver.navigate().to("https://the-internet.herokuapp.com/nested_frames");

        driver.findElements(By.cssSelector("frame[src='/frame_bottom'"));
        driver.switchTo().frame(0);

        driver.get("https://the-internet.herokuapp.com/windows");
        Set<String> windowHandles = driver.getWindowHandles();
        driver.findElement(By.linkText("Click Here")).click();
        Set<String> windowHandlesAfter = driver.getWindowHandles();
        windowHandlesAfter.removeAll(windowHandles);
        driver.switchTo().window(windowHandlesAfter.stream().findFirst().get()).getTitle();

        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1" );
        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));
        myDynamicElement.getText();

        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1" );
        driver.findElement(By.tagName("button")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1" );
        WebElement myDynamicElement2 = driver.findElement(By.tagName("button"));

        try {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            InputStream initialStream = new FileInputStream(scrFile);
            File targetFile = new File("targetFile.png");
            java.nio.file.Files.copy(initialStream, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            LOGGER.debug("TakesScreenshot error", e);
        }

        driver.close();
        LOGGER.debug("driver closed");
    }

}
