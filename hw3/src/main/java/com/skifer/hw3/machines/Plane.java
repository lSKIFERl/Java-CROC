package com.skifer.hw3.machines;

import com.skifer.hw3.options.Air;
import com.skifer.hw3.states.*;

public class Plane extends Car implements Air {

    String capitanName;
    State tailState, wingState;

    /**
     * Самолёт
     *
     * @param state        физическое состояние
     * @param rent         статус аренды
     * @param id           идентификатор
     * @param rentPrice    стоимость аренды
     * @param capacity     вместительность
     * @param payload      грузоподъемность
     * @param enginesState состояние двигателей
     * @param wheelsState  состояние колёс
     * @param engine       тип двигателя
     * @param brand        Марка автомобиля
     * @param capitanName  Имя капитана экипажа
     * @param tailState    состояние хвоста средства
     * @param wingState    состояние крыльев средства
     */
    public Plane(State state, Rent rent, int id, int rentPrice, int capacity, String payload,
                 State enginesState, State wheelsState, Engine engine, String brand, String capitanName,
                 State tailState, State wingState) {
        super(state, rent, id, rentPrice, capacity, payload, enginesState, wheelsState, engine, brand);
        this.capitanName = capitanName;
        this.tailState = tailState;
        this.wingState = wingState;
    }

    public Plane(int id, int rentPrice, String brand, String capitanName) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 8, "900 kg", State.PERFECT, State.PERFECT,
                Engine.DIESEL, "Boeing", capitanName, State.PERFECT, State.PERFECT);
    }


    @Override
    public State getWingState() {
        return this.wingState;
    }

    @Override
    public State getTailState() {
        return this.tailState;
    }

    @Override
    public void setWingState(State state) {
        this.wingState = state;
        setSumState(state.ordinal() + getWheelsState().ordinal() + this.tailState.ordinal());
    }

    public String getCapitanName() {
        return capitanName;
    }

    @Override
    public void setTailState(State state) {
        this.tailState = state;
        setSumState(state.ordinal() + getWheelsState().ordinal() + this.wingState.ordinal());
    }

    @Override
    public void setWheelState(State state) {
        super.setWheelState(state);
        setSumState(state.ordinal() + getWingState().ordinal() + this.tailState.ordinal());
    }

    @Override
    public void repairWings() {
        setWingState(State.PERFECT);
        repair();
    }

    @Override
    public void repairTail() {
        setTailState(State.PERFECT);
        repair();
    }

    public void setCapitanName(String capitanName) {
        this.capitanName = capitanName;
    }
}
