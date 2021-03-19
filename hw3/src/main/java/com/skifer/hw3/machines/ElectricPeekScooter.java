package com.skifer.hw3.machines;

import com.skifer.hw3.options.Motorized;
import com.skifer.hw3.states.*;

public class ElectricPeekScooter extends PeekScooter implements Motorized {


    private State enginesState;
    private Engine engine;

    /**
     * Электро самокат
     *
     * @param state        физическое состояние
     * @param rent         статус аренды
     * @param id           идентификатор
     * @param rentPrice    стоимость аренды
     * @param parkingID    идентификатор парковки
     * @param engine       тип двигателя
     * @param enginesState состояние двигателя
     * @param capacity     вместимость
     * @param payload      грузоподъёмность
     * @param wheelsState  состояние колёс
     */
    public ElectricPeekScooter(State state, Rent rent, int id, int rentPrice, int capacity, String payload,
                               State wheelsState, int parkingID, State enginesState, Engine engine) {
        super(state, rent, id, rentPrice, capacity, payload, wheelsState, parkingID);
        this.enginesState = enginesState;
        this.engine = engine;
    }

    public ElectricPeekScooter(int id, int rentPrice, int parkingID) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 2, "180 kg", State.PERFECT, parkingID, State.PERFECT, Engine.ELECTRIC);
    }

    @Override
    public void setWheelState(State state) {
        super.setWheelState(state);
        setSumState(this.enginesState.ordinal() + this.getWheelsState().ordinal());
    }

    @Override
    public State getEnginesState() {
        return this.enginesState;
    }

    @Override
    public void setEnginesState(State state) {
        this.enginesState = state;
        setSumState(this.enginesState.ordinal() + getWheelsState().ordinal());
    }

    @Override
    public Engine getEngineType() {
        return this.engine;
    }

    @Override
    public void repairEngines() {
        setEnginesState(State.PERFECT);
        repair();
    }
}
