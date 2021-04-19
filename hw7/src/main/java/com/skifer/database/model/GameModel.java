package com.skifer.database.model;

import java.util.Date;
import java.util.Objects;

/**
 * Моделька игори
 */
public class GameModel {

    /**
     * ID игры в приложении
     */
    private final Integer id;

    /**
     * Название игры
     */
    private String title;

    /**
     * Жанр игры
     */
    private String genre;

    /**
     * Цена игры
     */
    private Integer price;

    /**
     * Доступность по подписке
     */
    private Boolean gamePassAvailable;

    /**
     * Дата выхода
     */
    private Date releaseDate;

    /**
     * Конструктор игры
     * @param id уникальный номер
     * @param title название
     * @param genre жанр
     * @param price цена
     * @param gamePassAvailable доступность по подписке
     * @param releaseDate дата выхода
     */
    public GameModel(Integer id, String title, String genre, Integer price, Boolean gamePassAvailable, Date releaseDate) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.gamePassAvailable = gamePassAvailable;
        this.releaseDate = releaseDate;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean isGamePassAvailable() {
        return gamePassAvailable;
    }

    public void setGamePassAvailable(Boolean gamePassAvailable) {
        this.gamePassAvailable = gamePassAvailable;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "GameModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", gamePassAvailable=" + gamePassAvailable +
                ", releaseDate=" + releaseDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameModel)) return false;
        GameModel gameModel = (GameModel) o;
        return Objects.equals(getId(), gameModel.getId());
    }
}
