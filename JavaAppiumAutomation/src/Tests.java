import io.appium.java_client.AppiumDriver;
import org.junit.Test;
import org.openqa.selenium.By;

public class Tests extends TestFunctions
{
    private AppiumDriver driver;
/*

    @Test
    public void firstTest()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find wiki input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );


        waitForElementPresent(
                By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text ='Object-oriented programming language']"),
                "Cannot find topic name",
                15
        );


    @Test
    public  void  testCancelSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "couldn't clear field",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                 "Cannot find X to cancel search ",
                 5
         );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Found X to cancel search ",
                10
        );

    }





    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find wiki input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text ='Object-oriented programming language']"),
                "Cannot find wiki input",
                5
        );

        WebElement title_element = waitForElementPresent(
                By.id ("org.wikipedia:id/view_page_title_text"),
                "can't find title",
                15
        );

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "unexpected title",
                "Java (programming language)",
                article_title
        );
    }


    }
*/

    @Test
    public void testCompareSearchText()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );

        assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search…",
                "Element does not correspond expected",
                5
        );
    }
}
