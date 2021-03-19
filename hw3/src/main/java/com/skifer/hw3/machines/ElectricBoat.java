package com.skifer.hw3.machines;

import com.skifer.hw3.options.Motorized;
import com.skifer.hw3.states.*;

public class ElectricBoat extends Boat implements Motorized {

    private State enginesState;
    private Engine engine;

    /**
     * Простая лодочка
     *
     * @param state        физическое состояние
     * @param rent         статус аренды
     * @param id           идентификатор
     * @param rentPrice    стоимость аренды
     * @param capacity     вместительность
     * @param payload      грузоподъёмность
     * @param decksState   состояние палуб
     * @param drowned      статус "утонула" или "нет"
     * @param engine       тип мотора
     * @param enginesState состояние мотора
     */
    public ElectricBoat(State state, Rent rent, int id, int rentPrice, int capacity, String payload,
                        State decksState, boolean drowned, State enginesState, Engine engine) {
        super(state, rent, id, rentPrice, capacity, payload, decksState, drowned);
        this.enginesState = enginesState;
        this.engine = engine;
    }

    public ElectricBoat(int id, int rentPrice, Engine engine) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 4, "500 kg",
                State.PERFECT, false, State.PERFECT, Engine.DIESEL);
    }

    @Override
    public State getEnginesState() {
        return this.enginesState;
    }

    @Override
    public void setEnginesState(State state) {
        this.enginesState = state;
        setSumState(this.enginesState.ordinal() + this.getDecksState().ordinal());
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

    @Override
    public void setDecksState(State state) {
        super.setDecksState(state);
        setSumState(getDecksState().ordinal());
    }
}
