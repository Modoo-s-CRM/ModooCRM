package com.modoocrm.modoocrm.domain.client.service;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.repository.ClientRepository;
import com.modoocrm.modoocrm.domain.counselor.entity.Counselor;
import com.modoocrm.modoocrm.domain.parents.entity.Parents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;



    @Override
    public void registerClient(Client client){
        clientRepository.save(client);
    }

    @Override
    public void updateClient(Client client){
        clientRepository.save(client);
    }

    @Override
    public void deleteClient(Client client){
        clientRepository.delete(client);
    }
}
