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
     * Модель загруженности на дорогах
     */
    private Traffic traffic;

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

        Date maxTrafficTime;
        Date rushHoursStart = new Date();
        Date rushHoursEnd = new Date();

        rushHoursStart.setHours(0);
        rushHoursStart.setMinutes(0);
        rushHoursEnd.setHours(23);
        rushHoursEnd.setMinutes(59);

        int maxAccidentCount = 0;
        int accidentCount = 0;
        int lastHour = -1;
        Date maxAccidentHour = null;
        for(AccidentModel row: rowData) {
            trafficPoints = row.getPoints();
            if(row.getTime().getHours() != lastHour) {
                if(accidentCount > maxAccidentCount) {
                    maxAccidentCount = accidentCount;
                    maxAccidentHour = row.getTime();
                }
                accidentCount = 0;
                if(trafficPoints > maxTrafficPoints) {
                    maxTrafficPoints = trafficPoints;
                }
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
            if(traffic.getPoints() > (pointsData.get(0).getPoints() * 0.66) && rushHoursEnd == null) {
                rushHoursStart = traffic.getTime();
            }
            if (traffic.getPoints() < (pointsData.get(0).getPoints() * 0.66 + 1) && rushHoursStart != null) {
                rushHoursEnd = traffic.getTime();
                trafficPerDay.addRushHours(rushHoursStart, rushHoursEnd);
                rushHoursStart = null;
                rushHoursEnd = null;
            }
        }

        return new Day(new Date(), new Accident(maxAccidentCount, maxAccidentHour), traffic);
    }

}
