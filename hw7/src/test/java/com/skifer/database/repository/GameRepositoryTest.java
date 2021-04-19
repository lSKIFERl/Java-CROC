package com.skifer.database.repository;

import com.skifer.database.dbprovider.DataBaseProvider;
import com.skifer.database.service.DataBaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class GameRepositoryTest {

    @BeforeEach
    void setUp() {
        DataBaseProvider provider = null;
        GameRepository repository = null;
        try {
            provider = new DataBaseProvider();
            repository = new GameRepository(provider.getDataSource(), "Games");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        DataBaseService service = new DataBaseService(repository);
    }

    @Test
    void selectAll() {
    }

    @Test
    void selectWhere() {
    }

    @Test
    void deleteWhere() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void insert() {
    }

    @Test
    void dropTable() {
    }
}