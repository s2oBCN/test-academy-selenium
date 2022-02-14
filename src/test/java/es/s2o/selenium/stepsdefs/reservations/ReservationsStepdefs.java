package es.s2o.selenium.stepsdefs.reservations;

import es.s2o.selenium.domain.ReservationDTO;
import es.s2o.selenium.pages.ReservationListPage;
import es.s2o.selenium.pages.ReservationPage;
import es.s2o.selenium.services.ReservationService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sacrists on 26.02.17.
 */
public class ReservationsStepdefs {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String WEB_ROOT = "https://s2obcn.github.io/";
    private static final String HOME = "reservationList.html";

    @Steps
    private ReservationService reservationService;

    private ReservationListPage reservationListPage;
    private ReservationPage reservationPage;

    private List<ReservationDTO> reservations;

    @Before
    public void beforeScenario() {
        LOGGER.debug("beforeScenario starts");
        reservationService.addReservations(2);
    }

    @After
    public void afterScenario() {
        LOGGER.debug("afterScenario starts");
        reservationService.clean();
    }

    @Given("^I'm in the reservations page$")
    public void iMInTheReservationsPage() throws Throwable {
        LOGGER.debug("iMInTheReservationsPage starts");
        reservationPage.openAt(WEB_ROOT + HOME);
    }

    @When("^I register the following reservations:$")
    public void iRegisterTheFollowingReservations(List<ReservationDTO> reservationDTOList) throws Throwable {
        LOGGER.debug("iRegisterTheFollowingReservations starts, list size:[{}]", reservationDTOList.size());
        reservations = reservationDTOList;
        reservations.forEach(reservation -> reservationListPage.addReservations(reservation));
    }

    @Then("^I get the reservation in the reservations list$")
    public void iGetTheReservationInTheReservationsList() throws Throwable {
        LOGGER.debug("iGetTheReservationInTheReservationsList starts");
        List<ReservationDTO> actualReservations = reservationListPage.getReservationList();
        assertThat(actualReservations).as("Reservation list")
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(reservations);
    }
}
