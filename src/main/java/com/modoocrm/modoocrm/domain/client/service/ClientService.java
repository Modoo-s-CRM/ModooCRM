package com.modoocrm.modoocrm.domain.client.service;

import com.modoocrm.modoocrm.domain.client.entity.Client;

import java.time.LocalDateTime;
import java.util.List;

public interface ClientService {

    void registerClient(Client client, String counselor);

    Client updateClient(Client client, Long clientId,String counselor);

    List<Client> searchClient(String keyword);

    Client getClientInfo(Long clientId);

    Client findVerifiedClient(Long client);

    Client setClientIfPresent(Client client, Client findClient);

    int monthFirstCounselCount(LocalDateTime startDate, LocalDateTime endDate);

    int adultCount(LocalDateTime startDate, LocalDateTime endDate);

    int marriedCoupleCount(LocalDateTime startDate, LocalDateTime endDate);

    int coupleCount(LocalDateTime startDate, LocalDateTime endDate);

    int familyCount(LocalDateTime startDate, LocalDateTime endDate);

    int youthCount(LocalDateTime startDate, LocalDateTime endDate);

    int antenatalCount(LocalDateTime startDate, LocalDateTime endDate);

    List<Client> clientsInYear(LocalDateTime startDate, LocalDateTime endDate);
}
