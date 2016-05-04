package selenium;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

//driver.findElement(By.xpath(".//*[@id='account']/a")).click();
public class TestDashboard {

    private static FirefoxDriver driver;
    WebElement element;

    @BeforeClass
    public static void openBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test
    public void test_add_computer() {
        //Given
        driver.get("http://localhost:8080/ComputerDatabaseMaven/dash");
        driver.findElement(By.id("addComputer")).click();   
        driver.findElement(By.id("computerName")).sendKeys("testuser");
        driver.findElement(By.id("introduced")).sendKeys("1991-02-12");
        driver.findElement(By.id("discontinued")).sendKeys("2000-02-12");
        
        //When
        driver.findElement(By.id("valid")).click();
       
        //Then
        assertNotNull(driver.findElement(By.id("compuAdded")));
    }
    
    @Test
    public void test_add_invalid_computer() {
        //Given
        driver.get("http://localhost:8080/ComputerDatabaseMaven/dash");
        //driver.findElement(By.xpath(".//*[@id='account']/a")).click();
        driver.findElement(By.id("addComputer")).click();
        
        driver.findElement(By.id("computerName")).sendKeys("");
        driver.findElement(By.id("introduced")).sendKeys("1991-02-12");
        driver.findElement(By.id("discontinued")).sendKeys("2000-02-12");
        driver.findElement(By.id("valid")).click();
       

        try {
            //When
            driver.findElement(By.id("compuAdded"));
            fail("The computer should not be added !");
        } catch (NoSuchElementException e) {
            //Then
            String errorMessage = "Unable to locate element: {\"method\":\"id\",\"selector\":\"compuAdded\"}";
            assertTrue(e.getMessage().contains(errorMessage));
        }
    }
    
    @Test
    public void test_list_computers_display() {
        //When
        driver.get("http://localhost:8080/ComputerDatabaseMaven/dash");
        
        //Then
        assertNotNull(driver.findElement(By.id("homeTitle")));
        assertNotNull(driver.findElement(By.id("results")));
    } 


    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }
}
