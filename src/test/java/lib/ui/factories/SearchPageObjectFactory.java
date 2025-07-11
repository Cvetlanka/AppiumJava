package lib.ui.factories;

import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.mobile_web.MWSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObjectFactory{

    public static SearchPageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().IsAndroid()){
            return new AndroidSearchPageObject(driver);
        }else{
            return new MWSearchPageObject(driver);
        }
    }
}
