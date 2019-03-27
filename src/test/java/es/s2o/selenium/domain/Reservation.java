package es.s2o.selenium.domain;


import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private Integer id;
    private Client client;
    private LocalDate date;
    private Integer number;
    private LocalTime time;
    private String table;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "id=" + id +
                ", client=" + client +
                ", date=" + date +
                ", number=" + number +
                ", time=" + time +
                ", table='" + table + '\'' +
                '}';
    }
}
