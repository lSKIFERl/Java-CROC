package com.skifer.database.service;

import com.skifer.database.dbprovider.DataBaseProvider;
import com.skifer.database.model.GameModel;
import com.skifer.database.repository.GameRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseServiceTest {

    DataBaseProvider provider;
    GameRepository repository;
    DataBaseService service;

    GameModel skyrim = new GameModel(1, "Skyrim", "RPG",
            1399,true, new Date(2016-1900, Calendar.OCTOBER, 28));
    GameModel ut2004 = new GameModel(2, "Unreal Tournament 2004", "Shooter",
            249, true, new Date(2004-1900, Calendar.MARCH, 16));
    GameModel minecraft = new GameModel(3, "Minecraft Java Edition", "Sandbox",
            2199, false, new Date(2011-1900, Calendar.OCTOBER, 18));

    @BeforeEach
    void setUp() {
        try {
            provider = new DataBaseProvider();
            repository = new GameRepository(provider.getDataSource(), "Games");
            repository.dropTable("Games");
            repository = new GameRepository(provider.getDataSource(), "Games");
            service = new DataBaseService(repository);
            service.insert(skyrim);
            service.insert(ut2004);
            service.insert(minecraft);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Простое добавление в базу.
     */
    @Test
    void selectAll() {

        List<GameModel> gameObjectsList = new ArrayList<>();
        List<GameModel> expectedGameList;

        gameObjectsList.add(skyrim);
        gameObjectsList.add(ut2004);
        gameObjectsList.add(minecraft);

        try {
            expectedGameList = service.selectAll();
            Assertions.assertEquals(expectedGameList, gameObjectsList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Тест на выборку с 1 параметром для WHERE и несколькими
     */
    @Test
    void selectWhere() {
        try {
            Assertions.assertEquals(service.selectWhere(Arrays.asList("genre"), Arrays.asList("RPG")), Arrays.asList(skyrim));
            Assertions.assertEquals(service.selectWhere(Arrays.asList("gamePassAvailable"), Arrays.asList(true)),
                    Arrays.asList(skyrim, ut2004));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Тестна удаление по параметрам
     */
    @Test
    void deleteWhere() {
        List<GameModel> gameList = Arrays.asList(skyrim, ut2004);
        try {
            service.deleteWhere(Arrays.asList("genre", "price"), Arrays.asList("Sandbox", 2199));
            Assertions.assertEquals(service.selectAll(), gameList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Тест на удаление объекта
     */
    @Test
    void delete() {
        List<GameModel> gameList = Arrays.asList(skyrim, ut2004);
        try {
            repository.delete(minecraft);
            Assertions.assertEquals(service.selectAll(), gameList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * Тест на обновление ячейки в таблице
     */
    @Test
    void update() {
        skyrim.setTitle("Skyrim ULTRA SPECIAL LEGENDARY REMASTERED EDITION FOR playstation 3");
        try {
            repository.update(skyrim);
            Assertions.assertEquals(service.selectWhere(Arrays.asList("genre"), Arrays.asList("RPG")), Arrays.asList(skyrim));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Тест на вставку новых значений в таблицу
     */
    @Test
    void insert() {
        GameModel subnautica = new GameModel(4, "Subnautica", "Sandbox",
                799, false, new Date(2014-1900, Calendar.DECEMBER, 16));
        try {
            service.insert(subnautica);
            Assertions.assertEquals(service.selectWhere(Arrays.asList("id"), Arrays.asList(4)), Arrays.asList(subnautica));
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
            service.dropTable("Games");
            Assertions.assertThrows(SQLException.class, () -> service.selectAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}