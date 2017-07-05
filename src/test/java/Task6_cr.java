import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by administrator on 30.06.17.
 */
public class Task6_cr {

    private WebDriver driver;
    String NameOfDuck, ProdName, validFromDate, validToDate, prefix;

    @Before
    public void start(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

    }
    @Test
    public void createNewItem(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).submit();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(titleIs("My Store"));

        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(calendar.HOUR_OF_DAY);
        int m = calendar.get(calendar.MINUTE);
        int s = calendar.get(calendar.SECOND);

        NameOfDuck = "Huarhe Uaralovich Ioshcarala";
        prefix = Integer.toString(h) + Integer.toString(m) + Integer.toString(s);
        ProdName = NameOfDuck + " " + prefix;

        int y = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH);
        int d = calendar.get(calendar.DAY_OF_MONTH);

        if(month < 10) {
            validFromDate = "0"+Integer.toString(month)+"-"+Integer.toString(d)+"-"+Integer.toString(y);
            validToDate = "0"+Integer.toString(month)+"-"+Integer.toString(d)+"-"+Integer.toString(y+2);
        } else {
            validFromDate = Integer.toString(month) + "-" + Integer.toString(d) + "-" + Integer.toString(y);
            validToDate = Integer.toString(month) + "-" + Integer.toString(d) + "-" + Integer.toString(y + 2);
        }
        if(d < 10) {
            validFromDate = Integer.toString(month)+"-0"+Integer.toString(d)+"-"+Integer.toString(y);
            validToDate = Integer.toString(month)+"-0"+Integer.toString(d)+"-"+Integer.toString(y+2);
        } else {
            validFromDate = Integer.toString(month)+"-"+Integer.toString(d)+"-"+Integer.toString(y);
            validToDate = Integer.toString(month)+"-"+Integer.toString(d)+"-"+Integer.toString(y+2);
        }

        driver.findElement(By.cssSelector("[href*=catalog]")).click();
        driver.findElement(By.linkText("Add New Product")).click();
        driver.findElement(By.name("status")).submit();
        driver.findElement(By.name("name[en]")).clear();
        driver.findElement(By.name("name[en]")).sendKeys(ProdName);
        driver.findElement(By.name("code")).sendKeys(prefix + Keys.TAB);
        driver.findElement(By.xpath("(//input[@name='categories[]'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='product_groups[]'])[3]")).click();
        driver.findElement(By.name("quantity")).sendKeys("1");
        driver.findElement(By.name("date_valid_from")).sendKeys(validFromDate);
        driver.findElement(By.name("date_valid_to")).sendKeys(validToDate);
        driver.findElement(By.name("sku")).sendKeys(prefix);
        driver.findElement(By.name("gtin")).sendKeys(prefix);
        driver.findElement(By.name("taric")).sendKeys(prefix);
        driver.findElement(By.name("weight")).sendKeys("1");
        driver.findElement(By.name("dim_x")).sendKeys("10");
        driver.findElement(By.name("dim_y")).sendKeys("10");
        driver.findElement(By.name("dim_z")).sendKeys("10");

        driver.findElement(By.linkText("Information")).click();
        new Select(driver.findElement(By.name("manufacturer_id"))).selectByVisibleText("ACME Corp.");
        driver.findElement(By.name("keywords")).sendKeys("Duck");
        driver.findElement(By.name("description[en]")).sendKeys(ProdName+" is georgeous!");
        driver.findElement(By.name("head_title[en]")).sendKeys(ProdName);
        driver.findElement(By.name("meta_description[en]")).sendKeys("767");
        driver.findElement(By.name("attributes[en]")).sendKeys("None");

        driver.findElement(By.linkText("Prices")).click();
        driver.findElement(By.name("purchase_price")).sendKeys("15");
        new Select(driver.findElement(By.name("purchase_price_currency_code"))).selectByVisibleText("Euros");
        driver.findElement(By.name("gross_prices[USD]")).sendKeys("20");
        driver.findElement(By.name("save")).click();

        Assert.assertTrue(driver.findElement(By.linkText(ProdName)).isDisplayed());

    }

    @After
    public void stop() {
        driver.quit();
    }

}
