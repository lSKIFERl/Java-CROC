package com.skifer.database.service;

import com.skifer.database.model.GameModel;
import com.skifer.database.repository.GameRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Сервис для нашей БД
 */
public class DataBaseService {

    /**
     * Репозиторий для работы с моделью игры
     */
    private final GameRepository repository;

    public DataBaseService(GameRepository repository) {
        this.repository = repository;
    }

    public void dropTable(String table) throws SQLException {
        repository.dropTable(table);
    }

    public List<GameModel> selectAll() throws SQLException {
        return repository.selectAll();
    }

    public <T> List<GameModel> selectWhere(List<String> columns, List<T> items) throws SQLException {
        return repository.selectWhere(columns, items);
    }

    public void delete(GameModel game) throws SQLException {
        repository.delete(game);
    }

    public <T> void deleteWhere(List<String> itemTypes, List<T> items) throws SQLException {
        repository.deleteWhere(itemTypes, items);
    }

    public void update(GameModel game) throws SQLException {
        repository.update(game);
    }

    public void insert(GameModel game) throws SQLException {
        repository.insert(game);
    }

}
