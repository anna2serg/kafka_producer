package ru.kafkasample.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class MeteodataDto {

    private String date;
    private Double temperature;
    private Double perceivedTemperature;
    private Double windSpeed;
    private String windDirection;
    private Integer pressure;
    private Integer humidity;
    private Integer geomagneticActivity;

}
