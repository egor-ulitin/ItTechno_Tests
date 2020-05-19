package ItTechno.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class MainPage {

    private WebDriver webDriver;
    private WebDriverWait wait;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 10);
    }

    @FindBy(className = "catalog-btn")
    private WebElement openCatalogButton;

    @FindBy(xpath = "//div[@class ='date']/../div[@class = 'title']//a")
    private List<WebElement> lastNews ;

    @FindBy(xpath = "//div[@class ='last-news'][2]//div[@class = 'title']//a")
    private List<WebElement> lastSales;

    @FindBy(xpath = "//a[@class='kiki']")
    private List<WebElement> categoryInCatalogButtons;

    @FindBy(xpath = "//a[@class = 'ol-menu-second-stage']")
    private List<WebElement> subcategoriesButton;
    @FindBy(id = "enter")
    private WebElement searchField;

    @FindBy(id = "city_select_input")
    private WebElement searchCityField;

    @FindBy(id = "head_geo_select")
    private WebElement cityChangeButton;

    @FindBy(className = "mCSB_dragger_bar")
    private WebElement sliderInListCities;

    @FindBy(xpath = "//form[@id = 'searchfield_form']//button[@type = 'submit']")
    private WebElement searchIcon;

    @FindBy(xpath = "//a[@rel ='loginform']")
    private WebElement personalAccountButton;

    @FindBy(xpath = "//div[@class = 'mCSB_container']//a[@href = '#']")
    private List<WebElement> leftListCitiesAndDistrict;

    @FindBy(className = "selcitylink")
    private List<WebElement> mainListCities;

    @Step("Открытие сайта")
    public void openCite()
    {
        webDriver.get("http://it-techno.by/");
    }
    public CatalogPage productSearch(String productName)
    {
        searchField.sendKeys(productName);
        searchIcon.click();
        return new CatalogPage(webDriver);
    }

    @Step("Выбор другого города через поиск")
    public void changeCityThroughSearchField(String cityName) throws Exception {
        cityChangeButton.click();
        try {
            wait.until(ExpectedConditions.visibilityOf(searchCityField));
        }
        catch (Exception io)
        {
            throw new Exception("Окно выбора города не доступно");
        }
        searchCityField.sendKeys(cityName);
        searchCityField.sendKeys(Keys.ENTER);
    }

    @Step("Выбор другого города города через список городов в левой части окна")
    public void changeCityThroughListCities(String cityName) throws Exception {
        cityChangeButton.click();
        try {
            wait.until(ExpectedConditions.elementToBeClickable(leftListCitiesAndDistrict.get(0)));
        }
        catch (Exception io)
        {
            throw new Exception("Окно выбора города не доступно");
        }
        for (WebElement city: leftListCitiesAndDistrict)
        {
            if(cityName.equals(city.getText()))
            {
                city.click();
            }
        }
    }

    @Step("Выбор другого города города через список самых популярных городов")
    public MainPage changeCityThroughMainListCities(String cityName) throws Exception {
        cityChangeButton.click();
        try {
            wait.until(ExpectedConditions.elementToBeClickable(mainListCities.get(mainListCities.size() - 1)));
        }
        catch (Exception io)
        {
            throw new Exception("Окно выбора города не доступно");
        }
        for (WebElement city: mainListCities)
        {
            if(cityName.equals(city.getText()))
            {
                city.click();
                return new MainPage(webDriver);
            }
        }
        return new MainPage(webDriver);
    }


    @Step("Переход к указанной категории")
    public CatalogPage openCategoryOfProductInCatalog(String categoryName, String subcategoryName) throws Exception {
        openCatalogButton.click();
        try {
            wait.until(ExpectedConditions.elementToBeClickable((categoryInCatalogButtons.get(categoryInCatalogButtons.size() - 1))));
        }
        catch (Exception io)
        {
            throw new Exception("Каталог не был открыт");
        }
            for (WebElement category : categoryInCatalogButtons) {
                if (categoryName.equals(category.getText())) {
                    category.click();
                    wait.until(ExpectedConditions.visibilityOfAllElements(subcategoriesButton));
                    for (WebElement subcategory : subcategoriesButton) {
                        if (subcategoryName.equals(subcategory.getText())) {
                            subcategory.click();
                            return new CatalogPage(webDriver);
                        }
                    }
                }
            }
        return new CatalogPage(webDriver);
    }

    @Step("Переход на экран логина")
    public LoginPage goToPersonalAccount()
    {
        personalAccountButton.click();
        return new LoginPage(webDriver);
    }

    @Step("Переход к подробному описанию новости")
    public NewsPage goToDescriptionNews(int numberNewsOnMainPage)
    {
        wait.until(ExpectedConditions.elementToBeClickable(lastNews.get(numberNewsOnMainPage)));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(lastNews.get(numberNewsOnMainPage), 100, 1).click().build().perform();
        return new NewsPage(webDriver);
    }
    @Step("Переход к подробному описанию акции")
    public SaleDescriptionsPage goToDescriptionSale(int numberSaleOnMainPage)
    {
        wait.until(ExpectedConditions.elementToBeClickable(lastSales.get(numberSaleOnMainPage)));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(lastSales.get(numberSaleOnMainPage), 100, 1).click().build().perform();
        return new SaleDescriptionsPage(webDriver);
    }


    @Step("Проверка выбранного города")
    public void isThisCitySelected(String cityName)
    {
          wait.until(ExpectedConditions.visibilityOf(cityChangeButton));
          Assert.assertEquals(cityChangeButton.getText(), cityName);
    }
    @Step("Проверка корректности отображения экрана выбора города")
    public void isСitySelectionDisplayCorrect()
    {
        boolean check = false;
        if (mainListCities.size() == 22)
        {
            if(leftListCitiesAndDistrict.size() > 50){
                if(searchCityField.isDisplayed())
                {
                    check = true;
                }
            }
        }
        Assert.assertTrue(check);
    }
    public void menyCatalogOpen() {
        boolean check = false;
        openCatalogButton.click();
        if (categoryInCatalogButtons.size() > 0)
            check = true;
        Assert.assertTrue(check);
    }

}
