package com.modoocrm.modoocrm.domain.client.service;

import com.modoocrm.modoocrm.domain.client.entity.Client;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ClientService {

    void registerClient(Client client, String counselor, String job);

    void updateClient(Client client, Long clientId,String counselor, String job);

    List<Client> searchClient(String keyword);

    Client getClientInfo(Long clientId);

    List<Client> searchFIlterClient(String counselType, String counselorName, String ageGroup,
                                    String clientGender, String counselProgress, String startDate, String endDate);

    Client findVerifiedClient(Long client);

    Client setClientIfPresent(Client client, Client findClient);

    int monthFirstCounselCount(LocalDateTime startDate, LocalDateTime endDate);

    int adultCount(LocalDateTime startDate, LocalDateTime endDate);

    int marriedCoupleCount(LocalDateTime startDate, LocalDateTime endDate);

    int coupleCount(LocalDateTime startDate, LocalDateTime endDate);

    int familyCount(LocalDateTime startDate, LocalDateTime endDate);

    int youthCount(LocalDateTime startDate, LocalDateTime endDate);

    int antenatalCount(LocalDateTime startDate, LocalDateTime endDate);

    int groupCount(LocalDateTime startDate, LocalDateTime endDate);

    List<Client> clientsInYear(LocalDateTime startDate, LocalDateTime endDate);

    List<Client> clientsInYearAndCure(LocalDateTime startDate, LocalDateTime endDate);

    Client findClientName(String name);

    void saveClient(Client client);

    List<Client> getThisMonthFirstCounselClient(LocalDateTime startMonth, LocalDateTime endMonth);

    Map<Client.SymptomGrade, Long> monthSymptomGradeCounts(LocalDateTime startDate, LocalDateTime endDate);

}
