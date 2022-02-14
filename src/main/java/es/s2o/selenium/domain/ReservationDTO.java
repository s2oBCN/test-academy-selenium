package es.s2o.selenium.domain;


/**
 * Created by sacrists on 26.02.17.
 */
public class ReservationDTO {

    private String name;
    private String phone;
    private String email;
    private String date;
    private String number;
    private String time;
    private String color;

    public ReservationDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", date='" + date + '\'' +
                ", number='" + number + '\'' +
                ", time='" + time + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
