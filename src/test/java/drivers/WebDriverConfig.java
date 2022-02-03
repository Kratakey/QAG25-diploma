package drivers;

import com.codeborne.selenide.Browser;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:config/${environment}.properties",
        "classpath:config/local.properties"
})
public interface WebDriverConfig extends Config {

    @Key("deviceFarm")
    @DefaultValue("desktop")
    String getDeviceFarm();

    @Key("browserName")
    @DefaultValue("chrome")
    String getBrowserName();

    @Key("browserVersion")
    @DefaultValue("")
    String getBrowserVersion();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("headless")
    @DefaultValue("false")
    Boolean getHeadless();

    @Key("timeout")
    @DefaultValue("10000")
    long getTimeout();

    @Key("startMaximized")
    @DefaultValue("false")
    boolean getStartMaximized();

    @Key("baseUrl")
    @DefaultValue("https://app.maxibooking.ru")
    String getBaseUrl();

    @Key("remote")
    String getRemote();

    @Key("enableVNC")
    @DefaultValue("true")
    Boolean getVNC();

    @Key("enableVideo")
    @DefaultValue("true")
    Boolean getVideo();
}
