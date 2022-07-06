package cloud.autotests.tests;

import cloud.autotests.config.CredConfig;
import cloud.autotests.helpers.AllureAttachments;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;


public class TestBase extends AllureAttachments {
    static CredConfig config = ConfigFactory.create(CredConfig.class);

    @BeforeAll
    static void beforeAll() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = config.baseUrl();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserSize = "1800x900";
        Configuration.holdBrowserOpen = true;

        String remoteDriverUrl = System.getProperty("remoteDriverUrl", "selenoid.autotests.cloud/wd/hub");
        Configuration.remote = String.format("https://%s:%s@%s", config.login(), config.password(), remoteDriverUrl);
    }

    @BeforeEach
    public void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    public void afterEach() {

        screenshotAs("Last screenshot");
        pageSource();
        browserConsoleLogs();
        addVideo();


    }
}
