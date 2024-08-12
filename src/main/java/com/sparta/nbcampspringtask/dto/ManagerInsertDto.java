package com.sparta.nbcampspringtask.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class ManagerInsertDto {
    @Size(max=50, min=1)
    @NotNull
    private String managerNm;

    @NotBlank
    @Email
    private String email;
}
