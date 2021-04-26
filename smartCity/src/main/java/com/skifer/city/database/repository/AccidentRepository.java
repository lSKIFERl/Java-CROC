package com.skifer.city.database.repository;

import com.skifer.city.database.model.AccidentModel;
import org.apache.derby.jdbc.EmbeddedDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Реопзиторий инцидентов
 */
public class AccidentRepository {
    /**
     * Подключения
     */
    private final EmbeddedDataSource dataSource;

    /**
     * Название таблицы
     */
    private final String table;

    /**
     * Репозиторий инцидентов
     * @param dataSource Подключения
     * @param table Название таблицы
     * @throws SQLException Возникает при ошибке обращения к базе данных
     */
    public AccidentRepository(EmbeddedDataSource dataSource, String table) throws SQLException {
        this.dataSource = dataSource;
        this.table = table;

        if(!isTableExists(table)) {
            initTable();
        }
    }

    /**
     * Проверит существование таблицы
     * @param tableName Название таблицы
     * @return true, если есть, false, если нет
     * @throws SQLException Возникает при ошибке обращения к базе данных
     */
    private boolean isTableExists(String tableName) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            DatabaseMetaData databaseMetadata = connection.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(
                    null, null, tableName.toUpperCase(), new String[]{"TABLE"});

            return resultSet.next();
        }
    }

    /**
     * Создаёт таблицу
     * @throws SQLException Возникает при ошибке обращения к базе данных
     */
    private void initTable() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "CREATE TABLE " + table + "(" +
                            "id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                            "time DATE" +
                            "region VARCHAR(255), " +
                            "street VARCHAR(255), " +
                            "points INTEGER, " +
                            "accident BOOLEAN, " +
                            ")"
            );
        }
    }

    /**
     * Удаление Таблицы
     * @param table имя таблицы
     * @throws SQLException Возникает при неправильном обращении к БД
     */
    public void dropTable(String table) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE " + table);
        }
    }


    /**
     * Делает запрос SELECT * FROM
     * @return Список инцидентов
     * @throws SQLException Возникает при ошибке обращения к базе данных
     */
    public List<AccidentModel> selectAll() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table);
            List<AccidentModel> accidents = new ArrayList<>();
            while (resultSet.next()) {
                AccidentModel accident = new AccidentModel(
                        resultSet.getInt("id"),
                        resultSet.getTime("time"),
                        resultSet.getString("region"),
                        resultSet.getString("street"),
                        resultSet.getInt("points"),
                        resultSet.getBoolean("accident"));
                accidents.add(accident);
            }
            return accidents;
        }
    }

    /**
     * Реализует запрос типа SELECT FROM table WHERE ...
     * @param columns Колонка по которой будем искать
     * @param items Искомое значение
     * @return Список найденных элементов
     * @throws SQLException Возникает при ошибке обращения к базе данных
     * @throws IllegalArgumentException Возникает при передаче неправильных параметров
     */
    public <T> List<AccidentModel> selectWhere(List<String> columns, List<T> items) throws SQLException, IllegalArgumentException {
        if (columns.size() != items.size() || !Arrays.asList(
                "id", "time", "region", "street", "points", "accident").containsAll(columns))
            throw new IllegalArgumentException("Такх колонок нет");
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            List<AccidentModel> accidents = new ArrayList<>();

            String sql = "SELECT * FROM " + table + " WHERE ";

            for(int i = 0; i < columns.size(); i++) {
                sql = sql.concat(columns.get(i) + " = ");
                if (columns.get(i).equals("id") || columns.get(i).equals("points")) {
                    sql = sql.concat(items.get(i).toString());
                } else {
                    sql = sql.concat("'" + items.get(i).toString() + "'");
                }
                sql = sql.concat(" AND ");
            }
            sql = sql.substring(0, sql.length() - 5);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                AccidentModel accident = new AccidentModel(
                        resultSet.getInt("id"),
                        resultSet.getTime("time"),
                        resultSet.getString("region"),
                        resultSet.getString("street"),
                        resultSet.getInt("points"),
                        resultSet.getBoolean("accident"));
                accidents.add(accident);
            }
            return accidents;
        }
    }

    /**
     * Реализует удаление
     * @param columns Колонка по которой будем искать
     * @param items Искомое значение
     * @throws SQLException Возникает при ошибке обращения к базе данных
     * @throws IllegalArgumentException Возникает при передаче неправильных параметров
     */
    public <T> void deleteWhere(List<String> columns, List<T> items) throws SQLException, IllegalArgumentException {
        if (columns.size() != items.size() || !Arrays.asList(
                "id", "time", "region", "street", "points", "accident").containsAll(columns))
            throw new IllegalArgumentException("Такх колонок нет");
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            String sql = "DELETE FROM " + table + " WHERE ";

            for(int i = 0; i < columns.size(); i++) {
                sql = sql.concat(columns.get(i) + " = ");
                if (columns.get(i).equals("id") || columns.get(i).equals("points")) {
                    sql = sql.concat(items.get(i).toString());
                } else {
                    sql = sql.concat("'" + items.get(i).toString() + "'");
                }
                sql = sql.concat(" AND ");
            }
            sql = sql.substring(0, sql.length() - 5);
            statement.execute(sql);
        }
    }

    /**
     * Реализует удаление
     * @throws SQLException Возникает при ошибке обращения к базе данных
     */
    public void delete(AccidentModel accident) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM " + table + " WHERE " + "id = " + accident.getId());
        }
    }

    /**
     * Обновляет строку в БД
     * @param accident Объект-инцидент
     * @throws SQLException  Возникает при ошибке обращения к базе данных
     */
    public void update(AccidentModel accident) throws SQLException {
        String sql =
                "UPDATE " + table + " SET " +
                        "time = ?," +
                        "region = ?," +
                        "street = ?," +
                        "points = ?," +
                        "accident = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            Date time = new Date(accident.getTime().getTime());
            statement.setTime(1, new Time(time.getTime()));
            statement.setString(2, accident.getRegion());
            statement.setString(3, accident.getStreet());
            statement.setInt(4, accident.getPoints());
            statement.setBoolean(5, accident.getAccident());
            statement.setInt(6, accident.getId());
            statement.execute();
        }
    }

    /**
     * Производит вставку элемента
     * @throws SQLException Возникает при ошибке обращения к базе данных
     */
    public void insert(AccidentModel accident) throws SQLException {
        String sql =
                "INSERT INTO " + table + "(time, region, street, points, accident)" +
                        "values(?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            Date time = new Date(accident.getTime().getTime());
            statement.setTime(1, new Time(time.getTime()));
            statement.setString(2, accident.getRegion());
            statement.setString(3, accident.getStreet());
            statement.setInt(4, accident.getPoints());
            statement.setBoolean(5, accident.getAccident());
            statement.execute();
        }
    }

}
