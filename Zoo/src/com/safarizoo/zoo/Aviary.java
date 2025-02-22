package com.safarizoo.zoo;

import com.safarizoo.animal.Animal;

import java.util.Arrays;
import java.util.Date;
import java.util.Stack;

/**
 * Класс вольеров
 */
public class Aviary {

    /** Номер вольера */
    private int aviaryNumber;

    /** Закреплённое животное */
    private Animal animal;

    /** История уборок */
    private Date[] cleaningStory;

    /**
     * Конструктор вольеров зоопарка, нужно указать лишь номер вальера
     * @param aviaryNumber номер вальера
     */
    public Aviary(int aviaryNumber) {
        cleaningStory = new Date[1];
        this.aviaryNumber = aviaryNumber;
    }

    /**
     * Вернёт объекта животного, закреплённого за вольером
     * @return животное
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Закрепит животное за вольером
     * @param animal животное
     */
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    /**
     * Открепляет животное от вольера
     */
    public void unboundAnimal() {
        this.animal = null;
    }

    /**
     * Добавит запись об очистке вольера
     * @param date запись
     */
    public void addCleaningStory(Date date)
    {
        cleaningStory[cleaningStory.length-1] = date;
        cleaningStory = Arrays.copyOf(cleaningStory, cleaningStory.length + 1);
    }

    /**
     * Вернёт номер вольера
     * @return номер вольера
     */
    public int getAviaryNumber() {
        return aviaryNumber;
    }

    /**
     * Возвращает того, кто чистил. По факту, чистить может и не закреплённый за животным сотрудник
     * @return дата последней очистки
     */
    public Date getCleaningStory() {
        return cleaningStory[cleaningStory.length-2];
    }

    @Override
    public String toString() {
        return "Вольер{" +
                "Номер вольера" + aviaryNumber +
                ", Закреплённое животное " + animal +
                '}';
    }
}
