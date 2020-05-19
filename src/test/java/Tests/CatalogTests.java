package Tests;

import ItTechno.Pages.CatalogPage;
import ItTechno.Pages.MainPage;
import ItTechno.WebDriverSettings;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.reporters.jq.Main;

import java.io.IOException;

public class CatalogTests extends WebDriverSettings {

    @DataProvider(name = "wordForSearch")
    public static Object[][] getWordForSearch() {
        return new Object[][]{ {"камера"}, {"dect"}};
    }
    @DataProvider(name = "Category and subcategory")
    public static Object[][] getCategoryAndSubcategory() {
        return new Object[][]{ {"AV", "AV ресиверы"}, {"DECT", "DECT телефоны"}};
    }

    //проверка поиска продукта
    @Test(dataProvider = "wordForSearch")
    private static void searchProductsTest(String wordForSearch)
    {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openCite();
        CatalogPage catalogPage = mainPage.productSearch(wordForSearch);
        catalogPage.doesListOfProductsSatisfyTheSearch(wordForSearch);
    }
    //проверка открытия каталога
    @Test(dataProvider = "Category and subcategory")
    private static void openCatalogTest(String category, String subcategory) throws TimeoutException
    {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openCite();
        CatalogPage catalogPage = mainPage.openCategoryOfProductInCatalog(category, subcategory);
        catalogPage.isItDisplayCorrectly();
    }
}
