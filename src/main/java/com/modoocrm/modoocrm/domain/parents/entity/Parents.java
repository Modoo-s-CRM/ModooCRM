package com.modoocrm.modoocrm.domain.parents.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;


@ToString
@Entity
@Getter
@Setter
@Table(name = "PARENTS")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Parents {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parents_id")
    private Long parentsId;

    @Column(nullable = false)
    private String parentsName;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String education;

    @Column(nullable = false)
    private String job;

    //동거 여부
    @Column(nullable = false)
    private String together;

}
