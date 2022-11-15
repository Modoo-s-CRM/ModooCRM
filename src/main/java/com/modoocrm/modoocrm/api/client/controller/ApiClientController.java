package com.modoocrm.modoocrm.api.client.controller;

import com.modoocrm.modoocrm.api.client.service.ApiClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ApiClientController {

    private final ApiClientService apiClientService;

    @PostMapping("/management")
    public String registerClient(){

    }
}
