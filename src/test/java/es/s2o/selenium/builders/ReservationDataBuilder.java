package es.s2o.selenium.builders;

import es.s2o.selenium.domain.Client;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationDataBuilder {

    public static ReservationBuilder defaultReservation(Integer id, Client client) {
        return ReservationBuilder
                .aReservation()
                .withId(id)
                .withClient(client)
                .withDate(LocalDate.now().plusDays(1))
                .withNumber(1)
                .withTable("#000000")
                .withTime(LocalTime.NOON);
    }
}
