package com.skifer.city.database.service;

import com.skifer.city.database.model.AccidentModel;
import com.skifer.city.database.provider.DerbyProvider;
import com.skifer.city.database.repository.AccidentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class DerbyServiceTest {

    DerbyProvider provider;
    AccidentRepository repository;
    DerbyService service;

    List<AccidentModel> rows = new ArrayList<>();
    AccidentModel row1;
    AccidentModel row2;
    AccidentModel row3;

    @BeforeEach
    void setUp() throws IOException, SQLException {
        provider = new DerbyProvider();
        repository = new AccidentRepository(provider.getDataSource(), "SimpleDay");
        service = new DerbyService(repository);
        service.dropTable();
        repository = new AccidentRepository(provider.getDataSource(), "SimpleDay");
        service = new DerbyService(repository);

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

    @Test
    void selectAll() throws SQLException {
        List<AccidentModel> expectedRows;

        try {
            expectedRows = service.selectAll();
            Assertions.assertEquals(expectedRows, rows);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }    }

    @Test
    void selectWhere() {

    }

    @Test
    void delete() {
    }

    @Test
    void deleteWhere() {
    }

    @Test
    void update() {
    }

    @Test
    void insert() {
    }
}