package com.skifer.hw3.machines;

import com.skifer.hw3.states.*;

public class Helicopter extends Plane {

    String takeOffAreaID;

    /**
     * Вертолёт
     *
     * @param state         физическое состояние
     * @param rent          статус аренды
     * @param id            идентификатор
     * @param rentPrice     стоимость аренды
     * @param capacity      вместительность
     * @param payload       грузоподъемность
     * @param enginesState  состояние двигателей
     * @param wheelsState   состояние колёс
     * @param engine        тип двигателя
     * @param brand         Марка автомобиля
     * @param capitanName   Имя капитана экипажа
     * @param tailState     состояние хвоста средства
     * @param wingState     состояние крыльев средства
     * @param takeOffAreaID идентификатор домашней стоянки
     */
    public Helicopter(State state, Rent rent, int id, int rentPrice, int capacity, String payload,
                      State enginesState, State wheelsState, Engine engine, String brand, String capitanName,
                      State tailState, State wingState, String takeOffAreaID) {
        super(state, rent, id, rentPrice, capacity, payload, enginesState, wheelsState, engine, brand,
                capitanName, tailState, wingState);
        this.takeOffAreaID = takeOffAreaID;
    }

    public Helicopter(int id, int rentPrice, String brand, String capitanName, String takeOffAreaID) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 5, "430 kg",
                State.PERFECT, State.PERFECT, Engine.DIESEL, "Boeing", capitanName,
                State.PERFECT, State.PERFECT, takeOffAreaID);
    }

    public String getTakeOffAreaID() {
        return takeOffAreaID;
    }
}
