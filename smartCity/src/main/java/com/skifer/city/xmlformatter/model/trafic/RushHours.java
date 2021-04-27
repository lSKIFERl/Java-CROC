package com.skifer.city.xmlformatter.model.trafic;

import javax.xml.bind.annotation.XmlAttribute;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Подкласс часов загруженности (за день их может быть несколько)
 */
public class RushHours {

    /**
     * Время начала пробки
     */
    @XmlAttribute(name = "С")
    private String start;

    /**
     * Время конца пробки
     */
    @XmlAttribute(name = "До")
    private String end;

    /**
     * Подкласс часов загруженности (за день их может быть несколько)
     */
    public RushHours(Time start, Time end) {
        this.start = new SimpleDateFormat("HH:mm").format(start);
        this.end = new SimpleDateFormat("HH:mm").format(end);
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RushHours)) return false;
        RushHours rushHours = (RushHours) o;
        return Objects.equals(start, rushHours.start) && Objects.equals(end, rushHours.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
