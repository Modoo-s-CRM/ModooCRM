package com.modoocrm.modoocrm.domain.family.service;

import com.modoocrm.modoocrm.api.family.dto.FamilyRegisterDto;

public interface FamilyService {
    void registerFamily(FamilyRegisterDto familyRegisterDto);

    void  deleteFamily(Long familyId);
}
