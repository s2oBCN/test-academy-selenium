package es.s2o.selenium.builders;

import es.s2o.selenium.domain.Client;
import es.s2o.selenium.domain.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

public final class ReservationBuilder {
    private Integer id;
    private Client client;
    private LocalDate date;
    private Integer number;
    private LocalTime time;
    private String table;

    private ReservationBuilder() {
    }

    public static ReservationBuilder aReservation() {
        return new ReservationBuilder();
    }

    public ReservationBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ReservationBuilder withClient(Client client) {
        this.client = client;
        return this;
    }

    public ReservationBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public ReservationBuilder withNumber(Integer number) {
        this.number = number;
        return this;
    }

    public ReservationBuilder withTime(LocalTime time) {
        this.time = time;
        return this;
    }

    public ReservationBuilder withTable(String table) {
        this.table = table;
        return this;
    }

    public Reservation build() {
        Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setClient(client);
        reservation.setDate(date);
        reservation.setNumber(number);
        reservation.setTime(time);
        reservation.setTable(table);
        return reservation;
    }
}
