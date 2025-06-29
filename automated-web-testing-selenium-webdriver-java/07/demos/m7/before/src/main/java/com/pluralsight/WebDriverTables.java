package com.pluralsight;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverTables {

    public static void main(String args[]) {
        WebDriver driver = new ChromeDriver();
        driver.get("file:///Users/me/dev/workspace/WebDriverDemo/src/main/webapp/TableTest.html");

        //WebElement outerTable = driver.findElement(By.tagName("table"));
        //WebElement innerTable = outerTable.findElement(By.tagName("table"));
        //WebElement row = innerTable.findElements(By.tagName("td")).get(1);

        WebElement row = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/table/tbody/tr[2]/td"));
        System.out.println(row.getText());
    }
}
