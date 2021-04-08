package com.skifer.film;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skifer.film.model.Film;
import com.skifer.film.model.FilmMakers;
import com.skifer.jaxbconverter.Converter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FilmsTest {

    @Test
    public void filmTest() throws JsonProcessingException {
        List<FilmMakers> directors = new ArrayList<>();
        List<FilmMakers> writers = new ArrayList<>();
        directors.add(new FilmMakers("jojo"));
        directors.add(new FilmMakers("speedwagoon"));
        writers.add(new FilmMakers("dio"));
        List<Film> cinema = new ArrayList<>();
        cinema.add(new Film("avatar", "smth", writers, directors));
        Films films = new Films();
        films.setFilms(cinema);
        Converter converter = new Converter();
        System.out.println(converter.toXml(films));
    }

}