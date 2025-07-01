package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
    SEARCH_INIT_ELEMENT ="css:button#searchIcon";  // Search Wikipedia
    SEARCH_INPUT ="css:form>input[type='search']";
    SEARCH_RESULT_ELEMENT = "css:ul.mw-mf-page-list>li.page-summary";// Search
    SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
    SEARCH_CANCEL_BUTTON ="css:button.cancel";
    SEARCH_RESULT_BY_SUBSTRING_TPL ="css:ul.mw-mf-page-list>li.page-summary[title='{SUBSTRING}']";

    //SEARCH_TITLE ="page_list_item_title";
    //SEARCH_DESCRIPTION ="page_list_item_description";
    SEARCH_DESCRIPTION_AND_TITLE_BY_SUBSTRING_TPL ="id:org.wikipedia:id/{SUBSTRING}";
}

    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
