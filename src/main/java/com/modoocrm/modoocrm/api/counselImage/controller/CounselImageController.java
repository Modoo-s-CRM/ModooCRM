package com.modoocrm.modoocrm.api.counselImage.controller;

import com.modoocrm.modoocrm.domain.counselimage.service.CounselImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Positive;
import java.util.List;

@RequestMapping("/api")
@RestController
public class CounselImageController {

    private final CounselImageService counselImageService;

    public CounselImageController(CounselImageService counselImageService) {
        this.counselImageService = counselImageService;
    }

    @PostMapping("/{client-id}/img")
    public ResponseEntity uploadCounselImage(@Positive @PathVariable("client-id") Long clientId,
                                             @RequestPart(value = "images") List<MultipartFile> multipartFile){
        counselImageService.uploadCounselImage(clientId,multipartFile);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{client-id}/img")
    public ResponseEntity deleteCounselImage(@Positive @PathVariable("client-id") Long clientId){
        counselImageService.deleteCounselImage(clientId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
