package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{
    protected static String
            BTN_SAVED,
            MAIN_MENU,
            MAIN_PAGE;

    public NavigationUI(RemoteWebDriver driver){ super(driver); }

    public void clickMyList(){

        if (Platform.getInstance().IsMW()){
            this.tryClickElementWithFewAttempts(BTN_SAVED, "Не найдена кнопка сохраненного списка", 5);
        }
        else {this.waitForElementAndClick(BTN_SAVED,"Не найдена кнопка сохраненных списков 'Saved'", 7);}
    }

    public void clickMainMenu(){
        if (Platform.getInstance().IsMW()){
            this.tryClickElementWithFewAttempts(MAIN_MENU, "Не найдена кнопка сохраненного списка", 5);
        }
        return;
    }

    public void clickMainPage() {
        if (Platform.getInstance().IsMW()) {
            this.tryClickElementWithFewAttempts(MAIN_PAGE, "Не найдена кнопка сохраненного списка", 5);
        }
        return;
    }
}
