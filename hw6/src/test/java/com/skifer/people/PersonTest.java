package com.skifer.people;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skifer.jaxbconverter.Converter;
import com.skifer.people.model.Functions;
import com.skifer.people.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class PersonTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void test() {
        Person person = new Person();
        person.setName("Кэмерон");
        person.addFilm("Avatar", Arrays.asList(new Functions("Режисёр"),new Functions("Сценарист")));
        try {
            System.out.println(new Converter().toXml(person));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}