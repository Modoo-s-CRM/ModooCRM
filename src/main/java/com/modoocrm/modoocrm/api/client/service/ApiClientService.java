package com.modoocrm.modoocrm.api.client.service;

import com.modoocrm.modoocrm.api.client.dto.ClientRegisterReqDto;
import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.service.ClientService;
import com.modoocrm.modoocrm.domain.counselor.entity.Counselor;
import com.modoocrm.modoocrm.domain.parents.entity.Parents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ApiClientService {

    private final ClientService clientService;

    public void registerClient(ClientRegisterReqDto clientRegisterReqDto) {
        Client client = clientRegisterReqDto.toEntity();
//        Client client = new Client(clientRegisterReqDto.getClientName(),
//                clientRegisterReqDto.getBirth(),
//                clientRegisterReqDto.getAge(),
//                clientRegisterReqDto.isClientGender(),
//                clientRegisterReqDto.getAddress(),
//                clientRegisterReqDto.getPhone(),
//                clientRegisterReqDto.getHobby(),
//                clientRegisterReqDto.getHeight(),
//                clientRegisterReqDto.getWeight(),
//                clientRegisterReqDto.getEducation(),
//                clientRegisterReqDto.getMarry(),
//                clientRegisterReqDto.getJob(),
//                clientRegisterReqDto.getCounselType(),
//                clientRegisterReqDto.getCounselMethod(),
//                clientRegisterReqDto.getInflowPath(),
//                clientRegisterReqDto.getSymptom(),
//                clientRegisterReqDto.getCounselHistory(),
//                clientRegisterReqDto.getCounselProgress(),
//                clientRegisterReqDto.getSpecialNote()
//        );

//        client.getParents().setParentsId(38L);
//        client.getParents().setParentsName("이용만");
//        client.getParents().setAge(28);
//        client.getParents().setEducation("대졸");
//        client.getParents().setJob("개발자");
//        client.getParents().setTogether("동거중");
//
//        client.getCounselor().setCounselorId(38L);
//        client.getCounselor().setCounselorName("안자미");
//        client.getCounselor().setCounselorGender(false);
//
        clientService.registerClient(client);

//        System.out.println("******service아이디" + client.getCounselor().getCounselorName());
    }


}
