package selenium;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestDashboard {

    @Test
    public void test_list_computers_display() {

    }

    private static FirefoxDriver driver;
    WebElement element;

    @BeforeClass
    public static void openBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void test_add_computers_founds() {

        driver.get("http://localhost:8080/ComputerDatabaseMaven/dash");
        /*driver.findElement(By.xpath(".//*[@id='account']/a")).click();
        driver.findElement(By.id("log")).sendKeys("testuser_3");
        driver.findElement(By.id("pwd")).sendKeys("Test@123");*/
        driver.findElement(By.id("addComputer")).click();
        
        driver.findElement(By.id("computerName")).sendKeys("testuser");
        driver.findElement(By.id("introduced")).sendKeys("1991-02-12");
        driver.findElement(By.id("discontinued")).sendKeys("2000-02-12");
        driver.findElement(By.id("valid")).click();
        Assert.assertNotNull(driver.findElementByClassName("alert alert-success"));
    }
    
    /*
    @Test
    public void inValid_UserCredential() {
        System.out.println("Starting test " + new Object() {
        }.getClass().getEnclosingMethod().getName());
        driver.get("http://www.store.demoqa.com");
        driver.findElement(By.xpath(".//*[@id='account']/a")).click();
        driver.findElement(By.id("log")).sendKeys("testuser");
        driver.findElement(By.id("pwd")).sendKeys("Test@123");
        driver.findElement(By.id("login")).click();
        try {
            element = driver.findElement(By
                    .xpath(".//*[@id='account_logout']/a"));
        } catch (Exception e) {
        }
        Assert.assertNotNull(element);
        System.out.println("Ending test " + new Object() {
        }.getClass().getEnclosingMethod().getName());
    }*/

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }
}
