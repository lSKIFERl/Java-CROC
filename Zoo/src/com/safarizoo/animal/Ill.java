package com.safarizoo.animal;

import java.util.Date;

/**
 * Класс для учёта записей болезни
 */
public class Ill {

    /** Дата болезни */
    private Date illDate;

    /** Название болезни */
    private String illName;

    /**
     * Конструктор для учёта записей болезней
     *
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
    public Date getIllDate() {
        return illDate;
    }

    /**
     * @return Название болезни
     */
    public String getIllName() {
        return illName;
    }
}
