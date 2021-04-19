package com.skifer.database.repository;

import com.skifer.database.model.GameModel;
import org.apache.derby.jdbc.EmbeddedDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Репозиторий игорей
 */
public class GameRepository {

    /**
     * Подключения
     */
    private final EmbeddedDataSource dataSource;

    /**
     * Название таблицы
     */
    private final String table;

    /**
     * Репозиторий игорей
     * @param dataSource Подключения
     * @param table Название таблицы
     * @throws SQLException Возникает при ошибке обращения к базе данных
     */
    public GameRepository(EmbeddedDataSource dataSource, String table) throws SQLException {
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
                    "title VARCHAR(255), " +
                    "genre VARCHAR(255), " +
                    "price INTEGER, " +
                    "gamePassAvailable BOOLEAN, " +
                    "releaseDate DATE" +
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
     * @return Список игр
     * @throws SQLException Возникает при ошибке обращения к базе данных
     */
    public List<GameModel> selectAll() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table);
            List<GameModel> games = new ArrayList<>();
            while (resultSet.next()) {
                GameModel game = new GameModel(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getInt("price"),
                        resultSet.getBoolean("gamePassAvailable"),
                        resultSet.getDate("releaseDate"));
                games.add(game);
            }
            return games;
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
    public <T> List<GameModel> selectWhere(List<String> columns, List<T> items) throws SQLException, IllegalArgumentException {
        if (columns.size() != items.size() || !Arrays.asList(
                "id", "title", "genre", "price", "gamePassAvailable", "releaseDate").containsAll(columns))
            throw new IllegalArgumentException("No such columns");
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            List<GameModel> games = new ArrayList<>();

            String sql = "SELECT * FROM " + table + " WHERE ";

            for(int i = 0; i < columns.size(); i++) {
                sql = sql.concat(columns.get(i) + " = ");
                if (columns.get(i).equals("id") || columns.get(i).equals("price")) {
                    sql = sql.concat(items.get(i).toString());
                } else {
                    sql = sql.concat("'" + items.get(i).toString() + "',");
                }
            }
            sql = sql.substring(0, sql.length() - 1);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                GameModel game = new GameModel(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("genre"),
                        resultSet.getInt("price"),
                        resultSet.getBoolean("gamePassAvailable"),
                        resultSet.getDate("releaseDate"));
                games.add(game);
            }
            return games;
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
                "id", "title", "genre", "price", "gamePassAvailable", "releaseDate").containsAll(columns))
            throw new IllegalArgumentException("No such columns");
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            String sql = "DELETE FROM " + table + " WHERE ";

            for(int i = 0; i < columns.size(); i++) {
                sql = sql.concat(columns.get(i) + " = ");
                if (columns.get(i).equals("id") || columns.get(i).equals("price")) {
                    sql = sql.concat(items.get(i).toString());
                } else {
                    sql = sql.concat("'" + items.get(i).toString() + "',");
                }
            }
            statement.execute(sql);
        }
    }

    /**
     * Реализует удаление
     * @param game Объект-игра
     * @throws SQLException Возникает при ошибке обращения к базе данных
     */
    public void delete(GameModel game) throws SQLException {
        String sql =
                "DELETE FROM " + table + " WHERE " +
                        "id = ?," +
                        "title = ?," +
                        "genre = ?," +
                        "price = ?," +
                        "gamePassAvailable = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, game.getTitle());
            statement.setString(2, game.getGenre());
            statement.setInt(3, game.getPrice());
            statement.setBoolean(4, game.isGamePassAvailable());
            Date release = new Date(game.getReleaseDate().getTime());
            statement.setDate(5, release);
            statement.setInt(6, game.getId());
            statement.execute();
        }
    }

    /**
     * Обновляет строку в БД
     * @param game Объект-игра
     * @throws SQLException  Возникает при ошибке обращения к базе данных
     */
    public void update(GameModel game) throws SQLException {
        String sql =
                "UPDATE " + table + " SET " +
                        "title = ?," +
                        "genre = ?," +
                        "price = ?," +
                        "gamePassAvailable = ?," +
                        "releaseDate = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, game.getTitle());
            statement.setString(2, game.getGenre());
            statement.setInt(3, game.getPrice());
            statement.setBoolean(4, game.isGamePassAvailable());
            Date release = new Date(game.getReleaseDate().getTime());
            statement.setDate(5, release);
            statement.setInt(6, game.getId());
            statement.execute();
        }
    }

    /**
     * Производит вставку элемента
     * @param game Объект-игра
     * @throws SQLException Возникает при ошибке обращения к базе данных
     */
    public void insert(GameModel game) throws SQLException {
        String sql =
                "INSERT INTO " + table + "(title, genre, price, gamePassAvailable, releaseDate)" +
                        "values(?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, game.getTitle());
            statement.setString(2, game.getGenre());
            statement.setInt(3, game.getPrice());
            statement.setBoolean(4, game.isGamePassAvailable());
            Date release = new Date(game.getReleaseDate().getTime());
            statement.setDate(5, release);
            statement.execute();
        }
    }


}
