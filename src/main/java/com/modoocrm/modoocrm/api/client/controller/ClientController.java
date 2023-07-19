package com.modoocrm.modoocrm.api.client.controller;

import com.modoocrm.modoocrm.api.client.dto.ClientRegisterDto;
import com.modoocrm.modoocrm.api.client.mapper.ClientMapper;
import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RequestMapping("/api")
@RestController
public class ClientController {

    private final ClientMapper clientMapper;
    private final ClientService clientService;

    public ClientController(ClientMapper clientMapper, ClientService clientService) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }

    @PostMapping("/client")
    public ResponseEntity registerClient(@Valid @RequestBody ClientRegisterDto clientRegisterDto){
        String counselor = clientRegisterDto.getCounselorName();
        Client saveClient = clientMapper.clientRegisterDtoToClient(clientRegisterDto);
        clientService.registerClient(saveClient,counselor);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PatchMapping("/client/{client-id}")
    public ResponseEntity updateClient(@Valid @RequestBody ClientRegisterDto clientRegisterDto,
                                       @Positive @PathVariable("client-id") Long clientId){
        String counselor = clientRegisterDto.getCounselorName();
        Client saveClient = clientMapper.clientRegisterDtoToClient(clientRegisterDto);
        clientService.updateClient(saveClient,clientId,counselor);
        //무한재귀 문제 -> mapper 사용
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
