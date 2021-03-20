package com.safarizoo.animal;

import com.safarizoo.zoo.Employee;

import java.util.Date;
import java.util.Stack;

/**
 * Класс животных на содержании зоопарка
 */
public class Animal {

    /** Имя животного */
    private String name;

    /** Вид */
    private String specie;

    /** Возраст */
    private int age;

    /** История болезней */
    private Stack<Ill> illStory;

    /** История кормления */
    private Stack<Feed> feedStory;

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
     * @return запись о последнем заболевании
     */
    public Ill getIllStory() {
        return illStory.peek();
    }

    /**
     * @return запись о последней кормёжке
     */
    public Feed getFeedStory() {
        return feedStory.peek();
    }

    /**
     * @return имя
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Животное {" +
                "Имя '" + name + '\'' +
                ", Вид '" + specie + '\'' +
                ", Возраст " + age +
                '}';
    }
}

