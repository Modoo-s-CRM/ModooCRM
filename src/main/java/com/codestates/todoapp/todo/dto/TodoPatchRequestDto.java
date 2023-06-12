package com.codestates.todoapp.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class TodoPatchRequestDto {
    private String title;
    private Integer todoOrder;
    private Boolean complete;

    public TodoPatchRequestDto(String title, Integer todoOrder, Boolean complete) {
        this.title = title;
        this.todoOrder = todoOrder;
        this.complete = complete;
    }
}
