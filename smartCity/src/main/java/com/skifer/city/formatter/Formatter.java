package com.skifer.city.formatter;

import com.skifer.city.database.model.AccidentModel;
import com.skifer.city.database.provider.DerbyProvider;
import com.skifer.city.database.repository.AccidentRepository;
import com.skifer.city.database.service.DerbyService;
import com.skifer.city.xmlformatter.model.Day;
import com.skifer.city.xmlformatter.model.accident.Accident;
import com.skifer.city.xmlformatter.model.trafic.Traffic;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Класс позволяющий перевести данные из таблицы в формат модели Day
 */
public class Formatter {

    /**
     * Проводник для БД Derby
     */
    private DerbyProvider provider;
    /**
     * Репозиторий для работы с моделью БД
     */
    private AccidentRepository repository;
    /**
     * Сервис для доступа к основным действиям над БД
     */
    private DerbyService service;

    /**
     * Модель дня
     */
    private Day day;
    /**
     * Модель максимального колличества ДТП и его времени
     */
    private Accident accident;

    /**
     * Класс позволяющий перевести данные из таблицы в формат модели Day
     * @param table имя таблицы
     */
    public Formatter(String table) throws IOException, SQLException {
        provider = new DerbyProvider();
        repository= new AccidentRepository(provider.getDataSource(), table);
        service = new DerbyService(repository);
    }

    /**
     * Преобразователь данных БД в нужную нам модель
     * @return модель {@link Day}
     */
    public Day fromDBToXML() throws SQLException {
        List<AccidentModel> rowData = service.selectAll();
        int maxTrafficPoints = 0;
        int trafficPoints = 0;

        Time maxTrafficTime;
        Time rushHoursStart = null;
        Time rushHoursEnd = null;

        int maxAccidentCount = 0;
        int accidentCount = 0;
        int lastHour = -1;
        Time maxAccidentHour = null;
        for(AccidentModel row: rowData) {
            trafficPoints = row.getPoints();
            if(row.getTime().getHours() != lastHour) {
                accidentCount = 0;
            }
            if(trafficPoints > maxTrafficPoints) {
                maxTrafficPoints = trafficPoints;
            }
            if(accidentCount > maxAccidentCount) {
                maxAccidentCount = accidentCount;
                maxAccidentHour = row.getTime();
            }
            if(row.getAccident()) {
                accidentCount++;
            }
            lastHour = row.getTime().getHours();
        }
        List<AccidentModel> pointsData = service.selectWhere(Arrays.asList("points"), Arrays.asList(maxTrafficPoints));
        Traffic trafficPerDay = new Traffic();
        maxTrafficTime = pointsData.get(0).getTime();
        trafficPerDay.setMaxRushHour(maxTrafficTime);
        for(AccidentModel traffic: rowData) {
            if(traffic.getPoints() > (pointsData.get(0).getPoints() * 0.5) && rushHoursStart == null && rushHoursEnd == null) {
                rushHoursStart = traffic.getTime();
            }
            if (traffic.getPoints() < (pointsData.get(0).getPoints() * 0.5) &&
                    rushHoursStart != null) {
                rushHoursEnd = traffic.getTime();
                trafficPerDay.addRushHours(rushHoursStart, rushHoursEnd);
                rushHoursStart = null;
                rushHoursEnd = null;
            }
        }
        if (rushHoursStart != null && rushHoursEnd == null) {
            rushHoursEnd = rowData.get(rowData.size() - 1).getTime();
            trafficPerDay.addRushHours(rushHoursStart, rushHoursEnd);
        }
        return new Day(new Time(new Date().getTime()), new Accident(maxAccidentCount, maxAccidentHour), trafficPerDay);
    }

}
