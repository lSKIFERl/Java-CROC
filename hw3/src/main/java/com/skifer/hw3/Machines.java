package com.skifer.hw3;

class Bike extends Transport implements Ground {

    private int defaultWheelsCount;

    public Bike(State state, Rent rent, int id, int rentPrice, int defaultWheelsCount) {
        super(state, rent, id, rentPrice);
        this.defaultWheelsCount = defaultWheelsCount;
    }

    @Override
    public int getWheelCount() {
        return 0;
    }

    @Override
    public int getDefaultWheelCount() {
        return 0;
    }

    @Override
    public void setWheelCount(int count) {

    }

    @Override
    public void setDefaultWheelCount(int count) {

    }
}


class HoverBoard extends Transport implements Ground, Motorized {

    public HoverBoard(State state, Rent rent, int id, int rentPrice) {
        super(state, rent, id, rentPrice);
    }

    @Override
    public int getWheelCount() {
        return 0;
    }

    @Override
    public int getDefaultWheelCount() {
        return 0;
    }

    @Override
    public void setWheelCount(int count) {

    }

    @Override
    public void setDefaultWheelCount(int count) {

    }

    @Override
    public int getDefaultNumberOfEngines() {
        return 0;
    }

    @Override
    public int getNumberOfEngines() {
        return 0;
    }

    @Override
    public void setNumberOfEngines(int count) {

    }

    @Override
    public void setDefaultNumberOfEngines(int count) {

    }

    @Override
    public void setEngineType(Engine type) {

    }
}