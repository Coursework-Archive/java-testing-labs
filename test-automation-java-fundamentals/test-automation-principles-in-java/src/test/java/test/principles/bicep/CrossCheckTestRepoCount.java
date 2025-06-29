package test.principles.bicep;

import io.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;

public class CrossCheckTestRepoCount {

    WebDriver driver;

    private static WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions().addArguments("start-fullscreen");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }

    @BeforeClass
    public void driverSetup(){
        driver = getChromeDriver();
    }

    @Test
    public void repoCountMatches(){

        // UI Layer
        driver.get("https://github.com/andrejs-ps/?tab=repositories");

        List<WebElement> repos = driver.findElements(By.xpath("//div[@id='user-repositories-list']//li"));
        int repoCount = repos.size();

        // API layer - cross-check
        RestAssured.get("https://api.github.com/users/andrejs-ps")
                .then()
                .assertThat()
                .body("public_repos", equalTo(repoCount));
    }

    @AfterClass(alwaysRun = true)
    public void cleanup(){
        driver.close();
    }
}
