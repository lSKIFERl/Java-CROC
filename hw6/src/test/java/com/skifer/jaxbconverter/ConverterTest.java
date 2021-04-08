package com.skifer.jaxbconverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skifer.films.Films;
import com.skifer.people.People;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;

import java.io.IOException;

class ConverterTest {

    final Converter converter = new Converter();
    final String xml = "<films>\n" +
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
            "</films>";
    String xml2 = "<people>\n" +
            "   <person>\n" +
            "       <name>Человек 1</name>\n" +
            "       <films>\n" +
            "           <film title=\"Фильм 1\">\n" +
            "               <functions>\n" +
            "                   <function name=\"Сценарист\"/>\n" +
            "                   <function name=\"Режиссер\"/>\n" +
            "               </functions>\n" +
            "           </film>\n" +
            "       </films>\n" +
            "   </person>\n" +
            "   <person>\n" +
            "       <name>Человек 2</name>\n" +
            "       <films>\n" +
            "           <film title=\"Фильм 1\">\n" +
            "               <functions>\n" +
            "                   <function name=\"Сценарист\"/>\n" +
            "               </functions>\n" +
            "           </film>\n" +
            "           <film title=\"Фильм 2\">\n" +
            "               <functions>\n" +
            "                   <function name=\"Сценарист\"/>\n" +
            "                   <function name=\"Режиссер\"/>\n" +
            "               </functions>\n" +
            "           </film>\n" +
            "       </films>\n" +
            "   </person>\n" +
            "   <person>\n" +
            "       <name>Человек 3</name>\n" +
            "       <films>\n" +
            "           <film title=\"Фильм 1\">\n" +
            "               <functions>\n" +
            "                   <function name=\"Режиссер\"/>\n" +
            "               </functions>\n" +
            "           </film>\n" +
            "           <film title=\"Фильм 2\">\n" +
            "               <functions>\n" +
            "                   <function name=\"Сценарист\"/>\n" +
            "                   <function name=\"Режиссер\"/>\n" +
            "               </functions>\n" +
            "           </film>\n" +
            "       </films>\n" +
            "   </person>\n" +
            "   <person>\n" +
            "       <name>Человек 4</name>\n" +
            "       <films>\n" +
            "           <film title=\"Фильм 2\">\n" +
            "               <functions>\n" +
            "                   <function name=\"Режиссер\"/>\n" +
            "               </functions>\n" +
            "           </film>\n" +
            "       </films>\n" +
            "   </person>\n" +
            "</people>\n";

    /*
    Тест руинится из-за немного отличающейся разметки из примера. В примере, я так понимаю, табуляция
    используется, а на выходе программы пробелы ставятся. Как это исправить я нинаю(
     */
    @Test
    void fromFilmsToPeople() throws IOException {
        System.out.println(xml);
        final People people = converter.fromFilmsToPeople(xml);
        try {
            System.out.println(converter.toXml(people));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(xml2, converter.toXml(people));
    }
}