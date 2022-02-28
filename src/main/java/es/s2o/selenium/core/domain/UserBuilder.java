package es.s2o.selenium.core.domain;

public final class UserBuilder {
    private String userName;
    private String password;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public User build() {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        return user;
    }
}
