package com.skifer.hw3.machines;

import com.skifer.hw3.Transport;
import com.skifer.hw3.options.Ground;
import com.skifer.hw3.states.*;

public class Bike extends Transport implements Ground {

    private State wheelsState;

    /**
     * Велосипед
     * @param state Физическое состояние
     * @param rent Состояние аренды
     * @param id идентификатор
     * @param rentPrice цена за аренду
     * @param capacity вместимость
     * @param payload грузоподъемность
     * @param wheelsState состояние колёс
     */
    public Bike(State state, Rent rent, int id, int rentPrice, int capacity, String payload, State wheelsState) {
        super(state, rent, id, rentPrice, capacity, payload);
        this.wheelsState = wheelsState;
    }

    public Bike(int id, int rentPrice) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 1, "80 kg", State.PERFECT);
    }

    @Override
    public State getWheelsState() {
        return this.wheelsState;
    }

    @Override
    public void setWheelState(State state) {
        this.wheelsState = state;
        setSumState(state.ordinal());
    }

    @Override
    public void repairWheels() {
        setWheelState(State.PERFECT);
        repair();
    }
}


