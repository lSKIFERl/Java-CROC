package com.skifer.films.film;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс фильма
 */
public class Film {
    /** Название */
    @XmlElement
    String title;

    /** Описание */
    @XmlElement
    String description;

    /** Список сценаристов */
    @XmlElementWrapper(name = "screenwriters")
    @XmlElement(name = "screenwriter")
    List<FilmMakers> screenWriters = new ArrayList<>();

    /** Список режисёров */
    @XmlElementWrapper(name = "directors")
    @XmlElement(name = "director")
    List<FilmMakers> directors = new ArrayList<>();

    /**
     * Класс фильма
     */
    public Film(String title, String description, List<FilmMakers> screenWriters, List<FilmMakers> directors) {
        this.title = title;
        this.description = description;
        this.screenWriters = screenWriters;
        this.directors = directors;
    }

    /**
     * Класс фильма
     */
    public Film() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает имена сценаристов
     * @return список сценаристов
     */
    public List<String> getScreenWriters() {
        List<String> writers = new ArrayList<>();
        for(FilmMakers filmMakers: screenWriters) {
            writers.add(filmMakers.getName());
        }
        return writers;
    }

    public void setScreenWriters(List<FilmMakers> screenWriters) {
        this.screenWriters = screenWriters;
    }

    /**
     * Возвращает список режисёров
     * @return список режисёров
     */
    public List<String> getDirectors() {
        List<String> directorsList = new ArrayList<>();
        for(FilmMakers filmMakers: directors) {
            directorsList.add(filmMakers.getName());
        }
        return directorsList;    }

    public void setDirectors(List<FilmMakers> directors) {
        this.directors = directors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return Objects.equals(title, film.getTitle()) && Objects.equals(description, film.getDescription()) && Objects.equals(this.getScreenWriters(), film.getScreenWriters()) && Objects.equals(this.getDirectors(), film.getDirectors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, screenWriters, directors);
    }
}
