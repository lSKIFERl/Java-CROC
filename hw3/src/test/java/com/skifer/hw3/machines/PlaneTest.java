package com.skifer.hw3.machines;

import com.skifer.hw3.states.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlaneTest {

    Plane plane;

    @BeforeEach
    void install() {
        plane = new Plane(14, 7000000, "Flex", "Richardo_Milos");
    }

    /**
     * Проверка работы суммарного урона
     */
    @Test
    void damage() {
        plane.setWingState(State.ACCEPTABLE);
        plane.setTailState(State.ACCEPTABLE);
        plane.setWheelState(State.DAMAGED);
        assertEquals(State.OOH_THAT_HAS_GOT_A_LOT_OF_DAMAGE, plane.getState());
    }

    /**
     * Проверка изменения стоимости аренды при повреждении
     */
    @Test
    void rentPrice() {
        assertTrue(7000000 == plane.getRentPrice());
        plane.setEnginesState(State.HARDLY_DAMAGED);
        assertFalse(7000000 == plane.getRentPrice());
        plane.repairEngines();
        assertTrue(7000000 == plane.getRentPrice());
    }
}
