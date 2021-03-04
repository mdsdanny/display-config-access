package com.mdsdanny.display.beans;

import java.io.Serializable;

public class DisplayModuleByUser extends Display implements Serializable {

    public DisplayModuleByUser() {}

    public DisplayModuleByUser(Long id, String name, String state) {
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
