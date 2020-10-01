package ru.kafkasample.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kafkasample.producer.dto.MeteoStationDto;
import ru.kafkasample.producer.dto.ResponseDto;
import ru.kafkasample.producer.service.MeteostationService;

@RestController
public class MeteostationController {

    @Autowired
    private MeteostationService service;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{key}")
    public ResponseDto send(@PathVariable String key, @RequestBody MeteoStationDto dto) {
        service.send(key, dto);
        return ResponseDto.createOkResponse();
    }

}
