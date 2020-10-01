package ru.kafkasample.producer.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import ru.kafkasample.producer.dto.MeteoStationDto;
import ru.kafkasample.producer.service.MeteostationService;

@Slf4j
@Service
public class MeteostationServiceImpl implements MeteostationService {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, MeteoStationDto> kafkaTemplate;

    @Override
    public void send(String key, MeteoStationDto dto) {
        //Synchronous messaging
        //kafkaTemplate.send(topicName, key, dto);

        //Asynchronous message sending
        ListenableFuture<SendResult<String, MeteoStationDto>> future =
                kafkaTemplate.send(topicName, key, dto);
        future.addCallback(result -> log.info("<- successfully sent {} ", dto),
                System.err::println);
        kafkaTemplate.flush();
    }

}
