package com.modoocrm.modoocrm.domain.parents.service;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.service.ClientService;
import com.modoocrm.modoocrm.domain.parents.entity.Parents;
import com.modoocrm.modoocrm.domain.parents.repository.ParentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class parentsServiceImpl implements ParentsService{

    private final ClientService clientService;
    private final ParentsRepository parentsRepository;

    public parentsServiceImpl(ClientService clientService, ParentsRepository parentsRepository) {
        this.clientService = clientService;
        this.parentsRepository = parentsRepository;
    }

    @Override
    public void registerParents(Parents parents, Long clientId) {
        Client client = clientService.findVerifiedClient(clientId);
        parents.setClient(client);
        parentsRepository.save(parents);
    }

    @Override
    public Parents getParents(Long clientId) {
        Client client = clientService.findVerifiedClient(clientId);
        Parents findParents = client.getParents();
        return findParents;
    }
}
