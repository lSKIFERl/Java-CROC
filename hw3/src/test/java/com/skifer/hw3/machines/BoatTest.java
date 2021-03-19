package com.skifer.hw3.machines;

import com.skifer.hw3.states.Rent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoatTest {

    Boat boat;

    @BeforeEach
    void install () {
        boat = new Boat(15, 150);
    }

    /**
     * Тест на невозможность аренды затопленной лодки
     */
    @Test
    void rentDrowned() {
        assertEquals(Rent.FREE, boat.getRent());
        boat.drowned(true);
        assertEquals(Rent.NOT_AVAILABLE, boat.getRent());
        boat.comeUpFromWater();
        assertEquals(Rent.FREE, boat.getRent());
    }

}
