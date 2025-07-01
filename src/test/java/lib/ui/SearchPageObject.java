package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject{
    protected static String
            SEARCH_INIT_ELEMENT_SKIP,  // Skip
            SEARCH_INIT_ELEMENT,  // Search Wikipedia
            SEARCH_INPUT,
            SEARCH_RESULT_ELEMENT, // Search
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_TITLE,
            SEARCH_DESCRIPTION ,
            SEARCH_DESCRIPTION_AND_TITLE_BY_SUBSTRING_TPL;

    public SearchPageObject(RemoteWebDriver driver){
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getTitleAndDescriptionElement(String substring){
        return SEARCH_DESCRIPTION_AND_TITLE_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {

        if (driver instanceof AppiumDriver) {
            this.waitForElementPresent(SEARCH_INIT_ELEMENT_SKIP, "Не найдена кнопка 'Skip'");
            this.waitForElementAndClick(SEARCH_INIT_ELEMENT_SKIP, "Не найдена кнопка 'Skip' для клика", 5);
        }
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Не найден начальный поисковый элемент");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Не найден начальный элемент поиска для клика", 5);
        this.waitForElementPresent(SEARCH_INPUT, "Не найден элемент строки поиска для ввода поискового запроса");
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Не найдена кнопка отмены поиска!", 15);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Кнопка отмены поиска всё еще присутствует!", 15);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Не найдена кнопка отмены поиска для клика", 15);
    }

    public void typeSearchLine(String search_line ){
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line,"Не найден элемент поиска для ввода поискового запроса", 15);
    }

    public void clearSearchLine() {
        this.waitForElementAndClear(SEARCH_INPUT, "Не найдено поле поиска", 5);
    }

    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Не найден результат поиска: '" + substring + "'");
    }

    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Не найден результат поиска для клика с подстрокой!" + substring, 10);
    }

    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Не найдены статьи в поисковом запросе ", 15);

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT,"Не найден элемент 'No results'", 15);
    }

    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "Не найдено результатов поиска");
    }

    public String getTextSearchInput(){
        WebElement title_element = this.waitForElementPresent(SEARCH_INPUT, "Не найден элемент строки поиска для ввода поискового запроса");;
        return title_element.getAttribute("text");
    }

    public int  waitForElementByTitleAndDescription(String s_title, String s_description){
        String s_search_title = getTitleAndDescriptionElement(SEARCH_TITLE);
        String s_search_description = getTitleAndDescriptionElement(SEARCH_DESCRIPTION);

        return this.getAmountPairElementsWithText(s_search_title, s_search_description, s_title, s_description);
    }

    public boolean assertHasTextSearchTitle(String text){
        return assertHasTextSearch(SEARCH_RESULT_ELEMENT, text);
    }
}
