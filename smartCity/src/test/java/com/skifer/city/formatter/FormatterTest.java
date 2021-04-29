package com.skifer.city.formatter;

import com.skifer.city.database.model.AccidentModel;
import com.skifer.city.database.provider.DataSourceProvider;
import com.skifer.city.database.repository.AccidentRepository;
import com.skifer.city.database.service.AccidentService;
import com.skifer.city.xmlformatter.converter.Converter;
import com.skifer.city.xmlformatter.model.Day;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

class FormatterTest {

    DataSourceProvider provider;
    AccidentRepository repository;
    AccidentService service;

    @BeforeEach
    void setUp() throws IOException, SQLException {
        provider = new DataSourceProvider();
        repository = new AccidentRepository(provider.getDataSource(), "SimpleDay");
        service = new AccidentService(repository);
        service.dropTable();
        repository = new AccidentRepository(provider.getDataSource(), "SimpleDay");
        service = new AccidentService(repository);
    }

    /**
     * Тест на правильную работу функции fromDBToXML
     * Т.к. при проверке бралось для наглядности 2 часа и использовался рандом, список загруженных часов
     * может показаться странным, но это нормально, т.к. баллы загруженности будут скакать от 1 до 9
     * в течение пары минут, а не плавно изменяться как в реальной жизни
     */
    @Test
    void fromDBToXML() throws SQLException, IOException {
        Formatter formatter = new Formatter("SimpleDay");
        Random random = new Random();
        List<Time> timeList = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            timeList.add(new Time((16 + random.nextInt(2)), random.nextInt(59), 0));
        }
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
        for (int i = 1; i < 20; i++) {
            service.insert(new AccidentModel(
                    i,
                    timeList.get(i-1),
                    "Центральный",
                    "Ставропольская",
                    random.nextInt(10),
                    random.nextBoolean()));
        }
        for (AccidentModel accident: service.selectAll()) {
            System.out.println(accident);
        }
        Day tableDay = formatter.fromDBToXML();
        System.out.println(new Converter().toXml(tableDay));
    }
}