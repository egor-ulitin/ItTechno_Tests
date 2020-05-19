package ItTechno.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SaleDescriptionsPage {
    private WebDriver webDriver;
    private WebDriverWait wait;

    public SaleDescriptionsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    @Step("Проверка: является текущая страница страницей подробного описания акции?")
    public void isSaleDescriptionPage()
    {
        String title = webDriver.getTitle();
        String expectedResult = "Акции и предложения";
        Assert.assertEquals(title, expectedResult);
    }
}
