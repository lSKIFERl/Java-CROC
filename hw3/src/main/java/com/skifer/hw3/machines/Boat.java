package com.skifer.hw3.machines;

import com.skifer.hw3.Transport;
import com.skifer.hw3.states.*;
import com.skifer.hw3.options.Water;

public class Boat extends Transport implements Water {

    private State decksState;
    private boolean drowned;

    /**
     * Простая лодочка
     *
     * @param state      физическое состояние
     * @param rent       статус аренды
     * @param id         идентификатор
     * @param rentPrice  стоимость аренды
     * @param drowned    статус "утонула" или "нет"
     * @param capacity   вместительность
     * @param payload    грузоподъёмность
     * @param decksState состояние палуб
     */
    public Boat(State state, Rent rent, int id, int rentPrice, int capacity, String payload, State decksState, boolean drowned) {
        super(state, rent, id, rentPrice, capacity, payload);
        this.decksState = decksState;
        this.drowned = drowned;
    }

    public Boat(int id, int rentPrice) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 2, "220 kg", State.PERFECT, false);
    }

    @Override
    public State getDecksState() {
        return this.decksState;
    }

    @Override
    public boolean isDrowned() {
        return this.drowned;
    }

    @Override
    public void drowned(boolean state) {
        this.drowned = state;
        if (state) {
            setState(State.SLIGHTLY_DAMAGED);
            setRent(Rent.NOT_AVAILABLE);
        } else {
            setRent(Rent.FREE);
        }
    }

    @Override
    public void setDecksState(State state) {
        this.decksState = state;
        setSumState(getDecksState().ordinal());
    }

    @Override
    public void repairDecks() {
        setDecksState(State.PERFECT);
        repair();
    }

    @Override
    public void comeUpFromWater() {
        drowned(false);
    }

}
