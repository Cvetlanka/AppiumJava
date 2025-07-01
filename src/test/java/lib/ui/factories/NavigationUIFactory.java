package lib.ui.factories;

import lib.Platform;
import lib.ui.NavigationUI;
import lib.ui.android.AndroidNavigationUI;
import lib.ui.mobile_web.MWNavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIFactory {

    public static NavigationUI get(RemoteWebDriver driver){
        if (Platform.getInstance().IsAndroid()) {
            return new AndroidNavigationUI(driver);
        } else if (Platform.getInstance().IsMW()){
            return new MWNavigationUI(driver);
        } else {
            System.out.println("Неизвестная платформа " + Platform.getInstance());
            return new AndroidNavigationUI(driver);
        }
    }
}
