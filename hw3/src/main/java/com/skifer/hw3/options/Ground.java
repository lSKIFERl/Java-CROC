package com.skifer.hw3.options;

import com.skifer.hw3.states.State;

/**
 * Интерфейс для транспорта, имеющего колёса
 */
public interface Ground{

    /**
     * Вернёт текущее состояние колёс
     * @return колёса
     */
    State getWheelsState();

    /**
     * Настоящее состояние колёс
     * @param state состояние
     */
    void setWheelState(State state);

    /**
     * Чинит колёса
     */
    void repairWheels();

}

