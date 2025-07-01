package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AutorizationPageObject extends MainPageObject{
    public static final  String
        MAIN_MENU = "css:#main-menu-input",
        LOGIN_BUTTON = "css:#p-personal > li > a > span.minerva-icon.minerva-icon--logIn", //xpath://body/div/a[text()='Log in']",
        LOGIN_INPUT = "css:#wpName1",//""css:input[name='wpName']",
        PASSWORD_INPUT = "css:#wpPassword1",//""css:input[name='wpPassword']",
        SUBMIT_BUTTON = "css:#wpLoginAttempt";//""css:button#wpLoginAttempt";

    public AutorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void clickAuthButton(){
        this.waitForElementPresent(MAIN_MENU, "Не найдена кнопка меню", 5);
        this.waitForElementAndClick(MAIN_MENU, "Не найдена кнопка меню для клика", 5);

        this.waitForElementPresent(LOGIN_BUTTON, "Не найдена кнопка авторизации", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Не найдена кнопка авторизации для клика", 5);
    }

    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, "welcome2svetlana","Не найдено поле для ввода логина", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, "svetlana2welcome","Не найдено поле для ввода пароля",5);
    }

    public void submiitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Не найдена кнопка подтверждения ввода пароля/логина", 5);
    }
}
