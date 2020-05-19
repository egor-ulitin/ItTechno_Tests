package Tests;

import ItTechno.Pages.*;
import ItTechno.WebDriverSettings;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends WebDriverSettings {


    //Тест перешел ли страница на экран логина
    @Test
    private void LoginScreenTransitionTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openCite();
        LoginPage loginPage = mainPage.goToPersonalAccount();
        loginPage.isLoginPage();
        webDriver.close();
    }



}

