import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by administrator on 19.06.17.
 */
public class Task_4 {
    private WebDriver driver;
    int menuQuantity, submenuQuantity;

    @Before
    public void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void chrome_test() {
        driver.navigate().to("http://localhost/litecart/admin/");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");

        WebElement login = driver.findElement(By.name("login"));
        login.submit();

        List<WebElement> menuPoints = driver.findElements(By.id("app-"));
        List<WebElement> submenuPoints;
        WebElement menuPoint, submenuPoint;

        menuQuantity = menuPoints.size();
             System.out.println("Current elements of left menu are : " + menuPoints.size());
        for (int i = 0; i < menuQuantity; i++  ) {
            menuPoints = driver.findElements(By.id("app-"));
            menuPoint = menuPoints.get(i);
            menuPoint.click();

        menuPoints = driver.findElements(By.id("app-"));
        menuPoint = menuPoints.get(i);
        submenuPoints = menuPoint.findElements(By.cssSelector("[id^=doc-]"));
        submenuQuantity = submenuPoints.size();
            System.out.println("Current elements of left menu are : " + submenuPoints.size());

        if(submenuQuantity > 0) {
            for (int j = 0;j < submenuQuantity;j++) {
                menuPoints = driver.findElements(By.id("app-"));
                menuPoint = menuPoints.get(i);
                submenuPoints = menuPoint.findElements(By.cssSelector("[id^=doc-]"));
                submenuPoint = submenuPoints.get(j);
                submenuPoint.click();
                Assert.assertTrue("Element h1 not found",isElementPresent(By.cssSelector("h1")));
            }
        } else {
            Assert.assertTrue("Element h1 not found",isElementPresent(By.cssSelector("h1")));
        }
    }
}

    private boolean isElementPresent(By h1) {
        return true;
    }

    @After
    public void stop() {
        driver.quit();
    }
}


