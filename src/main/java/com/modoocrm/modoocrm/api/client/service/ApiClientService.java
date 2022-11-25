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
        clientService.registerClient(client);
    }


}
