package com.modoocrm.modoocrm.domain.counselimage.service;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.service.ClientServiceImpl;
import com.modoocrm.modoocrm.domain.counselimage.entity.CounselImage;
import com.modoocrm.modoocrm.domain.counselimage.repository.CounselImageRepository;
import com.modoocrm.modoocrm.global.error.exception.BusinessLogicException;
import com.modoocrm.modoocrm.global.error.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Transactional
@Service
public class CounselImageServiceImpl implements CounselImageService {

    private final CounselImageRepository counselImageRepository;
    private final ClientServiceImpl clientService;
    private final ImgService imgService;

    public CounselImageServiceImpl(CounselImageRepository counselImageRepository, ClientServiceImpl clientService, ImgService imgService) {
        this.counselImageRepository = counselImageRepository;
        this.clientService = clientService;
        this.imgService = imgService;
    }

    @Override
    public void uploadCounselImage(Long clientId, List<MultipartFile> multipartFile) {
        Client client = clientService.findVerifiedClient(clientId);
        //DB에 각 이미지 링크들을 저장
        try {
            String applicationFormOriginalFileName = multipartFile.get(0).getOriginalFilename();
            String applicationFormImagePath = this.saveImageAndGetLink(client,"상담신청서",multipartFile.get(0));
            String selfAptitudeOriginalFileName = multipartFile.get(1).getOriginalFilename();
            String selfAptitudeImagePath = this.saveImageAndGetLink(client,"자기적성진단표",multipartFile.get(1));
            String landScapeOriginalFileName = multipartFile.get(2).getOriginalFilename();
            String landScapeImagePath = this.saveImageAndGetLink(client,"그림",multipartFile.get(2));

            CounselImage counselImage = CounselImage.builder()
                    .applicationFormOriginalFileName(applicationFormOriginalFileName)
                    .applicationFormImagePath(applicationFormImagePath)
                    .selfAptitudeOriginalFileName(selfAptitudeOriginalFileName)
                    .selfAptitudeImagePath(selfAptitudeImagePath)
                    .landscapeOriginalFileName(landScapeOriginalFileName)
                    .landscapeImagePath(landScapeImagePath)
                    .build();

            counselImage.setClient(client);
            counselImageRepository.save(counselImage);
        }catch (IOException e){
            throw new BusinessLogicException(ExceptionCode.IMAGE_UPLOAD_FAILED);
        }
    }

    private String saveImageAndGetLink(Client client, String imageType, MultipartFile multipartFile) throws IOException {
        String folderPath = imgService.createClientFolder(client);
        String fileName = imgService.getClientImageFileName(client,imageType);
        String filePath = folderPath + File.separator + fileName;

        imgService.saveImageToFolder(filePath,multipartFile);
        return imgService.generateImageLink(client,fileName,folderPath);
    }

    @Override
    public void deleteCounselImage(Long clientId) {
        Client client = clientService.findVerifiedClient(clientId);
        Long counselImgId = client.getCounselImage().getCounselImageId();
        CounselImage counselImage = this.findVerifiedCounselImg(counselImgId);

        //이미지 파일 경로를 가져오자
        String applicationFormImagePath = counselImage.getApplicationFormImagePath();
        String selfAptitudeImagePath = counselImage.getSelfAptitudeImagePath();
        String landscapeImagePath = counselImage.getLandscapeImagePath();

        //이미지 파일 삭제
        deleteImageFile(applicationFormImagePath);
        deleteImageFile(selfAptitudeImagePath);
        deleteImageFile(landscapeImagePath);

        //폴더 삭제
        String folderPath = getFolderPath(applicationFormImagePath);
        deleteFolder(folderPath);

        counselImageRepository.delete(counselImage);

    }

    private CounselImage findVerifiedCounselImg(Long counselImgId){
        return counselImageRepository.findById(counselImgId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.IMAGE_NOT_FOUND)
        );
    }

    //이미지 파일 삭제 로직
    private void deleteImageFile(String imagePath){
        if (imagePath != null){
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                boolean isDeleted = imageFile.delete();
                if (!isDeleted){
                    throw new BusinessLogicException(ExceptionCode.IMAGE_FILE_NOT_DELETE);
                }
            }
        }
    }

    //이미지 폴더 삭제
    private void deleteFolder(String folderPath){
        if (folderPath != null){
            File folder = new File(folderPath);
            if (folder.exists() && folder.isDirectory()){
                File[] files = folder.listFiles();
                if (files != null){
                    for (File file : files){
                        boolean isDeleted = file.delete();
                        if (!isDeleted){
                            throw new BusinessLogicException(ExceptionCode.IMAGE_FILE_NOT_DELETE);
                        }
                    }
                }
                boolean isDeleted = folder.delete();
                if (!isDeleted){
                    throw new BusinessLogicException(ExceptionCode.IMAGE_FILE_NOT_DELETE);
                }
            }
        }
    }

    private String getFolderPath(String imagePath){
        if (imagePath != null){
            File imageFile = new File(imagePath);
            File parentFolder = imageFile.getParentFile();
            return  parentFolder != null ? parentFolder.getAbsolutePath() : null;
        }
        return null;
    }

}
