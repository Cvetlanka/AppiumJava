package lib.ui.android;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
         TITLE = "xpath://*[@resource-id='pcs']//*[@class='android.view.View'][@index='0']//*[@class='android.view.View'][@index='0']"; //  "//*[@resource-id='pcs']//*[@class='android.view.View']//*[@instance='2']";
         FOOTER_ELEMENT = "xpath://*[@text='View article in browser']";
         BTN_SAVE_FOR_LIST = "id:org.wikipedia:id/page_save";
         BTN_ADD_TO_LIST = "id:org.wikipedia:id/snackbar_action";
         TEXT_INPUT_TO_LIST = "id:org.wikipedia:id/text_input";
         BTN_OK = "id:android:id/button1";
         SEARCH_CANCEL_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
         SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{SUBSTRING}')]";
         ITEM_LIST_BY_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][contains(@text,'{SUBSTRING}')]";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
