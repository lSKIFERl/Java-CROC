package com.skifer.hw3.machines;

import com.skifer.hw3.states.*;

public class Car extends HoverBoard {

    private String brand;

    /**
     * Ааааавтомобиль!
     *
     * @param state        физическое состояние
     * @param rent         статус аренды
     * @param id           идентификатор
     * @param rentPrice    стоимость аренды
     * @param brand        Марка автомобиля
     * @param engine       тип двигателя
     * @param capacity     вместительность
     * @param payload      грузоподъемность
     * @param enginesState состояние двигателей
     * @param wheelsState  состояние колёс
     */
    public Car(State state, Rent rent, int id, int rentPrice, int capacity, String payload,
               State enginesState, State wheelsState, Engine engine, String brand) {
        super(state, rent, id, rentPrice, capacity, payload, enginesState, wheelsState, engine);
        this.brand = brand;
    }

    public Car(int id, int rentPrice, String brand) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 5, "600 kg", State.PERFECT, State.PERFECT, Engine.PETROL, brand);
    }

    public String getBrand() {
        return brand;
    }
}
