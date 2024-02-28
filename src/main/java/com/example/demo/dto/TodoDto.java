package com.example.demo.dto;

import java.util.UUID;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoDto {

    private UUID id;

    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Description Name is required")
    private String description;

    private Boolean status = false;
}
