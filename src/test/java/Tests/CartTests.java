package Tests;

import ItTechno.Pages.CartPage;
import ItTechno.Pages.CatalogPage;
import ItTechno.Pages.DetailedDescriptionProductPage;
import ItTechno.Pages.MainPage;
import ItTechno.WebDriverSettings;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class CartTests extends WebDriverSettings {

    //Тест, проверяющий добавление в корзину через каталог
    @Test
    public void addToCartViaCatalogTest() throws Exception {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openCite();
        CatalogPage catalogPage = mainPage.openCategoryOfProductInCatalog("AV", "AV ресиверы");
        catalogPage.addToCart(1);
        catalogPage.addToCart(2);
        catalogPage.productsAddedToTheCart("2");
    }
    //Тест, проверяющий добавление в корзину через подробное описание товара
    @Test
    public void addToCartViaProductDetailedDescriptionTest() throws Exception {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openCite();
        CatalogPage catalogPage = mainPage.openCategoryOfProductInCatalog("AV", "AV ресиверы");
        DetailedDescriptionProductPage detailedDescriptionProductPage = catalogPage.goToProductDetailedDescription(1);
        String productName = detailedDescriptionProductPage.returnProductName();
        CartPage cartPage = detailedDescriptionProductPage.buyProduct();
        cartPage.isThisProduct(productName);
    }

    //Тест, проверяющий функцию увеличение количества заказываемого товара
    @Test
    public void increanseNumberProductTest() throws Exception {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openCite();
        CatalogPage catalogPage = mainPage.openCategoryOfProductInCatalog("AV", "AV ресиверы");
        DetailedDescriptionProductPage detailedDescriptionProductPage = catalogPage.goToProductDetailedDescription(1);
        CartPage cartPage = detailedDescriptionProductPage.buyProduct();
        cartPage.increaseNumberProduct();
        cartPage.isTheTotalCostCalcutedCorrectly();
    }

    //Тест, проверяющий функцию уменьшения количества заказываемого товара
    @Test
    public void reduceNumberProductTest() throws Exception {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openCite();
        CatalogPage catalogPage = mainPage.openCategoryOfProductInCatalog("AV", "AV ресиверы");
        DetailedDescriptionProductPage detailedDescriptionProductPage = catalogPage.goToProductDetailedDescription(1);
        CartPage cartPage = detailedDescriptionProductPage.buyProduct();
        cartPage.increaseNumberProduct();
        cartPage.increaseNumberProduct();
        cartPage.increaseNumberProduct();
        cartPage.reduceNumberProduct();
        cartPage.isTheTotalCostCalcutedCorrectly();
    }

    //Тест, проверяющий корректность изображения корзины
    @Test
    public void correctlyDisplayedTest() throws Exception {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openCite();
        CatalogPage catalogPage =mainPage.openCategoryOfProductInCatalog("AV", "AV ресиверы");
        DetailedDescriptionProductPage detailedDescriptionProductPage = catalogPage.goToProductDetailedDescription(1);
        CartPage cartPage =  detailedDescriptionProductPage.buyProduct();
        cartPage.isItDisplayedCorrectly();
    }
//
//    @Test
//    public void cleanCartTest()
//    {
//        MainPage mainPage = new MainPage(webDriver);
//        mainPage.openCite();
//        CatalogPage catalogPage =mainPage.openCategoryOfProductInCatalog("AV", "AV ресиверы");
//        DetailedDescriptionProductPage detailedDescriptionProductPage = catalogPage.goToProductDetailedDescription(1);
//        CartPage cartPage =  detailedDescriptionProductPage.buyProduct();
//    }
}
