package ItTechno.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

public class CartPage {
    private WebDriver webDriver;
    private WebDriverWait wait;

    public CartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    @FindBy(xpath = "//span[@class='cart-item__price-value']")
    private WebElement priceProduct;

    @FindBy(className = "price-links")
    private WebElement totalCostItems;

    private  String totalAmmount = "price-ammount";
    @FindBy(id = "kluk")
    private WebElement numberProducts;
    @FindBy(id = "plus")
    private WebElement increaseNumberProductButton;
    @FindBy(id = "minus")
    private WebElement reduceNumberProductButton;
    @FindBy(xpath = "//span[@class = 't']")
    private WebElement productName;

    @FindBy(id = "fio_subscribe")
    private WebElement fioField;

    @FindBy(id = "email_subscribe")
    private WebElement emailField;

    @FindBy(id = "fone_subscribe")
    private WebElement phoneField;

    @FindBy(id = "kol_city")
    private WebElement cityField;



    @Step("Увеличение количества товаров")
    public void increaseNumberProduct() {
        wait.until(ExpectedConditions.visibilityOf(increaseNumberProductButton));
        increaseNumberProductButton.click();
    }
    @Step("Уменьшение количества товаров")
    public void reduceNumberProduct() {
        wait.until(ExpectedConditions.visibilityOf(reduceNumberProductButton));
        reduceNumberProductButton.click();
    }

    @Step("Проверка: в корзине находится указанный продукт?")
    public void isThisProduct(String product)
    {
        wait.until(ExpectedConditions.visibilityOf(productName));
        Assert.assertEquals(productName.getText().toLowerCase(),product.toLowerCase());
    }

    public int getPrice(String priceWithCurrencyText)
    {
        String []temp = priceWithCurrencyText.split(" ");
        return Integer.parseInt(temp[0]);
    }

    @Step("Проверка: правильно ли подсчитана общая стоимость товара?")
    public void isTheTotalCostCalcutedCorrectly()
    {
        int priceOneItem = getPrice(priceProduct.getText());
        int totalCost = getPrice(totalCostItems.getText());
        Assert.assertEquals(totalCost, priceOneItem * Integer.parseInt(numberProducts.getText()));
    }

    @Step("Проверка корректности отображения корзины")
    public void isItDisplayedCorrectly() throws IOException
    {

        boolean check = false;
        if (fioField.isDisplayed()) {
            if (cityField.isDisplayed())
                if (emailField.isDisplayed())
                    if (phoneField.isDisplayed())
                        if(!webDriver.findElements(By.className(totalAmmount)).isEmpty())
                        {
                            check = true;
                        }

        }
        Assert.assertTrue(check);
    }

}
