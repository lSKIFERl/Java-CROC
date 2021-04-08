package com.skifer.people.hooman;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс человека, принимававшего участие в создании фильма, нужен для правильно построения xml
 */
public class Person {

    /** Имя */
    @XmlElement
    private String name;

    /** Список фильмов, где снимался */
    @XmlElementWrapper(name = "films")
    @XmlElement (name = "film")
    private List<Film> films = new ArrayList<>();

    public Person() {}

    /**
     * Класс человека, принимававшего участие в создании фильма, нужен для правильно построения xml
     */
    public Person(String name, List<Film> films) {
        this.name = name;
        this.films = films;
    }

    /** Добавляет фильм в список фильмов, где участвовал человек */
    public void addFilm (String title, List<Functions> functions) {
        films.add(new Film(title, functions));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(this.name, person.getName()) && Objects.equals(this.getFilms(), person.getFilms());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, films);
    }
}
