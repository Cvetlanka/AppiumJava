package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {

    private static final String MAIN_MENU = "css:#main-menu-input";
    private static final String MAIN_PAGE = "css:#p-navigation > li:nth-child(1)";

    static {
        BTN_SAVED = "css:#ca-watch";
    }

    public MWNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}