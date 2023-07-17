package com.modoocrm.modoocrm.api.client.controller;

import com.modoocrm.modoocrm.api.client.dto.ClientRegisterDto;
import com.modoocrm.modoocrm.api.client.mapper.ClientMapper;
import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        Client saveClient = clientMapper.clientRegisterDtoToClient(clientRegisterDto);
        clientService.registerClient(saveClient);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public String hi(){
        return "hi";
    }
}
