package com.modoocrm.modoocrm.domain.family.service;

import com.modoocrm.modoocrm.api.family.dto.FamilyRegisterDto;
import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.repository.ClientRepository;
import com.modoocrm.modoocrm.domain.client.service.ClientService;
import com.modoocrm.modoocrm.domain.family.entity.Family;
import com.modoocrm.modoocrm.domain.family.repository.FamilyRepository;
import com.modoocrm.modoocrm.global.error.exception.BusinessLogicException;
import com.modoocrm.modoocrm.global.error.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
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
        Client fatherClient = clientService.findClientName(father);
        clients.add(fatherClient);

        String mother = familyRegisterDto.getMotherName();
        Client motherClient = clientService.findClientName(mother);
        clients.add(motherClient);

        List<String> childrens = familyRegisterDto.getChildren();
        for (int i = 0; i < childrens.size(); i++){
            String childrenName = childrens.get(i);
            clients.add(clientService.findClientName(childrenName));
        }

        Family family = Family.builder()
                .houseHolder(familyRegisterDto.getHouseHolder())
                .familySpecialNote(familyRegisterDto.getFamilySpecialNote())
                .clients(clients)
                .build();

        familyRepository.save(family);

        for (Client client : clients){
            client.setFamily(family);
            clientRepository.save(client);
        }

    }

    @Override
    public void deleteFamily(Long familyId) {
        Family findFamily = this.findVerifiedFamily(familyId);
        findFamily.removeClients();
        familyRepository.delete(findFamily);
    }

    @Override
    public Family getFamilyInfo(Long clientId) {
        Client findClient = clientService.findVerifiedClient(clientId);
        if (findClient.getFamily() == null){
            throw new BusinessLogicException(ExceptionCode.FAMILY_NOT_REGISTER);
        }
        return findClient.getFamily();
    }

    private Family findVerifiedFamily(Long familyId){
        return familyRepository.findById(familyId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.FAMILY_NOT_FOUND)
        );
    }
}
