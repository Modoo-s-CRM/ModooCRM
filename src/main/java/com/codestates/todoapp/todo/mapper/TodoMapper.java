package com.codestates.todoapp.todo.mapper;

import com.codestates.todoapp.todo.dto.TodoPatchRequestDto;
import com.codestates.todoapp.todo.dto.TodoPostRequestDto;
import com.codestates.todoapp.todo.dto.TodoResponseDto;
import com.codestates.todoapp.todo.entity.Todo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TodoMapper {

    public Todo todoPostDtoToTodo(TodoPostRequestDto todoPostRequestDto) {
        return new Todo(
                todoPostRequestDto.getTitle(),
                todoPostRequestDto.getTodoOrder(),
                todoPostRequestDto.getComplete()
        );
    }

    public TodoResponseDto todoTotodoResponseDto(Todo todo) {
        return new TodoResponseDto(
                todo.getTodoId(),
                todo.getTitle(),
                todo.getTodoOrder(),
                todo.getComplete()
        );
    }

    public List<TodoResponseDto> todoTotodoResponseDtos(List<Todo> todos) {
        List<TodoResponseDto> list = new ArrayList<>(todos.size());
        for (int i = 0; i < todos.size(); i++) {
            list.add(this.todoTotodoResponseDto(todos.get(i)));
        }
        return list;
    }

    public Todo todoPatchRequestDtoToTodo(TodoPatchRequestDto todoPatchRequestDto){
        return new Todo(
                todoPatchRequestDto.getTitle(),
                todoPatchRequestDto.getTodoOrder(),
                todoPatchRequestDto.getComplete()
        );
    }
}
