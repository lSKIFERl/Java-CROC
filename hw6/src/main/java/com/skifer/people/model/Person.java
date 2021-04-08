package com.skifer.people.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {

    @XmlElement
    private String name;

    @XmlElementWrapper(name = "films")
    @XmlElement (name = "film")
    private List<Film> films = new ArrayList<>();

    public Person() {}

    private class Film {
        @XmlAttribute
        private String title;
        @XmlElementWrapper(name = "functions")
        @XmlElement(name = "function")
        private List<Functions> functions;

        public Film(String title, List<Functions> functions) {
            this.title = title;
            this.functions = functions;
        }

        public void addFunction(Functions function) {
            this.functions.add(function);
        }
    }

    public Person(String name, List<Film> films) {
        this.name = name;
        this.films = films;
    }

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
        return Objects.equals(name, person.name) && Objects.equals(films, person.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, films);
    }
}
