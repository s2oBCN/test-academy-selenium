package es.s2o.selenium.tests.reservations;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.ThucydidesSystemProperty;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.BeforeClass;

public class RunnerBase {
    public static final String WEBDRIVER_REMOTE_URL = "webdriver.remote.url";

    @BeforeClass
    public static void setupClass() {
        EnvironmentVariables environmentVariables = Injectors.getInjector().getInstance(EnvironmentVariables.class);
        if (!environmentVariables.aValueIsDefinedFor(WEBDRIVER_REMOTE_URL))
            setUpWebDriver(environmentVariables);

    }

    private static void setUpWebDriver(EnvironmentVariables environmentVariables) {
        WebDriverManager.chromedriver().setup();
        environmentVariables.setProperty(ThucydidesSystemProperty.WEBDRIVER_CHROME_DRIVER.getPropertyName(),
                WebDriverManager.chromedriver().getBinaryPath());
    }

}
