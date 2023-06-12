package com.codestates.todoapp.todo.entity;

import com.codestates.todoapp.image.entity.Images;
import com.codestates.todoapp.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Todo {

    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long todoId;

    @Column(nullable = false) @Setter
    private String title;

    @Column(nullable = false) @Setter
    private Integer todoOrder;

    @Column(nullable = false) @Setter
    private Boolean complete;

    @OneToMany(mappedBy = "todo")
    private List<Images> uploadImages = new ArrayList<>();

    @Setter
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public Todo(String title, int todoOrder, boolean complete) {
        this.title = title;
        this.todoOrder = todoOrder;
        this.complete = complete;
    }

    public void createImages(Images images){
        this.uploadImages.add(images);
        if(images.getTodo() != this){
            images.setTodo(this);
        }
    }


}
