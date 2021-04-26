package com.skifer.city.xmlformatter.model.accident;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 * Класс для регистрации максимального колличества ДТП и его времени
 */
@XmlRootElement(name = "accident")
public class Accident {

    /**
     * Максимальное колличество ДТП за день
     */
    @XmlElement(name = "Максимальное количество ДТП")
    private Integer maxAccidentCount;

    /**
     * Час максимального колличества ДТП
     */
    @XmlElement(name = "Час максимального количества ДТП")
    private String maxAccidentHour;

    /**
     * Класс для регистрации максимального колличества ДТП и его времени
     */
    public Accident(Integer maxAccidentCount, Time maxAccidentHour) {
        this.maxAccidentCount = maxAccidentCount;
        this.maxAccidentHour = new SimpleDateFormat("HH").format(maxAccidentHour);
    }

    public Integer getMaxAccidentCount() {
        return maxAccidentCount;
    }

    public void setMaxAccidentCount(Integer maxAccidentCount) {
        this.maxAccidentCount = maxAccidentCount;
    }

    public String getMaxAccidentHour() {
        return maxAccidentHour;
    }

    public void setMaxAccidentHour(Time maxAccidentHour) {
        this.maxAccidentHour = new SimpleDateFormat("HH").format(maxAccidentHour);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Accident)) return false;
        Accident accident = (Accident) o;
        return Objects.equals(getMaxAccidentCount(), accident.getMaxAccidentCount()) && Objects.equals(getMaxAccidentHour(), accident.getMaxAccidentHour());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaxAccidentCount(), getMaxAccidentHour());
    }
}
