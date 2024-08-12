package com.sparta.nbcampspringtask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleUpdateDto {
    @NotNull
    private Long idx;
    @Size(max=200, min=1)
    private String content;
    private Long managerIdx;
    @NotBlank
    private String pw;
}
