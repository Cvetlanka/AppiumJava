package lib.ui.android;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {

    static {
        BTN_SAVED = "xpath://android.widget.FrameLayout[@content-desc='Saved']/android.view.ViewGroup/android.widget.TextView";
    }

    public AndroidNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}