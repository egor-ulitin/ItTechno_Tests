package ItTechno;

import ItTechno.Pages.MainPage;
import ItTechno.WebDriverListener;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

public class WebDriverSettings {
    protected static ChromeDriver chromeDriver ;
    protected static FirefoxDriver firefoxDriver ;
    protected static EventFiringWebDriver webDriver;
    public static WebDriverListener listener;


    public static WebDriverWait wait;
    @BeforeMethod
    public void beforeMethod() {
        firefoxDriver = new FirefoxDriver();
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Егор\\IdeaProjects\\It-techno.by_Tests\\geckodriver.exe");
        webDriver = new EventFiringWebDriver(firefoxDriver);
        listener = new WebDriverListener();
        webDriver.register(listener);
        webDriver.manage().window().maximize();
    }
    @AfterMethod
    public void finish() {
        webDriver.close();
    }
}
