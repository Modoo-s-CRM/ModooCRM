package com.modoocrm.modoocrm.domain.family.entity;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Table(name = "family")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyId;

    @Setter
    @Column
    private String houseHolder;

    @Setter
    @Column
    private String familySpecialNote;

    @Setter
    @OneToMany(mappedBy = "family")
    private List<Client> clients = new ArrayList<>();

    @Builder
    public Family(String houseHolder,String familySpecialNote, List<Client> clients){
        this.houseHolder = houseHolder;
        this.familySpecialNote = familySpecialNote;
        this.clients = clients;
    }

    public void removeClients(){
        for (Client client : clients){
            client.setFamily(null);
        }
        clients.clear();
    }
}
