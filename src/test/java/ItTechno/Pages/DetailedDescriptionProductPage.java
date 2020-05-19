package ItTechno.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetailedDescriptionProductPage {
    private WebDriver webDriver;
    private WebDriverWait wait;
    public DetailedDescriptionProductPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }
    @FindBy(xpath = "//span[@onclick = 'return pre_click(this);']")
    private WebElement buyProductButton;
    @FindBy(id = "reviews_c")
    private WebElement switchOnReviewsButton;
    @FindBy(id = "char_c")
    private WebElement switchOnCharacteristicsButton;
    @FindBy(xpath = "//div[@class= 'cte_w_info_block cte_w_price_block']//span[@class='price_leg']")
    private WebElement priceWithCurrencyText;
    @FindBy(xpath = "//h1[@itemprop = 'name']")
    private WebElement productName;

    public CartPage buyProduct()
    {
        wait.until(ExpectedConditions.elementToBeClickable(buyProductButton));
        buyProductButton.click();
        return new CartPage(webDriver);
    }

    @Step("Окрытие характеристики товара")
    public void switchOnCharacteristics() {
        wait.until(ExpectedConditions.visibilityOf(switchOnCharacteristicsButton));
        switchOnCharacteristicsButton.click();
    }
    @Step("Открытие ревью")
    public void switchOnReviews() {
        wait.until(ExpectedConditions.visibilityOf(switchOnReviewsButton));
        switchOnReviewsButton.click();
    }

    @Step("Получение цену товара")
    public int getPriceProduct()
    {
        wait.until(ExpectedConditions.visibilityOf(priceWithCurrencyText));
        wait.until(ExpectedConditions.elementToBeClickable(switchOnCharacteristicsButton));
        String []temp = priceWithCurrencyText.getText().split(" ");
        int sum = 0;
        int coef = 1;
        System.out.println(priceWithCurrencyText.getText());
        System.out.println(temp[0]);
        System.out.println(temp[1]);
        for(int i = temp[0].length() - 1; i >= 0; --i)
        {

            sum += temp[0].charAt(i) *coef;
            coef *= 10;
        }
        return sum;
    }

    public String returnProductName()
    {
        wait.until(ExpectedConditions.visibilityOf(productName));
        return productName.getText();
    }




}
