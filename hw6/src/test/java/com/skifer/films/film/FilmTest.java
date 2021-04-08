package com.skifer.films.film;

import com.skifer.jaxbconverter.Converter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FilmTest {

    @Test
    void filmTest() throws IOException {
        final List<FilmMakers> directors = new ArrayList<>();
        final List<FilmMakers> writers = new ArrayList<>();
        directors.add(new FilmMakers("Майк Джадж"));
        writers.add(new FilmMakers("Майк Джадж"));
        writers.add(new FilmMakers("Этан Коэн"));
        final Film film = new Film("Идиократия", "фвфцвфвц", writers,directors);

        System.out.println(new Converter().toXml(film));
        final Converter converter = new Converter();
        final String xml = converter.toXml(film);
        Assertions.assertEquals(film, converter.fromXml(xml,Film.class));
    }

}