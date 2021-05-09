package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CommonUtils {
        public static WebDriver driver;
        public void openBrowser() throws IOException
        {
            String browserObj = readPropertyFile("browser");
            switch (browserObj)
            {

                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "Firefox":
                    WebDriverManager.chromedriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
        }
            }

        public void closeBrowser()
        {
            driver.quit();
        }
        public String readPropertyFile(String key) throws IOException {
            FileInputStream fis = new FileInputStream("src/main/resources/Properties/data.properties");
            Properties properties = new Properties();
            properties.load(fis);
            return properties.getProperty(key);
        }
        public void waitForTime(int waitTime){
            driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
        }
        public void waitForElement(WebElement element, int maxTime){
            WebDriverWait wait = new WebDriverWait(driver, maxTime);
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        public void mouseOver(WebElement element)
        {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
        }
        public BufferedReader readTextFile(String Path) throws FileNotFoundException
        {
            BufferedReader read = new BufferedReader(new FileReader(Path));
            return read;

        }
    public static boolean hasPopupAppeared() {
        try {
            if (driver.findElement(By.xpath("//*[@id=\"m\"]/div[2]/div[12]/div/div/dl/div/dd[2]/a")).isDisplayed() == true) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    }

