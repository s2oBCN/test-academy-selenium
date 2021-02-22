package es.s2o.selenium.tests.reservations;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/reservations",
        glue = "es.s2o.selenium.stepsdefs.reservations")
public class ReservationTest extends RunnerBase{


}
