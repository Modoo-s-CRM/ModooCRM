package com.modoocrm.modoocrm.domain.client.repository;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {

}
