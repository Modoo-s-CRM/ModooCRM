package com.modoocrm.modoocrm.api.family.controller;

import com.modoocrm.modoocrm.api.family.dto.FamilyRegisterDto;
import com.modoocrm.modoocrm.domain.family.service.FamilyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/family")
@RestController
public class FamilyController {

    private final FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @PostMapping
    public ResponseEntity registerFamily(@RequestBody FamilyRegisterDto familyRegisterDto){
        familyService.registerFamily(familyRegisterDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
