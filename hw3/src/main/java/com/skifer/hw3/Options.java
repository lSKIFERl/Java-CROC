package com.skifer.hw3;

/**
 * Интерфейс для транспорта, имеющего колёса
 */
interface Ground{

    /**
     * Текущее количество колёс
     * @return колёса
     */
    int getWheelCount();

    /**
     * Вернёт нормальное количество колёс
     * @return колёса
     */
    int getDefaultWheelCount();

    /**
     * Настоящее количество колёс
     * @param count колёса
     */
    void setWheelCount(int count);

}

/**
 * Транспорт, имеющий лопасти/крылья, приспособленный к полёту
 */
interface Air{

    /**
     * Текущее количество неповреждённых лопастей или крыльев
     * @return лопасти или крылья
     */
    int getWingCount();

    /**
     * Нормальное количество крыльев/лопастей
     * @return количество крыльев/лопастей
     */
    int getDefaultWingCount();

    /**
     * Состояние хвоста
     * @return состояние
     */
    State getTailState();

    /**
     * Количество лопастей или крыльев на текущий момент
     * @param count крылья/лопасти
     */
    void setWingCount(int count);

    /**
     * Укажет на состояние хвоста. Повреждённый хвост уменьшит маневренность транспорта
     * @param state состояние
     */
    void setTailState(State state);

}

/**
 * Транспорт, приспособленный к передвижению по воде
 */
interface Water{

    /**
     * Нормальное количество палуб
     * @return количество палуб
     */
    int getDefaultNumberOfDecks();

    /**
     * Текущее количество палуб
     * @return количество палуб
     */
    int getNumberOfDecks();

    /**
     * Покажет потопленно ли судно
     * @return состояние
     */
    boolean isDrowned();

    /**
     * Установит или снимет статус утонувшего
     * @param state статус
     */
    void drowned(boolean state);

    /**
     * Установит текущее количество незатопленных/неповреждённых палуб
     * @param decks палубы
     */
    void setNumberOfDecks(int decks);

}

/**
 * Моторизованный транспорт
 */
interface Motorized{

    /**
     * Вернёт нормальное количество двигателей
     * @return количество двигателей
     */
    int getDefaultNumberOfEngines();

    /**
     * Вернёт текущее количество двигателей/моторов
     * @return двигатели/моторы
     */
    int getNumberOfEngines();

    /**
     * Установит текущее количество моторов/двигателей
     * @param count количество двигателей/моторов
     */
    void setNumberOfEngines(int count);

    /**
     * Вернёт тип двигателя
     * @return тип двигателя
     */
    Engine getEngineType();

}