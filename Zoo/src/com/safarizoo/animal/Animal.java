package com.safarizoo.animal;

import com.safarizoo.zoo.Employee;

import java.util.Arrays;
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
    private Ill[] illStory;

    /** История кормления */
    private Feed[] feedStory;

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
        illStory = new Ill[1];
        feedStory = new Feed[1];
    }

    /**
     * добавление записи о болезни
     * @param date время
     * @param illName название болезни
     */
    public void addIllStory(Date date, String illName) {
        illStory[illStory.length-1] = new Ill(date,illName);
        illStory = Arrays.copyOf(illStory, illStory.length + 1);
    }

    /**
     * добавление записи о кормёжке
     * @param date время
     * @param feeder кормилец
     */
    public void addFeedStory(Date date, Employee feeder) {
        feedStory[feedStory.length-1] = new Feed(date, feeder);
        feedStory = Arrays.copyOf(feedStory, feedStory.length + 1);
    }

    /**
     * @return запись о последнем заболевании
     */
    public Ill getIllStory() {
        return illStory[illStory.length-2];
    }

    /**
     * @return запись о последней кормёжке
     */
    public Feed getFeedStory() {
        return feedStory[feedStory.length-2];
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

