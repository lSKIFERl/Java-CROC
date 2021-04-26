package com.skifer.city.xmlformatter.model.trafic;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Класс загруженности дорог
 */
@XmlRootElement(name = "traffic")
public class Traffic {

    /**
     * Класс загруженности дорог
     */
    public Traffic() {}

    /**
     * Подкласс часов загруженности (за день их может быть несколько)
     */
    private class RushHours {

        /**
         * Самые нагруженные часы
         */
        @XmlAttribute
        private final String name = "Самые нагруженные часы";

        /**
         * Время начала пробки
         */
        @XmlElement(name = "С")
        private String start;

        /**
         * Время конца пробки
         */
        @XmlElement(name = "До")
        private String end;

        /**
         * Подкласс часов загруженности (за день их может быть несколько)
         */
        public RushHours(Date start, Date end) {
            this.start = new SimpleDateFormat("HH").format(start);
            this.end = new SimpleDateFormat("HH").format(end);
        }

        public String getStart() {
            return start;
        }

        public String getEnd() {
            return end;
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
            return Objects.equals(getStart(), rushHours.getStart()) && Objects.equals(getEnd(), rushHours.getEnd());
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, getStart(), getEnd());
        }
    }

    /**
     * Подкласс для обозначения час пика
     */
    private class MaxRushHour {

        /**
         * Имя аттрибута
         */
        @XmlAttribute
        private final String name = "Час пик";

        /**
         * Час пик
         */
        @XmlElement
        private String time;

        /**
         * Подкласс для обозначения час пика
         */
        public MaxRushHour(Date time) {
            this.time = new SimpleDateFormat("HH").format(time);
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MaxRushHour)) return false;
            MaxRushHour that = (MaxRushHour) o;
            return Objects.equals(getTime(), that.getTime());
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, getTime());
        }
    }

    @XmlElement
    private final String name = "Загруженность";

    @XmlElement
    private List<RushHours> rushHours;

    @XmlElement
    private MaxRushHour maxRushHour;

    public String getName() {
        return name;
    }

    public List<RushHours> getRushHours() {
        return rushHours;
    }

    public void setRushHours(List<RushHours> rushHours) {
        this.rushHours = rushHours;
    }

    /**
     * Добавление пробки
     * @param start время начала пробки
     * @param end время конца пробки
     */
    public void addRushHours(Date start, Date end) {
        this.rushHours.add(new RushHours(start, end));
    }

    public MaxRushHour getMaxRushHour() {
        return maxRushHour;
    }

    public void setMaxRushHour(Date maxRushHour) {
        this.maxRushHour.setTime(new SimpleDateFormat("HH").format(maxRushHour));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Traffic)) return false;
        Traffic traffic = (Traffic) o;
        return Objects.equals(getName(), traffic.getName()) && Objects.equals(getRushHours(), traffic.getRushHours()) && Objects.equals(getMaxRushHour(), traffic.getMaxRushHour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getRushHours(), getMaxRushHour());
    }
}
