package com.skifer.city.xmlformatter.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skifer.city.xmlformatter.model.Day;
import com.skifer.city.xmlformatter.model.accident.Accident;
import com.skifer.city.xmlformatter.model.trafic.Traffic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Time;
import java.util.Date;

class ConverterTest {

    Converter converter;

    @BeforeEach
    void setUp() {
        converter = new Converter();
    }

    @Test
    void toXml() throws JsonProcessingException {
        Traffic traffic = new Traffic();
        traffic.addRushHours(new Time(13, 0, 0), new Time(17, 0, 0));
        traffic.setMaxRushHour(new Time(16, 0, 0));
        Day day = new Day(new Date(), new Accident(5, new Time(15, 50, 23)), traffic);
        System.out.println(converter.toXml(day));
    }

    @Test
    void toXmlFile() {
        Traffic traffic = new Traffic();
        traffic.addRushHours(new Time(13, 0, 0), new Time(17, 0, 0));
        traffic.setMaxRushHour(new Time(16, 0, 0));
        Day day = new Day(new Date(), new Accident(5, new Time(15, 50, 23)), traffic);
        converter.toXmlFile(day);
        Assertions.assertTrue(new File("Traffic.xml").exists());
    }

}