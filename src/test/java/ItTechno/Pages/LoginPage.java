package ItTechno.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {
    private WebDriver webDriver;
    private WebDriverWait wait;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    @Step("Проверяю является текущая страница экраном логина")
    public void isLoginPage()
    {
        String title = webDriver.getTitle();
        String expectedResult = "Регистрация";
        Assert.assertEquals(title, expectedResult);
    }
}
