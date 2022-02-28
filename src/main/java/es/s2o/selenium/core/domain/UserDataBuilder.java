package es.s2o.selenium.core.domain;

public class UserDataBuilder {

    public static UserBuilder defaultUser(){
        return UserBuilder.anUser().withUserName("tomsmith").withPassword("SuperSecretPassword!");
    }
}
