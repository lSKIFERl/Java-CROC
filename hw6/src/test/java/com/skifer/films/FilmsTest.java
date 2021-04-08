package com.skifer.films;

import com.skifer.films.film.Film;
import com.skifer.films.film.FilmMakers;
import com.skifer.jaxbconverter.Converter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FilmsTest {

    @Test
    public void filmTest() throws IOException {
        final Films films = new Films();
        final List<FilmMakers> directors = new ArrayList<>();
        final List<FilmMakers> writers = new ArrayList<>();
        directors.add(new FilmMakers("Майк Джадж"));
        writers.add(new FilmMakers("Майк Джадж"));
        writers.add(new FilmMakers("Этан Коэн"));
        final List<Film> cinema = new ArrayList<>();
        cinema.add(new Film("Идиократия", "The Future Is A No Brainer", writers, directors));
        films.setFilms(cinema);
        final Converter converter = new Converter();
        final String filmsXml = converter.toXml(films);
        Assertions.assertEquals(films.getFilms().get(0).getDirectors(), converter.fromXml(filmsXml, Films.class).getFilms().get(0).getDirectors());
        System.out.println(converter.toXml(films));
    }

}