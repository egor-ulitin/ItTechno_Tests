package Tests;

import ItTechno.Pages.MainPage;
import ItTechno.Pages.NewsPage;
import ItTechno.Pages.SaleDescriptionsPage;
import ItTechno.WebDriverSettings;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewsAndSaleTests extends WebDriverSettings {

    //тест открытия информации о новости
    @Test
    public void openDescriptionNewsTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openCite();
        NewsPage newsPage = mainPage.goToDescriptionNews(1);
        newsPage.isNewsPage();

    }
    //Открытие информации о акции
    @Test
    public void openDescriptionSaleTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openCite();
        SaleDescriptionsPage saleDescriptionsPage = mainPage.goToDescriptionSale(1);
        saleDescriptionsPage.isSaleDescriptionPage();
    }
}
