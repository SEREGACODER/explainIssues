import com.gargoylesoftware.htmlunit.html.Keyboard;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.xerces.impl.xs.opti.DefaultElement;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.crypto.Data;
import java.net.URL;

public class TestFunctions {

    public AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","9");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/user/IdeaProjects/explainIssues/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub") , capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }



    //Метод проверки присутствия елемента
    public   WebElement waitForElementPresent(By by, String error_message, long timeoutIntSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutIntSeconds);
        wait.withMessage(error_message + "/n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    //Метод проверки присутствия елемента с таймаутом 5 сек
    public  WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by , error_message , 5);
    }


    //Метод для тапа по елементу
    public   WebElement waitForElementAndClick(By by, String error_message, long timeoutIntSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message , 5);
        element.click();
        return element;
    }

    //Метод для вставки текста и скрытия клавиатуры
    public   WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutIntSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        driver.hideKeyboard();
        return element;
    }

    //Метод для проверки отсутствия елемента
    public  boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    //Метод для отчистки поля
     public  WebElement waitForElementAndClear(By by, String error_message, long timeoutIntSeconds) {
         WebElement element = waitForElementPresent(by, error_message, 5);
         element.clear();
         return element;
     }

     //Метод для проверки соответствия текста
    public WebElement assertElementHasText(By by, String inputText, String error_message, long timeoutIntSeconds){

        WebElement title_element = waitForElementPresent(by, error_message, timeoutIntSeconds);

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "unexpected title",
                article_title,
                inputText
        );

         return title_element;
    }
}