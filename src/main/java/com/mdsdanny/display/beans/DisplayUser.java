package com.mdsdanny.display.beans;

import java.io.Serializable;
import java.util.logging.Logger;

public class DisplayUser extends Display implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(DisplayUser.class.getName());

    private Long dateOfBirth;
    private String email;

    public Long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DisplayUser() {}

    public DisplayUser(Long id, String name, String state, String email, Long dateOfBirth) {
        setId(id);
        setName(name);
        setState(state);
        setEmail(email);
        setDateOfBirth(dateOfBirth);
    }

    public DisplayUser(Long id, String name, String state) {
        setId(id);
        setName(name);
        setState(state);
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", state='" + getState() + '\'' +
                '}';
    }

}
