package com.skifer.people.hooman;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

/**
 * Класс ролей/должностей в фильмах, нужен для правильного построения xml
 */
public class Functions {

    /** Наименование должности */
    @XmlAttribute
    private String name;

    public Functions(String name) {
        this.name = name;
    }

    /**
     * Класс ролей/должностей в фильмах, нужен для правильного построения xml
     */
    public Functions() {
    }

    /**
     * Класс ролей/должностей в фильмах, нужен для правильного построения xml
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Functions)) return false;
        Functions functions = (Functions) o;
        return Objects.equals(name, functions.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
