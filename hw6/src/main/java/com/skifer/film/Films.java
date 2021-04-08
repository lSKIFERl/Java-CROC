package com.skifer.film;

import com.skifer.film.model.Film;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "films")
public class Films {
    @XmlElement(name = "film")
    private List<Film> films = new ArrayList<>();

    public Films() {}

    public Films(List<Film> films) {
        this.films = films;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }
}
