package com.skifer.hw3.machines;

import com.skifer.hw3.states.*;

public class Yacht extends ElectricBoat {

    private String owner;
    private int dockID;

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
     * @param enginesState состояние мотора
     * @param engine       тип мотора
     * @param owner        владелец
     * @param dockID       идентификатор доков
     */
    public Yacht(State state, Rent rent, int id, int rentPrice, int capacity, String payload,
                 State decksState, boolean drowned, State enginesState, Engine engine, String owner, int dockID) {
        super(state, rent, id, rentPrice, capacity, payload, decksState, drowned, enginesState, engine);
        this.owner = owner;
        this.dockID = dockID;
    }

    public Yacht(int id, int rentPrice, String owner, int dockID) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 12, "2 t",
                State.PERFECT, false, State.PERFECT, Engine.GAS, owner, dockID);
    }

    public int getDockID() {
        return dockID;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
