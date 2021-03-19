package com.skifer.hw3;

import com.skifer.hw3.states.*;

/**
 * Описывает основную модель поведения и состояния для любого транспорта компании, а также его стоимость и номер
 */
public abstract class Transport {

    /**
     * Показывает физическое состояние транспорта
     */
    private State state;

    /**
     * Суммарный показатель физического состояния транспорта
     */
    private int sumState;

    /**
     * Показывает статус аренды транспорта
     */
    private Rent rent;

    /**
     * Идентификатор транспорта
     */
    private int id;

    /**
     * Цена на аренду транспорта
     */
    private int rentPrice;

    /**
     * Начальная цена на аренду
     */
    private int defaultPrice;

    /**
     * Вместительность
     */
    private int capacity;

    /**
     * Грузоподъёмность транспорта
     */
    private String payload;

    /**
     * Конструктор с типичными для всего арендуемого транспорта параметрами
     * @param state физическое состояние
     * @param rent статус аренды
     * @param id идентификатор
     * @param rentPrice цена за аренду
     * @param capacity Вместимость
     * @param payload грузоподъемность
     */
    public Transport(State state, Rent rent, int id, int rentPrice, int capacity, String payload) {
        this.state = state;
        this.rent = rent;
        this.id = id;
        this.rentPrice = rentPrice;
        this.defaultPrice = rentPrice;
        this.capacity = capacity;
        this.payload = payload;
    }

    public int getSumState() {
        return sumState;
    }

    protected void setSumState(int sumState) {
        this.sumState = sumState;
        setState(State.values()[sumState]);
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getPayload() {
        return payload;
    }

    /**
     * Устанавливает состояние в зависимости от состояния компонентов
     */
    public void repair() {
        if (this.sumState > 5) {
            setState(State.BROKEN);
        } else {
            setState(State.values()[this.sumState]);
        }
    }

    /**
     * Вернёт цену на аренду
     * @return цена
     */
    public int getRentPrice() {
        return rentPrice;
    }

    /**
     * Установит цену на аренду
     * @param rentPrice цена
     */
    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    /**
     * Вернёт текущее физическое состояние транспорта
     * @return Состояние
     */
    public State getState() {
        return state;
    }

    /**
     * Установит текущее физическое состояние транспорта
     * @param state состояние
     */
    public void setState(State state) {
        this.state = state;
        if(state.ordinal() > 2)
            setRent(Rent.NOT_AVAILABLE);
        else { setRent(Rent.FREE); }
        setRentPrice(defaultPrice - (defaultPrice / (state.ordinal() + 5)) * state.ordinal());
    }

    /**
     * Вернёт статус арендованности транспорта
     * @return статус
     */
    public Rent getRent() {
        return rent;
    }

    /**
     * Установит текущий статус арендованности транспорта
     * @param rent статус
     */
    public void setRent(Rent rent) {
        this.rent = rent;
    }
}
