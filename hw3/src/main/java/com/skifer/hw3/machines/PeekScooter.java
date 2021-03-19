package com.skifer.hw3.machines;

import com.skifer.hw3.states.*;

public class PeekScooter extends Bike {

    private int parkingID;

    /**
     * Самокат
     *
     * @param state       физическое состояние
     * @param rent        статус аренды
     * @param id          идентификатор
     * @param rentPrice   стоимость аренды
     * @param parkingID   идентификатор парковки
     * @param capacity    вместимость
     * @param payload     грузоподъемность
     * @param wheelsState состояние колёс
     */
    public PeekScooter(State state, Rent rent, int id, int rentPrice, int capacity,
                       String payload, State wheelsState, int parkingID) {
        super(state, rent, id, rentPrice, capacity, payload, wheelsState);
        this.parkingID = parkingID;
    }

    public PeekScooter(int id, int rentPrice, int parkingID) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 1, "100 kg", State.PERFECT, parkingID);
    }

    public int getParkingID() {
        return parkingID;
    }

    public void setParkingID(int parkingID) {
        this.parkingID = parkingID;
    }

}
