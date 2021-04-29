package com.skifer.city.database.service;

import com.skifer.city.database.model.AccidentModel;
import com.skifer.city.database.provider.DataSourceProvider;
import com.skifer.city.database.repository.AccidentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

class AccidentServiceTest {

    DataSourceProvider provider;
    AccidentRepository repository;
    AccidentService service;

    List<AccidentModel> rows = new ArrayList<>();
    AccidentModel row1;
    AccidentModel row2;
    AccidentModel row3;

    @BeforeEach
    void setUp() throws IOException, SQLException {
        provider = new DataSourceProvider();
        repository = new AccidentRepository(provider.getDataSource(), "SimpleDay");
        service = new AccidentService(repository);
        service.dropTable();
        repository = new AccidentRepository(provider.getDataSource(), "SimpleDay");
        service = new AccidentService(repository);

        row1 = new AccidentModel(1, new Time(5, 10, 0), "ЮМР", "Победы", 4, false);
        service.insert(row1);
        rows.add(row1);

        row2 = new AccidentModel(2, new Time(15, 0, 0), "Центральный", "Ставропольской", 7, true);
        service.insert(row2);
        rows.add(row2);

        row3 = new AccidentModel(3, new Time(18, 12, 27), "Пашковка", "8 Марта", 0, false);
        service.insert(row3);
        rows.add(row3);
    }

    /**
     * Получение всех элементов
     */
    @Test
    void selectAll() {
        List<AccidentModel> expectedRows;

        try {
            expectedRows = service.selectAll();
            Assertions.assertEquals(expectedRows, rows);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }    }

    /**
     * Получение объекта по столбцам
     */
    @Test
    void selectWhere() {
        try {
            Assertions.assertEquals(service.selectWhere(Arrays.asList("street"), Arrays.asList("8 Марта")), Arrays.asList(row3));
            Assertions.assertEquals(service.selectWhere(Arrays.asList("accident"), Arrays.asList(false)),
                    Arrays.asList(row1, row3));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Удаление элемента
     */
    @Test
    void delete() {
        List<AccidentModel> accidents = Arrays.asList(row1, row2);
        try {
            repository.delete(row3);
            Assertions.assertEquals(service.selectAll(), accidents);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Тест на удаление элемента по значениям в столбцах
     */
    @Test
    void deleteWhere() {
        List<AccidentModel> gameList = Arrays.asList(row1, row2);
        try {
            service.deleteWhere(Arrays.asList("region", "time"), Arrays.asList("Пашковка", new Time(18, 12, 27)));
            Assertions.assertEquals(service.selectAll(), gameList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Тест на обновление элемента
     */
    @Test
    void update() {
        row1.setPoints(10);
        try {
            service.update(row1);
            Assertions.assertEquals(service.selectWhere(Arrays.asList("points"), Arrays.asList(10)), Arrays.asList(row1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Тест на вставку элементов
     */
    @Test
    void insert() {
        AccidentModel row4 = new AccidentModel(4, new Time(new Date().getTime()), "Прикубанский округ", "Мира", 9, false);
        try {
            service.insert(row4);
            Assertions.assertEquals(service.selectWhere(Arrays.asList("id"), Arrays.asList(4)), Arrays.asList(row4));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Удаляем табличку и пытаемся к ней обратиться, чтобы пропинговать её
     */
    @Test
    void dropTable() {
        try {
            service.dropTable();
            Assertions.assertThrows(SQLException.class, () -> service.selectAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}