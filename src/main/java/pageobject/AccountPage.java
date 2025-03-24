package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {

    private WebDriver driver;

    //Поля и локаторы:
    //кнопка Профиль
    private By profileButton = By.xpath(".//a[contains(text(),'Профиль')]");
    //кнопка Выход
    private By exitButton = By.xpath(".//button[contains(text(),'Выход')]");
    //кнопка Конструктор
    private By constructorButton = By.xpath(".//p[contains(text(),'Конструктор')]");
    //логотип
    private By logo = By.xpath(".//div[contains(@class, 'header__logo')]");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    //Методы:

    @Step("кликнуть кнопку «Выход»")
    public void clickExitButton(){
        driver.findElement(exitButton).isEnabled();
        driver.findElement(exitButton).click();
    }

    @Step("ожидание загрузки страницы Профиля")
    public void waitLoadingProfilePage(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(profileButton));
    }

    @Step("проверка, отображения кнопки «Профиль»")
    public boolean checkLoadingProfilePage(){
        return driver.findElement(profileButton).isDisplayed();
    }

    @Step("клик кнопки «Конструктор»")
    public void clickConstructorButton(){
        driver.findElement(constructorButton).isEnabled();
        driver.findElement(constructorButton).click();
    }

    @Step("клик на лого «Stellar Burgers»")
    public void clickLogo(){
        driver.findElement(logo).isEnabled();
        driver.findElement(logo).click();
    }
}
