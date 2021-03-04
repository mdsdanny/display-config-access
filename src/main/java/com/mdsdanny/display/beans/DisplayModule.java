package com.mdsdanny.display.beans;

import java.io.Serializable;

public class DisplayModule extends Display implements Serializable {

    private String accessPoint;

    public String getAccessPoint() {
        return accessPoint;
    }

    public void setAccessPoint(String accessPoint) {
        this.accessPoint = accessPoint;
    }

    public DisplayModule() {}

    public DisplayModule(Long id, String name, String state, String accessPoint) {
        setId(id);
        setName(name);
        setState(state);
        setAccessPoint(accessPoint);
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", state='" + getState() + '\'' +
                ", accessPoint='" + getAccessPoint() + '\'' +
                '}';
    }

}
