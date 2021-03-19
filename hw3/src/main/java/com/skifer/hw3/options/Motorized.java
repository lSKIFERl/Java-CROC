package com.skifer.hw3.options;

import com.skifer.hw3.states.Engine;
import com.skifer.hw3.states.State;

/**
 * Моторизованный транспорт
 */
public interface Motorized {

    /**
     * Вернёт текущее состояние двигателей/моторов
     *
     * @return двигатели/моторы
     */
    State getEnginesState();

    /**
     * Установит текущее состояние моторов/двигателей
     *
     * @param state состояние двигателей/моторов
     */
    void setEnginesState(State state);

    /**
     * Вернёт тип двигателя
     *
     * @return тип двигателя
     */
    Engine getEngineType();

    /**
     * Чинит двигатели
     */
    void repairEngines();

}
