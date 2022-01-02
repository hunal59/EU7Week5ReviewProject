package com.cybertek.tests;

import com.cybertek.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class DropdownTest {

    WebDriver driver; // declare our reference for the object

    WebDriverWait wait;

    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver("chrome"); // create the object
        driver.manage().window().maximize();
        // implicitly wait, this is going to be applied to whole test cases and elements
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }

    public void Test(){
        WebElement userInputBox = driver.findElement(By.id("ctl00_MainContent_username"));
        userInputBox.sendKeys("Tester");
        WebElement passwordBox = driver.findElement(By.id("ctl00_MainContent_password"));
        passwordBox.sendKeys("test"+ Keys.ENTER);

        WebElement orderLink = driver.findElement(By.linkText("Order"));
        orderLink.click();


        String expectedSelectedOption = "MyMoney";
        WebElement productDropdownElement = driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));
        Select productDropdown = new Select(productDropdownElement);
        String actualSelectedOption = productDropdown.getFirstSelectedOption().getText();
        Assert.assertEquals(actualSelectedOption,expectedSelectedOption,"First option selected is NOT as expected");
    }
}
