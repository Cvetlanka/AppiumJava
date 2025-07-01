package lib.ui.mobile_web;

import lib.ui.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject {

    static{
        FOLDER_BY_NAME_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][contains(@text,'{SUBSTRING}')]";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][contains(@text,'{SUBSTRING}')]";
    }
    public MWMyListPageObject(RemoteWebDriver driver){
        super(driver);
    }
}