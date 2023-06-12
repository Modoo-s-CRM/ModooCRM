package com.codestates.todoapp.todo.controller;

import com.codestates.todoapp.todo.dto.TodoPatchRequestDto;
import com.codestates.todoapp.todo.dto.TodoPostRequestDto;
import com.codestates.todoapp.todo.entity.Todo;
import com.codestates.todoapp.todo.mapper.TodoMapper;
import com.codestates.todoapp.response.MultiResponseDto;
import com.codestates.todoapp.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Positive;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;
    private final TodoMapper todoMapper;

    @PostMapping(value = "/post", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity postTodo(
            @RequestPart("files") MultipartFile[] files,
            @RequestPart TodoPostRequestDto todoPostRequestDto,
            Authentication authentication) throws IOException {
        String authenticationUsername = authentication.getName();
        Todo todo = todoMapper.todoPostDtoToTodo(todoPostRequestDto);
        Todo saveTodo = todoService.postTodo(todo, authenticationUsername,files);
        return new ResponseEntity(todoMapper.todoTotodoResponseDto(saveTodo), HttpStatus.CREATED);
    }

    //로그인한 회원의 것을 가져와야함
    @GetMapping
    public ResponseEntity getAllTodo(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size,
                                     Authentication authentication) {
        Page<Todo> todoPage = todoService.getAllTodo(page - 1, size, authentication.getName());
        List<Todo> todoList = todoPage.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(
                        todoMapper.todoTotodoResponseDtos(todoList), todoPage),
                HttpStatus.OK);
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity getTodo(@PathVariable("todo-id") Long todoId) {
        Todo findTodo = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoMapper.todoTotodoResponseDto(findTodo), HttpStatus.OK);
    }

    @PatchMapping("{todo-id}")
    public ResponseEntity patchTodo(@PathVariable("todo-id") Long todoId, @RequestBody TodoPatchRequestDto todoPatchRequestDto,
                                    Authentication authentication) {
        Todo findTodo = todoMapper.todoPatchRequestDtoToTodo(todoPatchRequestDto);
        Todo patchTodo = todoService.patchTodo(todoId, findTodo);
        return new ResponseEntity<>(todoMapper.todoTotodoResponseDto(patchTodo), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity deleteAllTodo() {
        todoService.deleteAllTodo();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{todo-id}")
    public ResponseEntity deleteTodo(@PathVariable("todo-id") Long todoId) {
        todoService.deleteTodo(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
