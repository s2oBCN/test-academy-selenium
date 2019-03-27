package es.s2o.selenium.pages;

import es.s2o.selenium.domain.ReservationDto;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.thucydides.core.pages.components.HtmlTable.inTable;

/**
 * Created by sacrists on 26.02.17.
 */

public class ReservationListPage extends PageObjectBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private ReservationPage reservationPage;

    private WebElementFacade tblList;
    private WebElementFacade btnAdd;

    public void addReservations(ReservationDto reservation) {
        btnAdd.click();
        reservationPage.registerReservation(reservation);
    }

    public List<ReservationDto> getReservationList() {
        LOGGER.debug("getReservationList starts");

        List<Map<Object, String>> rows = inTable(tblList).getRows();
        List<ReservationDto> reservations = rows.stream().map(this::mapReservation).collect(Collectors.toList());
        return reservations;
    }

    private ReservationDto mapReservation(Map<Object, String> rowMap) {
        ReservationDto reservation = new ReservationDto();
        reservation.setName(rowMap.get("Name"));
        reservation.setPhone(rowMap.get("Phone"));
        reservation.setEmail(rowMap.get("Email"));
        reservation.setDate(rowMap.get("Date"));
        reservation.setNumber(rowMap.get("Number"));
        reservation.setTime(rowMap.get("Time"));
        reservation.setColor(rowMap.get("Table"));
        return reservation;
    }
}
