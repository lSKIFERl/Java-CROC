package com.skifer.hw3.machines;

import com.skifer.hw3.states.Rent;
import com.skifer.hw3.states.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BikeTest {

    Bike bike;

    @BeforeEach
    void install() {
        bike = new Bike(18, 50);
    }

    /**
     * Клиент сам сломал, сам починил велосипед
     */
    @Test
    void broke() {
        bike.setState(State.BROKEN);
        assertEquals(State.BROKEN, bike.getState());
        bike.setWheelState(State.PERFECT);
        assertEquals(State.PERFECT, bike.getState());
    }

    /**
     * Проверка на доступности брони сломанного велика
     */
    @Test
    void rentBroken() {
        bike.setState(State.BROKEN);
        assertEquals(Rent.NOT_AVAILABLE, bike.getRent());
    }
}
