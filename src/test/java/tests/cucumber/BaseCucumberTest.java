package tests.cucumber;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import configuration.domain.PropertyNameSpace;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import configuration.listeners.SelenideListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import configuration.ProjectConfiguration;

public class BaseCucumberTest extends AbstractTestNGCucumberTests {
    protected static final Logger LOGGER = LogManager.getLogger(BaseCucumberTest.class);

    @BeforeSuite
    public void setConfiguration() {
        SelenideLogger.addListener("SelenideLogger", new SelenideListener());
        if(System.getProperty(PropertyNameSpace.SELENIDE_REMOTE.getValue()) == null) {
            Configuration.remote = ProjectConfiguration.getProperty(PropertyNameSpace.SELENIUM_HOST);
        }
        Configuration.timeout = Long.parseLong(ProjectConfiguration.getProperty(PropertyNameSpace.IMPLICIT_TIMEOUT));

        LOGGER.info("selenide.remote: " + Configuration.remote);
        LOGGER.info("selenide.baseUrl: " + Configuration.baseUrl);
    }
}
