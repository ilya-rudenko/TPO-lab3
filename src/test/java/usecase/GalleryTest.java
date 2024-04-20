package usecase;

import org.example.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Arrays;

import static org.example.Utils.CORRECT_LOGIN;
import static org.example.Utils.CORRECT_PASSWORD;
import static org.junit.jupiter.api.Assertions.*;

public class GalleryTest {
    @BeforeAll
    public static void prepareDrivers() {
        Utils.prepareDriver();
    }

    @Test
    void createAlbumTest() {

        WebDriver webDriver = AuthorizationTest.doLogin(CORRECT_LOGIN, CORRECT_PASSWORD);

        WebElement createAlbumButton = Utils.getElementBySelector(webDriver, By.xpath(".//main[@id='main']/div/div[2]/div/div/div/div/div/div/div/div/div/span"));

        createAlbumButton.click();

        WebElement albumTitle = Utils.getElementBySelector(webDriver, By.xpath(".//h2[@id='mui-33']/p"));

        assertEquals("Albums", albumTitle.getText());

        WebElement closeButton = Utils.getElementBySelector(webDriver, By.xpath(".//button[contains(.,'Cancel')]"));

        closeButton.click();
        webDriver.quit();
    }

    @Test
    void openNavbarTest() {
        WebDriver webDriver = AuthorizationTest.doLogin(CORRECT_LOGIN, CORRECT_PASSWORD);

        WebElement navbar = Utils.getElementBySelector(webDriver, By.cssSelector("#appbar-drawer-toggle > .MuiSvgIcon-root"));
        navbar.click();

        WebElement title = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='album-menu']/div/div/div/div/h3"));

        assertEquals("My Bucket", title.getText());

        WebElement caption = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='album-menu']/div/div/div/span"));

        assertEquals("No images", caption.getText());
    }

    @Test
    void openTrashTest() {
        WebDriver webDriver = AuthorizationTest.doLogin(CORRECT_LOGIN, CORRECT_PASSWORD);

        WebElement navbar = Utils.getElementBySelector(webDriver, By.cssSelector("#appbar-drawer-toggle > .MuiSvgIcon-root"));
        navbar.click();

        WebElement trash = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='album-menu']/div[3]/ul/li[4]/div/div[2]/span"));

        trash.click();

        WebElement caption = Utils.getElementBySelector(webDriver, By.xpath(".//main[@id='main']/div/p"));

        assertEquals("Your Trash is Empty", caption.getText());
    }

    @Test
    void selectTest() {
        WebDriver webDriver = AuthorizationTest.doLogin(CORRECT_LOGIN, CORRECT_PASSWORD);
        WebElement selectButton = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='root']/div/header/div/button"));
        selectButton.click();

        WebElement cancelSelection = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='root']/div/header/div/div/button"));

        assertEquals("0 Selected", cancelSelection.getText());

        cancelSelection.click();
    }

    @Test
    void sizeInputTest() {
        WebDriver webDriver = AuthorizationTest.doLogin(CORRECT_LOGIN, CORRECT_PASSWORD);


        WebElement inputSwitch = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='zoom-in-out']/div/span/span[2]"));

        assertEquals("0%;", inputSwitch.getAttribute("style").split(" ")[3]);

        WebElement slider = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='zoom-in-out']/div/span/span[3]"));

        Actions action = new Actions(webDriver);
        action.clickAndHold(slider).moveByOffset(200, 0).perform();

        inputSwitch = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='zoom-in-out']/div/span/span[2]"));
        assertEquals("100%;", inputSwitch.getAttribute("style").split(" ")[3]);

        slider = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='zoom-in-out']/div/span/span[3]"));
        action = new Actions(webDriver);
        action.clickAndHold(slider).moveByOffset(-200, 0).perform();

        inputSwitch = Utils.getElementBySelector(webDriver, By.xpath(".//div[@id='zoom-in-out']/div/span/span[2]"));
        assertEquals("0%;", inputSwitch.getAttribute("style").split(" ")[3]);

        webDriver.quit();
    }
}
