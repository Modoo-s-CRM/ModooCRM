package com.modoocrm.modoocrm.api.client.controller;

import com.modoocrm.modoocrm.api.client.dto.ClientRegisterDto;
import com.modoocrm.modoocrm.api.client.mapper.ClientMapper;
import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.service.ClientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RequestMapping("/api/client")
@RestController
public class ClientController {

    private final ClientMapper clientMapper;
    private final ClientServiceImpl clientService;

    public ClientController(ClientMapper clientMapper, ClientServiceImpl clientService) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity registerClient(@Valid @RequestBody ClientRegisterDto clientRegisterDto){
        String counselor = clientRegisterDto.getCounselorName();
        Client saveClient = clientMapper.clientRegisterDtoToClient(clientRegisterDto);
        clientService.registerClient(saveClient,counselor);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PatchMapping("/{client-id}")
    public ResponseEntity updateClient(@Valid @RequestBody ClientRegisterDto clientRegisterDto,
                                       @Positive @PathVariable("client-id") Long clientId){
        String counselor = clientRegisterDto.getCounselorName();
        Client saveClient = clientMapper.clientRegisterDtoToClient(clientRegisterDto);
        clientService.updateClient(saveClient,clientId,counselor);
        //무한재귀 문제 -> response 요청시 mapper 사용
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/info/{client-id}")
    public ResponseEntity getClientInfo(@Positive @PathVariable("client-id") Long clientId){
        Client findClient = clientService.getClientInfo(clientId);
        return new ResponseEntity(clientMapper.clientToClientInfoResponseDto(findClient),HttpStatus.OK);
    }

    //Todo 검색어 자동 완성 -> Advance한 내용..
    @GetMapping("/lookup")
    public ResponseEntity searchClient(@RequestParam("keyword") String keyword){
        List<Client> clients = clientService.searchClient(keyword);
        return new ResponseEntity(clientMapper.clientSearchResponseDtos(clients),HttpStatus.OK);
    }
}
