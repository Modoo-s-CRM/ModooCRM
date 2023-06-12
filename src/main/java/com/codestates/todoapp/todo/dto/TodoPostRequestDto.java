package com.codestates.todoapp.todo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
public class TodoPostRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private Integer todoOrder;

    @NotBlank
    private Boolean complete;

    public TodoPostRequestDto(String title, int todoOrder, boolean complete) {
        this.title = title;
        this.todoOrder = todoOrder;
        this.complete = complete;
    }
}
