package com.longpc.myblogrestapi.dto;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class MemoDTO {
    private String id;
    @NotBlank(message = "Content must not empty")
    private String content;
    @Min(value = 1900, message = "Year must be start from 1900 ")
    private int year;
}
