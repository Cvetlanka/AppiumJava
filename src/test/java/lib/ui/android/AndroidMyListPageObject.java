package lib.ui.android;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListPageObject extends MyListPageObject {

    static{
        FOLDER_BY_NAME_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][contains(@text,'{SUBSTRING}')]";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{SUBSTRING}')]";
    }
    public AndroidMyListPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
