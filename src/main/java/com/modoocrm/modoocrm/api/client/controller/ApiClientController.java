package com.modoocrm.modoocrm.api.client.controller;

import com.modoocrm.modoocrm.api.client.dto.ClientRegisterReqDto;
import com.modoocrm.modoocrm.api.client.service.ApiClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor // 요구하는 매개변수 , AllArgsConstructor 모든 매개변수
public class ApiClientController {

    private final ApiClientService apiClientService;

    @PostMapping
    public String registerClient(@RequestBody ClientRegisterReqDto clientRegisterReqDto){
        System.out.println("이름 : " + clientRegisterReqDto.getClientName());
        System.out.println("나이 : " + clientRegisterReqDto.getAge());
        apiClientService.registerClient(clientRegisterReqDto);
        return "registration success";
    }

//    public Parents tempParents(){
//        Parents parents = new Parents();
//        parents.setParentsId(37L);
//        parents.setParentsName("부모님");
//        parents.setJob("개발");
//        parents.setEducation("중퇴");
//        parents.setTogether("동거");
//        return parents;
//    }
//
//    public Counselor tempCounselor(){
//        Counselor counselor = new Counselor();
//        counselor.setCounselorId(37L);
//        counselor.setCounselorName("김기영");
//        counselor.setCounselorGender(false);
//        return counselor;
//    }
}
