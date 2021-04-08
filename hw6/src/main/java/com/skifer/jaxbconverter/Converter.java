package com.skifer.jaxbconverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.skifer.films.Films;
import com.skifer.films.film.Film;
import com.skifer.people.People;
import com.skifer.people.hooman.Functions;
import com.skifer.people.hooman.Person;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Класс конвертации, основной метод конвертации реализован здесь
 */
public class Converter {

    /**
     * Сериализует объект в xml.
     *
     * @param data объект
     * @return xml
     */
    public String toXml(Object data) throws JsonProcessingException {
        return createXmlMapper().writeValueAsString(data);
    }

    /**
     * Десериализация из xml.
     *
     * @param xml xml
     * @param type тип объекта
     * @param <T> тип
     * @return объект
     */
    public <T> T fromXml(String xml, Class<T> type) throws IOException {
        return createXmlMapper().readValue(xml, type);
    }

    /**
     * Создаём настроенный mapper JAXB.
     * @return mapper
     */
    private XmlMapper createXmlMapper() {
        final XmlMapper mapper = new XmlMapper();
        mapper.setDefaultUseWrapper(false);
        mapper.registerModule(new JaxbAnnotationModule()); // модуль jaxb
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // форматирование вывода
        return mapper;
    }

    /**
     * Конвертирует данные о фильмах в данные о людях, принимавших в них участие
     * @param filmsXml текст формата xml
     * @return Объект People
     * @throws IOException Выбрасывается, если не получилось сконвертировать
     */
    public People fromFilmsToPeople(String filmsXml) throws IOException {

        Films films;
        People people = new People();

            films = fromXml(filmsXml, Films.class);

            Set<String> names = new TreeSet<>();
            for (Film film : films.getFilms()) {
                names.addAll(film.getScreenWriters());
                names.addAll(film.getDirectors());
            }

            for (String i : names) {
                Person person = new Person();
                person.setName(i);
                for (Film film : films.getFilms()) {
                    List<Functions> functions = new ArrayList<>();
                    if (film.getScreenWriters().contains(i)) {
                        functions.add(new Functions("Сценарист"));
                    }
                    if (film.getDirectors().contains(i)) {
                        functions.add(new Functions("Режиссер"));
                    }
                    if(!functions.isEmpty()) {
                        person.addFilm(film.getTitle(), functions);
                    }
                }
                people.getPeople().add(person);
            }
        return people;
    }

}
