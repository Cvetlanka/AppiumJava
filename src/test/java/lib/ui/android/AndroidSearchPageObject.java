package lib.ui.android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT_SKIP = "id:org.wikipedia:id/fragment_onboarding_skip_button";   // Skip
        SEARCH_INIT_ELEMENT = "id:org.wikipedia:id/search_container";  // Search Wikipedia
        SEARCH_INPUT = "xpath://android.widget.EditText[@resource-id='org.wikipedia:id/search_src_text']";
        SEARCH_RESULT_ELEMENT = "id:org.wikipedia:id/page_list_item_title";// Search
        SEARCH_EMPTY_RESULT_ELEMENT = "id:org.wikipedia:id/results_text";
        SEARCH_CANCEL_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{SUBSTRING}')]";
//        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']";
        SEARCH_TITLE = "page_list_item_title";
        SEARCH_DESCRIPTION = "page_list_item_description";
        SEARCH_DESCRIPTION_AND_TITLE_BY_SUBSTRING_TPL = "id:org.wikipedia:id/{SUBSTRING}";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}

