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

public class RegisterTest {
    private WebDriver driver;

    private String login;
    private String password6; // пароль 6 символов
    private String password5; // пароль 5 символов - некорректный
    private String firstName;
    StartPage startPage;
    UserPage userPage;
    UserCreationPage userCreationPage;

    @Before
    public void setUp() {
        // Инициализация WebDriver
        driver = getWebDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(BASE_URI);

        // Генерация данных для юзера
        login = generateRandomLogin();
        password6 = generateRandomPassword6();
        password5 = generateRandomPassword5();
        firstName = generateRandomFirstName();
        RestAssured.baseURI = BASE_URI;

        // Экземпляры классов page object
        startPage = new StartPage(driver);
        userPage = new UserPage(driver);
        userCreationPage = new UserCreationPage(driver);
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void checkSuccessfulRegistrationTest(){

        startPage.clickPersonalAccountButton();
        userPage.clickRegisterButton();
        userCreationPage.setInputData(login,password6, firstName);
        userPage.waitLoadingLoginPage();

        Assert.assertTrue(userPage.checkLoadingLoginPage());

        // Авторизация созданного юзера (для получения accessToken) + удаление созданного юзера
        loginUser(login, password6);
        deleteUser(accessToken);
    }

    @Test
    @DisplayName("Вывод ошибки для пароля менее 6 символов")
    public void checkBadPasswordErrorRegistrationTest(){

        startPage.clickPersonalAccountButton();
        userPage.clickRegisterButton();
        userCreationPage.setInputData(login, password5, firstName);
        userPage.waitLoadingLoginPage();

        Assert.assertTrue(userCreationPage.isVisibleIncorrectPassText());

        // Удаление юзера не производится, тк юзер НЕ создан с паролем короче 6 символов
    }

    @After
    public void tearDown() { driver.quit(); }

}
