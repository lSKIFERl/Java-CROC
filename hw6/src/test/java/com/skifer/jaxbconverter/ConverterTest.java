package com.skifer.jaxbconverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skifer.film.Films;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    Converter converter = new Converter();

    @Test
    void toXml() {
        //System.out.println(converter.);
    }

    @Test
    void fromXml() {
    }

    @Test
    void fromFilmsToPeople() {
        try {
            System.out.println(converter.toXml(converter.fromFilmsToPeople("<films>\n" +
                    "    <film>\n" +
                    "        <title>Фильм 1</title>\n" +
                    "        <description>Описание 1</description>\n" +
                    "        <screenwriters>\n" +
                    "            <screenwriter name=\"Человек 1\"/>\n" +
                    "            <screenwriter name=\"Человек 2\"/>\n" +
                    "        </screenwriters>\n" +
                    "        <directors>\n" +
                    "            <director name=\"Человек 1\"/>\n" +
                    "            <director name=\"Человек 3\"/>\n" +
                    "        </directors>\n" +
                    "    </film>\n" +
                    "    <film>\n" +
                    "        <title>Фильм 2</title>\n" +
                    "        <description>Описание 2</description>\n" +
                    "        <screenwriters>\n" +
                    "            <screenwriter name=\"Человек 3\"/>\n" +
                    "            <screenwriter name=\"Человек 2\"/>\n" +
                    "        </screenwriters>\n" +
                    "        <directors>\n" +
                    "            <director name=\"Человек 2\"/>\n" +
                    "            <director name=\"Человек 4\"/>\n" +
                    "            <director name=\"Человек 3\"/>\n" +
                    "        </directors>\n" +
                    "    </film>\n" +
                    "</films>")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void objectToFileXml() {
    }

    @Test
    void fileXmlToObject() {
        try {
            System.out.println(converter.toXml(converter.fileXmlToObject("films.xml", Films.class)));
        } catch (JsonProcessingException | JAXBException e) {
            e.printStackTrace();
        }
    }
}