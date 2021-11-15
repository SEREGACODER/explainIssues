
import lib.CoreTestCase;
import lib.UI.TestFunctions;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

public class Tests extends CoreTestCase {

    private TestFunctions TestFunctions;

    protected void setUp() throws Exception
    {
        super.setUp();

        TestFunctions = new TestFunctions(driver);
    }

    @Test
    public void testSearch() {
        TestFunctions.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find wiki input",
                5
        );

        TestFunctions.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );


        TestFunctions.waitForElementPresent(
                By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text ='Object-oriented programming language']"),
                "Cannot find topic name",
                15
        );
    }

    @Test
    public void testCancelSearch() {
        TestFunctions.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );
        TestFunctions.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );
        TestFunctions.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "couldn't clear field",
                5
        );

        TestFunctions.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search ",
                5
        );
        TestFunctions.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Found X to cancel search ",
                10
        );

    }

    @Test
    public void testCompareArticleTitle() {
        TestFunctions.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find wiki input",
                5
        );

        TestFunctions.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );
        TestFunctions.waitForElementAndClick(
                By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text ='Object-oriented programming language']"),
                "Cannot find wiki input",
                5
        );

        WebElement title_element = TestFunctions.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
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


    @Test
    public void testCompareSearchText() {
        TestFunctions.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );

        TestFunctions.assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search…",
                "Element does not correspond expected",
                5
        );
    }


    @Test
    public void testFindCancelSearch() {
        TestFunctions.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );

        TestFunctions.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "657676575765",
                "Cannot find search input",
                5
        );

        TestFunctions.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_empty_view"),
                "No search results has been found",
                10
        );

        TestFunctions.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_empty_image"),
                "No search results has been found",
                10
        );

        TestFunctions.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search ",
                5
        );

        TestFunctions.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search result did not disappeared",
                10
        );

    }

    @Test
    public void testFindCancelSearchByAbsence() // проверяет что разультат поиска есть опираясь на отсутствие елементов:
    //так как результат может отсутствовать по причине пустого поля поиска
    // и отсутствию результатов поиска
    {
        TestFunctions.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );

        TestFunctions.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "1488",
                "Cannot find search input",
                5
        );

        TestFunctions.waitForElementsNotPresent(
                By.id("org.wikipedia:id/search_empty_view"),
                By.id("org.wikipedia:id/search_empty_image"),
                "No search results has been found",
                10
        );

        TestFunctions.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search ",
                5
        );

        TestFunctions.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search result did not disappeared",
                10
        );

    }

    @Test
    public void testFindCancelSearchByListItem() //проверяет что разультат поиска есть опираясь на его присутствие
    {
        TestFunctions.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search input",
                5
        );

        TestFunctions.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "1488",
                "Cannot find search input",
                5
        );

        TestFunctions.waitForElementPresent(
                By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']"),
                "No search results",
                10
        );

        TestFunctions.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search ",
                5
        );

        TestFunctions.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search result did not disappeared",
                10
        );

    }

    /*@Test
    public void testCompareArticleText()
    {
        TestFunctions.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find wiki input",
                5
        );

        TestFunctions.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "1488",
                "Cannot find search input",
                5
        );

        List<WebElement> elements = driver.findElements(By.id("org.wikipedia:id/page_list_item_container"));
        assertThat(elements, containsString("1488"));

        TestFunctions.checkTextPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "1480",
                "kal",
                5
        );
    }*/

    @Test
    public void testSwipeArticle() {
        TestFunctions.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find wiki input",
                5
        );

        TestFunctions.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find search input",
                5
        );
        TestFunctions.waitForElementAndClick(
                By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_title'][@text ='Appium']"),
                "Cannot find wiki input",
                5
        );

        TestFunctions.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "can't find title",
                15
        );
        TestFunctions.swipingUpToFindElement(
                By.xpath("//*[@text = 'View page in browser']"),
                "errorrrrrrrrrrR"
        );
    }
    @Test
    public void testSaveFirstArticleToMyList() {
        TestFunctions.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find wiki input",
                5
        );

        TestFunctions.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        TestFunctions.waitForElementAndClick(
                By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text ='Object-oriented programming language']"),
                "Cannot find wiki input",
                5
        );

        TestFunctions.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "can't find title",
                15
        );

        TestFunctions.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "can not open options",
                5
        );

        TestFunctions.waitForMenuToRender(
                By.id("org.wikipedia:id/title"),
                "",
                15
        );

        TestFunctions.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "can not find Add to reading list",
                10
        );

        TestFunctions.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "can not find got it button",
                5
        );

        TestFunctions.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "can not find text field",
                2
        );

        TestFunctions.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "popka",
                "can not find text field",
                2
        );

        TestFunctions.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "can not find OK button",
                5
        );

        TestFunctions.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "can not find X button",
                5
        );

        TestFunctions.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "can not find 'MY LISTS' button",
                15
        );

        TestFunctions.waitForElementAndClick(
                By.xpath("//*[@text='popka']"),
                "can not find popka",
                5
        );

        TestFunctions.swipeElementToLeft(
                By.xpath("//*[@text='popka']"),
                "cannot find popka"
        );

        TestFunctions.waitForElementNotPresent(
                By.xpath("//*[@text='popka']"),
                "there is popka",
                5
        );


    }

    @Test
    public void testOfNonEmptySearchResult()
    {
        TestFunctions.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find wiki input",
                5
        );

        String search_input = "java";
        TestFunctions.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_input,
                "Cannot find search input",
                5
        );
        String search_list = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        TestFunctions.waitForElementPresent(
                By.xpath(search_list),
                "popa",
                15
        );
        int amount_of_search_results = TestFunctions.getAmountOfElements(By.xpath(search_list));
        System.out.println(amount_of_search_results);
        Assert.assertTrue(
                "kakasgkaaaaaaaaa",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        TestFunctions.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find wiki input",
                5
        );

        String search_input = "dfjknfhbbhv";
        TestFunctions.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_input,
                "Cannot find search input",
                5
        );
        String search_list = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String empty_result_label ="//*[@text='No results found']";
        TestFunctions.waitForElementPresent(
                    By.xpath(empty_result_label),
                    "kal"
            );

        TestFunctions.assertElementNotPresent(
                    By.xpath(search_list),
                    "govno"
            );

    }

    @Test
    public void testChangeScreenOrientation()
    {
        TestFunctions.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find wiki input",
                5
        );

        String search_input = "java";
        TestFunctions.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_input,
                "Cannot find search input",
                5
        );
        TestFunctions.waitForElementAndClick(
                By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text ='Object-oriented programming language']"),
                "Cannot find wiki input",
                15
        );
        String title_before_rotation = TestFunctions.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "can not find article title",
                15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = TestFunctions.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "can not find article title",
                15
        );

        Assert.assertEquals(
                "title changed after rotation",
                title_before_rotation,
                title_after_rotation
        );

        driver.rotate(ScreenOrientation.PORTRAIT);

        String title_after_second_rotation = TestFunctions.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "can not find article title",
                15
        );

        Assert.assertEquals(
                "title changed after rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testArticleSearchInBackground()
    {
        TestFunctions.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find wiki input",
                5
        );

        TestFunctions.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        TestFunctions.waitForElementPresent(
                By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text ='Object-oriented programming language']"),
                "Cannot find wiki input",
                5
        );

        driver.runAppInBackground(2);

        TestFunctions.waitForElementPresent(
                By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_container']//*[@text ='Object-oriented programming language']"),
                "Cannot find wiki input after backgrounding",
                5
        );
    }

}