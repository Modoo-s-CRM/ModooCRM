package com.modoocrm.modoocrm.api.client.controller;

import com.modoocrm.modoocrm.api.client.dto.ClientRegisterReqDto;
import com.modoocrm.modoocrm.api.client.dto.ClientUpdateDto;
import com.modoocrm.modoocrm.api.client.service.ApiClientService;
import com.modoocrm.modoocrm.domain.counselor.entity.Counselor;
import com.modoocrm.modoocrm.domain.parents.entity.Parents;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor // 요구하는 매개변수 , AllArgsConstructor 모든 매개변수
public class ApiClientController {

    private final ApiClientService apiClientService;

    @PostMapping
    public String registerClient(@RequestBody ClientRegisterReqDto clientRegisterReqDto){
        apiClientService.registerClient(clientRegisterReqDto);
        return "registration success";
    }

    @PutMapping("{clientId}")
    public String updateClient(@PathVariable Long clientId, ClientUpdateDto clientUpdateDto){
        apiClientService.updateClient(clientId,clientUpdateDto);
        return "update success";
    }

    @DeleteMapping("{clientId}")
    public String deleteClient(@PathVariable Long clientId){
        apiClientService.deleteClient(clientId);
        return "delete success";
    }
}
