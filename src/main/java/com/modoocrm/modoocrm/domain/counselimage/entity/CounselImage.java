package com.modoocrm.modoocrm.domain.counselimage.entity;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import lombok.*;

import javax.persistence.*;

@ToString
@Entity
@Getter
@Table(name = "COUNSELIMAGE")
@NoArgsConstructor
public class CounselImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "counsel_image_id")
    private Long counselImageId;

    @Setter
    @Column
    private String applicationFormOriginalFileName; //상담신청서 파일 이름

    @Setter
    @Column
    private String applicationFormImagePath; //상담신청서 경로

    @Setter
    @Column
    private String selfAptitudeOriginalFileName; //자기적성진단표 파일 이름

    @Setter
    @Column
    private String selfAptitudeImagePath; //자기적성진단표 경로

    @Setter
    @Column
    private String landscapeOriginalFileName;

    @Setter
    @Column
    private String landscapeImagePath; /// 그림 경로

    @Setter
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Builder
    public CounselImage(String applicationFormOriginalFileName,
            String applicationFormImagePath,
                        String selfAptitudeOriginalFileName,
                        String selfAptitudeImagePath,
                        String landscapeOriginalFileName,
                        String landscapeImagePath,
                        Client client){
        this.applicationFormOriginalFileName = applicationFormOriginalFileName;
        this.applicationFormImagePath = applicationFormImagePath;
        this.selfAptitudeOriginalFileName = selfAptitudeOriginalFileName;
        this.selfAptitudeImagePath = selfAptitudeImagePath;
        this.landscapeOriginalFileName = landscapeOriginalFileName;
        this.landscapeImagePath = landscapeImagePath;
        this.client = client;
    }

}
