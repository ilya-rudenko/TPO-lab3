package usecase;

import org.example.Utils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.example.Utils.*;
import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationTest {

    @BeforeAll
    public static void prepareDrivers() {
        Utils.prepareDriver();
    }

    public static WebDriver doLogin(String username, String password) {
        WebDriver webDriver = Utils.getDriver();

        webDriver.get(Utils.BASE_URL);

        WebElement signInButton = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='root']/header/div/div/div/a"));
        signInButton.click();

        WebElement loginInput = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='root']/div/div/form/div[2]/div/input"));
        WebElement loginPassword = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='root']/div/div/form/div[3]/div/input"));
        WebElement authButton = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='root']/div/div/form/button"));
        loginInput.clear();
        loginPassword.clear();
        loginInput.sendKeys(username);
        loginPassword.sendKeys(password);
        authButton.click();

        return webDriver;
    }

    @Test
    void correctLoginTest() {

        WebDriver webDriver = AuthorizationTest.doLogin(CORRECT_LOGIN, CORRECT_PASSWORD);

        WebElement profileButton = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='root']/div/header/div/button[7]"));
        profileButton.click();

        WebElement settingsButton = Utils.getElementBySelector(webDriver, By.xpath(".//a[contains(@href, '/settings#profile')]"));
        settingsButton.click();

        WebElement username = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='simple-tabpanel-profile']/div/form/h6"));

        assertEquals("87QjD103nJT7jvoaALDHa3nRv6T2", username.getText());

        webDriver.quit();

    }

    @Test
    void logoutTest() {
        WebDriver webDriver = AuthorizationTest.doLogin(CORRECT_LOGIN, CORRECT_PASSWORD);

        WebElement profileButton = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='root']/div/header/div/button[7]"));
        profileButton.click();

        WebElement logoutButton = Utils.getElementBySelector(webDriver, By.xpath(".//li[@id='appbar-dropdown-logout']"));
        logoutButton.click();

        WebElement signInButton = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='root']/header/div/div/div/a"));
        signInButton.click();

        WebElement header = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='root']/div/div/form/p"));

        assertEquals("Sign in", header.getText());

        webDriver.quit();
    }

    @Test
    void wrongLoginTest() {
        WebDriver webDriver = AuthorizationTest.doLogin(CORRECT_LOGIN, WRONG_PASSWORD);

        WebElement errorForm = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='root']/div/div/form/div[2]/div[2]/div"));

        assertEquals("Error logging in", errorForm.getText());

        webDriver.quit();
    }
}

