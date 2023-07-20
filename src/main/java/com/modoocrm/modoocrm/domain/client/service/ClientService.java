package com.modoocrm.modoocrm.domain.client.service;

import com.modoocrm.modoocrm.domain.client.entity.Client;

import java.util.List;

public interface ClientService {

    void registerClient(Client client, String counselor);

    Client updateClient(Client client, Long clientId,String counselor);

    List<Client> searchClient(String keyword);

    Client findVerifiedClient(Long client);

    Client setClientIfPresent(Client client, Client findClient);
}
