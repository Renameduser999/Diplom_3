import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.*;

import java.util.concurrent.TimeUnit;

import static driver.WebDriverCreator.getWebDriver;
import static userdata.UserData.*;
import static userdata.UserApi.*;

public class UserTest {

    private WebDriver driver;

    private String login;
    private String password6; // пароль 6 символов
    private String firstName;
    StartPage startPage;
    UserPage userPage;
    AccountPage accountPage;
    UserCreationPage userCreationPage;
    ForgotPasswordPage forgotPasswordPage;

    @Before
    public void setUp() {
        // Инициализация WebDriver
        driver = getWebDriver();
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
        userCreationPage = new UserCreationPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    public void loginWithLoginAccountButtonTest(){

        startPage.clickLoginAccountButton();
        userPage.setInputData(login, password6);
        startPage.waitAuthorization();

        Assert.assertTrue(startPage.isCreateOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginWithPersonalAccountButtonTest(){

        startPage.clickPersonalAccountButton();
        userPage.setInputData(login, password6);
        startPage.waitAuthorization();

        Assert.assertTrue(startPage.isCreateOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через «Войти» на странице регистрации")
    public void loginWithRegistrationFormLoginButtonTest(){

        startPage.clickPersonalAccountButton();
        userPage.clickRegisterButton();
        userCreationPage.clickLoginButton();
        userPage.setInputData(login, password6);
        startPage.waitAuthorization();

        Assert.assertTrue(startPage.isCreateOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через «Войти» на странице восстановления пароля")
    public void loginWithPasswordRecoveryButtonTest(){

        startPage.clickPersonalAccountButton();
        userPage.clickPasswordRecoveryButton();
        forgotPasswordPage.clickLoginButton();
        userPage.setInputData(login, password6);
        startPage.waitAuthorization();

        Assert.assertTrue(startPage.isCreateOrderButtonVisible());
    }


    @After
    public void tearDown() {
        driver.quit();
        deleteUser(accessToken);
    }
}
