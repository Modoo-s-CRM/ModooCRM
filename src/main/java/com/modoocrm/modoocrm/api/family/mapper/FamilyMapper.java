package com.modoocrm.modoocrm.api.family.mapper;

import com.modoocrm.modoocrm.api.family.dto.FamilyInfoRepDto;
import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.family.entity.Family;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FamilyMapper {

    public FamilyInfoRepDto familyToFamilyInfoRepDto(Family family){

        List<Client> familyClient = family.getClients();

        List<FamilyInfoRepDto.FamilyMember> familyMembers = new ArrayList<>();
        for (Client client : familyClient){
            FamilyInfoRepDto.FamilyMember familyMember = new FamilyInfoRepDto.FamilyMember();
            familyMember.setName(client.getClientName());
            familyMember.setAge(client.getAge());
            familyMember.setPhone(client.getPhone());
            familyMember.setJob(client.getJob().getJobGroup());

            familyMembers.add(familyMember);
        }

        FamilyInfoRepDto familyInfoRepDto = new FamilyInfoRepDto();
        familyInfoRepDto.setFamilyMembers(familyMembers);
        familyInfoRepDto.setHouseHolder(family.getHouseHolder());
        familyInfoRepDto.setFamilySpecialNote(family.getFamilySpecialNote());

        return familyInfoRepDto;
    }


}
