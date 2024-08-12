package com.sparta.nbcampspringtask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleInsertDto {
    @Size(max=200, min=1)
    @NotNull
    private String content;
    @NotNull
    private Long managerIdx;
    @NotBlank
    private String pw;
}
