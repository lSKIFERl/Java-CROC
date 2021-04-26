package com.skifer.city.database.model;

import java.sql.Time;
import java.util.Objects;

/**
 * Класс модели событий изменения загруженности на дорогах и ДТП
 */
public class AccidentModel {

    /**
     * ID события
     */
    private final Integer id;

    /**
     * Время изменения загруженности или регистрации инцидента
     */
    private Time time;

    /**
     * Район города
     */
    private String region;

    /**
     * Улица
     */
    private String street;

    /**
     * Пробки от 0 до 10
     */
    private Integer points;

    /**
     * Факт регистрации ДТП
     */
    private Boolean accident;

    /**
     * Класс модели событий изменения загруженности на дорогах и ДТП
     */
    public AccidentModel(Integer id, Time time, String region, String street, Integer points, Boolean accident) {
        if(points < 0 || points > 10)
            throw new IllegalArgumentException("Неправильный формат баллов пробки (0-10)");
        this.id = id;
        this.time = time;
        this.region = region;
        this.street = street;
        this.points = points;
        this.accident = accident;
    }

    /**
     * Класс модели событий изменения загруженности на дорогах и ДТП
     */
    public AccidentModel(Integer id, Time time, String region, String street, Integer points) {
        this(id, time, region, street, points, false);
    }

    public Integer getId() {
        return id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Boolean getAccident() {
        return accident;
    }

    public void setAccident(Boolean accident) {
        this.accident = accident;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccidentModel)) return false;
        AccidentModel that = (AccidentModel) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTime(), that.getTime()) && Objects.equals(getRegion(), that.getRegion()) && Objects.equals(getStreet(), that.getStreet()) && Objects.equals(getPoints(), that.getPoints()) && Objects.equals(getAccident(), that.getAccident());
    }

    @Override
    public String toString() {
        return "AccidentModel{" +
                "id=" + id +
                ", time=" + time +
                ", region='" + region + '\'' +
                ", street='" + street + '\'' +
                ", points=" + points +
                ", accident=" + accident +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTime(), getRegion(), getStreet(), getPoints(), getAccident());
    }
}
