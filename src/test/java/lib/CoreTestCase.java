package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CoreTestCase extends TestCase {
    protected RemoteWebDriver driver;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();

        if(Platform.getInstance().IsMW())
            this.openWikiWebPageForMobileWeb();;
    }

    @Override
    protected void tearDown() throws Exception{
        if (driver != null) {
            driver.quit();
            super.tearDown();
        }
        driver = null;
    }

    protected void rotateScreenPortrait(){
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Метод rotateScreenPortrait() отсутствует для платформы " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void rotateScreenLandscape(){
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Метод rotateScreenLandscape() отсутствует для платформы " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void backgroundApp(int seconds){
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(seconds);
        } else {
            System.out.println("Метод backgroundApp() отсутствует для платформы " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void openWikiWebPageForMobileWeb(){
        if (Platform.getInstance().IsMW()){
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Метод openWikiWebPageForMobileWeb() отсутствует для платформы " + Platform.getInstance().getPlatformVar());
        }
    }
}
