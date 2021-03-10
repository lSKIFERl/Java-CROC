package com.safarizoo;

/**
 * Класс сотрудников
 */
class Employee {

    private String name;
    private String secondName;
    private int number;

    /**
     * Конструктор для класса "сотрудник"
     * @param name имя
     * @param secondName фамилия
     * @param number табельный номер
     */
    public Employee(String name, String secondName, int number) {
        this.name = name;
        this.secondName = secondName;
        this.number = number;
    }

    /**
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * @return фамилия
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * @return табельный номер
     */
    public int getNumber() {
        return number;
    }
}
