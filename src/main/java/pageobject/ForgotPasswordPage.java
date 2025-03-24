package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ForgotPasswordPage {

    private WebDriver driver;

    //Поля и локаторы:
    //кнопка Войти
    private By loginButton = By.xpath(".//a[contains(text(),'Войти')]");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    //Методы:
    @Step("проверка доступность и клик по кнопке «Войти»")
    public void clickLoginButton(){
        driver.findElement(loginButton).isEnabled();
        driver.findElement(loginButton).click();
    }
}