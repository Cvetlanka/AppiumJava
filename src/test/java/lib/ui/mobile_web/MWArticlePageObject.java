package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject  extends ArticlePageObject {

    static {
        TITLE = "css:#firstHeading";//""css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        BTN_SAVE_FOR_LIST = "css:#page-actions li#ca-watch button";
     //   BTN_ADD_TO_LIST = "id:org.wikipedia:id/snackbar_action";
     //   TEXT_INPUT_TO_LIST = "id:org.wikipedia:id/text_input";
     //   BTN_OK = "id:android:id/button1";
     //   SEARCH_CANCEL_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
     //   SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{SUBSTRING}')]";
     //   ITEM_LIST_BY_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][contains(@text,'{SUBSTRING}')]";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
