package com.skifer.city.xmlformatter.model.trafic;

import javax.xml.bind.annotation.XmlElement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс загруженности дорог
 */
public class Traffic {

    /**
     * Класс загруженности дорог
     */
    public Traffic() {
        this.rushHours = new ArrayList<>();
        this.maxRushHour = new MaxRushHour();
    }

    /**
     * Класс загруженности дорог
     */
    public Traffic(List<RushHours> rushHours, MaxRushHour maxRushHour) {
        this.rushHours = rushHours;
        this.maxRushHour = maxRushHour;
    }

    @XmlElement
    private List<RushHours> rushHours;

    @XmlElement
    private MaxRushHour maxRushHour;

    public void setRushHours(List<RushHours> rushHours) {
        this.rushHours = rushHours;
    }

    /**
     * Добавление пробки
     * @param start время начала пробки
     * @param end время конца пробки
     */
    public void addRushHours(Time start, Time end) {
        if(!start.equals(end))
            this.rushHours.add(new RushHours(start, end));
    }

    public void setMaxRushHour(Time maxRushHour) {
        this.maxRushHour.setTime(new SimpleDateFormat("HH:mm").format(maxRushHour));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Traffic)) return false;
        Traffic traffic = (Traffic) o;
        return Objects.equals(rushHours, traffic.rushHours) && Objects.equals(maxRushHour, traffic.maxRushHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rushHours, maxRushHour);
    }
}
