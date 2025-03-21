package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverCreator {

    public static final BrowserName BROWSER_NAME = BrowserName.CHROME;

    // Enum для типов браузеров
    public enum BrowserName {
        CHROME,
        YANDEX
    }

    // Метод для получения WebDriver
    public static WebDriver getWebDriver(BrowserName browserName) {
        switch (browserName) {
            case CHROME:
                return startChrome();
            case YANDEX:
                return startYandexBrowser();
            default:
                throw new IllegalArgumentException("Не распознан браузер: " + browserName);
        }
    }

    // Метод для получения WebDriver по умолчанию (использует константу BROWSER_NAME)
    public static WebDriver getWebDriver() {
        return getWebDriver(BROWSER_NAME);
    }

    // Метод для CHROME
    private static WebDriver startChrome() {
        // путь к chromedriver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        // экземпляр ChromeOptions
        ChromeOptions options = new ChromeOptions();
        // новый экземпляр ChromeDriver
        return new ChromeDriver(options);
    }

    // Метод для YANDEX
    private static WebDriver startYandexBrowser() {
        // путь к yandexdriver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
        // экземпляр ChromeOptions
        ChromeOptions options = new ChromeOptions();
        // путь к исполняемому файлу Yandex Browser
        options.setBinary("/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
        // новый экземпляр ChromeDriver с настройками для Yandex Browser
        return new ChromeDriver(options);
    }
}