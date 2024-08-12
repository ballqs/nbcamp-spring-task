package com.sparta.nbcampspringtask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScheduleDeleteDto {
    @NotNull
    private Long idx;
    @NotBlank
    private String pw;
}
