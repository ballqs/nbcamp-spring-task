package com.sparta.nbcampspringtask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDto<T> {
    private int status;
    private T content;
    private String msg;
}
