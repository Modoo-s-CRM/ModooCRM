package com.modoocrm.modoocrm.domain.counselor.entity;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@Entity
@Table(name = "COUNSELOR")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Counselor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counselor_id")
    private Long counselorId;

    @Column(nullable = false)
    private String counselorName;

    @Column(nullable = false)
    private String counselorGender;

    @OneToMany(mappedBy = "counselor")
    private List<Client> clients = new ArrayList<>();

    @Builder
    public Counselor(Long counselorId,String counselorName, String counselorGender){
        this.counselorId = counselorId;
        this.counselorName = counselorName;
        this.counselorGender = counselorGender;
    }

}
