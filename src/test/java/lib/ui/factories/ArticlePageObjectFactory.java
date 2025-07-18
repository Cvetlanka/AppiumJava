package lib.ui.factories;

import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.mobile_web.MWArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ArticlePageObjectFactory {

    public static ArticlePageObject get(RemoteWebDriver driver){

        if (Platform.getInstance().IsAndroid()){
            return new AndroidArticlePageObject(driver);
        }else{
            return new MWArticlePageObject(driver);
        }
    }
}
