package com.modoocrm.modoocrm.api.client.service;

import com.modoocrm.modoocrm.api.client.dto.ClientRegisterReqDto;
import com.modoocrm.modoocrm.api.client.dto.ClientUpdateDto;
import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.repository.ClientRepository;
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
    private final ClientRepository clientRepository;

    public void registerClient(ClientRegisterReqDto clientRegisterReqDto) {
        Client client = clientRegisterReqDto.toEntity();
        clientService.registerClient(client);
    }

    public void updateClient(Long clientId, ClientUpdateDto clientUpdateDto){
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new IllegalArgumentException("해당 내담자는 존재하지 않습니다."));
        Client changeClient = clientUpdateDto.toEntity();
        changeClient.setClientId(client.getClientId());
        clientService.updateClient(changeClient);
    }

    public void deleteClient(Long clientId){
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new IllegalArgumentException("해당 내담자는 존재하지 않습니다."));
        clientService.deleteClient(client);
    }



}
