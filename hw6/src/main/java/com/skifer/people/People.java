package com.skifer.people;

import com.skifer.people.model.Person;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "people")
public class People {
    @XmlElement(name = "person")
    private List<Person> people = new ArrayList<>();

    public People() {}

    public People(List<Person> people) {
        this.people = people;
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public List<Person> getPeople() {
        return people;
    }
}
