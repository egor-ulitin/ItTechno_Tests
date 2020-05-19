package ItTechno.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class NewsPage {

    private WebDriver webDriver;
    private WebDriverWait wait;

    public NewsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }

    @Step("Проверка: является текущая страница страницей подробного описания новости?")
    public void isNewsPage()
    {
        String title = webDriver.getTitle();
        String expectedResult = "Новости компании";
        Assert.assertEquals(title, expectedResult);
    }
}
