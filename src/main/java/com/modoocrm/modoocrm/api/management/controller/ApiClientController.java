package com.modoocrm.modoocrm.api.management.controller;

import com.modoocrm.modoocrm.api.management.service.ApiClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/management")
@RequiredArgsConstructor
public class ApiClientController {

    private final ApiClientService apiClientService;

    @PostMapping
    public String registerClient(){

    }
}
