package com.skifer.city.formatter;

import com.skifer.city.database.model.AccidentModel;
import com.skifer.city.database.provider.DataSourceProvider;
import com.skifer.city.database.repository.AccidentRepository;
import com.skifer.city.database.service.AccidentService;
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
    private DataSourceProvider provider;
    /**
     * Репозиторий для работы с моделью БД
     */
    private AccidentRepository repository;
    /**
     * Сервис для доступа к основным действиям над БД
     */
    private AccidentService service;

    /**
     * Класс позволяющий перевести данные из таблицы в формат модели Day
     * @param table имя таблицы
     */
    public Formatter(String table) throws IOException, SQLException {
        provider = new DataSourceProvider();
        repository= new AccidentRepository(provider.getDataSource(), table);
        service = new AccidentService(repository);
    }

    /**
     * Преобразователь данных БД в нужную нам модель
     * @return модель {@link Day}
     */
    public Day fromDBToXML() throws SQLException {
        List<AccidentModel> rowData = service.selectAll();
        return new Day(new Time(new Date().getTime()), accidentsPerDay(rowData), trafficPerDay(rowData));
    }

    /**
     * Подсчёт колличества ДТП за день
     * @param rowData Вся информация из БД
     * @return модель {@link Accident}
     */
    private Accident accidentsPerDay(List<AccidentModel> rowData) {
        int maxAccidentCount = 0;
        int accidentCount = 0;
        int lastHour = -1;
        Time maxAccidentHour = null;
        for(AccidentModel row: rowData) {
            if(row.getTime().getHours() != lastHour) {
                accidentCount = 0;
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
        return new Accident(maxAccidentCount, maxAccidentHour);
    }

    /**
     * Подсчёт максимально загруженного времени
     * @param rowData Вся информация из БД
     * @return Модель {@link Traffic}
     */
    private Traffic trafficPerDay (List<AccidentModel> rowData) throws SQLException {

        int maxTrafficPoints = 0;
        int trafficPoints;
        Time maxTrafficTime;
        Time rushHoursStart = null;
        Time rushHoursEnd = null;
        List<AccidentModel> pointsData = service.selectWhere(Arrays.asList("points"), Arrays.asList(maxTrafficPoints));
        maxTrafficTime = pointsData.get(0).getTime();
        Traffic trafficPerDay = new Traffic();
        trafficPerDay.setMaxRushHour(maxTrafficTime);
        for(AccidentModel traffic: rowData) {
            trafficPoints = traffic.getPoints();
            if(trafficPoints > maxTrafficPoints) {
                maxTrafficPoints = trafficPoints;
            }
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
        return trafficPerDay;
    }

}
