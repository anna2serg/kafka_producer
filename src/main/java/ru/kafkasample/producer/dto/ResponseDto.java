package ru.kafkasample.producer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

    @JsonProperty("status")
    private String status;

    public static ResponseDto createOkResponse() {
        ResponseDto response = new ResponseDto();
        response.setStatus(toStatusCode(HttpStatus.OK));
        return response;
    }

    private static String toStatusCode(HttpStatus status) {
        return Integer.toString(status.value());
    }

}
