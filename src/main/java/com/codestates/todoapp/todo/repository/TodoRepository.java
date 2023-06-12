package com.codestates.todoapp.todo.repository;

import com.codestates.todoapp.member.entity.Member;
import com.codestates.todoapp.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {

    Page<Todo> findByMemberOrderByTodoIdDesc(Member member, Pageable pageable);
//    Page<Todo> findAllByOrderByTodoIdDesc(Pageable pageable);
}
