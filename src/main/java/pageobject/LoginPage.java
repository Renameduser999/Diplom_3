package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    //Поля и локаторы:
    //поле для ввода почты
    private By inputEmail = By.xpath(".//label[contains(text(),'Email')]/parent::div/input");
    //поле для ввода пароля
    private By inputPass = By.xpath(".//label[contains(text(),'Пароль')]/parent::div/input");
    //кнопка Войти
    private By loginButton = By.xpath(".//button[contains(text(),'Войти')]");
    // кнопка Зарегистрироваться
    private By registerButton = By.xpath(".//a[contains(text(),'Зарегистрироваться')]");
    //надпись Вход
    private By loginText = By.xpath(".//main/div/h2");
    //кнопка Восстановить пароль
    private By passwordRecoveryButton = By.xpath(".//a[contains(text(),'Восстановить пароль')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Методы:

    @Step("клик по кнопке «Зарегистрироваться»")
    public void clickRegisterButton(){
        driver.findElement(registerButton).isEnabled();
        driver.findElement(registerButton).click();
    }

    @Step("проверка видимости страницы Входа")
    public boolean checkLoadingLoginPage() {
        return driver.findElement(loginText).isDisplayed();
    }

    @Step("заполнение поля 'Email'")
    public void setInputEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }

    @Step("заполнение поля 'Пароль'")
    public void setInputPassword(String password) {
        driver.findElement(inputPass).sendKeys(password);
    }

    @Step("проверить доступность и кликнуть кнопку «Войти»")
    public void clickLoginButton(){
        driver.findElement(loginButton).isEnabled();
        driver.findElement(loginButton).click();
    }

    @Step("заполнить поля и отправить форму авторизации")
    public void setInputData(String email, String password){
        setInputEmail(email);
        setInputPassword(password);
        clickLoginButton();
    }

    @Step("клик по кнопке «Восстановить пароль»")
    public void clickPasswordRecoveryButton(){
        driver.findElement(passwordRecoveryButton).isEnabled();
        driver.findElement(passwordRecoveryButton).click();
    }

    @Step("подождать загрузки страницы авторизации")
    public void waitLoadingLoginPage(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(loginText));
    }
}