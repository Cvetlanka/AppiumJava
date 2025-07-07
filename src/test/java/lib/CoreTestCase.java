package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.util.Properties;

public class CoreTestCase {
    protected RemoteWebDriver driver;

    @Before
    @Step("Запуск драйвера и сессии")
    public void setUp() throws Exception {
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();

        if(Platform.getInstance().IsMW())
            this.openWikiWebPageForMobileWeb();;
    }

    @After
    @Step("Удаление драйвера и сессии")
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }

    @Step("Поворот экрана в портретный режим")
    protected void rotateScreenPortrait(){
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Метод rotateScreenPortrait() отсутствует для платформы " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Поворот экрана в горизонтальный режим")
    protected void rotateScreenLandscape(){
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Метод rotateScreenLandscape() отсутствует для платформы " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Уход приложения в режим background (не работает для Mobile Web)")
    protected void backgroundApp(int seconds){
        if (driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(seconds);
        } else {
            System.out.println("Метод backgroundApp() отсутствует для платформы " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Открытие URL-ссылки Википедии для Mobile Web (не работает для Android)")
    protected void openWikiWebPageForMobileWeb(){
        if (Platform.getInstance().IsMW()){
            driver.get("https://en.m.wikipedia.org");
        } else {
            System.out.println("Метод openWikiWebPageForMobileWeb() отсутствует для платформы " + Platform.getInstance().getPlatformVar());
        }
    }

    private void createAllurePropertyFile(){
        String path = System.getProperty("allure.results.directory");
        try{
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path +"/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos,"See https://github.com/allure-framework/allure-app/wiki/Environment");
            fos.close();
        } catch (Exception e) {
            System.err.println("Проблемы с записью файл с allure-properties");
            e.printStackTrace();
        }
    }
}
