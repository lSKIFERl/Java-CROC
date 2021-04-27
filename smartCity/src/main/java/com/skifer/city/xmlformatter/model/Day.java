package com.skifer.city.xmlformatter.model;

import com.skifer.city.xmlformatter.model.accident.Accident;
import com.skifer.city.xmlformatter.model.trafic.Traffic;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Класс дня, в котором замеряются колличество ДТП и нагруженность трафика
 */
@XmlRootElement
public class Day {

    /**
     * Дата
     */
    @XmlAttribute
    private String date;

    /**
     * Записи о ДТП
     */
    @XmlElement
    private Accident accidents;

    /**
     * Загруженность
     */
    @XmlElement
    private Traffic traffic;

    /**
     * Класс дня, в котором замеряются колличество ДТП и нагруженность трафика
     * @param date Дата
     * @param accident {@link Accident}
     * @param traffic {@link Traffic}
     */
    public Day(Date date, Accident accident, Traffic traffic) {
        this.date = new SimpleDateFormat("d MMMM yyyy").format(date);
        this.accidents = accident;
        this.traffic = traffic;
    }

    public Day() {
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAccident(Accident accident) {
        this.accidents = accident;
    }

    public void setTraffic(Traffic traffic) {
        this.traffic = traffic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Day)) return false;
        Day day = (Day) o;
        return Objects.equals(date, day.date) && Objects.equals(accidents, day.accidents) && Objects.equals(traffic, day.traffic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, accidents, traffic);
    }
}
