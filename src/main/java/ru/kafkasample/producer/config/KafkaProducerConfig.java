package ru.kafkasample.producer.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.kafkasample.producer.dto.MeteoStationDto;
import ru.kafkasample.producer.partitioner.NorthPartitioner;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String kafkaServer;

    @Value("${spring.kafka.producer.id}")
    private String kafkaProducerId;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProducerId);
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, NorthPartitioner.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, MeteoStationDto> producerMeteoStationFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, MeteoStationDto> kafkaTemplate() {
        KafkaTemplate<String, MeteoStationDto> template = new KafkaTemplate<>(
                producerMeteoStationFactory());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }
}
