package com.mdsdanny.display.beans;

import java.io.Serializable;

public class DisplayPermissionByUser extends Display implements Serializable {

    public DisplayPermissionByUser() {}

    public DisplayPermissionByUser(Long id, String name, String state) {
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
