package task;

import exceptions.IllegalInteraptException;
import exceptions.TooLongStringException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    Task task;

    @BeforeEach
    void setUp() {
        try {
            task = new Task("test Name" , "test Description", 0);
        } catch (TooLongStringException | IllegalInteraptException e) {
            e.printStackTrace();
        }
    }

    @Test
    void setTaskName() {
        assertThrows(
                TooLongStringException.class ,
                () -> task.setTaskName("nyooooooooooooooooooooooooooooom"));
        assertDoesNotThrow(() -> task.setTaskName("Колечко"));
    }

    @Test
    void setTaskDescription() {
        assertDoesNotThrow(() -> task.setTaskDescription("Найти колечко"));
    }

    @Test
    void setExecutor() {
        assertThrows(
                TooLongStringException.class ,
                () -> task.setExecutor("Король-чародей Ангмара"));
        assertDoesNotThrow(() -> task.setExecutor("Назгул"));
    }

    @Test
    void setStatus() {
        task.setStatus(Status.IN_PROGRESS);
        assertThrows(IllegalInteraptException.class, () -> task.setExecutor("Jungle"));
        assertThrows(IllegalInteraptException.class, () -> task.setTaskName("Busy Earning"));
        assertThrows(IllegalInteraptException.class, () -> task.setTaskDescription("So you come a long way\n" +
                "But you'll never have me\n" +
                "Never have things for a normal life\n" +
                "It's time, too busy earnin'\n" +
                "You can't get enough"));
    }

}