package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

//import static jdk.internal.org.jline.utils.Colors.s;

public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver){
        this.driver = driver;
    }

    public void assertElementPresent(String locator, String error_message){
        Assert.assertTrue(error_message,
                getAmountOfElements(locator) > 0
        );
    }

    public void assertElementNotPresent(String locator, String error_message){
        Assert.assertTrue(error_message,
                getAmountOfElements(locator) == 0
        );
    }

    public int getAmountOfElements(String locator){
        By by = this.getLocatorByString(locator);

        List elements = driver.findElements(by);
        return elements.size();
    }

    public void tryClickElementWithFewAttempts(String locator, String error_message, int amount_of_attempts){
        int current_attempts = 0;
        boolean need_more_attempts = true;

        while (need_more_attempts){
            try{
                this.waitForElementAndClick(locator, error_message, 1);
                need_more_attempts = false;
            } catch (Exception e){
                if (current_attempts > amount_of_attempts){
                    this.waitForElementAndClick(locator, error_message, 1);
                }
            }
            ++current_attempts;
        }
    }

    public boolean assertElementHasText(String locator, String expected_text, String error_message) {
        WebElement element = waitForElementPresent(locator,"Не найден элемент!'", 30 );

        if(Platform.getInstance().IsAndroid()) {
            if (!element.getAttribute("text").contains(expected_text)) {
                System.out.println(error_message);
                return false;
            }
        }
        else if(Platform.getInstance().IsMW()){
            if (!element.getText().contains(expected_text)) {
                System.out.println(error_message);
                return false;
            }
        }
        return true;
    }

    public String getTextElement(WebElement element){

        if(Platform.getInstance().IsAndroid()) {
            return element.getAttribute("text");
        }
        else if(Platform.getInstance().IsMW()){
            return element.getText();
        }
        return "";

    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 5);
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);

        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public void swipeUp(int timeOfSwipe){
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver) driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.5);
            int end_y = (int) (size.height * 0.1);

            action
                    .press(x, start_y)
                    .waitAction(timeOfSwipe)
                    .moveTo(x, end_y)
                    .release()
                    .perform();
        }else{
            System.out.println("Метод swipeUp() отсутствует для платформы " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void swipeUpQuick(){
        swipeUp(200);
    }

    public void scrollWebPageUp(){
        if(Platform.getInstance().IsMW()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0, 250)");
        }
        else{
            System.out.println("Метод scrollWebPageUp() отсутствует для платформы " + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, String error_message, int max_swipes){
        int already_swiped = 0;
        By by = this.getLocatorByString(locator);
        WebElement element = this.waitForElementPresent(locator, error_message);

        while( driver.findElements(by).size() == 0 ){
            scrollWebPageUp();
            ++already_swiped;

            if( already_swiped > max_swipes ){
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }

    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes){

        if(Platform.getInstance().IsAndroid()){
            int already_swiped = 0;
            By by = this.getLocatorByString(locator);
            while( driver.findElements(by).size() == 0 ){

            if (already_swiped > max_swipes) {
                waitForElementPresent(
                        locator, "Не найден элемент при свайпе. \n" + error_message,
                        0
                );
                return;
            }
            swipeUpQuick();
            ++already_swiped;
            }
        }
        else if(Platform.getInstance().IsMW()){
           this.scrollWebPageTillElementNotVisible(locator, error_message, max_swipes);
        }
        else{
            System.out.println("Метод swipeUpToFindElement() отсутствует для платформы " + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeElementToLeft(String locator, String error_message) {

        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(
                    locator,
                    error_message,
                    10
            );
            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action
                    .press(right_x, middle_y)
                    .waitAction(300)
                    .moveTo(left_x, middle_y)
                    .release()
                    .perform();
        } else{
            System.out.println("Метод swipeElementToLeft() отсутствует для платформы " + Platform.getInstance().getPlatformVar());
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeInSeconds){
        WebElement element = waitForElementPresent(locator, error_message,timeInSeconds);
        return element.getAttribute(attribute);
    }

    public boolean assertHasTextSearch(String locator, String text){
        By by = this.getLocatorByString(locator);

        List<WebElement> elements_search = driver.findElements(by);
        for(WebElement elem: elements_search)
            if (elem.getText().contains(text))
                return true;
        return false;
    }

    public int getAmountPairElementsWithText(String locator_s1, String locator_s2, String s1, String s2){
        int count_match = 0;
        String s_text1, s_text2;

        By by1 = this.getLocatorByString(locator_s1);
        By by2 = this.getLocatorByString(locator_s2);

        List<WebElement> list_elements_1 = driver.findElements(by1);
        List<WebElement> list_elements_2 = driver.findElements(by2);

        for (int i = 0; i < list_elements_1.size(); i++){
            s_text1 = list_elements_1.get(i).getText();

            if (i >= 0 && i < list_elements_2.size()) {
                s_text2 = list_elements_2.get(i).getText();
                if (s_text1.contains(s1) & s_text2.contains(s2)) count_match++;
            }
        }
       return count_match;
    }

    private By getLocatorByString(String locator_with_type){
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")){
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else if (by_type.equals("className")) {
            return By.className(locator);
        } else if (by_type.equals("css")) {
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Неизвестный тип локатора " + locator_with_type);
        }
    }

}

