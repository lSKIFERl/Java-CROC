package com.skifer.hw3.machines;

import com.skifer.hw3.states.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YachtTest {

    Yacht yacht;

    @BeforeEach
    void install () {
        yacht = new Yacht(10, 9999999, "Abromovich", 10450);
    }

    /**
     * Проверка на невозможность арендовать дырявую яхту
     */
    @Test
    void broken () {
        yacht.setDecksState(State.SLIGHTLY_DAMAGED);
        yacht.setEnginesState(State.DAMAGED);
        assertEquals(State.OOH_THAT_HAS_GOT_A_LOT_OF_DAMAGE, yacht.getState());
    }

}
