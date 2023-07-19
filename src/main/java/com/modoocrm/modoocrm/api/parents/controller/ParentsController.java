package com.modoocrm.modoocrm.api.parents.controller;

import com.modoocrm.modoocrm.api.parents.dto.ParentsRegisterDto;
import com.modoocrm.modoocrm.api.parents.mapper.ParentsMapper;
import com.modoocrm.modoocrm.domain.parents.entity.Parents;
import com.modoocrm.modoocrm.domain.parents.service.ParentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@RequestMapping("/api")
@RestController
public class ParentsController {

    private final ParentsMapper parentsMapper;
    private final ParentsService parentsService;

    public ParentsController(ParentsMapper parentsMapper, ParentsService parentsService) {
        this.parentsMapper = parentsMapper;
        this.parentsService = parentsService;
    }

    @PostMapping("/parents/{client-id}")
    public ResponseEntity registerParents(@Valid @RequestBody ParentsRegisterDto parentsRegisterDto,
                                          @Positive @PathVariable("client-id") Long clientId){
        Parents saveParents = parentsMapper.parentsRegisterDtoToParents(parentsRegisterDto);
        parentsService.registerParents(saveParents, clientId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/parents/{client-id}")
    public ResponseEntity getParents(@Positive @PathVariable("client-id") Long clientId){
        Parents findParents = parentsService.getParents(clientId);
        return new ResponseEntity(parentsMapper.parentsToParentsResponseDto(findParents),HttpStatus.OK);
    }
}
