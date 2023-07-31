package com.modoocrm.modoocrm.domain.family.service;

import com.modoocrm.modoocrm.api.family.dto.FamilyRegisterDto;
import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.repository.ClientRepository;
import com.modoocrm.modoocrm.domain.client.service.ClientService;
import com.modoocrm.modoocrm.domain.family.entity.Family;
import com.modoocrm.modoocrm.domain.family.repository.FamilyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamilyServiceImpl implements FamilyService{

    private final FamilyRepository familyRepository;
    private final ClientService clientService;
    private final ClientRepository clientRepository;

    public FamilyServiceImpl(FamilyRepository familyRepository, ClientService clientService, ClientRepository clientRepository) {
        this.familyRepository = familyRepository;
        this.clientService = clientService;
        this.clientRepository = clientRepository;
    }

    @Override
    public void registerFamily(FamilyRegisterDto familyRegisterDto) {

        List<Client> clients = new ArrayList<>();

        String father = familyRegisterDto.getFatherName();
        clients.add(clientService.findClientName(father));

        String mother = familyRegisterDto.getMotherName();
        clients.add(clientService.findClientName(mother));

        List<String> childrens = familyRegisterDto.getChildren();
        for (int i = 0; i < childrens.size(); i++){
            String childrenName = childrens.get(i);
            clients.add(clientService.findClientName(childrenName));
        }

        Family family = Family.builder()
                .familySpecialNote(familyRegisterDto.getFamilySpecialNote())
                .clients(clients)
                .build();

        familyRepository.save(family);

        for (Client client : clients){
            client.setFamily(family);
            clientRepository.save(client);
        }

    }
}
