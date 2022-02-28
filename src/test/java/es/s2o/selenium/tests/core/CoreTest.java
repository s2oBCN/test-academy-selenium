package es.s2o.selenium.tests.core;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/core",
        glue = "es.s2o.selenium.stepsdefs.core")
public class CoreTest {


}
