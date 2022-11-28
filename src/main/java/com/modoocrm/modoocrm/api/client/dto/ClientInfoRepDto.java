package com.modoocrm.modoocrm.api.client.dto;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.counseldiary.entity.CounselDiary;
import com.modoocrm.modoocrm.domain.counselimage.entity.CounselImage;
import com.modoocrm.modoocrm.domain.counselor.entity.Counselor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientInfoRepDto {

    private Client client;

    private Counselor counselor;

    private CounselImage counselImage;

    private List<CounselDiary> counselDiaryList;
}
