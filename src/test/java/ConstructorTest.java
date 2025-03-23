import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.StartPage;

import java.util.concurrent.TimeUnit;

import static driver.WebDriverCreator.createWebDriver;
import static org.junit.Assert.assertEquals;
import static userdata.UserData.BASE_URI;

public class ConstructorTest {

    private WebDriver driver;
    StartPage startPage;

    @Before
    public void setUp() {
        // Инициализация WebDriver
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(BASE_URI);

        // Экземпляры классов page object
        startPage = new StartPage(driver);
    }

    @Test
    @DisplayName("Переход к разделам «Соусы»")
    public void jumpToSectionSauces(){

        startPage.clickSaucesButton();
        assertEquals("Не сработал переход к разделу «Соусы»", "Соусы", startPage.getButtonText());
    }

    @Test
    @DisplayName("Переход к разделам «Начинки»")
    public void jumpToSectionFillings(){

        startPage.clickFillingsButton();
        assertEquals("Не сработал переход к разделу Начинки", "Начинки", startPage.getButtonText());
    }

    @Test
    @DisplayName("Переход к разделам «Булки»")
    public void jumpToSectionBuns(){

        startPage.clickSaucesButton();
        startPage.clickBunsButton();
        assertEquals("Не сработал переход к разделу Булки", "Булки", startPage.getButtonText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
