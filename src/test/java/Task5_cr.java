import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

/**
 * Created by administrator on 20.06.17.
 */
public class Task5_cr {
    private WebDriver driver;
    WebElement product;
    String[] regularPrice1, regularPrice2, campaignPrice1, campaignPrice2;
    String productName1, productName2;
    float size1, size2;



    @Before
    public void start() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void FfTest() {
        driver.get("http://localhost/litecart/en/");
        product = driver.findElement(By.cssSelector("[id=campaign-products]"));
        productName1 = product.findElement(By.cssSelector(".name")).getText();
        regularPrice1 = new String[4];
        campaignPrice1 = new String[4];
        regularPrice1[0]= product.findElement(By.cssSelector(".regular-price")).getText();
        regularPrice1[1]= product.findElement(By.cssSelector(".regular-price")).getCssValue("font-weight");
        regularPrice1[2]= product.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration");
        regularPrice1[3]= product.findElement(By.cssSelector(".regular-price")).getCssValue("font-size");
        size1 = Float.parseFloat(regularPrice1[3].replaceAll("px",""));
        campaignPrice1[0]= product.findElement(By.cssSelector(".campaign-price")).getText();
        campaignPrice1[1]= product.findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight");
        campaignPrice1[2]= product.findElement(By.cssSelector(".campaign-price")).getCssValue("text-decoration");
        campaignPrice1[3]= product.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size");
        size2 = Float.parseFloat(campaignPrice1[3].replaceAll("px",""));

        Assert.assertTrue(size1<size2);

        product = driver.findElement(By.cssSelector("a.link"));
        product.click();

        product = driver.findElement(By.cssSelector("[id=box-product]"));
        productName2 = product.findElement(By.cssSelector("h1")).getText();

        Assert.assertTrue(productName1.compareTo(productName2)==0);

        regularPrice2 = new String[4];
        campaignPrice2 = new String[4];
        regularPrice2[0]= product.findElement(By.cssSelector(".regular-price")).getText();
        regularPrice2[1]= product.findElement(By.cssSelector(".regular-price")).getCssValue("font-weight");
        regularPrice2[2]= product.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration");
        regularPrice2[3]= product.findElement(By.cssSelector(".regular-price")).getCssValue("font-size");
        size1 = Float.parseFloat(regularPrice2[3].replaceAll("px",""));
        campaignPrice2[0]= product.findElement(By.cssSelector(".campaign-price")).getText();
        campaignPrice2[1]= product.findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight");
        campaignPrice2[2]= product.findElement(By.cssSelector(".campaign-price")).getCssValue("text-decoration");
        campaignPrice2[3]= product.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size");
        size2 = Float.parseFloat(campaignPrice2[3].replaceAll("px",""));

        Assert.assertTrue(size1<size2);

        for(int i = 0; i < 3; i++) {
            Assert.assertTrue(regularPrice1[i].compareTo(regularPrice2[i])==0);
            Assert.assertTrue(campaignPrice1[i].compareTo(campaignPrice2[i])==0);
        }
    }

    @After
    public void stop() {
        driver.quit();
    }

}