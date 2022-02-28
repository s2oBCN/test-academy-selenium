package es.s2o.selenium.reservations.pages;

import es.s2o.selenium.core.pages.PageObjectBase;
import es.s2o.selenium.reservations.domain.ReservationDTO;
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

    public void addReservations(ReservationDTO reservation) {
        btnAdd.click();
        reservationPage.registerReservation(reservation);
    }

    public List<ReservationDTO> getReservationList() {
        LOGGER.debug("getReservationList starts");

        List<Map<Object, String>> rows = inTable(tblList).getRows();
        return  rows.stream().map(this::mapReservation).collect(Collectors.toList());
    }

    private ReservationDTO mapReservation(Map<Object, String> rowMap) {
        ReservationDTO reservation = new ReservationDTO();
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
