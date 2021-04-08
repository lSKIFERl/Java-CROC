package com.skifer.people.hooman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skifer.jaxbconverter.Converter;
import com.skifer.people.hooman.Functions;
import com.skifer.people.hooman.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

class PersonTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void test() throws IOException {
        final Person person = new Person();
        person.setName("Кэмерон");
        final Functions director = new Functions("Режисёр"), writer = new Functions("Сценарист");
        person.addFilm("Avatar", Arrays.asList(director,writer));
        try {
            System.out.println(new Converter().toXml(person));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        final Converter converter = new Converter();
        final String xml = converter.toXml(person);
        Assertions.assertEquals(person, converter.fromXml(xml, Person.class));
    }
}