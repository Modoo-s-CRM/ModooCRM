package com.codestates.todoapp.image.entity;

import com.codestates.todoapp.todo.entity.Todo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long imageId;

    @Column(nullable = false)
    @Comment("사용자 지정 파일 이름")
    private String uploadFileName;

    @Column(nullable = false)
    @Comment("지정된 파일 이름")
    private String storedFileName;

    @Column(nullable = false)
    @Comment("파일 저장 경로")
    private String fullPath;

    @Comment("파일 사이즈")
    private Long size;

    @Comment("확장자")
    private String extension;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "todoId")
    private Todo todo;

    public Images(String uploadFileName, String storedFileName, String fullPath, Long size, String extension) {
        this.uploadFileName = uploadFileName;
        this.storedFileName = storedFileName;
        this.fullPath = fullPath;
        this.size = size;
        this.extension = extension;
    }

    public void setTodo(Todo todo){
        this.todo = todo;
//        if (!todo.getUploadImages().contains(this)){
//            todo.getUploadImages().add(this);
//        }
        if (todo != null && !todo.getUploadImages().contains(this)) {
            todo.getUploadImages().add(this);
        }
    }
}
