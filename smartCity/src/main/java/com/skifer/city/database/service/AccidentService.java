package com.skifer.city.database.service;

import com.skifer.city.database.model.AccidentModel;
import com.skifer.city.database.repository.AccidentRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Класс-сервис для работы с моделью пробок и ДТП
 */
public class AccidentService {

    /**
     * Репозиторий для работы с моделью пробок и ДТП
     */
    private final AccidentRepository repository;

    /**
     * Класс-сервис для работы с моделью пробок и ДТП
     */
    public AccidentService(AccidentRepository repository) {
        this.repository = repository;
    }

    /**
     * Найти все элементы в таблице
     * @return Список всех записей о ДТП и пробках на дорогах
     * @throws SQLException Возникает при неправильном обращении к БД
     */
    public List<AccidentModel> selectAll() throws SQLException {
        return repository.selectAll();
    }

    /**
     * Найти все элементы в столбцах {@param columns} со значениями {@param items}
     * @param columns искомые столбцы
     * @param items искомые значения
     * @return Список записей о ДТП и пробках на дорогах
     * @throws SQLException Возникает при неправильном обращении к БД
     */
    public <T> List<AccidentModel> selectWhere(List<String> columns, List<T> items) throws SQLException {
        return repository.selectWhere(columns, items);
    }

    /**
     * Удаление таблицы
     */
    public void dropTable() throws SQLException {
        repository.dropTable(repository.getTable());
    }

    /**
     * Удалить элемент
     * @param accident элемент
     * @throws SQLException Возникает при неправильном обращении к БД
     */
    public void delete(AccidentModel accident) throws SQLException {
        repository.delete(accident);
    }

    /**
     * Удалить элемент, где в столбцах {@param columns} указаны значения {@param items}
     * @param itemTypes столбцы
     * @param items значения
     * @throws SQLException Возникает при неправильном обращении к БД
     */
    public <T> void deleteWhere(List<String> itemTypes, List<T> items) throws SQLException {
        repository.deleteWhere(itemTypes, items);
    }

    /**
     * Обновить элемент в таблице
     * @param accident элемент из таблицы с обновлёнными данными
     * @throws SQLException Возникает при неправильном обращении к БД
     */
    public void update(AccidentModel accident) throws SQLException {
        repository.update(accident);
    }

    /**
     * Вставить новый элемент в таблицу
     * @param accident элемент
     * @throws SQLException Возникает при неправильном обращении к БД
     */
    public void insert(AccidentModel accident) throws SQLException {
        repository.insert(accident);
    }
}
