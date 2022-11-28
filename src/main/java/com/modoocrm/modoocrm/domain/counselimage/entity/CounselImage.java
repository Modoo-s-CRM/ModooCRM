package com.modoocrm.modoocrm.domain.counselimage.entity;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Entity
@Getter
@Table(name = "COUNSELIMAGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CounselImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "counsel_image_id")
    private Long counselImageId;

    @Column
    private String applicationForm;

    @Column
    private String selfAptitude;

    @Column
    private String landscape;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
