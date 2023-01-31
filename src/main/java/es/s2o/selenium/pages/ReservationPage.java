package es.s2o.selenium.pages;

import es.s2o.selenium.domain.ReservationDTO;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * Created by sacrists on 26.02.17.
 */

public class ReservationPage extends PageObjectBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    // Form
    @FindBy(id = "txtName")
    private WebElementFacade txtName;
    
    // With Selenium PageObject a WebElement without a @FindBy is like findById,
    // where the id is the name of the field
    private WebElementFacade txtPhone;
    private WebElementFacade txtEmail;
    private WebElementFacade txtDate;
    private WebElementFacade txtNumber;
    private WebElementFacade txtSearch;
    private WebElementFacade txtColor;
    private WebElementFacade btnSave;

    private WebElementFacade display;
    private WebElementFacade display_txtName;

    public void registerReservation(ReservationDTO reservation) {
        LOGGER.debug("registerReservation starts, reservation: [{}]", reservation);

        typeInto(txtName, reservation.getName());
        typeInto(txtPhone, reservation.getPhone());
        typeInto(txtEmail, reservation.getEmail());
        typeInto(txtDate, reservation.getDate());
        display.click();
        typeInto(txtNumber, reservation.getNumber());
        typeInto(txtSearch, reservation.getTime());
        display.click();
        evaluateJavascript("arguments[0].value=arguments[1];", txtColor, reservation.getColor());
        btnSave.click();
    }

    private String getHiddenValue(){
        return getDocument().getElementById("hiddenField").attr("value");
    }
}
