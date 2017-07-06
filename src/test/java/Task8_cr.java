import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * Created by administrator on 05.07.17.
 */
public class Task8_cr {
    private WebDriver driver;
    int countryQuantity, linksQuantity;
    int randomIndex;
    WebElement countryRow;
    List<WebElement> countryRows, listLinks;
    String originalWindow, newWindow;
    Set<String> existingWindows;

    @Before
    public void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }


    @Test
    public void myCheckNewTabs() {

        driver.navigate().to("http://localhost/litecart/admin/");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        WebElement login = driver.findElement(By.name("login"));
        login.submit();
        driver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        countryRows = driver.findElements(By.cssSelector("[name=countries_form] .row"));
        countryQuantity=countryRows.size();
        countryRow=countryRows.get(randomIndex);
        countryRow.findElement(By.cssSelector("a")).click();
        listLinks = driver.findElements(By.cssSelector("form .fa-external-link"));
        linksQuantity = listLinks.size();
    }

    @After
    public void stop() {
        driver.get("http://localhost/litecart/admin/logout.php");
        driver.quit();
    }


}

