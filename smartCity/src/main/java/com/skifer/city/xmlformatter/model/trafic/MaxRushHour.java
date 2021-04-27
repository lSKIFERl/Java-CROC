package com.skifer.city.xmlformatter.model.trafic;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Подкласс для обозначения час пика
 */
public class MaxRushHour {

    /**
     * Имя аттрибута
     */
    @XmlAttribute
    private final String name = "Час_пик";

    /**
     * Час пик
     */
    @XmlElement
    private String time;

    /**
     * Подкласс для обозначения час пика
     */
    public MaxRushHour(Time time) {
        this.time = new SimpleDateFormat("HH").format(time);
    }

    public MaxRushHour() {}

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaxRushHour)) return false;
        MaxRushHour that = (MaxRushHour) o;
        return Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, time);
    }
}

