package com.recruitercorp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> implements Serializable {
    private String code;
    private String message;
    private boolean ok;
    private T data;

    public static <T> ResponseData<T> responseOK(T data) {
        ResponseData<T> basicResponseDto = new ResponseData<>();
        basicResponseDto.setData(data);
        basicResponseDto.setCode(String.valueOf(HttpStatus.OK.value()));
        basicResponseDto.setMessage("OK");
        basicResponseDto.setOk(true);
        return basicResponseDto;
    }
}
