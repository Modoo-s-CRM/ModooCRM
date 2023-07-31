package com.modoocrm.modoocrm.api.family.controller;

import com.modoocrm.modoocrm.api.family.dto.FamilyRegisterDto;
import com.modoocrm.modoocrm.domain.family.service.FamilyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

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

    @DeleteMapping("/{family-id}")
    public ResponseEntity deleteFamily(@Positive @PathVariable("family-id") Long familyId){
        familyService.deleteFamily(familyId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
