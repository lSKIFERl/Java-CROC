package com.skifer.hw3.options;

import com.skifer.hw3.states.State;

/**
 * Транспорт, имеющий лопасти/крылья, приспособленный к полёту
 */
public interface Air {

    /**
     * Текущее состояние лопастей или крыльев
     *
     * @return состояние
     */
    State getWingState();

    /**
     * Состояние хвоста
     *
     * @return состояние
     */
    State getTailState();

    /**
     * Состояние лопастей или крыльев на текущий момент
     *
     * @param state состояние
     */
    void setWingState(State state);

    /**
     * Укажет на состояние хвоста
     *
     * @param state состояние
     */
    void setTailState(State state);

    /**
     * Чинит лопасти и крылья
     */
    void repairWings();

    /**
     * Чинит хвост
     */
    void repairTail();

}
