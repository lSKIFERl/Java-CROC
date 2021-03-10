package com.safarizoo;

import java.util.Date;
import java.util.Stack;

/**
 * Класс животных на содержании зоопарка
 */
class Animal {

    private String name;
    private String specie;
    private int age;
    private Stack<Ill> illStory;
    private Stack<Feed> feedStory;

    private Employee employee;
    private Aviary aviary;

    /**
     * Инициализация животного
     * @param specie вид
     * @param name имя
     * @param age возраст
     */
    public Animal(String specie, String name, int age) {
        this.name = name;
        this.age = age;
        this.specie = specie;
        illStory = new Stack<>();
        feedStory = new Stack<>();
    }

    /**
     * добавление записи о болезни
     * @param date время
     * @param illName название болезни
     */
    public void addIllStory(Date date, String illName) {
        illStory.add(new Ill(date,illName));
    }

    /**
     * добавление записи о кормёжке
     * @param date время
     * @param feeder кормилец
     */
    public void addFeedStory(Date date, Employee feeder) {
        feedStory.add(new Feed(date, feeder));
    }

    /**
     * закрепит сотрудника
     * @param employee сотрудник
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * установит вальер
     * @param aviary - вальер
     */
    public void setAviary(Aviary aviary) {
        this.aviary = aviary;
    }

    /**
     *
     * @return запись о последнем заболевании
     */
    public Ill getIllStory() {
        return illStory.peek();
    }

    /**
     *
     * @return запись о последней кормёжке
     */
    public Feed getFeedStory() {
        return feedStory.peek();
    }

    /**
     *
     * @return приставленный сотрудник
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     *
     * @return номер вальера
     */
    public Aviary getAviary() {
        return aviary;
    }

    /**
     *
     * @return имя
     */
    public String getName() {
        return name;
    }
}

class Feed {
    private Date feedDate;
    private Employee feeder;

    /**
     * Конструктор для учёта кормления
     * @param feedDate дата
     * @param feeder кормилец
     */
    public Feed(Date feedDate, Employee feeder) {
        this.feedDate = feedDate;
        this.feeder = feeder;
    }

    /**
     * @return Дата кормёжки
     */
    protected Date getFeedDate() {
        return feedDate;
    }

    /**
     * @return Кормилец
     */
    protected Employee getFeeder() {
        return feeder;
    }
}

/**
 * Класс для учёта записей болезни
 */
class Ill {
    private Date illDate;
    private String illName;

    /**
     * Конструктор для учёта записей болезней
     * @param illDate дата
     * @param illName болезнь
     */
    public Ill(Date illDate, String illName) {
        this.illDate = illDate;
        this.illName = illName;
    }

    /**
     * @return Дата регистрации болезни
     */
    protected Date getIllDate() {
        return illDate;
    }

    /**
     * @return Название болезни
     */
    protected String getIllName() {
        return illName;
    }
}
