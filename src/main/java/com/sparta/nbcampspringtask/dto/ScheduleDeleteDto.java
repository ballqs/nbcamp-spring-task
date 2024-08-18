package com.sparta.nbcampspringtask.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ScheduleDeleteDto {
    @NotBlank
    private String pw;
}
