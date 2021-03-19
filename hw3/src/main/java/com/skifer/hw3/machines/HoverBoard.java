package com.skifer.hw3.machines;

import com.skifer.hw3.Transport;
import com.skifer.hw3.options.Ground;
import com.skifer.hw3.options.Motorized;
import com.skifer.hw3.states.*;

public class HoverBoard extends Transport implements Ground, Motorized {

    private State enginesState, wheelsState;

    private Engine engine;

    public HoverBoard(State state, Rent rent, int id, int rentPrice, int capacity, String payload,
                      State enginesState, State wheelsState, Engine engine) {
        super(state, rent, id, rentPrice, capacity, payload);
        this.enginesState = enginesState;
        this.wheelsState = wheelsState;
        this.engine = engine;
    }

    public HoverBoard(int id, int rentPrice) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 1, "75 kg", State.PERFECT, State.PERFECT, Engine.ELECTRIC);
    }

    @Override
    public State getEnginesState() {
        return enginesState;
    }

    @Override
    public void setEnginesState(State enginesState) {
        this.enginesState = enginesState;
        setSumState(this.enginesState.ordinal() + this.wheelsState.ordinal());
    }

    @Override
    public State getWheelsState() {
        return wheelsState;
    }

    @Override
    public void setWheelState(State state) {
        this.wheelsState = state;
        setSumState(this.enginesState.ordinal() + this.wheelsState.ordinal());
    }

    @Override
    public void repairWheels() {
        setWheelState(State.PERFECT);
        repair();
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
