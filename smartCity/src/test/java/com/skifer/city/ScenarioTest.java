package com.skifer.city;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skifer.city.database.model.AccidentModel;
import com.skifer.city.database.provider.DerbyProvider;
import com.skifer.city.database.repository.AccidentRepository;
import com.skifer.city.database.service.DerbyService;
import com.skifer.city.formatter.Formatter;
import com.skifer.city.xmlformatter.converter.Converter;
import com.skifer.city.xmlformatter.model.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ScenarioTest {

    DerbyProvider provider;
    AccidentRepository repository;
    DerbyService service;

    @BeforeEach
    void setUp() {
        try {
            provider = new DerbyProvider();
            repository = new AccidentRepository(provider.getDataSource(), "SimpleDay");
            service = new DerbyService(repository);
            service.dropTable();
            repository = new AccidentRepository(provider.getDataSource(), "SimpleDay");
            service = new DerbyService(repository);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            System.err.println("Не могу найти таблицу");
            throwables.printStackTrace();
        }
    }

    @Test
    void ultimateTest(){
        Formatter formatter = null;
        try {
            formatter = new Formatter("SimpleDay");
        } catch (IOException | SQLException e) {
            System.err.println("Не могу обработать таблицу");
            e.printStackTrace();
        }
        Random random = new Random();
        List<Time> timeList = new ArrayList<>();
        List<Integer> pointsList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            timeList.add(new Time(10 + random.nextInt(7), random.nextInt(60), random.nextInt(60)));
            pointsList.add(random.nextInt(5));
        }

        for (int i = 0; i < 10; i++) {
            timeList.add(new Time(17 + random.nextInt(2), random.nextInt(60), random.nextInt(60)));
            pointsList.add(6 + random.nextInt(5));
        }

        pointsList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else if(o1 < o2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        timeList.sort(new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                if (o1.getTime() > o2.getTime())
                    return 1;
                else if (o1.getTime() < o2.getTime())
                    return -1;
                else {
                    return 0;
                }
            }
        });
        List<AccidentModel> models = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            models.add(new AccidentModel(
                    i,
                    timeList.get(i - 1),
                    "Центральный",
                    "Ставропольская",
                    pointsList.get(i - 1),
                    false)
            );
            try {
                service.insert(models.get(i - 1));
            } catch (SQLException throwables) {
                System.err.println("Не могу добавить элемент в таблицу");
                throwables.printStackTrace();
            }
        }
        for (int i = 11; i < 21; i++) {
            models.add(new AccidentModel(
                    i,
                    timeList.get(i - 1),
                    "Центральный",
                    "Ставропольская",
                    pointsList.get(i - 1),
                    random.nextBoolean())
            );
            try {
                service.insert(models.get(i - 1));
            } catch (SQLException throwables) {
                System.err.println("Не могу добавить элемент в таблицу");
                throwables.printStackTrace();
            }        }
        Converter converter = new Converter();
        try {
            for (AccidentModel accident: service.selectAll()) {
                System.out.println(accident);
            }
        } catch (SQLException throwables) {
            System.err.println("Не могу обратиться к таблице");
            throwables.printStackTrace();
        }
        Day tableDay = null;
        try {
            tableDay = formatter.fromDBToXML();
        } catch (SQLException throwables) {
            System.err.println("Не могу обратиться к таблице");
            throwables.printStackTrace();
        }
        try {
            System.out.println(converter.toXml(tableDay));
        } catch (JsonProcessingException e) {
            System.err.println("Ошибка Сериализации");
            e.printStackTrace();
        }
        converter.toXmlFile(tableDay);
        Assertions.assertTrue(new File("Traffic.xml").exists());
    }
}
