package com.skifer.hw3.options;

import com.skifer.hw3.states.State;

/**
 * Транспорт, приспособленный к передвижению по воде
 */
public interface Water {

    /**
     * Текущее состояние палуб
     *
     * @return состояние палуб
     */
    State getDecksState();

    /**
     * Покажет потопленно ли судно
     *
     * @return состояние
     */
    boolean isDrowned();

    /**
     * Установит или снимет статус утонувшего
     *
     * @param state статус
     */
    void drowned(boolean state);

    /**
     * Установит текущее состояние палуб
     *
     * @param state палубы
     */
    void setDecksState(State state);

    /**
     * Чинит палубы судна
     */
    void repairDecks();

    /**
     * Поднимает затопленное судно из воды
     */
    void comeUpFromWater();

}
