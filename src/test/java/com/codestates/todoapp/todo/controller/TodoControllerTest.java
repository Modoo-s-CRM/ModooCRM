package com.codestates.todoapp.todo.controller;

import com.codestates.todoapp.todo.dto.TodoPatchRequestDto;
import com.codestates.todoapp.todo.dto.TodoPostRequestDto;
import com.codestates.todoapp.todo.entity.Todo;
import com.codestates.todoapp.todo.mapper.TodoMapper;
import com.codestates.todoapp.todo.service.TodoService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private TodoService todoService;

    @Autowired
    private TodoMapper todoMapper;

//    @Test
//    void postTodoTest() throws Exception {
//        //given
//        TodoPostRequestDto todoPostRequestDto = new TodoPostRequestDto(
//                "title",
//                1,
//                false);
//        Todo todo = todoMapper.todoPostDtoToTodo(todoPostRequestDto);
//        todo.setTodoId(1L);
//        given(todoService.postTodo(Mockito.any(Todo.class)))
//                .willReturn(todo);
//        String content = gson.toJson(todoPostRequestDto);
//        //when
//        ResultActions actions =
//                mockMvc.perform(
//                        post("/todo")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(content)
//                );
//        //then
//        actions
//                .andExpect(status().isCreated());
//    }

//    @Test
//    void getAllTodoTest() throws Exception {
//        //given
//        Todo todo1 = new Todo("하이", 1, false);
//        todo1.setTodoId(1L);
//        Todo todo2 = new Todo("헬로우", 2, false);
//        todo2.setTodoId(2L);
//        Todo todo3 = new Todo("헬로우우", 3, false);
//        todo3.setTodoId(3L);
//
//        List<Todo> todos = new ArrayList<>();
//        todos.add(todo1);
//        todos.add(todo2);
//        todos.add(todo3);
//
//        //when
//        given(todoService.getAllTodo(Mockito.anyInt(), Mockito.anyInt())).willReturn(new PageImpl<>(todos));
//
//        ResultActions actions = mockMvc.perform(
//                get("/todo")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .param("page", String.valueOf(1))
//                        .param("size", String.valueOf(3))
//        );
//        //then
//        actions.andExpect(status().isOk());
//    }

    @Test
    void getTodoTest() throws Exception {
        //given
        Todo todo = new Todo("CRUD 개발", 1, false);
        todo.setTodoId(1L);

        given(todoService.getTodo(Mockito.anyLong())).willReturn(todo);

        //when
        ResultActions actions = mockMvc.perform(
                get("/todo/" + todo.getTodoId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        //then
        actions.andExpect(status().isOk());
    }

    @Test
    void patchTodoTest() throws Exception {
        //given
        TodoPatchRequestDto patchDto = new TodoPatchRequestDto("사랑해",1,false);
        Todo patchTodo = todoMapper.todoPatchRequestDtoToTodo(patchDto);
        patchTodo.setTodoId(1L);

        //when
        given(todoService.patchTodo(Mockito.anyLong(),Mockito.any(Todo.class))).willReturn(patchTodo);
        String content = gson.toJson(patchDto);

        ResultActions actions = mockMvc.perform(
                patch("/todo/" + patchTodo.getTodoId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("title").value(patchDto.getTitle()))
                .andExpect(jsonPath("todoOrder").value(patchDto.getTodoOrder()));

    }

    @Test
    void deleteTodoTest() throws Exception {
        //given
        Todo todo = new Todo("CRUD 개발", 1,false);
        todo.setTodoId(1L);

        //when
        doNothing().when(todoService).deleteTodo(Mockito.anyLong());
        ResultActions actions = mockMvc.perform(
                delete("/todo/" + todo.getTodoId())
        );

        //then
        actions.andExpect(status().isNoContent());
    }


}