package lib.ui.factories;

import lib.Platform;
import lib.ui.MyListPageObject;
import lib.ui.android.AndroidMyListPageObject;
import lib.ui.mobile_web.MWMyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListPageObjectFactory {

    public static MyListPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().IsAndroid()){
            return new AndroidMyListPageObject(driver);
        } else if (Platform.getInstance().IsMW()){
            return new MWMyListPageObject(driver);
        } else {
            System.out.println("Неизвестная платформа " + Platform.getInstance());
            return new AndroidMyListPageObject(driver);
        }
    }
}
