package com.modoocrm.modoocrm.domain.family.service;

import com.modoocrm.modoocrm.api.family.dto.FamilyRegisterDto;
import com.modoocrm.modoocrm.domain.family.entity.Family;

public interface FamilyService {
    void registerFamily(FamilyRegisterDto familyRegisterDto);

    void  deleteFamily(Long familyId);

    Family getFamilyInfo(Long clientId);
}
