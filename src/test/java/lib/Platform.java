package lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {

    private static final String PLATFORM_ANDROID = "Android";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
    private static final String PLATFORM_IOS = "iOS";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";

    private static Platform instance;
    private Platform() {};

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);
        if (this.IsAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        } else if (this.IsIOS()) {
            return new IOSDriver(URL, this.getIOSdDesiredCapabilities());
        } else if (this.IsMW()) {
            return new ChromeDriver(this.getMWChromeOptions());
        }
        else {
            throw new Exception("Cannot detect type of the Driver. Platform value " + this.getPlatformVar());
        }
    }

    public boolean IsAndroid() { return IsPlatform(PLATFORM_ANDROID); }

    public boolean IsIOS() { return IsPlatform(PLATFORM_IOS); }

    public boolean IsMW() { return IsPlatform(PLATFORM_MOBILE_WEB); }

    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\Leo\\Desktop\\Света\\Разное\\Курсы\\Автоматизация моб.приложений\\IdeaProjects\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIOSdDesiredCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","IOS");
        capabilities.setCapability("deviceName","iPhone SE");
        capabilities.setCapability("platformVersion","11.3");
        capabilities.setCapability("app","C:\\Users\\Leo\\Desktop\\Света\\Разное\\Курсы\\Автоматизация моб.приложений\\IdeaProjects\\JavaAppiumAutomation\\apks\\org.wikipedia.app");
        return capabilities;
    }

    private ChromeOptions getMWChromeOptions(){
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        //mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4/2/1; en-rus; Nexus 5 Build/JQP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.2");
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=340,640");

        return chromeOptions;
    }

    private boolean IsPlatform(String my_platform){
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    public String getPlatformVar(){
        return System.getenv("PLATFORM");
    }
}
