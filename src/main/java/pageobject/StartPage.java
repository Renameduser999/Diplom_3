package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StartPage {
    private WebDriver driver;

    //Поля и локаторы:
    //кнопка Личный кабинет
    private By personalAccountButton = By.xpath(".//p[contains(text(),'Личный Кабинет')]");
    //кнопка Войти в аккаунт
    private By loginAccountButton = By.xpath(".//button[contains(text(),'Войти в аккаунт')]");
    //Кнопка Оформить заказ
    private By createOrderButton = By.xpath(".//button[contains(text(),'Оформить заказ')]");
    //надпись Соберите бургер
    private By assembleBurgerText = By.xpath(".//h1[contains(text(),'Соберите бургер')]");
    //кнопка Булки
    private By bunsButton = By.xpath(".//span[contains(text(),'Булки')]/parent::div");
    //кнопка Соусы
    private By saucesButton = By.xpath(".//span[contains(text(),'Соусы')]/parent::div");
    //кнопка Начинки
    private By fillingsButton = By.xpath(".//span[contains(text(),'Начинки')]/parent::div");
    //активная вкладка Конструктора
    private By activeSection = By.xpath(".//div[contains(@class,'current')]/span");

    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    //Методы:

    @Step("кликнуть кнопку 'Личный кабинет'")
    public void clickPersonalAccountButton(){
        driver.findElement(personalAccountButton).isEnabled();
        driver.findElement(personalAccountButton).click();
    }

    @Step("кликнуть кнопку 'Войти в аккаунт'")
    public void clickLoginAccountButton(){
        driver.findElement(loginAccountButton).isEnabled();
        driver.findElement(loginAccountButton).click();
    }

    @Step("проверить, что кнопка 'Оформить заказ' отображается")
    public boolean isCreateOrderButtonVisible(){
        return driver.findElement(createOrderButton).isDisplayed();
    }

    @Step("проверить, что надпись 'Соберите бургер' отображается")
    public boolean isAssembleBurgerTextVisible(){
        return driver.findElement(assembleBurgerText).isDisplayed();
    }

    @Step("кликнуть кнопку 'Булки'")
    public void clickBunsButton(){
        driver.findElement(bunsButton).isEnabled();
        driver.findElement(bunsButton).click();
    }

    @Step("кликнуть кнопку 'Соусы'")
    public void clickSaucesButton(){
        driver.findElement(saucesButton).isEnabled();
        driver.findElement(saucesButton).click();
    }

    @Step("кликнуть кнопку 'Начинки'")
    public void clickFillingsButton(){
        driver.findElement(fillingsButton).isEnabled();
        driver.findElement(fillingsButton).click();
    }

    @Step("получить текст кнопки - активной вкладки Конструктора")
    public String getButtonText(){
        return driver.findElement(activeSection).getText();
    }

    @Step("подождать загрузки Главной страницы")
    public void waitLoadingMainPage(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(assembleBurgerText));
    }

    @Step("подождать успешной авторизации")
    public void waitAuthorization (){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
    }
}