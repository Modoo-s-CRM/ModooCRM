package com.modoocrm.modoocrm.api.main.controller;

import com.modoocrm.modoocrm.api.main.dto.MainRepDto;
import com.modoocrm.modoocrm.domain.main.service.MainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/api/main")
    public ResponseEntity getMain(@RequestParam(name = "date") String date){
        MainRepDto mainRepDto = mainService.getMainInfo(date);
        return new ResponseEntity(mainRepDto, HttpStatus.OK);
    }
}
