package com.skifer.people.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Functions {
    @XmlAttribute
    private String name;

    public Functions(String name) {
        this.name = name;
    }

    public Functions() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
