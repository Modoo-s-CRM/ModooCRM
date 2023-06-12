package com.codestates.todoapp.member.entity;

import com.codestates.todoapp.config.Timestamped;
import com.codestates.todoapp.todo.entity.Todo;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@SQLDelete(sql = "UPDATE MEMBER SET deleted_at = CURRENT_TIMESTAMP WHERE MEMBER_ID = ?")
@Where(clause = "deleted_at IS NULL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long memberId;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String gender;

    @OneToMany(mappedBy = "member",cascade = CascadeType.PERSIST)
    private List<Todo> todos = new ArrayList<>();

    //사용자 등록 시, 사용자의 권한을 등록하기 위한 권한 테이블
    @Setter
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public Member(String username, String password, String gender) {
        this.username = username;
        this.password = password;
        this.gender = gender;
    }
}
