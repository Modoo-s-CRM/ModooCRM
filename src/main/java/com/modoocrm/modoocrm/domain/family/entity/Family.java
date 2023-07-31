package com.modoocrm.modoocrm.domain.family.entity;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Table(name = "family")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyId;

    @Column
    private String familySpecialNote;

    @OneToMany(mappedBy = "family")
    private List<Client> clients = new ArrayList<>();

    @Builder
    public Family(String familySpecialNote, List<Client> clients){
        this.familySpecialNote = familySpecialNote;
        this.clients = clients;
    }
}
