package es.s2o.selenium.builders;

import es.s2o.selenium.domain.Client;

public final class ClientBuilder {
    private Integer id;
    private String name;
    private String phone;
    private String email;

    private ClientBuilder() {
    }

    public static ClientBuilder aClient() {
        return new ClientBuilder();
    }

    public ClientBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ClientBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ClientBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public Client build() {
        Client client = new Client();
        client.setId(id);
        client.setName(name);
        client.setPhone(phone);
        client.setEmail(email);
        return client;
    }
}
