package com.modoocrm.modoocrm.domain.client.repository;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long>, ClientCustomRepository{

    //search
    List<Client> findByClientNameContaining(String keyword);

    int countByFirstCounselBetween(LocalDateTime startDate, LocalDateTime endDate);

    Optional<Client> findByClientName(String name);


}
