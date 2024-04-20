import org.example.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTest {

    @BeforeAll
    static void prepareDrivers() {
        Utils.prepareDriver();
    }

    @Test
    void testDriver() {
        this.executeWithCapabilities(Utils.getDriver());
    }

    private void executeWithCapabilities(WebDriver driver) {
        driver.get(Utils.BASE_URL);
        String title = driver.getTitle();
        assertEquals("Photobucket | Photo Storage", title);
        driver.quit();
    }
}

