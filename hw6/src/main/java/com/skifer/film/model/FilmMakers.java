package com.skifer.film.model;

import javax.xml.bind.annotation.XmlAttribute;

public class FilmMakers {

    @XmlAttribute
    private String name;

    public FilmMakers(String name) {
        this.name = name;
    }

    public FilmMakers() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
