package es.s2o.selenium.stepsdefs.core;

import es.s2o.selenium.core.domain.User;
import es.s2o.selenium.core.domain.UserDataBuilder;
import es.s2o.selenium.core.pages.LoginPage;
import es.s2o.selenium.core.pages.SecurePage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.assertj.core.api.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginStepdefs {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String WEB_ROOT = "WEB_LOGIN";
    private static final String HOME = "/login";

    private SecurePage securePage;
    private LoginPage loginPage;
    private User user;

    @Before
    public void beforeScenario() {
        LOGGER.debug("beforeScenario starts");
        EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
        String baseUrl = variables.getProperty(WEB_ROOT);
        loginPage.openUrl(baseUrl + HOME);
    }

    @Given("a default user")
    public void a_default_user() {
        user = UserDataBuilder.defaultUser().build();
    }

    @When("he does the login")
    public void he_does_the_login() {
        loginPage.login(user);
    }

    @Then("he is logged ok")
    public void he_is_logged_ok() {
        assertThat(securePage.getTitle()).isEqualTo("Secure Page");
    }

}
