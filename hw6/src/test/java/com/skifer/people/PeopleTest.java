package com.skifer.people;

import com.skifer.jaxbconverter.Converter;
import com.skifer.people.hooman.Functions;
import com.skifer.people.hooman.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PeopleTest {

    @Test
    public void peopleTest() throws IOException {
        final Person person = new Person();
        person.setName("Кэмерон");
        final Functions director = new Functions("Режисёр"), writer = new Functions("Сценарист");
        person.addFilm("Avatar", Arrays.asList(director,writer));
        final People people = new People();
        final Converter converter = new Converter();
        people.addPerson(person);
        final String xml = converter.toXml(people);
        System.out.println(xml);
        Assertions.assertEquals(people, converter.fromXml(xml, People.class));
    }
}