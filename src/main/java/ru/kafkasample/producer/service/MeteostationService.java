package ru.kafkasample.producer.service;

import ru.kafkasample.producer.dto.MeteoStationDto;

public interface MeteostationService {

    void send(String key, MeteoStationDto dto);

}
