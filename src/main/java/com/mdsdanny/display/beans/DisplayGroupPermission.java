package com.mdsdanny.display.beans;

import java.io.Serializable;

public class DisplayGroupPermission extends Display implements Serializable{

    public DisplayGroupPermission() {}

    public DisplayGroupPermission(Long id, String name, String state) {
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
