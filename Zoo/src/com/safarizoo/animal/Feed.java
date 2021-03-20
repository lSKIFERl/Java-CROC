package com.safarizoo.animal;

import com.safarizoo.zoo.Employee;

import java.util.Date;

/**
 * Класс для учёта кормёжки
 */
public class Feed {

    /** Дата кормления */
    private Date feedDate;

    /** Кормилец */
    private Employee feeder;

    /**
     * Конструктор для учёта кормления
     *
     * @param feedDate дата
     * @param feeder   кормилец
     */
    public Feed(Date feedDate, Employee feeder) {
        this.feedDate = feedDate;
        this.feeder = feeder;
    }

    /**
     * Возвращает дату кормёжки
     * @return Дата кормёжки
     */
    public Date getFeedDate() {
        return feedDate;
    }

    /**
     * Возвращает того, кто кормил. По факту, кормить может и не закреплённый за животным сотрудник
     * @return Кормилец
     */
    public Employee getFeeder() {
        return feeder;
    }
}
