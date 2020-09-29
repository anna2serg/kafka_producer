package ru.kafkasample.producer.dto;

import java.util.List;
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
public class MeteoStationDto {

    private String sn;
    private String model;
    private String coordinates;
    private List<MeteodataDto> data;

}
