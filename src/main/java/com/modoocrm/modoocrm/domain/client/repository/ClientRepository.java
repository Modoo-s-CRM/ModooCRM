package com.modoocrm.modoocrm.domain.client.repository;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {

    Optional<Client> findByClientId(Long clientId);
}
