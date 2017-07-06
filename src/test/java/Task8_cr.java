import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


/**
 * Created by administrator on 05.07.17.
 */
public class Task8_cr {
    private WebDriver driver;
    private WebDriverWait wait;
    int  linksQuantity;
    List<WebElement>  listLinks;
    String originalWindow, newWindow;
    Set<String> existingWindows;

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles=driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size()>0 ? handles.iterator().next():null;
            }
        };
    }

    @Before
    public void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @Test
    public void myCheckNewTabs() {

        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).submit();

        driver.get("http://localhost/litecart/admin/?app=countries&doc=edit_country");
        listLinks = driver.findElements(By.cssSelector("form .fa-external-link"));
        linksQuantity = listLinks.size();
        System.out.println(linksQuantity);
        for (int i=0; i<linksQuantity; i++) {
            originalWindow=driver.getWindowHandle();
            existingWindows=driver.getWindowHandles();
            listLinks.get(i).click();
            newWindow = wait.until(anyWindowOtherThan(existingWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(originalWindow);
        }


    }

    @After
    public void stop() {
        driver.get("http://localhost/litecart/admin/logout.php");
        driver.quit();
    }


}

