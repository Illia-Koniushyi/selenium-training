import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


/**
 * Created by administrator on 08.06.17.
 */
public class Task3_cr {
        WebDriver driver;

@Before
        public void start(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
}

@Test
        public void chrome_test(){
        driver.navigate().to("http://localhost/litecart/admin/");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");

        WebElement login = driver.findElement(By.name("login"));
        login.submit();
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

}

@After
        public void stop (){

                driver.quit();
        }
}