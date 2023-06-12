package com.codestates.todoapp.todo.service;

import com.codestates.todoapp.image.entity.Images;
import com.codestates.todoapp.image.repository.ImagesRepository;
import com.codestates.todoapp.member.entity.Member;
import com.codestates.todoapp.member.repository.MemberRepository;
import com.codestates.todoapp.member.service.MemberService;
import com.codestates.todoapp.todo.entity.Todo;
import com.codestates.todoapp.exception.BusinessLogicException;
import com.codestates.todoapp.exception.ExceptionCode;
import com.codestates.todoapp.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberService memberService;
    private final ImagesRepository imagesRepository;

    @Value("${spring.servlet.multipart.location}")
    private String fileDir;

    public Todo postTodo(Todo todo, String authenticationUsername, MultipartFile[] files) throws IOException {

        if (files != null){
            for (MultipartFile file : files){
                String originalFilename = file.getOriginalFilename();
                Long size = file.getSize();
                String contentType = file.getContentType();
                String fullPath = fileDir + originalFilename;

                Images images = new Images(originalFilename,originalFilename,fullPath,size,contentType);
                todo.getUploadImages().add(images);
                images.setTodo(todo);
                imagesRepository.save(images);

                file.transferTo(new File(fullPath));

            }
        }

        Member findMember = memberService.verifyUsername(authenticationUsername);
        todo.setMember(findMember);
        return todoRepository.save(todo);
    }

    public Page<Todo> getAllTodo(int page, int size, String username){
        Member findMember = memberService.verifyUsername(username);
        PageRequest pageRequest = PageRequest.of(page,size);
        return todoRepository.findByMemberOrderByTodoIdDesc(findMember,pageRequest);
    }

    public Todo getTodo(Long todoId){
        Todo findTodo = checkTodo(todoId);
        return findTodo;
    }

    public Todo patchTodo(Long todoId, Todo todo){
        Todo findTodo = checkTodo(todoId);

        Optional.ofNullable(todo.getTitle())
                .ifPresent(title -> findTodo.setTitle(title));
        Optional.ofNullable(todo.getTodoOrder())
                .ifPresent(todoOrder -> findTodo.setTodoOrder(todoOrder));
        Optional.ofNullable(todo.getComplete())
                .ifPresent(complete -> findTodo.setComplete(complete));

        return todoRepository.save(findTodo);
    }

    public void deleteAllTodo(){
        todoRepository.deleteAll();
    }

    public void deleteTodo(Long todoId){
        todoRepository.deleteById(todoId);
    }





    private Todo checkTodo(Long todoId){
        Todo checkTodo = todoRepository.findById(todoId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND)
        );
        return checkTodo;
    }
}
