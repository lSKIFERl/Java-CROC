package com.safarizoo.zoo;

import com.safarizoo.animal.Animal;

/**
 * Класс сотрудников
 */
public class Employee {

    /** Имя сотрудника */
    private String firstName;

    /** Фамилия */
    private String lastName;

    /** Закреплённое животное */
    private Animal animal;

    /** Номер */
    private int number;

    /**
     * Конструктор для класса "сотрудник"
     * @param firstName имя
     * @param lastName фамилия
     * @param number табельный номер
     */
    public Employee(String firstName, String lastName, int number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
    }

    /**
     * Вернёт объекта животного, закреплённого за сотрудником
     * @return животное
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Закрепит животное за сотруником
     * @param animal животное
     */
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    /**
     * Открепляет животное от вальера
     */
    public void unboundAnimal() {
        this.animal = null;
    }

    /**
     * @return имя
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return фамилия
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return табельный номер
     */
    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Сотрудник {" +
                "Имя '" + firstName + '\'' +
                ", Фамилия '" + lastName + '\'' +
                ", Привязанное животное " + animal.getName() +
                ", Порядковый номер " + number +
                '}';
    }
}
