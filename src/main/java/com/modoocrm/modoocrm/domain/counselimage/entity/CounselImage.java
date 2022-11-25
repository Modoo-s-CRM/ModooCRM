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
@Table(name = "COUSELIMAGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CounselImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "counselImage_id")
    private Long counselImageId;

    @Column
    private String applicationForm;

    @Column
    private String selfaptitude;

    @Column
    private String landscape;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
