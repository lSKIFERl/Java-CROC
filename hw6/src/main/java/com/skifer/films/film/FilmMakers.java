package com.skifer.films.film;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

/**
 * Класс участников фильма, нужен исключительно чтобы правильно построить xml
 */
public class FilmMakers {

    /** Имя */
    @XmlAttribute
    private String name;

    /**
     * Класс участников фильма, нужен исключительно чтобы правильно построить xml
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmMakers)) return false;
        FilmMakers that = (FilmMakers) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
