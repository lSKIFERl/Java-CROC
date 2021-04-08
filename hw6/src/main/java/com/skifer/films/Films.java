package com.skifer.films;

import com.skifer.films.film.Film;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс фильмов, нужен для равильного построения xml
 */
@XmlRootElement(name = "films")
public class Films {

    /** Название фильма */
    @XmlElement(name = "film")
    private List<Film> films = new ArrayList<>();

    /**
     * Класс фильмов, нужен для равильного построения xml
     */
    public Films() {}

    /**
     * Класс фильмов, нужен для равильного построения xml
     */
    public Films(List<Film> films) {
        this.films = films;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Films)) return false;
        Films films1 = (Films) o;
        return Objects.equals(films, films1.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(films);
    }
}
