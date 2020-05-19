package Tests;
import ItTechno.WebDriverSettings;
import ItTechno.Pages.MainPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ChangeCityTest extends WebDriverSettings{

    @DataProvider(name = "citiesInMainList")
    public static Object[][] getCityInMainList() {
        return new Object[][]{ {"Екатеринбург"}, {"Саратов"}};
    }

    @DataProvider(name = "citiesInLeftList")
    public static Object[][] dataProviderMethod() {
        return new Object[][]{ {"Саратовская"}, {"Гродно"}};
    }

    //Проверка выбора города через список самых популярных горолов
    @Test(dataProvider = "citiesInMainList")
    private static void changeCityThroughMainListCitiesTest(String city) throws Exception {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openCite();
        mainPage = mainPage.changeCityThroughMainListCities(city);
        mainPage.isThisCitySelected(city);
    }
    //Проверка выбора города через поиск
    @Test(dataProvider = "citiesInLeftList")
    private static void changeCityThroughSearchFieldTest(String city) throws Exception {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openCite();
        mainPage.changeCityThroughSearchField(city);
        mainPage.isThisCitySelected(city);
    }
    //Проверка выбора города через прокручивающийся список
    @Test(dataProvider = "citiesInLeftList")
    private static void changeCityThroughListCitiesTest(String city) throws Exception {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openCite();
        mainPage.changeCityThroughListCities(city);
        mainPage.isThisCitySelected(city);
    }
    //Проверка корректности отображения экрана выбора города
    @Test
    private static void correctCitySelectedDisplayedTest() throws Exception {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openCite();
        mainPage.changeCityThroughListCities("Гродно");
        mainPage.isСitySelectionDisplayCorrect();
    }

}
