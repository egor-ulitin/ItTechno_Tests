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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CatalogPage {

    private WebDriver webDriver;
    private WebDriverWait wait;

    public CatalogPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }

    @FindBy(className = "pcomm")
    private List<WebElement> addToCartButton;

    @FindBy(xpath = "//div[@class = 'smallparamscont catalog-rewrite-item']//div[@class = 'pic']")
    private List<WebElement> productPhoto;

    @FindBy(xpath = "//div[@style ='height:72px;text-align:left;word-wrap: break-word;overflow:hidden;margin:0 0 5px;']")
    private List<WebElement> productsName;

    @FindBy(id = "mycart")
    private WebElement myCartButton;

    @FindBy(id ="itemsCount")
    private WebElement countProductsInCart;

    @FindBy(id = "old-price-0567145")
    private List<WebElement> pricesProduct;

    @FindBy(xpath = "//div[@class = 'itemscontainer big']//Img[@src = '']")
    private List<WebElement> unknownImages;

    @Step("Добавить товар в корзину")
    public void addToCart(int numberProductInCatalog) {
        wait.until(ExpectedConditions.visibilityOfAllElements(addToCartButton));
        addToCartButton.get(numberProductInCatalog - 1).click();
    }
    @Step("Переход в корзину")
    public CartPage goToCart() {
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(myCartButton)));
        myCartButton.click();
        return new CartPage(webDriver);
    }

    @Step("Переход к детальному описанию товара")
    public DetailedDescriptionProductPage goToProductDetailedDescription(int numberProductInCatalog) {
        wait.until(ExpectedConditions.visibilityOfAllElements(productPhoto));
        productPhoto.get(numberProductInCatalog).click();
        return new DetailedDescriptionProductPage(webDriver);
    }

    @Step("Проверка на корректность результатов выдачи поискового запроса")
    public void doesListOfProductsSatisfyTheSearch(String wordByCheck) {
        boolean contains = true;
        for (WebElement temp : productsName) {
            System.out.println(temp.getText());
            if (!temp.getText().toLowerCase().contains(wordByCheck.toLowerCase())) {
                contains = false;
            }
        }
        Assert.assertTrue(contains, "Удовлетворяет ли показанные продукты поиску");
    }

    @Step("Проверка на корректность отображения каталога")
    public void isItDisplayCorrectly() {
        boolean check = false;
        if (addToCartButton.size() == productsName.size()) {
            if (addToCartButton.size() == productPhoto.size())
                if (addToCartButton.size() == pricesProduct.size())
                    if (unknownImages.size() == 0)
                        check = true;
        }
        Assert.assertTrue(check);
    }
    @Step("Проверка: добавился ли товар в корзину")
    public void productsAddedToTheCart(String numberAddedProducts)
    {
        Assert.assertEquals(countProductsInCart.getText(), numberAddedProducts);
    }

}
