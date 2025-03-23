import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.AccountPage;
import pageobject.StartPage;
import pageobject.UserPage;
import java.util.concurrent.TimeUnit;

import static driver.WebDriverCreator.createWebDriver;
import static userdata.UserApi.createUser;
import static userdata.UserApi.deleteUser;
import static userdata.UserData.*;
import static userdata.UserData.BASE_URI;
import static userdata.UserApi.accessToken;

public class LoginPageTest {
    private WebDriver driver;

    private String login;
    private String password6; // пароль 6 символов
    private String firstName;
    StartPage startPage;
    UserPage userPage;
    AccountPage accountPage;

    @Before
    public void setUp() {
        // Инициализация WebDriver
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(BASE_URI);

        // Создание юзера и получение его accessToken
        login = generateRandomLogin();
        password6 = generateRandomPassword6();
        firstName = generateRandomFirstName();
        RestAssured.baseURI = BASE_URI;
        createUser(login, password6, firstName);

        // Экземпляры классов page object
        startPage = new StartPage(driver);
        userPage = new UserPage(driver);
        accountPage = new AccountPage(driver);

        // Авторизация перед тестом
        startPage.clickLoginAccountButton();
        userPage.setInputData(login, password6);
        startPage.waitAuthorization();
    }

    @Test
    @DisplayName("Переход по клику «Личный кабинет»")
    public void followPersonalAccountButtonTest() {

        startPage.clickPersonalAccountButton();
        accountPage.waitLoadingProfilePage();

        Assert.assertTrue(accountPage.checkLoadingProfilePage());
    }

    @Test
    @DisplayName("переход по клику на «Конструктор» ")
    public void followConstructorButtonTest(){

        startPage.clickPersonalAccountButton();
        accountPage.waitLoadingProfilePage();
        accountPage.clickConstructorButton();
        startPage.waitLoadingMainPage();

        Assert.assertTrue(startPage.isAssembleBurgerTextVisible());
    }

    @Test
    @DisplayName("переход по клику на лого «Stellar Burgers»")
    public void followLogoTest(){

        startPage.clickPersonalAccountButton();
        accountPage.waitLoadingProfilePage();
        accountPage.clickLogo();
        startPage.waitLoadingMainPage();

        Assert.assertTrue(startPage.isAssembleBurgerTextVisible());
    }

    @Test
    @DisplayName("выход по кнопке «Выйти» в личном кабинете")
    public void followExitButtonTest(){

        startPage.clickPersonalAccountButton();
        accountPage.waitLoadingProfilePage();
        accountPage.clickExitButton();
        userPage.waitLoadingLoginPage();

        Assert.assertTrue(userPage.checkLoadingLoginPage());
    }

    @After
    public void tearDown() {
        driver.quit();
        deleteUser(accessToken);
    }
}
