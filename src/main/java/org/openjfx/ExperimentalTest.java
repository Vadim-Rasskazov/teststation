package org.openjfx;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import java.time.Duration;
//import java.util.List;
//import org.openqa.selenium.WebElement;

public class ExperimentalTest {
    MonitorSize size = new MonitorSize();
    Configs conf = new Configs();
    ChromeDriver driver;
    WebDriverWait wait;

    void testStart() { //start of each test
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options); //run ChromeDriver
        driver.manage().window().setSize(new Dimension(size.width - size.width / 5, size.height)); //resolution
        driver.manage().window().setPosition(new Point(size.width / 5, 0)); //resolution
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(25000)); //waiting for elements
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(25000)); //waiting for the page to load
        driver.manage().timeouts().scriptTimeout(Duration.ofMillis(25000)); //waiting for scripts
        wait = new WebDriverWait(driver, (Duration.ofMillis(25000))); //method for waiting
    }

    void testEnd() throws Exception { //end of each test
        Thread.sleep(6000);
        driver.quit();
    }

    void experimentTest() throws Exception {
        testStart();
        driver.get(conf.experimentalTestUrl);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[2]/a")).click();
        driver.findElement(By.id("input_loginId")).sendKeys(conf.carrierLogin);
        driver.findElement(By.id("input_password-input")).sendKeys(conf.adminPassword);
        driver.findElement(By.id("saveSetting")).click();
        Thread.sleep(1500);
        driver.get(conf.experimentalTestUrl);
        Thread.sleep(30000);
        JavascriptExecutor js = driver;
        js.executeScript("return document.getElementById('channel-header').remove();");
        js.executeScript("return document.getElementById('global-header').remove();");
        for (int i = 1; i < 1000; i++ ) {
//            List<WebElement> elementList = driver.findElements(By.xpath("//p[contains(text(),'Allowed memory size')]"));
//            elementList.get(elementList.size() - 1).click();
            driver.findElement(By.xpath("//p[contains(text(),'Allowed memory size')]")).click();
            Thread.sleep(200);
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div/div/div[2]/div/div/div/div/div/div/div/div[1]/div[2]/div//div[1]/div/div/div[2]/div[1]/div[2]/div/div[2]/div[5]/button")).click();
            Thread.sleep(200);
            driver.findElement(By.cssSelector("[aria-label=Delete]")).click();
            Thread.sleep(200);
            driver.findElement(By.id("deletePostModalButton")).click();
            Thread.sleep(200);
        }
        testEnd();
    }
}