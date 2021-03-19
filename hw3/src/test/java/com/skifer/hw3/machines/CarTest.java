package com.skifer.hw3.machines;

import com.skifer.hw3.states.Engine;
import com.skifer.hw3.states.Rent;
import com.skifer.hw3.states.State;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    Car car;

    @BeforeEach
    void install() {
        car = new Car(12, 8000, "UAZ");
    }

    /**
     * Балуемся с уроном. Проверяем урон по 1 части
     */
    @Test
    void damageAPiece() {
        car.setEnginesState(State.DAMAGED);
        assertEquals(State.DAMAGED, car.getEnginesState());
    }

    /**
     * Проверка на суммируемость урона
     */
    @Test
    void damageWhole() {
        car.setWheelState(State.SLIGHTLY_DAMAGED);
        assertEquals(State.DAMAGED.ordinal() + State.SLIGHTLY_DAMAGED.ordinal(), car.getState().ordinal());
    }

    /**
     * Проверка на невозможность взятия в аренду поломанной машины
     */
    @Test
    void rentable() {
        car.setWheelState(State.BROKEN);
        assertEquals(Rent.NOT_AVAILABLE, car.getRent());
    }

}
