package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class UiPluralsightTest {

    WebDriver driver;

    @BeforeMethod
    public void startUpBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("https://pluralsight.com");

        doBasicCheck();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        System.out.println("Closing down the browser");
        driver.close();
    }



    @Test(description = "No exception thrown by findElement considered a succsessful test")
    public void checkLoginButtonIsPresent(){
        driver.findElement(By.className("menu-a"));
    }


    /**
     * Do basic check that the page has loaded
     * Will fail
     */
    private void doBasicCheck() {
//        driver.findElement(By.className("horizontal--logo"));
        driver.findElement(By.className("ps-navigation-universal"));
    }

}
