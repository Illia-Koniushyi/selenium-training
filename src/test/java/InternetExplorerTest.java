import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.ie.InternetExplorerDriverService;

/**
 * Created by administrator on 12.06.17.
 */
public class InternetExplorerTest {
    WebDriver driver;

    @Before
    public void start() throws WebDriverException, IOException {
        InternetExplorerDriverManager.getInstance().arch64().setup();
        driver = new InternetExplorerDriver();
//        driver = new InternetExplorerDriverService();
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
    }

    @Test
    public void internetExplorer_test(){
        driver.navigate().to("http://localhost/litecart/admin/");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");

        WebElement login = driver.findElement(By.name("login"));
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
        login.submit();
    }

    @After
    public void stop (){

        driver.quit();
    }
}