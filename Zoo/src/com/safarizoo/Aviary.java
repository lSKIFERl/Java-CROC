package com.safarizoo;

import java.util.Date;
import java.util.Stack;

/**
 * Класс вальеров
 */
class Aviary {

    private int aviaryNumber;
    private Stack<Date> cleaningStory;

    /**
     * Конструктор вальеров зоопарка, нужно указать лишь номер вальера
     * @param aviaryNumber номер вальера
     */
    public Aviary(int aviaryNumber) {
        cleaningStory = new Stack<>();
        this.aviaryNumber = aviaryNumber;
    }

    /**
     * Добавит запись об очистке вальера
     * @param date запись
     */
    public void addCleaningStory(Date date)
    {
        cleaningStory.add(date);
    }

    /**
     * @return номер вальера
     */
    public int getAviaryNumber() {
        return aviaryNumber;
    }

    /**
     * @return дата последней очистки
     */
    public Date getCleaningStory() {
        return cleaningStory.peek();
    }
}
