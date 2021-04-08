package com.skifer.people;

import com.skifer.people.hooman.Person;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс людей, принимавших участие в фильмах, нужен для правильного построения xml
 */
@XmlRootElement(name = "people")
public class People {

    /** Список людей, снимавших фильмы */
    @XmlElement(name = "person")
    private List<Person> people = new ArrayList<>();

    /**
     * Класс людей, принимавших участие в фильмах, нужен для правильного построения xml
     */
    public People() {}

    /**
     * Класс людей, принимавших участие в фильмах, нужен для правильного построения xml
     * @param people Список людей, принимавших участие в фильме
     */
    public People(List<Person> people) {
        this.people = people;
    }

    /** Добавляет к списку людей ещё 1 человека, создателя кино */
    public void addPerson(Person person) {
        people.add(person);
    }

    public List<Person> getPeople() {
        return people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof People)) return false;
        People people1 = (People) o;
        return Objects.equals(people, people1.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(people);
    }
}
