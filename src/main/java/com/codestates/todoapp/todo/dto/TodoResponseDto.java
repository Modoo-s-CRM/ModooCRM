package com.codestates.todoapp.todo.dto;

import lombok.Getter;

@Getter
public class TodoResponseDto {
    private Long todoId;
    private String title;
    private Integer todoOrder;
    private Boolean complete;

    public TodoResponseDto(Long todoId, String title, int todoOrder, boolean complete) {
        this.todoId = todoId;
        this.title = title;
        this.todoOrder = todoOrder;
        this.complete = complete;
    }
}
