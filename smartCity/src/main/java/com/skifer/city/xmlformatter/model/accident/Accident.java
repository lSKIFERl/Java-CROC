package com.skifer.city.xmlformatter.model.accident;

import javax.xml.bind.annotation.XmlElement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Класс для регистрации максимального колличества ДТП и его времени
 */
public class Accident {

    /**
     * Максимальное колличество ДТП за день
     */
    @XmlElement(name = "Максимальное_количество_ДТП")
    private Integer maxAccidentCount;

    /**
     * Час максимального колличества ДТП
     */
    @XmlElement(name = "Час_максимального_количества_ДТП")
    private String maxAccidentHour;

    /**
     * Класс для регистрации максимального колличества ДТП и его времени
     */
    public Accident(Integer maxAccidentCount, Time maxAccidentHour) {
        this.maxAccidentCount = maxAccidentCount;
        this.maxAccidentHour = new SimpleDateFormat("HH").format(maxAccidentHour);
    }

    public Accident() {
    }

    public void setMaxAccidentCount(Integer maxAccidentCount) {
        this.maxAccidentCount = maxAccidentCount;
    }

    public void setMaxAccidentHour(Time maxAccidentHour) {
        this.maxAccidentHour = new SimpleDateFormat("HH").format(maxAccidentHour);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Accident)) return false;
        Accident accident = (Accident) o;
        return Objects.equals(maxAccidentCount, accident.maxAccidentCount) && Objects.equals(maxAccidentHour, accident.maxAccidentHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxAccidentCount, maxAccidentHour);
    }
}
