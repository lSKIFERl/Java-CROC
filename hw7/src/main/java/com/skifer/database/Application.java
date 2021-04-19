package com.skifer.database;

import com.skifer.database.dbprovider.DataBaseProvider;
import com.skifer.database.model.GameModel;
import com.skifer.database.repository.GameRepository;
import com.skifer.database.service.DataBaseService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Application {
    public static void main(String[] args) throws IOException, SQLException {
        DataBaseProvider provider = new DataBaseProvider();
        GameRepository repository = new GameRepository(provider.getDataSource(), "Games");
        DataBaseService service = new DataBaseService(repository);

        /*service.insert(new GameModel(
                2, "TES V: Skyrim SE", "RPG", 799 ,
                false,
                new Date(2016-1900, Calendar.OCTOBER, 28)));*/

        service.selectAll().forEach(System.out::println);
        System.out.println(service.selectWhere(Arrays.asList("genre"), Arrays.asList("RPG")));
    }
}
