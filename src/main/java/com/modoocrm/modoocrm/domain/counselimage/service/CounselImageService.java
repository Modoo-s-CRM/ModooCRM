package com.modoocrm.modoocrm.domain.counselimage.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CounselImageService {

    void uploadCounselImage(Long clientId, List<MultipartFile> multipartFile);

    void deleteCounselImage(Long clientId);
}
