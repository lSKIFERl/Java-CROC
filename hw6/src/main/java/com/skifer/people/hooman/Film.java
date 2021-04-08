package com.skifer.people.hooman;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.Objects;

/** Вложенный класс, описывающий фильмы, где снимался данный человек, нужно для построения xml  */

public class Film {
        /** Название фильма */
        @XmlAttribute
        private String title;

        /** Список занимаемых должностей в фильме */
        @XmlElementWrapper(name = "functions")
        @XmlElement(name = "function")
        private List<Functions> functions;

        /**
         * Вложенный класс, описывающий фильмы, где снимался данный человек, нужно для построения xml
         * @param title Название
         * @param functions Список занимаемых должностей в фильме
         */
        public Film(String title, List<Functions> functions) {
            this.title = title;
            this.functions = functions;
        }

        public Film() {
        }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Functions> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Functions> functions) {
        this.functions = functions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return Objects.equals(this.title, film.getTitle()) && Objects.equals(this.getFunctions(), film.getFunctions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, functions);
    }
}
